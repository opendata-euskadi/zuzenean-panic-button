package pb01.ui.vaadin.view;

import java.io.Serializable;
import java.util.Collection;

import com.google.common.collect.Lists;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import r01f.patterns.Provider;
import r01f.ui.i18n.UII18NService;
import r01f.ui.presenter.UIPresenterSubscriber;
import r01f.ui.vaadin.view.VaadinView;
import r01f.util.types.collections.CollectionUtils;
import x47b.model.oids.X47BIDs.X47BPersistableObjectID;
import x47b.model.oids.X47BOIDs.X47BPersistableObjectOID;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionID;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionServiceID;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionServiceLocationID;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrganizationID;
import x47b.model.oids.X47BOrganizationalIDs.X47BWorkPlaceID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceLocationOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrganizationOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BWorkPlaceOID;
import x47b.model.org.X47BOrgObjectRef;

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

	private final Grid<PB01ViewObjForSearchResultItem> _grid = new Grid<>();

	private ListDataProvider<PB01ViewObjForSearchResultItem> _gridDataProvider;


/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01MainGridView(final UII18NService i18n,
							final PB01MainViewPresenter presenter,
							// what happens when the user clicks on an organizational entity
							final PB01OrgEntityClickEventListener<X47BOrganizationOID,X47BOrganizationID> orgClickEventListener,
						    final PB01OrgEntityClickEventListener<X47BOrgDivisionOID,X47BOrgDivisionID> orgDivClickEventListener,
						    final PB01OrgEntityClickEventListener<X47BOrgDivisionServiceOID,X47BOrgDivisionServiceID> orgDivSrvcClickEventListener,
						    final PB01OrgEntityClickEventListener<X47BOrgDivisionServiceLocationOID,X47BOrgDivisionServiceLocationID> orgDivSrvcLocClickEventListener,
						    final PB01OrgEntityClickEventListener<X47BWorkPlaceOID,X47BWorkPlaceID> workPlaceClickEventListener) {
		_i18n = i18n;
		_presenter = presenter;

        _grid.setStyleName( "stripes" );
        _grid.setSizeFull();
        _grid.setHeightMode( HeightMode.UNDEFINED );

        _buildGridColumns(orgClickEventListener,
        				  orgDivClickEventListener,
        				  orgDivSrvcClickEventListener,
        				  orgDivSrvcLocClickEventListener,
        				  workPlaceClickEventListener);

        this.addComponent(_grid);
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
	private void _buildGridColumns(final PB01OrgEntityClickEventListener<X47BOrganizationOID,X47BOrganizationID> orgClickEventListener,
								   final PB01OrgEntityClickEventListener<X47BOrgDivisionOID,X47BOrgDivisionID> orgDivClickEventListener,
								   final PB01OrgEntityClickEventListener<X47BOrgDivisionServiceOID,X47BOrgDivisionServiceID> orgDivSrvcClickEventListener,
								   final PB01OrgEntityClickEventListener<X47BOrgDivisionServiceLocationOID,X47BOrgDivisionServiceLocationID> orgDivSrvcLocClickEventListener,
								   final PB01OrgEntityClickEventListener<X47BWorkPlaceOID,X47BWorkPlaceID> workPlaceClickEventListener) {
        // OBJECT TYPE
		_grid.addColumn( PB01ViewObjForSearchResultItem::getOrgObjectType )
				 .setCaption( _i18n.getMessage("pb01.view.grid.orgObjectType") )
				 .setResizable(false)
				 .setId( "objType" );
		// Number of raised alarms
		_grid.addComponentColumn(this::_createRaisedAlarmsButtonFor)
			 .setCaption( _i18n.getMessage("pb01.view.grid.raisedAlarmNum") )
			 .setDescriptionGenerator(item -> item.getAlarmLastRaiseDateExplained(_i18n))
			 .setId( "alarmRaiseCount" );
        // ORG
		_grid.addComponentColumn( item -> _createOrgEntityButtonFor(item,
																	orgClickEventListener,
																	item::getOrganizationName,							// provides the button caption
																	item::getOrganizationOid,item::getOrganizationId) )	// provides the button oid & id
				 .setCaption( _i18n.getMessage("pb01.view.grid.org") )
				 .setDescriptionGenerator(PB01ViewObjForSearchResultItem::getOrganizationHint)
				 .setResizable(true)
				 .setId( "org" );
        // ORG DIVISION
		_grid.addComponentColumn( item -> _createOrgEntityButtonFor(item,
																	orgDivClickEventListener,
																	item::getOrgDivisionName,							// provides the button caption
																	item::getOrgDivisionOid,item::getOrgDivisionId) )	// provides the button oid & id
				 .setCaption( _i18n.getMessage("pb01.view.grid.orgDivision") )
				 .setDescriptionGenerator(PB01ViewObjForSearchResultItem::getOrgDivisionHint)
				 .setResizable(true)
				 .setId( "orgDivision" );
        // ORG DIVISION SERVICE
		_grid.addComponentColumn( item -> _createOrgEntityButtonFor(item,
																	orgDivSrvcClickEventListener,
																	item::getOrgDivisionServiceName,								// provides the button caption
																	item::getOrgDivisionServiceOid,item::getOrgDivisionServiceId) )	// provides the button oid & id
				 .setCaption( _i18n.getMessage("pb01.view.grid.orgDivisionService") )
				 .setDescriptionGenerator(PB01ViewObjForSearchResultItem::getOrgDivisionServiceHint)
				 .setResizable(true)
				 .setId( "orgDivisionService" );
        // ORG DIVISION SERVICE LOCATION
		_grid.addComponentColumn( item -> _createOrgEntityButtonFor(item,
																	orgDivSrvcLocClickEventListener,
																	item::getOrgDivisionServiceLocationName,										// provides the button caption
																	item::getOrgDivisionServiceLocationOid,item::getOrgDivisionServiceLocationId) )	// provides the button oid & id
				 .setCaption( _i18n.getMessage("pb01.view.grid.orgDivisionServiceLocation") )
				 .setDescriptionGenerator(PB01ViewObjForSearchResultItem::getOrgDivisionServiceLocationHint)
				 .setResizable(true)
				 .setId( "orgDivisionServiceLocation" );
        // WORKPLACE
		_grid.addComponentColumn( item -> _createOrgEntityButtonFor(item,
																	workPlaceClickEventListener,
																	item::getWorkPlaceName,							// provides the button caption
																	item::getWorkPlaceOid,item::getWorkPlaceId) )	// provides the button oid & id
				 .setCaption( _i18n.getMessage("pb01.view.grid.workPlace") )
				 .setDescriptionGenerator(PB01ViewObjForSearchResultItem::getWorkPlaceHint)
				 .setResizable(true)
				 .setId( "workPlaceId" );
	}
	private Button _createRaisedAlarmsButtonFor(final PB01ViewObjForSearchResultItem item) {
		// show a link that opens a popup that shows the list of raised alarms
		final Button btn = new Button(Long.toString(item.getAlarmRaiseCount()));
		btn.addStyleName(ValoTheme.BUTTON_LINK);
		return btn;
	}
	private <O extends X47BPersistableObjectOID,I extends X47BPersistableObjectID<O>>
			Button _createOrgEntityButtonFor(final PB01ViewObjForSearchResultItem item,
										  	 final PB01OrgEntityClickEventListener<O,I> clickEventListener,
										  	 final Provider<String> buttonCaption,
										  	 final Provider<O> objOid,final Provider<I> objId) {
		// show a link that opens a popup for editing the entity
		final Button btn = new Button(buttonCaption.provideValue());
		btn.addStyleName(ValoTheme.BUTTON_LINK);
		if (clickEventListener != null) btn.addClickListener(event -> {	// just raise other event type
																		final PB01OrgEntityClickedEvent<O,I> entityClickedEvent = new PB01OrgEntityClickedEvent<>(btn,
																																							      objOid.provideValue(),objId.provideValue());
																		clickEventListener.entityClicked(entityClickedEvent);
																	  });
		return btn;
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	EVENTS
/////////////////////////////////////////////////////////////////////////////////////////
	@Accessors(prefix="_")
	public class PB01OrgEntityClickedEvent<O extends X47BPersistableObjectOID,I extends X47BPersistableObjectID<O>>
	     extends Component.Event {
		private static final long serialVersionUID = 6771268655053782852L;

		@Getter private final X47BOrgObjectRef<O,I> _objRef;

		public PB01OrgEntityClickedEvent(final Component source,
								  		 final O oid,final I id) {
			super(source);
			_objRef = new X47BOrgObjectRef<>(oid,id);
		}
	}
	public interface PB01OrgEntityClickEventListener<O extends X47BPersistableObjectOID,I extends X47BPersistableObjectID<O>>
	         extends Serializable {
		void entityClicked(PB01OrgEntityClickedEvent<O,I> event);
	}
}
