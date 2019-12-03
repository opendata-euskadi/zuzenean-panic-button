package x47b.db.crud;

import javax.persistence.EntityManager;

import r01f.model.persistence.PersistencePerformedOperation;
import r01f.objectstreamer.Marshaller;
import r01f.persistence.db.config.DBModuleConfig;
import r01f.persistence.db.entities.primarykeys.DBPrimaryKeyForModelObjectImpl;
import r01f.securitycontext.SecurityContext;
import x47b.api.interfaces.X47BCRUDServicesForOrgDivision;
import x47b.db.entities.X47BDBEntityForOrgDivision;
import x47b.db.entities.X47BDBEntityForOrganization;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionOID;
import x47b.model.org.X47BOrgDivision;

/**
 * Persistence layer
 */
public class X47BDBCRUDForOrgDivision
	 extends X47BDBCRUDForOrganizationalEntityBase<X47BOrgDivisionOID,X47BOrgDivisionID,X47BOrgDivision,
	 											   X47BDBEntityForOrgDivision>
  implements X47BCRUDServicesForOrgDivision {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BDBCRUDForOrgDivision(final DBModuleConfig dbCfg,
									final EntityManager entityManager,
									final Marshaller marshaller) {
		super(X47BOrgDivision.class,X47BDBEntityForOrgDivision.class,
			  dbCfg,
			  entityManager,
			  marshaller);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void setDBEntityFieldsFromModelObject(final SecurityContext securityContext, 
												 final X47BOrgDivision div,final X47BDBEntityForOrgDivision dbEntity) {
		super.setDBEntityFieldsFromModelObject(securityContext, 
											   div,dbEntity);
		
		// Organization reference
		dbEntity.setOrganizationOid(div.getOrgRef().getOid().asString());
		dbEntity.setOrganizationId(div.getOrgRef().getId().asString());
		
		// hierarchy level
		dbEntity.setHierarchyLevel(2);	// used to return ordered results when searching (see X47BDBSearcherForEntityModelObject)
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void completeDBEntityBeforeCreateOrUpdate(final SecurityContext securityContext,
													 final PersistencePerformedOperation performedOp, 
													 final X47BOrgDivision modelObj,final X47BDBEntityForOrgDivision dbDivision) {
		// load the organization entity
		X47BDBEntityForOrganization dbOrg = this.getEntityManager().find(X47BDBEntityForOrganization.class,
																		 new DBPrimaryKeyForModelObjectImpl(dbDivision.getOrganizationOid()));
		// set the dependency
		dbDivision.setOrganization(dbOrg);
		
		// setting the division's dependent objects (org), also modifies the later since it's a BI-DIRECTIONAL relation
		// ... so the entity manager MUST be refreshed in order to avoid an optimistic locking exception
		this.getEntityManager().refresh(dbOrg);
	}
}
