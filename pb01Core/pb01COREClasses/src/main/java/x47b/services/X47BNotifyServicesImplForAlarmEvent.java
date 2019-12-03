package x47b.services;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import javax.persistence.EntityManager;

import com.google.common.eventbus.EventBus;
import com.google.inject.persist.Transactional;

import lombok.Getter;
import lombok.experimental.Accessors;
import r01f.bootstrap.services.config.core.ServicesCoreBootstrapConfigWhenBeanExposed;
import r01f.model.annotations.ModelObjectsMarshaller;
import r01f.model.persistence.CRUDResult;
import r01f.objectstreamer.Marshaller;
import r01f.securitycontext.SecurityContext;
import r01f.services.persistence.CorePersistenceServicesBase;
import r01f.services.persistence.ServiceDelegateProvider;
import x47b.api.interfaces.X47BNotifyServicesForAlarmEvent;
import x47b.model.X47BAlarmEvent;
import x47b.model.oids.X47BOrganizationalIDs.X47BWorkPlaceID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BWorkPlaceOID;
import x47b.model.oids.X47BPanicButtonOIDs.X47BAlarmEventOID;
import x47b.services.delegates.persistence.X47BCRUDServicesDelegateForAlarmEvent;
import x47b.services.delegates.persistence.X47BNotifyServicesDelegateForAlarmEvent;

/**
 * Implements the {@link X47BAlarmEvent} persistence-related services which in turn are
 * delegated to {@link X47BCRUDServicesDelegateForAlarmEvent}
 */
@Singleton
@Accessors(prefix="_")
public class X47BNotifyServicesImplForAlarmEvent
     extends CorePersistenceServicesBase
  implements X47BNotifyServicesForAlarmEvent,
  			 X47BPanicButtonCoreServiceImpl {
/////////////////////////////////////////////////////////////////////////////////////////
//	DELEGATE PROVIDER
/////////////////////////////////////////////////////////////////////////////////////////
	@Getter private final ServiceDelegateProvider<X47BNotifyServicesDelegateForAlarmEvent> _delegateProvider;
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public X47BNotifyServicesImplForAlarmEvent(							final ServicesCoreBootstrapConfigWhenBeanExposed coreCfg,
											   @ModelObjectsMarshaller 	final Marshaller modelObjectsMarshaller,
																		final EventBus eventBus,
									   		  							final Provider<EntityManager> entityManagerProvider) {
		super(coreCfg,
			  modelObjectsMarshaller,
			  eventBus,
			  entityManagerProvider);
		_delegateProvider = new ServiceDelegateProvider<X47BNotifyServicesDelegateForAlarmEvent>() {
									@Override
									public X47BNotifyServicesDelegateForAlarmEvent createDelegate(final SecurityContext securityContext) {
										return new X47BNotifyServicesDelegateForAlarmEvent(_coreConfig,
																						   X47BNotifyServicesImplForAlarmEvent.this.getFreshNewEntityManager(),
																						   _modelObjectsMarshaller,
																						   _eventBus);
									}
							};
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	SERVICES EXTENSION
// 	IMPORTANT!!! Do NOT put any logic in these methods ONLY DELEGATE!!!
/////////////////////////////////////////////////////////////////////////////////////////
	@Transactional
	@Override
	public CRUDResult<X47BAlarmEvent> raiseAlarm(final SecurityContext securityContext,
												 final X47BWorkPlaceID id) {
		return this.forSecurityContext(securityContext)
						.createDelegateAs(X47BNotifyServicesForAlarmEvent.class)
							.raiseAlarm(securityContext,
										id);
	}
	@Transactional
	@Override
	public CRUDResult<X47BAlarmEvent> raiseAlarm(final SecurityContext securityContext,
												 final X47BWorkPlaceOID id) {
		return this.forSecurityContext(securityContext)
						.createDelegateAs(X47BNotifyServicesForAlarmEvent.class)
							.raiseAlarm(securityContext,
										id);
	}
	@Transactional
	@Override
	public CRUDResult<X47BAlarmEvent> cancelAlarm(final SecurityContext securityContext,
												  final X47BAlarmEventOID oid) {
		return this.forSecurityContext(securityContext)
						.createDelegateAs(X47BNotifyServicesForAlarmEvent.class)
							.cancelAlarm(securityContext,
										 oid);
	}
}
