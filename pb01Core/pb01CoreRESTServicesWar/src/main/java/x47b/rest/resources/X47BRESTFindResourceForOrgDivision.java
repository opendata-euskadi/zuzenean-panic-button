package x47b.rest.resources;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import lombok.experimental.Accessors;
import r01f.locale.Language;
import r01f.model.persistence.PersistenceException;
import x47b.api.context.X47BSecurityContext;
import x47b.api.interfaces.X47BFindServicesForOrgDivision;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrganizationOID;
import x47b.model.org.X47BOrgDivision;
import x47b.rest.resources.delegates.X47BRESTFindDelegateForOrgDivision;
import x47b.server.rest.resources.X47BRESTFindResourceBaseForOrganizationalEntity;

/**
 * The implementation must follow the HTTP 1.1 spec (http://www.w3.org/Protocols/rfc2616/rfc2616.html), specifically
 * the section about the methods (GET, PUT, POST, DELETE...)
 *
 * Log: see web.xml
 */
@Path("divisions/list")
@Singleton
@Accessors(prefix="_")
public class X47BRESTFindResourceForOrgDivision
	 extends X47BRESTFindResourceBaseForOrganizationalEntity<X47BOrgDivisionOID,X47BOrgDivisionID,X47BOrgDivision> {
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public X47BRESTFindResourceForOrgDivision(final X47BFindServicesForOrgDivision findServices) {
		super(new X47BRESTFindDelegateForOrgDivision(findServices));
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  FIND
/////////////////////////////////////////////////////////////////////////////////////////
	@GET @Path("byOrganization/{orgOid}")
	@Produces(MediaType.APPLICATION_XML)
	public Response findByOrganization(@HeaderParam("securityContext")	final X47BSecurityContext securityContext,
							 	       @PathParam("orgOid")  		final X47BOrganizationOID orgOid) throws PersistenceException {
		return this.getDelegateAs(X47BRESTFindDelegateForOrgDivision.class)
						.findByOrganization(securityContext,_req.getPathInfo(),
									        orgOid);
	}
	@GET @Path("byOrganization/{orgOid}/summarized")	// would be better if summarized were a param BUT it cannot be done this way: conflict with findAll
	@Produces(MediaType.APPLICATION_XML)
	public Response findSummariesByOrganization(@HeaderParam("securityContext")	final X47BSecurityContext securityContext,
												@PathParam("orgOid")  		final X47BOrganizationOID orgOid,
							 	  				@QueryParam("lang")			final Language lang) throws PersistenceException {
		return this.getDelegateAs(X47BRESTFindDelegateForOrgDivision.class)
						.findSummariesByOrganization(securityContext,_req.getPathInfo(),
									   				 orgOid,lang);
	}
}
