package x47b.server.rest.resources;

import lombok.experimental.Accessors;
import r01f.model.PersistableModelObject;
import x47b.model.oids.X47BIDs.X47BModelObjectID;
import x47b.model.oids.X47BOIDs.X47BPersistableObjectOID;
import x47b.server.rest.resources.delegates.X47BRESTCRUDDelegateBaseForOrganizationalEntity;

/**
 * The implementation must follow the HTTP 1.1 spec (http://www.w3.org/Protocols/rfc2616/rfc2616.html), specifically
 * the section about the methods (GET, PUT, POST, DELETE...)
 *
 * Log: see web.xml
 */
@Accessors(prefix="_")
public abstract class X47BRESTCRUDResourceBaseForOrganizationalEntity<O extends X47BPersistableObjectOID,ID extends X47BModelObjectID<O>,
									  						 		  M extends PersistableModelObject<O>>
	          extends X47BRESTCRUDResourceBaseForEntity<O,ID,M> {

/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BRESTCRUDResourceBaseForOrganizationalEntity(final X47BRESTCRUDDelegateBaseForOrganizationalEntity<O,ID,M> crudDelegate) {
		super(crudDelegate);
	}
}
