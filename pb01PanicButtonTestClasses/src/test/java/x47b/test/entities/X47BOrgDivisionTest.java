package x47b.test.entities;

import java.util.Collection;

import org.junit.Assert;

import lombok.extern.slf4j.Slf4j;
import r01f.locale.Language;
import r01f.test.persistence.TestPersistableModelObjectManager;
import r01f.util.types.collections.CollectionUtils;
import x47b.client.api.X47BPanicButtonClientAPI;
import x47b.client.api.sub.delegates.X47BClientAPIDelegateForOrgDivisionFindServices;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionOID;
import x47b.model.org.X47BOrgDivision;
import x47b.model.org.X47BOrganization;
import x47b.model.org.summaries.X47BSummarizedOrgDivision;
import x47b.test.entities.mock.X47BMockOrgDivisionFactory;

@Slf4j
public class X47BOrgDivisionTest 
	extends X47BOrganizationalEntityPersistenceTestBase<X47BOrgDivisionOID,X47BOrgDivisionID,X47BOrgDivision> {
/////////////////////////////////////////////////////////////////////////////////////////
//  FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	private final X47BOrganization _organization;
	
/////////////////////////////////////////////////////////////////////////////////////////
//  
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BOrgDivisionTest(final X47BPanicButtonClientAPI api,
							   			final X47BOrganization organization) {
		super(TestPersistableModelObjectManager.create(X47BOrgDivision.class,new X47BMockOrgDivisionFactory(organization),
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
		X47BClientAPIDelegateForOrgDivisionFindServices findApi = getClientFindApiAs(X47BClientAPIDelegateForOrgDivisionFindServices.class);
		
		// Find by organization
		log.warn("[{} org divisions]",_organization.getId());
		Collection<X47BOrgDivision> divisions = findApi.findByOrganization(_organization.getOid());
		Assert.assertTrue(CollectionUtils.hasData(divisions));
		
		System.out.println("[OrgDivisions for organization]: " + divisions + "_____________________");
		for(X47BOrgDivision div : divisions) {
			log.warn("\t-{} > {}",div.getOid(),div.getName().getIn(Language.SPANISH));
		}
		
		// Find summaries by organization
		log.warn("[{} org divisions' summaries]",_organization.getId());
		Collection<X47BSummarizedOrgDivision> divisionsSummaries = findApi.findSummariesByOrganization(_organization.getOid(),Language.SPANISH);
		Assert.assertTrue(CollectionUtils.hasData(divisionsSummaries));
		
		if (CollectionUtils.hasData(divisionsSummaries)) {
			for (X47BSummarizedOrgDivision divSummary : divisionsSummaries) {
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
