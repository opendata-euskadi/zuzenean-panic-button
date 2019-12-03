package x47b.db.crud;

import javax.persistence.EntityManager;

import r01f.model.persistence.PersistencePerformedOperation;
import r01f.objectstreamer.Marshaller;
import r01f.persistence.db.config.DBModuleConfig;
import r01f.persistence.db.entities.primarykeys.DBPrimaryKeyForModelObjectImpl;
import r01f.securitycontext.SecurityContext;
import x47b.api.interfaces.X47BCRUDServicesForOrgDivisionService;
import x47b.db.entities.X47BDBEntityForOrgDivision;
import x47b.db.entities.X47BDBEntityForOrgDivisionService;
import x47b.db.entities.X47BDBEntityForOrganization;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionServiceID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceOID;
import x47b.model.org.X47BOrgDivisionService;

/**
 * Persistence layer
 */
public class X47BDBCRUDForOrgDivisionService
	 extends X47BDBCRUDForOrganizationalEntityBase<X47BOrgDivisionServiceOID,X47BOrgDivisionServiceID,X47BOrgDivisionService,
	 				        X47BDBEntityForOrgDivisionService>
  implements X47BCRUDServicesForOrgDivisionService {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BDBCRUDForOrgDivisionService(final DBModuleConfig dbCfg,
										   final EntityManager entityManager,
										   final Marshaller marshaller) {
		super(X47BOrgDivisionService.class,X47BDBEntityForOrgDivisionService.class,
			  dbCfg,
			  entityManager,
			  marshaller);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void setDBEntityFieldsFromModelObject(final SecurityContext securityContext, 
												 final X47BOrgDivisionService service,final X47BDBEntityForOrgDivisionService dbEntity) {
		super.setDBEntityFieldsFromModelObject(securityContext,
											   service,dbEntity);
		
		// org reference
		dbEntity.setOrganizationOid(service.getOrgRef().getOid().asString());
		dbEntity.setOrganizationId(service.getOrgRef().getId().asString());
		
		// division reference
		dbEntity.setOrgDivisionOid(service.getOrgDivisionRef().getOid().asString());		
		dbEntity.setOrgDivisionId(service.getOrgDivisionRef().getId().asString());
		
		// hierarchy level
		dbEntity.setHierarchyLevel(3);	// used to return ordered results when searching (see X47BDBSearcherForEntityModelObject)
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void completeDBEntityBeforeCreateOrUpdate(final SecurityContext securityContext,
													 final PersistencePerformedOperation performedOp, 
													 final X47BOrgDivisionService modelObj,final X47BDBEntityForOrgDivisionService dbService) {
		// load the organization & division entities
		X47BDBEntityForOrganization dbOrg = this.getEntityManager().find(X47BDBEntityForOrganization.class,
																		 new DBPrimaryKeyForModelObjectImpl(dbService.getOrganizationOid()));
		X47BDBEntityForOrgDivision dbDivision = this.getEntityManager().find(X47BDBEntityForOrgDivision.class,
																	 		 new DBPrimaryKeyForModelObjectImpl(dbService.getOrgDivisionOid()));
		// set the dependencies
		dbService.setOrganization(dbOrg);
		dbService.setOrgDivision(dbDivision);	
		
		// setting the service's dependent objects (org /division), also modifies the later since it's a BI-DIRECTIONAL relation
		// ... so the entity manager MUST be refreshed in order to avoid an optimistic locking exception
		this.getEntityManager().refresh(dbOrg);
		this.getEntityManager().refresh(dbDivision);
	}
}
