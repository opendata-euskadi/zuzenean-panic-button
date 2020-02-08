package pb01.ui.vaadin.view;

import javax.inject.Inject;

import com.vaadin.navigator.View;
import com.vaadin.shared.ui.window.WindowMode;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import lombok.extern.slf4j.Slf4j;
import pb01.ui.vaadin.alarmevent.PB01PresenterForRaisedAlarmsListView;
import pb01.ui.vaadin.orgentity.PB01DetailWindowForOrganizationalEntityBase;
import pb01.ui.vaadin.orgentity.organization.PB01DetailWindowForOrganization;
import pb01.ui.vaadin.orgentity.organization.PB01PresenterForOrganizationDetailView;
import pb01.ui.vaadin.orgentity.orgdivision.PB01DetailWindowForOrgDivision;
import pb01.ui.vaadin.orgentity.orgdivision.PB01PresenterForOrgDivisionDetailView;
import pb01.ui.vaadin.orgentity.orgdivisionservice.PB01DetailWindowForOrgDivisionService;
import pb01.ui.vaadin.orgentity.orgdivisionservice.PB01PresenterForOrgDivisionServiceDetailView;
import pb01.ui.vaadin.orgentity.orgdivisionservicelocation.PB01DetailWindowForOrgDivisionServiceLocation;
import pb01.ui.vaadin.orgentity.orgdivisionservicelocation.PB01PresenterForOrgDivisionServiceLocationDetailView;
import pb01.ui.vaadin.orgentity.workplace.PB01DetailWindowForWorkPlace;
import pb01.ui.vaadin.orgentity.workplace.PB01PresenterForWorkPlaceDetailView;
import pb01.ui.vaadin.view.PB01CascadedComboEvents.PB01ComboValueChangedEvent;
import pb01.ui.vaadin.view.PB01CascadedCombos.PB01CascadedComboForOrgDivision;
import pb01.ui.vaadin.view.PB01CascadedCombos.PB01CascadedComboForOrgDivisionService;
import pb01.ui.vaadin.view.PB01CascadedCombos.PB01CascadedComboForOrgDivisionServiceLocation;
import pb01.ui.vaadin.view.PB01CascadedCombos.PB01CascadedComboForOrganization;
import pb01.ui.vaadin.view.PB01CascadedCombos.PB01CascadedComboForWorkPlace;
import pb01.ui.vaadin.view.components.PB01VaadinComboItem;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgObjectID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgObjectOID;
import pb01a.model.org.PB01AOrgObjectRef;
import r01f.ui.i18n.UII18NService;
import r01f.ui.presenter.UIPresenterSubscriber;

@Slf4j
public class PB01MainView
 	 extends VerticalLayout
  implements View {

	private static final long serialVersionUID = 4570960508688725887L;
/////////////////////////////////////////////////////////////////////////////////////////
//  FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	private final transient UII18NService _i18n;
	private final transient PB01MainViewPresenter _presenter;

	// Organizations
	private final PB01DetailWindowForOrganization _orgDetailPopUp;
	private final PB01CascadedComboForOrganization _orgCmb;
	// Org divisions
	private final PB01DetailWindowForOrgDivision _orgDivDetailPopUp;
	private final PB01CascadedComboForOrgDivision _orgDivCmb;

	// Org division services
	private final PB01DetailWindowForOrgDivisionService _orgDivSrvcDetailPopUp;
	private final PB01CascadedComboForOrgDivisionService _orgDivSrvcCmb;

	// Org division services location
	private final PB01DetailWindowForOrgDivisionServiceLocation _orgDivSrvcLocDetailPopUp;
	private final PB01CascadedComboForOrgDivisionServiceLocation _orgDivSrvcLocCmb;

	// WorkPlaces
	private final PB01DetailWindowForWorkPlace _workPlaceDetailPopUp;
	private final PB01CascadedComboForWorkPlace _workPlaceCmb;

	// Grid
	private final PB01MainGridView _gridView;
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public PB01MainView(final UII18NService i18n,
						// the presenter
						final PB01MainViewPresenter presenter,
						// presenter for the detail view of every org entity
						final PB01PresenterForOrganizationDetailView orgDetailViewPresenter,
						final PB01PresenterForOrgDivisionDetailView orgDivDetailViewPresenter,
						final PB01PresenterForOrgDivisionServiceDetailView orgDivSrvcDetailViewPresenter,
						final PB01PresenterForOrgDivisionServiceLocationDetailView orgDivSrvcLocDetailViewPresenter,
						final PB01PresenterForWorkPlaceDetailView workPlaceDetailViewPresenter,
						// presenter for the alarm list view
						final PB01PresenterForRaisedAlarmsListView alarmListViewPresenter) {
		_i18n = i18n;
		_presenter = presenter;

		///////// Store popup windows
		_orgDetailPopUp = new PB01DetailWindowForOrganization(i18n,
															  orgDetailViewPresenter);
		_orgDivDetailPopUp = new PB01DetailWindowForOrgDivision(i18n,
																orgDivDetailViewPresenter);
		_orgDivSrvcDetailPopUp = new PB01DetailWindowForOrgDivisionService(i18n,
																		   orgDivSrvcDetailViewPresenter);
		_orgDivSrvcLocDetailPopUp = new PB01DetailWindowForOrgDivisionServiceLocation(i18n,
																			  		  orgDivSrvcLocDetailViewPresenter);
		_workPlaceDetailPopUp = new PB01DetailWindowForWorkPlace(i18n,
																 workPlaceDetailViewPresenter);
		_stylePopUpWin(_orgDetailPopUp);
		_stylePopUpWin(_orgDivDetailPopUp);
		_stylePopUpWin(_orgDivSrvcDetailPopUp);
		_stylePopUpWin(_orgDivSrvcLocDetailPopUp);
		_stylePopUpWin(_workPlaceDetailPopUp);

		///////// Create combos
		_orgCmb = new PB01CascadedComboForOrganization(i18n,
													   presenter,
													   _orgDetailPopUp);
		_orgDivCmb = new PB01CascadedComboForOrgDivision(i18n,
													     presenter,
													     _orgDivDetailPopUp);
		_orgDivSrvcCmb = new PB01CascadedComboForOrgDivisionService(i18n,
													     			presenter,
													     			_orgDivSrvcDetailPopUp);
		_orgDivSrvcLocCmb = new PB01CascadedComboForOrgDivisionServiceLocation(i18n,
													     					   presenter,
													     					   _orgDivSrvcLocDetailPopUp);
		_workPlaceCmb =  new PB01CascadedComboForWorkPlace(i18n,
								     					   presenter,
								     					   _workPlaceDetailPopUp);
		///////// set parent & child combos
		_orgCmb.setParentCombo(null);
		_orgCmb.setChildCombos(_orgDivCmb,
							   _orgDivSrvcCmb,
							   _orgDivSrvcLocCmb,
							   _workPlaceCmb);
		_orgDivCmb.setParentCombo(_orgCmb);
		_orgDivCmb.setChildCombos(_orgDivSrvcCmb,
							   	  _orgDivSrvcLocCmb,
							   	  _workPlaceCmb);
		_orgDivSrvcCmb.setParentCombo(_orgDivCmb);
		_orgDivSrvcCmb.setChildCombos(_orgDivSrvcLocCmb,
							   		  _workPlaceCmb);
		_orgDivSrvcLocCmb.setParentCombo(_orgDivSrvcCmb);
		_orgDivSrvcLocCmb.setChildCombos(_workPlaceCmb);
		_workPlaceCmb.setParentCombo(_orgDivSrvcLocCmb);

		///////// Event listeners
		_orgCmb.setValueChangeEventListener(this::_handleComboValueChangeEvent);
		_orgDivCmb.setValueChangeEventListener(this::_handleComboValueChangeEvent);
		_orgDivSrvcCmb.setValueChangeEventListener(this::_handleComboValueChangeEvent);
		_orgDivSrvcLocCmb.setValueChangeEventListener(this::_handleComboValueChangeEvent);
		_workPlaceCmb.setValueChangeEventListener(this::_handleComboValueChangeEvent);

		///////// add combo components
		this.addComponent(_orgCmb);
		this.addComponent(_orgDivCmb);
		this.addComponent(_orgDivSrvcCmb);
		this.addComponent(_orgDivSrvcLocCmb);
		this.addComponent(_workPlaceCmb);

		///////// Grid
		_gridView = new PB01MainGridView(i18n,
										 presenter,								// the presenter
										 // presenter for the alarm list view
										 alarmListViewPresenter,
										 // what happens when an org entity is clicked on the grid
										 orgClickedEvent -> _showDetailViewForEditingExistingRecord(orgClickedEvent.getObjRef(),
												 												 	_orgDetailPopUp,
												 												 	_orgCmb),
										 orgDivClickedEvent -> _showDetailViewForEditingExistingRecord(orgDivClickedEvent.getObjRef(),
												 													   _orgDivDetailPopUp,
												 													   _orgDivCmb),
										 orgDivSrvcClickedEvent -> _showDetailViewForEditingExistingRecord(orgDivSrvcClickedEvent.getObjRef(),
												 														   _orgDivSrvcDetailPopUp,
												 														   _orgDivSrvcCmb),
										 orgDivSrvcLocClickedEvent -> _showDetailViewForEditingExistingRecord(orgDivSrvcLocClickedEvent.getObjRef(),
												 															  _orgDivSrvcLocDetailPopUp,
												 															  _orgDivSrvcLocCmb),
										 workPlaceClickedEvent -> _showDetailViewForEditingExistingRecord(workPlaceClickedEvent.getObjRef(),
												 														  _workPlaceDetailPopUp,
												 														  _workPlaceCmb));
		this.addComponent(_gridView);
		_gridView.setVisible(false);	// by default nothing is selected

		// Refresh combos
		_orgCmb.refresh();
		_orgDivCmb.refresh();
		_orgDivSrvcCmb.refresh();
		_orgDivSrvcLocCmb.refresh();
		_workPlaceCmb.refresh();
	}
	private void _stylePopUpWin(final Window popUp) {
        popUp.setModal(true);
        popUp.setClosable(true);
        popUp.setDraggable(true);
        popUp.setResizable(false);
        popUp.setWindowMode(WindowMode.NORMAL);
        popUp.center();
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	private <O extends PB01AOrgObjectOID,I extends PB01AOrgObjectID<O>>
		    void _handleComboValueChangeEvent(final PB01ComboValueChangedEvent<O,I> event) {
		_refreshGridUsingComboValues();
	}
	/**
	 * Uses the combo selected values to refresh the grid
	 */
	private void _refreshGridUsingComboValues() {
		_gridView.refresh(_orgCmb.getSelectedOrgObjectRef(),
						  _orgDivCmb.getSelectedOrgObjectRef(),
						  _orgDivSrvcCmb.getSelectedOrgObjectRef(),
						  _orgDivSrvcLocCmb.getSelectedOrgObjectRef(),
						  _workPlaceCmb.getSelectedOrgObjectRef());
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	private <O extends PB01AOrgObjectOID,I extends PB01AOrgObjectID<O>>
			void _showDetailViewForEditingExistingRecord(final PB01AOrgObjectRef<O,I> objRef,
														 final PB01DetailWindowForOrganizationalEntityBase<O,?,?,?,?,?> popUp,
														 final PB01CascadedCombo<?,?> cmbToUpdateAfterSave) {
		
		if (objRef.getOid() == null) return;
		// after save or delete at the detail view the action to be done is the same: refresh the combos/grid & close the detail view		
		popUp.forEditing(objRef.getOid(),
	 		      	  	 // save subscriber: executed after save: refresh the combo
	 		      	  	 UIPresenterSubscriber.from(viewObj -> _onOrgObjectSavedFromGrid(cmbToUpdateAfterSave,
	 				   														   		  	 popUp),
	 		    						     	    this::_onPersistenceError), 	// Error subscriber
	 		      	  	 // delete subscriber: executed after delete
	 		      	  	 UIPresenterSubscriber.from(viewObj -> _onOrgObjectDeletedFromGrid(cmbToUpdateAfterSave,
	 				   															    	   popUp),
			 		    						    this::_onPersistenceError));	// Error subscriber
		UI.getCurrent()
		  .addWindow(popUp);
	}
	private void _onOrgObjectSavedFromGrid(final PB01CascadedCombo<?,?> cmbToUpdate,
										   final Window popUpToClose) {
		PB01VaadinComboItem selectedOrgObj = cmbToUpdate.getSelectedComboItem();

		// if the combo already had a selected item, refresh the combo and keep the selected item
		// ... since the combo refresh & select will fire a combo value change event, there's NO need
		//	   to refresh the grid (the grid will be refreshed on response of the combo value change event)
		if (selectedOrgObj != null) {
			cmbToUpdate.refreshAndSelect(selectedOrgObj);
		}
		// if the combo DID NOT have a selected item, refreshing the combo will NOT fire
		// a combo value change event so the grid will NOT be updated
		// ... the grid has to be refreshed manually
		else {
			cmbToUpdate.refresh();
			_refreshGridUsingComboValues();
		}

		// close the popup
		popUpToClose.close();
	}
	private void _onOrgObjectDeletedFromGrid(final PB01CascadedCombo<?,?> cmbToUpdate,
											 final Window popUpToClose) {
		cmbToUpdate.refresh();
		_refreshGridUsingComboValues();
		popUpToClose.close();
	}
	private void _onPersistenceError(final Throwable th) {
		log.error("Error while saving an entity: {}",
		  		  th.getMessage(),th);
	}
}
