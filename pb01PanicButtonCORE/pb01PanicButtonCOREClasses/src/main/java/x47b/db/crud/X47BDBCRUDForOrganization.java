package x47b.db.crud;

import javax.persistence.EntityManager;

import r01f.model.persistence.PersistencePerformedOperation;
import r01f.objectstreamer.Marshaller;
import r01f.persistence.db.config.DBModuleConfig;
import r01f.securitycontext.SecurityContext;
import x47b.api.interfaces.X47BCRUDServicesForOrganization;
import x47b.db.entities.X47BDBEntityForOrganization;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrganizationID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrganizationOID;
import x47b.model.org.X47BOrganization;

/**
 * Persistence layer
 */
public class X47BDBCRUDForOrganization
	 extends X47BDBCRUDForOrganizationalEntityBase<X47BOrganizationOID,X47BOrganizationID,X47BOrganization,
	 											   X47BDBEntityForOrganization>
  implements X47BCRUDServicesForOrganization {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BDBCRUDForOrganization(final DBModuleConfig dbCfg,
									 final EntityManager entityManager,
									 final Marshaller marshaller) {
		super(X47BOrganization.class,X47BDBEntityForOrganization.class,
			  dbCfg,
			  entityManager,
			  marshaller);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void setDBEntityFieldsFromModelObject(final SecurityContext securityContext,
												 final X47BOrganization org,final X47BDBEntityForOrganization dbEntity) {
		super.setDBEntityFieldsFromModelObject(securityContext, 
											   org,dbEntity);
		// hierarchy level
		dbEntity.setHierarchyLevel(1);	// used to return ordered results when searching (see X47BDBSearcherForEntityModelObject)
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void completeDBEntityBeforeCreateOrUpdate(final SecurityContext securityContext,
													 final PersistencePerformedOperation performedOp, 
													 final X47BOrganization modelObj,final X47BDBEntityForOrganization dbEntity) {
		// nothing
	}
}
