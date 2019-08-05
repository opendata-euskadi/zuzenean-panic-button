package x47b.db.search;

import com.google.common.base.Function;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import r01f.locale.Language;
import r01f.objectstreamer.Marshaller;
import r01f.persistence.search.db.TransformsDBEntityToSearchResultItem;
import r01f.securitycontext.SecurityContext;
import x47b.db.entities.X47BDBEntityForOrgDivision;
import x47b.db.entities.X47BDBEntityForOrgDivisionService;
import x47b.db.entities.X47BDBEntityForOrgDivisionServiceLocation;
import x47b.db.entities.X47BDBEntityForOrganization;
import x47b.db.entities.X47BDBEntityForOrganizationalEntityBase;
import x47b.db.entities.X47BDBEntityForWorkPlace;
import x47b.model.org.X47BOrgDivision;
import x47b.model.org.X47BOrgDivisionService;
import x47b.model.org.X47BOrgDivisionServiceLocation;
import x47b.model.org.X47BOrganization;
import x47b.model.org.X47BOrganizationalPersistableObjectBase;
import x47b.model.org.X47BWorkPlace;
import x47b.model.org.summaries.X47BSummarizedObject;
import x47b.model.org.summaries.X47BSummarizedOrgDivision;
import x47b.model.org.summaries.X47BSummarizedOrgDivisionService;
import x47b.model.org.summaries.X47BSummarizedOrgDivisionServiceLocation;
import x47b.model.org.summaries.X47BSummarizedOrganization;
import x47b.model.org.summaries.X47BSummarizedWorkPlace;
import x47b.model.search.X47BSearchResultItemForPanicButtonOrganizationalEntity;

@Slf4j
@RequiredArgsConstructor
public class X47BDBEntityToSearchResultItemTransformerForPanicButtonOrganizationalEntity
  implements TransformsDBEntityToSearchResultItem<X47BDBEntityForOrganizationalEntityBase,X47BSearchResultItemForPanicButtonOrganizationalEntity> {
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
	public Function<X47BDBEntityForOrganizationalEntityBase,
					X47BSearchResultItemForPanicButtonOrganizationalEntity> asTransformFuncion(final SecurityContext securityContext,
																				 			   final Language lang) {
		return new Function<X47BDBEntityForOrganizationalEntityBase,X47BSearchResultItemForPanicButtonOrganizationalEntity>() {
						@Override
						public X47BSearchResultItemForPanicButtonOrganizationalEntity apply(final X47BDBEntityForOrganizationalEntityBase dbEntity) {
							return X47BDBEntityToSearchResultItemTransformerForPanicButtonOrganizationalEntity.this.dbEntityToSearchResultItem(securityContext,
																		  									 								   dbEntity,
																		  									 								   lang);
						}
			   };
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public X47BSearchResultItemForPanicButtonOrganizationalEntity dbEntityToSearchResultItem(final SecurityContext securityContext,
																			   				 final X47BDBEntityForOrganizationalEntityBase dbEntity,
																			   				 final Language lang) {
		final X47BSearchResultItemForPanicButtonOrganizationalEntity outItem = new X47BSearchResultItemForPanicButtonOrganizationalEntity();

		Class<? extends X47BOrganizationalPersistableObjectBase<?,?,?>> modelObjType = null;

		// [1] - object type & summaries
		if (dbEntity instanceof X47BDBEntityForOrganization) {
			outItem.unsafeSetModelObjectType(X47BOrganization.class);
			outItem.setOrganization(_orgSummaryIn(dbEntity,lang));

			modelObjType = X47BOrganization.class;
		}
		else if (dbEntity instanceof X47BDBEntityForOrgDivision) {
			outItem.unsafeSetModelObjectType(X47BOrgDivision.class);
			outItem.setOrganization(_orgSummaryIn(dbEntity,lang));
			outItem.setOrgDivision(_divisionSummaryIn(dbEntity,lang));

			modelObjType = X47BOrgDivision.class;
		}
		else if (dbEntity instanceof X47BDBEntityForOrgDivisionService) {
			outItem.unsafeSetModelObjectType(X47BOrgDivisionService.class);
			outItem.setOrganization(_orgSummaryIn(dbEntity,lang));
			outItem.setOrgDivision(_divisionSummaryIn(dbEntity,lang));
			outItem.setOrgDivisionService(_serviceSummaryIn(dbEntity,lang));

			modelObjType = X47BOrgDivisionService.class;
		}
		else if (dbEntity instanceof X47BDBEntityForOrgDivisionServiceLocation) {
			outItem.unsafeSetModelObjectType(X47BOrgDivisionServiceLocation.class);
			outItem.setOrganization(_orgSummaryIn(dbEntity,lang));
			outItem.setOrgDivision(_divisionSummaryIn(dbEntity,lang));
			outItem.setOrgDivisionService(_serviceSummaryIn(dbEntity,lang));
			outItem.setOrgDivisionServiceLocation(_locationSummaryIn(dbEntity,lang));

			modelObjType = X47BOrgDivisionServiceLocation.class;
		}
		else if (dbEntity instanceof X47BDBEntityForWorkPlace) {
			outItem.unsafeSetModelObjectType(X47BWorkPlace.class);
			outItem.setOrganization(_orgSummaryIn(dbEntity,lang));
			outItem.setOrgDivision(_divisionSummaryIn(dbEntity,lang));
			outItem.setOrgDivisionService(_serviceSummaryIn(dbEntity,lang));
			outItem.setOrgDivisionServiceLocation(_locationSummaryIn(dbEntity,lang));
			outItem.setWorkPlace(_workPlaceSummaryIn(dbEntity,lang));

			modelObjType = X47BWorkPlace.class;
		}

		// [2] - Name (it's also at the summary for the object type)
		outItem.setName(dbEntity.getName());

		// [3] - Alarm data
		if (dbEntity.getLastAlarmRaiseDate() != null) outItem.setLastAlarmRaiseDate(dbEntity.getLastAlarmRaiseDate().getTime());
		outItem.setAlarmRaiseCount(dbEntity.getAlarmRaiseCount());

		// [4] - Phones & emails
		final X47BOrganizationalPersistableObjectBase<?,?,?> modelObj = _marshaller.forReading().fromXml(dbEntity.getDescriptor(),
																							 modelObjType);
		outItem.setPhones(modelObj.getPhones());
		outItem.setEmails(modelObj.getEmails());

		return outItem;
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	@SuppressWarnings("unchecked")
	private <S extends X47BSummarizedObject<?,?,?>> S _orgModelObjectSummary(final X47BDBEntityForOrganizationalEntityBase dbEntity,
										 			 						 final Language lang) {
		// guess the model obj type
		Class<? extends X47BOrganizationalPersistableObjectBase<?,?,?>> modelObjType = null;
		if (dbEntity instanceof X47BDBEntityForOrganization) {
			modelObjType = X47BOrganization.class;
		}
		else if (dbEntity instanceof X47BDBEntityForOrgDivision) {
			modelObjType = X47BOrgDivision.class;
		}
		else if (dbEntity instanceof X47BDBEntityForOrgDivisionService) {
			modelObjType = X47BOrgDivisionService.class;
		}
		else if (dbEntity instanceof X47BDBEntityForOrgDivisionServiceLocation) {
			modelObjType = X47BOrgDivisionServiceLocation.class;
		}
		else if (dbEntity instanceof X47BDBEntityForWorkPlace) {
			modelObjType = X47BWorkPlace.class;
		}
		else {
			throw new IllegalArgumentException(dbEntity + " is NOT a supported db entity!");
		}

		// Create a summary from the dbEntity: transform it to a model object and get it summarized
		final X47BOrganizationalPersistableObjectBase<?,?,?> modelObject = _marshaller.forReading().fromXml(dbEntity.getDescriptor(),
																						 					modelObjType);
		return (S)modelObject.getSummarizedIn(lang);
	}
	private X47BSummarizedOrganization _orgSummaryIn(final X47BDBEntityForOrganizationalEntityBase dbEntity,
										 			 final Language lang) {
		X47BDBEntityForOrganization dbOrg = null;
		if (dbEntity instanceof X47BDBEntityForOrganization) {
			dbOrg = dbEntity.as(X47BDBEntityForOrganization.class);
		}
		else if (dbEntity instanceof X47BDBEntityForOrgDivision) {
			final X47BDBEntityForOrgDivision dbDiv = dbEntity.as(X47BDBEntityForOrgDivision.class);
			dbOrg = dbDiv.getOrganization();
		}
		else if (dbEntity instanceof X47BDBEntityForOrgDivisionService) {
			final X47BDBEntityForOrgDivisionService dbService = dbEntity.as(X47BDBEntityForOrgDivisionService.class);
			dbOrg = dbService.getOrganization();
		}
		else if (dbEntity instanceof X47BDBEntityForOrgDivisionServiceLocation) {
			final X47BDBEntityForOrgDivisionServiceLocation dbServiceLoc = dbEntity.as(X47BDBEntityForOrgDivisionServiceLocation.class);
			dbOrg = dbServiceLoc.getOrganization();
		}
		else if (dbEntity instanceof X47BDBEntityForWorkPlace) {
			final X47BDBEntityForWorkPlace dbWorkPlace = dbEntity.as(X47BDBEntityForWorkPlace.class);
			dbOrg = dbWorkPlace.getOrganization();
		}
		if (dbOrg == null) 	log.error("The {} DB entity with oid={} does NOT have {} info!",
						  			  dbEntity.getClass(),dbEntity.getOid(),X47BDBEntityForOrganization.class);
		return _orgModelObjectSummary(dbOrg,lang);
	}
	private X47BSummarizedOrgDivision _divisionSummaryIn(final X47BDBEntityForOrganizationalEntityBase dbEntity,
												 		 final Language lang) {
		X47BDBEntityForOrgDivision dbDiv = null;
		if (dbEntity instanceof X47BDBEntityForOrgDivision) {
			dbDiv = dbEntity.as(X47BDBEntityForOrgDivision.class);
		}
		else if (dbEntity instanceof X47BDBEntityForOrgDivisionService) {
			final X47BDBEntityForOrgDivisionService dbService = dbEntity.as(X47BDBEntityForOrgDivisionService.class);
			dbDiv = dbService.getOrgDivision();
		}
		else if (dbEntity instanceof X47BDBEntityForOrgDivisionServiceLocation) {
			final X47BDBEntityForOrgDivisionServiceLocation dbServiceLoc = dbEntity.as(X47BDBEntityForOrgDivisionServiceLocation.class);
			dbDiv = dbServiceLoc.getOrgDivision();
		}
		else if (dbEntity instanceof X47BDBEntityForWorkPlace) {
			final X47BDBEntityForWorkPlace dbWorkPlace = dbEntity.as(X47BDBEntityForWorkPlace.class);
			dbDiv = dbWorkPlace.getOrgDivision();
		}
		if (dbDiv == null) 	log.error("The {} DB entity with oid={} does NOT have {} info!",
						  			  dbEntity.getClass(),dbEntity.getOid(),X47BDBEntityForOrgDivision.class);
		return _orgModelObjectSummary(dbDiv,lang);
	}
	private X47BSummarizedOrgDivisionService _serviceSummaryIn(final X47BDBEntityForOrganizationalEntityBase dbEntity,
										 					   final Language lang) {
		X47BDBEntityForOrgDivisionService dbService = null;
		if (dbEntity instanceof X47BDBEntityForOrgDivisionService) {
			dbService = dbEntity.as(X47BDBEntityForOrgDivisionService.class);
		}
		else if (dbEntity instanceof X47BDBEntityForOrgDivisionServiceLocation) {
			final X47BDBEntityForOrgDivisionServiceLocation dbServiceLoc = dbEntity.as(X47BDBEntityForOrgDivisionServiceLocation.class);
			dbService = dbServiceLoc.getOrgDivisionService();
		}
		else if (dbEntity instanceof X47BDBEntityForWorkPlace) {
			final X47BDBEntityForWorkPlace dbWorkPlace = dbEntity.as(X47BDBEntityForWorkPlace.class);
			dbService = dbWorkPlace.getOrgDivisionService();
		}
		if (dbService == null) 	log.error("The {} DB entity with oid={} does NOT have {} info!",
						  			      dbEntity.getClass(),dbEntity.getOid(),X47BDBEntityForOrgDivisionService.class);
		return _orgModelObjectSummary(dbService,lang);
	}
	private X47BSummarizedOrgDivisionServiceLocation _locationSummaryIn(final X47BDBEntityForOrganizationalEntityBase dbEntity,
										 					   		    final Language lang) {
		X47BDBEntityForOrgDivisionServiceLocation dbLocation = null;
		if (dbEntity instanceof X47BDBEntityForOrgDivisionServiceLocation) {
			dbLocation = dbEntity.as(X47BDBEntityForOrgDivisionServiceLocation.class);
		}
		else if (dbEntity instanceof X47BDBEntityForWorkPlace) {
			final X47BDBEntityForWorkPlace dbWorkPlace = dbEntity.as(X47BDBEntityForWorkPlace.class);
			dbLocation = dbWorkPlace.getOrgDivisionServiceLocation();
		}
		if (dbLocation == null) 	log.error("The {} DB entity with oid={} does NOT have {} info!",
						  			      	  dbEntity.getClass(),dbEntity.getOid(),X47BDBEntityForOrgDivisionServiceLocation.class);
		return _orgModelObjectSummary(dbLocation,lang);
	}
	private X47BSummarizedWorkPlace _workPlaceSummaryIn(final X47BDBEntityForOrganizationalEntityBase dbEntity,
											    		final Language lang) {
		X47BDBEntityForWorkPlace dbWorkPlace = null;
		if (dbEntity instanceof X47BDBEntityForWorkPlace) {
			dbWorkPlace = dbEntity.as(X47BDBEntityForWorkPlace.class);
		}
		if (dbWorkPlace == null) 	log.error("The {} DB entity with oid={} does NOT have {} info!",
						  			      dbEntity.getClass(),dbEntity.getOid(),X47BDBEntityForWorkPlace.class);
		return _orgModelObjectSummary(dbWorkPlace,lang);
	}
}
