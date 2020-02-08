package pb01c.db.crud;

import javax.persistence.EntityManager;

import pb01a.api.interfaces.PB01ACRUDServicesForOrgDivision;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionOID;
import pb01a.model.org.PB01AOrgDivision;
import pb01c.db.entities.PB01CDBEntityForOrgDivision;
import pb01c.db.entities.PB01CDBEntityForOrganization;
import r01f.model.persistence.PersistencePerformedOperation;
import r01f.objectstreamer.Marshaller;
import r01f.persistence.db.config.DBModuleConfig;
import r01f.persistence.db.entities.primarykeys.DBPrimaryKeyForModelObjectImpl;
import r01f.securitycontext.SecurityContext;

/**
 * Persistence layer
 */
public class PB01CDBCRUDForOrgDivision
	 extends PB01CDBCRUDForOrganizationalEntityBase<PB01AOrgDivisionOID,PB01AOrgDivisionID,PB01AOrgDivision,
	 											   PB01CDBEntityForOrgDivision>
  implements PB01ACRUDServicesForOrgDivision {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01CDBCRUDForOrgDivision(final DBModuleConfig dbCfg,
									final EntityManager entityManager,
									final Marshaller marshaller) {
		super(PB01AOrgDivision.class,PB01CDBEntityForOrgDivision.class,
			  dbCfg,
			  entityManager,
			  marshaller);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void setDBEntityFieldsFromModelObject(final SecurityContext securityContext,
												 final PB01AOrgDivision div,final PB01CDBEntityForOrgDivision dbEntity) {
		super.setDBEntityFieldsFromModelObject(securityContext,
											   div,dbEntity);

		// Organization reference
		dbEntity.setOrganizationOid(div.getOrgRef().getOid().asString());
		dbEntity.setOrganizationId(div.getOrgRef().getId().asString());

		// hierarchy level
		dbEntity.setHierarchyLevel(2);	// used to return ordered results when searching (see PB01CDBSearcherForEntityModelObject)
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void completeDBEntityBeforeCreateOrUpdate(final SecurityContext securityContext,
													 final PersistencePerformedOperation performedOp,
													 final PB01AOrgDivision modelObj,final PB01CDBEntityForOrgDivision dbDivision) {
		// load the organization entity
		PB01CDBEntityForOrganization dbOrg = this.getEntityManager().find(PB01CDBEntityForOrganization.class,
																		 new DBPrimaryKeyForModelObjectImpl(dbDivision.getOrganizationOid()));
		// set the dependency
		dbDivision.setOrganization(dbOrg);

		// setting the division's dependent objects (org), also modifies the later since it's a BI-DIRECTIONAL relation
		// ... so the entity manager MUST be refreshed in order to avoid an optimistic locking exception
		this.getEntityManager().refresh(dbOrg);
	}
}
