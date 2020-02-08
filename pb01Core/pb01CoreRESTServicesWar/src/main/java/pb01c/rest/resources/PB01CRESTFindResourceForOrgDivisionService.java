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
import pb01a.api.interfaces.PB01AFindServicesForOrgDivisionService;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionServiceID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionServiceOID;
import pb01a.model.org.PB01AOrgDivisionService;
import pb01c.rest.resources.delegates.PB01CRESTFindDelegateForOrgDivisionService;
import pb01c.server.rest.resources.PB01CRESTFindResourceBaseForOrganizationalEntity;
import r01f.locale.Language;
import r01f.model.persistence.PersistenceException;

/**
 * The implementation must follow the HTTP 1.1 spec (http://www.w3.org/Protocols/rfc2616/rfc2616.html), specifically
 * the section about the methods (GET, PUT, POST, DELETE...)
 *
 * Log: see web.xml
 */
@Path("services/list")
@Singleton
@Accessors(prefix="_")
public class PB01CRESTFindResourceForOrgDivisionService
	 extends PB01CRESTFindResourceBaseForOrganizationalEntity<PB01AOrgDivisionServiceOID,PB01AOrgDivisionServiceID,PB01AOrgDivisionService> {
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public PB01CRESTFindResourceForOrgDivisionService(final PB01AFindServicesForOrgDivisionService findServices) {
		super(new PB01CRESTFindDelegateForOrgDivisionService(findServices));
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  FIND
/////////////////////////////////////////////////////////////////////////////////////////
	@GET @Path("byDivision/{divOid}")
	@Produces(MediaType.APPLICATION_XML)
	public Response findByOrgDivision(@HeaderParam("securityContext")	final PB01ASecurityContext securityContext,
							 	   	  @PathParam("divOid")  		final PB01AOrgDivisionOID divisionOid) throws PersistenceException {
		return this.getDelegateAs(PB01CRESTFindDelegateForOrgDivisionService.class)
						.findByOrgDivision(securityContext,_req.getPathInfo(),
									       divisionOid);
	}
	@GET @Path("byDivision/{divOid}/summarized")	// would be better if summarized were a param BUT it cannot be done this way: conflict with findAll
	@Produces(MediaType.APPLICATION_XML)
	public Response findSummariesByOrgDivision(@HeaderParam("securityContext")	final PB01ASecurityContext securityContext,
											   @PathParam("divOid")  		final PB01AOrgDivisionOID divisionOid,
							 	  			   @QueryParam("lang")			final Language lang) throws PersistenceException {
		return this.getDelegateAs(PB01CRESTFindDelegateForOrgDivisionService.class)
						.findSummariesByOrgDivision(securityContext,_req.getPathInfo(),
									   			    divisionOid,lang);
	}
}
