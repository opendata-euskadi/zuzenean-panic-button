package pb01c.rest.resources;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.Path;

import lombok.experimental.Accessors;
import pb01a.api.interfaces.PB01ACRUDServicesForOrgDivisionServiceLocation;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionServiceLocationID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionServiceLocationOID;
import pb01a.model.org.PB01AOrgDivisionServiceLocation;
import pb01c.rest.resources.delegates.PB01CRESTCRUDDelegateForOrgDivisionServiceLocation;
import pb01c.server.rest.resources.PB01CRESTCRUDResourceBaseForOrganizationalEntity;

/**
 * The implementation must follow the HTTP 1.1 spec (http://www.w3.org/Protocols/rfc2616/rfc2616.html), specifically
 * the section about the methods (GET, PUT, POST, DELETE...)
 *
 * Log: see web.xml
 */
@Path("locations")
@Singleton
@Accessors(prefix="_")
public class PB01CRESTCRUDResourceForOrgDivisionServiceLocation
	 extends PB01CRESTCRUDResourceBaseForOrganizationalEntity<PB01AOrgDivisionServiceLocationOID,PB01AOrgDivisionServiceLocationID,PB01AOrgDivisionServiceLocation> {
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public PB01CRESTCRUDResourceForOrgDivisionServiceLocation(final PB01ACRUDServicesForOrgDivisionServiceLocation crudServices) {
		super(new PB01CRESTCRUDDelegateForOrgDivisionServiceLocation(crudServices));
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  EXTENSIONS
/////////////////////////////////////////////////////////////////////////////////////////	

	
}
