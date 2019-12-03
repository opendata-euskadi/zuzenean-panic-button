package x47b.db.crud;

import javax.persistence.EntityManager;

import r01f.model.persistence.PersistencePerformedOperation;
import r01f.objectstreamer.Marshaller;
import r01f.persistence.db.config.DBModuleConfig;
import r01f.persistence.db.entities.primarykeys.DBPrimaryKeyForModelObjectImpl;
import r01f.securitycontext.SecurityContext;
import x47b.api.interfaces.X47BCRUDServicesForWorkPlace;
import x47b.db.entities.X47BDBEntityForOrgDivision;
import x47b.db.entities.X47BDBEntityForOrgDivisionService;
import x47b.db.entities.X47BDBEntityForOrgDivisionServiceLocation;
import x47b.db.entities.X47BDBEntityForOrganization;
import x47b.db.entities.X47BDBEntityForWorkPlace;
import x47b.model.oids.X47BOrganizationalIDs.X47BWorkPlaceID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BWorkPlaceOID;
import x47b.model.org.X47BWorkPlace;

/**
 * Persistence layer
 */
public class X47BDBCRUDForWorkPlace
	 extends X47BDBCRUDForOrganizationalEntityBase<X47BWorkPlaceOID,X47BWorkPlaceID,X47BWorkPlace,
	 								 			   X47BDBEntityForWorkPlace>
  implements X47BCRUDServicesForWorkPlace {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BDBCRUDForWorkPlace(final DBModuleConfig dbCfg,
								  final EntityManager entityManager,
							      final Marshaller marshaller) {
		super(X47BWorkPlace.class,X47BDBEntityForWorkPlace.class,
			  dbCfg,
			  entityManager,
			  marshaller);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void setDBEntityFieldsFromModelObject(final SecurityContext securityContext,
												 final X47BWorkPlace workPlace,final X47BDBEntityForWorkPlace dbEntity) {
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
		
		
		dbEntity.setHierarchyLevel(5);	// used to return ordered results when searching (see X47BDBSearcherForEntityModelObject)	
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void completeDBEntityBeforeCreateOrUpdate(final SecurityContext securityContext,
													 final PersistencePerformedOperation performedOp, 
													 final X47BWorkPlace modelObj,final X47BDBEntityForWorkPlace dbWorkPlace) {
		// load the organization, division, service & location entities
		X47BDBEntityForOrganization dbOrg = this.getEntityManager().find(X47BDBEntityForOrganization.class,
																		 new DBPrimaryKeyForModelObjectImpl(dbWorkPlace.getOrganizationOid()));
		X47BDBEntityForOrgDivision dbDivision = this.getEntityManager().find(X47BDBEntityForOrgDivision.class,
																	 		 new DBPrimaryKeyForModelObjectImpl(dbWorkPlace.getOrgDivisionOid()));
		X47BDBEntityForOrgDivisionService dbService = this.getEntityManager().find(X47BDBEntityForOrgDivisionService.class,
																	 		 	   new DBPrimaryKeyForModelObjectImpl(dbWorkPlace.getOrgDivisionServiceOid()));
		X47BDBEntityForOrgDivisionServiceLocation dbLocation = this.getEntityManager().find(X47BDBEntityForOrgDivisionServiceLocation.class,
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
