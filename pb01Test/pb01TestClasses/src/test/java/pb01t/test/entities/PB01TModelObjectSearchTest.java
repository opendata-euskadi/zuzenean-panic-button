package pb01t.test.entities;

import org.junit.Assert;

import pb01a.client.api.PB01APanicButtonClientAPI;
import pb01a.model.org.PB01AOrgDivision;
import pb01a.model.org.PB01AOrgDivisionService;
import pb01a.model.org.PB01AOrgDivisionServiceLocation;
import pb01a.model.org.PB01AOrganization;
import pb01a.model.org.PB01AWorkPlace;
import pb01a.model.search.PB01ASearchFilterForPanicButtonOrganizationalEntity;
import pb01a.model.search.PB01ASearchResultItemForPanicButtonOrganizationalEntity;
import r01f.locale.Language;
import r01f.model.search.SearchResults;

public class PB01TModelObjectSearchTest {
/////////////////////////////////////////////////////////////////////////////////////////
//  FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	private final PB01APanicButtonClientAPI _api;
	
	private final PB01AOrganization _org;
	private final PB01AOrgDivision _division;
	private final PB01AOrgDivisionService _service;
	private final PB01AOrgDivisionServiceLocation _location;
	private final PB01AWorkPlace _workPlace;
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01TModelObjectSearchTest(final PB01APanicButtonClientAPI api,
									 final PB01AOrganization org,                  
									 final PB01AOrgDivision division,              
									 final PB01AOrgDivisionService service,        
									 final PB01AOrgDivisionServiceLocation location,
									 final PB01AWorkPlace workPlace) {
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
			PB01ASearchFilterForPanicButtonOrganizationalEntity filter = new PB01ASearchFilterForPanicButtonOrganizationalEntity(PB01AOrganization.class,
																											 PB01AOrgDivision.class,
																											 PB01AOrgDivisionService.class,
																								   			 PB01AOrgDivisionServiceLocation.class,
																								   			 PB01AWorkPlace.class);
			filter.belongingTo(_org.getOid());
			filter.withText("WorkPlace").in(Language.SPANISH);
			SearchResults<PB01ASearchFilterForPanicButtonOrganizationalEntity,
						  PB01ASearchResultItemForPanicButtonOrganizationalEntity> searchResults = _api.entitySearchAPI()
						  															   	.search(filter)
						  															   	.firstPage();
				Assert.assertTrue(searchResults != null && searchResults.getTotalItemsCount() > 0);
				System.out.println("--->Found: " + searchResults.getTotalItemsCount() + " items searching by " + filter.toCriteriaString());

				for (PB01ASearchResultItemForPanicButtonOrganizationalEntity item : searchResults.getPageItems()) {
					System.out.println("\t>" + item.getHierarchyPath() + 
											   " > alarms raised: " + item.getAlarmRaiseCount() + " (last one at " + item.getLastAlarmRaiseDate() + ")");
				}
		} catch(Throwable th) {
			th.printStackTrace(System.out);
		}
	}
}
