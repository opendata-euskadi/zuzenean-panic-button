package pb01t.test.entities;

import java.util.Collection;

import org.junit.Assert;

import lombok.extern.slf4j.Slf4j;
import pb01a.client.api.PB01APanicButtonClientAPI;
import pb01a.client.api.sub.delegates.PB01AClientAPIDelegateForOrgDivisionServiceFindServices;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionServiceID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionServiceOID;
import pb01a.model.org.PB01AOrgDivision;
import pb01a.model.org.PB01AOrgDivisionService;
import pb01a.model.org.PB01AOrganization;
import pb01a.model.org.summaries.PB01ASummarizedOrgDivisionService;
import pb01t.test.entities.mock.PB01TMockOrgDivisionServiceFactory;
import r01f.locale.Language;
import r01f.test.persistence.TestPersistableModelObjectManager;
import r01f.util.types.collections.CollectionUtils;

@Slf4j
public class PB01TDivisionServiceTest 
	 extends PB01TOrganizationalEntityPersistenceTestBase<PB01AOrgDivisionServiceOID,PB01AOrgDivisionServiceID,PB01AOrgDivisionService> {
/////////////////////////////////////////////////////////////////////////////////////////
//  FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	private final PB01AOrganization _organization;
	private final PB01AOrgDivision _division;
	
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01TDivisionServiceTest(final PB01APanicButtonClientAPI api,
						 			  		   final PB01AOrganization organization,
						 			  		   final PB01AOrgDivision division) {
		super(TestPersistableModelObjectManager.create(PB01AOrgDivisionService.class,new PB01TMockOrgDivisionServiceFactory(organization,division),
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
		PB01AClientAPIDelegateForOrgDivisionServiceFindServices findApi = getClientFindApiAs(PB01AClientAPIDelegateForOrgDivisionServiceFindServices.class);
		
		// Find services by divisions
		log.warn("[{} division services]",_division.getId());
		Collection<PB01AOrgDivisionService> services = findApi.findByOrgDivision(_division.getOid());
		
		Assert.assertTrue(CollectionUtils.hasData(services));		
		for(PB01AOrgDivisionService service : services) {
			log.warn("\t-{} > {}",service.getOid(),service.getName().getIn(Language.SPANISH));
		}
		
		// Find service summaries by division
		log.warn("[{} division services summaries]",_division.getId());
		Collection<PB01ASummarizedOrgDivisionService> servicesSummaries = findApi.findSummariesByOrgDivision(_division.getOid(),Language.SPANISH);
		Assert.assertTrue(CollectionUtils.hasData(servicesSummaries));
		
		if (CollectionUtils.hasData(servicesSummaries)) {
			for (PB01ASummarizedOrgDivisionService serviceSummary : servicesSummaries) {
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
