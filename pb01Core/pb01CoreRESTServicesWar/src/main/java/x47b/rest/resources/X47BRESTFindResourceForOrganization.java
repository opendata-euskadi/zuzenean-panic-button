package x47b.rest.resources;

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
import r01f.locale.Language;
import r01f.model.persistence.PersistenceException;
import x47b.api.context.X47BSecurityContext;
import x47b.api.interfaces.X47BFindServicesForOrganization;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrganizationID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrganizationOID;
import x47b.model.org.X47BOrganization;
import x47b.rest.resources.delegates.X47BRESTFindDelegateForOrganization;
import x47b.server.rest.resources.X47BRESTFindResourceBaseForOrganizationalEntity;

/**
 * The implementation must follow the HTTP 1.1 spec (http://www.w3.org/Protocols/rfc2616/rfc2616.html), specifically
 * the section about the methods (GET, PUT, POST, DELETE...)
 *
 * Log: see web.xml
 */
@Path("organizations/list")
@Singleton
@Accessors(prefix="_")
public class X47BRESTFindResourceForOrganization
	 extends X47BRESTFindResourceBaseForOrganizationalEntity<X47BOrganizationOID,X47BOrganizationID,X47BOrganization> {
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public X47BRESTFindResourceForOrganization(final X47BFindServicesForOrganization findServices) {
		super(new X47BRESTFindDelegateForOrganization(findServices));
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	@GET @Path("summarized")	// would be better if summarized were a param BUT it cannot be done this way: conflict with findAll
	@Produces(MediaType.APPLICATION_XML)
	public Response findSummaries(@HeaderParam("securityContext")	final X47BSecurityContext securityContext,
							 	  @QueryParam("lang")			final Language lang) throws PersistenceException {
		return this.getDelegateAs(X47BRESTFindDelegateForOrganization.class)
						.findSummaries(securityContext,_req.getPathInfo(),
									   lang);
	}
}
