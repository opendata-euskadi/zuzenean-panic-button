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
import r01f.model.persistence.PersistenceException;
import r01f.types.TimeLapse;
import x47b.api.context.X47BSecurityContext;
import x47b.api.interfaces.X47BFindServicesForAlarmEvent;
import x47b.model.X47BAlarmEvent;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionID;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionServiceID;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionServiceLocationID;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrganizationID;
import x47b.model.oids.X47BOrganizationalIDs.X47BWorkPlaceID;
import x47b.model.oids.X47BPanicButtonOIDs.X47BAlarmEventOID;
import x47b.rest.resources.delegates.X47BRESTFindDelegateForAlarmEvent;
import x47b.server.rest.resources.X47BRESTFindResourceBase;

/**
 * The implementation must follow the HTTP 1.1 spec (http://www.w3.org/Protocols/rfc2616/rfc2616.html), specifically
 * the section about the methods (GET, PUT, POST, DELETE...)
 *
 * Log: see web.xml
 */
@Path("alarmEvents/list")
@Singleton
@Accessors(prefix="_")
public class X47BRESTFindResourceForAlarmEvent
	 extends X47BRESTFindResourceBase<X47BAlarmEventOID,X47BAlarmEvent> {
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public X47BRESTFindResourceForAlarmEvent(final X47BFindServicesForAlarmEvent findServices) {
		super(new X47BRESTFindDelegateForAlarmEvent(findServices));
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	EXTENSION METHODS
/////////////////////////////////////////////////////////////////////////////////////////
	@GET @Path("byOrganizationId/{id:.+}")
	@Produces(MediaType.APPLICATION_XML)
	public Response findBySourceId(@HeaderParam("securityContext")	final X47BSecurityContext securityContext,
						 	 	   @PathParam("id")  			final X47BOrganizationID id,
						 	 	   @QueryParam("timeLapse")		final TimeLapse timeLapse) throws PersistenceException {	
		return this.getDelegateAs(X47BRESTFindDelegateForAlarmEvent.class)
						.findBySourceId(securityContext,_req.getPathInfo(),
								  		id,
								  		timeLapse);
	}
	@GET @Path("byDivisionId/{id:.+}")
	@Produces(MediaType.APPLICATION_XML)
	public Response findBySourceId(@HeaderParam("securityContext")	final X47BSecurityContext securityContext,
						 	 	   @PathParam("id")  			final X47BOrgDivisionID id,
						 	 	   @QueryParam("timeLapse")		final TimeLapse timeLapse) throws PersistenceException {	
		return this.getDelegateAs(X47BRESTFindDelegateForAlarmEvent.class)
						.findBySourceId(securityContext,_req.getPathInfo(),
								  		id,
								  		timeLapse);
	}
	@GET @Path("byServiceId/{id:.+}")
	@Produces(MediaType.APPLICATION_XML)
	public Response findBySourceId(@HeaderParam("securityContext")	final X47BSecurityContext securityContext,
						 	 	   @PathParam("id")  			final X47BOrgDivisionServiceID id,
						 	 	   @QueryParam("timeLapse")		final TimeLapse timeLapse) throws PersistenceException {	
		return this.getDelegateAs(X47BRESTFindDelegateForAlarmEvent.class)
						.findBySourceId(securityContext,_req.getPathInfo(),
								  		id,
								  		timeLapse);
	}
	@GET @Path("byLocationId/{id:.+}")
	@Produces(MediaType.APPLICATION_XML)
	public Response findBySourceId(@HeaderParam("securityContext")	final X47BSecurityContext securityContext,
						 	 	   @PathParam("id")  			final X47BOrgDivisionServiceLocationID id,
						 	 	   @QueryParam("timeLapse")		final TimeLapse timeLapse) throws PersistenceException {	
		return this.getDelegateAs(X47BRESTFindDelegateForAlarmEvent.class)
						.findBySourceId(securityContext,_req.getPathInfo(),
								  		id,
								  		timeLapse);
	}
	@GET @Path("byWorkPlaceId/{id:.+}")
	@Produces(MediaType.APPLICATION_XML)
	public Response findBySourceId(@HeaderParam("securityContext")	final X47BSecurityContext securityContext,
						 	 	   @PathParam("id")  			final X47BWorkPlaceID id,
						 	 	   @QueryParam("timeLapse")		final TimeLapse timeLapse) throws PersistenceException {	
		return this.getDelegateAs(X47BRESTFindDelegateForAlarmEvent.class)
						.findBySourceId(securityContext,_req.getPathInfo(),
								  		id,
								  		timeLapse);
	}
}
