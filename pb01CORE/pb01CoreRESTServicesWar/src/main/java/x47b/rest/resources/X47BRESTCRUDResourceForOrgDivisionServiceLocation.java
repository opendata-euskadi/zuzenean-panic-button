package x47b.rest.resources;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.Path;

import lombok.experimental.Accessors;
import x47b.api.interfaces.X47BCRUDServicesForOrgDivisionServiceLocation;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionServiceLocationID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceLocationOID;
import x47b.model.org.X47BOrgDivisionServiceLocation;
import x47b.rest.resources.delegates.X47BRESTCRUDDelegateForOrgDivisionServiceLocation;
import x47b.server.rest.resources.X47BRESTCRUDResourceBaseForOrganizationalEntity;

/**
 * The implementation must follow the HTTP 1.1 spec (http://www.w3.org/Protocols/rfc2616/rfc2616.html), specifically
 * the section about the methods (GET, PUT, POST, DELETE...)
 *
 * Log: see web.xml
 */
@Path("locations")
@Singleton
@Accessors(prefix="_")
public class X47BRESTCRUDResourceForOrgDivisionServiceLocation
	 extends X47BRESTCRUDResourceBaseForOrganizationalEntity<X47BOrgDivisionServiceLocationOID,X47BOrgDivisionServiceLocationID,X47BOrgDivisionServiceLocation> {
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public X47BRESTCRUDResourceForOrgDivisionServiceLocation(final X47BCRUDServicesForOrgDivisionServiceLocation crudServices) {
		super(new X47BRESTCRUDDelegateForOrgDivisionServiceLocation(crudServices));
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  EXTENSIONS
/////////////////////////////////////////////////////////////////////////////////////////	

	
}
