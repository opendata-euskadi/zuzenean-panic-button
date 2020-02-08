package pb01c.rest.resources;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.Path;

import lombok.experimental.Accessors;
import pb01a.api.interfaces.PB01ACRUDServicesForOrgDivision;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionOID;
import pb01a.model.org.PB01AOrgDivision;
import pb01c.rest.resources.delegates.PB01CRESTCRUDDelegateForOrgDivision;
import pb01c.server.rest.resources.PB01CRESTCRUDResourceBaseForOrganizationalEntity;

/**
 * The implementation must follow the HTTP 1.1 spec (http://www.w3.org/Protocols/rfc2616/rfc2616.html), specifically
 * the section about the methods (GET, PUT, POST, DELETE...)
 *
 * Log: see web.xml
 */
@Path("divisions")
@Singleton
@Accessors(prefix="_")
public class PB01CRESTCRUDResourceForOrgDivision
	 extends PB01CRESTCRUDResourceBaseForOrganizationalEntity<PB01AOrgDivisionOID,PB01AOrgDivisionID,PB01AOrgDivision> {
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public PB01CRESTCRUDResourceForOrgDivision(final PB01ACRUDServicesForOrgDivision crudServices) {
		super(new PB01CRESTCRUDDelegateForOrgDivision(crudServices));
	}
}
