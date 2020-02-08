package pb01.ui.vaadin.view;

import javax.inject.Inject;
import javax.inject.Singleton;

import lombok.extern.slf4j.Slf4j;
import pb01a.client.api.PB01APanicButtonClientAPI;
import pb01a.model.PB01AAlarmEvent;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AWorkPlaceOID;
import pb01a.model.search.PB01ASearchFilterForPanicButtonOrganizationalEntity;
import pb01a.model.search.PB01ASearchResultItemForPanicButtonOrganizationalEntity;
import r01f.model.search.SearchResults;
import r01f.ui.coremediator.UICOREMediatorBase;
import r01f.ui.coremediator.UICOREMediatorSubscriber;

@Slf4j
@Singleton
public class PB01MainViewCOREMediator
     extends UICOREMediatorBase<PB01APanicButtonClientAPI> {

/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public PB01MainViewCOREMediator(final PB01APanicButtonClientAPI api) {
		super(api);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	FILTER
/////////////////////////////////////////////////////////////////////////////////////////
	public void search(final PB01ASearchFilterForPanicButtonOrganizationalEntity filter,
					   final int firstItemNum,final int numberOfItems,
					   final UICOREMediatorSubscriber<SearchResults<PB01ASearchFilterForPanicButtonOrganizationalEntity,
												  				    PB01ASearchResultItemForPanicButtonOrganizationalEntity>> subscriber) {
		final SearchResults<PB01ASearchFilterForPanicButtonOrganizationalEntity,
						 PB01ASearchResultItemForPanicButtonOrganizationalEntity> outResults = _api.entitySearchAPI()
																										.search(filter)
																										.fromItemAt(firstItemNum)
																										.returning(numberOfItems);
		subscriber.onSuccess(outResults);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	RAISE ALARM
/////////////////////////////////////////////////////////////////////////////////////////
	public void raiseAlarm(final PB01AWorkPlaceOID workPlaceOid,
						   final UICOREMediatorSubscriber<PB01AAlarmEvent> subscriber) {
		PB01AAlarmEvent event = _api.alarmEventsAPI()
										.getForNotify()
										.raiseAlarm(workPlaceOid);
		subscriber.onSuccess(event);
	}
}
