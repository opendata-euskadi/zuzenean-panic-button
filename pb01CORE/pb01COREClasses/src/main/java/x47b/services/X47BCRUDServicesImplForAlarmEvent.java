package x47b.services;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import javax.persistence.EntityManager;

import com.google.common.eventbus.EventBus;

import lombok.Getter;
import lombok.experimental.Accessors;
import r01f.bootstrap.services.config.core.ServicesCoreBootstrapConfigWhenBeanExposed;
import r01f.model.annotations.ModelObjectsMarshaller;
import r01f.objectstreamer.Marshaller;
import r01f.securitycontext.SecurityContext;
import r01f.services.persistence.CoreCRUDServicesForModelObjectBase;
import r01f.services.persistence.ServiceDelegateProvider;
import x47b.api.interfaces.X47BCRUDServicesForAlarmEvent;
import x47b.model.X47BAlarmEvent;
import x47b.model.oids.X47BPanicButtonOIDs.X47BAlarmEventOID;
import x47b.services.delegates.persistence.X47BCRUDServicesDelegateForAlarmEvent;


/**
 * Implements the {@link X47BAlarmEvent} persistence-related services which in turn are
 * delegated to {@link X47BCRUDServicesDelegateForAlarmEvent}
 */
@Singleton
@Accessors(prefix="_")
public class X47BCRUDServicesImplForAlarmEvent
     extends CoreCRUDServicesForModelObjectBase<X47BAlarmEventOID,X47BAlarmEvent>
  implements X47BCRUDServicesForAlarmEvent,
  			 X47BPanicButtonCoreServiceImpl {
/////////////////////////////////////////////////////////////////////////////////////////
//	DELEGATE PROVIDER
/////////////////////////////////////////////////////////////////////////////////////////
	@Getter private final ServiceDelegateProvider<X47BCRUDServicesDelegateForAlarmEvent> _delegateProvider;
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public X47BCRUDServicesImplForAlarmEvent(							final ServicesCoreBootstrapConfigWhenBeanExposed coreCfg,
											 @ModelObjectsMarshaller 	final Marshaller modelObjectsMarshaller,
									   		  							final EventBus eventBus,
									   		  							final Provider<EntityManager> entityManagerProvider) {
		super(coreCfg,
			  modelObjectsMarshaller,
			  eventBus,
			  entityManagerProvider);
		_delegateProvider = new ServiceDelegateProvider<X47BCRUDServicesDelegateForAlarmEvent>() {
									@Override
									public X47BCRUDServicesDelegateForAlarmEvent createDelegate(final SecurityContext securityContext) {
										return new X47BCRUDServicesDelegateForAlarmEvent(_coreConfig,
																						 X47BCRUDServicesImplForAlarmEvent.this.getFreshNewEntityManager(),
																						 _modelObjectsMarshaller,
																						 _eventBus);
									}
							};
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	SERVICES EXTENSION
// 	IMPORTANT!!! Do NOT put any logic in these methods ONLY DELEGATE!!!
/////////////////////////////////////////////////////////////////////////////////////////
}
