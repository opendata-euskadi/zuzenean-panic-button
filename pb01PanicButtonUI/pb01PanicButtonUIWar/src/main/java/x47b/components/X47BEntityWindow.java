package x47b.components;

import java.util.ResourceBundle;

import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.server.ThemeResource;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import lombok.Getter;
import lombok.experimental.Accessors;
import r01f.util.types.Strings;
import x47b.application.X47BPanicButtonVaadinApp;
import x47b.forms.X47BEntityObjectForm;
import x47b.forms.X47BLocationForm;
import x47b.forms.X47BOrganizationForm;
import x47b.forms.X47BWorkPlaceForm;
import x47b.services.X47BAPIHelper;

/**
 * Ventana para la edición de entidades
 */
@Accessors(prefix="_")
public class X47BEntityWindow 
     extends Window {
					
	private static final long serialVersionUID = 2976211012295960218L;
/////////////////////////////////////////////////////////////////////////////////////////
//  
/////////////////////////////////////////////////////////////////////////////////////////
			private X47BAPIHelper _api;
		
			private ResourceBundle _bundle = ((X47BPanicButtonVaadinApp) UI.getCurrent()).getBundle();
	
	@Getter private Button _saveBtn = new Button(_bundle.getString("x47b.buttons.save"));
	@Getter private Button _cancelBtn = new Button(_bundle.getString("x47b.buttons.cancel"));	
	@Getter private Button _removeBtn = new Button(_bundle.getString("x47b.buttons.remove"));		
	
	@Getter private Button _selectedBtn;
	@Getter private boolean _newEntity;
	
/////////////////////////////////////////////////////////////////////////////////////////
//  
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BEntityWindow(final X47BEntityObjectForm form,
							final X47BAPIHelper api) {		
		_api = api;		 
		_newEntity = true;
		
		VerticalLayout l = new VerticalLayout();
		//l.setSizeFull();
		
	    l.addComponent(form);	    
	    // add form buttons
	    HorizontalLayout buttonBar = new HorizontalLayout();
	    buttonBar.setWidth(100, Unit.PERCENTAGE);
	    buttonBar.setSpacing(true);
	    buttonBar.setMargin(new MarginInfo(false, true, false, true));		    	    	
	    	    
	    _removeBtn.setIcon(new ThemeResource("images/r01_delete.png"));
	    _saveBtn.setIcon(new ThemeResource("images/r01_save.png"));
	    _cancelBtn.setIcon(new ThemeResource("images/r01_cancel.png"));
	    
	    _saveBtn.setClickShortcut(KeyCode.ENTER);
	    _cancelBtn.setClickShortcut(KeyCode.ESCAPE);
	    						    
	    buttonBar.addComponent(_removeBtn);
	    buttonBar.addComponent(_saveBtn);		    
	    buttonBar.addComponent(_cancelBtn);	
	    buttonBar.setExpandRatio(_removeBtn, 1);
	    buttonBar.setComponentAlignment(_removeBtn, Alignment.MIDDLE_LEFT);
	    l.addComponent( buttonBar );
	    	    
	    setContent(l);
	    
	    //Evento botón Cancelar
	    _cancelBtn.addClickListener(
	    				new ClickListener() {			
							@Override
							public void buttonClick(ClickEvent event) {
								getUI().removeWindow(X47BEntityWindow.this);
								
							}
						});
	    
	    //Evento botón Guardar
	    _saveBtn.addClickListener(
	    				new ClickListener() {			
								@Override
								public void buttonClick(ClickEvent event) {									
									String validEmptyFieldsMsg = form.composeValidateEmptyFieldMessage();				
									if (Strings.isNullOrEmpty(validEmptyFieldsMsg)) {
										String validFieldMsg = form.composeValidateEntityFieldMessage();
										
										if (Strings.isNullOrEmpty(validFieldMsg)) {										
											_newEntity = form.isNewEntity();													
											
											if (_newEntity && form.exists()) {
												Notification.show(_bundle.getString("x47b.msg.existsID"));
											} else {						
												//Establece el objeto entidad, que devuelve el API, en el data de la Ventana
												X47BEntityWindow.this.setData(form.save());	
												//Establece el botón Guardar como seleccionado
												_selectedBtn = _saveBtn;								
												getUI().removeWindow(X47BEntityWindow.this);
											}	
										} else {
											_showValidationErrors(validFieldMsg);	
										}
									} else {
										//Hay campos obligatorios
										_showValidationErrors(validEmptyFieldsMsg);					
									}
								}
							});
	    
	    //Evento botón Borrar
	    _removeBtn.addClickListener(
	    				new ClickListener() {			
							@Override
							public void buttonClick(ClickEvent event) {						
								final X47BConfirmWindow wConfirm = new X47BConfirmWindow();								
								wConfirm.setStyleName("x47bentityWindow");				
								wConfirm.setWidth(350, Unit.PIXELS);
								wConfirm.setHeight(250,Unit.PIXELS);
								wConfirm.center();
								wConfirm.setModal(true);
								wConfirm.setResizable(false);
								String removeMsg = _setRemoveMsgSettings(wConfirm, form);
								getUI().addWindow(wConfirm);
								wConfirm.getLabelMessage().setValue(removeMsg);
								wConfirm.getYesBtn()
										.addClickListener( 
												new ClickListener() {
													@Override
													public void buttonClick(ClickEvent event) {
														//Establece el objeto entidad, que devuelve el API, en el data de la Ventana
														X47BEntityWindow.this.setData(form.remove());		
														//Establece el botón Borrar como seleccionado
														_selectedBtn = _removeBtn;
														_newEntity = form.isNewEntity();
														
														getUI().removeWindow(wConfirm);
														getUI().removeWindow(X47BEntityWindow.this);
													}
												});
							}
						});	    

	}
	
	
	/**
	 * Muestra una ventana con los mesajes de validación
	 * @param validationMsg
	 */
	private void _showValidationErrors(final String validationMsg) {
		final X47BValidationWindow wValid = new X47BValidationWindow();	
		wValid.setCaption(_bundle.getString("x47b.buttons.save") + " " + this.getCaption());
		wValid.setStyleName("entityWindow");				
		wValid.setWidth(350, Unit.PIXELS);
		wValid.setHeight(270, Unit.PIXELS);
		wValid.center();
		wValid.setModal(true);
		wValid.setResizable(false);					
		getUI().addWindow(wValid);
		wValid.getLabelMessage().setValue(validationMsg);
		wValid.getCloseBtn().addClickListener(
									new ClickListener() {						
											@Override
											public void buttonClick(ClickEvent event) {														
												getUI().removeWindow(wValid);							
											}
									});
		
	}
	
	/**
	 * Compone el mensaje de la ventana de confirmación de boorrado
	 * y establece los captions e iconos de la ventana de confirmación según la entidad
	 * @param wConfirm objeto ventana de confirmación
	 * @param form formulario. Indica el tipo de entidad
	 */
	private String _setRemoveMsgSettings(final X47BConfirmWindow wConfirm,
										 final X47BEntityObjectForm<?> form) {									
		String entityMsg = _bundle.getString("x47b.msg.delete.confirm");		
		StringBuffer msg = new StringBuffer(100);
		msg.append("<h1>").append(_bundle.getString("x47b.msg.delete.confirm.warning")).append("</h1><p>");
		
		if (form instanceof X47BOrganizationForm) {
			msg.append(Strings.customized(entityMsg,
										  _bundle.getString("x47b.msg.delete.organization"),
										  form.getNameES().getValue()));			
			wConfirm.setIcon(new ThemeResource(X47BPanicButtonVaadinApp.ORGANIZATION_ICON));
			wConfirm.setCaption(_bundle.getString("x47b.msg.delete.organization.caption"));
		} else 	if (form instanceof X47BLocationForm) {	
			msg.append(Strings.customized(entityMsg,
										  _bundle.getString("x47b.msg.delete.location"), 
										  form.getNameES().getValue()));
			wConfirm.setIcon(new ThemeResource(X47BPanicButtonVaadinApp.LOCATION_ICON));
			wConfirm.setCaption(_bundle.getString("x47b.msg.delete.location.caption"));
		} else 	if (form instanceof X47BWorkPlaceForm) {
			msg.append(Strings.customized(entityMsg,
										  _bundle.getString("x47b.msg.delete.workPlace"), 
										  form.getNameES().getValue()));
			wConfirm.setIcon(new ThemeResource(X47BPanicButtonVaadinApp.WORKPLACE_ICON));
			wConfirm.setCaption(_bundle.getString("x47b.msg.delete.workPlace.caption"));
		}					
		msg.append("</p>");
		return msg.toString();
	}	
}
