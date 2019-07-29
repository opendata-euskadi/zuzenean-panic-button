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
import x47b.api.interfaces.X47BFindServicesForOrgDivisionService;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionServiceID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceOID;
import x47b.model.org.X47BOrgDivisionService;
import x47b.rest.resources.delegates.X47BRESTFindDelegateForOrgDivisionService;
import x47b.server.rest.resources.X47BRESTFindResourceBaseForOrganizationalEntity;

/**
 * The implementation must follow the HTTP 1.1 spec (http://www.w3.org/Protocols/rfc2616/rfc2616.html), specifically
 * the section about the methods (GET, PUT, POST, DELETE...)
 *
 * Log: see web.xml
 */
@Path("services/list")
@Singleton
@Accessors(prefix="_")
public class X47BRESTFindResourceForOrgDivisionService
	 extends X47BRESTFindResourceBaseForOrganizationalEntity<X47BOrgDivisionServiceOID,X47BOrgDivisionServiceID,X47BOrgDivisionService> {
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public X47BRESTFindResourceForOrgDivisionService(final X47BFindServicesForOrgDivisionService findServices) {
		super(new X47BRESTFindDelegateForOrgDivisionService(findServices));
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  FIND
/////////////////////////////////////////////////////////////////////////////////////////
	@GET @Path("byDivision/{divOid}")
	@Produces(MediaType.APPLICATION_XML)
	public Response findByOrgDivision(@HeaderParam("securityContext")	final X47BSecurityContext securityContext,
							 	   	  @PathParam("divOid")  		final X47BOrgDivisionOID divisionOid) throws PersistenceException {
		return this.getDelegateAs(X47BRESTFindDelegateForOrgDivisionService.class)
						.findByOrgDivision(securityContext,_req.getPathInfo(),
									       divisionOid);
	}
	@GET @Path("byDivision/{divOid}/summarized")	// would be better if summarized were a param BUT it cannot be done this way: conflict with findAll
	@Produces(MediaType.APPLICATION_XML)
	public Response findSummariesByOrgDivision(@HeaderParam("securityContext")	final X47BSecurityContext securityContext,
											   @PathParam("divOid")  		final X47BOrgDivisionOID divisionOid,
							 	  			   @QueryParam("lang")			final Language lang) throws PersistenceException {
		return this.getDelegateAs(X47BRESTFindDelegateForOrgDivisionService.class)
						.findSummariesByOrgDivision(securityContext,_req.getPathInfo(),
									   			    divisionOid,lang);
	}
}
