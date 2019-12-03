package x47b.services.delegates.persistence;

import com.google.common.eventbus.EventBus;

import r01f.bootstrap.services.config.core.ServicesCoreBootstrapConfigWhenBeanExposed;
import r01f.model.persistence.CRUDResult;
import r01f.model.persistence.PersistenceRequestedOperation;
import r01f.persistence.db.DBCRUDForModelObject;
import r01f.securitycontext.SecurityContext;
import r01f.validation.ObjectValidationResult;
import r01f.validation.ObjectValidationResultBuilder;
import x47b.api.interfaces.X47BCRUDServicesForOrganizationalModelObjectBase;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgObjectID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgObjectOID;
import x47b.model.org.X47BOrganizationalPersistableObject;
import x47b.model.org.X47BOrganizationalPersistableObjectBase;

/**
 * Service layer delegated type for CRUD (Create/Read/Update/Delete) operations
 */
abstract class X47BCRUDServicesDelegateForOrganizationalEntityBase<O extends X47BOrgObjectOID,ID extends X47BOrgObjectID<O>,M extends X47BOrganizationalPersistableObject<O,ID>>
	   extends X47BCRUDServicesDelegateBase<O,ID,M>
    implements X47BCRUDServicesForOrganizationalModelObjectBase<O,ID,M> {

/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BCRUDServicesDelegateForOrganizationalEntityBase(final ServicesCoreBootstrapConfigWhenBeanExposed coreCfg,
															   final Class<M> modelObjType,
									    					   final DBCRUDForModelObject<O,M> crud,
									    					   final EventBus eventBus) {
		super(coreCfg,
			  modelObjType,
			  crud,
			  eventBus);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  PARAMS VALIDATION ON CREATION / UPDATE
/////////////////////////////////////////////////////////////////////////////////////////
	@Override @SuppressWarnings("unchecked")
	public ObjectValidationResult<M> validateModelObjBeforeCreateOrUpdate(final SecurityContext securityContext,
																	 	  final PersistenceRequestedOperation requestedOp,
																	 	  final M entity) {
		// Validate the model object
		ObjectValidationResult<?> modelObjectValidation = ((X47BOrganizationalPersistableObjectBase<?,?,?>)entity).validate();
		if (modelObjectValidation.isNOTValid()) {
			return (ObjectValidationResult<M>)modelObjectValidation;
		}
		// Validate that it does not exists another entity with the same id
		if (requestedOp.is(PersistenceRequestedOperation.CREATE)) {
			CRUDResult<M> existingEntityByIdLoadResult = this.loadById(securityContext,
																       entity.getId());
			if (existingEntityByIdLoadResult.hasSucceeded()) {
				return ObjectValidationResultBuilder.on(entity)
												    .isNotValidBecause("The {} org entity with oid={} is NOT valid because there cannot exists two org entities of type {} with the same id ({})",
														 			   _modelObjectType.getSimpleName(),entity.getOid(),_modelObjectType.getSimpleName(),entity.getId());
			}
		}

		return super.validateModelObjBeforeCreateOrUpdate(securityContext,
													      requestedOp,
														  entity);
	}


}
