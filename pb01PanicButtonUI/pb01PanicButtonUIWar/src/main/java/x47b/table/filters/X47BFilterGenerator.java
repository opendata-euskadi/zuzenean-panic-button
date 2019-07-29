package x47b.table.filters;

import java.util.ResourceBundle;

import org.tepi.filtertable.FilterGenerator;

import com.vaadin.data.Container.Filter;
import com.vaadin.data.Item;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.AbstractSelect.ItemCaptionMode;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Field;
import com.vaadin.ui.TextField;

import lombok.Getter;
import lombok.experimental.Accessors;
import r01f.locale.Language;
import r01f.util.types.Strings;
import x47b.application.X47BPanicButtonVaadinApp;
import x47b.model.org.X47BOrgDivisionServiceLocation;
import x47b.model.org.X47BWorkPlace;
import x47b.model.search.X47BSearchResultItemForPanicButtonOrganizationalEntity;

/**
 * Filtros para la tabla de entidades
 */
@Accessors(prefix="_")
public class X47BFilterGenerator 
  implements FilterGenerator {

	private static final long serialVersionUID = -278594865966300018L;
/////////////////////////////////////////////////////////////////////////////////////////
//  
/////////////////////////////////////////////////////////////////////////////////////////
	private ResourceBundle _bundle;
	private Language _lang;
	
	@Getter private TextField _deleteFilters;
	@Getter private ComboBox _entityTypeBox;
	@Getter private TextField _locationText;
	@Getter private ComboBox _alarmsBox;
	@Getter private TextField _workPlaceText;
	@Getter private TextField _phonesText;
	@Getter private TextField _emailsText;
	
/////////////////////////////////////////////////////////////////////////////////////////
//  
/////////////////////////////////////////////////////////////////////////////////////////
    public X47BFilterGenerator(final Language lang,
    						   final ResourceBundle bundle) {
		super();
		_bundle = bundle;
		_lang = lang;
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
    public Filter generateFilter(final Object propertyId,final Object value) {
    	
    	//Filtro columa TIPO de entidad (Localidad, puesto de trabajo); 
        if (X47BPanicButtonVaadinApp.TYPE_COLUMN.equals(propertyId)) {        	  
        	  final Class filter = (Class)value;
        	  return new Filter() {                
                  @Override
                  public boolean passesFilter(Object itemId, Item item) throws UnsupportedOperationException {
                	  /*
                	    if (filter == Object.class) {
                	    	return true;                	                    	    
                	    } else if (filter == ((X47BSearchResultItemForEntityModelObject) itemId).getModelObjectType()) {
                	    	return true;
                	    }                  	    
                	    return false;
                	   */
                	  return true;
                  }

                  @Override
                  public boolean appliesToProperty(Object propertyId) {
                          return false;
                  }
        	  };
        // Filtro Número de alarmas	  
        } else if (X47BPanicButtonVaadinApp.ALARM_COLUMN.equals(propertyId)) {          	
         	  final String filter = (String) value;
  	      	  
	      	  return new Filter() {                
	                @Override
	                public boolean passesFilter(Object itemId, Item item) throws UnsupportedOperationException {
	                	/*
	                	if (Strings.isNOTNullOrEmpty(filter)) {
	                		String nAlarms =  String.valueOf(((X47BSearchResultItemForEntityModelObject) itemId).getAlarmRaiseCount());              		
	                		if (Strings.isNOTNullOrEmpty(nAlarms)) {
	                			if (nAlarms.equals(filter)) {
	                				return true;
	                			}
	                		}	                		
	                	} else {
	                		return true;
	                	}	                			                		                			                			                         	   
	              	    return false;
	              	    */
	                	
	                	return true;
	                }
	
	                @Override
	                public boolean appliesToProperty(Object propertyId) {
	                        return false;
	                }
	      	  };        	
        	/*
      	  	final Integer filter = (Integer) value;
      	  	return new Filter() {                
                @Override
                public boolean passesFilter(Object itemId, Item item) throws UnsupportedOperationException {                	
                	if (filter == 0) {
                		//No hay filtros                	
                		return true;
                	} else {
                		//Hay filtros solo se muestran entidades con alarmas
    					if (((X47BSearchResultItemForEntityModelObject) itemId).getLastAlarmRaiseDate() != null) {
    						//Comprueba si tiene hay alarmas en la última media hora
    						Calendar calLastAlarm = Calendar.getInstance();
    						Calendar calDateToCompare = Calendar.getInstance();    						
    						calLastAlarm.setTime(((X47BSearchResultItemForEntityModelObject) itemId).getLastAlarmRaiseDate());
    						
    						//Establece el filtro en los últimos minutos
    						calDateToCompare.add(Calendar.MINUTE, filter);
    	                	
    						//Comprueba si ha habido alarma en los últimos minutos indicados en el filtro
    						if (calLastAlarm.after(calDateToCompare)) {							
    							return true;					
    						} else {
    							return false;
    						}
    					} else {
    						return false;
    					}                		
                		
                	}															              	                   		                	 	
                }

                @Override
                public boolean appliesToProperty(Object propertyId) {
                        return false;
                }
      	  	};
      	  	*/	        
	    //Filtro columa LOCATION de entidad (Localidad, puesto de trabajo);   	  
        } else if (X47BPanicButtonVaadinApp.LOCATION_COLUMN.equals(propertyId)) {
	      	  final String filter = (String) value;
	      	  	      	  
	      	  return new Filter() {                
	                @Override
	                public boolean passesFilter(Object itemId, Item item) throws UnsupportedOperationException {	                		
	                	if (Strings.isNOTNullOrEmpty(filter)) {
	                		String location =  ((X47BSearchResultItemForPanicButtonOrganizationalEntity) itemId).getLocation().getName();	                		
	                		if (Strings.isNOTNullOrEmpty(location)) {
	                			if (location.toLowerCase().indexOf(filter.toLowerCase()) != -1) {
	                				return true;
	                			}	                			
	                		}	                		
	                	} else {
	                		return true;
	                	}	                			                		                			                			                         	   
	              	    return true;                		                	 	 
	                }
	
	                @Override
	                public boolean appliesToProperty(Object propertyId) {
	                        return false;
	                }
	      	  };
	      	//Filtro columa puesto de trabajo de entidad (Localidad, puesto de trabajo);   	    	  
        }  else if (X47BPanicButtonVaadinApp.WORKPLACE_COLUMN.equals(propertyId)) {
	      	  final String filter = (String) value;
	  	      	  
	      	  return new Filter() {                
	                @Override
	                public boolean passesFilter(Object itemId, Item item) throws UnsupportedOperationException {	                		
	                	if (Strings.isNOTNullOrEmpty(filter)) {
	                		String workPlace =  ((X47BSearchResultItemForPanicButtonOrganizationalEntity) itemId).getWorkPlace().getName();	                		
	                		if (Strings.isNOTNullOrEmpty(workPlace)) {
	                			if (workPlace.toLowerCase().indexOf(filter.toLowerCase()) != -1) {
	                				return true;
	                			}
	                		}	                		
	                	} else {
	                		return true;
	                	}	                			                		                			                			                         	   
	              	    return true;                		                	 	 
	                }
	
	                @Override
	                public boolean appliesToProperty(Object propertyId) {
	                        return false;
	                }
	      	  };
        }
     	
        
        // For other properties, use the default filter
        return null;
    }

    @Override
    public Filter generateFilter(Object propertyId, Field<?> originatingField) {               
        return null;
    }

    @Override
    public AbstractField<?> getCustomFilterComponent(Object propertyId) {
        
        //Combo para el filtro TYPO de entidad
        if (X47BPanicButtonVaadinApp.INDEX_ROW_COLUMN.equals(propertyId)) {     
           	if (_deleteFilters == null) {
           		_deleteFilters= new TextField();
           		_deleteFilters.setEnabled(false);
        	}
        	return _deleteFilters;       	
        //Combo para el filtro TYPO de entidad
        } else if (X47BPanicButtonVaadinApp.TYPE_COLUMN.equals(propertyId)) {     
        	
        	if (_entityTypeBox == null) {        	
	            _entityTypeBox = new ComboBox();
	                      
	            _entityTypeBox.setItemCaptionMode(ItemCaptionMode.ID);
	            _entityTypeBox.addContainerProperty("name", String.class, "0");
	            _entityTypeBox.setItemCaptionPropertyId("name");
	                        
	            _entityTypeBox.setInvalidAllowed(false);
	            _entityTypeBox.setNullSelectionAllowed(false);
	            
	            Item item;
	            item = _entityTypeBox.addItem(Object.class);
	            item.getItemProperty("name").setValue(_bundle.getString("x47b.all"));
	            item = _entityTypeBox.addItem(X47BOrgDivisionServiceLocation.class);
	            item.getItemProperty("name").setValue(_bundle.getString("x47b.location"));            
	            item = _entityTypeBox.addItem(X47BWorkPlace.class);
	            item.getItemProperty("name").setValue(_bundle.getString("x47b.workPlace"));
	            _entityTypeBox.setValue(Object.class);
        	}    
                         
            return _entityTypeBox;
        } else if (X47BPanicButtonVaadinApp.ALARM_COLUMN.equals(propertyId)) {      
        	
        	if (_alarmsBox == null) {
        		_alarmsBox = new ComboBox();
        		_alarmsBox.setImmediate(true);
                _alarmsBox.setItemCaptionMode(ItemCaptionMode.ID);
                _alarmsBox.addContainerProperty("name", String.class, _bundle.getString("x47b.all.a"));
                _alarmsBox.setItemCaptionPropertyId("name");
                            
                _alarmsBox.setInvalidAllowed(false);
                _alarmsBox.setNullSelectionAllowed(false);
                
                Item item;
                item = _alarmsBox.addItem(new Integer(0));
                item.getItemProperty("name").setValue(_bundle.getString("x47b.all.a"));
                item = _alarmsBox.addItem(new Integer(-30));
                item.getItemProperty("name").setValue(_bundle.getString("x47b.last30"));            
                item = _alarmsBox.addItem(new Integer(-1440));
                item.getItemProperty("name").setValue(_bundle.getString("x47b.lastDay"));
                _alarmsBox.setValue(new Integer(-1440));
        		
        	}
        	return _alarmsBox;
        	/*
            ComboBox 
                         
            return box;
            */
        } else if (X47BPanicButtonVaadinApp.LOCATION_COLUMN.equals(propertyId)) {   
           	if (_locationText == null) {
        		_locationText= new TextField();
        		_locationText.setImmediate(true);
        	}
        	return _locationText;
        	
        } else if (X47BPanicButtonVaadinApp.WORKPLACE_COLUMN.equals(propertyId)) {   
           	if (_workPlaceText == null) {
        		_workPlaceText= new TextField();
        		_locationText.setImmediate(true);
        	}
        	return _workPlaceText;
        	
        } else if (X47BPanicButtonVaadinApp.PHONES_COLUMN.equals(propertyId)) {   
           	if (_phonesText == null) {
        		_phonesText= new TextField();
        		_phonesText.setEnabled(false);
        	}
        	return _phonesText;
        	
        } else if (X47BPanicButtonVaadinApp.EMAILS_COLUMN.equals(propertyId)) {   
           	if (_emailsText == null) {
        		_emailsText= new TextField();
        		_emailsText.setEnabled(false);
        	}
        	return _emailsText;
        	
        }
        return null;
    }

    @Override
    public void filterRemoved(Object propertyId) {
    	/*
        Notification n = new Notification("Filter removed from: " + propertyId,
                Notification.Type.TRAY_NOTIFICATION);
        n.setDelayMsec(800);
        n.show(Page.getCurrent());
        */
    }

    @Override
    public void filterAdded(Object propertyId,
            Class<? extends Filter> filterType, Object value) {
    	/*
        Notification n = new Notification("Filter added to: " + propertyId,
                Notification.Type.TRAY_NOTIFICATION);
        n.setDelayMsec(800);
        n.show(Page.getCurrent());
        */
    }

    @Override
    public Filter filterGeneratorFailed(Exception reason, Object propertyId,
            Object value) {        
        return null;
    }
}
