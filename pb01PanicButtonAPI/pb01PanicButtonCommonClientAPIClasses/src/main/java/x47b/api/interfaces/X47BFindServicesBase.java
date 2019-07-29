package x47b.api.interfaces;

import r01f.locale.Language;
import r01f.model.PersistableModelObject;
import r01f.model.persistence.FindResult;
import r01f.securitycontext.SecurityContext;
import r01f.services.interfaces.FindServicesForModelObject;
import x47b.model.oids.X47BIDs.X47BModelObjectID;
import x47b.model.oids.X47BOIDs.X47BPersistableObjectOID;

@SuppressWarnings("unused")
public interface X47BFindServicesBase<O extends X47BPersistableObjectOID,ID extends X47BModelObjectID<O>,
									  M extends PersistableModelObject<O>> 
         extends FindServicesForModelObject<O,M> {
/////////////////////////////////////////////////////////////////////////////////////////
//  EXTENSION METHODS
/////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Finds an org entity by it's name in a given language
	 * @param securityContext
	 * @param lang
	 * @param name
	 * @return
	 */
	public FindResult<M> findByNameIn(final SecurityContext securityContext,
									  final Language lang,final String name);
}
