package pb01c.rest.resources;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.Path;

import lombok.experimental.Accessors;
import pb01a.api.interfaces.PB01ACRUDServicesForOrganization;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrganizationID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrganizationOID;
import pb01a.model.org.PB01AOrganization;
import pb01c.rest.resources.delegates.PB01CRESTCRUDDelegateForOrganization;
import pb01c.server.rest.resources.PB01CRESTCRUDResourceBaseForOrganizationalEntity;

/**
 * The implementation must follow the HTTP 1.1 spec (http://www.w3.org/Protocols/rfc2616/rfc2616.html), specifically
 * the section about the methods (GET, PUT, POST, DELETE...)
 *
 * Log: see web.xml
 */
@Path("organizations")
@Singleton
@Accessors(prefix="_")
public class PB01CRESTCRUDResourceForOrganization
	 extends PB01CRESTCRUDResourceBaseForOrganizationalEntity<PB01AOrganizationOID,PB01AOrganizationID,PB01AOrganization> {
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public PB01CRESTCRUDResourceForOrganization(final PB01ACRUDServicesForOrganization crudServices) {
		super(new PB01CRESTCRUDDelegateForOrganization(crudServices));
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
}
