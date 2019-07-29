package x47b.api.interfaces;

import r01f.model.PersistableModelObject;
import r01f.model.persistence.CRUDResult;
import r01f.securitycontext.SecurityContext;
import r01f.services.interfaces.CRUDServicesForModelObject;
import x47b.model.oids.X47BIDs.X47BModelObjectID;
import x47b.model.oids.X47BOIDs.X47BPersistableObjectOID;

public interface X47BCRUDServicesBaseForModelObject<O extends X47BPersistableObjectOID,ID extends X47BModelObjectID<O>,
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
