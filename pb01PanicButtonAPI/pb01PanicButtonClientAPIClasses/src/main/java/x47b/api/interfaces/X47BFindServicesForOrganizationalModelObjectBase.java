package x47b.api.interfaces;

import r01f.locale.Language;
import r01f.model.PersistableModelObject;
import r01f.model.persistence.FindResult;
import r01f.securitycontext.SecurityContext;
import x47b.model.oids.X47BIDs.X47BPersistableObjectID;
import x47b.model.oids.X47BOIDs.X47BPersistableObjectOID;

public interface X47BFindServicesForOrganizationalModelObjectBase<O extends X47BPersistableObjectOID,ID extends X47BPersistableObjectID<O>,
									  						 M extends PersistableModelObject<O>> 
         extends X47BFindServicesBase<O,ID,M> {
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
