package pb01c.services;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import javax.persistence.EntityManager;

import com.google.common.eventbus.EventBus;
import com.google.inject.persist.Transactional;

import lombok.Getter;
import lombok.experimental.Accessors;
import pb01a.api.interfaces.PB01ANotifyServicesForAlarmEvent;
import pb01a.model.PB01AAlarmEvent;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AWorkPlaceID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AWorkPlaceOID;
import pb01a.model.oids.PB01APanicButtonOIDs.PB01AAlarmEventOID;
import pb01c.services.delegates.persistence.PB01CCRUDServicesDelegateForAlarmEvent;
import pb01c.services.delegates.persistence.PB01CNotifyServicesDelegateForAlarmEvent;
import r01f.bootstrap.services.config.core.ServicesCoreBootstrapConfigWhenBeanExposed;
import r01f.model.annotations.ModelObjectsMarshaller;
import r01f.model.persistence.CRUDResult;
import r01f.objectstreamer.Marshaller;
import r01f.securitycontext.SecurityContext;
import r01f.services.persistence.CorePersistenceServicesBase;
import r01f.services.persistence.ServiceDelegateProvider;

/**
 * Implements the {@link PB01AAlarmEvent} persistence-related services which in turn are
 * delegated to {@link PB01CCRUDServicesDelegateForAlarmEvent}
 */
@Singleton
@Accessors(prefix="_")
public class PB01CNotifyServicesImplForAlarmEvent
     extends CorePersistenceServicesBase
  implements PB01ANotifyServicesForAlarmEvent,
  			 PB01CPanicButtonCoreServiceImpl {
/////////////////////////////////////////////////////////////////////////////////////////
//	DELEGATE PROVIDER
/////////////////////////////////////////////////////////////////////////////////////////
	@Getter private final ServiceDelegateProvider<PB01CNotifyServicesDelegateForAlarmEvent> _delegateProvider;
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public PB01CNotifyServicesImplForAlarmEvent(							final ServicesCoreBootstrapConfigWhenBeanExposed coreCfg,
											   @ModelObjectsMarshaller 	final Marshaller modelObjectsMarshaller,
																		final EventBus eventBus,
									   		  							final Provider<EntityManager> entityManagerProvider) {
		super(coreCfg,
			  modelObjectsMarshaller,
			  eventBus,
			  entityManagerProvider);
		_delegateProvider = new ServiceDelegateProvider<PB01CNotifyServicesDelegateForAlarmEvent>() {
									@Override
									public PB01CNotifyServicesDelegateForAlarmEvent createDelegate(final SecurityContext securityContext) {
										return new PB01CNotifyServicesDelegateForAlarmEvent(_coreConfig,
																						   PB01CNotifyServicesImplForAlarmEvent.this.getFreshNewEntityManager(),
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
	public CRUDResult<PB01AAlarmEvent> raiseAlarm(final SecurityContext securityContext,
												 final PB01AWorkPlaceID id) {
		return this.forSecurityContext(securityContext)
						.createDelegateAs(PB01ANotifyServicesForAlarmEvent.class)
							.raiseAlarm(securityContext,
										id);
	}
	@Transactional
	@Override
	public CRUDResult<PB01AAlarmEvent> raiseAlarm(final SecurityContext securityContext,
												 final PB01AWorkPlaceOID id) {
		return this.forSecurityContext(securityContext)
						.createDelegateAs(PB01ANotifyServicesForAlarmEvent.class)
							.raiseAlarm(securityContext,
										id);
	}
	@Transactional
	@Override
	public CRUDResult<PB01AAlarmEvent> cancelAlarm(final SecurityContext securityContext,
												  final PB01AAlarmEventOID oid) {
		return this.forSecurityContext(securityContext)
						.createDelegateAs(PB01ANotifyServicesForAlarmEvent.class)
							.cancelAlarm(securityContext,
										 oid);
	}
}
