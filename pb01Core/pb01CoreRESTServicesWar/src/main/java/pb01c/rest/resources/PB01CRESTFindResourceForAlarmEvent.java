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
import pb01a.api.interfaces.PB01AFindServicesForAlarmEvent;
import pb01a.model.PB01AAlarmEvent;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionID;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionServiceID;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionServiceLocationID;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrganizationID;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AWorkPlaceID;
import pb01a.model.oids.PB01APanicButtonOIDs.PB01AAlarmEventOID;
import pb01c.rest.resources.delegates.PB01CRESTFindDelegateForAlarmEvent;
import pb01c.server.rest.resources.PB01CRESTFindResourceBase;
import r01f.model.persistence.PersistenceException;
import r01f.types.TimeLapse;

/**
 * The implementation must follow the HTTP 1.1 spec (http://www.w3.org/Protocols/rfc2616/rfc2616.html), specifically
 * the section about the methods (GET, PUT, POST, DELETE...)
 *
 * Log: see web.xml
 */
@Path("alarmEvents/list")
@Singleton
@Accessors(prefix="_")
public class PB01CRESTFindResourceForAlarmEvent
	 extends PB01CRESTFindResourceBase<PB01AAlarmEventOID,PB01AAlarmEvent> {
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public PB01CRESTFindResourceForAlarmEvent(final PB01AFindServicesForAlarmEvent findServices) {
		super(new PB01CRESTFindDelegateForAlarmEvent(findServices));
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	EXTENSION METHODS
/////////////////////////////////////////////////////////////////////////////////////////
	@GET @Path("byOrganizationId/{id:.+}")
	@Produces(MediaType.APPLICATION_XML)
	public Response findBySourceId(@HeaderParam("securityContext")	final PB01ASecurityContext securityContext,
						 	 	   @PathParam("id")  			final PB01AOrganizationID id,
						 	 	   @QueryParam("timeLapse")		final TimeLapse timeLapse) throws PersistenceException {	
		return this.getDelegateAs(PB01CRESTFindDelegateForAlarmEvent.class)
						.findBySourceId(securityContext,_req.getPathInfo(),
								  		id,
								  		timeLapse);
	}
	@GET @Path("byDivisionId/{id:.+}")
	@Produces(MediaType.APPLICATION_XML)
	public Response findBySourceId(@HeaderParam("securityContext")	final PB01ASecurityContext securityContext,
						 	 	   @PathParam("id")  			final PB01AOrgDivisionID id,
						 	 	   @QueryParam("timeLapse")		final TimeLapse timeLapse) throws PersistenceException {	
		return this.getDelegateAs(PB01CRESTFindDelegateForAlarmEvent.class)
						.findBySourceId(securityContext,_req.getPathInfo(),
								  		id,
								  		timeLapse);
	}
	@GET @Path("byServiceId/{id:.+}")
	@Produces(MediaType.APPLICATION_XML)
	public Response findBySourceId(@HeaderParam("securityContext")	final PB01ASecurityContext securityContext,
						 	 	   @PathParam("id")  			final PB01AOrgDivisionServiceID id,
						 	 	   @QueryParam("timeLapse")		final TimeLapse timeLapse) throws PersistenceException {	
		return this.getDelegateAs(PB01CRESTFindDelegateForAlarmEvent.class)
						.findBySourceId(securityContext,_req.getPathInfo(),
								  		id,
								  		timeLapse);
	}
	@GET @Path("byLocationId/{id:.+}")
	@Produces(MediaType.APPLICATION_XML)
	public Response findBySourceId(@HeaderParam("securityContext")	final PB01ASecurityContext securityContext,
						 	 	   @PathParam("id")  			final PB01AOrgDivisionServiceLocationID id,
						 	 	   @QueryParam("timeLapse")		final TimeLapse timeLapse) throws PersistenceException {	
		return this.getDelegateAs(PB01CRESTFindDelegateForAlarmEvent.class)
						.findBySourceId(securityContext,_req.getPathInfo(),
								  		id,
								  		timeLapse);
	}
	@GET @Path("byWorkPlaceId/{id:.+}")
	@Produces(MediaType.APPLICATION_XML)
	public Response findBySourceId(@HeaderParam("securityContext")	final PB01ASecurityContext securityContext,
						 	 	   @PathParam("id")  			final PB01AWorkPlaceID id,
						 	 	   @QueryParam("timeLapse")		final TimeLapse timeLapse) throws PersistenceException {	
		return this.getDelegateAs(PB01CRESTFindDelegateForAlarmEvent.class)
						.findBySourceId(securityContext,_req.getPathInfo(),
								  		id,
								  		timeLapse);
	}
}
