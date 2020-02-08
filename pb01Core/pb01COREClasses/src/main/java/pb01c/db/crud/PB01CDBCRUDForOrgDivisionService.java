package pb01c.db.crud;

import javax.persistence.EntityManager;

import pb01a.api.interfaces.PB01ACRUDServicesForOrgDivisionService;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionServiceID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionServiceOID;
import pb01a.model.org.PB01AOrgDivisionService;
import pb01c.db.entities.PB01CDBEntityForOrgDivision;
import pb01c.db.entities.PB01CDBEntityForOrgDivisionService;
import pb01c.db.entities.PB01CDBEntityForOrganization;
import r01f.model.persistence.PersistencePerformedOperation;
import r01f.objectstreamer.Marshaller;
import r01f.persistence.db.config.DBModuleConfig;
import r01f.persistence.db.entities.primarykeys.DBPrimaryKeyForModelObjectImpl;
import r01f.securitycontext.SecurityContext;

/**
 * Persistence layer
 */
public class PB01CDBCRUDForOrgDivisionService
	 extends PB01CDBCRUDForOrganizationalEntityBase<PB01AOrgDivisionServiceOID,PB01AOrgDivisionServiceID,PB01AOrgDivisionService,
	 				        PB01CDBEntityForOrgDivisionService>
  implements PB01ACRUDServicesForOrgDivisionService {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01CDBCRUDForOrgDivisionService(final DBModuleConfig dbCfg,
										   final EntityManager entityManager,
										   final Marshaller marshaller) {
		super(PB01AOrgDivisionService.class,PB01CDBEntityForOrgDivisionService.class,
			  dbCfg,
			  entityManager,
			  marshaller);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void setDBEntityFieldsFromModelObject(final SecurityContext securityContext,
												 final PB01AOrgDivisionService service,final PB01CDBEntityForOrgDivisionService dbEntity) {
		super.setDBEntityFieldsFromModelObject(securityContext,
											   service,dbEntity);

		// org reference
		dbEntity.setOrganizationOid(service.getOrgRef().getOid().asString());
		dbEntity.setOrganizationId(service.getOrgRef().getId().asString());

		// division reference
		dbEntity.setOrgDivisionOid(service.getOrgDivisionRef().getOid().asString());
		dbEntity.setOrgDivisionId(service.getOrgDivisionRef().getId().asString());

		// hierarchy level
		dbEntity.setHierarchyLevel(3);	// used to return ordered results when searching (see PB01CDBSearcherForEntityModelObject)
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void completeDBEntityBeforeCreateOrUpdate(final SecurityContext securityContext,
													 final PersistencePerformedOperation performedOp,
													 final PB01AOrgDivisionService modelObj,final PB01CDBEntityForOrgDivisionService dbService) {
		// load the organization & division entities
		PB01CDBEntityForOrganization dbOrg = this.getEntityManager().find(PB01CDBEntityForOrganization.class,
																		 new DBPrimaryKeyForModelObjectImpl(dbService.getOrganizationOid()));
		PB01CDBEntityForOrgDivision dbDivision = this.getEntityManager().find(PB01CDBEntityForOrgDivision.class,
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
