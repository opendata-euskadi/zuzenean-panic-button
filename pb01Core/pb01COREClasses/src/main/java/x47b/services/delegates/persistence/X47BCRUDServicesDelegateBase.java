package x47b.services.delegates.persistence;

import com.google.common.eventbus.EventBus;

import r01f.bootstrap.services.config.core.ServicesCoreBootstrapConfigWhenBeanExposed;
import r01f.model.persistence.CRUDResult;
import r01f.model.persistence.CRUDResultBuilder;
import r01f.model.persistence.PersistenceRequestedOperation;
import r01f.persistence.db.DBCRUDForModelObject;
import r01f.securitycontext.SecurityContext;
import r01f.services.delegates.persistence.CRUDServicesForModelObjectDelegateBase;
import r01f.services.delegates.persistence.CompletesModelObjectBeforeCreateOrUpdate;
import r01f.services.delegates.persistence.ValidatesModelObjectBeforeCreateOrUpdate;
import r01f.validation.ObjectValidationResult;
import r01f.validation.ObjectValidationResultBuilder;
import x47b.api.interfaces.X47BCRUDServicesBase;
import x47b.model.X47BPersistableObject;
import x47b.model.oids.X47BIDs.X47BPersistableObjectID;
import x47b.model.oids.X47BOIDs.X47BPersistableObjectOID;

/**
 * Service layer delegated type for CRUD (Create/Read/Update/Delete) operations
 */
abstract class X47BCRUDServicesDelegateBase<O extends X47BPersistableObjectOID,ID extends X47BPersistableObjectID<O>,M extends X47BPersistableObject<O,ID>>
	   extends CRUDServicesForModelObjectDelegateBase<O,M>
    implements X47BCRUDServicesBase<O,ID,M>,
    		   ValidatesModelObjectBeforeCreateOrUpdate<M>,
  			   CompletesModelObjectBeforeCreateOrUpdate<M> {

/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BCRUDServicesDelegateBase(final ServicesCoreBootstrapConfigWhenBeanExposed coreCfg,
										final Class<M> modelObjType,
									    final DBCRUDForModelObject<O,M> crud,
									    final EventBus eventBus) {
		super(coreCfg,
			  modelObjType,
			  crud,
			  eventBus);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  EXTENSION METHODS
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public CRUDResult<M> loadById(final SecurityContext securityContext,
							      final ID id) {
		if (id == null) {
			return CRUDResultBuilder.using(securityContext)
								    .on(_modelObjectType)
								    .notLoaded()
						     		.becauseClientBadRequest("The entity id MUST not be null")
						     			.build();
		}
		CRUDResult<M> outResult = this.getServiceImplAs(X47BCRUDServicesBase.class)
											.loadById(securityContext,
													  id);
		return outResult;
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  PARAMS VALIDATION ON CREATION / UPDATE
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public M completeModelObjBeforeCreateOrUpdate(final SecurityContext securityContext, 
												  final PersistenceRequestedOperation requestedOp,
												  final M modelObj) {
		return modelObj;
	}
	@Override
	public ObjectValidationResult<M> validateModelObjBeforeCreateOrUpdate(final SecurityContext securityContext,
																	 		   final PersistenceRequestedOperation requestedOp,
																	 		   final M entity) {
		ObjectValidationResult<M> outValid = null;

		// Validate the data
		if (entity.getOid() == null) {
			outValid = ObjectValidationResultBuilder.on(entity)
														 .isNotValidBecause("The {} entity MUST have an oid",
															  			    entity.getClass().getSimpleName());
		}
		if (outValid == null
		 && entity.getId() == null) {
			outValid = ObjectValidationResultBuilder.on(entity)
														 .isNotValidBecause("The {} entity with oid={} has a NULL ID",
																 			entity.getClass().getSimpleName(),entity.getOid());
		}
		// Validate that it does not exists another entity with the same id
		if (outValid == null
		 && requestedOp.is(PersistenceRequestedOperation.CREATE)) {
			CRUDResult<M> existingEntityByIdLoadResult = this.loadById(securityContext,
																       entity.getId());
			if (existingEntityByIdLoadResult.hasSucceeded()) {
				outValid = ObjectValidationResultBuilder.on(entity)
															 .isNotValidBecause("The {} entity with oid={} is NOT valid because there cannot exists two entities with the same id ({})",
																	 			entity.getClass().getSimpleName(),entity.getOid(),entity.getId());
			}
		}
		// no errors... it's valid
		if (outValid == null) {
			outValid = ObjectValidationResultBuilder.on(entity)
								   						 .isValid();
		}
		return outValid;
	}


}
