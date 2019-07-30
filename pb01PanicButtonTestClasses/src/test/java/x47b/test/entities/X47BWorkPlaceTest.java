package x47b.test.entities;

import java.util.Collection;

import org.springframework.util.Assert;

import lombok.extern.slf4j.Slf4j;
import r01f.locale.Language;
import r01f.test.persistence.TestPersistableModelObjectManager;
import r01f.util.types.collections.CollectionUtils;
import x47b.client.api.X47BPanicButtonClientAPI;
import x47b.client.api.sub.delegates.X47BClientAPIDelegateForWorkPlaceCRUDServices;
import x47b.client.api.sub.delegates.X47BClientAPIDelegateForWorkPlaceFindServices;
import x47b.model.oids.X47BOrganizationalIDs.X47BWorkPlaceID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BWorkPlaceOID;
import x47b.model.org.X47BOrgDivision;
import x47b.model.org.X47BOrgDivisionService;
import x47b.model.org.X47BOrgDivisionServiceLocation;
import x47b.model.org.X47BOrganization;
import x47b.model.org.X47BWorkPlace;
import x47b.model.org.summaries.X47BSummarizedWorkPlace;
import x47b.test.entities.mock.X47BMockWorkPlaceFactory;

@Slf4j
public class X47BWorkPlaceTest 
	 extends X47BOrganizationalEntityPersistenceTestBase<X47BWorkPlaceOID,X47BWorkPlaceID,X47BWorkPlace> {
/////////////////////////////////////////////////////////////////////////////////////////
//  FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	private final X47BOrganization _organization;
	private final X47BOrgDivision _division;
	private final X47BOrgDivisionService _service;
	private final X47BOrgDivisionServiceLocation _location;
	
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BWorkPlaceTest(final X47BPanicButtonClientAPI api,
						 		  	  final X47BOrganization organization,
						 		  	  final X47BOrgDivision division,
						 		  	  final X47BOrgDivisionService service,
						 		  	  final X47BOrgDivisionServiceLocation location) {
		super(TestPersistableModelObjectManager.create(X47BWorkPlace.class,new X47BMockWorkPlaceFactory(organization,division,service,location),
													   api.workPlacesAPI().getForCRUD(),
													   0l),
			  api.workPlacesAPI().getForCRUD(),api.workPlacesAPI().getForFind());
		_organization = organization;
		_division = division;
		_service = service;
		_location = location;
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void testOtherCRUDMethods() {
		// nothing
	}
	@Override
	public void testOtherFindMethods() {
		// [0]: Setup mock objects
		_managesTestMockObjects.setUpMockObjs(5);
		
		// [1]: Test
		X47BClientAPIDelegateForWorkPlaceFindServices findApi = getClientFindApiAs(X47BClientAPIDelegateForWorkPlaceFindServices.class);
		
		// Find workPlaces by locations
		log.warn("[{} WorkPlaces]",_location.getId());
		Collection<X47BWorkPlace> locationWorkPlaces = findApi.findByOrgDivisionServiceLocation(_location.getOid());
		
		Assert.isTrue(CollectionUtils.hasData(locationWorkPlaces));		
		for(X47BWorkPlace workPlace : locationWorkPlaces) {
			log.warn("\t-{} > {}",workPlace.getOid(),workPlace.getName().getIn(Language.SPANISH));
		}
		
		// Find summaries by location
		log.warn("[{}  WorkPlace's summaries]",_location.getId());
		Collection<X47BSummarizedWorkPlace> workPlacesSummaries = findApi.findSummariesByOrgDivisionServiceLocation(_location.getOid(),Language.SPANISH);
		Assert.isTrue(CollectionUtils.hasData(workPlacesSummaries));
		
		if (CollectionUtils.hasData(workPlacesSummaries)) {
			for (X47BSummarizedWorkPlace workPlaceSummary : workPlacesSummaries) {
				log.warn("\t-{} > {}: {}",workPlaceSummary.getOid(),workPlaceSummary.getId(),workPlaceSummary.getName());
			}
		}
		
		// [2]: teardown created mock objects
		_managesTestMockObjects.tearDownCreatedMockObjs();
	}
	@Override
	public void testOtherMethods() {
		// [0]: Setup mock objects
		_managesTestMockObjects.setUpMockObjs(5);
		
		// [1]: Test
		// Find any already created workPlace
		X47BClientAPIDelegateForWorkPlaceFindServices findApi = getClientFindApiAs(X47BClientAPIDelegateForWorkPlaceFindServices.class);
		Collection<X47BWorkPlace> locationWorkPlaces = findApi.findByOrgDivisionServiceLocation(_location.getOid());
		
		// Find work place by host
		X47BWorkPlace anyWorkPlace = CollectionUtils.of(locationWorkPlaces)
											.pickOneElement();
		
		System.out.println("\n\n\n\n--------------------------------------------------------------------");
		System.out.println("[WorkPlaces by host] (" + anyWorkPlace.getId());
		X47BClientAPIDelegateForWorkPlaceCRUDServices crudApi = getClientCRUDApiAs(X47BClientAPIDelegateForWorkPlaceCRUDServices.class);
		X47BWorkPlace anyWorkPlaceById = crudApi.loadById(anyWorkPlace.getId());
		Assert.isTrue(anyWorkPlaceById.getOid().equals(anyWorkPlace.getOid()));
		
		// [2]: teardown created mock objects
		_managesTestMockObjects.tearDownCreatedMockObjs();
	}

}
