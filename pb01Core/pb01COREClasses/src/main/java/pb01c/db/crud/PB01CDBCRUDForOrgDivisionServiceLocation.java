package pb01c.db.crud;

import javax.persistence.EntityManager;

import pb01a.api.interfaces.PB01ACRUDServicesForOrgDivisionServiceLocation;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionServiceLocationID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionServiceLocationOID;
import pb01a.model.org.PB01AOrgDivisionServiceLocation;
import pb01c.db.entities.PB01CDBEntityForOrgDivision;
import pb01c.db.entities.PB01CDBEntityForOrgDivisionService;
import pb01c.db.entities.PB01CDBEntityForOrgDivisionServiceLocation;
import pb01c.db.entities.PB01CDBEntityForOrganization;
import r01f.model.persistence.PersistencePerformedOperation;
import r01f.objectstreamer.Marshaller;
import r01f.persistence.db.config.DBModuleConfig;
import r01f.persistence.db.entities.primarykeys.DBPrimaryKeyForModelObjectImpl;
import r01f.securitycontext.SecurityContext;

/**
 * Persistence layer
 */
public class PB01CDBCRUDForOrgDivisionServiceLocation
	 extends PB01CDBCRUDForOrganizationalEntityBase<PB01AOrgDivisionServiceLocationOID,PB01AOrgDivisionServiceLocationID,PB01AOrgDivisionServiceLocation,
	 				        PB01CDBEntityForOrgDivisionServiceLocation>
  implements PB01ACRUDServicesForOrgDivisionServiceLocation {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01CDBCRUDForOrgDivisionServiceLocation(final DBModuleConfig dbCfg,
												   final EntityManager entityManager,
												   final Marshaller marshaller) {
		super(PB01AOrgDivisionServiceLocation.class,PB01CDBEntityForOrgDivisionServiceLocation.class,
			  dbCfg,
			  entityManager,
			  marshaller);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void setDBEntityFieldsFromModelObject(final SecurityContext securityContext,
												 final PB01AOrgDivisionServiceLocation loc,final PB01CDBEntityForOrgDivisionServiceLocation dbEntity) {
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
		dbEntity.setHierarchyLevel(4);	// used to return ordered results when searching (see PB01CDBSearcherForEntityModelObject)
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void completeDBEntityBeforeCreateOrUpdate(final SecurityContext securityContext,
													 final PersistencePerformedOperation performedOp,
													 final PB01AOrgDivisionServiceLocation modelObj,final PB01CDBEntityForOrgDivisionServiceLocation dbLocation) {
		// load the organization, division & service entities
		PB01CDBEntityForOrganization dbOrg = this.getEntityManager().find(PB01CDBEntityForOrganization.class,
																		 new DBPrimaryKeyForModelObjectImpl(dbLocation.getOrganizationOid()));
		PB01CDBEntityForOrgDivision dbDivision = this.getEntityManager().find(PB01CDBEntityForOrgDivision.class,
																	 		 new DBPrimaryKeyForModelObjectImpl(dbLocation.getOrgDivisionOid()));
		PB01CDBEntityForOrgDivisionService dbService = this.getEntityManager().find(PB01CDBEntityForOrgDivisionService.class,
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
