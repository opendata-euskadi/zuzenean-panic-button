package x47b.components;

import java.util.ResourceBundle;

import com.vaadin.server.ThemeResource;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import lombok.Getter;
import lombok.experimental.Accessors;
import x47b.application.X47BPanicButtonVaadinApp;

/**
 * Ventana de confirmación de Acciones
 */
@Accessors(prefix="_")
public class X47BConfirmWindow 
     extends Window {
	
	private static final long serialVersionUID = -6130502387134347316L;
/////////////////////////////////////////////////////////////////////////////////////////
//  
/////////////////////////////////////////////////////////////////////////////////////////
			private ResourceBundle _bundle = ((X47BPanicButtonVaadinApp) UI.getCurrent()).getBundle();
	
	@Getter private Button _yesBtn = new Button(_bundle.getString("x47b.buttons.yes"));
	@Getter private Button _noBtn = new Button(_bundle.getString("x47b.buttons.no"));					
	@Getter private Label _labelMessage;	
	
/////////////////////////////////////////////////////////////////////////////////////////
//  
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BConfirmWindow() {						
		VerticalLayout l= new VerticalLayout();
		HorizontalLayout warningLayout = new HorizontalLayout();
		warningLayout.setSpacing(true);
		warningLayout.setSizeFull();
		
		Embedded logo = new Embedded(null,new ThemeResource("images/r01_close.png"));
		
		
		l.setMargin(true);
		l.setSpacing(true);	
		_labelMessage = new Label();
		_labelMessage.setContentMode(ContentMode.HTML);
		_labelMessage.addStyleName("x47bdeletewarning");
		
		warningLayout.addComponent(logo);
		warningLayout.addComponent(_labelMessage);
		warningLayout.setExpandRatio(_labelMessage, 1);
		
		
	    l.addComponent(warningLayout);	    
	    // add form buttons
	    HorizontalLayout buttonBar = new HorizontalLayout();
	    buttonBar.setWidth(100, Unit.PERCENTAGE);
	    buttonBar.setSpacing(true);
	    buttonBar.setMargin(new MarginInfo(true));	
	    	   
	    _yesBtn.setIcon(new ThemeResource("images/r01_accept.png"));
	    _noBtn.setIcon(new ThemeResource("images/r01_cancel.png"));
	    						    	  
	    buttonBar.addComponent(_yesBtn);		    
	    buttonBar.addComponent(_noBtn);	
	    buttonBar.setExpandRatio(_yesBtn, 1);
	    buttonBar.setComponentAlignment(_noBtn, Alignment.MIDDLE_LEFT);
	    l.addComponent( buttonBar );
	    	    
	    setContent(l);
	    
	    //Evento botón NO
	    _noBtn.addClickListener(
	    			new ClickListener() {			
						@Override
						public void buttonClick(ClickEvent event) {
							X47BConfirmWindow.this.setData(false);	
							getUI().removeWindow(X47BConfirmWindow.this);
							
						}
					});
	    
	    //Evento botón SI
	    _yesBtn.addClickListener(
	    			new ClickListener() {			
						@Override
						public void buttonClick(ClickEvent event) {	
							//Establece el objeto entidad, que devuelve el API, en el data de la Ventana
							X47BConfirmWindow.this.setData(true);	
							//Establece el botón Guardar como seleccionado								
							getUI().removeWindow(X47BConfirmWindow.this);
						}
					});
	    
	

	}
	
	
	
	
	

}
