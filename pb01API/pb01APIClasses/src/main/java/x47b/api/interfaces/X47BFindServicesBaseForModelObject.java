package x47b.api.interfaces;

import r01f.locale.Language;
import r01f.model.PersistableModelObject;
import r01f.model.persistence.FindResult;
import r01f.securitycontext.SecurityContext;
import r01f.services.interfaces.FindServicesForModelObject;
import x47b.model.oids.X47BIDs.X47BPersistableObjectID;
import x47b.model.oids.X47BOIDs.X47BPersistableObjectOID;

@SuppressWarnings("unused")
public interface X47BFindServicesBaseForModelObject<O extends X47BPersistableObjectOID,ID extends X47BPersistableObjectID<O>,
											   M extends PersistableModelObject<O>> 
         extends FindServicesForModelObject<O,M> {
	/**
	 * Returns all entities by name and language
	 * @param securityContext
	 * @param userCode
	 * @return
	 */
	public FindResult<M> findByNameIn(final SecurityContext securityContext,
									  final Language lang,
									  final String name);
}
