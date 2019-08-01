package pb01.ui.vaadin.view;

import javax.inject.Inject;

import com.vaadin.data.HasValue.ValueChangeEvent;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import lombok.extern.slf4j.Slf4j;
import pb01.ui.vaadin.orgentity.organization.PB01DetailWindowForOrganization;
import pb01.ui.vaadin.orgentity.organization.PB01ViewObjForOrganization;
import pb01.ui.vaadin.orgentity.orgdivision.PB01DetailWindowForOrgDivision;
import pb01.ui.vaadin.orgentity.orgdivision.PB01ViewObjForOrgDivision;
import pb01.ui.vaadin.orgentity.orgdivisionservice.PB01DetailWindowForOrgDivisionService;
import pb01.ui.vaadin.orgentity.orgdivisionservice.PB01ViewObjForOrgDivisionService;
import pb01.ui.vaadin.orgentity.orgdivisionservicelocation.PB01DetailWindowForOrgDivisionServiceLocation;
import pb01.ui.vaadin.orgentity.orgdivisionservicelocation.PB01ViewObjForOrgDivisionServiceLocation;
import pb01.ui.vaadin.orgentity.workplace.PB01DetailWindowForWorkPlace;
import pb01.ui.vaadin.orgentity.workplace.PB01ViewObjForWorkPlace;
import pb01.ui.vaadin.view.components.PB01VaadinComboItem;
import r01f.ui.i18n.UII18NService;
import r01f.ui.presenter.UIPresenterSubscriber;
import r01f.util.types.collections.Lists;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionID;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionServiceID;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionServiceLocationID;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrganizationID;
import x47b.model.oids.X47BOrganizationalIDs.X47BWorkPlaceID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceLocationOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrganizationOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BWorkPlaceOID;
import x47b.model.org.X47BOrganizationalObjectRef;

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
	private final ComboBox<PB01VaadinComboItem> _orgsCmb;
	private final Button _orgCreateButton;
	private final Button _orgEditButton;

	// Org divisions
	private final PB01DetailWindowForOrgDivision _orgDivDetailPopUp;
	private final ComboBox<PB01VaadinComboItem> _orgDivsCmb;
	private final Button _orgDivCreateButton;
	private final Button _orgDivEditButton;

	// Org division services
	private final PB01DetailWindowForOrgDivisionService _orgDivSrvcDetailPopUp;
	private final ComboBox<PB01VaadinComboItem> _orgDivSrvcsCmb;
	private final Button _orgDivSrvcCreateButton;
	private final Button _orgDivSrvcEditButton;

	// Org division services location
	private final PB01DetailWindowForOrgDivisionServiceLocation _orgDivSrvcLocDetailPopUp;
	private final ComboBox<PB01VaadinComboItem> _orgDivSrvcLocsCmb;
	private final Button _orgDivSrvcLocCreateButton;
	private final Button _orgDivSrvcLocEditButton;

	// WorkPlaces
	private final PB01DetailWindowForWorkPlace _workPlaceDetailPopUp;
	private final ComboBox<PB01VaadinComboItem> _workPlacesCmb;
	private final Button _workPlaceCreateButton;
	private final Button _workPlaceEditButton;

	// The combo selected organizational entities
	private X47BOrganizationalObjectRef<X47BOrganizationOID,X47BOrganizationID> _cmbSelectedOrgRef;
	private X47BOrganizationalObjectRef<X47BOrgDivisionOID,X47BOrgDivisionID> _cmbSelectedOrgDivRef;
	private X47BOrganizationalObjectRef<X47BOrgDivisionServiceOID,X47BOrgDivisionServiceID> _cmbSelectedOrgDivSrvcRef;
	private X47BOrganizationalObjectRef<X47BOrgDivisionServiceLocationOID,X47BOrgDivisionServiceLocationID> _cmbSelectedOrgDivSrvcLocRef;
	private X47BOrganizationalObjectRef<X47BWorkPlaceOID,X47BWorkPlaceID> _cmbSelectedWorkPlaceRef;

	// The pop-up window being edited organizational entities

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

		///////// Organization
		_orgDetailPopUp = orgDetailPopUpWin;

		_orgsCmb = new ComboBox<>(_i18n.getMessage("pb01.view.main.combo.orgs"));
		_orgsCmb.setItemCaptionGenerator(PB01VaadinComboItem::getValue);
		_orgsCmb.addValueChangeListener(this::_onOrgsComboValueChanged);

		_orgCreateButton = new Button(VaadinIcons.PLUS);
		_orgCreateButton.addClickListener(event -> _showOrganizationDetailViewForCreatingNewRecord());	// show the org detail dialog

		_orgEditButton = new Button(VaadinIcons.EDIT);
		_orgCreateButton.setEnabled(true);
		_orgEditButton.setEnabled(false);
		_orgEditButton.addClickListener(event ->_showOrganizationDetailViewForEditingExistingRecord());	// show the org detail dialog

		final HorizontalLayout orgLayout = new HorizontalLayout(_orgsCmb,
											   			  _orgCreateButton,_orgEditButton);
		orgLayout.setComponentAlignment(_orgCreateButton,Alignment.BOTTOM_LEFT);
		orgLayout.setComponentAlignment(_orgEditButton,Alignment.BOTTOM_LEFT);
		this.addComponent(orgLayout);

		///////// Org divisions
		_orgDivDetailPopUp = orgDivDetailPopUpWin;

		_orgDivsCmb = new ComboBox<>(_i18n.getMessage("pb01.view.main.combo.org.divs"));
		_orgDivsCmb.setItemCaptionGenerator(PB01VaadinComboItem::getValue);
		_orgDivsCmb.addValueChangeListener(this::_onOrgDivsComboValueChanged);

		_orgDivCreateButton = new Button(VaadinIcons.PLUS);
		_orgDivCreateButton.setEnabled(false);
		_orgDivCreateButton.addClickListener(event -> _showOrgDivDetailViewForCreatingNewRecord());	// show the item detail dialog

		_orgDivEditButton = new Button(VaadinIcons.EDIT);
		_orgDivEditButton.setEnabled(false);
		_orgDivEditButton.addClickListener(event -> _showOrgDivDetailViewForEditingExistingRecord());	// show the item detail dialog

		final HorizontalLayout orgDivLayout = new HorizontalLayout(_orgDivsCmb,
											   				 _orgDivCreateButton,_orgDivEditButton);
		orgDivLayout.setComponentAlignment(_orgDivCreateButton,Alignment.BOTTOM_LEFT);
		orgDivLayout.setComponentAlignment(_orgDivEditButton,Alignment.BOTTOM_LEFT);
		this.addComponent(orgDivLayout);

		///////// Org division services
		_orgDivSrvcDetailPopUp = orgDivSrvcDetailPopUpWin;

		_orgDivSrvcsCmb = new ComboBox<>(_i18n.getMessage("pb01.view.main.combo.org.div.srvcs"));
		_orgDivSrvcsCmb.setItemCaptionGenerator(PB01VaadinComboItem::getValue);
		_orgDivSrvcsCmb.addValueChangeListener(this::_onOrgDivSrvcsComboValueChanged);

		_orgDivSrvcCreateButton = new Button(VaadinIcons.PLUS);
		_orgDivSrvcCreateButton.setEnabled(false);
		_orgDivSrvcCreateButton.addClickListener(event -> _showOrgDivSrvcDetailViewForCreatingNewRecord());		// show the item detail dialog

		_orgDivSrvcEditButton = new Button(VaadinIcons.EDIT);
		_orgDivSrvcEditButton.setEnabled(false);
		_orgDivSrvcEditButton.addClickListener(event -> _showOrgDivSrvcDetailViewForEditingExistingRecord());	// show the item detail dialog

		final HorizontalLayout orgDivSrvcLayout = new HorizontalLayout(_orgDivSrvcsCmb,
											   					 _orgDivSrvcCreateButton,_orgDivSrvcEditButton);
		orgDivSrvcLayout.setComponentAlignment(_orgDivSrvcCreateButton,Alignment.BOTTOM_LEFT);
		orgDivSrvcLayout.setComponentAlignment(_orgDivSrvcEditButton,Alignment.BOTTOM_LEFT);
		this.addComponent(orgDivSrvcLayout);

		///////// Org division service locations
		_orgDivSrvcLocDetailPopUp = orgDivSrvcLocDetailPopUpWin;

		_orgDivSrvcLocsCmb = new ComboBox<>(_i18n.getMessage("pb01.view.main.combo.org.div.srvc.loc"));
		_orgDivSrvcLocsCmb.setItemCaptionGenerator(PB01VaadinComboItem::getValue);
		_orgDivSrvcLocsCmb.addValueChangeListener(this::_onOrgDivSrvcLocsComboValueChanged);

		_orgDivSrvcLocCreateButton = new Button(VaadinIcons.PLUS);
		_orgDivSrvcLocCreateButton.setEnabled(false);
		_orgDivSrvcLocCreateButton.addClickListener(event -> _showOrgDivSrvcLocDetailViewForCreatingNewRecord());		// show the item detail dialog

		_orgDivSrvcLocEditButton = new Button(VaadinIcons.EDIT);
		_orgDivSrvcLocEditButton.setEnabled(false);
		_orgDivSrvcLocEditButton.addClickListener(event -> _showOrgDivSrvcLocDetailViewForEditingExistingRecord());	// show the item detail dialog

		final HorizontalLayout orgDivSrvcLocLayout = new HorizontalLayout(_orgDivSrvcLocsCmb,
											   						_orgDivSrvcLocCreateButton,_orgDivSrvcLocEditButton);
		orgDivSrvcLocLayout.setComponentAlignment(_orgDivSrvcLocCreateButton,Alignment.BOTTOM_LEFT);
		orgDivSrvcLocLayout.setComponentAlignment(_orgDivSrvcLocEditButton,Alignment.BOTTOM_LEFT);
		this.addComponent(orgDivSrvcLocLayout);

		///////// WorkPlaces
		_workPlaceDetailPopUp = workPlaceDetailPopUpWin;

		_workPlacesCmb = new ComboBox<>(_i18n.getMessage("pb01.view.main.combo.org.div.srvc.loc"));
		_workPlacesCmb.setItemCaptionGenerator(PB01VaadinComboItem::getValue);
		_workPlacesCmb.addValueChangeListener(this::_onWorkPlacesComboValueChanged);

		_workPlaceCreateButton = new Button(VaadinIcons.PLUS);
		_workPlaceCreateButton.setEnabled(false);
		_workPlaceCreateButton.addClickListener(event -> _showWorkPlaceDetailViewForCreatingNewRecord());		// show the item detail dialog

		_workPlaceEditButton = new Button(VaadinIcons.EDIT);
		_workPlaceEditButton.setEnabled(false);
		_workPlaceEditButton.addClickListener(event -> _showWorkPlaceDetailViewForEditingExistingRecord());	// show the item detail dialog

		final HorizontalLayout workPlaceLayout = new HorizontalLayout(_workPlacesCmb,
											   						  _workPlaceCreateButton,_workPlaceEditButton);
		workPlaceLayout.setComponentAlignment(_workPlaceCreateButton,Alignment.BOTTOM_LEFT);
		workPlaceLayout.setComponentAlignment(_workPlaceEditButton,Alignment.BOTTOM_LEFT);
		this.addComponent(workPlaceLayout);

		// Grid
		_gridView = new PB01MainGridView(i18n,
										 presenter,
										 // what happens when an org entity is clicked on the grid
										 orgClickedEvent -> _showOrganizationDetailViewForEditingExistingRecord(orgClickedEvent.getObjRef()),
										 orgDivClickedEvent -> _showOrgDivDetailViewForEditingExistingRecord(orgDivClickedEvent.getObjRef()),
										 orgDivSrvcClickedEvent -> _showOrgDivSrvcDetailViewForEditingExistingRecord(orgDivSrvcClickedEvent.getObjRef()),
										 orgDivSrvcLocClickedEvent -> _showOrgDivSrvcLocDetailViewForEditingExistingRecord(orgDivSrvcLocClickedEvent.getObjRef()),
										 workPlaceClickedEvent -> _showWorkPlaceDetailViewForEditingExistingRecord(workPlaceClickedEvent.getObjRef()));
		this.addComponent(_gridView);

		// Refresh
		_refreshOrganizationsCombo();
		_refreshOrgDivsCombo(null);
		_refreshOrgDivSrvcsCombo(null);
		_refreshOrgDivSrvcLocsCombo(null);
		_refreshWorkPlacesCombo(null);

		_gridView.setVisible(false);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Uses the combo selected values to refresh the grid
	 */
	private void _refreshGridUsingComboValues() {
		_gridView.refresh(_cmbSelectedOrgRef,
						  _cmbSelectedOrgDivRef,
						  _cmbSelectedOrgDivSrvcRef,
						  _cmbSelectedOrgDivSrvcLocRef,
						  _cmbSelectedWorkPlaceRef);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	ORGANIZATIONS
/////////////////////////////////////////////////////////////////////////////////////////
	private void _onOrgsComboValueChanged(final ValueChangeEvent<PB01VaadinComboItem> event) {
		// store the selected org ref
		_cmbSelectedOrgRef = _orgsCmb.getValue() != null ? _orgsCmb.getValue()
												     		   .getOrgEntityRefUsing(X47BOrganizationOID::forId,
												     						         X47BOrganizationID::forId)
												     : null;
		_cmbSelectedOrgDivRef = null;
		_cmbSelectedOrgDivSrvcRef = null;
		_cmbSelectedOrgDivSrvcLocRef = null;
		_cmbSelectedWorkPlaceRef = null;

		_orgEditButton.setEnabled(_cmbSelectedOrgRef != null);		// can edit orgs
		_orgDivCreateButton.setEnabled(_cmbSelectedOrgRef != null);// can create divs

		// refresh the underlying combos
		_refreshOrgDivsCombo(_cmbSelectedOrgRef != null ? _cmbSelectedOrgRef.getOid() : null);
		_refreshOrgDivSrvcsCombo(null);
		_refreshOrgDivSrvcLocsCombo(null);
		_refreshWorkPlacesCombo(null);

		// refresh the grid
		_refreshGridUsingComboValues();
	}
	private void _refreshOrganizationsCombo() {
		_cmbSelectedOrgRef = null;
		_orgsCmb.setValue(null);

		log.info("...refresh organizations combo");
		_presenter.onOrgsComboDataNeeded(_i18n.getCurrentLanguage(),
										 orgs -> {	// when loaded the combo data, just refresh the combo
											 		final ListDataProvider<PB01VaadinComboItem> dataProvider = DataProvider.ofCollection(orgs);
											 		_orgsCmb.setDataProvider(dataProvider);
										 		 });
	}
	private void _onOrganizationSaved(final PB01ViewObjForOrganization viewObj) {
		// refresh the org divisions combo
		_refreshOrganizationsCombo();

		// select the saved record
		final PB01VaadinComboItem cmbItem = PB01VaadinComboItem.FROM_VIEW_OBJ.apply(viewObj,
																			  		_i18n.getCurrentLanguage());
		_orgsCmb.setValue(cmbItem);

		// close the popup
		_orgDetailPopUp.close();
	}
	private void _onOrganizationDeleted(final PB01ViewObjForOrganization viewObj) {
		// refresh the combos
		_refreshOrganizationsCombo();
		_refreshOrgDivsCombo(null);
		_refreshOrgDivSrvcsCombo(null);
		_refreshOrgDivSrvcLocsCombo(null);
		_refreshWorkPlacesCombo(null);

		// close the popup
		_orgDetailPopUp.close();
	}
	private void _showOrganizationDetailViewForCreatingNewRecord() {
		// after save at the detail view the action to be done is the same: refresh the list & close the detail view
		_orgDetailPopUp.forCreating(// save subscriber: executed after save: refresh the combo
						 		    UIPresenterSubscriber.from(PB01MainView.this::_onOrganizationSaved,		// OK subscriber
						 		    						   PB01MainView.this::_onPersistenceError)); 	// Error subscriber
		UI.getCurrent()
		  .addWindow(_orgDetailPopUp);
	}
	private void _showOrganizationDetailViewForEditingExistingRecord() {
		_showOrganizationDetailViewForEditingExistingRecord(_cmbSelectedOrgRef);
	}
	private void _showOrganizationDetailViewForEditingExistingRecord(final X47BOrganizationalObjectRef<X47BOrganizationOID,X47BOrganizationID> orgRef) {
		if (orgRef == null) throw new IllegalStateException("NO organization to be edited!");
		// after save or delete at the detail view the action to be done is the same: refresh the list & close the detail view
		_orgDetailPopUp.forEditing(orgRef.getOid(),
						 		   // save subscriber: executed after save: refresh the combo
						 		   UIPresenterSubscriber.from(PB01MainView.this::_onOrganizationSaved,	// OK subscriber
						 		    						  PB01MainView.this::_onPersistenceError), 	// Error subscriber
						 		   // delete subscriber: executed after delete
						 		   UIPresenterSubscriber.from(PB01MainView.this::_onOrganizationDeleted,// OK subscriber
						 		    						  PB01MainView.this::_onPersistenceError));	// Error subscriber
		UI.getCurrent()
		  .addWindow(_orgDetailPopUp);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	ORG DIVISIONS
/////////////////////////////////////////////////////////////////////////////////////////
	private void _onOrgDivsComboValueChanged(final ValueChangeEvent<PB01VaadinComboItem> event) {
		// store the selected object ref
		_cmbSelectedOrgDivRef = _orgDivsCmb.getValue() != null ? _orgDivsCmb.getValue()
													     			    .getOrgEntityRefUsing(X47BOrgDivisionOID::forId,
													     					   		          X47BOrgDivisionID::forId)
													       : null;
		_cmbSelectedOrgDivSrvcRef = null;
		_cmbSelectedOrgDivSrvcLocRef = null;
		_cmbSelectedWorkPlaceRef = null;

		_orgDivEditButton.setEnabled(_cmbSelectedOrgDivRef != null);		// can edit divs
		_orgDivSrvcCreateButton.setEnabled(_cmbSelectedOrgDivRef != null);	// can create srvcs

		 // refresh the underlying combos
		_refreshOrgDivSrvcsCombo(_cmbSelectedOrgDivRef != null ? _cmbSelectedOrgDivRef.getOid() : null);
		_refreshOrgDivSrvcLocsCombo(null);
		_refreshWorkPlacesCombo(null);

		// refresh the grid
		_refreshGridUsingComboValues();
	}
	private void _refreshOrgDivsCombo(final X47BOrganizationOID orgOid) {
		_cmbSelectedOrgDivRef = null;
		_orgDivsCmb.setValue(null);

		if (orgOid == null) {
			_orgDivsCmb.setDataProvider(DataProvider.ofCollection(Lists.newArrayList()));
		} else {
			log.info("...refresh org divisions combo for org oid={}",orgOid);
			_presenter.onOrgDivisionsComboDataNeeded(orgOid,
													 _i18n.getCurrentLanguage(),
											 		 divs -> {	// when loaded the combo data, just refresh the combo
														 		final ListDataProvider<PB01VaadinComboItem> dataProvider = DataProvider.ofCollection(divs);
														 		_orgDivsCmb.setDataProvider(dataProvider);
											 		 		 });
		}
	}
	private void _onOrgDivSaved(final PB01ViewObjForOrgDivision viewObj) {
		// refresh the combo
		if (_cmbSelectedOrgRef != null) {
			_refreshOrgDivsCombo(viewObj.getWrappedModelObject()
										.getOrgRef().getOid());		// parent object

			// select the saved object
			final PB01VaadinComboItem cmbItem = PB01VaadinComboItem.FROM_VIEW_OBJ.apply(viewObj,
																			 	  _i18n.getCurrentLanguage());
			_orgDivsCmb.setValue(cmbItem);
		}
		// Refresh the grid
		_refreshGridUsingComboValues();

		// close the popup window
		_orgDivDetailPopUp.close();
	}
	private void _onOrgDivDeleted(final PB01ViewObjForOrgDivision viewObj) {
		// refresh the combo
		if (_cmbSelectedOrgRef != null) {
			_refreshOrgDivsCombo(viewObj.getWrappedModelObject()
										.getOrgRef().getOid());		// parent object
			_refreshOrgDivSrvcsCombo(null);
			_refreshOrgDivSrvcLocsCombo(null);
			_refreshWorkPlacesCombo(null);
		}
		// refresh the grid
		_refreshGridUsingComboValues();

		// close the popup
		_orgDivDetailPopUp.close();
	}
	private void _showOrgDivDetailViewForCreatingNewRecord() {
		// after save or delete at the detail view the action to be done is the same: refresh the list & close the detail view
		if (_cmbSelectedOrgRef == null) throw new IllegalStateException("NO organization selected!");
		_orgDivDetailPopUp.forCreating(_cmbSelectedOrgRef,
							 		   // save subscriber: executed after save: refresh the organizations combo
							 		   UIPresenterSubscriber.from(PB01MainView.this::_onOrgDivSaved,		// OK subscriber
						 		    						      PB01MainView.this::_onPersistenceError)); // Error subscriber,
		UI.getCurrent()
		  .addWindow(_orgDivDetailPopUp);
	}
	private void _showOrgDivDetailViewForEditingExistingRecord() {
		_showOrgDivDetailViewForEditingExistingRecord(_cmbSelectedOrgDivRef);
	}
	private void _showOrgDivDetailViewForEditingExistingRecord(final X47BOrganizationalObjectRef<X47BOrgDivisionOID,X47BOrgDivisionID> orgDivRef) {
		if (orgDivRef == null) throw new IllegalStateException("NO org division to be edited!");
		// after save or delete at the detail view the action to be done is the same: refresh the list & close the detail view
		_orgDivDetailPopUp.forEditing(orgDivRef.getOid(),
							 		  // save subscriber: executed after save: refresh the combo
							 		  UIPresenterSubscriber.from(PB01MainView.this::_onOrgDivSaved,			// OK subscriber
						 		    						     PB01MainView.this::_onPersistenceError), 	// Error subscriber,
							 		  // delete subscriber: executed after delete
							 		  UIPresenterSubscriber.from(PB01MainView.this::_onOrgDivDeleted,		// OK subscriber
						 		    						     PB01MainView.this::_onPersistenceError)); 	// Error subscriber,
		UI.getCurrent()
		  .addWindow(_orgDivDetailPopUp);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	ORG DIVISION SERVICES
/////////////////////////////////////////////////////////////////////////////////////////
	private void _onOrgDivSrvcsComboValueChanged(final ValueChangeEvent<PB01VaadinComboItem> event) {
		// store the selected object
		_cmbSelectedOrgDivSrvcRef = _orgDivSrvcsCmb.getValue() != null ? _orgDivSrvcsCmb.getValue()
															     			    .getOrgEntityRefUsing(X47BOrgDivisionServiceOID::forId,
															     					   		          X47BOrgDivisionServiceID::forId)
														     	    : null;
		_cmbSelectedOrgDivSrvcLocRef = null;
		_cmbSelectedWorkPlaceRef = null;

		_orgDivSrvcEditButton.setEnabled(_cmbSelectedOrgDivSrvcRef != null);		// can edit srvc
		_orgDivSrvcLocCreateButton.setEnabled(_cmbSelectedOrgDivSrvcRef != null);	// can create locs

		// refresh the underlying combos
		_refreshOrgDivSrvcLocsCombo(_cmbSelectedOrgDivSrvcRef != null ? _cmbSelectedOrgDivSrvcRef.getOid() : null);
		_refreshWorkPlacesCombo(null);

		// refresh the grid
		_refreshGridUsingComboValues();
	}
	private void _refreshOrgDivSrvcsCombo(final X47BOrgDivisionOID orgDivOid) {
		_cmbSelectedOrgDivSrvcRef = null;
		_orgDivSrvcsCmb.setValue(null);

		if (orgDivOid == null) {
			_orgDivSrvcsCmb.setDataProvider(DataProvider.ofCollection(Lists.newArrayList()));
		} else {
			log.info("...refresh org division services combo for division oid={}",orgDivOid);
			_presenter.onOrgDivisionServicesComboDataNeeded(orgDivOid,
													 		_i18n.getCurrentLanguage(),
													 		srvcs -> {	// when loaded the combo data, just refresh the combo
																 		final ListDataProvider<PB01VaadinComboItem> dataProvider = DataProvider.ofCollection(srvcs);
																 		_orgDivSrvcsCmb.setDataProvider(dataProvider);
													 		 		 });
		}
	}
	private void _onOrgDivSrvcSaved(final PB01ViewObjForOrgDivisionService viewObj) {
		// refresh the combo
		if (_cmbSelectedOrgDivRef != null) {
			_refreshOrgDivSrvcsCombo(viewObj.getWrappedModelObject()
											.getOrgDivisionRef().getOid());		// parent object

			// select the saved object
			final PB01VaadinComboItem cmbItem = PB01VaadinComboItem.FROM_VIEW_OBJ.apply(viewObj,
																			 	  		_i18n.getCurrentLanguage());
			_orgDivSrvcsCmb.setValue(cmbItem);
		}
		// refresh the grid using combo values
		_refreshGridUsingComboValues();

		// close the popup window
		_orgDivSrvcDetailPopUp.close();
	}
	private void _onOrgDivSrvcDeleted(final PB01ViewObjForOrgDivisionService viewObj) {
		// refresh the combo
		if (_cmbSelectedOrgDivRef != null) {
			_refreshOrgDivSrvcsCombo(viewObj.getWrappedModelObject()
											.getOrgDivisionRef().getOid());		// parent object
			_refreshOrgDivSrvcLocsCombo(null);
			_refreshOrgDivSrvcsCombo(null);
		}
		// refresh the grid using combo values
		_refreshGridUsingComboValues();

		// close the popup
		_orgDivSrvcDetailPopUp.close();
	}
	private void _showOrgDivSrvcDetailViewForCreatingNewRecord() {
		// after save or delete at the detail view the action to be done is the same: refresh the list & close the detail view
		if (_cmbSelectedOrgRef == null) throw new IllegalStateException("NO org selected!");
		if (_cmbSelectedOrgDivRef == null) throw new IllegalStateException("NO org division selected!");
		_orgDivSrvcDetailPopUp.forCreating(_cmbSelectedOrgRef,_cmbSelectedOrgDivRef,
							 		   	   // save subscriber: executed after save: refresh the combo
							 		   	   UIPresenterSubscriber.from(PB01MainView.this::_onOrgDivSrvcSaved,		// OK subscriber
						 		    						          PB01MainView.this::_onPersistenceError)); 	// Error subscriber
		UI.getCurrent()
		  .addWindow(_orgDivSrvcDetailPopUp);
	}
	private void _showOrgDivSrvcDetailViewForEditingExistingRecord() {
		_showOrgDivSrvcDetailViewForEditingExistingRecord(_cmbSelectedOrgDivSrvcRef);
	}
	private void _showOrgDivSrvcDetailViewForEditingExistingRecord(final X47BOrganizationalObjectRef<X47BOrgDivisionServiceOID,X47BOrgDivisionServiceID> orgDivSrvcRef) {
		if (orgDivSrvcRef == null) throw new IllegalStateException("NO org division service to be edited!");
		// after save or delete at the detail view the action to be done is the same: refresh the list & close the detail view
		_orgDivSrvcDetailPopUp.forEditing(orgDivSrvcRef.getOid(),
							 		  	  // save subscriber: executed after save: refresh the combo
								 		  UIPresenterSubscriber.from(PB01MainView.this::_onOrgDivSrvcSaved,		// OK subscriber
							 		    						     PB01MainView.this::_onPersistenceError),// Error subscriber,
								 		  // delete subscriber: executed after delete
								 		  UIPresenterSubscriber.from(PB01MainView.this::_onOrgDivSrvcDeleted,	// OK subscriber
						 		    						         PB01MainView.this::_onPersistenceError)); 	// Error subscriber
		UI.getCurrent()
		  .addWindow(_orgDivSrvcDetailPopUp);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	ORG DIVISION SERVICE LOCATIONS
/////////////////////////////////////////////////////////////////////////////////////////
	private void _onOrgDivSrvcLocsComboValueChanged(final ValueChangeEvent<PB01VaadinComboItem> event) {
		// store the object ref
		_cmbSelectedOrgDivSrvcLocRef = _orgDivSrvcLocsCmb.getValue() != null ? _orgDivSrvcLocsCmb.getValue()
															     			    .getOrgEntityRefUsing(X47BOrgDivisionServiceLocationOID::forId,
															     					   		          X47BOrgDivisionServiceLocationID::forId)
															     		  : null;
		_cmbSelectedWorkPlaceRef = null;

		_orgDivSrvcLocEditButton.setEnabled(_cmbSelectedOrgDivSrvcLocRef != null);	// can edit locs
		_workPlaceCreateButton.setEnabled(_cmbSelectedOrgDivSrvcLocRef != null);	// can create workplaces

		// refresh the underlying combos
		_refreshWorkPlacesCombo(_cmbSelectedOrgDivSrvcLocRef != null ? _cmbSelectedOrgDivSrvcLocRef.getOid() : null);

		// refresh the grid
		_refreshGridUsingComboValues();
	}
	private void _refreshOrgDivSrvcLocsCombo(final X47BOrgDivisionServiceOID orgDivSrvcOid) {
		_cmbSelectedOrgDivSrvcLocRef = null;
		_orgDivSrvcLocsCmb.setValue(null);

		if (orgDivSrvcOid == null) {
			_orgDivSrvcLocsCmb.setDataProvider(DataProvider.ofCollection(Lists.newArrayList()));
		} else {
			log.info("...refresh org division service locations combo for service oid={}",orgDivSrvcOid);
			_presenter.onOrgDivisionServiceLocationsComboDataNeeded(orgDivSrvcOid,
													 				_i18n.getCurrentLanguage(),
															 		locs -> {	// when loaded the combo data, just refresh the combo
																		 		final ListDataProvider<PB01VaadinComboItem> dataProvider = DataProvider.ofCollection(locs);
																		 		_orgDivSrvcLocsCmb.setDataProvider(dataProvider);
															 		 		 });
		}
	}
	private void _onOrgDivSrvcLocSaved(final PB01ViewObjForOrgDivisionServiceLocation viewObj) {
		// refresh the combo
		if (_cmbSelectedOrgDivSrvcRef != null) {
			_refreshOrgDivSrvcLocsCombo(viewObj.getWrappedModelObject()
											   .getOrgDivisionServiceRef().getOid());	// parent object

			// select the saved object
			final PB01VaadinComboItem cmbItem = PB01VaadinComboItem.FROM_VIEW_OBJ.apply(viewObj,
																			 	  		_i18n.getCurrentLanguage());
			_orgDivSrvcLocsCmb.setValue(cmbItem);
		}
		// refresh the grid
		_refreshGridUsingComboValues();

		// close the popup window
		_orgDivSrvcLocDetailPopUp.close();
	}
	private void _onOrgDivSrvcLocDeleted(final PB01ViewObjForOrgDivisionServiceLocation viewObj) {
		// refresh the combo
		if (_cmbSelectedOrgDivSrvcRef != null) {
			_refreshOrgDivSrvcLocsCombo(viewObj.getWrappedModelObject()
											   .getOrgDivisionServiceRef().getOid());	// parent object
			_refreshOrgDivSrvcsCombo(null);
		}
		// refresh the grid
		_refreshGridUsingComboValues();

		// close the popup
		_orgDivSrvcLocDetailPopUp.close();
	}
	private void _showOrgDivSrvcLocDetailViewForCreatingNewRecord() {
		// after save or delete at the detail view the action to be done is the same: refresh the list & close the detail view
		if (_cmbSelectedOrgRef == null) throw new IllegalStateException("NO org selected!");
		if (_cmbSelectedOrgDivRef == null) throw new IllegalStateException("NO org division selected!");
		if (_cmbSelectedOrgDivSrvcRef == null) throw new IllegalStateException("NO org division service selected!");
		_orgDivSrvcLocDetailPopUp.forCreating(_cmbSelectedOrgRef,_cmbSelectedOrgDivRef,_cmbSelectedOrgDivSrvcRef,
								 		   	  // save subscriber: executed after save: refresh the combo
								 		   	  UIPresenterSubscriber.from(PB01MainView.this::_onOrgDivSrvcLocSaved,		// OK subscriber
							 		    						         PB01MainView.this::_onPersistenceError));		// Error subscriber
		UI.getCurrent()
		  .addWindow(_orgDivSrvcLocDetailPopUp);
	}
	private void _showOrgDivSrvcLocDetailViewForEditingExistingRecord() {
		_showOrgDivSrvcLocDetailViewForEditingExistingRecord(_cmbSelectedOrgDivSrvcLocRef);
	}
	private void _showOrgDivSrvcLocDetailViewForEditingExistingRecord(final X47BOrganizationalObjectRef<X47BOrgDivisionServiceLocationOID,X47BOrgDivisionServiceLocationID> orgDivSrvcLocRef) {
		if (orgDivSrvcLocRef == null) throw new IllegalStateException("NO org division service location to be edited!");
		// after save or delete at the detail view the action to be done is the same: refresh the list & close the detail view
		_orgDivSrvcLocDetailPopUp.forEditing(orgDivSrvcLocRef.getOid(),
							 		  	  	 // save subscriber: executed after save: refresh the combo
								 		  	 UIPresenterSubscriber.from(PB01MainView.this::_onOrgDivSrvcLocSaved,		// OK subscriber
							 		    						        PB01MainView.this::_onPersistenceError),		// Error subscriber,
								 		  	 // delete subscriber: executed after delete
								 		  	 UIPresenterSubscriber.from(PB01MainView.this::_onOrgDivSrvcLocDeleted,		// OK subscriber
							 		    						        PB01MainView.this::_onPersistenceError));		// Error subscriber
		UI.getCurrent()
		  .addWindow(_orgDivSrvcLocDetailPopUp);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	WORKPLACES
/////////////////////////////////////////////////////////////////////////////////////////
	private void _onWorkPlacesComboValueChanged(final ValueChangeEvent<PB01VaadinComboItem> event) {
		// store the selected object ref
		_cmbSelectedWorkPlaceRef = _workPlacesCmb.getValue() != null ? _workPlacesCmb.getValue()
															     			    .getOrgEntityRefUsing(X47BWorkPlaceOID::forId,
															     					   		          X47BWorkPlaceID::forId)
															     		  : null;
		// set edit button status
		_workPlaceEditButton.setEnabled(_cmbSelectedWorkPlaceRef != null);

		// refresh the grid
		_refreshGridUsingComboValues();
	}
	private void _refreshWorkPlacesCombo(final X47BOrgDivisionServiceLocationOID locOid) {
		_cmbSelectedWorkPlaceRef = null;
		_workPlacesCmb.setValue(null);

		if (locOid == null) {
			_workPlacesCmb.setDataProvider(DataProvider.ofCollection(Lists.newArrayList()));
		} else {
			log.info("...refresh workplaces combo for service location oid={}",locOid);
			_presenter.onWorkPlacesComboDataNeeded(locOid,
												   _i18n.getCurrentLanguage(),
											 	   wpcs -> {	// when loaded the combo data, just refresh the combo
														 		final ListDataProvider<PB01VaadinComboItem> dataProvider = DataProvider.ofCollection(wpcs);
														 		_workPlacesCmb.setDataProvider(dataProvider);
											 		 		});
		}
	}
	private void _onWorkPlaceSaved(final PB01ViewObjForWorkPlace viewObj) {
		// refresh the combo
		if (_cmbSelectedOrgDivSrvcLocRef != null) {
			_refreshWorkPlacesCombo(viewObj.getWrappedModelObject()
										   .getOrgDivisionServiceLocationRef().getOid());	// parent object

			// select the saved org division service
			final PB01VaadinComboItem cmbItem = PB01VaadinComboItem.FROM_VIEW_OBJ.apply(viewObj,
																			 	  		_i18n.getCurrentLanguage());
			_workPlacesCmb.setValue(cmbItem);
		}
		// refresh the grid
		_refreshGridUsingComboValues();

		// close the popup window
		_workPlaceDetailPopUp.close();
	}
	private void _onWorkPlaceDeleted(final PB01ViewObjForWorkPlace viewObj) {
		// refresh the combo
		if (_cmbSelectedOrgDivSrvcLocRef != null) {
			_refreshWorkPlacesCombo(viewObj.getWrappedModelObject()
										   .getOrgDivisionServiceLocationRef().getOid());	// parent object
		}
		// refresh the grid
		_refreshGridUsingComboValues();

		// close the popup
		_workPlaceDetailPopUp.close();
	}
	private void _showWorkPlaceDetailViewForCreatingNewRecord() {
		// after save or delete at the detail view the action to be done is the same: refresh the list & close the detail view
		if (_cmbSelectedOrgRef == null) throw new IllegalStateException("NO org selected!");
		if (_cmbSelectedOrgDivRef == null) throw new IllegalStateException("NO org division selected!");
		if (_cmbSelectedOrgDivSrvcRef == null) throw new IllegalStateException("NO org division service selected!");
		if (_cmbSelectedOrgDivSrvcLocRef == null) throw new IllegalStateException("NO org division service location selected!");
		_workPlaceDetailPopUp.forCreating(_cmbSelectedOrgRef,_cmbSelectedOrgDivRef,_cmbSelectedOrgDivSrvcRef,_cmbSelectedOrgDivSrvcLocRef,
								 		  // save subscriber: executed after save: refresh the combo
								 		  UIPresenterSubscriber.from(PB01MainView.this::_onWorkPlaceSaved,		// OK subscriber
							 		    						     PB01MainView.this::_onPersistenceError));	// Error subscriber
		UI.getCurrent()
		  .addWindow(_workPlaceDetailPopUp);
	}
	private void _showWorkPlaceDetailViewForEditingExistingRecord() {
		_showWorkPlaceDetailViewForEditingExistingRecord(_cmbSelectedWorkPlaceRef);
	}
	private void _showWorkPlaceDetailViewForEditingExistingRecord(final X47BOrganizationalObjectRef<X47BWorkPlaceOID,X47BWorkPlaceID> workPlaceRef) {
		if (workPlaceRef == null) throw new IllegalStateException("NO workplace to be edited!");
		// after save or delete at the detail view the action to be done is the same: refresh the list & close the detail view
		_workPlaceDetailPopUp.forEditing(workPlaceRef.getOid(),
							 		  	 // save subscriber: executed after save: refresh the combo
								 		 UIPresenterSubscriber.from(PB01MainView.this::_onWorkPlaceSaved,		// OK subscriber
							 		    						    PB01MainView.this::_onPersistenceError),	// Error subscriber,
								 		 // delete subscriber: executed after delete
								 		 UIPresenterSubscriber.from(PB01MainView.this::_onWorkPlaceDeleted,		// OK subscriber
							 		    						    PB01MainView.this::_onPersistenceError));	// Error subscriber
		UI.getCurrent()
		  .addWindow(_workPlaceDetailPopUp);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	private void _onPersistenceError(final Throwable th) {
		log.error("Error while saving an entity: {}",
		  		  th.getMessage(),th);
	}
}
