package x47b.test.entities;

import java.util.Collection;

import org.junit.Assert;

import lombok.extern.slf4j.Slf4j;
import r01f.locale.Language;
import r01f.test.persistence.TestPersistableModelObjectManager;
import r01f.util.types.collections.CollectionUtils;
import x47b.client.api.X47BPanicButtonClientAPI;
import x47b.client.api.sub.delegates.X47BClientAPIDelegateForOrgDivisionServiceFindServices;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionServiceID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceOID;
import x47b.model.org.X47BOrgDivision;
import x47b.model.org.X47BOrgDivisionService;
import x47b.model.org.X47BOrganization;
import x47b.model.org.summaries.X47BSummarizedOrgDivisionService;
import x47b.test.entities.mock.X47BMockOrgDivisionServiceFactory;

@Slf4j
public class X47BDivisionServiceTest 
	 extends X47BOrganizationalEntityPersistenceTestBase<X47BOrgDivisionServiceOID,X47BOrgDivisionServiceID,X47BOrgDivisionService> {
/////////////////////////////////////////////////////////////////////////////////////////
//  FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	private final X47BOrganization _organization;
	private final X47BOrgDivision _division;
	
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BDivisionServiceTest(final X47BPanicButtonClientAPI api,
						 			  		   final X47BOrganization organization,
						 			  		   final X47BOrgDivision division) {
		super(TestPersistableModelObjectManager.create(X47BOrgDivisionService.class,new X47BMockOrgDivisionServiceFactory(organization,division),
													   api.orgDivisionServicesAPI().getForCRUD(),
													   0l),
			  api.orgDivisionServicesAPI().getForCRUD(),api.orgDivisionServicesAPI().getForFind());
		_organization = organization;
		_division = division;
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
		X47BClientAPIDelegateForOrgDivisionServiceFindServices findApi = getClientFindApiAs(X47BClientAPIDelegateForOrgDivisionServiceFindServices.class);
		
		// Find services by divisions
		log.warn("[{} division services]",_division.getId());
		Collection<X47BOrgDivisionService> services = findApi.findByOrgDivision(_division.getOid());
		
		Assert.assertTrue(CollectionUtils.hasData(services));		
		for(X47BOrgDivisionService service : services) {
			log.warn("\t-{} > {}",service.getOid(),service.getName().getIn(Language.SPANISH));
		}
		
		// Find service summaries by division
		log.warn("[{} division services summaries]",_division.getId());
		Collection<X47BSummarizedOrgDivisionService> servicesSummaries = findApi.findSummariesByOrgDivision(_division.getOid(),Language.SPANISH);
		Assert.assertTrue(CollectionUtils.hasData(servicesSummaries));
		
		if (CollectionUtils.hasData(servicesSummaries)) {
			for (X47BSummarizedOrgDivisionService serviceSummary : servicesSummaries) {
				log.warn("\t-{} > {}: {}",serviceSummary.getOid(),serviceSummary.getId(),serviceSummary.getName());
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
