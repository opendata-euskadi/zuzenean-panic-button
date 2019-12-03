package x47b.services.delegates.persistence;

import javax.persistence.EntityManager;

import com.google.common.eventbus.EventBus;

import r01f.bootstrap.services.config.core.ServicesCoreBootstrapConfigWhenBeanExposed;
import r01f.model.persistence.CRUDResult;
import r01f.model.persistence.PersistenceRequestedOperation;
import r01f.objectstreamer.Marshaller;
import r01f.persistence.db.config.DBModuleConfigBuilder;
import r01f.securitycontext.SecurityContext;
import r01f.validation.ObjectValidationResult;
import r01f.validation.ObjectValidationResultBuilder;
import x47b.api.interfaces.X47BCRUDServicesForOrgDivisionService;
import x47b.db.crud.X47BDBCRUDForOrgDivisionService;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionServiceID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrganizationOID;
import x47b.model.org.X47BOrgDivision;
import x47b.model.org.X47BOrgDivisionService;
import x47b.model.org.X47BOrganization;

/**
 * Service layer delegated type for CRUD (Create/Read/Update/Delete) operations
 */
public class X47BCRUDServicesDelegateForOrgDivisionService
	 extends X47BCRUDServicesDelegateForOrganizationalEntityBase<X47BOrgDivisionServiceOID,X47BOrgDivisionServiceID,X47BOrgDivisionService>
  implements X47BCRUDServicesForOrgDivisionService {
/////////////////////////////////////////////////////////////////////////////////////////
//  FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	private final X47BCRUDServicesDelegateForOrganization _orgCRUD;
	private final X47BCRUDServicesDelegateForOrgDivision _orgDivisionCRUD;
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BCRUDServicesDelegateForOrgDivisionService(final ServicesCoreBootstrapConfigWhenBeanExposed coreCfg,
														 final EntityManager entityManager,
													     final Marshaller marshaller,
						  			   		   	   	     final EventBus eventBus) {
		super(coreCfg,
			  X47BOrgDivisionService.class,
			  new X47BDBCRUDForOrgDivisionService(DBModuleConfigBuilder.dbModuleConfigFrom(coreCfg),
					  							  entityManager,
					  							  marshaller),
			  eventBus);
		_orgCRUD = new X47BCRUDServicesDelegateForOrganization(coreCfg,
															   entityManager,
															   marshaller,
															   eventBus);
		_orgDivisionCRUD = new X47BCRUDServicesDelegateForOrgDivision(coreCfg,
																	  entityManager,
																	  marshaller,
																	  eventBus);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  VALIDATION
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public ObjectValidationResult<X47BOrgDivisionService> validateModelObjBeforeCreateOrUpdate(final SecurityContext securityContext,
																							   final PersistenceRequestedOperation requestedOp,
																							   final X47BOrgDivisionService srvc) {
		// Ensure the hierarchy is correct
		if (srvc.getOrgRef() == null || srvc.getOrgDivisionRef() == null) { 
			return ObjectValidationResultBuilder.on(srvc)
												.isNotValidBecause("The organization and division are mandatory to create a {}",X47BOrgDivisionService.class.getSimpleName());
		}
		if (requestedOp.isIn(PersistenceRequestedOperation.CREATE,
							 PersistenceRequestedOperation.UPDATE)) {
			X47BOrganizationOID orgOid = srvc.getOrgRef().getOid();
			X47BOrgDivisionOID divOid = srvc.getOrgDivisionRef().getOid();
			
			// try to load the org & division by it's ids					
			CRUDResult<X47BOrganization> existingOrgByOidLoadResult = _orgCRUD.load(securityContext,
																			 	    orgOid);
			CRUDResult<X47BOrgDivision> existingDivByOidLoadResult = _orgDivisionCRUD.load(securityContext,
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
