package pb01.ui.vaadin.view;

import javax.inject.Inject;

import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import lombok.extern.slf4j.Slf4j;
import pb01.ui.vaadin.orgentity.organization.PB01DetailWindowForOrganization;
import pb01.ui.vaadin.orgentity.orgdivision.PB01DetailWindowForOrgDivision;
import pb01.ui.vaadin.view.components.PB01VaadinComboItem;
import r01f.ui.i18n.UII18NService;
import r01f.ui.presenter.UIPresenterSubscriber;
import r01f.ui.vaadin.view.VaadinViewMultiValueItem;
import r01f.util.types.collections.Lists;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionID;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionServiceID;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionServiceLocationID;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrganizationID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceLocationOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrganizationOID;
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

	// Divisions
	private final PB01DetailWindowForOrgDivision _orgDivDetailPopUp;
	private final ComboBox<PB01VaadinComboItem> _orgDivsCmb;
	private final Button _orgDivCreateButton;
	private final Button _orgDivEditButton;

	private X47BOrganizationalObjectRef<X47BOrganizationOID,X47BOrganizationID> _seletedOrgRef;
	private X47BOrganizationalObjectRef<X47BOrgDivisionOID,X47BOrgDivisionID> _seletedOrgDivRef;
	private X47BOrganizationalObjectRef<X47BOrgDivisionServiceOID,X47BOrgDivisionServiceID> _seletedOrgDivSrvcRef;
	private X47BOrganizationalObjectRef<X47BOrgDivisionServiceLocationOID,X47BOrgDivisionServiceLocationID> _seletedOrgDivSrvcLocRef;
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public PB01MainView(final UII18NService i18n,
						final PB01MainViewPresenter presenter,
						final PB01DetailWindowForOrganization orgDetailPopUpWin,
						final PB01DetailWindowForOrgDivision orgDivDetailPopUpWin) {
		_i18n = i18n;
		_presenter = presenter;

		// Organization
		_orgDetailPopUp = orgDetailPopUpWin;

		_orgsCmb = new ComboBox<>(_i18n.getMessage("pb01.view.main.combo.orgs"));
		_orgsCmb.setItemCaptionGenerator(VaadinViewMultiValueItem::getValue);
		_orgsCmb.addValueChangeListener(ev -> {
													// refresh the divisons
													X47BOrganizationOID selectedOrgOid = _selectedOrgOid();
													_refreshOrgDivisionsCombo(selectedOrgOid);
											  });
		_orgCreateButton = new Button(VaadinIcons.PLUS);
		_orgCreateButton.addClickListener(event -> {
														// show the org detail dialog
														_showOrganizationDetailView(null);
											  	    });
		_orgEditButton = new Button(VaadinIcons.EDIT);
		_orgEditButton.addClickListener(event -> {
														// show the org detail dialog
														X47BOrganizationOID selectedOrgOid = _selectedOrgOid();
														_showOrganizationDetailView(selectedOrgOid);
											  	 });
		this.addComponent(_orgsCmb);
		this.addComponent(_orgCreateButton);
		this.addComponent(_orgEditButton);

		// Org divisions
		_orgDivDetailPopUp = orgDivDetailPopUpWin;

		_orgDivsCmb = new ComboBox<>(_i18n.getMessage("pb01.view.main.combo.org.divs"));
		_orgDivsCmb.setItemCaptionGenerator(VaadinViewMultiValueItem::getValue);
		_orgDivCreateButton = new Button(VaadinIcons.PLUS);
		_orgDivCreateButton.addClickListener(event -> {
														// show the item detail dialog
														_showOrgDivisionDetailView(null);
											  	      });
		_orgDivEditButton = new Button(VaadinIcons.EDIT);
		_orgDivEditButton.addClickListener(event -> {
														// get the selected entity oid
														X47BOrgDivisionOID selectedOrgDivOid = _selectedOrgDivisionOid();
														// show the item detail dialog
														_showOrgDivisionDetailView(selectedOrgDivOid);
											  	 	});
		this.addComponent(_orgDivsCmb);
		this.addComponent(_orgDivCreateButton);
		this.addComponent(_orgDivEditButton);

		// Refresh
		_refreshOrganizationsCombo();
		_refreshOrgDivisionsCombo(null);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	private X47BOrganizationOID _selectedOrgOid() {
		return _orgDivsCmb.getValue() != null ? _orgsCmb.getValue()
										     			.getOidAs(X47BOrganizationOID::forId)
										      : null;
	}
	private X47BOrganizationID _selectedOrgId() {
		return _orgDivsCmb.getValue() != null ? _orgsCmb.getValue()
										     			.getIdAs(X47BOrganizationID::forId)
										      : null;
	}
	private X47BOrgDivisionOID _selectedOrgDivisionOid() {
		return _orgDivsCmb.getValue() != null ? _orgDivsCmb.getValue()
										     			   .getOidAs(X47BOrgDivisionOID::forId)
										      : null;
	}
	private X47BOrgDivisionID _selectedOrgDivisionId() {
		return _orgDivsCmb.getValue() != null ? _orgDivsCmb.getValue()
										     			   .getIdAs(X47BOrgDivisionID::forId)
										      : null;
	}

/////////////////////////////////////////////////////////////////////////////////////////
//	COMBO REFRESH
/////////////////////////////////////////////////////////////////////////////////////////
	private void _refreshOrganizationsCombo() {
		log.info("...refresh organizations combo");
		_presenter.onOrgsComboDataNeeded(_i18n.getCurrentLanguage(),
										 orgs -> {	// when loaded the combo data, just refresh the combo
											 		ListDataProvider<PB01VaadinComboItem> dataProvider = DataProvider.ofCollection(orgs);
											 		_orgsCmb.setDataProvider(dataProvider);
										 		 });
	}
	private void _refreshOrgDivisionsCombo(final X47BOrganizationOID orgOid) {
		if (orgOid == null) {
			_orgDivsCmb.setDataProvider(DataProvider.ofCollection(Lists.newArrayList()));
		} else {
			log.info("...refresh org divisions combo for org oid={}",orgOid);
			_presenter.onOrgDivisionsComboDataNeeded(orgOid,
													 _i18n.getCurrentLanguage(),
											 		 divs -> {	// when loaded the combo data, just refresh the combo
														 		ListDataProvider<PB01VaadinComboItem> dataProvider = DataProvider.ofCollection(divs);
														 		_orgDivsCmb.setDataProvider(dataProvider);
											 		 		 });
		}
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	DETAIL WINDOW
/////////////////////////////////////////////////////////////////////////////////////////
	private void _showOrganizationDetailView(final X47BOrganizationOID oid) {
		// after save or delete at the detail view the action to be done is the same: refresh the list & close the detail view
		_orgDetailPopUp.goTo(oid,
				 		     // save subscriber: executed after save: refresh the organizations combo
				 		     UIPresenterSubscriber.from(// OK
				 				   					    viewObj -> {
				 				   						  			// refresh the org divisions combo
				 			   										_refreshOrganizationsCombo();

				 			   										// select the saved org division
				 			   										PB01VaadinComboItem cmbItem = PB01VaadinComboItem.FROM_VIEW_OBJ.apply(viewObj,
				 			   																											  _i18n.getCurrentLanguage());
				 			   										_orgsCmb.setValue(cmbItem);

				 			   										// close the popup
				 			   										_orgDetailPopUp.close();
				 		   										   },
				 				   					    // Error
				 				   					    th -> log.error("Error while saving org with oid={}: {}",
				 				   							  		    oid,
				 				   							  		    th.getMessage(),th)),
				 		     // delete subscriber: executed after delete
				 		     UIPresenterSubscriber.log(log));
		UI.getCurrent()
		  .addWindow(_orgDetailPopUp);
	}
	private void _showOrgDivisionDetailView(final X47BOrgDivisionOID oid) {
		// after save or delete at the detail view the action to be done is the same: refresh the list & close the detail view
		_orgDivDetailPopUp.goTo(oid,
					 		    // save subscriber: executed after save: refresh the organizations combo
					 		    UIPresenterSubscriber.from(// OK
					 				   					   viewObj -> {
					 				   						 			// get the currently selected org
					 				   						 			X47BOrganizationOID selectedOrgOid = _selectedOrgOid();
					 				   						 			if (selectedOrgOid == null) throw new IllegalStateException("NO organization selected!");

					 				   						 			// refresh the org divisions combo
					 			   										_refreshOrgDivisionsCombo(selectedOrgOid);

					 			   										// select the saved org division
					 			   										PB01VaadinComboItem cmbItem = PB01VaadinComboItem.FROM_VIEW_OBJ.apply(viewObj,
					 			   																										 	  _i18n.getCurrentLanguage());
					 			   										_orgDivsCmb.setValue(cmbItem);

					 			   										// close the popup window
					 			   										_orgDivDetailPopUp.close();
					 		   										 },
					 				   					   // Error
					 				   					   th -> log.error("Error while saving org division with oid={}: {}",
					 				   							  		   oid,
					 				   							  		   th.getMessage(),th)),
					 		    // delete subscriber: executed after delete
					 		    UIPresenterSubscriber.log(log));
		UI.getCurrent()
		  .addWindow(_orgDivDetailPopUp);
	}
}
