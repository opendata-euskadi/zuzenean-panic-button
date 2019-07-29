package x47b.forms;

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
import lombok.experimental.Accessors;
import r01f.locale.Language;
import r01f.locale.LanguageTexts.LangTextNotFoundBehabior;
import r01f.locale.services.LanguageTextsBuilder;
import r01f.util.types.Strings;
import x47b.application.X47BPanicButtonVaadinApp;
import x47b.components.X47BLocationIDComponent;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionServiceLocationID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceLocationOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrganizationOID;
import x47b.model.org.X47BOrgDivisionServiceLocation;
import x47b.model.org.X47BOrganization;
import x47b.model.org.summaries.X47BSummarizedOrganization;
import x47b.model.search.X47BSearchResultItemForPanicButtonOrganizationalEntity;
import x47b.services.X47BAPIHelper;


/**
 * Componente formulario para la edición de  localizaciones
 */
@Accessors(prefix="_")
public class X47BLocationForm 
	 extends X47BEntityObjectForm<X47BOrgDivisionServiceLocation> {	
	
	private static final long serialVersionUID = 2315835785584073107L;
/////////////////////////////////////////////////////////////////////////////////////////
//  
/////////////////////////////////////////////////////////////////////////////////////////
					private ResourceBundle _bundle = ((X47BPanicButtonVaadinApp) UI.getCurrent()).getBundle();	
	@Getter @Setter private ComboBox _cboOrganizations = new ComboBox();
	
					private X47BOrgDivisionServiceLocation _location;
		
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////	
	/**
	 * Constructor
	 * @param fLayout
	 * @param api
	 */	
	public X47BLocationForm(GridLayout fLayout, X47BAPIHelper api) {				
		super( fLayout , api, new X47BLocationIDComponent(new HorizontalLayout()),1);
		_location = null;
		
		_cboOrganizations.setWidthUndefined();		
		_cboOrganizations.setData(_bundle.getString("x47b.organization"));
		
		fLayout.addComponent(new Label(_bundle.getString("x47b.organization").concat(":")),0,0);
		fLayout.addComponent(_cboOrganizations,1,0);
				
		loadOrganizatiosCbo(_cboOrganizations);
		
		//Al cambiar de organización se recarga el combo de Localizaciones
		_cboOrganizations.addValueChangeListener(new Property.ValueChangeListener() {
			@Override
		    public void valueChange(ValueChangeEvent event) {		    	  
		    	X47BSummarizedOrganization sumOrg = ((BeanItem<X47BSummarizedOrganization>)_cboOrganizations.getItem(_cboOrganizations.getValue())).getBean();	    	
		    	_organizationIDComponent.getOrganizationId().setValue(sumOrg.getId().asString());
		    }		
		});	   		
		
		
		X47BLocationIDComponent locationIDCom = (X47BLocationIDComponent)_organizationIDComponent;		
		locationIDCom.getOrganizationId().setEnabled(false);
		locationIDCom.getOrganizationId().addStyleName("x47b-textField-disabled");
		
		
		
	}
	
/////////////////////////////////////////////////////////////////////////////////////////
//  METHODS
/////////////////////////////////////////////////////////////////////////////////////////	
	
	@Override	
	public X47BOrgDivisionServiceLocation save() {
		return _api.saveLocation(this.getLocation());		
	}
	
	@Override
	public X47BOrgDivisionServiceLocation remove() {
		return _api.deleteLocation(_location.getOid());
	}
	
	/* (non-Javadoc)
	 * @see x47b.forms.X47BEntityObjectForm#exists()
	 */
	public boolean exists() {						
		X47BLocationIDComponent entityId = (X47BLocationIDComponent)_organizationIDComponent;
		String id = Strings.customized("{}/{}", entityId.getOrganizationId().getValue(), 
													entityId.getLocationId().getValue());
		
		X47BOrgDivisionServiceLocation location = _api.loadLocationByID(X47BOrgDivisionServiceLocationID.forId(id.toUpperCase())); 
		
		if (location != null) {
			return true;
			
		}
		
		return false;		
	}		
	
	/**
	 * Compone el objeto {@link X47BLocation} con los datos del formulario
	 * @return objeto {@link X47BLocation}
	 */
	public X47BOrgDivisionServiceLocation getLocation() {				
		if (_location == null) {
			//New Location
			_location = new X47BOrgDivisionServiceLocation().withOid(X47BOrgDivisionServiceLocationOID.supply());												
			
			X47BOrganization org =  _api.loadOrganization((X47BOrganizationOID) _cboOrganizations.getValue());			
			X47BLocationIDComponent locationIDCom = (X47BLocationIDComponent)_organizationIDComponent;
			
			locationIDCom.getOrganizationId().setValue(org.getId().asString());	
			
			//The location Id has that format: orgId/locId
			_location.setId(X47BOrgDivisionServiceLocationID.forId(Strings.customized("{}/{}",
													locationIDCom.getOrganizationId().getValue().toUpperCase(),
													locationIDCom.getLocationId().getValue().toUpperCase())));
			_location.setOrganizationOid((X47BOrganizationOID)_cboOrganizations.getValue());
		}
						
		//Rest of new values or fields that can be modified.
		_location.setName(LanguageTextsBuilder.createMapBacked()
										   .withMissingLangTextBehavior(LangTextNotFoundBehabior.RETURN_NULL)
										   .addForLang(Language.SPANISH,_nameES.getValue())
										   .addForLang(Language.BASQUE,_nameEU.getValue())
										   .build());
		
		_location.setPhones(createPhoneList());
		_location.setEmails(createEMailList());
		
		return _location;
	}	
	
	
	/**
	 * Establece el objeto {@link X47BSearchResultItemForPanicButtonOrganizationalEntity} en el formulario
	 * Carga el {@link X47BLocation} correspondiente y lo establece en el formulario
	 */		
	public void setValue(X47BOrgDivisionServiceLocationOID locationOID) {		
		_location = _api.loadLocation(locationOID);
		_newEntity = false;
		
		_cboOrganizations.setValue(_location.getOrgRef().getOid());
		_cboOrganizations.setEnabled(false);
		_cboOrganizations.addStyleName("x47b-combo-disabled");
						
		X47BLocationIDComponent locationIDCom = (X47BLocationIDComponent)_organizationIDComponent;
		locationIDCom.getOrganizationId().setValue(_location.getId().getHierarchy().getOrganizationIdAsStringOrNull());
		locationIDCom.getOrganizationId().setEnabled(false);
		locationIDCom.getOrganizationId().setRequired(false);
		
		
		locationIDCom.getLocationId().setValue(Splitter.on('/')				
													.splitToList(_location.getId().getHierarchy().getLocationIdAsStringOrNull())
													.get(1));		
		locationIDCom.getLocationId().setEnabled(false);	
		locationIDCom.getLocationId().setRequired(false);
		locationIDCom.getLocationId().addStyleName("x47b-textField-disabled");
		
		
		_nameES.setValue(_location.getName().getFor(Language.SPANISH));
		_nameEU.setValue(_location.getName().getFor(Language.BASQUE));
		_phones.setValue(_location.convertPhonesToString());
		_emails.setValue(_location.convertEmailsToString());	
	}	
	@Override
	public String composeValidateEmptyFieldMessage() {
		X47BLocationIDComponent locationIDCom = (X47BLocationIDComponent)_organizationIDComponent;
		AbstractField<?>[] fields = {_cboOrganizations,
										locationIDCom.getOrganizationId(),
										locationIDCom.getLocationId()};		
		return super.composeValidateEntityEmptyFieldMessage(fields);		
	}
	
				
}
