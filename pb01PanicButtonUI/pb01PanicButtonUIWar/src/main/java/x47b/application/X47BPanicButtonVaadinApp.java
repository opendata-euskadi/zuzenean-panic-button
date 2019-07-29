package x47b.application;

import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.vaadin.annotations.Theme;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.AbstractSelect;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomTable;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table.Align;
import com.vaadin.ui.Table.HeaderClickEvent;
import com.vaadin.ui.Table.HeaderClickListener;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window.CloseEvent;
import com.vaadin.ui.Window.CloseListener;

import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import r01f.locale.Language;
import r01f.model.search.SearchResults;
import r01f.util.types.Strings;
import r01f.util.types.locale.Languages;
import x47b.components.X47BAlarmsWindow;
import x47b.components.X47BEntityWindow;
import x47b.components.X47BLocationIDComponent;
import x47b.forms.X47BEntityObjectForm;
import x47b.forms.X47BLocationForm;
import x47b.forms.X47BOrganizationForm;
import x47b.forms.X47BWorkPlaceForm;
import x47b.model.X47BAlarmEvent;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceLocationOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrganizationOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BWorkPlaceOID;
import x47b.model.org.X47BOrgDivisionServiceLocation;
import x47b.model.org.X47BOrganization;
import x47b.model.org.X47BWorkPlace;
import x47b.model.org.summaries.X47BSummarizedOrgDivisionServiceLocation;
import x47b.model.org.summaries.X47BSummarizedOrganization;
import x47b.model.org.summaries.X47BSummarizedWorkPlace;
import x47b.model.search.X47BSearchFilterForPanicButtonOrganizationalEntity;
import x47b.model.search.X47BSearchResultItemForPanicButtonOrganizationalEntity;
import x47b.services.X47BAPIHelper;
import x47b.table.X47BPageFilterTable;
import x47b.table.X47BPagedFilterControlConfig;
import x47b.table.filters.X47BFilterDecorator;
import x47b.table.filters.X47BFilterGenerator;

@Theme("x47breindeer")
@Slf4j
@Accessors(prefix="_")
public class X47BPanicButtonVaadinApp 
     extends UI {

	private static final long serialVersionUID = 8694302433864968565L;
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTANTS
/////////////////////////////////////////////////////////////////////////////////////////
	public static final String INDEX_ROW_COLUMN = "_organization";
	public static final String TYPE_COLUMN = "type";
	public static final String ALARM_COLUMN = "_alarmRaiseCount";
	public static final String LOCATION_COLUMN = "_location";
	public static final String WORKPLACE_COLUMN = "_workPlace";
	public static final String PHONES_COLUMN = "phones";
	public static final String EMAILS_COLUMN = "emails";
	
	public static final String REMOVE_FILTER_ICON = "images/r01_remove_filter.png";
	public static final String ALARM_ICON = "images/x47b_ico_alarma1.gif";
	public static final String ORGANIZATION_ICON = "images/x47b_ico_org.gif";
	public static final String LOCATION_ICON = "images/x47b_ico_loc.gif";
	public static final String WORKPLACE_ICON = "images/x47b_ico_agente1.gif";
/////////////////////////////////////////////////////////////////////////////////////////
//  FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	@Getter private ResourceBundle _bundle;
	
			private Language _lang = Language.SPANISH;
	
			private X47BAPIHelper _api;
	
			//Página actual
			private int _currentPage = 1;
			//Última página
			private int _lastPage = 0;
			//Número de registros por página de la tabla
			private int _itemsPerPage = 10;
			//Número de resultados totales
			private long _totalItemsCount = 0;
			//Índice para pintar las filas de la tabla
			private long _currentIndexRow=0;
			//Flag para evitar que se disparen los eventos al limpiar los filtros
			private boolean _eventsEnabled = true;
	

			private X47BPagedFilterControlConfig _pagedFilterTableconfig;	
			private X47BFilterGenerator _filterGenerator;
	

			private X47BPageFilterTable<IndexedContainer> _pagedFilterTable;
			private ComboBox _cboOrganizations;
			private ComboBox _cboLocations;
			private ComboBox _cboWorkPlaces;
			private Button _btnNewWorkPlace;
			private Button _btnNewLocation;
			private Button _btnEditOrganization;
			private Button _btnEditLocation;
			private Button _btnEditWorkPlace;
/////////////////////////////////////////////////////////////////////////////////////////
//	METHODS
/////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Initialize application view.
	 */
	@Override
	protected void init(final VaadinRequest request) {	
		
		String lang = "es";
		
		if (Strings.isNOTNullOrEmpty(request.getParameter("lang"))) {
			lang = request.getParameter("lang");
		}
		setLocale(new Locale(lang, "ES"));
		_lang = Languages.of(getLocale());		
		
		
		_loadLangResources();
		_api = X47BAPIHelper.getInstance();
								
		VerticalLayout mainLayout = new VerticalLayout();
		mainLayout.setSpacing(true);
		mainLayout.setMargin(true);			
		
		//mainLayout.addComponent(logo);
		mainLayout.addComponent(_buildHead());			
		
		
		_buildPagedFilterTable();											
		_composePagedFilterControlConfig();
		
		mainLayout.addComponent(_pagedFilterTable);				
		mainLayout.addComponent(_pagedFilterTable.createControls(_pagedFilterTableconfig));
		
		//Si el combo no está vacio mostramos las entidades de la primera organización
		if (!_cboOrganizations.isEmpty()) {											
				_reloadTable(true);					
		} else {
			//No hay organizaciones, no se carga la tabla
			_pagedFilterTable.setContainerDataSource(new BeanItemContainer<X47BSearchResultItemForPanicButtonOrganizationalEntity>(X47BSearchResultItemForPanicButtonOrganizationalEntity.class));
			_setTableVisibleColumns();			
		}					
						
		_composeFilterEvents();						
		log.info("X47B >>> Showing application view ...");	
		
		setContent(mainLayout);
	}

/////////////////////////////////////////////////////////////////////////////////////////
//	PRIVATE METHODS
/////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Populate table with data
	 * @return the table
	 */
	private void _buildPagedFilterTable() {
		log.info("X47B >>> Building entities table ...");
		
		_pagedFilterTable = new X47BPageFilterTable<IndexedContainer>() {						
			protected String formatPropertyValue(final Object rowId,final  Object colId, 
												 final com.vaadin.data.Property<?> property) {
				//Tratamiento de las columnas PHONES y EMAILS		
	            if ( colId instanceof String ) {
	            	X47BSearchResultItemForPanicButtonOrganizationalEntity resultItem = (X47BSearchResultItemForPanicButtonOrganizationalEntity) rowId;
	            	if (((String) colId).equalsIgnoreCase(TYPE_COLUMN) ) {	            		
	            	} else if (((String) colId).equalsIgnoreCase(PHONES_COLUMN) ) {
	                	return _composeContactText(resultItem.getPhones());
		            } else if (((String) colId).equalsIgnoreCase(EMAILS_COLUMN) ) {
		            	return _composeContactText(resultItem.getEmails());
		            }
	            }
                return super.formatPropertyValue(rowId, colId, property);
			};
		};
				
		
		_pagedFilterTable.setPagination(X47BPageFilterTable.PaginationTypes.CUSTOM);
		
		_pagedFilterTable.setPageLength(_itemsPerPage);
		_pagedFilterTable.setSizeFull();		
		_pagedFilterTable.setLocale(getLocale());
		_pagedFilterTable.setFilterDecorator(new X47BFilterDecorator(_lang, _bundle));
		_filterGenerator = new X47BFilterGenerator(_lang, _bundle);
		_pagedFilterTable.setFilterBarVisible(true);
		_pagedFilterTable.setFilterGenerator(_filterGenerator);
		//_pagedFilterTable.setFooterVisible(true);		
		_pagedFilterTable.setSelectable(true);
		_pagedFilterTable.setImmediate(true);
		_pagedFilterTable.setMultiSelect(false);
		//_pagedFilterTable.setRowHeaderMode(RowHeaderMode.INDEX);
		_pagedFilterTable.setFilterOnDemand(true);
		
		_pagedFilterTable.setColumnCollapsingAllowed(true);		
		_pagedFilterTable.setColumnReorderingAllowed(true);
		_pagedFilterTable.addStyleName("x47bentitytable");
		//Columna INDEX
		_pagedFilterTable.addGeneratedColumn(INDEX_ROW_COLUMN, 
											 new CustomTable.ColumnGenerator() {						
			@Override
			public Object generateCell(CustomTable source, Object itemId, Object columnId) {
				final X47BSearchResultItemForPanicButtonOrganizationalEntity resultItem = (X47BSearchResultItemForPanicButtonOrganizationalEntity) itemId;											
				_currentIndexRow++;								
				// TODO Auto-generated method stub
				return _currentIndexRow;
			}
		});		
				
		//Columna TYPO ENTIDAD
		_pagedFilterTable.addGeneratedColumn(TYPE_COLUMN, 
											 new CustomTable.ColumnGenerator() {						
			@Override
			public Object generateCell(CustomTable source, Object itemId, Object columnId) {
				final X47BSearchResultItemForPanicButtonOrganizationalEntity resultItem = (X47BSearchResultItemForPanicButtonOrganizationalEntity) itemId;							
				String resourceTypeName = null;
				
				String altText = "";
				if (resultItem.getModelObjectType() == X47BWorkPlace.class) {
					resourceTypeName = WORKPLACE_ICON;
					altText = _bundle.getString("x47b.workPlace");
				} else {
					resourceTypeName = LOCATION_ICON;
					altText = _bundle.getString("x47b.location");
				}
				
				Image entityIcon = new Image("", new ThemeResource(resourceTypeName));				
				entityIcon.setAlternateText(altText);	
				
				return entityIcon;
			}
		});		

		//Columna ALARMA		
		_pagedFilterTable.addGeneratedColumn(ALARM_COLUMN, 
											 new CustomTable.ColumnGenerator() {
			@Override
			public Object generateCell(final CustomTable source,
									   final Object itemId,final Object columnId) {
				
				final X47BSearchResultItemForPanicButtonOrganizationalEntity resultItem = (X47BSearchResultItemForPanicButtonOrganizationalEntity) itemId;															
				final long nalarms = resultItem.getAlarmRaiseCount();				
												
				// Solo se pinta la columna si tiene alarmas
				if (nalarms > 0) {												
					Button edit = new Button(String.valueOf(nalarms));

					edit.setStyleName("link");
					edit.addStyleName("entityTableButton");

					//Comprueba si tiene hay alarmas en la última media hora
					Calendar calLastAlarm = Calendar.getInstance(getLocale());
					Calendar calDateToCompare = Calendar.getInstance(getLocale());
					
					calLastAlarm.setTime(((X47BSearchResultItemForPanicButtonOrganizationalEntity) itemId).getLastAlarmRaiseDate());
					
					//Última media hora
					calDateToCompare.add(Calendar.MINUTE, ((Integer) _filterGenerator.getAlarmsBox().getValue()).intValue());
					
					if (calLastAlarm.after(calDateToCompare)) {							
						edit.addStyleName("x47balarm-link-active");						
					}											
					
					//Evento click del número de alarmas en la tabla
					edit.addClickListener(new ClickListener() {
												@Override
												public void buttonClick(ClickEvent event) {								
													//Visualiza las alarmas
													Collection<X47BAlarmEvent> alarms = _api.getAlarms(resultItem);
													
													String alarmCaption = "";
													if (resultItem.getModelObjectType() == X47BWorkPlace.class) {
														alarmCaption = resultItem.getWorkPlace().getName();
														alarmCaption += " ("+resultItem.getLocation().getName()+")";
													} else if (resultItem.getModelObjectType() == X47BOrgDivisionServiceLocation.class) {
														alarmCaption = resultItem.getLocation().getName();
													}
													
													
													final X47BAlarmsWindow wAlarm = new X47BAlarmsWindow(alarms);								
													wAlarm.setStyleName("x47bentityWindow");
													wAlarm.setIcon(new ThemeResource(ALARM_ICON));
													wAlarm.setCaption(alarmCaption);
													wAlarm.setWidth(800, Unit.PIXELS);
													wAlarm.setHeight(600, Unit.PIXELS);
													wAlarm.center();
													wAlarm.setModal(true);
													wAlarm.setResizable(true);					
													getUI().addWindow(wAlarm);															
												}
											});
					return edit;
				} else {
					return null;
				}									
			}
		});
						
		
		//Columna LOCALIZACIÓN
		_pagedFilterTable.addGeneratedColumn(LOCATION_COLUMN, 
											 new CustomTable.ColumnGenerator() {
			@Override
			public Object generateCell(CustomTable source, Object itemId, Object columnId) {
				final X47BSearchResultItemForPanicButtonOrganizationalEntity resultItem = (X47BSearchResultItemForPanicButtonOrganizationalEntity) itemId;												
				String name = resultItem.getLocation().getName();				
				
				//Trata el caso de llegar nombre vacio
				if (Strings.isNullOrEmpty(name)) name = "";	  
				
				Button edit = new Button(name);
				edit.setStyleName("link");
				edit.addStyleName("entityTableButton");				
				
				//Evento click de la LOCALIZACIÓN en la tabla. 
				//Edita la localización
				edit.addClickListener(
						new ClickListener() {
							@Override
							public void buttonClick(final ClickEvent event) {	
								GridLayout fLayout = new GridLayout(2, 5);
								X47BLocationForm x = new X47BLocationForm(fLayout, _api);
								x.setValue(resultItem.getLocation().getOid());
								final X47BEntityWindow w = _composeEntityWindow(x, 350, LOCATION_ICON);
													
								w.setCaption(Strings.customized(_bundle.getString("x47b.msg.location.window.caption"), "\""+resultItem.getLocation().getName()+"\""));			
								w.addCloseListener(new CloseListener() {					
														@Override
														public void windowClose(final CloseEvent e) {								
															_closeEntityUpdateWindowEventActions(w);
														}
													});
							}
						});

				// TODO Auto-generated method stub
				return edit;
			}
		});
		
		//Columna WORKPLACE
		_pagedFilterTable.addGeneratedColumn(WORKPLACE_COLUMN, 
											 new CustomTable.ColumnGenerator() {
			@Override
			public Object generateCell(CustomTable source, Object itemId,
					Object columnId) {
				final X47BSearchResultItemForPanicButtonOrganizationalEntity resultItem = (X47BSearchResultItemForPanicButtonOrganizationalEntity) itemId;

				if (resultItem.getModelObjectType() == X47BWorkPlace.class) {
					String name = resultItem.getWorkPlace().getName();
					//Trata el caso de llegar nombre vacio
					if (Strings.isNullOrEmpty(name)) name = "";	
					
					List<String> listIDs = Splitter.on('/')										
										.splitToList(resultItem.getHiearchyPath());
					
					//name = Strings.concat(name, " (", resultItem.getHiearchyPath(), ")");
					name = Strings.concat(name, " (",listIDs.get(2), ")");
					
					Button edit = new Button(name);
					edit.setStyleName("link");
					edit.addStyleName("entityTableButton");
					//Evento click del WORKPLACE en la tabla
					edit.addClickListener(new ClickListener() {
						@Override
						public void buttonClick(final ClickEvent event) {
							GridLayout fLayout = new GridLayout(2, 6);
							X47BWorkPlaceForm x = new X47BWorkPlaceForm(fLayout, _api);
							x.setValue(resultItem.getWorkPlace().getOid());
							final X47BEntityWindow w = _composeEntityWindow(x, 400, WORKPLACE_ICON);	
							w.setCaption(Strings.customized(_bundle.getString("x47b.msg.workPlace.window.caption"), "\""+resultItem.getWorkPlace().getName()+"\""));	
							w.addCloseListener(new CloseListener() {					
													@Override
													public void windowClose(final CloseEvent e) {
														_closeEntityUpdateWindowEventActions(w);
													}
												});
						}
					});
					return edit;
				} else {
					return null;
				}
			}
		});
		
		log.info("X47B >>> End building entities table.");		
	}
	
	/**
	 * Carga el fichero de literales
	 */
	private void _loadLangResources() {
		_bundle = ResourceBundle.getBundle("x47b.i18n.messages", getLocale());		
	}
	
	/**
	 * Establece las columnas visibles
	 */
	private void _setTableVisibleColumns() {						
		_pagedFilterTable.setVisibleColumns((Object[]) new String[] { INDEX_ROW_COLUMN, TYPE_COLUMN, ALARM_COLUMN, LOCATION_COLUMN, WORKPLACE_COLUMN, PHONES_COLUMN, EMAILS_COLUMN });
		
		_pagedFilterTable.setColumnHeader(INDEX_ROW_COLUMN, "");
		_pagedFilterTable.setColumnHeader(TYPE_COLUMN, _bundle.getString("x47b.type"));
		_pagedFilterTable.setColumnHeader(ALARM_COLUMN, _bundle.getString("x47b.alarms"));
		_pagedFilterTable.setColumnHeader(LOCATION_COLUMN, _bundle.getString("x47b.location"));
		_pagedFilterTable.setColumnHeader(WORKPLACE_COLUMN, _bundle.getString("x47b.workPlace"));
		_pagedFilterTable.setColumnHeader(PHONES_COLUMN,_bundle.getString("x47b.phones"));
		_pagedFilterTable.setColumnHeader(EMAILS_COLUMN, _bundle.getString("x47b.emails"));	

		_pagedFilterTable.setColumnHeaderAlt(INDEX_ROW_COLUMN,  _bundle.getString("x47b.filter.alt"));
		_pagedFilterTable.setColumnHeaderAlt(TYPE_COLUMN, _bundle.getString("x47b.type.alt"));
		_pagedFilterTable.setColumnHeaderAlt(ALARM_COLUMN, _bundle.getString("x47b.alarms.alt"));
		_pagedFilterTable.setColumnHeaderAlt(LOCATION_COLUMN, _bundle.getString("x47b.location.alt"));
		_pagedFilterTable.setColumnHeaderAlt(WORKPLACE_COLUMN, _bundle.getString("x47b.workPlace.alt"));
		_pagedFilterTable.setColumnHeaderAlt(PHONES_COLUMN,_bundle.getString("x47b.phones.alt"));
		_pagedFilterTable.setColumnHeaderAlt(EMAILS_COLUMN, _bundle.getString("x47b.emails.alt"));	
								
		_pagedFilterTable.setColumnIcon(INDEX_ROW_COLUMN, REMOVE_FILTER_ICON);		
		_pagedFilterTable.setColumnIcon(ALARM_COLUMN, ALARM_ICON);		
				
		_pagedFilterTable.setColumnAlignment(INDEX_ROW_COLUMN, Align.RIGHT);
		_pagedFilterTable.setColumnAlignment(TYPE_COLUMN, Align.CENTER);
		_pagedFilterTable.setColumnAlignment(ALARM_COLUMN, Align.RIGHT);
		
		_pagedFilterTable.setColumnWidth(INDEX_ROW_COLUMN, 30);
		_pagedFilterTable.setColumnWidth(TYPE_COLUMN, 100);
		_pagedFilterTable.setColumnWidth(ALARM_COLUMN, 100);
								
		_pagedFilterTable.setColumnExpandRatio(LOCATION_COLUMN, 0.3f);
		_pagedFilterTable.setColumnExpandRatio(WORKPLACE_COLUMN, 0.3f);
		_pagedFilterTable.setColumnExpandRatio(PHONES_COLUMN, 0.2f);
		_pagedFilterTable.setColumnExpandRatio(EMAILS_COLUMN, 0.2f);
		
	
	}
	/**
	 * Cabecera (Logo, barra de Organización, Cambio de idioma
	 * @return
	 */
	private Component _buildHead() {
		GridLayout head = new GridLayout(3,1);
		head.setStyleName("x47bformgrid");
		//head.setWidth(100, Unit.PERCENTAGE);
		head.setSpacing(true);
		head.setWidth(100, Unit.PERCENTAGE);
		
		//Logo
		Embedded logo = new Embedded(null, new ThemeResource("images/x47bLogo.gif"));
		//Barra organización
		Component organizationsBar =_buildOrganizationBarButtons();
		
		//Barra idioma
		Label lIdioma = new Label(_bundle.getString("x47b.idioma").concat(":"));
		Link btnLangES= new Link(_bundle.getString("x47b.idioma.es"), new ExternalResource("/x47bPanicButtonWar/?lang=es"));						
		Link btnLangEU= new Link(_bundle.getString("x47b.idioma.eu"),  new ExternalResource("/x47bPanicButtonWar/?lang=eu"));
			
		if (_lang == Language.SPANISH) {
			btnLangES.setEnabled(false);
		} else {
			btnLangEU.setEnabled(false);
		}
		
		HorizontalLayout hLang = new HorizontalLayout();
		hLang.setSpacing(true);
		hLang.addComponent(lIdioma);
		hLang.addComponent(btnLangEU);
		hLang.addComponent(btnLangES);
		
		
		head.addComponent(logo, 0,0);		
		head.addComponent(organizationsBar, 1,0);
		head.addComponent(hLang, 2,0);
		
		head.setColumnExpandRatio(1, 1);

		
		return head;		
	}
	
	/**
	 * Create new entity objects buttons.
	 * @param relatedFilterTable the object to be included the buttons
	 * @return the buttons
	 */
	private Component _buildOrganizationBarButtons() {
		GridLayout buttonLayout = new GridLayout(3,3);
		buttonLayout.setStyleName("x47borganizationbar");
		
		buttonLayout.setSpacing(true);
		buttonLayout.setMargin(true);
		

		Label lblOrganizations = new Label(_bundle.getString("x47b.organization").concat(":"));
		lblOrganizations.setWidth(100, Unit.PIXELS);		// Combo ORGANIZACIONES de la vista general
		_cboOrganizations = new ComboBox();
		// Evento change del combo
		_cboOrganizations.addValueChangeListener(
				new Property.ValueChangeListener() {
				    public void valueChange(final ValueChangeEvent event) {
				    	//Recarga la tabla
				    	if (_pagedFilterTable != null) {
				    		_eventsEnabled= false;
				    		_cboWorkPlaces.removeAllItems();
				    		_cboLocations.removeAllItems();
				    		_loadLocationsCb();
				    		_reloadTable();
				    		_btnEditLocation.setEnabled(false);
				    		_btnEditWorkPlace.setEnabled(false);
				    		_btnNewWorkPlace.setEnabled(false);		    				    		
				    		_eventsEnabled= true;
				    	}			    			    	
				    }
				});
		
		
		Label lblLocations = new Label(_bundle.getString("x47b.location").concat(":"));
		lblLocations.setWidth(100, Unit.PIXELS);		
		// Combo LOCALIZACIONES de la vista general
		_cboLocations = new ComboBox();
		// Evento change del combo
		_cboLocations.addValueChangeListener(
						new Property.ValueChangeListener() {
						    public void valueChange(final ValueChangeEvent event) {
						    	if (_eventsEnabled) {
						    		//Recarga la tabla
						    		if (_pagedFilterTable != null) {	
							    		_eventsEnabled= false;
							    		_cboWorkPlaces.removeAllItems();		    		
							    		_loadWorkPlacesCb();
							    		_reloadTable();
							    		
							    		if (event.getProperty().getValue() == null) {
							    			_btnEditLocation.setEnabled(false);
							    		} else {
							    			_btnEditLocation.setEnabled(true);
							    		}
							    		
							    		_btnEditWorkPlace.setEnabled(false);
							    		if (_cboLocations.getValue() != null) {
							    			_btnNewWorkPlace.setEnabled(true);
							    		} else {
							    			_btnNewWorkPlace.setEnabled(false);
							    		}
							    		_eventsEnabled= true; 					    					    		
						    		}			    			    	
						    	}
						    }	
						});		
		
		
		Label lblWorkPlaces = new Label(_bundle.getString("x47b.workPlace").concat(":"));
		lblWorkPlaces.setWidth(100, Unit.PIXELS);		
		// Combo WORKPLACES de la vista general
		_cboWorkPlaces = new ComboBox();
		// Evento change del combo
		_cboWorkPlaces.addValueChangeListener(
						new Property.ValueChangeListener() {
						    public void valueChange(final ValueChangeEvent event) {
						    	if (_eventsEnabled) {
						    		if (event.getProperty().getValue() == null) {
						    			_btnEditWorkPlace.setEnabled(false);
						    		} else {
						    			_btnEditWorkPlace.setEnabled(true);
						    		}			    	
						    	}	
						    }
						});				
		

		
		//Añade los botones a la vista 
		buttonLayout.addComponent(lblOrganizations,0,0);
		buttonLayout.addComponent(_cboOrganizations,1,0);
		buttonLayout.addComponent(_buildOrganizationButtons() , 2,0);

		buttonLayout.addComponent(lblLocations,0,1);
		buttonLayout.addComponent(_cboLocations,1,1);
		buttonLayout.addComponent(_buildLocationButtons(), 2,1);		
				
		buttonLayout.addComponent(lblWorkPlaces,0,2);
		buttonLayout.addComponent(_cboWorkPlaces,1,2);
		buttonLayout.addComponent(_buildWorkPlaceButtons(), 2,2);		

		
		// Carga el combo de organizaciones						
		_loadOrganizationsCb();	
		
		//Selecciona la primera organización del combo 
		if (!_cboOrganizations.getItemIds().isEmpty()) {
			_cboOrganizations.setValue(_cboOrganizations.getItemIds().iterator().next());			
			_loadLocationsCb();			
		} else { 
			_btnEditOrganization.setEnabled(false);
		}	
		
		_btnEditLocation.setEnabled(false);
		_btnEditWorkPlace.setEnabled(false);
		_btnNewWorkPlace.setEnabled(false);				
		
		return buttonLayout;
	}	
	
	
	private Component _buildOrganizationButtons() {
		//Botón para EDITAR ORGANIZACIÓN
		_btnEditOrganization = new Button(_bundle.getString("x47b.buttons.edit"));
		_btnEditOrganization.setIcon(new ThemeResource("images/r01_update.png"));
		_btnEditOrganization.addClickListener(
				new Button.ClickListener() {
					@Override
					public void buttonClick(final ClickEvent event) {
						if ( _cboOrganizations.getValue() != null) {
							GridLayout fLayout = new GridLayout(2, 4);
							X47BOrganizationForm x = new X47BOrganizationForm(fLayout, _api);
							X47BOrganization org = _api.loadOrganization(((X47BOrganizationOID) _cboOrganizations.getValue())); 
							x.setValue(org);
							x.getNameES().focus();
							final X47BEntityWindow w = _composeEntityWindow(x, 350, ORGANIZATION_ICON);					
							w.setCaption(Strings.customized(_bundle.getString("x47b.msg.location.window.caption"), "\""+org.getName().getFor(_lang)+"\""));	
							w.addCloseListener(new CloseListener() {						
													@Override
													public void windowClose(final CloseEvent e) {							
														_closeEntityUpdateWindowEventActions(w);
													}
												});
						}	
					}
				});
				
		//Botón para NUEVA ORGANIZACIÓN
		Button btnNewOrganization = new Button(_bundle.getString("x47b.buttons.create"));
		btnNewOrganization.setIcon(new ThemeResource("images/r01_add.png"));
		btnNewOrganization.addClickListener(
				new Button.ClickListener() {
					@Override
					public void buttonClick(final ClickEvent event) {
						GridLayout fLayout = new GridLayout(2, 4);
						X47BOrganizationForm x = new X47BOrganizationForm(fLayout, _api);
						x.getOrganizationIDComponent().getOrganizationId().focus();				
						final X47BEntityWindow w = _composeEntityWindow(x, 350, ORGANIZATION_ICON);
						w.setCaption(_bundle.getString("x47b.buttons.new.organization"));
						w.getRemoveBtn().setEnabled(false);				
						w.addCloseListener(new CloseListener() {					
												@Override
												public void windowClose(final CloseEvent e) {
													_closeEntityUpdateWindowEventActions(w);
												}
											});
					}
				});
		
		HorizontalLayout lOrganizationButtons = new HorizontalLayout();
		lOrganizationButtons.setSpacing(true);
		lOrganizationButtons.addComponent(_btnEditOrganization);
		lOrganizationButtons.addComponent(btnNewOrganization);
		
		return lOrganizationButtons;		
	}
	
	private Component _buildLocationButtons() {
		//Botón para EDITAR LOCALIZACION
		_btnEditLocation = new Button(_bundle.getString("x47b.buttons.edit"));
		_btnEditLocation.setIcon(new ThemeResource("images/r01_update.png"));
		_btnEditLocation.addClickListener(
							new Button.ClickListener() {
								@Override
								public void buttonClick(final ClickEvent event) {
									if ( _cboLocations.getValue() != null) {
										GridLayout fLayout = new GridLayout(2, 5);
										X47BLocationForm x = new X47BLocationForm(fLayout, _api);
										x.getNameES().focus();
										x.setValue((X47BOrgDivisionServiceLocationOID)_cboLocations.getValue());																			
										final X47BEntityWindow w = _composeEntityWindow(x, 350, LOCATION_ICON);
															
										w.setCaption(Strings.customized(_bundle.getString("x47b.msg.location.window.caption"), "\""+_cboLocations.getItem(_cboLocations.getValue()).getItemProperty("name").getValue()+"\""));			
										w.addCloseListener(new CloseListener() {					
																@Override
																public void windowClose(final CloseEvent e) {								
																	_closeEntityUpdateWindowEventActions(w);
																}
															});
									}	
					
								}
							});
				
		//Botón para NUEVA LOCALIZACIÓN
		Button btnNewLocation = new Button(_bundle.getString("x47b.buttons.create"));
		btnNewLocation.setIcon(new ThemeResource("images/r01_add.png"));
		btnNewLocation.addClickListener(
							new Button.ClickListener() {
								@Override
								public void buttonClick(final ClickEvent event) {
									GridLayout fLayout = new GridLayout(2, 5);
									X47BLocationForm x = new X47BLocationForm(fLayout, _api);				
									((X47BLocationIDComponent) x.getOrganizationIDComponent()).getLocationId().focus();				
									if (_cboOrganizations.getValue() != null) {
										x.getCboOrganizations().setValue(_cboOrganizations.getValue());
										x.getCboOrganizations().setEnabled(false);
										x.getCboOrganizations().addStyleName("x47b-combo-disabled");
									}	
									final X47BEntityWindow w = _composeEntityWindow(x, 350, LOCATION_ICON);
									w.setCaption(_bundle.getString("x47b.buttons.new.location"));
									w.getRemoveBtn().setEnabled(false);
									w.addCloseListener(new CloseListener() {					
															@Override
															public void windowClose(final CloseEvent e) {
																_closeEntityUpdateWindowEventActions(w);
															}
														});
					
								}
							});
		
		HorizontalLayout lLocationButtons = new HorizontalLayout();
		lLocationButtons.setSpacing(true);
		lLocationButtons.addComponent(_btnEditLocation);
		lLocationButtons.addComponent(btnNewLocation);
		
		return lLocationButtons;		
	}	
	
	private Component _buildWorkPlaceButtons() {
		//Botón para EDITAR WORKPLACE
		_btnEditWorkPlace = new Button(_bundle.getString("x47b.buttons.edit"));
		_btnEditWorkPlace.setIcon(new ThemeResource("images/r01_update.png"));
		_btnEditWorkPlace.addClickListener(
						new Button.ClickListener() {
							@Override
							public void buttonClick(final ClickEvent event) {
								if ( _cboWorkPlaces.getValue() != null) {
									GridLayout fLayout = new GridLayout(2, 6);
									X47BWorkPlaceForm x = new X47BWorkPlaceForm(fLayout, _api);
									x.getNameES().focus();
									x.setValue((X47BWorkPlaceOID) _cboWorkPlaces.getValue());																			
									final X47BEntityWindow w = _composeEntityWindow(x, 350, WORKPLACE_ICON);
														
									w.setCaption(Strings.customized(_bundle.getString("x47b.msg.workPlace.window.caption"), "\""+_cboWorkPlaces.getItem(_cboWorkPlaces.getValue()).getItemProperty("name").getValue()+"\""));			
									w.addCloseListener(new CloseListener() {					
															@Override
															public void windowClose(CloseEvent e) {								
																_closeEntityUpdateWindowEventActions(w);
															}
														});
								}	
							}
						});
				
		//Botón para NUEVO WORKPLACE
		_btnNewWorkPlace = new Button(_bundle.getString("x47b.buttons.create"));
		_btnNewWorkPlace.setEnabled(false);
		_btnNewWorkPlace.setIcon(new ThemeResource("images/r01_add.png"));
		_btnNewWorkPlace.addClickListener(
						new Button.ClickListener() {
							@Override
							public void buttonClick(final ClickEvent event) {
								GridLayout fLayout = new GridLayout(2, 6);
								X47BWorkPlaceForm x = new X47BWorkPlaceForm(fLayout, _api);
								x.getCboLocations().focus();	
								if (_cboOrganizations.getValue() != null) {
									x.getCboOrganizations().setValue(_cboOrganizations.getValue());
									x.getCboOrganizations().setEnabled(false);
								}	
								if (_cboLocations.getValue() != null) {
									x.getCboLocations().setValue(_cboLocations.getValue());
									x.getCboLocations().setEnabled(false);
								}					
								final X47BEntityWindow w = _composeEntityWindow(x, 400, WORKPLACE_ICON);
								w.setCaption(_bundle.getString("x47b.buttons.new.workPlace"));
								w.getRemoveBtn().setEnabled(false);
								w.addCloseListener(new CloseListener() {					
														@Override
														public void windowClose(CloseEvent e) {
															_closeEntityUpdateWindowEventActions(w);
														}
													});
							}
						});
				
		
		HorizontalLayout lWorPlaceButtons = new HorizontalLayout();
		lWorPlaceButtons.setSpacing(true);
		lWorPlaceButtons.addComponent(_btnEditWorkPlace);
		lWorPlaceButtons.addComponent(_btnNewWorkPlace);
		
		return lWorPlaceButtons;		
	}		
	

	/**
	 * Compone la ventana de edición de las entidades
	 * @param x
	 * @return
	 */
	private X47BEntityWindow _composeEntityWindow(X47BEntityObjectForm x, int height, String entityIcon) {
		X47BEntityWindow w = new X47BEntityWindow( x, _api );
		w.setStyleName("x47bentityWindow");
		w.setIcon(new ThemeResource(entityIcon));
		w.setWidth(600, Unit.PIXELS);
		w.setHeight(height,Unit.PIXELS);
		w.center();
		w.setModal(true);
		w.setResizable(false);
		addWindow(w);

		return w;
	}
	
	/**
	 * Acciones a realizar después de cerrar la ventana de mantenimiento de entidades
	 * @param w {@link X47BEntityWindow}}
	 */
	private void _closeEntityUpdateWindowEventActions(final X47BEntityWindow w) {		
		if ( w.getData() instanceof X47BOrgDivisionServiceLocation){
			//La ventana devuleve actualización de Localización o lugar de trabajo
			if (w.getSelectedBtn() == w.getSaveBtn() || w.getSelectedBtn() == w.getRemoveBtn()) {
				//Se ha pulsado guardar o eliminar, Hay que recargar la tabla de entidades
				
				//Se ha pulsado guardar, Hay que añadir o modificar la organización al combo
				X47BOrgDivisionServiceLocation loc = (X47BOrgDivisionServiceLocation)w.getData();									
								
				if (w.isNewEntity()) {					
					X47BSummarizedOrgDivisionServiceLocation sumLoc = new X47BSummarizedOrgDivisionServiceLocation().withOid(loc.getOid())
																						.withId(loc.getId())
																						.named(loc.getName().getFor(_lang));
					((BeanContainer) _cboLocations.getContainerDataSource()).addItem(loc.getOid(), sumLoc);
					_cboLocations.setValue(loc.getOid());
							
				} else if (w.getSelectedBtn() == w.getSaveBtn()) {
					if (_cboLocations.getItem(_cboLocations.getValue()) != null) {
						X47BSummarizedOrgDivisionServiceLocation sumLoc = ((BeanItem<X47BSummarizedOrgDivisionServiceLocation>)_cboLocations.getItem(_cboLocations.getValue())).getBean();
						sumLoc.named(loc.getName().getFor(_lang));
						_cboLocations.markAsDirty();			
					}
					
										
				} else if (w.getSelectedBtn() == w.getRemoveBtn()) {
					_cboLocations.removeItem(_cboLocations.getValue());
		    	}
				
		    	if (_pagedFilterTable != null) {			    			    		 
		    		_reloadTable(); 
		    	}
		    	
		    	if (w.getSelectedBtn() == w.getSaveBtn()) {
		    		Notification.show(_bundle.getString("x47b.msg.location.saved"), Notification.Type.HUMANIZED_MESSAGE);
		    	} else if (w.getSelectedBtn() == w.getRemoveBtn()) {
		    		Notification.show(_bundle.getString("x47b.msg.location.removed"), Notification.Type.HUMANIZED_MESSAGE);
		    	}
		    	
		    	if (_cboOrganizations.isEmpty()) _btnEditOrganization.setEnabled(false);
		    	else _btnEditOrganization.setEnabled(true);
		    		
		    	
			}				
		} else if (w.getData() instanceof X47BWorkPlace) { 
			//La ventana devuleve actualización de Localización o puesto de trabajo
			if (w.getSelectedBtn() == w.getSaveBtn() || w.getSelectedBtn() == w.getRemoveBtn()) {
				
				// Se ha pulsado guardar, Hay que añadir o modificar la organización al combo
				X47BWorkPlace workPlace = (X47BWorkPlace)w.getData();	
				
				log.debug("----------------- NAME ES:  "+workPlace.getName().getFor(_lang));
								
				if (w.isNewEntity()) {					
					X47BSummarizedWorkPlace sumWorkPlace = new X47BSummarizedWorkPlace().withOid(workPlace.getOid())
																				  		.withId(workPlace.getId())
																				  		.named(workPlace.getName().getFor(_lang));
					((BeanContainer) _cboWorkPlaces.getContainerDataSource()).addItem(workPlace.getOid(), sumWorkPlace);
					_cboWorkPlaces.setValue(workPlace.getOid());
							
				} else if (w.getSelectedBtn() == w.getSaveBtn()) {
					if (_cboWorkPlaces.getItem(_cboWorkPlaces.getValue()) != null) {					
						X47BSummarizedWorkPlace sumWorkPlace = ((BeanItem<X47BSummarizedWorkPlace>)_cboWorkPlaces.getItem(_cboWorkPlaces.getValue())).getBean();
						sumWorkPlace.named(workPlace.getName().getFor(_lang));
						_cboWorkPlaces.markAsDirty();
					}
				} else if (w.getSelectedBtn() == w.getRemoveBtn()) {
		    			_cboWorkPlaces.removeItem(_cboWorkPlaces.getValue());
		    	}					
				
				//Se ha pulsado guardar o eliminar, Hay que recargar la tabla de entidades
		    	if (_pagedFilterTable != null) {
		    		//_currentPage=1;		    				    		
		    		_reloadTable();
		    	}
		    	if (w.getSelectedBtn() == w.getSaveBtn()) {
		    		Notification.show(_bundle.getString("x47b.msg.workPlace.saved"), Notification.Type.HUMANIZED_MESSAGE);
		    	} else if (w.getSelectedBtn() == w.getRemoveBtn()) {
		    		Notification.show(_bundle.getString("x47b.msg.workPlace.removed"), Notification.Type.HUMANIZED_MESSAGE);
		    	}
			}																		 																							
		} else if (w.getData() instanceof X47BOrganization) {
			//La ventana devuleve actualización de Localización o puesto de trabajo			
			if (w.getSelectedBtn() == w.getSaveBtn()) {
				//Se ha pulsado guardar, Hay que añadir o modificar la organización al combo
				X47BOrganization org = (X47BOrganization) w.getData();									
								
				if (w.isNewEntity()) {					
					X47BSummarizedOrganization sumOrg = new X47BSummarizedOrganization().withOid(org.getOid())
																						.withId(org.getId())
																						.named(org.getName().getFor(_lang));
					((BeanContainer) _cboOrganizations.getContainerDataSource()).addItem(org.getOid(), sumOrg);
					_cboOrganizations.setValue(org.getOid());
							
				} else if (w.getSelectedBtn() == w.getSaveBtn()) {
					X47BSummarizedOrganization sumOrg = ((BeanItem<X47BSummarizedOrganization>) _cboOrganizations.getItem(_cboOrganizations.getValue())).getBean();
					sumOrg.named(org.getName().getFor(_lang));
					_cboOrganizations.markAsDirty();
				} 										
				Notification.show(_bundle.getString("x47b.msg.organization.saved"), Notification.Type.HUMANIZED_MESSAGE);
			} else if (w.getSelectedBtn() == w.getRemoveBtn()) {
				//Elimina la organización
				_cboOrganizations.removeItem(_cboOrganizations.getValue());				
				Notification.show(_bundle.getString("x47b.msg.organization.saved"), Notification.Type.HUMANIZED_MESSAGE);
			}	 								
		}
		

	}
	
	/**
	 * Carga el combo de Organizaciones
	 */
	private void _loadOrganizationsCb() {
		_cboOrganizations.setNullSelectionAllowed(false);						
		_cboOrganizations.setItemCaptionMode(AbstractSelect.ItemCaptionMode.PROPERTY);
		_cboOrganizations.setItemCaptionPropertyId("name");	
		
		BeanContainer<X47BOrganizationOID, X47BSummarizedOrganization> orgContainer = new BeanContainer<X47BOrganizationOID, X47BSummarizedOrganization>(X47BSummarizedOrganization.class);					
		orgContainer.setBeanIdProperty("oid");
		orgContainer.addAll(_api.loadOrganizations());

		_cboOrganizations.setContainerDataSource(orgContainer);	
	}
	
	/**
	 * Carga el combo de Localizaciones
	 */
	private void _loadLocationsCb() {
		
		if (_cboOrganizations.getValue() != null) {
			_eventsEnabled = false;
			_cboLocations.setNullSelectionAllowed(true);						
			_cboLocations.setItemCaptionMode(AbstractSelect.ItemCaptionMode.PROPERTY);
			_cboLocations.setItemCaptionPropertyId("name");	
			
			BeanContainer<X47BOrgDivisionServiceLocationOID,
						  X47BSummarizedOrgDivisionServiceLocation> locContainer = new BeanContainer<X47BOrgDivisionServiceLocationOID,
						  																			 X47BSummarizedOrgDivisionServiceLocation>(X47BSummarizedOrgDivisionServiceLocation.class);					
			locContainer.setBeanIdProperty("oid");
			locContainer.addAll(_api.loadLocations((X47BOrganizationOID) _cboOrganizations.getValue()));
	
			_cboLocations.setContainerDataSource(locContainer);
			_eventsEnabled =  true;
		}	
	}	
	
	/**
	 * Carga el combo de lugares de trabajo
	 */
	private void _loadWorkPlacesCb() {
		if (_cboLocations.getValue() != null) {
			_eventsEnabled = false;
			_cboWorkPlaces.setNullSelectionAllowed(true);						
			_cboWorkPlaces.setItemCaptionMode(AbstractSelect.ItemCaptionMode.PROPERTY);
			_cboWorkPlaces.setItemCaptionPropertyId("name");	
			
			BeanContainer<X47BWorkPlaceOID, 
						  X47BSummarizedWorkPlace> workPlaceContainer = new BeanContainer<X47BWorkPlaceOID, 
						  															      X47BSummarizedWorkPlace>(X47BSummarizedWorkPlace.class);					
			workPlaceContainer.setBeanIdProperty("oid");
			workPlaceContainer.addAll(_api.loadWorkPlaces((X47BOrgDivisionServiceLocationOID)_cboLocations.getValue()));
	
			_cboWorkPlaces.setContainerDataSource(workPlaceContainer);
			_eventsEnabled = true;
		}	
	}		
	
	
	/**
	 * Recarga la tabla de entidades, si inicializar ContainerDatasource
	 */
	private void _reloadTable() {
		_reloadTable(false);
	}
	
	/**
	 * Recarga la tabla de entidades
	 * @param initDatasource indica si hay inicializar el ContainerDatasource de la tabla
	 */
	private void _reloadTable(boolean initDatasource) {
		_eventsEnabled=false;
		if (_currentPage==0) _currentPage=1;
		
		BeanItemContainer<X47BSearchResultItemForPanicButtonOrganizationalEntity> beanEnties = new BeanItemContainer<X47BSearchResultItemForPanicButtonOrganizationalEntity>(X47BSearchResultItemForPanicButtonOrganizationalEntity.class);
		if (_cboOrganizations.getValue() != null) {
			
			log.debug("RELOAD TABLE");
			 /*************************************
	         * Configura el filtro
	         *************************************/         
			String filterLocationText = null;
			String filterWorkPlaceText = null;
			if (Strings.isNOTNullOrEmpty((String)_pagedFilterTable.getFilterFieldValue(LOCATION_COLUMN))) {
				filterLocationText = (String)_pagedFilterTable.getFilterFieldValue(LOCATION_COLUMN);
				
			}
			else if (Strings.isNOTNullOrEmpty((String)_pagedFilterTable.getFilterFieldValue(WORKPLACE_COLUMN))) {
				filterWorkPlaceText = (String)_pagedFilterTable.getFilterFieldValue(WORKPLACE_COLUMN);
			}
						
			Class[] types = null;  
			if (((Class)_pagedFilterTable.getFilterFieldValue(TYPE_COLUMN)) == X47BOrgDivisionServiceLocation.class) {
				types = new Class[] {X47BOrgDivisionServiceLocation.class};
			} else if (((Class)_pagedFilterTable.getFilterFieldValue(TYPE_COLUMN)) == X47BWorkPlace.class) {
				types =  new Class[] {X47BWorkPlace.class};
			} else {
				if (Strings.isNullOrEmpty(filterLocationText) && Strings.isNullOrEmpty(filterWorkPlaceText)) {
					types =  new Class[] {X47BOrganization.class,
										  X47BOrgDivisionServiceLocation.class,
										  X47BWorkPlace.class};
				} else if (Strings.isNOTNullOrEmpty(filterLocationText)) {
					types = new Class[] {X47BOrgDivisionServiceLocation.class};					
				} else {
					types = new Class[] {X47BWorkPlace.class};
				}
			}			
			
			X47BSearchFilterForPanicButtonOrganizationalEntity filter = new X47BSearchFilterForPanicButtonOrganizationalEntity(types)																		
															.belongingTo((X47BOrganizationOID) _cboOrganizations.getValue());
			
			if (_cboLocations.getValue() != null) {
				filter.belongingTo((X47BOrgDivisionServiceLocationOID)_cboLocations.getValue());
			}
									
			if (Strings.isNOTNullOrEmpty(filterLocationText)) {				
				filter.withText (filterLocationText).in(_lang);											
			} else if (Strings.isNOTNullOrEmpty(filterWorkPlaceText)) {
				filter.withText(filterWorkPlaceText).in(_lang);				
			}
															
			 /*************************************
	         * Ejecuta la búsqueda
	         *************************************/   
			log.debug("/*************************************");
			log.debug("EJECUTA BÜSQUEDA");
			log.debug("*************************************/");
			log.debug("Primera a fila a devolver		:"+(_currentPage*_itemsPerPage-_itemsPerPage));
			log.debug("Número de elementos por página	:"+_itemsPerPage);
			log.debug("Total elementos búsqueda			:"+_totalItemsCount);
			log.debug("*************************************/");
			
			SearchResults<X47BSearchFilterForPanicButtonOrganizationalEntity,
						X47BSearchResultItemForPanicButtonOrganizationalEntity> searchResults = _api.searchOrganizationEntities(filter, _currentPage*_itemsPerPage-_itemsPerPage, _itemsPerPage);
								
			 /*************************************
	         * Establece elementos de paginación
	         *************************************/         
			
			if (searchResults.getPageItems() != null) {
				//Hay resultados
				_totalItemsCount = searchResults.getTotalItemsCount();
				_currentIndexRow = _currentPage*_itemsPerPage-_itemsPerPage;				
				
				_lastPage =  (int) ((_totalItemsCount%_itemsPerPage == 0) ? _totalItemsCount/_itemsPerPage : _totalItemsCount/_itemsPerPage-1);
				_pagedFilterTable.getTotalPagesLabel().setValue(String.valueOf(_lastPage));
				
				
				if (_currentPage >= _lastPage) {
					_pagedFilterTable.getNext().setEnabled(false);
					_pagedFilterTable.getLast().setEnabled(false);
				} else {
					_pagedFilterTable.getNext().setEnabled(true);
					_pagedFilterTable.getLast().setEnabled(true);
				}
				
				if (_currentPage == 1) {
					_pagedFilterTable.getPrevious().setEnabled(false);
					_pagedFilterTable.getFirst().setEnabled(false);
				} else {
					_pagedFilterTable.getPrevious().setEnabled(true);
					_pagedFilterTable.getFirst().setEnabled(true);
				}
												
				beanEnties.addAll(searchResults.getPageItems());
				
				
				
				log.debug("/*************************************");
				log.debug("DEVULEVE RESULTADOS");
				log.debug("*************************************/");				
				log.debug("Primera a fila a devolver		:"+(_currentPage*_itemsPerPage-_itemsPerPage));
				log.debug("Número de elementos por página	:"+_itemsPerPage);
				log.debug("Total elementos búsqueda			:"+_totalItemsCount);
				log.debug("*************************************/");
							
			} else {
				//No hay resultados
				_currentPage=0;
				_currentIndexRow = 0;
				_totalItemsCount = 0;
				_pagedFilterTable.setContainerDataSource(new BeanItemContainer<X47BSearchResultItemForPanicButtonOrganizationalEntity>(X47BSearchResultItemForPanicButtonOrganizationalEntity.class));
				_setTableVisibleColumns();										
				_pagedFilterTable.getTotalPagesLabel().setValue("0");
				
								log.debug("/*************************************");
				log.debug("NO HAY RESULTADOS");
				log.debug("*************************************/");				
				log.debug("Primera a fila a devolver		:"+(_currentPage*_itemsPerPage-_itemsPerPage));
				log.debug("Número de elementos por página	:"+_itemsPerPage);
				log.debug("Total elementos búsqueda			:"+_totalItemsCount);
				log.debug("*************************************/");
			}
			
		}
		
		_pagedFilterTable.getCurrentPageTextField().setValue(String.valueOf(_currentPage));		
		
		if (beanEnties.size() >0) {
			if (initDatasource) {
				_pagedFilterTable.setContainerDataSource(beanEnties);
				_setTableVisibleColumns();	
			} else {			
				_pagedFilterTable.getContainerDataSource().removeAllItems();
				_pagedFilterTable.setContainerDataSource(beanEnties);
				_currentIndexRow = _currentPage*_itemsPerPage-_itemsPerPage;												
				_setTableVisibleColumns();	
			}
		}				
		_eventsEnabled=true;													
	}	
	
		
	/**
	 * Compose string with all phones or emails separated by comma character.
	 * @param contacts phones or emails collection
	 * @return string with all phones or emails separated by comma character, 
	 * 		   empty string if hasn´t phones or emails.
	 */
	private String _composeContactText(final Collection<?> contacts) {
		String contacText = "";		
		if (contacts != null && !contacts.isEmpty()) {
			contacText = Joiner.on(',')
								.skipNulls()
								.join(contacts);
		}
		return 	contacText;	
	}		
	
	/**
	 * Configura loas literales de la paginación de la tabla
	 * @return
	 */
	private void _composePagedFilterControlConfig() {
		_pagedFilterTableconfig = new X47BPagedFilterControlConfig();
		_pagedFilterTableconfig.setItemsPerPage(_bundle.getString("x47b.table.itemsPerPage"));
		_pagedFilterTableconfig.setPage(_bundle.getString("x47b.table.pageNumber"));
		_pagedFilterTableconfig.setInitialItemsPerPage(_itemsPerPage);
				
		//Evento de cambio de elementos por página
		_pagedFilterTableconfig.setItemsPerPageChangeListener(new ValueChangeListener() {			
			@Override
			public void valueChange(ValueChangeEvent event) {
				_itemsPerPage= ((Integer) event.getProperty().getValue()).intValue();
				_pagedFilterTable.setPageLength(_itemsPerPage);
				_currentPage=1;
				_pagedFilterTable.getCurrentPageTextField().setValue(String.valueOf(_currentPage));				
				_reloadTable();
				
			}
		});			
		
		//Evento de cambio de número de página
		_pagedFilterTableconfig.setCurrentPageTextFieldListener(
									new ValueChangeListener() {			
										@Override
										public void valueChange(ValueChangeEvent event) {
											if (_eventsEnabled) {
												if (Strings.isNOTNullOrEmpty((String)event.getProperty().getValue())) {
													try {
														int pageNumber = Integer.parseInt((String)event.getProperty().getValue());							
														log.debug("pageNumber:"+pageNumber);
														log.debug("_totalItemsCount:"+_totalItemsCount);
														log.debug("_itemsPerPage:"+_itemsPerPage);
														log.debug("lastPage:"+(_totalItemsCount/_itemsPerPage)+1);
														if (pageNumber > 1 && pageNumber <= (_totalItemsCount/_itemsPerPage)+1) {
															_currentPage = pageNumber;
															_reloadTable();
														} else  {
															_pagedFilterTable.getCurrentPageTextField().setValue(String.valueOf(_currentPage));	
														}
													} catch (NumberFormatException ex) {
														_pagedFilterTable.getCurrentPageTextField().setValue(String.valueOf(_currentPage));		
													}											
												} else {
													_pagedFilterTable.getCurrentPageTextField().setValue(String.valueOf(_currentPage));	
												}
											}
										}
									});	
		
		//Evento botón página anterior
		_pagedFilterTableconfig.setPrevBtnClickListener(
									new ClickListener() {			
										@Override
										public void buttonClick(ClickEvent event) {
											_eventsEnabled = false;
											_currentPage--;
											_pagedFilterTable.getCurrentPageTextField().setValue(String.valueOf(_currentPage));
											_reloadTable();			
											_eventsEnabled = true;
										}
									});
		
		//Evento botón página siguiente
		_pagedFilterTableconfig.setNextBtnClickListener(
									new ClickListener() {			
										@Override
										public void buttonClick(ClickEvent event) {
											_eventsEnabled = false;
											_currentPage++;
											_pagedFilterTable.getCurrentPageTextField().setValue(String.valueOf(_currentPage));
											_reloadTable();
											_eventsEnabled = true;				
										}
									});
									
		//Evento botón primera página
		_pagedFilterTableconfig.setFirstBtnClickListener(
									new ClickListener() {			
										@Override
										public void buttonClick(ClickEvent event) {
											_eventsEnabled = false;
											_currentPage=1;
											_pagedFilterTable.getCurrentPageTextField().setValue(String.valueOf(_currentPage));
											_reloadTable();
											_eventsEnabled = true;				
										}
									});		
		
		//Evento botón última página
		_pagedFilterTableconfig.setLastBtnClickListener(
									new ClickListener() {			
										@Override
										public void buttonClick(ClickEvent event) {
											_eventsEnabled = false;
											_currentPage = _lastPage;
											_pagedFilterTable.getCurrentPageTextField().setValue(String.valueOf(_currentPage));
											_reloadTable();
											_eventsEnabled = true;
										}
									});								
	}	
	
	private void _composeFilterEvents() {	
		//Elimina filtros
		_pagedFilterTable.addHeaderClickListener(
							new HeaderClickListener() {			
								@Override
								public void headerClick(HeaderClickEvent event) {				
									if (event.getPropertyId().equals(INDEX_ROW_COLUMN)) {	
										_eventsEnabled=false;
										_filterGenerator.getEntityTypeBox().setValue(Object.class);
										_filterGenerator.getAlarmsBox().setValue(new Integer(0));
										_filterGenerator.getLocationText().setValue("");
										_filterGenerator.getWorkPlaceText().setValue("");					
										_reloadTable();
										_eventsEnabled=true;
									}								
								}
							});
		
		_filterGenerator.getEntityTypeBox()
						.addValueChangeListener(
							new ValueChangeListener() {
								@Override
								public void valueChange(ValueChangeEvent event) {
									if (_eventsEnabled) {
										_currentPage=1;
										_reloadTable();
									}	
								}
							});
		
		_filterGenerator.getAlarmsBox()
						.addValueChangeListener(
							new ValueChangeListener() {
								@Override
								public void valueChange(ValueChangeEvent event) {
									if (_eventsEnabled) {
										_currentPage=1;
										_reloadTable();
									}	
								}
							});		
		
		_filterGenerator.getLocationText()
						.addValueChangeListener(
								new ValueChangeListener() {			
									@Override
									public void valueChange(ValueChangeEvent event) {
										if (_eventsEnabled) {
											_currentPage=1;				
											_reloadTable();
										}					
									}
								});
		
		_filterGenerator.getWorkPlaceText()
						.addValueChangeListener(
								new ValueChangeListener() {			
									@Override
									public void valueChange(ValueChangeEvent event) {
										if (_eventsEnabled) {			
											_currentPage=1;				
											_reloadTable();
										}	
										
									}
								});		
		
	}
	
	


	
	
}