package pb01c.server.rest.resources.delegates;

import pb01a.api.interfaces.PB01ACRUDServicesForOrganizationalModelObjectBase;
import pb01a.model.oids.PB01AIDs.PB01APersistableObjectID;
import pb01a.model.oids.PB01AOIDs.PB01APersistableObjectOID;
import r01f.model.PersistableModelObject;

public abstract class PB01CRESTCRUDDelegateBaseForOrganizationalEntity<O extends PB01APersistableObjectOID,ID extends PB01APersistableObjectID<O>,
																	  M extends PersistableModelObject<O>>
	          extends PB01CRESTCRUDDelegateBaseForEntity<O,ID,M> {

/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01CRESTCRUDDelegateBaseForOrganizationalEntity(final Class<M> modelObjectType,
														   final PB01ACRUDServicesForOrganizationalModelObjectBase<O,ID,M> crudServices) {
		super(modelObjectType,
			  crudServices);
	}
}
