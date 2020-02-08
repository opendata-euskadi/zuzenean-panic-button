package pb01t.test.entities;

import java.util.Collection;

import org.junit.Assert;

import lombok.extern.slf4j.Slf4j;
import pb01a.client.api.PB01APanicButtonClientAPI;
import pb01a.client.api.sub.delegates.PB01AClientAPIDelegateForOrgDivisionFindServices;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionOID;
import pb01a.model.org.PB01AOrgDivision;
import pb01a.model.org.PB01AOrganization;
import pb01a.model.org.summaries.PB01ASummarizedOrgDivision;
import pb01t.test.entities.mock.PB01TMockOrgDivisionFactory;
import r01f.locale.Language;
import r01f.test.persistence.TestPersistableModelObjectManager;
import r01f.util.types.collections.CollectionUtils;

@Slf4j
public class PB01TOrgDivisionTest 
	extends PB01TOrganizationalEntityPersistenceTestBase<PB01AOrgDivisionOID,PB01AOrgDivisionID,PB01AOrgDivision> {
/////////////////////////////////////////////////////////////////////////////////////////
//  FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	private final PB01AOrganization _organization;
	
/////////////////////////////////////////////////////////////////////////////////////////
//  
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01TOrgDivisionTest(final PB01APanicButtonClientAPI api,
							   			final PB01AOrganization organization) {
		super(TestPersistableModelObjectManager.create(PB01AOrgDivision.class,new PB01TMockOrgDivisionFactory(organization),
													   api.orgDivisionsAPI().getForCRUD(),
													   0l),
			  api.orgDivisionsAPI().getForCRUD(),api.orgDivisionsAPI().getForFind());
		_organization = organization;
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void testOtherCRUDMethods() {
		// no other CRUD methods
	}
	@Override
	public void testOtherFindMethods() {
		// [0]: Create test objects
		_managesTestMockObjects.setUpMockObjs(5);
		
		
		// [1]: Test
		PB01AClientAPIDelegateForOrgDivisionFindServices findApi = getClientFindApiAs(PB01AClientAPIDelegateForOrgDivisionFindServices.class);
		
		// Find by organization
		log.warn("[{} org divisions]",_organization.getId());
		Collection<PB01AOrgDivision> divisions = findApi.findByOrganization(_organization.getOid());
		Assert.assertTrue(CollectionUtils.hasData(divisions));
		
		System.out.println("[OrgDivisions for organization]: " + divisions + "_____________________");
		for(PB01AOrgDivision div : divisions) {
			log.warn("\t-{} > {}",div.getOid(),div.getName().getIn(Language.SPANISH));
		}
		
		// Find summaries by organization
		log.warn("[{} org divisions' summaries]",_organization.getId());
		Collection<PB01ASummarizedOrgDivision> divisionsSummaries = findApi.findSummariesByOrganization(_organization.getOid(),Language.SPANISH);
		Assert.assertTrue(CollectionUtils.hasData(divisionsSummaries));
		
		if (CollectionUtils.hasData(divisionsSummaries)) {
			for (PB01ASummarizedOrgDivision divSummary : divisionsSummaries) {
				log.warn("\t-{} > {}: {}",divSummary.getOid(),divSummary.getId(),divSummary.getName());
			}
		}
		
		// [2]: Teardown created test objects
		_managesTestMockObjects.tearDownCreatedMockObjs();
	}
	@Override
	public void testOtherMethods() {
		// nothing
	}


}
