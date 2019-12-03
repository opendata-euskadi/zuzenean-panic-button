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
import x47b.api.interfaces.X47BFindServicesForWorkPlace;
import x47b.model.oids.X47BOrganizationalIDs.X47BWorkPlaceID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceLocationOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BWorkPlaceOID;
import x47b.model.org.X47BWorkPlace;
import x47b.rest.resources.delegates.X47BRESTFindDelegateForWorkPlace;
import x47b.server.rest.resources.X47BRESTFindResourceBaseForOrganizationalEntity;

/**
 * The implementation must follow the HTTP 1.1 spec (http://www.w3.org/Protocols/rfc2616/rfc2616.html), specifically
 * the section about the methods (GET, PUT, POST, DELETE...)
 *
 * Log: see web.xml
 */
@Path("workPlaces/list")
@Singleton
@Accessors(prefix="_")
public class X47BRESTFindResourceForWorkPlace
	 extends X47BRESTFindResourceBaseForOrganizationalEntity<X47BWorkPlaceOID,X47BWorkPlaceID,X47BWorkPlace> {
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public X47BRESTFindResourceForWorkPlace(final X47BFindServicesForWorkPlace findServices) {
		super(new X47BRESTFindDelegateForWorkPlace(findServices));
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  FIND
/////////////////////////////////////////////////////////////////////////////////////////
	@GET @Path("byLocation/{locOid}")
	@Produces(MediaType.APPLICATION_XML)
	public Response findByOrgDivisionServiceLocation(@HeaderParam("securityContext")	final X47BSecurityContext securityContext,
							 	   					 @PathParam("locOid")  			final X47BOrgDivisionServiceLocationOID locOid) throws PersistenceException {
		return this.getDelegateAs(X47BRESTFindDelegateForWorkPlace.class)
						.findByOrgDivisionServiceLocation(securityContext,_req.getPathInfo(),
									    				  locOid);
	}
	@GET @Path("byLocation/{locOid}/summarized")	// would be better if summarized were a param BUT it cannot be done this way: conflict with findAll
	@Produces(MediaType.APPLICATION_XML)
	public Response findSummariesByOrgDivisionServiceLocation(@HeaderParam("securityContext")	final X47BSecurityContext securityContext,
															  @PathParam("locOid")  		final X47BOrgDivisionServiceLocationOID locOid,
															  @QueryParam("lang")			final Language lang) throws PersistenceException {
		return this.getDelegateAs(X47BRESTFindDelegateForWorkPlace.class)
						.findSummariesByOrgDivisionServiceLocation(securityContext,_req.getPathInfo(),
									   			 				   locOid,lang);
	}
}
