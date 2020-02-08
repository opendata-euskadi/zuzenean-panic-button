package pb01a.api.interfaces;

import pb01a.model.oids.PB01AIDs.PB01APersistableObjectID;
import pb01a.model.oids.PB01AOIDs.PB01APersistableObjectOID;
import r01f.locale.Language;
import r01f.model.PersistableModelObject;
import r01f.model.persistence.FindResult;
import r01f.securitycontext.SecurityContext;

public interface PB01AFindServicesForOrganizationalModelObjectBase<O extends PB01APersistableObjectOID,ID extends PB01APersistableObjectID<O>,
									  						 M extends PersistableModelObject<O>> 
         extends PB01AFindServicesBase<O,ID,M> {
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
