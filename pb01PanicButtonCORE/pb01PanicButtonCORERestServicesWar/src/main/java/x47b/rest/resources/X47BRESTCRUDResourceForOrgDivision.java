package x47b.rest.resources;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.Path;

import lombok.experimental.Accessors;
import x47b.api.interfaces.X47BCRUDServicesForOrgDivision;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionOID;
import x47b.model.org.X47BOrgDivision;
import x47b.rest.resources.delegates.X47BRESTCRUDDelegateForOrgDivision;
import x47b.server.rest.resources.X47BRESTCRUDResourceBaseForOrganizationalEntity;

/**
 * The implementation must follow the HTTP 1.1 spec (http://www.w3.org/Protocols/rfc2616/rfc2616.html), specifically
 * the section about the methods (GET, PUT, POST, DELETE...)
 *
 * Log: see web.xml
 */
@Path("divisions")
@Singleton
@Accessors(prefix="_")
public class X47BRESTCRUDResourceForOrgDivision
	 extends X47BRESTCRUDResourceBaseForOrganizationalEntity<X47BOrgDivisionOID,X47BOrgDivisionID,X47BOrgDivision> {
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public X47BRESTCRUDResourceForOrgDivision(final X47BCRUDServicesForOrgDivision crudServices) {
		super(new X47BRESTCRUDDelegateForOrgDivision(crudServices));
	}
}
