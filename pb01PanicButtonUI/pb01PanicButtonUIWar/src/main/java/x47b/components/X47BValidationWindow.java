package x47b.components;

import java.util.ResourceBundle;

import com.vaadin.server.ThemeResource;
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
 * Ventana de validación de entidades
 */
@Accessors(prefix="_")
public class X47BValidationWindow 
	 extends Window {				

	private static final long serialVersionUID = -9089207198832992101L;
/////////////////////////////////////////////////////////////////////////////////////////
//  
/////////////////////////////////////////////////////////////////////////////////////////
			private ResourceBundle _bundle = ((X47BPanicButtonVaadinApp) UI.getCurrent()).getBundle();	
	
	@Getter private Button _closeBtn = new Button(_bundle.getString("x47b.buttons.close"));	
	@Getter private Label _labelMessage;	
/////////////////////////////////////////////////////////////////////////////////////////
//  
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BValidationWindow() {						
		VerticalLayout l= new VerticalLayout();
		HorizontalLayout warningLayout = new HorizontalLayout();
		warningLayout.setSpacing(true);
		warningLayout.setSizeFull();
		
		Embedded logo = new Embedded(null,new ThemeResource("images/r01_close.png"));
				
		l.setMargin(true);
		l.setSpacing(true);	
		_labelMessage = new Label();
		_labelMessage.setContentMode(ContentMode.HTML);
				
		warningLayout.addComponent(logo);
		warningLayout.addComponent(_labelMessage);
		warningLayout.setExpandRatio(_labelMessage, 1);
				
	    l.addComponent(warningLayout);	    
	    // add form buttons
	    HorizontalLayout buttonBar = new HorizontalLayout();
	    buttonBar.setWidth(100, Unit.PERCENTAGE);
	    buttonBar.setSpacing(false);
	    buttonBar.setMargin(false);	
	    	   	    
	    _closeBtn.setIcon(new ThemeResource("images/r01_cancel.png"));
	    						    	  
	    buttonBar.addComponent(_closeBtn);		    	    
	    buttonBar.setComponentAlignment(_closeBtn, Alignment.MIDDLE_CENTER);
	    l.addComponent( buttonBar );
	    	    
	    setContent(l);
	    
	    //Evento botón NO
	    _closeBtn.addClickListener(
	    				new ClickListener() {			
							@Override
							public void buttonClick(ClickEvent event) {
								X47BValidationWindow.this.setData(false);	
								getUI().removeWindow(X47BValidationWindow.this);
								
							}
						});
	    		    	
	}
	
	
	
	
	

}
