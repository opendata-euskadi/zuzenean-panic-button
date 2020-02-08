package pb01.ui.vaadin.alarmevent;

import java.util.Collection;
import java.util.Date;

import javax.inject.Inject;
import javax.inject.Singleton;

import lombok.extern.slf4j.Slf4j;
import pb01a.client.api.PB01APanicButtonClientAPI;
import pb01a.model.PB01AAlarmEvent;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionID;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionServiceID;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionServiceLocationID;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrganizationID;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AWorkPlaceID;
import r01f.types.Range;
import r01f.ui.coremediator.UICOREMediatorBase;
import r01f.ui.coremediator.UICOREMediatorSubscriber;

@Slf4j
@Singleton
public class PB01COREMediatorForRaisedAlarmsListView
     extends UICOREMediatorBase<PB01APanicButtonClientAPI> {

/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public PB01COREMediatorForRaisedAlarmsListView(final PB01APanicButtonClientAPI api) {
		super(api);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	LIST RAISED ALARMS
/////////////////////////////////////////////////////////////////////////////////////////
	public void raisedAlarmsFor(final PB01AOrganizationID orgId,final Range<Date> dateRange,
								final UICOREMediatorSubscriber<Collection<PB01AAlarmEvent>> subscriber) {
		Collection<PB01AAlarmEvent> alarms = _api.alarmEventsAPI()
												.getForFind()
												.findBySourceId(orgId,dateRange);
		subscriber.onSuccess(alarms);
	}
	public void raisedAlarmsFor(final PB01AOrgDivisionID orgDivId,final Range<Date> dateRange,
								final UICOREMediatorSubscriber<Collection<PB01AAlarmEvent>> subscriber) {
		Collection<PB01AAlarmEvent> alarms = _api.alarmEventsAPI()
												.getForFind()
												.findBySourceId(orgDivId,dateRange);
		subscriber.onSuccess(alarms);
	}
	public void raisedAlarmsFor(final PB01AOrgDivisionServiceID orgDivSrvcId,final Range<Date> dateRange,
								final UICOREMediatorSubscriber<Collection<PB01AAlarmEvent>> subscriber) {
		Collection<PB01AAlarmEvent> alarms = _api.alarmEventsAPI()
												.getForFind()
												.findBySourceId(orgDivSrvcId,dateRange);
		subscriber.onSuccess(alarms);
	}
	public void raisedAlarmsFor(final PB01AOrgDivisionServiceLocationID orgDivSrvcLocId,final Range<Date> dateRange,
								final UICOREMediatorSubscriber<Collection<PB01AAlarmEvent>> subscriber) {
		Collection<PB01AAlarmEvent> alarms = _api.alarmEventsAPI()
												.getForFind()
												.findBySourceId(orgDivSrvcLocId,dateRange);
		subscriber.onSuccess(alarms);
	}
	public void raisedAlarmsFor(final PB01AWorkPlaceID workPlaceId,final Range<Date> dateRange,
								final UICOREMediatorSubscriber<Collection<PB01AAlarmEvent>> subscriber) {
		Collection<PB01AAlarmEvent> alarms = _api.alarmEventsAPI()
												.getForFind()
												.findBySourceId(workPlaceId,dateRange);
		subscriber.onSuccess(alarms);
	}
}
