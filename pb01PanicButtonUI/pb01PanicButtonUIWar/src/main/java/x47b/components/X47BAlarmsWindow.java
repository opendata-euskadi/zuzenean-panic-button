package x47b.components;

import java.util.Collection;
import java.util.ResourceBundle;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import lombok.Getter;
import x47b.application.X47BPanicButtonVaadinApp;
import x47b.model.X47BAlarmEvent;

/**
 * View last alarm events.
 */
public class X47BAlarmsWindow 
     extends Window {			
	
	private static final long serialVersionUID = -5594621332616069856L;
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTANTS
/////////////////////////////////////////////////////////////////////////////////////////
	public static final String WORKPLACEHOST_COLUMN = "workPlaceHost";
	public static final String LOCATION_ID_COLUMN = "locationId";
	public static final String DATE_COLUMN = "timeStamp";	
/////////////////////////////////////////////////////////////////////////////////////////
//  FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
			private ResourceBundle _bundle = ((X47BPanicButtonVaadinApp) UI.getCurrent()).getBundle();	
	@Getter private Button closeBtn = new Button(_bundle.getString("x47b.buttons.close"));							
			private Table _alarmsTable;
	
	public X47BAlarmsWindow(final Collection<X47BAlarmEvent> alarms) {						
		VerticalLayout l= new VerticalLayout();
								
		l.setMargin(true);
		l.setSpacing(true);	
		
		_buildAlarmsTable(alarms);
		l.addComponent(_alarmsTable);
		
	    // add form buttons
	    HorizontalLayout buttonBar = new HorizontalLayout();
	    buttonBar.setWidth(100, Unit.PERCENTAGE);
	    buttonBar.setSpacing(false);
	    buttonBar.setMargin(false);	
	    	   	    
	    closeBtn.setIcon(new ThemeResource("images/r01_cancel.png"));
	    						    	  
	    buttonBar.addComponent(closeBtn);		    	    
	    buttonBar.setComponentAlignment(closeBtn, Alignment.MIDDLE_CENTER);
	    l.addComponent( buttonBar );
	    	    
	    setContent(l);
	    
	    //Evento botón NO
	    closeBtn.addClickListener(new ClickListener() {			
										@Override
										public void buttonClick(ClickEvent event) {
											X47BAlarmsWindow.this.setData(false);	
											getUI().removeWindow(X47BAlarmsWindow.this);				
										}
								   });
	    		    	
	}
	
	private void _buildAlarmsTable(final Collection<X47BAlarmEvent> alarms) {
		_alarmsTable = new Table();
		_alarmsTable.setWidth(100, Unit.PERCENTAGE);
		
		
		_loadTable(alarms);				
	}
	
	
	/**
	 * Carga la tabla de alarmas
	 */
	private void _loadTable(final Collection<X47BAlarmEvent> alarms) {
				
		BeanItemContainer<X47BAlarmEvent> beanAlarms = new BeanItemContainer<X47BAlarmEvent>(X47BAlarmEvent.class);								
			 
		if (alarms != null) {									
			beanAlarms.addAll(alarms);
		} else {
			_alarmsTable.setContainerDataSource(new BeanItemContainer<X47BAlarmEvent>(X47BAlarmEvent.class));		
		}
		
		_alarmsTable.setContainerDataSource(beanAlarms);					
		_setTableVisibleColumns();		
	}	
	
	/**
	 * Establece las columnas visibles
	 */
	private void _setTableVisibleColumns() {		
		_alarmsTable.setVisibleColumns((Object[]) new String[] { WORKPLACEHOST_COLUMN, LOCATION_ID_COLUMN, DATE_COLUMN});
		
		_alarmsTable.setColumnHeader(WORKPLACEHOST_COLUMN, _bundle.getString("x47b.workPlaceHost"));
		_alarmsTable.setColumnHeader(LOCATION_ID_COLUMN, _bundle.getString("x47b.location"));
		_alarmsTable.setColumnHeader(DATE_COLUMN, _bundle.getString("x47b.date"));
		
								
		

	}	
	
	

}
