package pb01c.server.rest.resources;

import lombok.experimental.Accessors;
import pb01a.model.oids.PB01AIDs.PB01APersistableObjectID;
import pb01a.model.oids.PB01AOIDs.PB01APersistableObjectOID;
import pb01c.server.rest.resources.delegates.PB01CRESTCRUDDelegateBaseForOrganizationalEntity;
import r01f.model.PersistableModelObject;

/**
 * The implementation must follow the HTTP 1.1 spec (http://www.w3.org/Protocols/rfc2616/rfc2616.html), specifically
 * the section about the methods (GET, PUT, POST, DELETE...)
 *
 * Log: see web.xml
 */
@Accessors(prefix="_")
public abstract class PB01CRESTCRUDResourceBaseForOrganizationalEntity<O extends PB01APersistableObjectOID,ID extends PB01APersistableObjectID<O>,
									  						 		  M extends PersistableModelObject<O>>
	          extends PB01CRESTCRUDResourceBaseForEntity<O,ID,M> {

/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01CRESTCRUDResourceBaseForOrganizationalEntity(final PB01CRESTCRUDDelegateBaseForOrganizationalEntity<O,ID,M> crudDelegate) {
		super(crudDelegate);
	}
}
