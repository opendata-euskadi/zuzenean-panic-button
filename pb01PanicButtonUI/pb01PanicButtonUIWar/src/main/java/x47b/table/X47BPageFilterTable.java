package x47b.table;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;

import org.tepi.filtertable.paged.PagedFilterTable;
import org.tepi.filtertable.paged.PagedTableChangeEvent;

import com.vaadin.data.Container;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.converter.StringToIntegerConverter;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table.ColumnHeaderMode;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.Reindeer;

import lombok.Getter;
import lombok.Setter;
import r01f.util.types.Strings;
import x47b.application.X47BPanicButtonVaadinApp;

public class X47BPageFilterTable<T extends Container.Indexed & Container.Filterable & Container.ItemSetChangeNotifier> 
	 extends PagedFilterTable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5695684200000312128L;
	
	ResourceBundle _bundle = ((X47BPanicButtonVaadinApp) UI.getCurrent()).getBundle();
	
	public static enum PaginationTypes {MANUAL, CUSTOM};
	
	 /**
     * Holds alternative text headers for visible columns (by propertyId).
     */
	private HashMap<Object, String> columnHeaderAlt = new HashMap<Object, String>();	
    /**
     * Holds icons for visible columns (by propertyId).
     */
    private final HashMap<Object, String> columnIcons = new HashMap<Object, String>();
	

	/*************************************
	 * MENBERS
	 *************************************/  
	@Getter @Setter PaginationTypes pagination = PaginationTypes.MANUAL; 
	
	/*************************************
	 * FIELDS
	 *************************************/
	@Getter ComboBox itemsPerPageSelect;
	@Getter Label totalPagesLabel;
	@Getter TextField currentPageTextField;
	@Getter Button first;
	@Getter Button previous;
	@Getter Button next;
	@Getter Button last;
		
    public HorizontalLayout createControls(X47BPagedFilterControlConfig config) {
         
        /*************************************
         * ITEMS PER PAGE COMBO
         *************************************/      
        Label itemsPerPageLabel = new Label(config.getItemsPerPage(),
                ContentMode.HTML);
        itemsPerPageLabel.setSizeUndefined();              
        itemsPerPageSelect = new ComboBox();
        
        for (Integer i : config.getPageLengths()) {
            itemsPerPageSelect.addItem(i);
            itemsPerPageSelect.setItemCaption(i, String.valueOf(i));
        }
        
        itemsPerPageSelect.setValue(new Integer(config.initialItemsPerPage));
        
        itemsPerPageSelect.setImmediate(true);
        itemsPerPageSelect.setNullSelectionAllowed(false);
        itemsPerPageSelect.setWidth(null);        
        
        if (config.getItemsPerPageChangeListener() != null && pagination == PaginationTypes.CUSTOM) {
        	itemsPerPageSelect.addValueChangeListener(config.getItemsPerPageChangeListener());
        } else {	       
        	itemsPerPageSelect.addValueChangeListener(new ValueChangeListener() {           
        		@Override
        		public void valueChange(
                    com.vaadin.data.Property.ValueChangeEvent event) {
        			setPageLength((Integer) event.getProperty().getValue());
        		}
        	});        
        }
        
        if (pagination == PaginationTypes.MANUAL) {
	        //Establebece el número de elementos por página, con paginación manual del componente
	        if (itemsPerPageSelect.containsId(getPageLength())) {
	            itemsPerPageSelect.select(getPageLength());
	        } else {
	            itemsPerPageSelect.select(itemsPerPageSelect.getItemIds()
	                    .iterator().next());
	        }
        }    
        
        /*************************************
         * PAGE NUMBER PER TEXT FIELD
         *************************************/              
        Label pageLabel = new Label(config.getPage(), ContentMode.HTML);        
        currentPageTextField = new TextField();
        currentPageTextField.setValue(String.valueOf(getCurrentPage()));
        currentPageTextField.setConverter(new StringToIntegerConverter() {
            @Override
            protected NumberFormat getFormat(Locale locale) {
                NumberFormat result = super.getFormat(UI.getCurrent()
                        .getLocale());
                result.setGroupingUsed(false);
                return result;
            }
        });
        Label separatorLabel = new Label("&nbsp;/&nbsp;", ContentMode.HTML);
        totalPagesLabel = new Label();
        totalPagesLabel.setContentMode(ContentMode.HTML);
        currentPageTextField.setStyleName(Reindeer.TEXTFIELD_SMALL);
        currentPageTextField.setImmediate(true);
        
        if (config.getCurrentPageTextFieldListener() != null && pagination == PaginationTypes.CUSTOM) {
        	currentPageTextField.addValueChangeListener(config.getCurrentPageTextFieldListener());
        } else {	
	        currentPageTextField.addValueChangeListener(new ValueChangeListener() {            
	            @Override
	            public void valueChange(final com.vaadin.data.Property.ValueChangeEvent event) {
	                if (currentPageTextField.isValid()
	                        && currentPageTextField.getValue() != null) {
	                    int page = Integer.valueOf(String
	                            .valueOf(currentPageTextField.getValue()));
	                    setCurrentPage(page);
	                }
	            }
	        });
        }    
        
        pageLabel.setWidth(null);
        currentPageTextField.setColumns(3);
        separatorLabel.setWidth(null);
        totalPagesLabel.setWidth(null);

        /*************************************
         * PAGE CONTROSLS LAYOUT
         *************************************/      
        HorizontalLayout controlBar = new HorizontalLayout();
        HorizontalLayout pageSize = new HorizontalLayout();
        HorizontalLayout pageManagement = new HorizontalLayout();
        
        /*************************************
         * FIRST PAGE BUTTON
         *************************************/        
        first = new Button(config.getFirst());
        
        if (config.getFirstBtnClickListener() !=null && pagination == PaginationTypes.CUSTOM) {
        	first.addClickListener(config.getFirstBtnClickListener());        	
        } else {
        	first.addClickListener( new ClickListener() {               
                @Override
                public void buttonClick(ClickEvent event) {
                    setCurrentPage(0);
                }
            });
        }
         
        
        /*************************************
         * PREVIOUS PAGE BUTTON
         *************************************/               
        previous = new Button(config.getPrevious());
        
        if (config.getPrevBtnClickListener() !=null && pagination == PaginationTypes.CUSTOM) {
        	previous.addClickListener(config.getPrevBtnClickListener());        	
        } else {
        	previous.addClickListener( new ClickListener() {               
                @Override
                public void buttonClick(ClickEvent event) {
                    previousPage();
                }
            });
        }
        
        /*************************************
         * NEXT PAGE BUTTON
         *************************************/       
        next = new Button(config.getNext());
                
        if (config.getNextBtnClickListener() !=null && pagination == PaginationTypes.CUSTOM) {
        	next.addClickListener(config.getNextBtnClickListener());        	
        } else {
        	next.addClickListener( new ClickListener() {                
                @Override
                public void buttonClick(ClickEvent event) {
                    nextPage();
                }
            });
        }

        /*************************************
         * LAST PAGE BUTTON
         *************************************/              
        last = new Button(config.getLast());
        
        if (config.getLastBtnClickListener() !=null && pagination == PaginationTypes.CUSTOM) {
        	last.addClickListener(config.getLastBtnClickListener());        	
        } else {
        	last.addClickListener( new ClickListener() {               
                @Override
                public void buttonClick(ClickEvent event) {
                	setCurrentPage(getTotalAmountOfPages());
                }
            });
        }        
               
        first.setStyleName(Reindeer.BUTTON_LINK);
        previous.setStyleName(Reindeer.BUTTON_LINK);
        next.setStyleName(Reindeer.BUTTON_LINK);
        last.setStyleName(Reindeer.BUTTON_LINK);

        itemsPerPageLabel.addStyleName("pagedtable-itemsperpagecaption");
        itemsPerPageSelect.addStyleName("pagedtable-itemsperpagecombobox");
        pageLabel.addStyleName("pagedtable-pagecaption");
        currentPageTextField.addStyleName("pagedtable-pagefield");
        separatorLabel.addStyleName("pagedtable-separator");
        totalPagesLabel.addStyleName("pagedtable-total");
        first.addStyleName("pagedtable-first");
        previous.addStyleName("pagedtable-previous");
        next.addStyleName("pagedtable-next");
        last.addStyleName("pagedtable-last");

        itemsPerPageLabel.addStyleName("pagedtable-label");
        itemsPerPageSelect.addStyleName("pagedtable-combobox");
        pageLabel.addStyleName("pagedtable-label");
        currentPageTextField.addStyleName("pagedtable-label");
        separatorLabel.addStyleName("pagedtable-label");
        totalPagesLabel.addStyleName("pagedtable-label");
        first.addStyleName("pagedtable-button");
        previous.addStyleName("pagedtable-button");
        next.addStyleName("pagedtable-button");
        last.addStyleName("pagedtable-button");

        
        pageSize.addComponent(itemsPerPageLabel);
        pageSize.addComponent(itemsPerPageSelect);
        pageSize.setComponentAlignment(itemsPerPageLabel, Alignment.MIDDLE_LEFT);
        pageSize.setComponentAlignment(itemsPerPageSelect, Alignment.MIDDLE_LEFT);
        pageSize.setSpacing(true);
        pageManagement.addComponent(first);
        pageManagement.addComponent(previous);
        pageManagement.addComponent(pageLabel);
        pageManagement.addComponent(currentPageTextField);
        pageManagement.addComponent(separatorLabel);
        pageManagement.addComponent(totalPagesLabel);
        pageManagement.addComponent(next);
        pageManagement.addComponent(last);        
        pageManagement.setComponentAlignment(first, Alignment.MIDDLE_LEFT);
        pageManagement.setComponentAlignment(previous, Alignment.MIDDLE_LEFT);
        pageManagement.setComponentAlignment(pageLabel, Alignment.MIDDLE_LEFT);
        pageManagement.setComponentAlignment(currentPageTextField, Alignment.MIDDLE_LEFT);
        pageManagement.setComponentAlignment(separatorLabel, Alignment.MIDDLE_LEFT);
        pageManagement.setComponentAlignment(totalPagesLabel, Alignment.MIDDLE_LEFT);
        pageManagement.setComponentAlignment(next, Alignment.MIDDLE_LEFT);
        pageManagement.setComponentAlignment(last, Alignment.MIDDLE_LEFT);
        pageManagement.setWidth(null);
        pageManagement.setSpacing(true);
        controlBar.setSpacing(true);
        controlBar.addComponent(pageSize);
        controlBar.addComponent(pageManagement);
        controlBar.setComponentAlignment(pageManagement, Alignment.MIDDLE_CENTER);              
        controlBar.setWidth(100, Unit.PERCENTAGE);
        controlBar.setExpandRatio(pageSize, 1);

        
        if (pagination == PaginationTypes.MANUAL) {        
	        if (getContainerDataSource() != null) {
	            first.setEnabled(getContainerDataSource().getStartIndex() > 0);
	            previous.setEnabled(getContainerDataSource().getStartIndex() > 0);
	            next.setEnabled(getContainerDataSource().getStartIndex() <getContainerDataSource().getRealSize()
	                    - getPageLength());
	            last.setEnabled(getContainerDataSource().getStartIndex() < getContainerDataSource().getRealSize()
	                    - getPageLength());
	        }
	        
	        
		        addListener(new PageChangeListener() {
		            private boolean inMiddleOfValueChange;
		
		            @Override
		            public void pageChanged(PagedTableChangeEvent event) {
		                if (!inMiddleOfValueChange) {
		                    inMiddleOfValueChange = true;
		                    
		                    first.setEnabled(getContainerDataSource().getStartIndex() > 0);
		                    previous.setEnabled(getContainerDataSource().getStartIndex() > 0);
		                    next.setEnabled(getContainerDataSource().getStartIndex() < getContainerDataSource()
		                            .getRealSize() - getPageLength());
		                    last.setEnabled(getContainerDataSource().getStartIndex() < getContainerDataSource()
		                            .getRealSize() - getPageLength());
		                    
		                    
		                    
		                    currentPageTextField.setValue(String
		                            .valueOf(getCurrentPage()));
		                    totalPagesLabel.setValue(Integer
		                            .toString(getTotalAmountOfPages()));
		                    itemsPerPageSelect
		                            .setValue(getPageLength());
		                    inMiddleOfValueChange = false;
		                    
		                }
		            }
		        });
	        
	        }
        
       
        
        return controlBar;
    }
    

	/**
	 * Set the alernative text for a column header by propetyId
	 * @param propertyId
	 * @param textAlt
	 */
	public void setColumnHeaderAlt(Object propertyId, String textAlt) {
		this.columnHeaderAlt.put(propertyId, textAlt);
	}
	
	/**
	 * Set the icon for a column header by propetyId
	 * @param propertyId
	 * @param textAlt
	 */
	public void setColumnIcon(Object propertyId, String icon) {
		this.columnIcons.put(propertyId, icon);
	}
    	
    
    @Override
    public String getColumnHeader(Object propertyId) {
    	String columHeader = null;
    	
        if (getColumnHeaderMode() == ColumnHeaderMode.HIDDEN) {
            return null;
        }

        String header = super.getColumnHeader(propertyId);
        if ((header == null && getColumnHeaderMode() == ColumnHeaderMode.EXPLICIT_DEFAULTS_ID)
                || getColumnHeaderMode() == ColumnHeaderMode.ID) {
            header = propertyId.toString();
        }
                      
        if (this.columnIcons.containsKey(propertyId) &&  Strings.isNOTNullOrEmpty(this.columnIcons.get(propertyId))) {
        	 if (this.columnHeaderAlt.containsKey(propertyId) &&  Strings.isNOTNullOrEmpty(this.columnHeaderAlt.get(propertyId))) {
        		 columHeader =  "<img class='v-icon' src='/x47bPanicButtonWar/VAADIN/themes/x47breindeer/"+this.columnIcons.get(propertyId)+"' title='"+this.columnHeaderAlt.get(propertyId)+"'>";
        	 } else {
        		 columHeader =  "<img class='v-icon' src='/x47bPanicButtonWar/VAADIN/themes/x47breindeer/"+this.columnIcons.get(propertyId)+"'>";
        	 }
        }      
        
        if (Strings.isNOTNullOrEmpty(header)) {
        	if (columHeader == null) columHeader = "";
	        if (this.columnHeaderAlt.containsKey(propertyId) &&  Strings.isNOTNullOrEmpty(this.columnHeaderAlt.get(propertyId))) {	        	
	        	columHeader += "<div title='"+this.columnHeaderAlt.get(propertyId)+"'>" + header + "</div>";
	        } else {	        	
	        	columHeader += "<div>" + header + "</div>";
	        }
        }    
        
        return columHeader;
        
    }    
  
  

}
