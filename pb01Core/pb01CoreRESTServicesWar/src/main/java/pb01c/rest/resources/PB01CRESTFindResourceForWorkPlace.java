package pb01c.rest.resources;

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
import pb01a.api.context.PB01ASecurityContext;
import pb01a.api.interfaces.PB01AFindServicesForWorkPlace;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AWorkPlaceID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionServiceLocationOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AWorkPlaceOID;
import pb01a.model.org.PB01AWorkPlace;
import pb01c.rest.resources.delegates.PB01CRESTFindDelegateForWorkPlace;
import pb01c.server.rest.resources.PB01CRESTFindResourceBaseForOrganizationalEntity;
import r01f.locale.Language;
import r01f.model.persistence.PersistenceException;

/**
 * The implementation must follow the HTTP 1.1 spec (http://www.w3.org/Protocols/rfc2616/rfc2616.html), specifically
 * the section about the methods (GET, PUT, POST, DELETE...)
 *
 * Log: see web.xml
 */
@Path("workPlaces/list")
@Singleton
@Accessors(prefix="_")
public class PB01CRESTFindResourceForWorkPlace
	 extends PB01CRESTFindResourceBaseForOrganizationalEntity<PB01AWorkPlaceOID,PB01AWorkPlaceID,PB01AWorkPlace> {
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public PB01CRESTFindResourceForWorkPlace(final PB01AFindServicesForWorkPlace findServices) {
		super(new PB01CRESTFindDelegateForWorkPlace(findServices));
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  FIND
/////////////////////////////////////////////////////////////////////////////////////////
	@GET @Path("byLocation/{locOid}")
	@Produces(MediaType.APPLICATION_XML)
	public Response findByOrgDivisionServiceLocation(@HeaderParam("securityContext")	final PB01ASecurityContext securityContext,
							 	   					 @PathParam("locOid")  			final PB01AOrgDivisionServiceLocationOID locOid) throws PersistenceException {
		return this.getDelegateAs(PB01CRESTFindDelegateForWorkPlace.class)
						.findByOrgDivisionServiceLocation(securityContext,_req.getPathInfo(),
									    				  locOid);
	}
	@GET @Path("byLocation/{locOid}/summarized")	// would be better if summarized were a param BUT it cannot be done this way: conflict with findAll
	@Produces(MediaType.APPLICATION_XML)
	public Response findSummariesByOrgDivisionServiceLocation(@HeaderParam("securityContext")	final PB01ASecurityContext securityContext,
															  @PathParam("locOid")  		final PB01AOrgDivisionServiceLocationOID locOid,
															  @QueryParam("lang")			final Language lang) throws PersistenceException {
		return this.getDelegateAs(PB01CRESTFindDelegateForWorkPlace.class)
						.findSummariesByOrgDivisionServiceLocation(securityContext,_req.getPathInfo(),
									   			 				   locOid,lang);
	}
}
