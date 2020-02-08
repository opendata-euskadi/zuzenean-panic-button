package pb01a.api.interfaces;

import pb01a.model.oids.PB01AIDs.PB01APersistableObjectID;
import pb01a.model.oids.PB01AOIDs.PB01APersistableObjectOID;
import r01f.locale.Language;
import r01f.model.PersistableModelObject;
import r01f.model.persistence.FindResult;
import r01f.securitycontext.SecurityContext;
import r01f.services.interfaces.FindServicesForModelObject;

@SuppressWarnings("unused")
public interface PB01AFindServicesBase<O extends PB01APersistableObjectOID,ID extends PB01APersistableObjectID<O>,
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
