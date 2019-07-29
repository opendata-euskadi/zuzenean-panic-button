package x47b.db.crud;

import javax.persistence.EntityManager;

import r01f.model.persistence.PersistencePerformedOperation;
import r01f.objectstreamer.Marshaller;
import r01f.persistence.db.config.DBModuleConfig;
import r01f.persistence.db.entities.primarykeys.DBPrimaryKeyForModelObjectImpl;
import r01f.securitycontext.SecurityContext;
import x47b.api.interfaces.X47BCRUDServicesForOrgDivisionServiceLocation;
import x47b.db.entities.X47BDBEntityForOrgDivision;
import x47b.db.entities.X47BDBEntityForOrgDivisionService;
import x47b.db.entities.X47BDBEntityForOrgDivisionServiceLocation;
import x47b.db.entities.X47BDBEntityForOrganization;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionServiceLocationID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceLocationOID;
import x47b.model.org.X47BOrgDivisionServiceLocation;

/**
 * Persistence layer
 */
public class X47BDBCRUDForOrgDivisionServiceLocation
	 extends X47BDBCRUDForOrganizationalEntityBase<X47BOrgDivisionServiceLocationOID,X47BOrgDivisionServiceLocationID,X47BOrgDivisionServiceLocation,
	 				        X47BDBEntityForOrgDivisionServiceLocation>
  implements X47BCRUDServicesForOrgDivisionServiceLocation {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BDBCRUDForOrgDivisionServiceLocation(final DBModuleConfig dbCfg,
												   final EntityManager entityManager,
												   final Marshaller marshaller) {
		super(X47BOrgDivisionServiceLocation.class,X47BDBEntityForOrgDivisionServiceLocation.class,
			  dbCfg,
			  entityManager,
			  marshaller);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void setDBEntityFieldsFromModelObject(final SecurityContext securityContext,
												 final X47BOrgDivisionServiceLocation loc,final X47BDBEntityForOrgDivisionServiceLocation dbEntity) {
		super.setDBEntityFieldsFromModelObject(securityContext, 
											   loc,dbEntity);
		// org reference
		dbEntity.setOrganizationOid(loc.getOrgRef().getOid().asString());
		dbEntity.setOrganizationId(loc.getOrgRef().getId().asString());
		
		// division reference
		dbEntity.setOrgDivisionOid(loc.getOrgDivisionRef().getOid().asString());		
		dbEntity.setOrgDivisionId(loc.getOrgDivisionRef().getId().asString());
		
		// service reference
		dbEntity.setOrgDivisionServiceOid(loc.getOrgDivisionServiceRef().getOid().asString());		
		dbEntity.setOrgDivisionServiceId(loc.getOrgDivisionServiceRef().getId().asString());
		
		// hierarchy level
		dbEntity.setHierarchyLevel(4);	// used to return ordered results when searching (see X47BDBSearcherForEntityModelObject)
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void completeDBEntityBeforeCreateOrUpdate(final SecurityContext securityContext,
													 final PersistencePerformedOperation performedOp, 
													 final X47BOrgDivisionServiceLocation modelObj,final X47BDBEntityForOrgDivisionServiceLocation dbLocation) {
		// load the organization, division & service entities
		X47BDBEntityForOrganization dbOrg = this.getEntityManager().find(X47BDBEntityForOrganization.class,
																		 new DBPrimaryKeyForModelObjectImpl(dbLocation.getOrganizationOid()));
		X47BDBEntityForOrgDivision dbDivision = this.getEntityManager().find(X47BDBEntityForOrgDivision.class,
																	 		 new DBPrimaryKeyForModelObjectImpl(dbLocation.getOrgDivisionOid()));
		X47BDBEntityForOrgDivisionService dbService = this.getEntityManager().find(X47BDBEntityForOrgDivisionService.class,
																	 		 	   new DBPrimaryKeyForModelObjectImpl(dbLocation.getOrgDivisionServiceOid()));
		// set the dependencies
		dbLocation.setOrganization(dbOrg);
		dbLocation.setOrgDivision(dbDivision);
		dbLocation.setOrgDivisionService(dbService);
		
		// setting the location's dependent objects (org /division / service), also modifies the later since it's a BI-DIRECTIONAL relation
		// ... so the entity manager MUST be refreshed in order to avoid an optimistic locking exception
		this.getEntityManager().refresh(dbOrg);
		this.getEntityManager().refresh(dbDivision);
		this.getEntityManager().refresh(dbService);
	}
}
