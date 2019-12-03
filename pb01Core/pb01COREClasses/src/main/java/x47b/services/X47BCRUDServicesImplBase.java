package x47b.services;

import javax.inject.Provider;
import javax.persistence.EntityManager;

import com.google.common.eventbus.EventBus;
import com.google.inject.persist.Transactional;

import lombok.experimental.Accessors;
import r01f.bootstrap.services.config.core.ServicesCoreBootstrapConfigWhenBeanExposed;
import r01f.model.persistence.CRUDResult;
import r01f.objectstreamer.Marshaller;
import r01f.securitycontext.SecurityContext;
import r01f.services.persistence.CoreCRUDServicesForModelObjectBase;
import x47b.api.interfaces.X47BCRUDServicesBase;
import x47b.model.X47BPersistableObject;
import x47b.model.oids.X47BIDs.X47BPersistableObjectID;
import x47b.model.oids.X47BOIDs.X47BPersistableObjectOID;

@Accessors(prefix="_")
public abstract class X47BCRUDServicesImplBase<O extends X47BPersistableObjectOID,ID extends X47BPersistableObjectID<O>,M extends X47BPersistableObject<O,ID>>
       		  extends CoreCRUDServicesForModelObjectBase<O,M>
    	   implements X47BCRUDServicesBase<O,ID,M>,
    	   			  X47BPanicButtonCoreServiceImpl {
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Constructor
	 * @param coreCfg
	 * @param modelObjectsMarshaller annotated with @ModelObjectsMarshaller
	 * @param eventBus
	 * @param entityManagerProvider
	 */
	public X47BCRUDServicesImplBase(final ServicesCoreBootstrapConfigWhenBeanExposed coreCfg,
									final Marshaller modelObjectsMarshaller,
									final EventBus eventBus,
									final Provider<EntityManager> entityManagerProvider) {
		super(coreCfg,
			  modelObjectsMarshaller,
			  eventBus,
			  entityManagerProvider);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	SERVICES EXTENSION
// 	IMPORTANT!!! Do NOT put any logic in these methods ONLY DELEGATE!!!
/////////////////////////////////////////////////////////////////////////////////////////
	@Transactional
	@Override @SuppressWarnings("unchecked")
	public CRUDResult<M> loadById(final SecurityContext securityContext,
								  final ID id) {
		CRUDResult<M> outResult = this.forSecurityContext(securityContext)
									  	.createDelegateAs(X47BCRUDServicesBase.class)
											.loadById(securityContext,
													  id);
		return outResult;
	}
}
