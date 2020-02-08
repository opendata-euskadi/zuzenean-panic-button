package pb01t.test.entities;

import org.junit.Assert;

import pb01a.client.api.sub.delegates.PB01AClientAPIDelegateBaseForModelObjectCRUDServices;
import pb01a.model.PB01APersistableObject;
import pb01a.model.oids.PB01AIDs.PB01APersistableObjectID;
import pb01a.model.oids.PB01AOIDs.PB01APersistableObjectOID;
import r01f.services.client.api.delegates.ClientAPIDelegateForModelObjectCRUDServices;
import r01f.services.client.api.delegates.ClientAPIDelegateForModelObjectFindServices;
import r01f.test.persistence.ManagesTestMockModelObjsLifeCycle;
import r01f.test.persistence.TestPersistableModelObjectBase;

/**
 * JVM arguments:
 * -javaagent:D:/tools_workspaces/eclipse/local_libs/aspectj/lib/aspectjweaver.jar -Daj.weaving.verbose=true
 */
public abstract class PB01TPanicButtonPersistablelObjectTestBase<O extends PB01APersistableObjectOID,ID extends PB01APersistableObjectID<O>,
																	M extends PB01APersistableObject<O,ID>> 
			  extends TestPersistableModelObjectBase<O,M> {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTORS
/////////////////////////////////////////////////////////////////////////////////////////
	protected PB01TPanicButtonPersistablelObjectTestBase(final ManagesTestMockModelObjsLifeCycle<O,M> managesTestMockObjects,
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
		M entityById = (M)this.getClientCRUDApiAs(PB01AClientAPIDelegateBaseForModelObjectCRUDServices.class)
									.loadById(entity.getId());
		Assert.assertEquals(entity.getId(),entityById.getId());
		Assert.assertNotNull(entityById);
		System.out.println("[Entity by id=" + entity.getId() + "]: " + entityById.getOid() + " > " + entityById.asSummarizable()
																											   .getSummary().asString());
		
		_managesTestMockObjects.tearDownCreatedMockObjs();
	}
}
