package pb01c.db.crud;

import javax.persistence.EntityManager;

import pb01a.api.interfaces.PB01ACRUDServicesForWorkPlace;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AWorkPlaceID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AWorkPlaceOID;
import pb01a.model.org.PB01AWorkPlace;
import pb01c.db.entities.PB01CDBEntityForOrgDivision;
import pb01c.db.entities.PB01CDBEntityForOrgDivisionService;
import pb01c.db.entities.PB01CDBEntityForOrgDivisionServiceLocation;
import pb01c.db.entities.PB01CDBEntityForOrganization;
import pb01c.db.entities.PB01CDBEntityForWorkPlace;
import r01f.model.persistence.PersistencePerformedOperation;
import r01f.objectstreamer.Marshaller;
import r01f.persistence.db.config.DBModuleConfig;
import r01f.persistence.db.entities.primarykeys.DBPrimaryKeyForModelObjectImpl;
import r01f.securitycontext.SecurityContext;

/**
 * Persistence layer
 */
public class PB01CDBCRUDForWorkPlace
	 extends PB01CDBCRUDForOrganizationalEntityBase<PB01AWorkPlaceOID,PB01AWorkPlaceID,PB01AWorkPlace,
	 								 			   PB01CDBEntityForWorkPlace>
  implements PB01ACRUDServicesForWorkPlace {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01CDBCRUDForWorkPlace(final DBModuleConfig dbCfg,
								  final EntityManager entityManager,
							      final Marshaller marshaller) {
		super(PB01AWorkPlace.class,PB01CDBEntityForWorkPlace.class,
			  dbCfg,
			  entityManager,
			  marshaller);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void setDBEntityFieldsFromModelObject(final SecurityContext securityContext,
												 final PB01AWorkPlace workPlace,final PB01CDBEntityForWorkPlace dbEntity) {
		super.setDBEntityFieldsFromModelObject(securityContext,
											   workPlace,dbEntity);
		// org reference
		dbEntity.setOrganizationOid(workPlace.getOrgRef().getOid().asString());
		dbEntity.setOrganizationId(workPlace.getOrgRef().getId().asString());

		// division reference
		dbEntity.setOrgDivisionOid(workPlace.getOrgDivisionRef().getOid().asString());
		dbEntity.setOrgDivisionId(workPlace.getOrgDivisionRef().getId().asString());

		// service reference
		dbEntity.setOrgDivisionServiceOid(workPlace.getOrgDivisionServiceRef().getOid().asString());
		dbEntity.setOrgDivisionServiceId(workPlace.getOrgDivisionServiceRef().getId().asString());

		// location reference
		dbEntity.setOrgDivisionServiceLocationOid(workPlace.getOrgDivisionServiceLocationRef().getOid().asString());
		dbEntity.setOrgDivisionServiceLocationId(workPlace.getOrgDivisionServiceLocationRef().getId().asString());


		dbEntity.setHierarchyLevel(5);	// used to return ordered results when searching (see PB01CDBSearcherForEntityModelObject)
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void completeDBEntityBeforeCreateOrUpdate(final SecurityContext securityContext,
													 final PersistencePerformedOperation performedOp,
													 final PB01AWorkPlace modelObj,final PB01CDBEntityForWorkPlace dbWorkPlace) {
		// load the organization, division, service & location entities
		PB01CDBEntityForOrganization dbOrg = this.getEntityManager().find(PB01CDBEntityForOrganization.class,
																		 new DBPrimaryKeyForModelObjectImpl(dbWorkPlace.getOrganizationOid()));
		PB01CDBEntityForOrgDivision dbDivision = this.getEntityManager().find(PB01CDBEntityForOrgDivision.class,
																	 		 new DBPrimaryKeyForModelObjectImpl(dbWorkPlace.getOrgDivisionOid()));
		PB01CDBEntityForOrgDivisionService dbService = this.getEntityManager().find(PB01CDBEntityForOrgDivisionService.class,
																	 		 	   new DBPrimaryKeyForModelObjectImpl(dbWorkPlace.getOrgDivisionServiceOid()));
		PB01CDBEntityForOrgDivisionServiceLocation dbLocation = this.getEntityManager().find(PB01CDBEntityForOrgDivisionServiceLocation.class,
																	 		 	   			new DBPrimaryKeyForModelObjectImpl(dbWorkPlace.getOrgDivisionServiceLocationOid()));
		// set the dependencies
		dbWorkPlace.setOrganization(dbOrg);
		dbWorkPlace.setOrgDivision(dbDivision);
		dbWorkPlace.setOrgDivisionService(dbService);
		dbWorkPlace.setOrgDivisionServiceLocation(dbLocation);

		// setting the work place's dependent objects (org /division / service / location), also modifies the later since it's a BI-DIRECTIONAL relation
		// ... so the entity manager MUST be refreshed in order to avoid an optimistic locking exception
		this.getEntityManager().refresh(dbOrg);
		this.getEntityManager().refresh(dbDivision);
		this.getEntityManager().refresh(dbService);
		this.getEntityManager().refresh(dbLocation);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  EXTENSION METHODS
/////////////////////////////////////////////////////////////////////////////////////////
}
