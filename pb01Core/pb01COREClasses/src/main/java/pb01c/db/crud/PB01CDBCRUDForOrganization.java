package pb01c.db.crud;

import javax.persistence.EntityManager;

import pb01a.api.interfaces.PB01ACRUDServicesForOrganization;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrganizationID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrganizationOID;
import pb01a.model.org.PB01AOrganization;
import pb01c.db.entities.PB01CDBEntityForOrganization;
import r01f.model.persistence.PersistencePerformedOperation;
import r01f.objectstreamer.Marshaller;
import r01f.persistence.db.config.DBModuleConfig;
import r01f.securitycontext.SecurityContext;

/**
 * Persistence layer
 */
public class PB01CDBCRUDForOrganization
	 extends PB01CDBCRUDForOrganizationalEntityBase<PB01AOrganizationOID,PB01AOrganizationID,PB01AOrganization,
	 											   PB01CDBEntityForOrganization>
  implements PB01ACRUDServicesForOrganization {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01CDBCRUDForOrganization(final DBModuleConfig dbCfg,
									 final EntityManager entityManager,
									 final Marshaller marshaller) {
		super(PB01AOrganization.class,PB01CDBEntityForOrganization.class,
			  dbCfg,
			  entityManager,
			  marshaller);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void setDBEntityFieldsFromModelObject(final SecurityContext securityContext,
												 final PB01AOrganization org,final PB01CDBEntityForOrganization dbEntity) {
		super.setDBEntityFieldsFromModelObject(securityContext,
											   org,dbEntity);
		// hierarchy level
		dbEntity.setHierarchyLevel(1);	// used to return ordered results when searching (see PB01CDBSearcherForEntityModelObject)
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void completeDBEntityBeforeCreateOrUpdate(final SecurityContext securityContext,
													 final PersistencePerformedOperation performedOp,
													 final PB01AOrganization modelObj,final PB01CDBEntityForOrganization dbEntity) {
		// nothing
	}
}
