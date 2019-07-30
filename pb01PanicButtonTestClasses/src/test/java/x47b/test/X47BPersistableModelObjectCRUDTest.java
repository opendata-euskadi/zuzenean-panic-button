package x47b.test;

import org.junit.Assert;

import lombok.RequiredArgsConstructor;
import r01f.guids.PersistableObjectOID;
import r01f.model.PersistableModelObject;
import r01f.patterns.CommandOn;
import r01f.services.client.api.delegates.ClientAPIDelegateForModelObjectCRUDServices;

@RequiredArgsConstructor
public class X47BPersistableModelObjectCRUDTest<O extends PersistableObjectOID,M extends PersistableModelObject<O>> {
/////////////////////////////////////////////////////////////////////////////////////////
//  
/////////////////////////////////////////////////////////////////////////////////////////
	private final ClientAPIDelegateForModelObjectCRUDServices<O,M> _crudAPI;
	
/////////////////////////////////////////////////////////////////////////////////////////
//  
/////////////////////////////////////////////////////////////////////////////////////////
	public static <O extends PersistableObjectOID,M extends PersistableModelObject<O>> X47BPersistableModelObjectCRUDTest<O,M> create(final ClientAPIDelegateForModelObjectCRUDServices<O,M> crudAPI) {
		return new X47BPersistableModelObjectCRUDTest<O,M>(crudAPI);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  
/////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Tests the CRUD API (creates an entity, updates it, loads it and finally deletes it)
	 * @param modelObject
	 */
	@SuppressWarnings("unchecked")
	public void testPersistence(final M modelObject,
								final CommandOn<M> modelObjectStateUpdateCommand) {
		Class<M> modelObjectType = (Class<M>)modelObject.getClass();
		
		O oid = modelObject.getOid();
		
		// [1] Create an entity
		System.out.println("CREATE AN ENTITY OF TYPE " + modelObjectType + "_________________________________");
		M createdModelObj = _crudAPI.save(modelObject);
		System.out.println("---->Version id=" + createdModelObj.getEntityVersion());
		
		Assert.assertNotNull(createdModelObj);
		long initialDBVersion = createdModelObj.getEntityVersion();
		
		// [2] Try to update the entity not having modified it: This should not do anything since nothing was modified
		System.out.println("SAVE WITHOUT MODIFY THE ENTITY OF TYPE " + modelObjectType + " ___________________");
		M notUpdatedModelObj = _crudAPI.save(createdModelObj);
		
		Assert.assertNotNull(notUpdatedModelObj);
		long notUpdatedDBVersion = notUpdatedModelObj.getEntityVersion();
		Assert.assertEquals(initialDBVersion,notUpdatedDBVersion);		// the DB version MUST remain (NO CRUD operation was issued)
		
		
		// [3]  Update the entity
		System.out.println("SAVE MODIFYING THE ENTITY OF TYPE " + modelObjectType + " ________________________");
		modelObjectStateUpdateCommand.executeOn(createdModelObj);
		M updatedModelObj = _crudAPI.save(createdModelObj);
				
		Assert.assertNotNull(updatedModelObj);
		long updatedDBVersion = updatedModelObj.getEntityVersion();
		Assert.assertNotEquals(initialDBVersion,updatedDBVersion);		// the DB version MUST NOT be the same (an UPDATE was issued)
		
		// [4] Load the modified creation request
		System.out.println("LOAD THE ENTITY OF TYPE " + modelObjectType + " WITH oid=" + createdModelObj.getOid() + " _________");
		M loadedModelObj = _crudAPI.load(oid);		
		System.out.println(">>>" + oid + " version=" + loadedModelObj.getEntityVersion());
		
		Assert.assertNotNull(loadedModelObj);
		long loadDBVersion = updatedModelObj.getEntityVersion();
		Assert.assertEquals(updatedDBVersion,loadDBVersion);		
		
//		// [5] Test Optimistic locking
//		System.out.println("[Optimistic Locking (this should fail)]");
//		loadedModelObj.setEntityVersion(100);		// setting the entityVersion at the client would BREAK the persisted version sequence so an exception should be raised
//		try {
//			_crudAPI.save(loadedModelObj);
//		} catch(Exception ex) {
//			System.out.println("\tFAILED!! the db's version is NOT the same as the client-provided one!");
//		}
		
		// [6] Delete the entity
		System.out.println("DELETE THE ENTITY OF TYPE " + modelObjectType + " WITH oid=" + createdModelObj.getOid() + " _______");
		M deletedModelObj = _crudAPI.delete(oid);
		System.out.println(">>>" + oid + " version=" + deletedModelObj.getEntityVersion());
		
		// try to load the deleted object... it must NOT exist
		loadedModelObj = _crudAPI.loadOrNull(deletedModelObj.getOid());
		Assert.assertNull(loadedModelObj);
	}
}
