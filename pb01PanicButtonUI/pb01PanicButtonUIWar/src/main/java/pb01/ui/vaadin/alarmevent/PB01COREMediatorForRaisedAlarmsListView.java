package pb01.ui.vaadin.alarmevent;

import java.util.Collection;
import java.util.Date;

import javax.inject.Inject;
import javax.inject.Singleton;

import lombok.extern.slf4j.Slf4j;
import r01f.types.Range;
import r01f.ui.coremediator.UICOREMediatorBase;
import r01f.ui.coremediator.UICOREMediatorSubscriber;
import x47b.client.api.X47BPanicButtonClientAPI;
import x47b.model.X47BAlarmEvent;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionID;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionServiceID;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionServiceLocationID;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrganizationID;
import x47b.model.oids.X47BOrganizationalIDs.X47BWorkPlaceID;

@Slf4j
@Singleton
public class PB01COREMediatorForRaisedAlarmsListView
     extends UICOREMediatorBase<X47BPanicButtonClientAPI> {

/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public PB01COREMediatorForRaisedAlarmsListView(final X47BPanicButtonClientAPI api) {
		super(api);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	LIST RAISED ALARMS
/////////////////////////////////////////////////////////////////////////////////////////
	public void raisedAlarmsFor(final X47BOrganizationID orgId,final Range<Date> dateRange,
								final UICOREMediatorSubscriber<Collection<X47BAlarmEvent>> subscriber) {
		Collection<X47BAlarmEvent> alarms = _api.alarmEventsAPI()
												.getForFind()
												.findBySourceId(orgId,dateRange);
		subscriber.onSuccess(alarms);
	}
	public void raisedAlarmsFor(final X47BOrgDivisionID orgDivId,final Range<Date> dateRange,
								final UICOREMediatorSubscriber<Collection<X47BAlarmEvent>> subscriber) {
		Collection<X47BAlarmEvent> alarms = _api.alarmEventsAPI()
												.getForFind()
												.findBySourceId(orgDivId,dateRange);
		subscriber.onSuccess(alarms);
	}
	public void raisedAlarmsFor(final X47BOrgDivisionServiceID orgDivSrvcId,final Range<Date> dateRange,
								final UICOREMediatorSubscriber<Collection<X47BAlarmEvent>> subscriber) {
		Collection<X47BAlarmEvent> alarms = _api.alarmEventsAPI()
												.getForFind()
												.findBySourceId(orgDivSrvcId,dateRange);
		subscriber.onSuccess(alarms);
	}
	public void raisedAlarmsFor(final X47BOrgDivisionServiceLocationID orgDivSrvcLocId,final Range<Date> dateRange,
								final UICOREMediatorSubscriber<Collection<X47BAlarmEvent>> subscriber) {
		Collection<X47BAlarmEvent> alarms = _api.alarmEventsAPI()
												.getForFind()
												.findBySourceId(orgDivSrvcLocId,dateRange);
		subscriber.onSuccess(alarms);
	}
	public void raisedAlarmsFor(final X47BWorkPlaceID workPlaceId,final Range<Date> dateRange,
								final UICOREMediatorSubscriber<Collection<X47BAlarmEvent>> subscriber) {
		Collection<X47BAlarmEvent> alarms = _api.alarmEventsAPI()
												.getForFind()
												.findBySourceId(workPlaceId,dateRange);
		subscriber.onSuccess(alarms);
	}
}
