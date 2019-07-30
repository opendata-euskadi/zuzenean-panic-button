package x47b.test;

import java.util.Collection;

import com.google.common.collect.Lists;

import lombok.RequiredArgsConstructor;
import r01f.guids.PersistableObjectOID;
import r01f.model.PersistableModelObject;
import r01f.patterns.Factory;
import r01f.services.client.api.delegates.ClientAPIDelegateForModelObjectCRUDServices;
import r01f.util.types.collections.CollectionUtils;

@RequiredArgsConstructor
public class X47BTestPersistableModelObjectFactory<O extends PersistableObjectOID,M extends PersistableModelObject<O>> {
/////////////////////////////////////////////////////////////////////////////////////////
//  
/////////////////////////////////////////////////////////////////////////////////////////
	private final ClientAPIDelegateForModelObjectCRUDServices<O,M> _crudAPI;
	
/////////////////////////////////////////////////////////////////////////////////////////
//  
/////////////////////////////////////////////////////////////////////////////////////////
	public static <O extends PersistableObjectOID,M extends PersistableModelObject<O>> X47BTestPersistableModelObjectFactory<O,M> create(final ClientAPIDelegateForModelObjectCRUDServices<O,M> crudAPI) {
		return new X47BTestPersistableModelObjectFactory<O,M>(crudAPI);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  
/////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Creates a number of model objects using the provided factory
	 * @param numOfObjectsToCreate
	 * @param modelObjectFactory
	 * @return the created objects
	 */
	public Collection<O> createTestModelObjects(final int numOfObjectsToCreate,
									   			final Factory<M> modelObjectFactory) {
		Collection<O> outCreatedObjsOids = Lists.newArrayListWithExpectedSize(numOfObjectsToCreate);
		for (int i=0; i < numOfObjectsToCreate; i++) {
			M modelObjectToBeCreated = modelObjectFactory.create();
			_crudAPI.save(modelObjectToBeCreated);
			outCreatedObjsOids.add(modelObjectToBeCreated.getOid());
		}
		return outCreatedObjsOids;
	}
	/**
	 * Deletes a {@link Collection} of previously created objects
	 * @param createdObjs
	 */
	public void deletedCreatedTestModelObjects(final Collection<O> createdObjs) {
		if (CollectionUtils.isNullOrEmpty(createdObjs)) return;
		for (O oid : createdObjs) {
			_crudAPI.delete(oid);
		}
	}
}
