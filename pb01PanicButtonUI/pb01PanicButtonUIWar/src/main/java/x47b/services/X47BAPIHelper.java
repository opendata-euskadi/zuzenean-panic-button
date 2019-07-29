/**
 *
 */
package x47b.services;

import java.util.Collection;

import lombok.extern.slf4j.Slf4j;
import r01f.locale.Language;
import r01f.model.persistence.PersistenceException;
import r01f.model.search.SearchResults;
import r01f.types.TimeLapse;
import r01f.util.types.collections.CollectionUtils;
import x47b.client.api.X47BPanicButtonClientAPI;
//import r01f.securitycontext.SecurityContext;
import x47b.client.api.X47BPanicButtonClientAPIProvider;
import x47b.model.X47BAlarmEvent;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionServiceLocationID;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrganizationID;
import x47b.model.oids.X47BOrganizationalIDs.X47BWorkPlaceID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceLocationOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrganizationOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BWorkPlaceOID;
import x47b.model.org.X47BOrgDivisionServiceLocation;
import x47b.model.org.X47BOrganization;
import x47b.model.org.X47BWorkPlace;
import x47b.model.org.summaries.X47BSummarizedOrgDivisionServiceLocation;
import x47b.model.org.summaries.X47BSummarizedOrganization;
import x47b.model.org.summaries.X47BSummarizedWorkPlace;
import x47b.model.search.X47BSearchFilterForPanicButtonOrganizationalEntity;
import x47b.model.search.X47BSearchResultItemForPanicButtonOrganizationalEntity;

/**
 * API services helper.
 * @author PCI. Internet
 * @since 2.0
 */
@Slf4j
public class X47BAPIHelper {
///////////////////////////////////////////////////////////////////////////////////////////
//  SINGLETON
///////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * The current API services helper.
	 */
	private static X47BAPIHelper _apiSingletonInstance;

/////////////////////////////////////////////////////////////////////////////////////////
//  API
/////////////////////////////////////////////////////////////////////////////////////////
	private X47BPanicButtonClientAPI _api;

/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Private Constructor.
	 */
	public X47BAPIHelper() {
		_api = X47BPanicButtonClientAPIProvider.getDefaultApi();
	}
	/**
	 * Return the API helper class.
	 * @return
	 */
	public static synchronized final X47BAPIHelper getInstance() {
		if (_apiSingletonInstance == null) {
			_apiSingletonInstance = new X47BAPIHelper();
		}
		return _apiSingletonInstance;
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  BUSQUEDA
/////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Búsqueda del entidades por Organización
	 * @param orgOid oid de la Organización {@link X47BOrganizationOID}
	 * @return {@link SearchResults<X47BSearchFilterForEntityModelObject,
	 *         X47BSearchResultItemForEntityModelObject>}
	 */
	public SearchResults<X47BSearchFilterForPanicButtonOrganizationalEntity, X47BSearchResultItemForPanicButtonOrganizationalEntity> searchOrganizationEntities(final X47BSearchFilterForPanicButtonOrganizationalEntity filter,
																																			  int firstRow,
																																			  int numRows) {



		log.debug("Cargando "+numRows+" REGISTROS desde la FILA:"+firstRow);
		final SearchResults<X47BSearchFilterForPanicButtonOrganizationalEntity,
							X47BSearchResultItemForPanicButtonOrganizationalEntity> searchResults = _api.entitySearchAPI()
																								.search(filter)
																								.fromItemAt(firstRow)
																								.returning(numRows);

		log.debug("--->Found: {} items searching by {}",searchResults.getTotalItemsCount(),
														filter.toCriteriaString());
		if (searchResults.hasData()) {
			for (X47BSearchResultItemForPanicButtonOrganizationalEntity item : searchResults.getPageItems()) {
				log.debug("\t> {} ({})",item.getHiearchyPath(),item.getModelObjectType());


				log.debug("-------------------------- RESULT NAME ES -------------" +  item.getWorkPlace().getName());

				if (item.getLastAlarmRaiseDate() != null) {
					log.debug(item.getLastAlarmRaiseDate().toString());
				} else {
					log.debug("NULA");
				}
			}
		}

		return searchResults;
	}

	/**
	 * Obltiene una colleción de objetos {@link X47BAlarmEvent} de una entidad en un lapso de tiempo
	 * @param resultItem
	 * @return alarms
	 */
	public Collection<X47BAlarmEvent> getAlarms(final X47BSearchResultItemForPanicButtonOrganizationalEntity resultItem) {

		X47BAlarmEventSourceID alarmSourceID = null;

		if (resultItem.getModelObjectType() == X47BWorkPlace.class) {
			alarmSourceID = resultItem.getWorkPlace().getId();
		} else {
			alarmSourceID = resultItem.getLocation().getId();
		}

		return _api.alarmEventsAPI()
				.getForFind()
				.findBySourceId(alarmSourceID, TimeLapse.createFor("30d"));


	}
/////////////////////////////////////////////////////////////////////////////////////////
//  CARGA
/////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Carga de las organizaciónes
	 * @return {@link Collection<X47BSummarizedOrganization>}
	 */
	public Collection<X47BSummarizedOrganization> loadOrganizations() {
		Collection<X47BSummarizedOrganization> orgSummaries = _api.organizationsAPI()
																		.getForFind()
																			.findSummaries(Language.SPANISH);
		return orgSummaries;
	}
	/**
	 * Carga de las localizaciones de una organización
	 * @return {@link Collection<X47BSummarizedOrganization>}
	 */
	public Collection<X47BSummarizedOrgDivisionServiceLocation> loadLocations(final X47BOrganizationOID orgOID) {
		Collection<X47BSummarizedOrgDivisionServiceLocation> locSummaries = _api.orgDivisionServiceLocationsAPI()
																					.getForFind()
																						.findSummariesByOrganization(orgOID,
																													 Language.SPANISH);
		if (CollectionUtils.hasData(locSummaries)) {
			for (X47BSummarizedOrgDivisionServiceLocation locSum : locSummaries) {
				log.debug("\t-{}: {} (oid={})",locSum.getId(),
											   locSum.getName(),
											   locSum.getOid());
			}
		}
		return locSummaries;
	}

	/**
	 * Carga de los lugares de trabajo  de una localozación en una organización
	 * @return {@link Collection<X47BSummarizedOrganization>}
	 */
	public Collection<X47BSummarizedWorkPlace> loadWorkPlaces(final X47BOrgDivisionServiceLocationOID locOID) {
		Collection<X47BSummarizedWorkPlace> workPlacesSummaries = _api.workPlacesAPI()
																		.getForFind()
																			.findSummariesByOrgDivisionServiceLocation(locOID,
																								 				   Language.SPANISH);

		if (CollectionUtils.hasData(workPlacesSummaries)) {
			for (X47BSummarizedWorkPlace workPlaceSum : workPlacesSummaries) {
				log.debug("\t-{}: {} (oid={})",workPlaceSum.getId(),
											   workPlaceSum.getName(),
											   workPlaceSum.getOid());
			}
		}
		return workPlacesSummaries;
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  ORGANIZATIONS
/////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Actualiza o crea una Organización
	 * @param org {@link X47BOrganization} a guardar
	 * @return {@link X47BOrganization}
	 */
	public X47BOrganization saveOrganization(final X47BOrganization org) {
		try {
			return _api.organizationsAPI()
							.getForCRUD()
								.save(org);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	/**
	 * Carga una organización
	 * @param orgOid oid de la Organización {@link X47BOrganizationOID}
	 * @return {@link X47BOrganization}
	 */
	public X47BOrganization loadOrganization(final X47BOrganizationOID orgOid) {
		return _api.organizationsAPI()
						.getForCRUD()
							.load(orgOid);
	}

	/**
	 * Elimina una organización
	 * @param orgOid oid de la Organización {@link X47BOrganizationOID}
	 * @return {@link X47BOrganization}
	 */
	public X47BOrganization deleteOrganization(final X47BOrganizationOID orgOid) {
		try {
			return _api.organizationsAPI()
							.getForCRUD()
								.delete(orgOid);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * Carga una organización por ID
	 * @param orgID ID de la Organización {@link X47BOrganizationID}
	 * @return {@link X47BOrganization}
	 */
	public X47BOrganization loadOrganizationByID(final X47BOrganizationID orgID) {
		try {
			return _api.organizationsAPI()
							.getForCRUD()
								.loadById(orgID);
		} catch (PersistenceException ex) {
			//No existe
			ex.printStackTrace();
			return null;
		}
	}


/////////////////////////////////////////////////////////////////////////////////////////
//  LOCATIONS
/////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Carga de las organizaciónes
	 * @return {@link Collection<X47BSummarizedOrganization>}
	 */
	public Collection<X47BSummarizedOrganization> loadLocations() {
		Collection<X47BSummarizedOrganization> orgSummaries = _api.organizationsAPI()
																		.getForFind()
																			.findSummaries(Language.SPANISH);
		return orgSummaries;
	}

	/**
	 * Create or update the location entity.
	 * @param org the {@link X47BLocation} to save.
	 * @return the {@link X47BLocation} object.
	 */
	public X47BOrgDivisionServiceLocation saveLocation(final X47BOrgDivisionServiceLocation loc) {
		try {
			return _api.orgDivisionServiceLocationsAPI()
							.getForCRUD()
								.save(loc);
		} catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * Carga una localización
	 * @param locOid oid de la Localización {@link X47BLocationOID}
	 * @return {@link X47BLocationOID}
	 */
	public X47BOrgDivisionServiceLocation loadLocation(final X47BOrgDivisionServiceLocationOID locOid) {
		try {
			return _api.orgDivisionServiceLocationsAPI()
							.getForCRUD()
								.load(locOid);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * Elimina una localización
	 * @param locOid oid de la Organización {@link X47BLocationOID}
	 * @return {@link X47BLocationOID}
	 */
	public X47BOrgDivisionServiceLocation deleteLocation(final X47BOrgDivisionServiceLocationOID locOid) {
		try {
			return _api.orgDivisionServiceLocationsAPI()
							.getForCRUD()
								.delete(locOid);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * Carga una localización por ID
	 * @param locationID ID de la localización {@link X47BLocationID}
	 * @return {@link X47BLocation}
	 */
	public X47BOrgDivisionServiceLocation loadLocationByID(final X47BOrgDivisionServiceLocationID locationID) {
		try {
		return _api.orgDivisionServiceLocationsAPI()
						.getForCRUD()
							.loadById(locationID);
		} catch (PersistenceException ex) {
			ex.printStackTrace();
			return null;
		}
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  Work Places
/////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Actualiza o crea un lugar de trabajo
	 * @param workPlace {@link X47BWorkPlace} a guardar
	 * @return {@link X47BWorkPlace}
	 */
	public X47BWorkPlace saveWorkPlace(final X47BWorkPlace workPlace) {
		try {
			return _api.workPlacesAPI()
							.getForCRUD()
								.save(workPlace);
		} catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	/**
	 * Carga un lugar de trabajo
	 * @param workPlaceOid oid de la lugar de trabajo {@link X47BWorkPlaceOID}
	 * @return {@link X47BWorkPlaceOID}
	 */
	public X47BWorkPlace loadWorkPlace(final X47BWorkPlaceOID workPlaceOid) {
		return _api.workPlacesAPI()
						.getForCRUD()
							.load(workPlaceOid);
	}
	/**
	 * Elimina una lugar de trabajo
	 * @param workPlaceOid oid de la Organización {@link X47BWorkPlaceOID}
	 * @return {@link X47BWorkPlaceOID}
	 */
	public X47BWorkPlace deleteWorkPlace(final X47BWorkPlaceOID workPlaceOid) {
		try {
			return _api.workPlacesAPI()
							.getForCRUD()
								.delete(workPlaceOid);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * Carga un lugar de trabajo por ID
	 * @param workPlaceId ID de la Organización {@link X47BWorkPlaceID}
	 * @return {@link X47BWorkPlace}
	 */
	public X47BWorkPlace loadWorkPlaceByID(final X47BWorkPlaceID workPlaceId) {
		try {
			return _api.workPlacesAPI()					
							.getForCRUD()
								.loadById(workPlaceId);

		} catch (PersistenceException ex) {
			ex.printStackTrace();
			return null;
		}
	}
}
