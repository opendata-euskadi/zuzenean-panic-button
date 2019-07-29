package x47b.forms;

import com.vaadin.ui.AbstractField;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;

import r01f.locale.Language;
import r01f.locale.LanguageTexts.LangTextNotFoundBehabior;
import r01f.locale.services.LanguageTextsBuilder;
import x47b.components.X47BOrganizationIDComponent;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrganizationID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrganizationOID;
import x47b.model.org.X47BOrganization;
import x47b.services.X47BAPIHelper;


/**
 * Componente formulario para la edici�n de  organizaciones
 */
public class X47BOrganizationForm extends X47BEntityObjectForm<X47BOrganization> {
	//Combo organizaciones
	private static final long serialVersionUID = 2315835785584073107L;

	//Objeto organizaci�n
	private X47BOrganization _organization;

	/**
	 * Constructor
	 * @param fLayout layout formulario
	 * @param api
	 */
	public X47BOrganizationForm(GridLayout fLayout, X47BAPIHelper api) {
		super( fLayout , api, new X47BOrganizationIDComponent(new HorizontalLayout()), 0);
		_organization=null;
	}
	@Override
	public X47BOrganization save() {
		return _api.saveOrganization(getOrganization());
	}
	@Override
	public X47BOrganization remove() {
		return _api.deleteOrganization(_organization.getOid());
	}
	@Override
	public boolean exists() {
		if (_api.loadOrganizationByID(X47BOrganizationID.forId(_organizationIDComponent.getOrganizationId().getValue().toUpperCase())) != null) {
			return true;
		}
		return false;
	}

	/**
	 * Compone el objeto {@link X47BOrganization} con los datos del formulario
	 * @return org objeto {@link X47BOrganization}
	 */
	public X47BOrganization getOrganization() {
		if (_organization == null) {
			X47BOrganizationOID oid = X47BOrganizationOID.supply();
			_organization = new X47BOrganization();
			_organization.setOid(oid);
		}
		_organization.setId(X47BOrganizationID.forId(_organizationIDComponent.getOrganizationId().getValue().toUpperCase()));
		_organization.setNameByLanguage(LanguageTextsBuilder.createMapBacked()
											   .withMissingLangTextBehavior(LangTextNotFoundBehabior.RETURN_NULL)
											   .addForLang(Language.SPANISH,_nameES.getValue())
											   .addForLang(Language.BASQUE,_nameEU.getValue())
											   .build());
		_organization.setPhones(createPhoneList());
		_organization.setEmails(createEMailList());
		return _organization;
	}
	/**
	 * Establece el objeto {@link X47BOrganization} en el formulario
	 */
	public void setValue(X47BOrganization org) {
		_organization = org;
		_newEntity = false;
		_organizationIDComponent.getOrganizationId().setValue(_organization.getId().asString());
		_organizationIDComponent.getOrganizationId().setEnabled(false);
		_organizationIDComponent.getOrganizationId().addStyleName("x47b-textField-disabled");
		_organizationIDComponent.getOrganizationId().setRequired(false);
		_nameES.setValue(_organization.getName().getIn(Language.SPANISH).orNull());
		_nameEU.setValue(_organization.getName().getIn(Language.BASQUE).orNull());
		_phones.setValue(_organization.convertPhonesToString());
		_emails.setValue(_organization.convertEmailsToString());
	}
	@Override
	public String composeValidateEmptyFieldMessage() {
		AbstractField<?>[] fields = {_organizationIDComponent.getOrganizationId()};
		return super.composeValidateEntityEmptyFieldMessage(fields);
	}


}
