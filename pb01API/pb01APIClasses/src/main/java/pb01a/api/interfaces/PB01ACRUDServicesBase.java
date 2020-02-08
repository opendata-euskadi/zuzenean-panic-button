package pb01a.api.interfaces;

import pb01a.model.oids.PB01AIDs.PB01APersistableObjectID;
import pb01a.model.oids.PB01AOIDs.PB01APersistableObjectOID;
import r01f.model.PersistableModelObject;
import r01f.model.persistence.CRUDResult;
import r01f.securitycontext.SecurityContext;
import r01f.services.interfaces.CRUDServicesForModelObject;

public interface PB01ACRUDServicesBase<O extends PB01APersistableObjectOID,ID extends PB01APersistableObjectID<O>,
									  M extends PersistableModelObject<O>> 
         extends CRUDServicesForModelObject<O,M> {
	/**
	 * Loads an entity by it's id
	 * @param id
	 * @return
	 */
	public CRUDResult<M> loadById(final SecurityContext securityContext,
								  final ID id);
	
}
