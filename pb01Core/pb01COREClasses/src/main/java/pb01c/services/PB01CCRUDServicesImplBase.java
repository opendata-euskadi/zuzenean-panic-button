package pb01c.services;

import javax.inject.Provider;
import javax.persistence.EntityManager;

import com.google.common.eventbus.EventBus;
import com.google.inject.persist.Transactional;

import lombok.experimental.Accessors;
import pb01a.api.interfaces.PB01ACRUDServicesBase;
import pb01a.model.PB01APersistableObject;
import pb01a.model.oids.PB01AIDs.PB01APersistableObjectID;
import pb01a.model.oids.PB01AOIDs.PB01APersistableObjectOID;
import r01f.bootstrap.services.config.core.ServicesCoreBootstrapConfigWhenBeanExposed;
import r01f.model.persistence.CRUDResult;
import r01f.objectstreamer.Marshaller;
import r01f.securitycontext.SecurityContext;
import r01f.services.persistence.CoreCRUDServicesForModelObjectBase;

@Accessors(prefix="_")
public abstract class PB01CCRUDServicesImplBase<O extends PB01APersistableObjectOID,ID extends PB01APersistableObjectID<O>,M extends PB01APersistableObject<O,ID>>
       		  extends CoreCRUDServicesForModelObjectBase<O,M>
    	   implements PB01ACRUDServicesBase<O,ID,M>,
    	   			  PB01CPanicButtonCoreServiceImpl {
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
	public PB01CCRUDServicesImplBase(final ServicesCoreBootstrapConfigWhenBeanExposed coreCfg,
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
									  	.createDelegateAs(PB01ACRUDServicesBase.class)
											.loadById(securityContext,
													  id);
		return outResult;
	}
}
