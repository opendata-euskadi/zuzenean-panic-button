package pb01t.test.entities;

import java.util.Collection;

import org.springframework.util.Assert;

import lombok.extern.slf4j.Slf4j;
import pb01a.client.api.PB01APanicButtonClientAPI;
import pb01a.client.api.sub.delegates.PB01AClientAPIDelegateForWorkPlaceCRUDServices;
import pb01a.client.api.sub.delegates.PB01AClientAPIDelegateForWorkPlaceFindServices;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AWorkPlaceID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AWorkPlaceOID;
import pb01a.model.org.PB01AOrgDivision;
import pb01a.model.org.PB01AOrgDivisionService;
import pb01a.model.org.PB01AOrgDivisionServiceLocation;
import pb01a.model.org.PB01AOrganization;
import pb01a.model.org.PB01AWorkPlace;
import pb01a.model.org.summaries.PB01ASummarizedWorkPlace;
import pb01t.test.entities.mock.PB01TMockWorkPlaceFactory;
import r01f.locale.Language;
import r01f.test.persistence.TestPersistableModelObjectManager;
import r01f.util.types.collections.CollectionUtils;

@Slf4j
public class PB01TWorkPlaceTest 
	 extends PB01TOrganizationalEntityPersistenceTestBase<PB01AWorkPlaceOID,PB01AWorkPlaceID,PB01AWorkPlace> {
/////////////////////////////////////////////////////////////////////////////////////////
//  FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	private final PB01AOrganization _organization;
	private final PB01AOrgDivision _division;
	private final PB01AOrgDivisionService _service;
	private final PB01AOrgDivisionServiceLocation _location;
	
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01TWorkPlaceTest(final PB01APanicButtonClientAPI api,
						 		  	  final PB01AOrganization organization,
						 		  	  final PB01AOrgDivision division,
						 		  	  final PB01AOrgDivisionService service,
						 		  	  final PB01AOrgDivisionServiceLocation location) {
		super(TestPersistableModelObjectManager.create(PB01AWorkPlace.class,new PB01TMockWorkPlaceFactory(organization,division,service,location),
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
		PB01AClientAPIDelegateForWorkPlaceFindServices findApi = getClientFindApiAs(PB01AClientAPIDelegateForWorkPlaceFindServices.class);
		
		// Find workPlaces by locations
		log.warn("[{} WorkPlaces]",_location.getId());
		Collection<PB01AWorkPlace> locationWorkPlaces = findApi.findByOrgDivisionServiceLocation(_location.getOid());
		
		Assert.isTrue(CollectionUtils.hasData(locationWorkPlaces));		
		for(PB01AWorkPlace workPlace : locationWorkPlaces) {
			log.warn("\t-{} > {}",workPlace.getOid(),workPlace.getName().getIn(Language.SPANISH));
		}
		
		// Find summaries by location
		log.warn("[{}  WorkPlace's summaries]",_location.getId());
		Collection<PB01ASummarizedWorkPlace> workPlacesSummaries = findApi.findSummariesByOrgDivisionServiceLocation(_location.getOid(),Language.SPANISH);
		Assert.isTrue(CollectionUtils.hasData(workPlacesSummaries));
		
		if (CollectionUtils.hasData(workPlacesSummaries)) {
			for (PB01ASummarizedWorkPlace workPlaceSummary : workPlacesSummaries) {
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
		PB01AClientAPIDelegateForWorkPlaceFindServices findApi = getClientFindApiAs(PB01AClientAPIDelegateForWorkPlaceFindServices.class);
		Collection<PB01AWorkPlace> locationWorkPlaces = findApi.findByOrgDivisionServiceLocation(_location.getOid());
		
		// Find work place by host
		PB01AWorkPlace anyWorkPlace = CollectionUtils.of(locationWorkPlaces)
											.pickOneElement();
		
		System.out.println("\n\n\n\n--------------------------------------------------------------------");
		System.out.println("[WorkPlaces by host] (" + anyWorkPlace.getId());
		PB01AClientAPIDelegateForWorkPlaceCRUDServices crudApi = getClientCRUDApiAs(PB01AClientAPIDelegateForWorkPlaceCRUDServices.class);
		PB01AWorkPlace anyWorkPlaceById = crudApi.loadById(anyWorkPlace.getId());
		Assert.isTrue(anyWorkPlaceById.getOid().equals(anyWorkPlace.getOid()));
		
		// [2]: teardown created mock objects
		_managesTestMockObjects.tearDownCreatedMockObjs();
	}

}
