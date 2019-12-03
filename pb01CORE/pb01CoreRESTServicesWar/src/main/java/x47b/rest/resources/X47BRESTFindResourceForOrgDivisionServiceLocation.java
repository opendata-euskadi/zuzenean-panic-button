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
import x47b.api.interfaces.X47BFindServicesForOrgDivisionServiceLocation;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionServiceLocationID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceLocationOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceOID;
import x47b.model.org.X47BOrgDivisionServiceLocation;
import x47b.rest.resources.delegates.X47BRESTFindDelegateForOrgDivisionServiceLocation;
import x47b.server.rest.resources.X47BRESTFindResourceBaseForOrganizationalEntity;

/**
 * The implementation must follow the HTTP 1.1 spec (http://www.w3.org/Protocols/rfc2616/rfc2616.html), specifically
 * the section about the methods (GET, PUT, POST, DELETE...)
 *
 * Log: see web.xml
 */
@Path("locations/list")
@Singleton
@Accessors(prefix="_")
public class X47BRESTFindResourceForOrgDivisionServiceLocation
	 extends X47BRESTFindResourceBaseForOrganizationalEntity<X47BOrgDivisionServiceLocationOID,X47BOrgDivisionServiceLocationID,X47BOrgDivisionServiceLocation> {
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public X47BRESTFindResourceForOrgDivisionServiceLocation(final X47BFindServicesForOrgDivisionServiceLocation findServices) {
		super(new X47BRESTFindDelegateForOrgDivisionServiceLocation(findServices));
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  FIND
/////////////////////////////////////////////////////////////////////////////////////////
	@GET @Path("byService/{srvcOid}")
	@Produces(MediaType.APPLICATION_XML)
	public Response findByOrgDivisionService(@HeaderParam("securityContext")	final X47BSecurityContext securityContext,
							 	   	  		 @PathParam("srvcOid")  		final X47BOrgDivisionServiceOID serviceOid) throws PersistenceException {
		return this.getDelegateAs(X47BRESTFindDelegateForOrgDivisionServiceLocation.class)
						.findByOrgDivisionService(securityContext,_req.getPathInfo(),
									       		  serviceOid);
	}
	@GET @Path("byService/{srvcOid}/summarized")	// would be better if summarized were a param BUT it cannot be done this way: conflict with findAll
	@Produces(MediaType.APPLICATION_XML)
	public Response findSummariesByOrgDivisionService(@HeaderParam("securityContext")	final X47BSecurityContext securityContext,
											   		  @PathParam("srvcOid")  		final X47BOrgDivisionServiceOID serviceOid,
											   		  @QueryParam("lang")			final Language lang) throws PersistenceException {
		return this.getDelegateAs(X47BRESTFindDelegateForOrgDivisionServiceLocation.class)
						.findSummariesByOrgDivisionService(securityContext,_req.getPathInfo(),
									   			 		   serviceOid,lang);
	}
}
