package pb01.ui.vaadin.alarmevent;

import java.util.Collection;

import javax.inject.Inject;

import com.google.common.collect.Lists;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import lombok.extern.slf4j.Slf4j;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionID;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionServiceID;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionServiceLocationID;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrganizationID;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AWorkPlaceID;
import r01f.types.TimeLapse;
import r01f.ui.i18n.UII18NService;
import r01f.ui.presenter.UIPresenterSubscriber;
import r01f.util.types.collections.CollectionUtils;

@Slf4j
public class PB01RaisedAlarmsListView
	 extends Window {

	private static final long serialVersionUID = 4474681131796734900L;
/////////////////////////////////////////////////////////////////////////////////////////
//  FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	private final transient UII18NService _i18n;
	private final transient PB01PresenterForRaisedAlarmsListView _presenter;
	private final transient UIPresenterSubscriber<Collection<PB01ViewAlarmEvent>> _presenterSubscriber;

	private final Grid<PB01ViewAlarmEvent> _grid = new Grid<>();

	private ListDataProvider<PB01ViewAlarmEvent> _gridDataProvider;

/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public PB01RaisedAlarmsListView(final UII18NService i18n,
									final PB01PresenterForRaisedAlarmsListView presenter) {
		_i18n = i18n;
		_presenter = presenter;
		_presenterSubscriber = UIPresenterSubscriber.from(
									// when loaded alarms
									viewAlarms -> _paintGridItems(viewAlarms),
									// on error
									th -> log.info("Error while loading raised alarms; {}",	// TODO do something useful with the error
																		   th.getMessage(),th));

		_buildGridColumns();
        _grid.setStyleName( "stripes" );
        _grid.setSizeFull();
        _grid.setHeightMode( HeightMode.ROW );
        _grid.setHeightByRows(10);
        _grid.setWidth("100%");

		// Layout
		final VerticalLayout layout = new VerticalLayout();
		layout.setMargin( true );
		layout.addComponent(_grid);

		this.setContent(layout);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	PUBLIC ENTRY POINT
/////////////////////////////////////////////////////////////////////////////////////////
	public void listAlarmEventsFor(final PB01AOrganizationID orgId,final TimeLapse timeLapse) {
		_presenter.onRaisedAlarmsListDataNeeded(orgId,timeLapse,
												_i18n.getCurrentLanguage(),
												_presenterSubscriber);
	}
	public void listAlarmEventsFor(final PB01AOrgDivisionID orgDivId,final TimeLapse timeLapse) {
		_presenter.onRaisedAlarmsListDataNeeded(orgDivId,timeLapse,
												_i18n.getCurrentLanguage(),
												_presenterSubscriber);
	}
	public void listAlarmEventsFor(final PB01AOrgDivisionServiceID orgDivSrvcId,final TimeLapse timeLapse) {
		_presenter.onRaisedAlarmsListDataNeeded(orgDivSrvcId,timeLapse,
												_i18n.getCurrentLanguage(),
												_presenterSubscriber);
	}
	public void listAlarmEventsFor(final PB01AOrgDivisionServiceLocationID orgDivSrvcLocId,final TimeLapse timeLapse) {
		_presenter.onRaisedAlarmsListDataNeeded(orgDivSrvcLocId,timeLapse,
												_i18n.getCurrentLanguage(),
												_presenterSubscriber);
	}
	public void listAlarmEventsFor(final PB01AWorkPlaceID wpId,final TimeLapse timeLapse) {
		_presenter.onRaisedAlarmsListDataNeeded(wpId,timeLapse,
												_i18n.getCurrentLanguage(),
												_presenterSubscriber);
	}
	private void _paintGridItems(final Collection<PB01ViewAlarmEvent> items) {
		// set the data
		if (CollectionUtils.hasData(items)) {
			_gridDataProvider = DataProvider.ofCollection(items);
		} else {
			_gridDataProvider = DataProvider.ofCollection(Lists.newArrayList());		// an empty grid
		}
		_grid.setDataProvider(_gridDataProvider);
		this.setVisible(CollectionUtils.hasData(items));
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	private void _buildGridColumns() {
        // Organization
		_grid.addColumn( PB01ViewAlarmEvent::getOrganizationName )
				 .setCaption( _i18n.getMessage("pb01.org") )
				 .setResizable(true)
				 .setId( "org" );
		// Division
		_grid.addColumn( PB01ViewAlarmEvent::getDivisionName )
			 .setCaption( _i18n.getMessage("pb01.org.division") )
			 .setResizable(true)
			 .setId( "orgDiv" );
		// Service
		_grid.addColumn( PB01ViewAlarmEvent::getServiceName )
			 .setCaption( _i18n.getMessage("pb01.org.service") )
			 .setResizable(true)
			 .setId( "orgDivSrvc" );
		// Location
		_grid.addColumn( PB01ViewAlarmEvent::getLocationName )
			 .setCaption( _i18n.getMessage("pb01.org.location") )
			 .setResizable(true)
			 .setId( "orgDivSrvcLoc" );
		// WorkPlace
		_grid.addColumn( PB01ViewAlarmEvent::getWorkPlaceName )
			 .setCaption( _i18n.getMessage("pb01.org.workPlace") )
			 .setResizable(true)
			 .setId( "workPlace" );
		// DateTime
		_grid.addColumn( PB01ViewAlarmEvent::getDateTime )
			 .setCaption( _i18n.getMessage("pb01.alarm.dateTime") )
			 .setResizable(true)
			 .setId( "dateTime" );
	}
}
