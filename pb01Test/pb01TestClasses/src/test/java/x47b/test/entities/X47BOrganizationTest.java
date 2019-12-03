package x47b.test.entities;

import java.util.Collection;

import lombok.extern.slf4j.Slf4j;
import r01f.locale.Language;
import r01f.test.persistence.TestPersistableModelObjectManager;
import r01f.util.types.collections.CollectionUtils;
import x47b.client.api.X47BPanicButtonClientAPI;
import x47b.client.api.sub.delegates.X47BClientAPIDelegateForOrganizationFindServices;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrganizationID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrganizationOID;
import x47b.model.org.X47BOrganization;
import x47b.model.org.summaries.X47BSummarizedOrganization;
import x47b.test.entities.mock.X47BMockOrganizationFactory;

@Slf4j
public class X47BOrganizationTest 
	 extends X47BOrganizationalEntityPersistenceTestBase<X47BOrganizationOID,X47BOrganizationID,X47BOrganization> {
/////////////////////////////////////////////////////////////////////////////////////////
//  
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BOrganizationTest(final X47BPanicButtonClientAPI api) {
		super(TestPersistableModelObjectManager.create(X47BOrganization.class,new X47BMockOrganizationFactory(),
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
		Collection<X47BSummarizedOrganization> orgSummaries = this.getClientFindApiAs(X47BClientAPIDelegateForOrganizationFindServices.class)
																		.findSummaries(Language.SPANISH);
		if (CollectionUtils.hasData(orgSummaries)) {
			for (X47BSummarizedOrganization orgSumm : orgSummaries) {
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
