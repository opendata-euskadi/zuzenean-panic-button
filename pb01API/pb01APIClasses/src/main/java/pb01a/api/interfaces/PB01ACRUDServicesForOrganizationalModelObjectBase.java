package pb01a.api.interfaces;

import pb01a.model.oids.PB01AIDs.PB01APersistableObjectID;
import pb01a.model.oids.PB01AOIDs.PB01APersistableObjectOID;
import r01f.model.PersistableModelObject;

public interface PB01ACRUDServicesForOrganizationalModelObjectBase<O extends PB01APersistableObjectOID,ID extends PB01APersistableObjectID<O>,
									  						 M extends PersistableModelObject<O>> 
         extends PB01ACRUDServicesBase<O,ID,M> {
	// nothing
}
