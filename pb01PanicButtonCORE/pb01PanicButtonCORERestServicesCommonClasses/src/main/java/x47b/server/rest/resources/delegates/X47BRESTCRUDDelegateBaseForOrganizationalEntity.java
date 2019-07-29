package x47b.server.rest.resources.delegates;

import r01f.model.PersistableModelObject;
import x47b.api.interfaces.X47BCRUDServicesForOrganizationalModelObjectBase;
import x47b.model.oids.X47BIDs.X47BModelObjectID;
import x47b.model.oids.X47BOIDs.X47BPersistableObjectOID;

public abstract class X47BRESTCRUDDelegateBaseForOrganizationalEntity<O extends X47BPersistableObjectOID,ID extends X47BModelObjectID<O>,
																	  M extends PersistableModelObject<O>>
	          extends X47BRESTCRUDDelegateBaseForEntity<O,ID,M> {

/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BRESTCRUDDelegateBaseForOrganizationalEntity(final Class<M> modelObjectType,
														   final X47BCRUDServicesForOrganizationalModelObjectBase<O,ID,M> crudServices) {
		super(modelObjectType,
			  crudServices);
	}
}
