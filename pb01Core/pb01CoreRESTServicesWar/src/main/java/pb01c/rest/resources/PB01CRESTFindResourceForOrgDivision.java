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
import pb01a.api.interfaces.PB01AFindServicesForOrgDivision;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrganizationOID;
import pb01a.model.org.PB01AOrgDivision;
import pb01c.rest.resources.delegates.PB01CRESTFindDelegateForOrgDivision;
import pb01c.server.rest.resources.PB01CRESTFindResourceBaseForOrganizationalEntity;
import r01f.locale.Language;
import r01f.model.persistence.PersistenceException;

/**
 * The implementation must follow the HTTP 1.1 spec (http://www.w3.org/Protocols/rfc2616/rfc2616.html), specifically
 * the section about the methods (GET, PUT, POST, DELETE...)
 *
 * Log: see web.xml
 */
@Path("divisions/list")
@Singleton
@Accessors(prefix="_")
public class PB01CRESTFindResourceForOrgDivision
	 extends PB01CRESTFindResourceBaseForOrganizationalEntity<PB01AOrgDivisionOID,PB01AOrgDivisionID,PB01AOrgDivision> {
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public PB01CRESTFindResourceForOrgDivision(final PB01AFindServicesForOrgDivision findServices) {
		super(new PB01CRESTFindDelegateForOrgDivision(findServices));
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  FIND
/////////////////////////////////////////////////////////////////////////////////////////
	@GET @Path("byOrganization/{orgOid}")
	@Produces(MediaType.APPLICATION_XML)
	public Response findByOrganization(@HeaderParam("securityContext")	final PB01ASecurityContext securityContext,
							 	       @PathParam("orgOid")  		final PB01AOrganizationOID orgOid) throws PersistenceException {
		return this.getDelegateAs(PB01CRESTFindDelegateForOrgDivision.class)
						.findByOrganization(securityContext,_req.getPathInfo(),
									        orgOid);
	}
	@GET @Path("byOrganization/{orgOid}/summarized")	// would be better if summarized were a param BUT it cannot be done this way: conflict with findAll
	@Produces(MediaType.APPLICATION_XML)
	public Response findSummariesByOrganization(@HeaderParam("securityContext")	final PB01ASecurityContext securityContext,
												@PathParam("orgOid")  		final PB01AOrganizationOID orgOid,
							 	  				@QueryParam("lang")			final Language lang) throws PersistenceException {
		return this.getDelegateAs(PB01CRESTFindDelegateForOrgDivision.class)
						.findSummariesByOrganization(securityContext,_req.getPathInfo(),
									   				 orgOid,lang);
	}
}
