package x47b.forms;

import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import com.google.common.base.Splitter;
import com.google.common.collect.Sets;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.AbstractSelect;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import r01f.types.contact.EMail;
import r01f.types.contact.Phone;
import r01f.util.types.Strings;
import x47b.application.X47BPanicButtonVaadinApp;
import x47b.components.X47BOrganizationIDComponent;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceLocationOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrganizationOID;
import x47b.model.org.summaries.X47BSummarizedOrgDivisionServiceLocation;
import x47b.model.org.summaries.X47BSummarizedOrganization;
import x47b.services.X47BAPIHelper;

@Accessors(prefix="_")
public abstract class X47BEntityObjectForm<BEAN>
		      extends CustomComponent {

	private static final long serialVersionUID = 2315835785584073107L;
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
					private ResourceBundle _bundle = ((X47BPanicButtonVaadinApp) UI.getCurrent()).getBundle();

					protected X47BAPIHelper _api;

	//Id de organizaci�n
	@Getter @Setter protected X47BOrganizationIDComponent _organizationIDComponent;
	@Getter @Setter protected TextField _nameES = new TextField();
	@Getter @Setter protected TextField _nameEU = new TextField();
	@Getter @Setter protected TextField _phones = new TextField();
	@Getter @Setter protected TextField _emails = new TextField();

	//Formulario en modo nueva entidad
	@Getter 		protected boolean _newEntity;
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * @param fLayout capa grid donde se a�aden los campos del formulario
	 * @param api
	 * @param orgIdCom componente ID de entidad
	 * @param initRow n�mero de fila del grid donde se empiezan a a�adir a�aden los campos comunes
	 */
	public X47BEntityObjectForm(GridLayout fLayout, X47BAPIHelper api, X47BOrganizationIDComponent orgIdCom, int initRow) {
		super( fLayout );

		fLayout.setStyleName("x47bformgrid");


		_api = api;

		_newEntity = true;
		_organizationIDComponent = orgIdCom;

		fLayout.setMargin( true );
		fLayout.setSpacing( true );
		fLayout.setWidth( 100, Unit.PERCENTAGE );



		_nameES.setRequired(true);
		_nameEU.setRequired(true);

		_nameES.setMaxLength(200);
		_nameEU.setMaxLength(200);

		_phones.setMaxLength(256);
		_emails.setMaxLength(256);

		_nameES.setWidth(350, Unit.PIXELS);
		_nameEU.setWidth(350, Unit.PIXELS);
		_phones.setWidth(400, Unit.PIXELS);
		_emails.setWidth(400, Unit.PIXELS);

		_nameES.setData(_bundle.getString("x47b.nameES"));
		_nameEU.setData(_bundle.getString("x47b.nameEU"));
		_phones.setData(_bundle.getString("x47b.phones"));
		_emails.setData(_bundle.getString("x47b.emails"));


		GridLayout hContact = new GridLayout(2,2);
		hContact.setMargin(true);
		hContact.setSpacing(true);
		hContact.setWidth(100, Unit.PERCENTAGE);
		hContact.addComponent(new Label(_bundle.getString("x47b.phones").concat(":")), 0, 0);
		hContact.addComponent(_phones, 1, 0);
		hContact.addComponent(new Label(_bundle.getString("x47b.emails").concat(":")), 0, 1);
		hContact.addComponent(_emails,1,1);
		Panel contactPanel = new Panel(_bundle.getString("x47b.notifyTo").concat(":"), hContact);
		hContact.setColumnExpandRatio(0, 0.5f);
		hContact.setColumnExpandRatio(1, 0.5f);

		fLayout.addComponent(new Label("Id:"),0,initRow);
		fLayout.addComponent(_organizationIDComponent.getIdLayout(),1,initRow);
		fLayout.addComponent(new Label(_bundle.getString("x47b.nameES").concat(":")), 0,initRow+1);
		fLayout.addComponent(_nameES,1,initRow+1);
		fLayout.addComponent(new Label(_bundle.getString("x47b.nameEU").concat(":")),0,initRow+2);
		fLayout.addComponent(_nameEU,1,initRow+2);
		fLayout.addComponent(contactPanel,0,initRow+3,1,initRow+3);

		fLayout.setColumnExpandRatio(0, 0.5f);
		fLayout.setColumnExpandRatio(1, 0.5f);

	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Guarda la entidad cargada en el formulario
	 * @return objeto entidad
	 */
	public abstract BEAN save();
	/**
	 * Elimina la entidad cargada en el formulario
	 * @return  objeto entidad
	 */
	public abstract BEAN remove();
	/**
	 * Comprueba si existe la Entidad por ID
	 * @return  true si existe.
	 */
	public abstract boolean exists();
	/**
	 * Valida los campos obligatorios y construye un mensaje en caso de campos vacios obligatorios.
	 * @return  message.
	 */
	public abstract String composeValidateEmptyFieldMessage();
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Validaci�n de campos obligatorios
	 * Devuelve un mensaje si hay campos obligatorios no introducidos
	 * @param fields Campos propios de una entidad en concreto (Organizaci�n y/o localizaci�n)
	 * @return null si todos los campos son v�lidoso o un mensage con los campos no v�lidos.
	 */
	public String composeValidateEntityEmptyFieldMessage(final AbstractField<?>[] fields) {
		StringBuffer mesaggeHTML = new StringBuffer(100);

		for (AbstractField<?> field:fields) {
			_validateEmptyValue(mesaggeHTML, field);
		}

		_validateEmptyValue(mesaggeHTML, _nameES);
		_validateEmptyValue(mesaggeHTML, _nameEU);

		if (Strings.isNOTNullOrEmpty(mesaggeHTML)) {
			mesaggeHTML.append("</ul>");
		}

		return mesaggeHTML!=null?mesaggeHTML.toString():null;
	}

	/**
	 * Validaci�n de campos
	 * Devuelve un mensaje si hay campos no v�lidos.
	 * @return null si todos los campos son v�lidoso o un mensage con los campos no v�lidos.
	 */
	public String composeValidateEntityFieldMessage() {
		StringBuffer mesaggeHTML = new StringBuffer(100);

		//Tel�fonos
		_validatePhones(mesaggeHTML);
		if (Strings.isNOTNullOrEmpty(mesaggeHTML)) {
			mesaggeHTML.append("</ul>");
			mesaggeHTML.append(_bundle.getString("x47b.msg.validation.phones"));
			return mesaggeHTML.toString();
		}

		//Emails
		_validateEmails(mesaggeHTML);
		if (Strings.isNOTNullOrEmpty(mesaggeHTML)) {
			mesaggeHTML.append("</ul>");
			return mesaggeHTML.toString();
		}


		return mesaggeHTML != null ? mesaggeHTML.toString() : null;
	}





	/**
	 * Devuelve una collecci�n de objetos {@link Phone},
	 * a partir de una cadena de tel�fonos separadas por comas
	 * @return {@link Collection<Phone>}
	 */
	protected  Collection<Phone> createPhoneList() {
        Set<Phone> outPhones = null;
        if(Strings.isNOTNullOrEmpty(_phones.getValue())) {
	        List<String> listPhones = Splitter.on(',')
	        									.trimResults()
	        									.splitToList(_phones.getValue());
	        if (!listPhones.isEmpty()) {
	        	outPhones = Sets.newHashSetWithExpectedSize(listPhones.size());
	            for (String phoneNumber: listPhones) {
	            	outPhones.add(Phone.create(phoneNumber));
	            }
	        }
        }
        return outPhones;
	}

	/**
	 * Devuelve una collecci�n de objetos {@link Email},
	 * a partir de una cadena de emails separadas por comas
	 * @return {@link Collection<EMail>}
	 */
	protected  Collection<EMail> createEMailList() {
        Set<EMail> outEmails = null;

        if(Strings.isNOTNullOrEmpty(_emails.getValue())) {
	        List<String> listEmails = Splitter.on(',')
	        									.trimResults()
	        									.splitToList(_emails.getValue());
	        if (!listEmails.isEmpty()) {
	        	outEmails = Sets.newHashSetWithExpectedSize(listEmails.size());
	            for (String email: listEmails) {
	            	outEmails.add(EMail.create(email));
	            }
	        }
        }
        return outEmails;
	}

	/**
	 * Carga el combo de organizaciones
	 * @param cboOrganizations
	 */
	protected void loadOrganizatiosCbo(final ComboBox cboOrganizations) {
		cboOrganizations.setNullSelectionAllowed(false);
		cboOrganizations.setItemCaptionMode(AbstractSelect.ItemCaptionMode.PROPERTY);
		cboOrganizations.setItemCaptionPropertyId("name");

		BeanContainer<X47BOrganizationOID, X47BSummarizedOrganization> orgContainer = new BeanContainer<X47BOrganizationOID, X47BSummarizedOrganization>(X47BSummarizedOrganization.class);
		orgContainer.setBeanIdProperty("oid");
		orgContainer.addAll(_api.loadOrganizations());

		cboOrganizations.setContainerDataSource(orgContainer);
	}

	/**
	 * Carga el combo de localizaciones
	 * @param cboLocations
	 */
	protected void loadLocationsCbo(final ComboBox cboLocations,
									final X47BOrganizationOID orgOid) {
		cboLocations.setNullSelectionAllowed(false);
		cboLocations.setItemCaptionMode(AbstractSelect.ItemCaptionMode.PROPERTY);
		cboLocations.setItemCaptionPropertyId("name");

		BeanContainer<X47BOrgDivisionServiceLocationOID,
					  X47BSummarizedOrgDivisionServiceLocation> orgContainer = new BeanContainer<X47BOrgDivisionServiceLocationOID,
					  																			 X47BSummarizedOrgDivisionServiceLocation>(X47BSummarizedOrgDivisionServiceLocation.class);
		orgContainer.setBeanIdProperty("oid");
		orgContainer.addAll(_api.loadLocations(orgOid));

		cboLocations.setContainerDataSource(orgContainer);
	}


	/**
	 * Valida si un campo est� vacio y compone el mensaje
	 * @param mesaggeHTML
	 * @param field
	 */
	private void _validateEmptyValue(StringBuffer mesaggeHTML, AbstractField<?> field) {
		//Si el campo es un combo si est� vacio es nulo.
		//Si es un campo de texto el valor ser� un String
		if (field.getValue() == null
				|| (field.getValue() instanceof String && Strings.isNullOrEmpty((String)field.getValue()))) {
			if (Strings.isNullOrEmpty(mesaggeHTML)) {
				mesaggeHTML.append(_bundle.getString("x47b.msg.validation.empty"));
				mesaggeHTML.append("<ul>");
			}
			mesaggeHTML.append("<li>").append((String)field.getData()).append("</li>");
		}

	}


	/**
	 * Valida tel�fonos y compone un mensaje con los tel�fonos no v�lidos
	 * @param mesaggeHTML
	 * @param field
	 */
	private void _validatePhones(final StringBuffer mesaggeHTML) {
		  if(Strings.isNOTNullOrEmpty(_phones.getValue())) {
		        List<String> listPhones = Splitter.on(',')
		        									.trimResults()
		        									.splitToList(_phones.getValue());
		        if (!listPhones.isEmpty()) {
		            for (String phoneNumber: listPhones) {
		            	Phone phone = Phone.create(phoneNumber);
		            	if (!phone.isValid()) {
		        			if (Strings.isNullOrEmpty(mesaggeHTML)) {
		        				mesaggeHTML.append(_bundle.getString("x47b.msg.validation.phones.invalid"));
		        				mesaggeHTML.append("<ul>");
		        			}
		        			mesaggeHTML.append("<li>").append(phoneNumber).append("</li>");
		            	}
		            }
		        }
	        }

	}

	/**
	 * Valida tel�fonos y compone un mensaje con los tel�fonos no v�lidos
	 * @param mesaggeHTML
	 * @param field
	 */
	private void _validateEmails(StringBuffer mesaggeHTML) {
        Set<EMail> outEmails = null;

        if(Strings.isNOTNullOrEmpty(_emails.getValue())) {
	        List<String> listEmails = Splitter.on(',')
	        									.trimResults()
	        									.splitToList(_emails.getValue());
	        if (!listEmails.isEmpty()) {
	        	outEmails = Sets.newHashSetWithExpectedSize(listEmails.size());
	            for (String emailText: listEmails) {
	            	EMail email = EMail.create(emailText);
		            	if (!email.isValid()) {
		        			if (Strings.isNullOrEmpty(mesaggeHTML)) {
		        				mesaggeHTML.append(_bundle.getString("x47b.msg.validation.emails.invalid"));
		        				mesaggeHTML.append("<ul>");
		        			}
		        			mesaggeHTML.append("<li>").append(emailText).append("</li>");
		            	}
		            }
		        }
	        }

	}



}
