package pb01c.services.delegates.persistence;

import javax.persistence.EntityManager;

import com.google.common.eventbus.EventBus;

import pb01a.api.interfaces.PB01ACRUDServicesForOrgDivisionServiceLocation;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionServiceLocationID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionServiceLocationOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionServiceOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrganizationOID;
import pb01a.model.org.PB01AOrgDivision;
import pb01a.model.org.PB01AOrgDivisionService;
import pb01a.model.org.PB01AOrgDivisionServiceLocation;
import pb01a.model.org.PB01AOrganization;
import pb01c.db.crud.PB01CDBCRUDForOrgDivisionServiceLocation;
import r01f.bootstrap.services.config.core.ServicesCoreBootstrapConfigWhenBeanExposed;
import r01f.model.persistence.CRUDResult;
import r01f.model.persistence.PersistenceRequestedOperation;
import r01f.objectstreamer.Marshaller;
import r01f.persistence.db.config.DBModuleConfigBuilder;
import r01f.securitycontext.SecurityContext;
import r01f.validation.ObjectValidationResult;
import r01f.validation.ObjectValidationResultBuilder;

/**
 * Service layer delegated type for CRUD (Create/Read/Update/Delete) operations
 */
public class PB01CCRUDServicesDelegateForOrgDivisionServiceLocation
	 extends PB01CCRUDServicesDelegateForOrganizationalEntityBase<PB01AOrgDivisionServiceLocationOID,PB01AOrgDivisionServiceLocationID,PB01AOrgDivisionServiceLocation>
  implements PB01ACRUDServicesForOrgDivisionServiceLocation {
/////////////////////////////////////////////////////////////////////////////////////////
//  FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	private final PB01CCRUDServicesDelegateForOrganization _orgCRUD;
	private final PB01CCRUDServicesDelegateForOrgDivision _orgDivisionCRUD;
	private final PB01CCRUDServicesDelegateForOrgDivisionService _orgDivisionServiceCRUD;
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01CCRUDServicesDelegateForOrgDivisionServiceLocation(final ServicesCoreBootstrapConfigWhenBeanExposed coreCfg,
																 final EntityManager entityManager,
															     final Marshaller marshaller,
								  			   		   	   	     final EventBus eventBus) {
		super(coreCfg,
			  PB01AOrgDivisionServiceLocation.class,
			  new PB01CDBCRUDForOrgDivisionServiceLocation(DBModuleConfigBuilder.dbModuleConfigFrom(coreCfg),
					  									  entityManager,
					  									  marshaller),
			  eventBus);
		 _orgCRUD = new PB01CCRUDServicesDelegateForOrganization(coreCfg,
				 												entityManager,
				 												marshaller,
				 												eventBus);
		 _orgDivisionCRUD = new PB01CCRUDServicesDelegateForOrgDivision(coreCfg,
				 													   entityManager,
				 													   marshaller,
				 													   eventBus);
		 _orgDivisionServiceCRUD = new PB01CCRUDServicesDelegateForOrgDivisionService(coreCfg,
				 																	 entityManager,	
				 																	 marshaller,
				 																	 eventBus);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  VALIDATION
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public ObjectValidationResult<PB01AOrgDivisionServiceLocation> validateModelObjBeforeCreateOrUpdate(final SecurityContext securityContext,
																							   		   final PersistenceRequestedOperation requestedOp,
																							   		   final PB01AOrgDivisionServiceLocation loc) {
		// Ensure the hierarchy is correct
		if (loc.getOrgRef() == null || loc.getOrgDivisionRef() == null || loc.getOrgDivisionServiceRef() == null) { 
			return ObjectValidationResultBuilder.on(loc)
												.isNotValidBecause("The organization, division and service are mandatory to create a {}",PB01AOrgDivisionServiceLocation.class.getSimpleName());
		}
		if (requestedOp.isIn(PersistenceRequestedOperation.CREATE,
							 PersistenceRequestedOperation.UPDATE)) {
			PB01AOrganizationOID orgOid = loc.getOrgRef().getOid();
			PB01AOrgDivisionOID divOid = loc.getOrgDivisionRef().getOid();
			PB01AOrgDivisionServiceOID srvcOid = loc.getOrgDivisionServiceRef().getOid();
			
			// try to load the org, division & service by it's ids					
			CRUDResult<PB01AOrganization> existingOrgByOidLoadResult = _orgCRUD.load(securityContext,
																			 	    orgOid);
			CRUDResult<PB01AOrgDivision> existingDivByOidLoadResult = _orgDivisionCRUD.load(securityContext,
																				  		   divOid);
			CRUDResult<PB01AOrgDivisionService> existingSrvcByOidLoadResult = _orgDivisionServiceCRUD.load(securityContext,
																				  		  				  srvcOid);
			
			if (existingOrgByOidLoadResult.hasFailed() || existingDivByOidLoadResult.hasFailed() || existingSrvcByOidLoadResult.hasFailed()) {
				return ObjectValidationResultBuilder.on(loc)
													 .isNotValidBecause("The {} with oid={} sets an INVALID orgOid={}, divisionOid={} or serviceOid={}",
															 			loc.getClass().getSimpleName(),loc.getOid(),
															 			orgOid,divOid,srvcOid);					
			} else if (existingOrgByOidLoadResult.getOrThrow().getId().isNOT(loc.getOrgRef().getId())
				    || existingDivByOidLoadResult.getOrThrow().getId().isNOT(loc.getOrgDivisionRef().getId())
				    || existingSrvcByOidLoadResult.getOrThrow().getId().isNOT(loc.getOrgDivisionServiceRef().getId())) {
				return ObjectValidationResultBuilder.on(loc)
													 .isNotValidBecause("The {} with oid={} sets an INVALID orgId={}, divisionId={} or serviceId={}",
															 			loc.getClass().getSimpleName(),loc.getOid(),
															 			loc.getOrgRef().getId(),loc.getOrgDivisionRef().getId(),loc.getOrgDivisionServiceRef().getId());	
			}
		}
		return super.validateModelObjBeforeCreateOrUpdate(securityContext,
														  requestedOp,
														  loc);
	}

}
