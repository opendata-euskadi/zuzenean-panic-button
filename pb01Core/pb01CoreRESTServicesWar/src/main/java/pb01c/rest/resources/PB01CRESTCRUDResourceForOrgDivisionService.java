package pb01c.rest.resources;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.Path;

import lombok.experimental.Accessors;
import pb01a.api.interfaces.PB01ACRUDServicesForOrgDivisionService;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionServiceID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionServiceOID;
import pb01a.model.org.PB01AOrgDivisionService;
import pb01c.rest.resources.delegates.PB01CRESTCRUDDelegateForOrgDivisionService;
import pb01c.server.rest.resources.PB01CRESTCRUDResourceBaseForOrganizationalEntity;

/**
 * The implementation must follow the HTTP 1.1 spec (http://www.w3.org/Protocols/rfc2616/rfc2616.html), specifically
 * the section about the methods (GET, PUT, POST, DELETE...)
 *
 * Log: see web.xml
 */
@Path("services")
@Singleton
@Accessors(prefix="_")
public class PB01CRESTCRUDResourceForOrgDivisionService
	 extends PB01CRESTCRUDResourceBaseForOrganizationalEntity<PB01AOrgDivisionServiceOID,PB01AOrgDivisionServiceID,PB01AOrgDivisionService> {
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public PB01CRESTCRUDResourceForOrgDivisionService(final PB01ACRUDServicesForOrgDivisionService crudServices) {
		super(new PB01CRESTCRUDDelegateForOrgDivisionService(crudServices));
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  EXTENSIONS
/////////////////////////////////////////////////////////////////////////////////////////	

	
}
