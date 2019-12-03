package x47b.rest.resources;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.Path;

import lombok.experimental.Accessors;
import x47b.api.interfaces.X47BCRUDServicesForOrganization;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrganizationID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrganizationOID;
import x47b.model.org.X47BOrganization;
import x47b.rest.resources.delegates.X47BRESTCRUDDelegateForOrganization;
import x47b.server.rest.resources.X47BRESTCRUDResourceBaseForOrganizationalEntity;

/**
 * The implementation must follow the HTTP 1.1 spec (http://www.w3.org/Protocols/rfc2616/rfc2616.html), specifically
 * the section about the methods (GET, PUT, POST, DELETE...)
 *
 * Log: see web.xml
 */
@Path("organizations")
@Singleton
@Accessors(prefix="_")
public class X47BRESTCRUDResourceForOrganization
	 extends X47BRESTCRUDResourceBaseForOrganizationalEntity<X47BOrganizationOID,X47BOrganizationID,X47BOrganization> {
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public X47BRESTCRUDResourceForOrganization(final X47BCRUDServicesForOrganization crudServices) {
		super(new X47BRESTCRUDDelegateForOrganization(crudServices));
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
}
