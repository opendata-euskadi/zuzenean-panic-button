package pb01c.db.search;

import com.google.common.base.Function;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pb01a.model.org.PB01AOrgDivision;
import pb01a.model.org.PB01AOrgDivisionService;
import pb01a.model.org.PB01AOrgDivisionServiceLocation;
import pb01a.model.org.PB01AOrganization;
import pb01a.model.org.PB01AOrganizationalPersistableObjectBase;
import pb01a.model.org.PB01AWorkPlace;
import pb01a.model.org.summaries.PB01ASummarizedObject;
import pb01a.model.org.summaries.PB01ASummarizedOrgDivision;
import pb01a.model.org.summaries.PB01ASummarizedOrgDivisionService;
import pb01a.model.org.summaries.PB01ASummarizedOrgDivisionServiceLocation;
import pb01a.model.org.summaries.PB01ASummarizedOrganization;
import pb01a.model.org.summaries.PB01ASummarizedWorkPlace;
import pb01a.model.search.PB01ASearchResultItemForPanicButtonOrganizationalEntity;
import pb01c.db.entities.PB01CDBEntityForOrgDivision;
import pb01c.db.entities.PB01CDBEntityForOrgDivisionService;
import pb01c.db.entities.PB01CDBEntityForOrgDivisionServiceLocation;
import pb01c.db.entities.PB01CDBEntityForOrganization;
import pb01c.db.entities.PB01CDBEntityForOrganizationalEntityBase;
import pb01c.db.entities.PB01CDBEntityForWorkPlace;
import r01f.locale.Language;
import r01f.objectstreamer.Marshaller;
import r01f.persistence.search.db.TransformsDBEntityToSearchResultItem;
import r01f.securitycontext.SecurityContext;

@Slf4j
@RequiredArgsConstructor
public class PB01CDBEntityToSearchResultItemTransformerForPanicButtonOrganizationalEntity
  implements TransformsDBEntityToSearchResultItem<PB01CDBEntityForOrganizationalEntityBase,PB01ASearchResultItemForPanicButtonOrganizationalEntity> {
/////////////////////////////////////////////////////////////////////////////////////////
//  FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	private final Marshaller _marshaller;
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Returns this transformer as a {@link Function}
	 * @param securityContext
	 * @return
	 */
	public Function<PB01CDBEntityForOrganizationalEntityBase,
					PB01ASearchResultItemForPanicButtonOrganizationalEntity> asTransformFuncion(final SecurityContext securityContext,
																				 			   final Language lang) {
		return new Function<PB01CDBEntityForOrganizationalEntityBase,PB01ASearchResultItemForPanicButtonOrganizationalEntity>() {
						@Override
						public PB01ASearchResultItemForPanicButtonOrganizationalEntity apply(final PB01CDBEntityForOrganizationalEntityBase dbEntity) {
							return PB01CDBEntityToSearchResultItemTransformerForPanicButtonOrganizationalEntity.this.dbEntityToSearchResultItem(securityContext,
																		  									 								   dbEntity,
																		  									 								   lang);
						}
			   };
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public PB01ASearchResultItemForPanicButtonOrganizationalEntity dbEntityToSearchResultItem(final SecurityContext securityContext,
																			   				 final PB01CDBEntityForOrganizationalEntityBase dbEntity,
																			   				 final Language lang) {
		final PB01ASearchResultItemForPanicButtonOrganizationalEntity outItem = new PB01ASearchResultItemForPanicButtonOrganizationalEntity();

		Class<? extends PB01AOrganizationalPersistableObjectBase<?,?,?>> modelObjType = null;

		// [1] - object type & summaries
		if (dbEntity instanceof PB01CDBEntityForOrganization) {
			outItem.unsafeSetModelObjectType(PB01AOrganization.class);
			outItem.setOrganization(_orgSummaryIn(dbEntity,lang));

			modelObjType = PB01AOrganization.class;
		}
		else if (dbEntity instanceof PB01CDBEntityForOrgDivision) {
			outItem.unsafeSetModelObjectType(PB01AOrgDivision.class);
			outItem.setOrganization(_orgSummaryIn(dbEntity,lang));
			outItem.setOrgDivision(_divisionSummaryIn(dbEntity,lang));

			modelObjType = PB01AOrgDivision.class;
		}
		else if (dbEntity instanceof PB01CDBEntityForOrgDivisionService) {
			outItem.unsafeSetModelObjectType(PB01AOrgDivisionService.class);
			outItem.setOrganization(_orgSummaryIn(dbEntity,lang));
			outItem.setOrgDivision(_divisionSummaryIn(dbEntity,lang));
			outItem.setOrgDivisionService(_serviceSummaryIn(dbEntity,lang));

			modelObjType = PB01AOrgDivisionService.class;
		}
		else if (dbEntity instanceof PB01CDBEntityForOrgDivisionServiceLocation) {
			outItem.unsafeSetModelObjectType(PB01AOrgDivisionServiceLocation.class);
			outItem.setOrganization(_orgSummaryIn(dbEntity,lang));
			outItem.setOrgDivision(_divisionSummaryIn(dbEntity,lang));
			outItem.setOrgDivisionService(_serviceSummaryIn(dbEntity,lang));
			outItem.setOrgDivisionServiceLocation(_locationSummaryIn(dbEntity,lang));

			modelObjType = PB01AOrgDivisionServiceLocation.class;
		}
		else if (dbEntity instanceof PB01CDBEntityForWorkPlace) {
			outItem.unsafeSetModelObjectType(PB01AWorkPlace.class);
			outItem.setOrganization(_orgSummaryIn(dbEntity,lang));
			outItem.setOrgDivision(_divisionSummaryIn(dbEntity,lang));
			outItem.setOrgDivisionService(_serviceSummaryIn(dbEntity,lang));
			outItem.setOrgDivisionServiceLocation(_locationSummaryIn(dbEntity,lang));
			outItem.setWorkPlace(_workPlaceSummaryIn(dbEntity,lang));

			modelObjType = PB01AWorkPlace.class;
		}

		// [2] - Name (it's also at the summary for the object type)
		outItem.setName(dbEntity.getName());

		// [3] - Alarm data
		if (dbEntity.getLastAlarmRaiseDate() != null) outItem.setLastAlarmRaiseDate(dbEntity.getLastAlarmRaiseDate().getTime());
		outItem.setAlarmRaiseCount(dbEntity.getAlarmRaiseCount());

		// [4] - Phones & emails
		final PB01AOrganizationalPersistableObjectBase<?,?,?> modelObj = _marshaller.forReading().fromXml(dbEntity.getDescriptor(),
																							 modelObjType);
		outItem.setPhones(modelObj.getPhones());
		outItem.setEmails(modelObj.getEmails());

		return outItem;
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	@SuppressWarnings("unchecked")
	private <S extends PB01ASummarizedObject<?,?,?>> S _orgModelObjectSummary(final PB01CDBEntityForOrganizationalEntityBase dbEntity,
										 			 						 final Language lang) {
		// guess the model obj type
		Class<? extends PB01AOrganizationalPersistableObjectBase<?,?,?>> modelObjType = null;
		if (dbEntity instanceof PB01CDBEntityForOrganization) {
			modelObjType = PB01AOrganization.class;
		}
		else if (dbEntity instanceof PB01CDBEntityForOrgDivision) {
			modelObjType = PB01AOrgDivision.class;
		}
		else if (dbEntity instanceof PB01CDBEntityForOrgDivisionService) {
			modelObjType = PB01AOrgDivisionService.class;
		}
		else if (dbEntity instanceof PB01CDBEntityForOrgDivisionServiceLocation) {
			modelObjType = PB01AOrgDivisionServiceLocation.class;
		}
		else if (dbEntity instanceof PB01CDBEntityForWorkPlace) {
			modelObjType = PB01AWorkPlace.class;
		}
		else {
			throw new IllegalArgumentException(dbEntity + " is NOT a supported db entity!");
		}

		// Create a summary from the dbEntity: transform it to a model object and get it summarized
		final PB01AOrganizationalPersistableObjectBase<?,?,?> modelObject = _marshaller.forReading().fromXml(dbEntity.getDescriptor(),
																						 					modelObjType);
		return (S)modelObject.getSummarizedIn(lang);
	}
	private PB01ASummarizedOrganization _orgSummaryIn(final PB01CDBEntityForOrganizationalEntityBase dbEntity,
										 			 final Language lang) {
		PB01CDBEntityForOrganization dbOrg = null;
		if (dbEntity instanceof PB01CDBEntityForOrganization) {
			dbOrg = dbEntity.as(PB01CDBEntityForOrganization.class);
		}
		else if (dbEntity instanceof PB01CDBEntityForOrgDivision) {
			final PB01CDBEntityForOrgDivision dbDiv = dbEntity.as(PB01CDBEntityForOrgDivision.class);
			dbOrg = dbDiv.getOrganization();
		}
		else if (dbEntity instanceof PB01CDBEntityForOrgDivisionService) {
			final PB01CDBEntityForOrgDivisionService dbService = dbEntity.as(PB01CDBEntityForOrgDivisionService.class);
			dbOrg = dbService.getOrganization();
		}
		else if (dbEntity instanceof PB01CDBEntityForOrgDivisionServiceLocation) {
			final PB01CDBEntityForOrgDivisionServiceLocation dbServiceLoc = dbEntity.as(PB01CDBEntityForOrgDivisionServiceLocation.class);
			dbOrg = dbServiceLoc.getOrganization();
		}
		else if (dbEntity instanceof PB01CDBEntityForWorkPlace) {
			final PB01CDBEntityForWorkPlace dbWorkPlace = dbEntity.as(PB01CDBEntityForWorkPlace.class);
			dbOrg = dbWorkPlace.getOrganization();
		}
		if (dbOrg == null) 	log.error("The {} DB entity with oid={} does NOT have {} info!",
						  			  dbEntity.getClass(),dbEntity.getOid(),PB01CDBEntityForOrganization.class);
		return _orgModelObjectSummary(dbOrg,lang);
	}
	private PB01ASummarizedOrgDivision _divisionSummaryIn(final PB01CDBEntityForOrganizationalEntityBase dbEntity,
												 		 final Language lang) {
		PB01CDBEntityForOrgDivision dbDiv = null;
		if (dbEntity instanceof PB01CDBEntityForOrgDivision) {
			dbDiv = dbEntity.as(PB01CDBEntityForOrgDivision.class);
		}
		else if (dbEntity instanceof PB01CDBEntityForOrgDivisionService) {
			final PB01CDBEntityForOrgDivisionService dbService = dbEntity.as(PB01CDBEntityForOrgDivisionService.class);
			dbDiv = dbService.getOrgDivision();
		}
		else if (dbEntity instanceof PB01CDBEntityForOrgDivisionServiceLocation) {
			final PB01CDBEntityForOrgDivisionServiceLocation dbServiceLoc = dbEntity.as(PB01CDBEntityForOrgDivisionServiceLocation.class);
			dbDiv = dbServiceLoc.getOrgDivision();
		}
		else if (dbEntity instanceof PB01CDBEntityForWorkPlace) {
			final PB01CDBEntityForWorkPlace dbWorkPlace = dbEntity.as(PB01CDBEntityForWorkPlace.class);
			dbDiv = dbWorkPlace.getOrgDivision();
		}
		if (dbDiv == null) 	log.error("The {} DB entity with oid={} does NOT have {} info!",
						  			  dbEntity.getClass(),dbEntity.getOid(),PB01CDBEntityForOrgDivision.class);
		return _orgModelObjectSummary(dbDiv,lang);
	}
	private PB01ASummarizedOrgDivisionService _serviceSummaryIn(final PB01CDBEntityForOrganizationalEntityBase dbEntity,
										 					   final Language lang) {
		PB01CDBEntityForOrgDivisionService dbService = null;
		if (dbEntity instanceof PB01CDBEntityForOrgDivisionService) {
			dbService = dbEntity.as(PB01CDBEntityForOrgDivisionService.class);
		}
		else if (dbEntity instanceof PB01CDBEntityForOrgDivisionServiceLocation) {
			final PB01CDBEntityForOrgDivisionServiceLocation dbServiceLoc = dbEntity.as(PB01CDBEntityForOrgDivisionServiceLocation.class);
			dbService = dbServiceLoc.getOrgDivisionService();
		}
		else if (dbEntity instanceof PB01CDBEntityForWorkPlace) {
			final PB01CDBEntityForWorkPlace dbWorkPlace = dbEntity.as(PB01CDBEntityForWorkPlace.class);
			dbService = dbWorkPlace.getOrgDivisionService();
		}
		if (dbService == null) 	log.error("The {} DB entity with oid={} does NOT have {} info!",
						  			      dbEntity.getClass(),dbEntity.getOid(),PB01CDBEntityForOrgDivisionService.class);
		return _orgModelObjectSummary(dbService,lang);
	}
	private PB01ASummarizedOrgDivisionServiceLocation _locationSummaryIn(final PB01CDBEntityForOrganizationalEntityBase dbEntity,
										 					   		    final Language lang) {
		PB01CDBEntityForOrgDivisionServiceLocation dbLocation = null;
		if (dbEntity instanceof PB01CDBEntityForOrgDivisionServiceLocation) {
			dbLocation = dbEntity.as(PB01CDBEntityForOrgDivisionServiceLocation.class);
		}
		else if (dbEntity instanceof PB01CDBEntityForWorkPlace) {
			final PB01CDBEntityForWorkPlace dbWorkPlace = dbEntity.as(PB01CDBEntityForWorkPlace.class);
			dbLocation = dbWorkPlace.getOrgDivisionServiceLocation();
		}
		if (dbLocation == null) 	log.error("The {} DB entity with oid={} does NOT have {} info!",
						  			      	  dbEntity.getClass(),dbEntity.getOid(),PB01CDBEntityForOrgDivisionServiceLocation.class);
		return _orgModelObjectSummary(dbLocation,lang);
	}
	private PB01ASummarizedWorkPlace _workPlaceSummaryIn(final PB01CDBEntityForOrganizationalEntityBase dbEntity,
											    		final Language lang) {
		PB01CDBEntityForWorkPlace dbWorkPlace = null;
		if (dbEntity instanceof PB01CDBEntityForWorkPlace) {
			dbWorkPlace = dbEntity.as(PB01CDBEntityForWorkPlace.class);
		}
		if (dbWorkPlace == null) 	log.error("The {} DB entity with oid={} does NOT have {} info!",
						  			      dbEntity.getClass(),dbEntity.getOid(),PB01CDBEntityForWorkPlace.class);
		return _orgModelObjectSummary(dbWorkPlace,lang);
	}
}
