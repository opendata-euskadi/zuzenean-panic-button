package x47b.test.entities;

import java.util.Collection;

import org.junit.Assert;

import lombok.extern.slf4j.Slf4j;
import r01f.locale.Language;
import r01f.test.persistence.TestPersistableModelObjectManager;
import r01f.util.types.collections.CollectionUtils;
import x47b.client.api.X47BPanicButtonClientAPI;
import x47b.client.api.sub.delegates.X47BClientAPIDelegateForOrgDivisionServiceLocationFindServices;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionServiceLocationID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceLocationOID;
import x47b.model.org.X47BOrgDivision;
import x47b.model.org.X47BOrgDivisionService;
import x47b.model.org.X47BOrgDivisionServiceLocation;
import x47b.model.org.X47BOrganization;
import x47b.model.org.summaries.X47BSummarizedOrgDivisionServiceLocation;
import x47b.test.entities.mock.X47BMockOrgDivisionServiceLocationFactory;

@Slf4j
public class X47BOrgDivisionServiceLocationTest 
	 extends X47BOrganizationalEntityPersistenceTestBase<X47BOrgDivisionServiceLocationOID,X47BOrgDivisionServiceLocationID,X47BOrgDivisionServiceLocation> {
/////////////////////////////////////////////////////////////////////////////////////////
//  FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	private final X47BOrganization _organization;
	private final X47BOrgDivision _division;
	private final X47BOrgDivisionService _service;
	
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BOrgDivisionServiceLocationTest(final X47BPanicButtonClientAPI api,
						 			  		  		   final X47BOrganization organization,
						 			  		  		   final X47BOrgDivision division,
						 			  		  		   final X47BOrgDivisionService service) {
		super(TestPersistableModelObjectManager.create(X47BOrgDivisionServiceLocation.class,new X47BMockOrgDivisionServiceLocationFactory(organization,division,service),
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
		X47BClientAPIDelegateForOrgDivisionServiceLocationFindServices findApi = getClientFindApiAs(X47BClientAPIDelegateForOrgDivisionServiceLocationFindServices.class);
		
		// Find locations by service
		log.warn("[{} sevice locations]",_service.getId());
		Collection<X47BOrgDivisionServiceLocation> locs = findApi.findByOrgDivisionService(_service.getOid());
		
		Assert.assertTrue(CollectionUtils.hasData(locs));		
		for(X47BOrgDivisionServiceLocation loc : locs) {
			log.warn("\t-{} > {}",loc.getOid(),loc.getName().getIn(Language.SPANISH));
		}
		
		// Find location summaries by service
		log.warn("[{} service locations summaries]",_service.getId());
		Collection<X47BSummarizedOrgDivisionServiceLocation> locationsSummaries = findApi.findSummariesByOrgDivisionService(_service.getOid(),Language.SPANISH);
		Assert.assertTrue(CollectionUtils.hasData(locationsSummaries));
		
		if (CollectionUtils.hasData(locationsSummaries)) {
			for (X47BSummarizedOrgDivisionServiceLocation locSummary : locationsSummaries) {
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
