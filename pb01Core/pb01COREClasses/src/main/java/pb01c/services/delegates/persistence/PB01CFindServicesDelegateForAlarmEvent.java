package pb01c.services.delegates.persistence;

import java.util.Date;

import javax.persistence.EntityManager;

import com.google.common.eventbus.EventBus;

import lombok.extern.slf4j.Slf4j;
import pb01a.api.interfaces.PB01AFindServicesForAlarmEvent;
import pb01a.model.PB01AAlarmEvent;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionID;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionServiceID;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionServiceLocationID;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrganizationID;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AWorkPlaceID;
import pb01a.model.oids.PB01APanicButtonOIDs.PB01AAlarmEventOID;
import pb01c.db.find.PB01CDBFindForAlarmEvent;
import r01f.bootstrap.services.config.core.ServicesCoreBootstrapConfigWhenBeanExposed;
import r01f.model.persistence.FindResult;
import r01f.objectstreamer.Marshaller;
import r01f.persistence.db.config.DBModuleConfigBuilder;
import r01f.securitycontext.SecurityContext;
import r01f.services.delegates.persistence.FindServicesForModelObjectDelegateBase;
import r01f.types.Range;
import r01f.types.TimeLapse;
import r01f.types.contact.EMail;
import r01f.types.contact.Phone;

/**
 * Service layer delegated type for CRUD (Create/Read/Update/Delete) operations
 */
@Slf4j
public class PB01CFindServicesDelegateForAlarmEvent
	 extends FindServicesForModelObjectDelegateBase<PB01AAlarmEventOID,PB01AAlarmEvent>
  implements PB01AFindServicesForAlarmEvent {

/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01CFindServicesDelegateForAlarmEvent(final ServicesCoreBootstrapConfigWhenBeanExposed coreCfg,
												 final EntityManager entityManager,
											     final Marshaller marshaller,
				  			   		   	   	     final EventBus eventBus) {
		super(coreCfg,
			  PB01AAlarmEvent.class,
			  new PB01CDBFindForAlarmEvent(DBModuleConfigBuilder.dbModuleConfigFrom(coreCfg),
					  					  entityManager,
					  					  marshaller),
			  eventBus);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  EXTENSION METHODS
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public FindResult<PB01AAlarmEvent> findBySourceId(final SecurityContext securityContext,
													 final PB01AOrganizationID id,
													 final Range<Date> dateRange) {
		Range<Date> theDateRange = _ensureDateRange(dateRange);
		return this.getServiceImplAs(PB01AFindServicesForAlarmEvent.class)
						.findBySourceId(securityContext,
										id,
										theDateRange);
	}
	@Override
	public FindResult<PB01AAlarmEvent> findBySourceId(final SecurityContext securityContext,
													 final PB01AOrgDivisionID id,
													 final Range<Date> dateRange) {
		Range<Date> theDateRange = _ensureDateRange(dateRange);
		return this.getServiceImplAs(PB01AFindServicesForAlarmEvent.class)
						.findBySourceId(securityContext,
										id,
										theDateRange);
	}
	@Override
	public FindResult<PB01AAlarmEvent> findBySourceId(final SecurityContext securityContext,
													 final PB01AOrgDivisionServiceID id,
													 final Range<Date> dateRange) {
		Range<Date> theDateRange = _ensureDateRange(dateRange);
		return this.getServiceImplAs(PB01AFindServicesForAlarmEvent.class)
						.findBySourceId(securityContext,
										id,
										theDateRange);
	}
	@Override
	public FindResult<PB01AAlarmEvent> findBySourceId(final SecurityContext securityContext,
													 final PB01AOrgDivisionServiceLocationID id,
													 final Range<Date> dateRange) {
		Range<Date> theDateRange = _ensureDateRange(dateRange);
		return this.getServiceImplAs(PB01AFindServicesForAlarmEvent.class)
						.findBySourceId(securityContext,
										id,
										theDateRange);
	}
	@Override
	public FindResult<PB01AAlarmEvent> findBySourceId(final SecurityContext securityContext,
													 final PB01AWorkPlaceID id,
													 final Range<Date> dateRange) {
		Range<Date> theDateRange = _ensureDateRange(dateRange);
		return this.getServiceImplAs(PB01AFindServicesForAlarmEvent.class)
						.findBySourceId(securityContext,
										id,
										theDateRange);
	}
	@Override
	public FindResult<PB01AAlarmEvent> findByNotifiedPhone(final SecurityContext securityContext,
											   		 	  final Phone phone,
											   		 	  final Range<Date> dateRange) {
		Range<Date> theDateRange = _ensureDateRange(dateRange);
		return this.getServiceImplAs(PB01AFindServicesForAlarmEvent.class)
						.findByNotifiedPhone(securityContext,
											 phone,
											 theDateRange);
	}
	@Override
	public FindResult<PB01AAlarmEvent> findByNotifiedEMail(final SecurityContext securityContext,
											   		 	  final EMail email,
											   		 	  final Range<Date> dateRange) {
		Range<Date> theDateRange = _ensureDateRange(dateRange);
		return this.getServiceImplAs(PB01AFindServicesForAlarmEvent.class)
						.findByNotifiedEMail(securityContext,
											 email,
											 theDateRange);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	private Range<Date> _ensureDateRange(final Range<Date> dateRange) {
		TimeLapse defTimeLapse = TimeLapse.createFor("1d");	// 1 day

		Range<Date> outDateRange = null;
		if (dateRange == null
		 || (!dateRange.hasLowerBound() && !dateRange.hasUpperBound())) {
			// Create the start and end date
			log.warn("NO date range was provided to find the raised alarm events; a default one is created!");
			Date now = new Date();
			Date startDate = new Date(now.getTime() - defTimeLapse.asMilis());	// time before
			Date endDate = now;

			outDateRange = Range.closed(startDate,endDate);
		}
		else if (!dateRange.hasLowerBound()) {
			Date startDate = new Date(dateRange.getUpperBound().getTime() - defTimeLapse.asMilis());	// time before

			outDateRange = Range.closed(startDate,dateRange.getUpperBound());
		}
		else if (!dateRange.hasUpperBound()) {
			Date endDate = new Date(dateRange.getLowerBound().getTime() + defTimeLapse.asMilis());	// time after

			outDateRange = Range.closed(dateRange.getLowerBound(),endDate);
		}
		else {
			// The date range is correct
			outDateRange = dateRange;
		}
		return outDateRange;
	}
}
