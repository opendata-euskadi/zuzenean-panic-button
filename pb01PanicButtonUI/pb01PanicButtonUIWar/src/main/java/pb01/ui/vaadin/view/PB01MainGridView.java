package pb01.ui.vaadin.view;

import java.util.Collection;

import com.google.common.collect.Lists;
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;

import lombok.extern.slf4j.Slf4j;
import r01f.ui.i18n.UII18NService;
import r01f.ui.presenter.UIPresenterSubscriber;
import r01f.ui.vaadin.view.VaadinView;
import r01f.util.types.collections.CollectionUtils;
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
import x47b.model.org.X47BOrganizationalObjectRef;

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

/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01MainGridView(final UII18NService i18n,
							final PB01MainViewPresenter presenter) {
		_i18n = i18n;
		_presenter = presenter;

        _grid.setStyleName( "stripes" );
        _grid.setSizeFull();
        _grid.setHeightMode( HeightMode.UNDEFINED );

        _buildGridColumns();

        this.addComponent(_grid);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	public void refresh(final X47BOrganizationalObjectRef<X47BOrganizationOID,X47BOrganizationID> selectedOrg,
						final X47BOrganizationalObjectRef<X47BOrgDivisionOID,X47BOrgDivisionID> selectedOrgDiv,
						final X47BOrganizationalObjectRef<X47BOrgDivisionServiceOID,X47BOrgDivisionServiceID> selectedOrgDivSrvc,
						final X47BOrganizationalObjectRef<X47BOrgDivisionServiceLocationOID,X47BOrgDivisionServiceLocationID> selectedOrgDivSrvcLoc,
						final X47BOrganizationalObjectRef<X47BWorkPlaceOID,X47BWorkPlaceID> selectedWorkPlace) {
		log.info( "...refresh grid" );
		_presenter.onGridDataNeeded(selectedOrg,selectedOrgDiv,selectedOrgDivSrvc,selectedOrgDivSrvcLoc,selectedWorkPlace,
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
			_grid.setItems(items);
		} else {
			// an empty grid
			_grid.setItems(Lists.newArrayList());
		}
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	private void _buildGridColumns() {
        // OBJECT TYPE
		_grid.addColumn( PB01ViewObjForSearchResultItem::getOrgObjectType )
				 .setCaption( _i18n.getMessage("pb01.view.grid.orgObjectType") )
				 .setResizable(false)
				 .setId( "objType" );
        // ORG
		_grid.addColumn( PB01ViewObjForSearchResultItem::getOrganizationId )
				 .setCaption( _i18n.getMessage("pb01.view.grid.org") )
				 .setDescriptionGenerator(PB01ViewObjForSearchResultItem::getOrganizationName)
				 .setResizable(true)
				 .setId( "orgId" );
        // ORG DIVISION
		_grid.addColumn( PB01ViewObjForSearchResultItem::getOrgDivisionId )
				 .setCaption( _i18n.getMessage("pb01.view.grid.orgDivision") )
				 .setDescriptionGenerator(PB01ViewObjForSearchResultItem::getOrgDivisionName)
				 .setResizable(true)
				 .setId( "orgDivisionId" );
        // ORG DIVISION SERVICE
		_grid.addColumn( PB01ViewObjForSearchResultItem::getOrgDivisionServiceId )
				 .setCaption( _i18n.getMessage("pb01.view.grid.orgDivisionService") )
				 .setDescriptionGenerator(PB01ViewObjForSearchResultItem::getOrgDivisionServiceName)
				 .setResizable(true)
				 .setId( "orgDivisionServiceId" );
        // ORG DIVISION SERVICE LOCATION
		_grid.addColumn( PB01ViewObjForSearchResultItem::getOrgDivisionServiceLocationId )
				 .setCaption( _i18n.getMessage("pb01.view.grid.orgDivisionServiceLocation") )
				 .setDescriptionGenerator(PB01ViewObjForSearchResultItem::getOrgDivisionServiceLocationName)
				 .setResizable(true)
				 .setId( "orgDivisionServiceLocationId" );
        // WORKPLACE
		_grid.addColumn( PB01ViewObjForSearchResultItem::getWorkPlaceId )
				 .setCaption( _i18n.getMessage("pb01.view.grid.workPlace") )
				 .setDescriptionGenerator(PB01ViewObjForSearchResultItem::getWorkPlaceName)
				 .setResizable(true)
				 .setId( "workPlaceId" );
	}
}
