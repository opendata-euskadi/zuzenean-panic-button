package pb01c.services.delegates.persistence;

import com.google.common.eventbus.EventBus;

import pb01a.api.interfaces.PB01ACRUDServicesForOrganizationalModelObjectBase;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgObjectID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgObjectOID;
import pb01a.model.org.PB01AOrganizationalPersistableObject;
import pb01a.model.org.PB01AOrganizationalPersistableObjectBase;
import r01f.bootstrap.services.config.core.ServicesCoreBootstrapConfigWhenBeanExposed;
import r01f.model.persistence.CRUDResult;
import r01f.model.persistence.PersistenceRequestedOperation;
import r01f.persistence.db.DBCRUDForModelObject;
import r01f.securitycontext.SecurityContext;
import r01f.validation.ObjectValidationResult;
import r01f.validation.ObjectValidationResultBuilder;

/**
 * Service layer delegated type for CRUD (Create/Read/Update/Delete) operations
 */
abstract class PB01CCRUDServicesDelegateForOrganizationalEntityBase<O extends PB01AOrgObjectOID,ID extends PB01AOrgObjectID<O>,M extends PB01AOrganizationalPersistableObject<O,ID>>
	   extends PB01CCRUDServicesDelegateBase<O,ID,M>
    implements PB01ACRUDServicesForOrganizationalModelObjectBase<O,ID,M> {

/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01CCRUDServicesDelegateForOrganizationalEntityBase(final ServicesCoreBootstrapConfigWhenBeanExposed coreCfg,
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
		ObjectValidationResult<?> modelObjectValidation = ((PB01AOrganizationalPersistableObjectBase<?,?,?>)entity).validate();
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
