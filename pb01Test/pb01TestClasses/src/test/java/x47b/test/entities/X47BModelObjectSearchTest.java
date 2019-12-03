package x47b.test.entities;

import org.junit.Assert;

import r01f.locale.Language;
import r01f.model.search.SearchResults;
import x47b.client.api.X47BPanicButtonClientAPI;
import x47b.model.org.X47BOrgDivision;
import x47b.model.org.X47BOrgDivisionService;
import x47b.model.org.X47BOrgDivisionServiceLocation;
import x47b.model.org.X47BOrganization;
import x47b.model.org.X47BWorkPlace;
import x47b.model.search.X47BSearchFilterForPanicButtonOrganizationalEntity;
import x47b.model.search.X47BSearchResultItemForPanicButtonOrganizationalEntity;

public class X47BModelObjectSearchTest {
/////////////////////////////////////////////////////////////////////////////////////////
//  FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	private final X47BPanicButtonClientAPI _api;
	
	private final X47BOrganization _org;
	private final X47BOrgDivision _division;
	private final X47BOrgDivisionService _service;
	private final X47BOrgDivisionServiceLocation _location;
	private final X47BWorkPlace _workPlace;
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BModelObjectSearchTest(final X47BPanicButtonClientAPI api,
									 final X47BOrganization org,                  
									 final X47BOrgDivision division,              
									 final X47BOrgDivisionService service,        
									 final X47BOrgDivisionServiceLocation location,
									 final X47BWorkPlace workPlace) {
		_api = api;
		_org = org;
		_division = division;
		_service = service;
		_location = location;
		_workPlace = workPlace;
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  
/////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	public void doTest() {
		// create a search filter for every entity
		try {
			@SuppressWarnings("unchecked")
			X47BSearchFilterForPanicButtonOrganizationalEntity filter = new X47BSearchFilterForPanicButtonOrganizationalEntity(X47BOrganization.class,
																											 X47BOrgDivision.class,
																											 X47BOrgDivisionService.class,
																								   			 X47BOrgDivisionServiceLocation.class,
																								   			 X47BWorkPlace.class);
			filter.belongingTo(_org.getOid());
			filter.withText("WorkPlace").in(Language.SPANISH);
			SearchResults<X47BSearchFilterForPanicButtonOrganizationalEntity,
						  X47BSearchResultItemForPanicButtonOrganizationalEntity> searchResults = _api.entitySearchAPI()
						  															   	.search(filter)
						  															   	.firstPage();
				Assert.assertTrue(searchResults != null && searchResults.getTotalItemsCount() > 0);
				System.out.println("--->Found: " + searchResults.getTotalItemsCount() + " items searching by " + filter.toCriteriaString());

				for (X47BSearchResultItemForPanicButtonOrganizationalEntity item : searchResults.getPageItems()) {
					System.out.println("\t>" + item.getHierarchyPath() + 
											   " > alarms raised: " + item.getAlarmRaiseCount() + " (last one at " + item.getLastAlarmRaiseDate() + ")");
				}
		} catch(Throwable th) {
			th.printStackTrace(System.out);
		}
	}
}
