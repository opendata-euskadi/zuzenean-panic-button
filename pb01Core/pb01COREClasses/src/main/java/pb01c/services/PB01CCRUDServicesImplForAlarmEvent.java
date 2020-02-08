package pb01c.services;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import javax.persistence.EntityManager;

import com.google.common.eventbus.EventBus;

import lombok.Getter;
import lombok.experimental.Accessors;
import pb01a.api.interfaces.PB01ACRUDServicesForAlarmEvent;
import pb01a.model.PB01AAlarmEvent;
import pb01a.model.oids.PB01APanicButtonOIDs.PB01AAlarmEventOID;
import pb01c.services.delegates.persistence.PB01CCRUDServicesDelegateForAlarmEvent;
import r01f.bootstrap.services.config.core.ServicesCoreBootstrapConfigWhenBeanExposed;
import r01f.model.annotations.ModelObjectsMarshaller;
import r01f.objectstreamer.Marshaller;
import r01f.securitycontext.SecurityContext;
import r01f.services.persistence.CoreCRUDServicesForModelObjectBase;
import r01f.services.persistence.ServiceDelegateProvider;


/**
 * Implements the {@link PB01AAlarmEvent} persistence-related services which in turn are
 * delegated to {@link PB01CCRUDServicesDelegateForAlarmEvent}
 */
@Singleton
@Accessors(prefix="_")
public class PB01CCRUDServicesImplForAlarmEvent
     extends CoreCRUDServicesForModelObjectBase<PB01AAlarmEventOID,PB01AAlarmEvent>
  implements PB01ACRUDServicesForAlarmEvent,
  			 PB01CPanicButtonCoreServiceImpl {
/////////////////////////////////////////////////////////////////////////////////////////
//	DELEGATE PROVIDER
/////////////////////////////////////////////////////////////////////////////////////////
	@Getter private final ServiceDelegateProvider<PB01CCRUDServicesDelegateForAlarmEvent> _delegateProvider;
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public PB01CCRUDServicesImplForAlarmEvent(							final ServicesCoreBootstrapConfigWhenBeanExposed coreCfg,
											 @ModelObjectsMarshaller 	final Marshaller modelObjectsMarshaller,
									   		  							final EventBus eventBus,
									   		  							final Provider<EntityManager> entityManagerProvider) {
		super(coreCfg,
			  modelObjectsMarshaller,
			  eventBus,
			  entityManagerProvider);
		_delegateProvider = new ServiceDelegateProvider<PB01CCRUDServicesDelegateForAlarmEvent>() {
									@Override
									public PB01CCRUDServicesDelegateForAlarmEvent createDelegate(final SecurityContext securityContext) {
										return new PB01CCRUDServicesDelegateForAlarmEvent(_coreConfig,
																						 PB01CCRUDServicesImplForAlarmEvent.this.getFreshNewEntityManager(),
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
