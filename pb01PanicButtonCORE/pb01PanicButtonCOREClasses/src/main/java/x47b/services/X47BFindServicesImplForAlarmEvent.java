package x47b.services;

import java.util.Date;

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
import r01f.model.persistence.FindResult;
import r01f.objectstreamer.Marshaller;
import r01f.securitycontext.SecurityContext;
import r01f.services.persistence.CoreFindServicesForModelObjectBase;
import r01f.services.persistence.ServiceDelegateProvider;
import r01f.types.Range;
import r01f.types.contact.EMail;
import r01f.types.contact.Phone;
import x47b.api.interfaces.X47BFindServicesForAlarmEvent;
import x47b.model.X47BAlarmEvent;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionID;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionServiceID;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionServiceLocationID;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrganizationID;
import x47b.model.oids.X47BOrganizationalIDs.X47BWorkPlaceID;
import x47b.model.oids.X47BPanicButtonOIDs.X47BAlarmEventOID;
import x47b.services.delegates.persistence.X47BFindServicesDelegateForAlarmEvent;

/**
 * Implements the {@link X47BAlarmEvent} find-related services which in turn are
 * delegated to {@link X47BFindServicesForAlarmEvent}
 */
@Singleton
@Accessors(prefix="_")
public class X47BFindServicesImplForAlarmEvent
     extends CoreFindServicesForModelObjectBase<X47BAlarmEventOID,X47BAlarmEvent>
  implements X47BFindServicesForAlarmEvent,
  			 X47BPanicButtonCoreServiceImpl {
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public X47BFindServicesImplForAlarmEvent(							final ServicesCoreBootstrapConfigWhenBeanExposed coreCfg,
											 @ModelObjectsMarshaller 	final Marshaller modelObjectsMarshaller,
									   		  							final EventBus eventBus,
									   		  							final Provider<EntityManager> entityManagerProvider) {
		super(coreCfg,
			  modelObjectsMarshaller,
			  eventBus,
			  entityManagerProvider);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	DELEGATE PROVIDER
/////////////////////////////////////////////////////////////////////////////////////////
	@Getter private final ServiceDelegateProvider<X47BFindServicesDelegateForAlarmEvent> _delegateProvider =
							new ServiceDelegateProvider<X47BFindServicesDelegateForAlarmEvent>() {
										@Override
										public X47BFindServicesDelegateForAlarmEvent createDelegate(final SecurityContext securityContext) {
											return new X47BFindServicesDelegateForAlarmEvent(_coreConfig,
																							 X47BFindServicesImplForAlarmEvent.this.getFreshNewEntityManager(),
																							 _modelObjectsMarshaller,
																							 _eventBus);
										}
							};
/////////////////////////////////////////////////////////////////////////////////////////
//	SERVICES EXTENSION
// 	IMPORTANT!!! Do NOT put any logic in these methods ONLY DELEGATE!!!
/////////////////////////////////////////////////////////////////////////////////////////
	@Transactional
	@Override
	public FindResult<X47BAlarmEvent> findBySourceId(final SecurityContext securityContext,
										  	  		 final X47BOrganizationID id,
										  	  		 final Range<Date> dateRange) {
		return this.forSecurityContext(securityContext)
						.createDelegateAs(X47BFindServicesForAlarmEvent.class)
							.findBySourceId(securityContext,
									  		id,
									  		dateRange);
	}
	@Transactional
	@Override
	public FindResult<X47BAlarmEvent> findBySourceId(final SecurityContext securityContext,
										  	  		 final X47BOrgDivisionID id,
										  	  		 final Range<Date> dateRange) {
		return this.forSecurityContext(securityContext)
						.createDelegateAs(X47BFindServicesForAlarmEvent.class)
							.findBySourceId(securityContext,
									  		id,
									  		dateRange);
	}
	@Transactional
	@Override
	public FindResult<X47BAlarmEvent> findBySourceId(final SecurityContext securityContext,
										  	  		 final X47BOrgDivisionServiceID id,
										  	  		 final Range<Date> dateRange) {
		return this.forSecurityContext(securityContext)
						.createDelegateAs(X47BFindServicesForAlarmEvent.class)
							.findBySourceId(securityContext,
									  		id,
									  		dateRange);
	}
	@Transactional
	@Override
	public FindResult<X47BAlarmEvent> findBySourceId(final SecurityContext securityContext,
										  	  		 final X47BOrgDivisionServiceLocationID id,
										  	  		 final Range<Date> dateRange) {
		return this.forSecurityContext(securityContext)
						.createDelegateAs(X47BFindServicesForAlarmEvent.class)
							.findBySourceId(securityContext,
									  		id,
									  		dateRange);
	}
	@Transactional
	@Override
	public FindResult<X47BAlarmEvent> findBySourceId(final SecurityContext securityContext,
										  	  		 final X47BWorkPlaceID id,
										  	  		 final Range<Date> dateRange) {
		return this.forSecurityContext(securityContext)
						.createDelegateAs(X47BFindServicesForAlarmEvent.class)
							.findBySourceId(securityContext,
									  		id,
									  		dateRange);
	}
	@Transactional
	@Override
	public FindResult<X47BAlarmEvent> findByNotifiedPhone(final SecurityContext securityContext,
											   		 	  final Phone phone,
											   		 	  final Range<Date> dateRange) {
		return this.forSecurityContext(securityContext)
						.createDelegateAs(X47BFindServicesForAlarmEvent.class)
							.findByNotifiedPhone(securityContext,
									  			 phone,
									  			 dateRange);
	}
	@Transactional
	@Override
	public FindResult<X47BAlarmEvent> findByNotifiedEMail(final SecurityContext securityContext,
											   		 	  final EMail email,
											   		 	  final Range<Date> dateRange) {
		return this.forSecurityContext(securityContext)
						.createDelegateAs(X47BFindServicesForAlarmEvent.class)
							.findByNotifiedEMail(securityContext,
									  			 email,
									  			 dateRange);
	}
}
