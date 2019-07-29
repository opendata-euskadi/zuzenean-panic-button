package x47b.table.filters;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import org.tepi.filtertable.FilterDecorator;
import org.tepi.filtertable.numberfilter.NumberFilterPopupConfig;

import com.vaadin.server.Resource;
import com.vaadin.shared.ui.datefield.Resolution;

import r01f.locale.Language;

@SuppressWarnings("serial")
public class X47BFilterDecorator 
  implements FilterDecorator, 
  			 Serializable {
	
	ResourceBundle _bundle;
	Language _lang;

	
	
    public X47BFilterDecorator(Language lang, ResourceBundle bundle) {
		super();
		this._bundle = bundle;
		this._lang = lang;
	}

	@Override
    public String getEnumFilterDisplayName(Object propertyId, Object value) {               
        return null;
    }

    @Override
    public Resource getEnumFilterIcon(Object propertyId, Object value) {      
        return null;
    }

    @Override
    public String getBooleanFilterDisplayName(Object propertyId, boolean value) {             
        return null;
    }

    @Override
    public Resource getBooleanFilterIcon(Object propertyId, boolean value) {    	    	
        return null;
    }

    @Override
    public String getFromCaption() {
        return "";
    }

    @Override
    public String getToCaption() {
        return "";
    }

    @Override
    public String getSetCaption() {
        // use default caption
        return null;
    }

    @Override
    public String getClearCaption() {        
        return null;
    }

    @Override
    public boolean isTextFilterImmediate(Object propertyId) {        
        return true;
    }

    @Override
    public int getTextChangeTimeout(Object propertyId) {       
        return 500;
    }

    @Override
    public String getAllItemsVisibleString() {
        return "";
    }

    @Override
    public Resolution getDateFieldResolution(Object propertyId) {
        return Resolution.DAY;
    }

    public DateFormat getDateFormat(Object propertyId) {
        return DateFormat.getDateInstance(DateFormat.SHORT, new Locale("es","ES"));
    }

    @Override
    public boolean usePopupForNumericProperty(Object propertyId) {        
        return true;
    }

    @Override
    public String getDateFormatPattern(Object propertyId) {       
        return null;
    }

    @Override
    public Locale getLocale() {       
        return null;
    }

    @Override
    public NumberFilterPopupConfig getNumberFilterPopupConfig() {       
        return null;
    }
}
