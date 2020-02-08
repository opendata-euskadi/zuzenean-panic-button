package pb01c.services.delegates.persistence;

import javax.persistence.EntityManager;

import com.google.common.eventbus.EventBus;

import pb01a.api.interfaces.PB01ACRUDServicesForOrgDivision;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrganizationOID;
import pb01a.model.org.PB01AOrgDivision;
import pb01a.model.org.PB01AOrganization;
import pb01c.db.crud.PB01CDBCRUDForOrgDivision;
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
public class PB01CCRUDServicesDelegateForOrgDivision
	 extends PB01CCRUDServicesDelegateForOrganizationalEntityBase<PB01AOrgDivisionOID,PB01AOrgDivisionID,PB01AOrgDivision>
  implements PB01ACRUDServicesForOrgDivision {
/////////////////////////////////////////////////////////////////////////////////////////
//  FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	private final PB01CCRUDServicesDelegateForOrganization _orgCRUD;
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01CCRUDServicesDelegateForOrgDivision(final ServicesCoreBootstrapConfigWhenBeanExposed coreCfg,
												  final EntityManager entityManager,
											      final Marshaller marshaller,
				  			   		   	   	      final EventBus eventBus) {
		super(coreCfg,
			  PB01AOrgDivision.class,
			  new PB01CDBCRUDForOrgDivision(DBModuleConfigBuilder.dbModuleConfigFrom(coreCfg),
					  					   entityManager,
					  					   marshaller),
			  eventBus);
		_orgCRUD = new PB01CCRUDServicesDelegateForOrganization(coreCfg,
															   entityManager,
															   marshaller,
															   eventBus);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  VALIDATION
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public ObjectValidationResult<PB01AOrgDivision> validateModelObjBeforeCreateOrUpdate(final SecurityContext securityContext,
																						final PersistenceRequestedOperation requestedOp,
																						final PB01AOrgDivision div) {
		if (div.getOrgRef() == null) { 
			return ObjectValidationResultBuilder.on(div)
												.isNotValidBecause("The organization is mandatory to create a {}",PB01AOrgDivision.class.getSimpleName());
		}
		if (requestedOp.isIn(PersistenceRequestedOperation.CREATE,
							 PersistenceRequestedOperation.UPDATE)) {
			PB01AOrganizationOID orgOid = div.getOrgRef().getOid();
			
			// try to load the org by it's oid					
			CRUDResult<PB01AOrganization> existingOrgByOidLoadResult = _orgCRUD.load(securityContext,
																			 	    orgOid);
			if (existingOrgByOidLoadResult.hasFailed()) {
				return ObjectValidationResultBuilder.on(div)
													 .isNotValidBecause("The {} with oid={} sets an INVALID (not existing) org oid={}",
															 			div.getClass().getSimpleName(),div.getOid(),
															 			orgOid);
			} else if (existingOrgByOidLoadResult.getOrThrow().getId().isNOT(div.getOrgRef().getId())) {
				return ObjectValidationResultBuilder.on(div)
													.isNotValidBecause("The {} with oid={} sets an INVALID (not existing) org id={}",
																	   div.getClass().getSimpleName(),div.getOid(),
																	   div.getOrgRef().getId());
			}
		}
		return super.validateModelObjBeforeCreateOrUpdate(securityContext, 
														  requestedOp,
														  div);
	}
	
}
