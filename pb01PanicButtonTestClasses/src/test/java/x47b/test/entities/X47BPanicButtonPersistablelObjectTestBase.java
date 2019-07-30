package x47b.test.entities;

import org.junit.Assert;

import r01f.services.client.api.delegates.ClientAPIDelegateForModelObjectCRUDServices;
import r01f.services.client.api.delegates.ClientAPIDelegateForModelObjectFindServices;
import r01f.test.persistence.ManagesTestMockModelObjsLifeCycle;
import r01f.test.persistence.TestPersistableModelObjectBase;
import x47b.client.api.sub.delegates.X47BClientAPIDelegateBaseForModelObjectCRUDServices;
import x47b.model.X47BEntityObject;
import x47b.model.oids.X47BIDs.X47BModelObjectID;
import x47b.model.oids.X47BOIDs.X47BPersistableObjectOID;

/**
 * JVM arguments:
 * -javaagent:D:/tools_workspaces/eclipse/local_libs/aspectj/lib/aspectjweaver.jar -Daj.weaving.verbose=true
 */
public abstract class X47BPanicButtonPersistablelObjectTestBase<O extends X47BPersistableObjectOID,ID extends X47BModelObjectID<O>,
																	M extends X47BEntityObject<O,ID>> 
			  extends TestPersistableModelObjectBase<O,M> {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTORS
/////////////////////////////////////////////////////////////////////////////////////////
	protected X47BPanicButtonPersistablelObjectTestBase(final ManagesTestMockModelObjsLifeCycle<O,M> managesTestMockObjects,
											 				final ClientAPIDelegateForModelObjectCRUDServices<O,M> crudApi,final ClientAPIDelegateForModelObjectFindServices<O,M> findApi) {
		super(managesTestMockObjects,
			  crudApi,findApi);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  CRUD
/////////////////////////////////////////////////////////////////////////////////////////
	@Override @SuppressWarnings("unchecked")
	protected void testOtherCRUDMethods() {
		System.out.println("[Test load by id]");
		_managesTestMockObjects.setUpMockObjs(1);
		
		M entity = _managesTestMockObjects.getAnyCreatedMockObj();	// get the recently created model object
		M entityById = (M)this.getClientCRUDApiAs(X47BClientAPIDelegateBaseForModelObjectCRUDServices.class)
									.loadById(entity.getId());
		Assert.assertEquals(entity.getId(),entityById.getId());
		Assert.assertNotNull(entityById);
		System.out.println("[Entity by id=" + entity.getId() + "]: " + entityById.getOid() + " > " + entityById.asSummarizable()
																											   .getSummary().asString());
		
		_managesTestMockObjects.tearDownCreatedMockObjs();
	}
}
