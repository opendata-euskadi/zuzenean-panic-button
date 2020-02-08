package pb01t.test.entities;

import java.util.Collection;

import org.junit.Assert;

import lombok.extern.slf4j.Slf4j;
import pb01a.client.api.PB01APanicButtonClientAPI;
import pb01a.client.api.sub.delegates.PB01AClientAPIDelegateForOrgDivisionServiceLocationFindServices;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionServiceLocationID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionServiceLocationOID;
import pb01a.model.org.PB01AOrgDivision;
import pb01a.model.org.PB01AOrgDivisionService;
import pb01a.model.org.PB01AOrgDivisionServiceLocation;
import pb01a.model.org.PB01AOrganization;
import pb01a.model.org.summaries.PB01ASummarizedOrgDivisionServiceLocation;
import pb01t.test.entities.mock.PB01TMockOrgDivisionServiceLocationFactory;
import r01f.locale.Language;
import r01f.test.persistence.TestPersistableModelObjectManager;
import r01f.util.types.collections.CollectionUtils;

@Slf4j
public class PB01TOrgDivisionServiceLocationTest 
	 extends PB01TOrganizationalEntityPersistenceTestBase<PB01AOrgDivisionServiceLocationOID,PB01AOrgDivisionServiceLocationID,PB01AOrgDivisionServiceLocation> {
/////////////////////////////////////////////////////////////////////////////////////////
//  FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	private final PB01AOrganization _organization;
	private final PB01AOrgDivision _division;
	private final PB01AOrgDivisionService _service;
	
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01TOrgDivisionServiceLocationTest(final PB01APanicButtonClientAPI api,
						 			  		  		   final PB01AOrganization organization,
						 			  		  		   final PB01AOrgDivision division,
						 			  		  		   final PB01AOrgDivisionService service) {
		super(TestPersistableModelObjectManager.create(PB01AOrgDivisionServiceLocation.class,new PB01TMockOrgDivisionServiceLocationFactory(organization,division,service),
													   api.orgDivisionServiceLocationsAPI().getForCRUD(),
													   0l),
			  api.orgDivisionServiceLocationsAPI().getForCRUD(),api.orgDivisionServiceLocationsAPI().getForFind());
		_organization = organization;
		_division = division;
		_service = service;
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
		// [0]: Create 5 divisions
		_managesTestMockObjects.setUpMockObjs(5);
		
		// [1]: Test
		PB01AClientAPIDelegateForOrgDivisionServiceLocationFindServices findApi = getClientFindApiAs(PB01AClientAPIDelegateForOrgDivisionServiceLocationFindServices.class);
		
		// Find locations by service
		log.warn("[{} sevice locations]",_service.getId());
		Collection<PB01AOrgDivisionServiceLocation> locs = findApi.findByOrgDivisionService(_service.getOid());
		
		Assert.assertTrue(CollectionUtils.hasData(locs));		
		for(PB01AOrgDivisionServiceLocation loc : locs) {
			log.warn("\t-{} > {}",loc.getOid(),loc.getName().getIn(Language.SPANISH));
		}
		
		// Find location summaries by service
		log.warn("[{} service locations summaries]",_service.getId());
		Collection<PB01ASummarizedOrgDivisionServiceLocation> locationsSummaries = findApi.findSummariesByOrgDivisionService(_service.getOid(),Language.SPANISH);
		Assert.assertTrue(CollectionUtils.hasData(locationsSummaries));
		
		if (CollectionUtils.hasData(locationsSummaries)) {
			for (PB01ASummarizedOrgDivisionServiceLocation locSummary : locationsSummaries) {
				log.warn("\t-{} > {}: {}",locSummary.getOid(),locSummary.getId(),locSummary.getName());
			}
		}
		
		// [2]: Tear down created model objects
		_managesTestMockObjects.tearDownCreatedMockObjs();
	}
	@Override
	public void testOtherMethods() {
		// nothing
	}
}
