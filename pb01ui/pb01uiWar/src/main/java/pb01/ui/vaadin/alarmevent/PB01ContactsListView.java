package pb01.ui.vaadin.alarmevent;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import r01f.types.contact.EMail;
import r01f.types.contact.Phone;
import r01f.types.contact.ValidatedContactMean;
import r01f.ui.i18n.UII18NService;
import r01f.util.types.collections.CollectionUtils;
import x47b.model.org.X47BOrgObjectType;

@Slf4j
public class PB01ContactsListView
	 extends Window {

	private static final long serialVersionUID = 4474681131796734900L;
/////////////////////////////////////////////////////////////////////////////////////////
//  FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	private final transient UII18NService _i18n;
	private final Grid<PB01EffectiveContactByOrg> _grid = new Grid<>();

	private ListDataProvider<PB01EffectiveContactByOrg> _gridDataProvider;

/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public PB01ContactsListView(final UII18NService i18n) {
		_i18n = i18n;

		_buildGridColumns();
        _grid.setStyleName( "stripes" );
        _grid.setSizeFull();
        _grid.setHeightMode( HeightMode.ROW );
        _grid.setHeightByRows(10);
        _grid.setWidth("100%");

		// Layout
		final VerticalLayout layout = new VerticalLayout();
		layout.setMargin( true );
		layout.addComponent(_grid);

		this.setContent(layout);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	PUBLIC ENTRY POINT
/////////////////////////////////////////////////////////////////////////////////////////
	public void paintGridPhoneItems(final Map<X47BOrgObjectType,Collection<Phone>> items, String captionKey) {		
		_grid.getColumn("orgContacts").setCaption(_i18n.getMessage(captionKey));		
		List<PB01EffectiveContactByOrg> phoneOrgTypeItems = Lists.newArrayList(Iterables.transform(items.keySet(),
	        new Function <X47BOrgObjectType, PB01EffectiveContactByOrg>() {
	            @Override
	            public PB01EffectiveContactByOrg apply(X47BOrgObjectType orgType) {
	                return new PB01EffectiveContactByOrg(orgType.name(), 
	                									Joiner.on(",").join(Lists.newArrayList(Iterables.transform(items.get(orgType), 
	                											new Function<ValidatedContactMean, String>(){
	                												@Override
	                													public String apply(ValidatedContactMean p) {
	                														return p.asString();		                			
	                													}			
	                											}))));	                				
	            }
	        }));		
		// set the data
		if (CollectionUtils.hasData(items)) {
			_gridDataProvider = DataProvider.ofCollection(phoneOrgTypeItems);
		} else {
			_gridDataProvider = DataProvider.ofCollection(Lists.newArrayList()); // an empty grid
		}
		_grid.setDataProvider(_gridDataProvider);
		this.setVisible(CollectionUtils.hasData(items));
	}
	
	public void paintGridEmailItems(final Map<X47BOrgObjectType,Collection<EMail>> items, String captionKey) {		
		_grid.getColumn("orgContacts").setCaption(_i18n.getMessage(captionKey));		
		List<PB01EffectiveContactByOrg> contactOrgTypeItems = Lists.newArrayList(Iterables.transform(items.keySet(),
	        new Function <X47BOrgObjectType, PB01EffectiveContactByOrg>() {
	            @Override
	            public PB01EffectiveContactByOrg apply(X47BOrgObjectType orgType) {
	                return new PB01EffectiveContactByOrg(orgType.name(), 
	                									Joiner.on(",").join(Lists.newArrayList(Iterables.transform(items.get(orgType), 
	                											new Function<ValidatedContactMean, String>(){
	                												@Override
	                													public String apply(ValidatedContactMean p) {
	                														return p.asString();		                			
	                													}			
	                											}))));	                				
	            }
	        }));		
		// set the data
		if (CollectionUtils.hasData(items)) {
			_gridDataProvider = DataProvider.ofCollection(contactOrgTypeItems);
		} else {
			_gridDataProvider = DataProvider.ofCollection(Lists.newArrayList()); // an empty grid
		}
		_grid.setDataProvider(_gridDataProvider);
		this.setVisible(CollectionUtils.hasData(items));
	}	
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	private void _buildGridColumns() {
        // Organization
		_grid.addColumn( PB01EffectiveContactByOrg::getOrgTypeCaption )
				 .setCaption( _i18n.getMessage("pb01.org") )
				 .setResizable(true)
				 .setId( "orgtype" );
		// Phones
		_grid.addColumn( PB01EffectiveContactByOrg::getContacts )
			 .setCaption( _i18n.getMessage("pb01.org.phones") )
			 .setResizable(true)
			 .setId( "orgContacts" );
	}

	class PB01EffectiveContactByOrg {		
		@Getter @Setter private String orgType;
		@Getter @Setter private String contacts;
		
		PB01EffectiveContactByOrg (String newOrgType, String newContacts) {
			orgType = newOrgType;
			contacts = newContacts;
		}
		
		public String getOrgTypeCaption() {								
			String i18nKey = "pb01.org";
			if (X47BOrgObjectType.valueOf(orgType) ==   X47BOrgObjectType.ORGANIZATION) {
				i18nKey = "pb01.org";
			} else if (X47BOrgObjectType.valueOf(orgType) ==   X47BOrgObjectType.ORG_DIVISION) {
				i18nKey = "pb01.org.division";
			} else if (X47BOrgObjectType.valueOf(orgType) ==   X47BOrgObjectType.ORG_DIVISION_SERVICE) {
				i18nKey = "pb01.org.service";
			} else if (X47BOrgObjectType.valueOf(orgType) ==   X47BOrgObjectType.ORG_DIVISION_SERVICE_LOCATION) {
				i18nKey = "pb01.org.location";
			} else if (X47BOrgObjectType.valueOf(orgType) ==   X47BOrgObjectType.WORKPLACE) {
				i18nKey = "pb01.org.workPlace";
			} 
			return _i18n.getMessage(i18nKey);
		}
		
		
	}
}


