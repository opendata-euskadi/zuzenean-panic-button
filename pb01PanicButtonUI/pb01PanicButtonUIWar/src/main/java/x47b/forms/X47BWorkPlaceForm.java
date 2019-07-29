package x47b.forms;

import java.util.List;
import java.util.ResourceBundle;

import com.google.common.base.Splitter;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;

import lombok.Getter;
import lombok.Setter;
import r01f.locale.Language;
import r01f.locale.LanguageTexts.LangTextNotFoundBehabior;
import r01f.locale.services.LanguageTextsBuilder;
import r01f.util.types.Strings;
import x47b.application.X47BPanicButtonVaadinApp;
import x47b.components.X47BLocationIDComponent;
import x47b.components.X47BWorkPlaceIDComponent;
import x47b.model.oids.X47BOrganizationalIDs.X47BWorkPlaceID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceLocationOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrganizationOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BWorkPlaceOID;
import x47b.model.org.X47BOrgDivisionServiceLocation;
import x47b.model.org.X47BWorkPlace;
import x47b.model.org.summaries.X47BSummarizedOrgDivisionServiceLocation;
import x47b.model.org.summaries.X47BSummarizedOrganization;
import x47b.model.search.X47BSearchResultItemForPanicButtonOrganizationalEntity;
import x47b.services.X47BAPIHelper;

/**
 * Componente formulario para la edici�n de lugares de trabajo
 */
public class X47BWorkPlaceForm
	 extends X47BEntityObjectForm<X47BWorkPlace> {

	private static final long serialVersionUID = 2315835785584073107L;
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	private ResourceBundle _bundle = ((X47BPanicButtonVaadinApp)UI.getCurrent()).getBundle();

/////////////////////////////////////////////////////////////////////////////////////////
//  FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	@Getter @Setter private ComboBox cboOrganizations = new ComboBox();
	@Getter @Setter private ComboBox cboLocations = new ComboBox();

					private X47BWorkPlace _workPlace;

/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Constructor
	 * @param fLayout
	 * @param api
	 */
	public X47BWorkPlaceForm(GridLayout fLayout, X47BAPIHelper api) {
		super( fLayout , api, new X47BWorkPlaceIDComponent(new HorizontalLayout()),2);
		_workPlace = null;

		cboOrganizations.setWidthUndefined();
		cboLocations.setWidthUndefined();

		fLayout.addComponent(new Label(_bundle.getString("x47b.organization").concat(":")),0,0);
		fLayout.addComponent(cboOrganizations,1,0);

		fLayout.addComponent(new Label(_bundle.getString("x47b.location").concat(":")),0,1);
		fLayout.addComponent(cboLocations,1,1);

		cboOrganizations.setData(_bundle.getString("x47b.organization"));
		cboLocations.setData(_bundle.getString("x47b.location"));

		loadOrganizatiosCbo(cboOrganizations);

		X47BWorkPlaceIDComponent workPlaceIDCom = (X47BWorkPlaceIDComponent)_organizationIDComponent;
		workPlaceIDCom.getOrganizationId().setEnabled(false);
		workPlaceIDCom.getLocationId().setEnabled(false);
		workPlaceIDCom.getOrganizationId().addStyleName("x47b-textField-disabled");
		workPlaceIDCom.getLocationId().addStyleName("x47b-textField-disabled");

		//Al cambiar de organizaci�n se recarga el combo de Localizaciones
		cboOrganizations.addValueChangeListener(new Property.ValueChangeListener() {
			@Override
		    public void valueChange(ValueChangeEvent event) {
		    	X47BWorkPlaceForm.this.loadLocationsCbo(cboLocations,
		    						  				(X47BOrganizationOID)cboOrganizations.getValue());
		    	X47BSummarizedOrganization sumOrg = ((BeanItem<X47BSummarizedOrganization>) cboOrganizations.getItem(cboOrganizations.getValue())).getBean();
		    	_organizationIDComponent.getOrganizationId().getValue();
		    	_organizationIDComponent.getOrganizationId().setValue(sumOrg.getId().asString());
		    }
		});


		//Al cambiar de organizaci�n se recarga el combo de Localizaciones
		cboLocations.addValueChangeListener(new Property.ValueChangeListener() {
			@Override
		    public void valueChange(ValueChangeEvent event) {
		    	X47BSummarizedOrgDivisionServiceLocation sumOrg = ((BeanItem<X47BSummarizedOrgDivisionServiceLocation>) cboLocations.getItem(cboLocations.getValue())).getBean();
		    	((X47BLocationIDComponent)_organizationIDComponent).getLocationId().setValue(Splitter.on('/')
						.splitToList(sumOrg.getId().getHierarchy().getLocationIdAsStringOrNull())
						.get(1));
		    }
		});

	}

/////////////////////////////////////////////////////////////////////////////////////////
//  METHODS
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public X47BWorkPlace save() {
		return _api.saveWorkPlace(this.getWorkPlace());
	}
	@Override
	public X47BWorkPlace remove() {
		return _api.deleteWorkPlace(_workPlace.getOid());
	}
	@Override
	public boolean exists() {
		X47BWorkPlaceIDComponent entityId = (X47BWorkPlaceIDComponent)_organizationIDComponent;
		String id = Strings.customized("{}/{}/{}", entityId.getOrganizationId().getValue(),
													entityId.getLocationId().getValue(),
													entityId.getWorkPlaceId().getValue());

		//X47BWorkPlace workPlace = _api.loadWorkPlaceByID(X47BWorkPlaceID.forId(id.toUpperCase()));
		X47BWorkPlace workPlace = _api.loadWorkPlaceByID(X47BWorkPlaceID.forId(entityId.getWorkPlaceId().getValue().toUpperCase()));

		if (workPlace != null) {
			return true;
		}

		return false;
	}
	/**
	 * Compone el objeto {@link X47BWorkPlace} con los datos del formulario
	 * @return objeto {@link X47BWorkPlace}
	 */
	public X47BWorkPlace getWorkPlace() {
		if (_workPlace == null) {
			//New X47BWorkPlace
			_workPlace = new X47BWorkPlace()
								.withOid(X47BWorkPlaceOID.supply());

			X47BOrgDivisionServiceLocation loc =  _api.loadLocation((X47BOrgDivisionServiceLocationOID)cboLocations.getValue());
			X47BWorkPlaceIDComponent workPlaceIDCom = (X47BWorkPlaceIDComponent)_organizationIDComponent;

			//The X47BWorkPlace Id has that format: orgId/locId/workPlaceId
			_workPlace.setId(X47BWorkPlaceID.forId(Strings.customized("{}/{}",
												  loc.getId().asString(),
												  workPlaceIDCom.getWorkPlaceId().getValue().toUpperCase())));

			_workPlace.setOrganizationOid((X47BOrganizationOID) cboOrganizations.getValue());
			_workPlace.setLocationOid((X47BOrgDivisionServiceLocationOID) cboLocations.getValue());
		}

		//Rest of new values or fields that can be modified.
		_workPlace.setNameByLanguage(LanguageTextsBuilder.createMapBacked()
										   .withMissingLangTextBehavior(LangTextNotFoundBehabior.RETURN_NULL)
										   .addForLang(Language.SPANISH,_nameES.getValue())
										   .addForLang(Language.BASQUE,_nameEU.getValue())
										   .build());

		_workPlace.setPhones(createPhoneList());
		_workPlace.setEmails(createEMailList());

		return _workPlace;
	}

	/**
	 * Establece el objeto {@link X47BSearchResultItemForPanicButtonOrganizationalEntity} en el formulario
	 * Carga el {@link X47BWorkPlace} correspondiente y lo establece en el formulario
	 */
	public void setValue(final X47BWorkPlaceOID workPlaceOID) {
		_workPlace = _api.loadWorkPlace(workPlaceOID);
		_newEntity = false;

		cboOrganizations.setValue(_workPlace.getOrgRef().getOid());
		cboOrganizations.setEnabled(false);
		cboOrganizations.addStyleName("x47b-combo-disabled");

		this.loadLocationsCbo(cboLocations,
							  _workPlace.getOrgRef().getOid());
		cboLocations.setValue(_workPlace.getOrgDivisionServiceLocationRef().getOid());
		cboLocations.setEnabled(false);
		cboLocations.addStyleName("x47b-combo-disabled");

		X47BWorkPlaceIDComponent workPlaceIDCom = (X47BWorkPlaceIDComponent)_organizationIDComponent;
		workPlaceIDCom.getOrganizationId().setValue(_workPlace.getId().getHierarchy().getOrganizationIdAsStringOrNull());
		workPlaceIDCom.getOrganizationId().setEnabled(false);
		workPlaceIDCom.getOrganizationId().setRequired(false);
		workPlaceIDCom.getOrganizationId().addStyleName("x47b-textField-disabled");

		List<String> listIDs = Splitter.on('/')
										.splitToList(_workPlace.getId().getHierarchy().getAgentIdAsStringOrNull());

		workPlaceIDCom.getLocationId().setValue(listIDs.get(1));
		workPlaceIDCom.getLocationId().setEnabled(false);
		workPlaceIDCom.getLocationId().setRequired(false);
		workPlaceIDCom.getLocationId().addStyleName("x47b-textField-disabled");
		workPlaceIDCom.getWorkPlaceId().setValue(listIDs.get(2));
		workPlaceIDCom.getWorkPlaceId().setEnabled(false);
		workPlaceIDCom.getWorkPlaceId().setRequired(false);
		workPlaceIDCom.getWorkPlaceId().addStyleName("x47b-textField-disabled");

		_nameES.setValue(_workPlace.getName().getIn(Language.SPANISH).orNull());
		_nameEU.setValue(_workPlace.getName().getIn(Language.BASQUE).orNull());
		_phones.setValue(_workPlace.convertPhonesToString());
		_emails.setValue(_workPlace.convertEmailsToString());
	}
	@Override
	public String composeValidateEmptyFieldMessage() {
		X47BWorkPlaceIDComponent workPlaceIDCom = (X47BWorkPlaceIDComponent)_organizationIDComponent;
		AbstractField<?>[] fields = {cboOrganizations,
								     cboLocations,
									 workPlaceIDCom.getOrganizationId(),
									 workPlaceIDCom.getLocationId()};
		return super.composeValidateEntityEmptyFieldMessage(fields);
	}


}
