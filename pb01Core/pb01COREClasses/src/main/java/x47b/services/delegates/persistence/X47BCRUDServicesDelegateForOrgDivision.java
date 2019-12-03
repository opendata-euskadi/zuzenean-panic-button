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
import x47b.api.interfaces.X47BCRUDServicesForOrgDivision;
import x47b.db.crud.X47BDBCRUDForOrgDivision;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrganizationOID;
import x47b.model.org.X47BOrgDivision;
import x47b.model.org.X47BOrganization;

/**
 * Service layer delegated type for CRUD (Create/Read/Update/Delete) operations
 */
public class X47BCRUDServicesDelegateForOrgDivision
	 extends X47BCRUDServicesDelegateForOrganizationalEntityBase<X47BOrgDivisionOID,X47BOrgDivisionID,X47BOrgDivision>
  implements X47BCRUDServicesForOrgDivision {
/////////////////////////////////////////////////////////////////////////////////////////
//  FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	private final X47BCRUDServicesDelegateForOrganization _orgCRUD;
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BCRUDServicesDelegateForOrgDivision(final ServicesCoreBootstrapConfigWhenBeanExposed coreCfg,
												  final EntityManager entityManager,
											      final Marshaller marshaller,
				  			   		   	   	      final EventBus eventBus) {
		super(coreCfg,
			  X47BOrgDivision.class,
			  new X47BDBCRUDForOrgDivision(DBModuleConfigBuilder.dbModuleConfigFrom(coreCfg),
					  					   entityManager,
					  					   marshaller),
			  eventBus);
		_orgCRUD = new X47BCRUDServicesDelegateForOrganization(coreCfg,
															   entityManager,
															   marshaller,
															   eventBus);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  VALIDATION
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public ObjectValidationResult<X47BOrgDivision> validateModelObjBeforeCreateOrUpdate(final SecurityContext securityContext,
																						final PersistenceRequestedOperation requestedOp,
																						final X47BOrgDivision div) {
		if (div.getOrgRef() == null) { 
			return ObjectValidationResultBuilder.on(div)
												.isNotValidBecause("The organization is mandatory to create a {}",X47BOrgDivision.class.getSimpleName());
		}
		if (requestedOp.isIn(PersistenceRequestedOperation.CREATE,
							 PersistenceRequestedOperation.UPDATE)) {
			X47BOrganizationOID orgOid = div.getOrgRef().getOid();
			
			// try to load the org by it's oid					
			CRUDResult<X47BOrganization> existingOrgByOidLoadResult = _orgCRUD.load(securityContext,
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
