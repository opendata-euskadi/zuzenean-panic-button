package pb01.ui.vaadin.view;

import javax.inject.Inject;

import com.vaadin.navigator.View;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import lombok.extern.slf4j.Slf4j;
import pb01.ui.vaadin.orgentity.PB01DetailWindowForOrganizationalEntityBase;
import pb01.ui.vaadin.orgentity.organization.PB01DetailWindowForOrganization;
import pb01.ui.vaadin.orgentity.orgdivision.PB01DetailWindowForOrgDivision;
import pb01.ui.vaadin.orgentity.orgdivisionservice.PB01DetailWindowForOrgDivisionService;
import pb01.ui.vaadin.orgentity.orgdivisionservicelocation.PB01DetailWindowForOrgDivisionServiceLocation;
import pb01.ui.vaadin.orgentity.workplace.PB01DetailWindowForWorkPlace;
import pb01.ui.vaadin.view.PB01CascadedComboEvents.PB01ComboValueChangedEvent;
import pb01.ui.vaadin.view.PB01CascadedCombos.PB01CascadedComboForOrgDivision;
import pb01.ui.vaadin.view.PB01CascadedCombos.PB01CascadedComboForOrgDivisionService;
import pb01.ui.vaadin.view.PB01CascadedCombos.PB01CascadedComboForOrgDivisionServiceLocation;
import pb01.ui.vaadin.view.PB01CascadedCombos.PB01CascadedComboForOrganization;
import pb01.ui.vaadin.view.PB01CascadedCombos.PB01CascadedComboForWorkPlace;
import pb01.ui.vaadin.view.components.PB01VaadinComboItem;
import r01f.ui.i18n.UII18NService;
import r01f.ui.presenter.UIPresenterSubscriber;
import x47b.model.oids.X47BIDs.X47BPersistableObjectID;
import x47b.model.oids.X47BOIDs.X47BPersistableObjectOID;
import x47b.model.org.X47BOrgObjectRef;

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
						final PB01MainViewPresenter presenter,
						final PB01DetailWindowForOrganization orgDetailPopUpWin,
						final PB01DetailWindowForOrgDivision orgDivDetailPopUpWin,
						final PB01DetailWindowForOrgDivisionService orgDivSrvcDetailPopUpWin,
						final PB01DetailWindowForOrgDivisionServiceLocation orgDivSrvcLocDetailPopUpWin,
						final PB01DetailWindowForWorkPlace workPlaceDetailPopUpWin) {
		_i18n = i18n;
		_presenter = presenter;

		///////// Store popup windows
		_orgDetailPopUp = orgDetailPopUpWin;
		_orgDivDetailPopUp = orgDivDetailPopUpWin;
		_orgDivSrvcDetailPopUp = orgDivSrvcDetailPopUpWin;
		_orgDivSrvcLocDetailPopUp = orgDivSrvcLocDetailPopUpWin;
		_workPlaceDetailPopUp = workPlaceDetailPopUpWin;

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
										 presenter,
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
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	private <O extends X47BPersistableObjectOID,I extends X47BPersistableObjectID<O>>
		    void _handleComboValueChangeEvent(final PB01ComboValueChangedEvent<O,I> event) {
		_refreshGridUsingComboValues();
	}
	/**
	 * Uses the combo selected values to refresh the grid
	 */
	private void _refreshGridUsingComboValues() {
		_gridView.refresh(_orgCmb.getSelectedOrgEntityRef(),
						  _orgDivCmb.getSelectedOrgEntityRef(),
						  _orgDivSrvcCmb.getSelectedOrgEntityRef(),
						  _orgDivSrvcLocCmb.getSelectedOrgEntityRef(),
						  _workPlaceCmb.getSelectedOrgEntityRef());
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	private <O extends X47BPersistableObjectOID,I extends X47BPersistableObjectID<O>>
			void _showDetailViewForEditingExistingRecord(final X47BOrgObjectRef<O,I> objRef,
														 final PB01DetailWindowForOrganizationalEntityBase<O,?,?,?,?,?> popUp,
														 final PB01CascadedCombo<?,?> cmbToUpdateAfterSave) {
		// after save or delete at the detail view the action to be done is the same: refresh the combos/grid & close the detail view
		popUp.forEditing(objRef.getOid(),
	 		      	  	 // save subscriber: executed after save: refresh the combo
	 		      	  	 UIPresenterSubscriber.from(viewObj -> _onOrgEntitySavedFromGrid(cmbToUpdateAfterSave,
	 				   														   		  	 popUp),
	 		    						     	    this::_onPersistenceError), 	// Error subscriber
	 		      	  	 // delete subscriber: executed after delete
	 		      	  	 UIPresenterSubscriber.from(viewObj -> _onOrgEntityDeletedFromGrid(cmbToUpdateAfterSave,
	 				   															    	   popUp),
			 		    						    this::_onPersistenceError));	// Error subscriber
		UI.getCurrent()
		  .addWindow(popUp);
	}
	private void _onOrgEntitySavedFromGrid(final PB01CascadedCombo<?,?> cmbToUpdate,
										   final Window popUpToClose) {
		PB01VaadinComboItem selectedOrg = cmbToUpdate.getSelectedComboItem();
		// if the combo already had a selected item, refresh the combo and keep the selected item
		// ... since the combo refresh & select will fire a combo value change event, there's NO need
		//	   to refresh the grid (the grid will be refreshed on response of the combo value change event)
		if (selectedOrg != null) {
			cmbToUpdate.refreshAndSelect(selectedOrg);
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
	private void _onOrgEntityDeletedFromGrid(final PB01CascadedCombo<?,?> cmbToUpdate,
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
