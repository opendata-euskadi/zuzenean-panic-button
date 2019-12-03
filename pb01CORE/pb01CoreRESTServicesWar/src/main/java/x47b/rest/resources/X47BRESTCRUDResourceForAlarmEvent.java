package x47b.rest.resources;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.DELETE;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import lombok.experimental.Accessors;
import x47b.api.context.X47BSecurityContext;
import x47b.api.interfaces.X47BCRUDServicesForAlarmEvent;
import x47b.api.interfaces.X47BNotifyServicesForAlarmEvent;
import x47b.model.X47BAlarmEvent;
import x47b.model.oids.X47BOrganizationalIDs.X47BWorkPlaceID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BWorkPlaceOID;
import x47b.model.oids.X47BPanicButtonOIDs.X47BAlarmEventOID;
import x47b.rest.resources.delegates.X47BRESTCRUDDelegateForAlarmEvent;
import x47b.server.rest.resources.X47BRESTCRUDResourceBase;

/**
 * The implementation must follow the HTTP 1.1 spec (http://www.w3.org/Protocols/rfc2616/rfc2616.html), specifically
 * the section about the methods (GET, PUT, POST, DELETE...)
 *
 * Log: see web.xml
 */
@Path("alarmEvents")
@Singleton
@Accessors(prefix="_")
public class X47BRESTCRUDResourceForAlarmEvent
	 extends X47BRESTCRUDResourceBase<X47BAlarmEventOID,X47BAlarmEvent> {
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public X47BRESTCRUDResourceForAlarmEvent(final X47BCRUDServicesForAlarmEvent crudServices,
											 final X47BNotifyServicesForAlarmEvent notifyServices) {
		super(new X47BRESTCRUDDelegateForAlarmEvent(crudServices,
													notifyServices));
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  EXTENSION METHODS
//	This methods come from the X47BNotifyServicesForAlarmEvent interface BUT they're normal
//	CRUD methods so the correct place to be exposed as REST service is this resource
//
//	BEWARE that the normal CRUD create / update method DO NOT send any notification 
/////////////////////////////////////////////////////////////////////////////////////////
	@POST @Path("bySourceId")
	@Produces(MediaType.APPLICATION_XML)
	public Response raiseAlarm(@HeaderParam("securityContext")	final X47BSecurityContext securityContext,
							    							final X47BWorkPlaceID id) {	// REST endpoint methods MUST be defined with concrete impls
		return this.getDelegateAs(X47BRESTCRUDDelegateForAlarmEvent.class)
						.raiseAlarm(securityContext,_req.getPathInfo(),
									id);
	}
	@POST @Path("bySourceHost")
	@Produces(MediaType.APPLICATION_XML)
	public Response raiseAlarm(@HeaderParam("securityContext")	final X47BSecurityContext securityContext,
							    							final X47BWorkPlaceOID oid) {	
		return this.getDelegateAs(X47BRESTCRUDDelegateForAlarmEvent.class)
						.raiseAlarm(securityContext,_req.getPathInfo(),
									oid);
	}
	@DELETE @Path("{oid}")
	public Response cancelAlarm(@HeaderParam("securityContext")	final X47BSecurityContext securityContext,
							    @PathParam("id") 			final X47BAlarmEventOID oid) {
		return this.getDelegateAs(X47BRESTCRUDDelegateForAlarmEvent.class)
						.cancelAlarm(securityContext,_req.getPathInfo(),
									 oid);
	}
}
