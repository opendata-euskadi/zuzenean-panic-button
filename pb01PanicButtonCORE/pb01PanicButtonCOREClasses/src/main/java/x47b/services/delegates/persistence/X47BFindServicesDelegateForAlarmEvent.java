package x47b.services.delegates.persistence;

import java.util.Date;

import javax.persistence.EntityManager;

import com.google.common.eventbus.EventBus;
import com.google.inject.persist.Transactional;

import lombok.extern.slf4j.Slf4j;
import r01f.bootstrap.services.config.core.ServicesCoreBootstrapConfigWhenBeanExposed;
import r01f.model.persistence.FindResult;
import r01f.objectstreamer.Marshaller;
import r01f.persistence.db.config.DBModuleConfigBuilder;
import r01f.securitycontext.SecurityContext;
import r01f.services.delegates.persistence.FindServicesForModelObjectDelegateBase;
import r01f.types.Range;
import r01f.types.TimeLapse;
import x47b.api.interfaces.X47BFindServicesForAlarmEvent;
import x47b.db.find.X47BDBFindForAlarmEvent;
import x47b.model.X47BAlarmEvent;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionID;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionServiceID;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionServiceLocationID;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrganizationID;
import x47b.model.oids.X47BOrganizationalIDs.X47BWorkPlaceID;
import x47b.model.oids.X47BPanicButtonOIDs.X47BAlarmEventOID;

/**
 * Service layer delegated type for CRUD (Create/Read/Update/Delete) operations
 */
@Slf4j
public class X47BFindServicesDelegateForAlarmEvent
	 extends FindServicesForModelObjectDelegateBase<X47BAlarmEventOID,X47BAlarmEvent>
  implements X47BFindServicesForAlarmEvent {

/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BFindServicesDelegateForAlarmEvent(final ServicesCoreBootstrapConfigWhenBeanExposed coreCfg,
												 final EntityManager entityManager,
											     final Marshaller marshaller,
				  			   		   	   	     final EventBus eventBus) {
		super(coreCfg,
			  X47BAlarmEvent.class,
			  new X47BDBFindForAlarmEvent(DBModuleConfigBuilder.dbModuleConfigFrom(coreCfg),
					  					  entityManager,
					  					  marshaller),
			  eventBus);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  EXTENSION METHODS
/////////////////////////////////////////////////////////////////////////////////////////
	@Transactional
	@Override
	public FindResult<X47BAlarmEvent> findBySourceId(final SecurityContext securityContext,
													 final X47BOrganizationID id,
													 final Range<Date> dateRange) {
		Range<Date> theDateRange = _ensureDateRange(dateRange);
		return this.getServiceImplAs(X47BFindServicesForAlarmEvent.class)
						.findBySourceId(securityContext,
										id,
										theDateRange);
	}
	@Transactional
	@Override
	public FindResult<X47BAlarmEvent> findBySourceId(final SecurityContext securityContext,
													 final X47BOrgDivisionID id,
													 final Range<Date> dateRange) {
		Range<Date> theDateRange = _ensureDateRange(dateRange);
		return this.getServiceImplAs(X47BFindServicesForAlarmEvent.class)
						.findBySourceId(securityContext,
										id,
										theDateRange);
	}
	@Transactional
	@Override
	public FindResult<X47BAlarmEvent> findBySourceId(final SecurityContext securityContext,
													 final X47BOrgDivisionServiceID id,
													 final Range<Date> dateRange) {
		Range<Date> theDateRange = _ensureDateRange(dateRange);
		return this.getServiceImplAs(X47BFindServicesForAlarmEvent.class)
						.findBySourceId(securityContext,
										id,
										theDateRange);
	}
	@Transactional
	@Override
	public FindResult<X47BAlarmEvent> findBySourceId(final SecurityContext securityContext,
													 final X47BOrgDivisionServiceLocationID id,
													 final Range<Date> dateRange) {
		Range<Date> theDateRange = _ensureDateRange(dateRange);
		return this.getServiceImplAs(X47BFindServicesForAlarmEvent.class)
						.findBySourceId(securityContext,
										id,
										theDateRange);
	}
	@Transactional
	@Override
	public FindResult<X47BAlarmEvent> findBySourceId(final SecurityContext securityContext,
													 final X47BWorkPlaceID id,
													 final Range<Date> dateRange) {
		Range<Date> theDateRange = _ensureDateRange(dateRange);
		return this.getServiceImplAs(X47BFindServicesForAlarmEvent.class)
						.findBySourceId(securityContext,
										id,
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
