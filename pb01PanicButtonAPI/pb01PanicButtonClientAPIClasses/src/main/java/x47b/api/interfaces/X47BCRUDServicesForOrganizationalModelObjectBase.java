package x47b.api.interfaces;

import r01f.model.PersistableModelObject;
import x47b.model.oids.X47BIDs.X47BPersistableObjectID;
import x47b.model.oids.X47BOIDs.X47BPersistableObjectOID;

public interface X47BCRUDServicesForOrganizationalModelObjectBase<O extends X47BPersistableObjectOID,ID extends X47BPersistableObjectID<O>,
									  						 M extends PersistableModelObject<O>> 
         extends X47BCRUDServicesBase<O,ID,M> {
	// nothing
}
