package pb01c.rest.resources;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import lombok.experimental.Accessors;
import pb01a.api.context.PB01ASecurityContext;
import pb01a.api.interfaces.PB01AFindServicesForOrganization;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrganizationID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrganizationOID;
import pb01a.model.org.PB01AOrganization;
import pb01c.rest.resources.delegates.PB01CRESTFindDelegateForOrganization;
import pb01c.server.rest.resources.PB01CRESTFindResourceBaseForOrganizationalEntity;
import r01f.locale.Language;
import r01f.model.persistence.PersistenceException;

/**
 * The implementation must follow the HTTP 1.1 spec (http://www.w3.org/Protocols/rfc2616/rfc2616.html), specifically
 * the section about the methods (GET, PUT, POST, DELETE...)
 *
 * Log: see web.xml
 */
@Path("organizations/list")
@Singleton
@Accessors(prefix="_")
public class PB01CRESTFindResourceForOrganization
	 extends PB01CRESTFindResourceBaseForOrganizationalEntity<PB01AOrganizationOID,PB01AOrganizationID,PB01AOrganization> {
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public PB01CRESTFindResourceForOrganization(final PB01AFindServicesForOrganization findServices) {
		super(new PB01CRESTFindDelegateForOrganization(findServices));
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	@GET @Path("summarized")	// would be better if summarized were a param BUT it cannot be done this way: conflict with findAll
	@Produces(MediaType.APPLICATION_XML)
	public Response findSummaries(@HeaderParam("securityContext")	final PB01ASecurityContext securityContext,
							 	  @QueryParam("lang")			final Language lang) throws PersistenceException {
		return this.getDelegateAs(PB01CRESTFindDelegateForOrganization.class)
						.findSummaries(securityContext,_req.getPathInfo(),
									   lang);
	}
}
