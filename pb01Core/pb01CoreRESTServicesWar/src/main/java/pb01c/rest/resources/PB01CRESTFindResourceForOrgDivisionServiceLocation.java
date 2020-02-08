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
import pb01a.api.interfaces.PB01AFindServicesForOrgDivisionServiceLocation;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionServiceLocationID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionServiceLocationOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionServiceOID;
import pb01a.model.org.PB01AOrgDivisionServiceLocation;
import pb01c.rest.resources.delegates.PB01CRESTFindDelegateForOrgDivisionServiceLocation;
import pb01c.server.rest.resources.PB01CRESTFindResourceBaseForOrganizationalEntity;
import r01f.locale.Language;
import r01f.model.persistence.PersistenceException;

/**
 * The implementation must follow the HTTP 1.1 spec (http://www.w3.org/Protocols/rfc2616/rfc2616.html), specifically
 * the section about the methods (GET, PUT, POST, DELETE...)
 *
 * Log: see web.xml
 */
@Path("locations/list")
@Singleton
@Accessors(prefix="_")
public class PB01CRESTFindResourceForOrgDivisionServiceLocation
	 extends PB01CRESTFindResourceBaseForOrganizationalEntity<PB01AOrgDivisionServiceLocationOID,PB01AOrgDivisionServiceLocationID,PB01AOrgDivisionServiceLocation> {
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public PB01CRESTFindResourceForOrgDivisionServiceLocation(final PB01AFindServicesForOrgDivisionServiceLocation findServices) {
		super(new PB01CRESTFindDelegateForOrgDivisionServiceLocation(findServices));
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  FIND
/////////////////////////////////////////////////////////////////////////////////////////
	@GET @Path("byService/{srvcOid}")
	@Produces(MediaType.APPLICATION_XML)
	public Response findByOrgDivisionService(@HeaderParam("securityContext")	final PB01ASecurityContext securityContext,
							 	   	  		 @PathParam("srvcOid")  		final PB01AOrgDivisionServiceOID serviceOid) throws PersistenceException {
		return this.getDelegateAs(PB01CRESTFindDelegateForOrgDivisionServiceLocation.class)
						.findByOrgDivisionService(securityContext,_req.getPathInfo(),
									       		  serviceOid);
	}
	@GET @Path("byService/{srvcOid}/summarized")	// would be better if summarized were a param BUT it cannot be done this way: conflict with findAll
	@Produces(MediaType.APPLICATION_XML)
	public Response findSummariesByOrgDivisionService(@HeaderParam("securityContext")	final PB01ASecurityContext securityContext,
											   		  @PathParam("srvcOid")  		final PB01AOrgDivisionServiceOID serviceOid,
											   		  @QueryParam("lang")			final Language lang) throws PersistenceException {
		return this.getDelegateAs(PB01CRESTFindDelegateForOrgDivisionServiceLocation.class)
						.findSummariesByOrgDivisionService(securityContext,_req.getPathInfo(),
									   			 		   serviceOid,lang);
	}
}
