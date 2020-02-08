package pb01t.test.entities;

import java.util.Collection;

import lombok.extern.slf4j.Slf4j;
import pb01a.client.api.PB01APanicButtonClientAPI;
import pb01a.client.api.sub.delegates.PB01AClientAPIDelegateForOrganizationFindServices;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrganizationID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrganizationOID;
import pb01a.model.org.PB01AOrganization;
import pb01a.model.org.summaries.PB01ASummarizedOrganization;
import pb01t.test.entities.mock.PB01TMockOrganizationFactory;
import r01f.locale.Language;
import r01f.test.persistence.TestPersistableModelObjectManager;
import r01f.util.types.collections.CollectionUtils;

@Slf4j
public class PB01TOrganizationTest 
	 extends PB01TOrganizationalEntityPersistenceTestBase<PB01AOrganizationOID,PB01AOrganizationID,PB01AOrganization> {
/////////////////////////////////////////////////////////////////////////////////////////
//  
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01TOrganizationTest(final PB01APanicButtonClientAPI api) {
		super(TestPersistableModelObjectManager.create(PB01AOrganization.class,new PB01TMockOrganizationFactory(),
													   api.organizationsAPI().getForCRUD(),
													   0l),
			  api.organizationsAPI().getForCRUD(),api.organizationsAPI().getForFind());
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	protected void testOtherCRUDMethods() {
		// no other CRUD methods
	}
	@Override
	protected void testOtherFindMethods() {		
		// [0]: Create 5 test orgs
		_managesTestMockObjects.setUpMockObjs(5);
		
		// [1]: Test
		log.warn("[Organization summaries]");
		Collection<PB01ASummarizedOrganization> orgSummaries = this.getClientFindApiAs(PB01AClientAPIDelegateForOrganizationFindServices.class)
																		.findSummaries(Language.SPANISH);
		if (CollectionUtils.hasData(orgSummaries)) {
			for (PB01ASummarizedOrganization orgSumm : orgSummaries) {
				log.warn("\t-{} > {}: {}",orgSumm.getOid(),orgSumm.getId(),orgSumm.getName());
			}
		}
		
		// [2]: delete created model objs
		_managesTestMockObjects.tearDownCreatedMockObjs();
	}
	@Override
	protected void testOtherMethods() {
		// nothing
	}
}
