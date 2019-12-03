package pb01.ui.vaadin.view;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

import com.google.common.collect.FluentIterable;
import com.google.common.collect.Lists;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.shared.ui.window.WindowMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.renderers.HtmlRenderer;
import com.vaadin.ui.themes.ValoTheme;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import pb01.ui.vaadin.alarmevent.PB01ContactsListView;
import pb01.ui.vaadin.alarmevent.PB01PresenterForRaisedAlarmsListView;
import pb01.ui.vaadin.alarmevent.PB01RaisedAlarmsListView;
import r01f.patterns.Provider;
import r01f.types.TimeLapse;
import r01f.types.contact.EMail;
import r01f.types.contact.Phone;
import r01f.ui.i18n.UII18NService;
import r01f.ui.presenter.UIPresenterSubscriber;
import r01f.ui.vaadin.view.VaadinView;
import r01f.util.types.collections.CollectionUtils;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionID;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionServiceID;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionServiceLocationID;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgObjectID;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrganizationID;
import x47b.model.oids.X47BOrganizationalIDs.X47BWorkPlaceID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceLocationOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgObjectOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrganizationOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BWorkPlaceOID;
import x47b.model.org.X47BOrgObjectRef;
import x47b.model.org.X47BOrgObjectType;

@Slf4j
public class PB01MainGridView
	 extends VerticalLayout
  implements VaadinView {

	private static final long serialVersionUID = -7729980110242578713L;

/////////////////////////////////////////////////////////////////////////////////////////
//  I18N
/////////////////////////////////////////////////////////////////////////////////////////
	private final UII18NService _i18n;
	private final PB01MainViewPresenter _presenter;

	// The grid
	private final Grid<PB01ViewObjForSearchResultItem> _grid = new Grid<>();
	private ListDataProvider<PB01ViewObjForSearchResultItem> _gridDataProvider;

	// A pop up where all the raised alarms are shown
	private final PB01RaisedAlarmsListView _raisedAlarmsPopUp;
	
	// A pop up where all phones for organization object are shown
	private final PB01ContactsListView _contactsPopUp;


/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01MainGridView(final UII18NService i18n,
							// the presenter
							final PB01MainViewPresenter presenter,
							// presenter for the alarm list view
							final PB01PresenterForRaisedAlarmsListView alarmListViewPresenter,
							// what happens when the user clicks on an organizational entity
							final PB01OrgObjectClickEventListener<X47BOrganizationOID,X47BOrganizationID> orgClickEventListener,
						    final PB01OrgObjectClickEventListener<X47BOrgDivisionOID,X47BOrgDivisionID> orgDivClickEventListener,
						    final PB01OrgObjectClickEventListener<X47BOrgDivisionServiceOID,X47BOrgDivisionServiceID> orgDivSrvcClickEventListener,
						    final PB01OrgObjectClickEventListener<X47BOrgDivisionServiceLocationOID,X47BOrgDivisionServiceLocationID> orgDivSrvcLocClickEventListener,
						    final PB01OrgObjectClickEventListener<X47BWorkPlaceOID,X47BWorkPlaceID> workPlaceClickEventListener) {
		_i18n = i18n;
		_presenter = presenter;

        _grid.setStyleName("stripes");
        _grid.setSizeFull();
        _grid.setHeightMode(HeightMode.ROW);
        _grid.setHeightByRows(8);

        _buildGridColumns(orgClickEventListener,
        				  orgDivClickEventListener,
        				  orgDivSrvcClickEventListener,
        				  orgDivSrvcLocClickEventListener,
        				  workPlaceClickEventListener);

        this.addComponent(_grid);

        // the popup shown when the number of alarms is clicked
        _raisedAlarmsPopUp = new PB01RaisedAlarmsListView(i18n,
        												  alarmListViewPresenter);
        _raisedAlarmsPopUp.setCaption(_i18n.getMessage("pb01.raisedAlarms"));
        _raisedAlarmsPopUp.setModal(true);
        _raisedAlarmsPopUp.setClosable(true);
        _raisedAlarmsPopUp.setDraggable(true);
        _raisedAlarmsPopUp.setResizable(false);
        _raisedAlarmsPopUp.setWindowMode(WindowMode.NORMAL);
        _raisedAlarmsPopUp.setWidth("80%");
        _raisedAlarmsPopUp.center();
        
      // the popup shown when the number of alarms is clicked
        _contactsPopUp = new PB01ContactsListView(i18n);        
        _contactsPopUp.setModal(true);
        _contactsPopUp.setClosable(true);
        _contactsPopUp.setDraggable(true);
        _contactsPopUp.setResizable(false);
        _contactsPopUp.setWindowMode(WindowMode.NORMAL);
        _contactsPopUp.setWidth("80%");
        _contactsPopUp.center();        
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	public void refresh(final X47BOrgObjectRef<X47BOrganizationOID,X47BOrganizationID> orgRef,
						final X47BOrgObjectRef<X47BOrgDivisionOID,X47BOrgDivisionID> orgDivRef,
						final X47BOrgObjectRef<X47BOrgDivisionServiceOID,X47BOrgDivisionServiceID> orgDivSrvcRef,
						final X47BOrgObjectRef<X47BOrgDivisionServiceLocationOID,X47BOrgDivisionServiceLocationID> orgDivSrvcLocRef,
						final X47BOrgObjectRef<X47BWorkPlaceOID,X47BWorkPlaceID> workPlaceRef) {
		log.info( "[Grid] Refresh..." );
		final X47BOrganizationOID orgOid = orgRef != null ? orgRef.getOid() : null;
		final X47BOrgDivisionOID orgDivOid = orgDivRef != null ? orgDivRef.getOid() : null;
		final X47BOrgDivisionServiceOID orgDivSrcvOid = orgDivSrvcRef != null ? orgDivSrvcRef.getOid() : null;
		final X47BOrgDivisionServiceLocationOID orgDivSrvcLocOid = orgDivSrvcLocRef != null ? orgDivSrvcLocRef.getOid() : null;
		final X47BWorkPlaceOID wrkPlcOid = workPlaceRef != null ? workPlaceRef.getOid() : null;
		_presenter.onGridDataNeeded(orgOid,orgDivOid,orgDivSrcvOid,orgDivSrvcLocOid,wrkPlcOid,
									0,10,
									UIPresenterSubscriber.from(
											// on success
											items -> _paintGridItems(items),
											// on error
											th -> log.info("Error while searching; {}",	// TODO do something useful with the error
														   th.getMessage(),th)));
	}
	private void _paintGridItems(final Collection<PB01ViewObjForSearchResultItem> items) {
		// set the data
		if (CollectionUtils.hasData(items)) {
			_gridDataProvider = DataProvider.ofCollection(items);
		} else {
			_gridDataProvider = DataProvider.ofCollection(Lists.newArrayList());		// an empty grid
		}
		_grid.setDataProvider(_gridDataProvider);
		this.setVisible(CollectionUtils.hasData(items));
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	private void _buildGridColumns(final PB01OrgObjectClickEventListener<X47BOrganizationOID,X47BOrganizationID> orgClickEventListener,
								   final PB01OrgObjectClickEventListener<X47BOrgDivisionOID,X47BOrgDivisionID> orgDivClickEventListener,
								   final PB01OrgObjectClickEventListener<X47BOrgDivisionServiceOID,X47BOrgDivisionServiceID> orgDivSrvcClickEventListener,
								   final PB01OrgObjectClickEventListener<X47BOrgDivisionServiceLocationOID,X47BOrgDivisionServiceLocationID> orgDivSrvcLocClickEventListener,
								   final PB01OrgObjectClickEventListener<X47BWorkPlaceOID,X47BWorkPlaceID> workPlaceClickEventListener) {
		
        // OBJECT TYPE				
		_grid.addColumn( item -> _createOrgObjectTypeIconFor(item), new HtmlRenderer())
		 		.setCaption( _i18n.getMessage("pb01.org.objectType") )
		 		.setResizable(false)
		 		.setId( "objType" );

		// Number of raised alarms
		_grid.addComponentColumn(this::_createRaisedAlarmsButtonFor)
				.setCaption( _i18n.getMessage("pb01.alarm.raised.count.short") )
				.setDescriptionGenerator(item -> item.getAlarmLastRaiseDateExplained(_i18n))
				.setId( "alarmRaiseCount" );
        // ORG
		_grid.addComponentColumn( item -> _createOrgObjectButtonFor(item,
																	orgClickEventListener,
																	item::getOrganizationName,							// provides the button caption
																	item::getOrganizationOid,item::getOrganizationId) )	// provides the button oid & id
				 .setCaption( _i18n.getMessage("pb01.org") )
				 .setDescriptionGenerator(PB01ViewObjForSearchResultItem::getOrganizationHint)
				 .setResizable(true)
				 .setId( "org" );
        // ORG DIVISION
		_grid.addComponentColumn( item -> _createOrgObjectButtonFor(item,
																	orgDivClickEventListener,
																	item::getOrgDivisionName,							// provides the button caption
																	item::getOrgDivisionOid,item::getOrgDivisionId) )	// provides the button oid & id
				 .setCaption( _i18n.getMessage("pb01.org.division") )
				 .setDescriptionGenerator(PB01ViewObjForSearchResultItem::getOrgDivisionHint)
				 .setResizable(true)
				 .setId( "orgDivision" );
        // ORG DIVISION SERVICE
		_grid.addComponentColumn( item -> _createOrgObjectButtonFor(item,
																	orgDivSrvcClickEventListener,
																	item::getOrgDivisionServiceName,								// provides the button caption
																	item::getOrgDivisionServiceOid,item::getOrgDivisionServiceId) )	// provides the button oid & id
				 .setCaption( _i18n.getMessage("pb01.org.service") )
				 .setDescriptionGenerator(PB01ViewObjForSearchResultItem::getOrgDivisionServiceHint)
				 .setResizable(true)
				 .setId( "orgDivisionService" );
        // ORG DIVISION SERVICE LOCATION
		_grid.addComponentColumn( item -> _createOrgObjectButtonFor(item,
																	orgDivSrvcLocClickEventListener,
																	item::getOrgDivisionServiceLocationName,										// provides the button caption
																	item::getOrgDivisionServiceLocationOid,item::getOrgDivisionServiceLocationId) )	// provides the button oid & id
				 .setCaption( _i18n.getMessage("pb01.org.location") )
				 .setDescriptionGenerator(PB01ViewObjForSearchResultItem::getOrgDivisionServiceLocationHint)
				 .setResizable(true)
				 .setId( "orgDivisionServiceLocation" );
        // WORKPLACE
		_grid.addComponentColumn( item -> _createOrgObjectButtonFor(item,
																	workPlaceClickEventListener,
																	item::getWorkPlaceName,							// provides the button caption
																	item::getWorkPlaceOid,item::getWorkPlaceId) )	// provides the button oid & id
				 .setCaption( _i18n.getMessage("pb01.org.workPlace") )
				 .setDescriptionGenerator(PB01ViewObjForSearchResultItem::getWorkPlaceHint)
				 .setResizable(true)
				 .setId( "workPlaceId" );
        // PHONES
//		_grid.addColumn( PB01ViewObjForSearchResultItem::getEffectivePhoneCount )
//				 .setCaption( _i18n.getMessage("pb01.org.phones") )
//				 .setDescriptionGenerator( item -> _i18n.getMessage("pb01.org.phones.hint") )
//				 .setResizable(true)
//				 .setId( "phones" );
		
		_grid.addComponentColumn( item -> _createContactsButtonFor(item, item.getEffectivePhoneCount(), "pb01.org.phones"))	
				 .setCaption( _i18n.getMessage("pb01.org.phones") )
				 .setDescriptionGenerator( item -> _i18n.getMessage("pb01.org.phones.hint") )
				 .setResizable(true)
				 .setId( "phones" );
		
        // EMAILS
//		_grid.addColumn( PB01ViewObjForSearchResultItem::getEffectiveEmailCount )
//				 .setCaption( _i18n.getMessage("pb01.org.emails") )
//				 .setDescriptionGenerator( item -> _i18n.getMessage("pb01.org.emails.hint") )
//				 .setResizable(true)
//				 .setId( "emails" );
		
		_grid.addComponentColumn( item -> _createContactsButtonFor(item, item.getEffectiveEmailCount(), "pb01.org.emails"))	
				 .setCaption( _i18n.getMessage("pb01.org.emails") )
				 .setDescriptionGenerator( item -> _i18n.getMessage("pb01.org.emails.hint") )
				 .setResizable(true)
				 .setId( "emails" );		
		
		// RAISE AN ALARM
		_grid.addComponentColumn( item -> {
											// Get the workplade oid
											X47BWorkPlaceOID workPlaceOid = item.getWorkPlaceOid();

											// create the button
											Button btn = new Button();
											btn.setIcon(VaadinIcons.BOLT);
											btn.setDescription(_i18n.getMessage("pb01.alarm.raise"));
											btn.addClickListener(event -> _presenter.raiseAlarm(workPlaceOid,
																								viewAlarm -> {	// update the count shown at the grid
																												for (PB01ViewObjForSearchResultItem gi : _gridDataProvider.getItems()) {
																													if (gi == item					// the workplace item
																													 || gi.isAncestorOf(item)) {	// all the ancestors
																														gi.increaseAlarmRaiseCount(1);
																														_grid.getDataProvider().refreshItem(gi);
																													}
																												}
																												Notification.show(_i18n.getMessage("pb01.alarm.raised",
																																				   item.getWorkPlaceId(),
																																				   item.getOrgHierarchyExplained()));
																											 }));
											btn.setVisible(item.getOrgObjectType() == X47BOrgObjectType.WORKPLACE);	// only visible for workplaces
											return btn;
										  } )
				 .setResizable(false)
				 .setId( "raiseAlarm" );
	}
	
	private String _createOrgObjectTypeIconFor(PB01ViewObjForSearchResultItem item) {		
		if (item.getOrgObjectType() == X47BOrgObjectType.ORG_DIVISION) {
			return VaadinIcons.OFFICE.getHtml();
		} else if (item.getOrgObjectType() == X47BOrgObjectType.ORG_DIVISION_SERVICE) {	
			return VaadinIcons.AREA_SELECT.getHtml();
		} else if (item.getOrgObjectType() == X47BOrgObjectType.ORG_DIVISION_SERVICE_LOCATION) {	
			return VaadinIcons.WORKPLACE.getHtml();
		} else if (item.getOrgObjectType() == X47BOrgObjectType.WORKPLACE) {		
			return VaadinIcons.DESKTOP.getHtml();
		}
			
		return item.getOrgObjectType().name();
	}
	
	
	private Button _createRaisedAlarmsButtonFor(final PB01ViewObjForSearchResultItem item) {
		// show a link that opens a popup that shows the list of raised alarms
		final Button btn = new Button(Long.toString(item.getAlarmRaiseCount()));
		btn.addClickListener(event -> {
											_raisedAlarmsPopUp.setCaption(item.getOrgHierarchyExplained());
											_raisedAlarmsPopUp.listAlarmEventsFor(item.getOrganization().getId(),TimeLapse.createFor("1d"));	// 1 day
											UI.getCurrent()
											  .addWindow(_raisedAlarmsPopUp);
									  });
		btn.addStyleName(ValoTheme.BUTTON_LINK);
		return btn;
	}
	private <O extends X47BOrgObjectOID,I extends X47BOrgObjectID<O>>
			Button _createOrgObjectButtonFor(final PB01ViewObjForSearchResultItem item,
										  	 final PB01OrgObjectClickEventListener<O,I> clickEventListener,
										  	 final Provider<String> buttonCaption,
										  	 final Provider<O> objOid,final Provider<I> objId) {
		// show a link that opens a popup for editing the entity
		final Button btn = new Button(buttonCaption.provideValue());
		btn.addStyleName(ValoTheme.BUTTON_LINK);
		if (clickEventListener != null) btn.addClickListener(event -> {	// just raise other event type
																		final PB01OrgObjectClickedEvent<O,I> entityClickedEvent = new PB01OrgObjectClickedEvent<>(btn,
																																							      objOid.provideValue(),objId.provideValue());
																		clickEventListener.entityClicked(entityClickedEvent);
																	   });
		return btn;
	}
		
	private Button _createContactsButtonFor(final PB01ViewObjForSearchResultItem item, int effectiveContactCount,  String contactCaptionKey) {
		// show a link that opens a popup that shows the list of raised alarms
		final Button btn = new Button(Integer.toString(item.getEffectiveEmailCount()));
		
//		log.info("------------EFFECTIVE PHONES-----------------------------");
//		log.info(item.getOrgHierarchyExplained());
//		for (Phone p: item.getEffectivePhones()) {		
//			log.info(p.asString());
//		}	
//				
//		log.info("------------EFFECTIVE PHONES BY ORG ---------------------");		
//		for (X47BOrgObjectType orgType: item.getEffectivePhonesByOrgEntityType().keySet()) {
//			log.info(orgType.name());
//			for (Phone p: item.getEffectivePhonesByOrgEntityType().get(orgType)) {		
//				log.info(p.asString());
//			}
//		}				
//		log.info("-----------------------------------------");
//		
		btn.addClickListener(event -> {	
											_contactsPopUp.setCaption(item.getOrgHierarchyExplained());
											//_contactsPopUp.setCaption(_i18n.getMessage(captionKey));
											log.info("-----------------------------------------");
											log.info(item.getOrgHierarchyExplained());
											
											if ("pb01.org.phones".equals(contactCaptionKey)) {
												_contactsPopUp.paintGridPhoneItems(item.getEffectivePhonesByOrgEntityType(), contactCaptionKey);
											} else if ("pb01.org.emails".equals(contactCaptionKey)) {
												_contactsPopUp.paintGridEmailItems(item.getEffectiveEmailsByOrgEntityType(), contactCaptionKey);
											}	
											UI.getCurrent()
											  .addWindow(_contactsPopUp);
									  });
		btn.addStyleName(ValoTheme.BUTTON_LINK);
		return btn;
	}
	
	
	
/////////////////////////////////////////////////////////////////////////////////////////
//	EVENTS
/////////////////////////////////////////////////////////////////////////////////////////
	@Accessors(prefix="_")
	public class PB01OrgObjectClickedEvent<O extends X47BOrgObjectOID,I extends X47BOrgObjectID<O>>
	     extends Component.Event {
		private static final long serialVersionUID = 6771268655053782852L;

		@Getter private final X47BOrgObjectRef<O,I> _objRef;

		public PB01OrgObjectClickedEvent(final Component source,
								  		 final O oid,final I id) {
			super(source);
			_objRef = new X47BOrgObjectRef<>(oid,id);
		}
	}
	public interface PB01OrgObjectClickEventListener<O extends X47BOrgObjectOID,I extends X47BOrgObjectID<O>>
	         extends Serializable {
		void entityClicked(PB01OrgObjectClickedEvent<O,I> event);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	
/////////////////////////////////////////////////////////////////////////////////////////
	@Accessors(prefix="_")
	@RequiredArgsConstructor
	private class PB01OrgEntityContactMean<T> 
	   implements Serializable {
		private static final long serialVersionUID = 9053980519186063674L;
		@Getter private final X47BOrgObjectType _type;
		@Getter private final Collection<T> _mean;
	}
	private ListDataProvider<PB01OrgEntityContactMean<EMail>> _createOrgEntityEffectiveEmailsGridDataProvider(final Map<X47BOrgObjectType,Collection<EMail>> orgEntityEmails) {
		if (CollectionUtils.isNullOrEmpty(orgEntityEmails)) return DataProvider.ofCollection(Lists.newArrayList());
		Collection<PB01OrgEntityContactMean<EMail>> items = FluentIterable.from(orgEntityEmails.entrySet())
																		  .transform(entry -> new PB01OrgEntityContactMean<EMail>(entry.getKey(),
																												   				  entry.getValue()))
																		  .toList();
		return DataProvider.ofCollection(items);
	}
	private ListDataProvider<PB01OrgEntityContactMean<Phone>> _createOrgEntityEffectivePhonesGridDataProvider(final Map<X47BOrgObjectType,Collection<Phone>> orgEntityPhones) {
		if (CollectionUtils.isNullOrEmpty(orgEntityPhones)) return DataProvider.ofCollection(Lists.newArrayList());
		Collection<PB01OrgEntityContactMean<Phone>> items = FluentIterable.from(orgEntityPhones.entrySet())
																		  .transform(entry -> new PB01OrgEntityContactMean<Phone>(entry.getKey(),
																												   				  entry.getValue()))
																		  .toList();
		return DataProvider.ofCollection(items);
	}
}
