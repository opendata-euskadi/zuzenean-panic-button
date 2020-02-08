package pb01c.services;

import java.util.Date;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import javax.persistence.EntityManager;

import com.google.common.eventbus.EventBus;
import com.google.inject.persist.Transactional;

import lombok.Getter;
import lombok.experimental.Accessors;
import pb01a.api.interfaces.PB01AFindServicesForAlarmEvent;
import pb01a.model.PB01AAlarmEvent;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionID;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionServiceID;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionServiceLocationID;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrganizationID;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AWorkPlaceID;
import pb01a.model.oids.PB01APanicButtonOIDs.PB01AAlarmEventOID;
import pb01c.services.delegates.persistence.PB01CFindServicesDelegateForAlarmEvent;
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

/**
 * Implements the {@link PB01AAlarmEvent} find-related services which in turn are
 * delegated to {@link PB01AFindServicesForAlarmEvent}
 */
@Singleton
@Accessors(prefix="_")
public class PB01CFindServicesImplForAlarmEvent
     extends CoreFindServicesForModelObjectBase<PB01AAlarmEventOID,PB01AAlarmEvent>
  implements PB01AFindServicesForAlarmEvent,
  			 PB01CPanicButtonCoreServiceImpl {
/////////////////////////////////////////////////////////////////////////////////////////
//	DELEGATE PROVIDER
/////////////////////////////////////////////////////////////////////////////////////////
	@Getter private final ServiceDelegateProvider<PB01CFindServicesDelegateForAlarmEvent> _delegateProvider;
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public PB01CFindServicesImplForAlarmEvent(							final ServicesCoreBootstrapConfigWhenBeanExposed coreCfg,
											 @ModelObjectsMarshaller 	final Marshaller modelObjectsMarshaller,
									   		  							final EventBus eventBus,
									   		  							final Provider<EntityManager> entityManagerProvider) {
		super(coreCfg,
			  modelObjectsMarshaller,
			  eventBus,
			  entityManagerProvider);
		_delegateProvider = new ServiceDelegateProvider<PB01CFindServicesDelegateForAlarmEvent>() {
									@Override
									public PB01CFindServicesDelegateForAlarmEvent createDelegate(final SecurityContext securityContext) {
										return new PB01CFindServicesDelegateForAlarmEvent(_coreConfig,
																						 PB01CFindServicesImplForAlarmEvent.this.getFreshNewEntityManager(),
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
	public FindResult<PB01AAlarmEvent> findBySourceId(final SecurityContext securityContext,
										  	  		 final PB01AOrganizationID id,
										  	  		 final Range<Date> dateRange) {
		return this.forSecurityContext(securityContext)
						.createDelegateAs(PB01AFindServicesForAlarmEvent.class)
							.findBySourceId(securityContext,
									  		id,
									  		dateRange);
	}
	@Transactional
	@Override
	public FindResult<PB01AAlarmEvent> findBySourceId(final SecurityContext securityContext,
										  	  		 final PB01AOrgDivisionID id,
										  	  		 final Range<Date> dateRange) {
		return this.forSecurityContext(securityContext)
						.createDelegateAs(PB01AFindServicesForAlarmEvent.class)
							.findBySourceId(securityContext,
									  		id,
									  		dateRange);
	}
	@Transactional
	@Override
	public FindResult<PB01AAlarmEvent> findBySourceId(final SecurityContext securityContext,
										  	  		 final PB01AOrgDivisionServiceID id,
										  	  		 final Range<Date> dateRange) {
		return this.forSecurityContext(securityContext)
						.createDelegateAs(PB01AFindServicesForAlarmEvent.class)
							.findBySourceId(securityContext,
									  		id,
									  		dateRange);
	}
	@Transactional
	@Override
	public FindResult<PB01AAlarmEvent> findBySourceId(final SecurityContext securityContext,
										  	  		 final PB01AOrgDivisionServiceLocationID id,
										  	  		 final Range<Date> dateRange) {
		return this.forSecurityContext(securityContext)
						.createDelegateAs(PB01AFindServicesForAlarmEvent.class)
							.findBySourceId(securityContext,
									  		id,
									  		dateRange);
	}
	@Transactional
	@Override
	public FindResult<PB01AAlarmEvent> findBySourceId(final SecurityContext securityContext,
										  	  		 final PB01AWorkPlaceID id,
										  	  		 final Range<Date> dateRange) {
		return this.forSecurityContext(securityContext)
						.createDelegateAs(PB01AFindServicesForAlarmEvent.class)
							.findBySourceId(securityContext,
									  		id,
									  		dateRange);
	}
	@Transactional
	@Override
	public FindResult<PB01AAlarmEvent> findByNotifiedPhone(final SecurityContext securityContext,
											   		 	  final Phone phone,
											   		 	  final Range<Date> dateRange) {
		return this.forSecurityContext(securityContext)
						.createDelegateAs(PB01AFindServicesForAlarmEvent.class)
							.findByNotifiedPhone(securityContext,
									  			 phone,
									  			 dateRange);
	}
	@Transactional
	@Override
	public FindResult<PB01AAlarmEvent> findByNotifiedEMail(final SecurityContext securityContext,
											   		 	  final EMail email,
											   		 	  final Range<Date> dateRange) {
		return this.forSecurityContext(securityContext)
						.createDelegateAs(PB01AFindServicesForAlarmEvent.class)
							.findByNotifiedEMail(securityContext,
									  			 email,
									  			 dateRange);
	}
}
