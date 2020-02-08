package pb01c.services.delegates.persistence;

import javax.persistence.EntityManager;

import com.google.common.eventbus.EventBus;

import pb01a.api.interfaces.PB01ACRUDServicesForOrgDivisionService;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionServiceID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionServiceOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrganizationOID;
import pb01a.model.org.PB01AOrgDivision;
import pb01a.model.org.PB01AOrgDivisionService;
import pb01a.model.org.PB01AOrganization;
import pb01c.db.crud.PB01CDBCRUDForOrgDivisionService;
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
public class PB01CCRUDServicesDelegateForOrgDivisionService
	 extends PB01CCRUDServicesDelegateForOrganizationalEntityBase<PB01AOrgDivisionServiceOID,PB01AOrgDivisionServiceID,PB01AOrgDivisionService>
  implements PB01ACRUDServicesForOrgDivisionService {
/////////////////////////////////////////////////////////////////////////////////////////
//  FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	private final PB01CCRUDServicesDelegateForOrganization _orgCRUD;
	private final PB01CCRUDServicesDelegateForOrgDivision _orgDivisionCRUD;
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01CCRUDServicesDelegateForOrgDivisionService(final ServicesCoreBootstrapConfigWhenBeanExposed coreCfg,
														 final EntityManager entityManager,
													     final Marshaller marshaller,
						  			   		   	   	     final EventBus eventBus) {
		super(coreCfg,
			  PB01AOrgDivisionService.class,
			  new PB01CDBCRUDForOrgDivisionService(DBModuleConfigBuilder.dbModuleConfigFrom(coreCfg),
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
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  VALIDATION
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public ObjectValidationResult<PB01AOrgDivisionService> validateModelObjBeforeCreateOrUpdate(final SecurityContext securityContext,
																							   final PersistenceRequestedOperation requestedOp,
																							   final PB01AOrgDivisionService srvc) {
		// Ensure the hierarchy is correct
		if (srvc.getOrgRef() == null || srvc.getOrgDivisionRef() == null) { 
			return ObjectValidationResultBuilder.on(srvc)
												.isNotValidBecause("The organization and division are mandatory to create a {}",PB01AOrgDivisionService.class.getSimpleName());
		}
		if (requestedOp.isIn(PersistenceRequestedOperation.CREATE,
							 PersistenceRequestedOperation.UPDATE)) {
			PB01AOrganizationOID orgOid = srvc.getOrgRef().getOid();
			PB01AOrgDivisionOID divOid = srvc.getOrgDivisionRef().getOid();
			
			// try to load the org & division by it's ids					
			CRUDResult<PB01AOrganization> existingOrgByOidLoadResult = _orgCRUD.load(securityContext,
																			 	    orgOid);
			CRUDResult<PB01AOrgDivision> existingDivByOidLoadResult = _orgDivisionCRUD.load(securityContext,
																				  		   divOid);
			if (existingOrgByOidLoadResult.hasFailed() || existingDivByOidLoadResult.hasFailed()) {
				return ObjectValidationResultBuilder.on(srvc)
													 .isNotValidBecause("The {} with oid={} sets an INVALID orgOid={} or divisionOid={}",
															 			srvc.getClass().getSimpleName(),srvc.getOid(),
															 			orgOid,divOid);					
			} else if (existingOrgByOidLoadResult.getOrThrow().getId().isNOT(srvc.getOrgRef().getId())
					|| existingDivByOidLoadResult.getOrThrow().getId().isNOT(srvc.getOrgDivisionRef().getId())) {
				return ObjectValidationResultBuilder.on(srvc)
													 .isNotValidBecause("The {} with oid={} sets an INVALID orgId={} or divisionId={}",
															 			srvc.getClass().getSimpleName(),srvc.getOid(),
															 			srvc.getOrgRef().getId(),srvc.getOrgDivisionRef().getId());	
			}
		}
		return super.validateModelObjBeforeCreateOrUpdate(securityContext, 
														  requestedOp,
														  srvc);
	}
}
