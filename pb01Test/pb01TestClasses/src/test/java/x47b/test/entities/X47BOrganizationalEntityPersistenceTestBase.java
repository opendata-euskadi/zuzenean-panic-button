package x47b.test.entities;

import java.util.Collection;

import org.junit.Assert;

import r01f.locale.Language;
import r01f.locale.LanguageTexts.LangTextNotFoundBehabior;
import r01f.locale.LanguageTextsMapBacked;
import r01f.patterns.CommandOn;
import r01f.services.client.api.delegates.ClientAPIDelegateForModelObjectCRUDServices;
import r01f.services.client.api.delegates.ClientAPIDelegateForModelObjectFindServices;
import r01f.test.persistence.ManagesTestMockModelObjsLifeCycle;
import r01f.util.types.collections.CollectionUtils;
import x47b.client.api.sub.delegates.X47BClientAPIDelegateForOrganizationalEntityFindServicesBase;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgObjectID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgObjectOID;
import x47b.model.org.X47BOrganizationalPersistableObject;

/**
 * JVM arguments:
 * -javaagent:D:/tools_workspaces/eclipse/local_libs/aspectj/lib/aspectjweaver.jar -Daj.weaving.verbose=true
 */
abstract class X47BOrganizationalEntityPersistenceTestBase<O extends X47BOrgObjectOID,ID extends X47BOrgObjectID<O>,
								  						   M extends X47BOrganizationalPersistableObject<O,ID>>
	   extends X47BPanicButtonPersistablelObjectTestBase<O,ID,M> {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTORS
/////////////////////////////////////////////////////////////////////////////////////////
	protected X47BOrganizationalEntityPersistenceTestBase(final ManagesTestMockModelObjsLifeCycle<O,M> managesTestMockObjects,
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
		X47BClientAPIDelegateForOrganizationalEntityFindServicesBase<O,ID,M> findApi = this.getClientFindApiAs(X47BClientAPIDelegateForOrganizationalEntityFindServicesBase.class);
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
