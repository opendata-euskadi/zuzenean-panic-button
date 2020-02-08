package pb01t.test.entities;

import java.util.Collection;

import org.junit.Assert;

import pb01a.client.api.sub.delegates.PB01AClientAPIDelegateForOrganizationalEntityFindServicesBase;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgObjectID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgObjectOID;
import pb01a.model.org.PB01AOrganizationalPersistableObject;
import r01f.locale.Language;
import r01f.locale.LanguageTexts.LangTextNotFoundBehabior;
import r01f.locale.LanguageTextsMapBacked;
import r01f.patterns.CommandOn;
import r01f.services.client.api.delegates.ClientAPIDelegateForModelObjectCRUDServices;
import r01f.services.client.api.delegates.ClientAPIDelegateForModelObjectFindServices;
import r01f.test.persistence.ManagesTestMockModelObjsLifeCycle;
import r01f.util.types.collections.CollectionUtils;

/**
 * JVM arguments:
 * -javaagent:D:/tools_workspaces/eclipse/local_libs/aspectj/lib/aspectjweaver.jar -Daj.weaving.verbose=true
 */
abstract class PB01TOrganizationalEntityPersistenceTestBase<O extends PB01AOrgObjectOID,ID extends PB01AOrgObjectID<O>,
								  						   M extends PB01AOrganizationalPersistableObject<O,ID>>
	   extends PB01TPanicButtonPersistablelObjectTestBase<O,ID,M> {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTORS
/////////////////////////////////////////////////////////////////////////////////////////
	protected PB01TOrganizationalEntityPersistenceTestBase(final ManagesTestMockModelObjsLifeCycle<O,M> managesTestMockObjects,
											 				final ClientAPIDelegateForModelObjectCRUDServices<O,M> crudApi,final ClientAPIDelegateForModelObjectFindServices<O,M> findApi) {
		super(managesTestMockObjects,
			  crudApi,findApi);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  CRUD
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	protected CommandOn<M> _modelObjectStateUpdateCommand() {
		return new CommandOn<M>() {
						@Override
						public void executeOn(final M entityToBeUpdated) {
							entityToBeUpdated.setNameByLanguage(new LanguageTextsMapBacked(LangTextNotFoundBehabior.RETURN_NULL)
																		.add(Language.SPANISH,"A changed name")
																		.add(Language.BASQUE,"[eu] A changed name"));
						}
			   };
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  FIND
/////////////////////////////////////////////////////////////////////////////////////////
	@Override @SuppressWarnings("unchecked")
	public void doFindTest() {
		// [1]: Basic find tests
		super.doFindTest();

		// [2]: Test find by name
		System.out.println("[Test find by name]");
		_managesTestMockObjects.setUpMockObjs(5);
		PB01AClientAPIDelegateForOrganizationalEntityFindServicesBase<O,ID,M> findApi = this.getClientFindApiAs(PB01AClientAPIDelegateForOrganizationalEntityFindServicesBase.class);
		Collection<M> entitiesWithName = findApi.findByNameIn(Language.SPANISH,
															  "TEST");
		Assert.assertTrue(CollectionUtils.hasData(entitiesWithName));
		System.out.println("\t[Entities by name]: TEST");
		for (M entityWithName : entitiesWithName) {
			System.out.println("\t\t-" + entityWithName.getOid() + " > " + entityWithName.getName().getIn(Language.SPANISH));
		}
		_managesTestMockObjects.tearDownCreatedMockObjs();
	}
}
