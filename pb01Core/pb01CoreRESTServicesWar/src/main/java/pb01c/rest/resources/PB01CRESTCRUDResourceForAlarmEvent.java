package pb01c.rest.resources;

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
import pb01a.api.context.PB01ASecurityContext;
import pb01a.api.interfaces.PB01ACRUDServicesForAlarmEvent;
import pb01a.api.interfaces.PB01ANotifyServicesForAlarmEvent;
import pb01a.model.PB01AAlarmEvent;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AWorkPlaceID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AWorkPlaceOID;
import pb01a.model.oids.PB01APanicButtonOIDs.PB01AAlarmEventOID;
import pb01c.rest.resources.delegates.PB01CRESTCRUDDelegateForAlarmEvent;
import pb01c.server.rest.resources.PB01CRESTCRUDResourceBase;

/**
 * The implementation must follow the HTTP 1.1 spec (http://www.w3.org/Protocols/rfc2616/rfc2616.html), specifically
 * the section about the methods (GET, PUT, POST, DELETE...)
 *
 * Log: see web.xml
 */
@Path("alarmEvents")
@Singleton
@Accessors(prefix="_")
public class PB01CRESTCRUDResourceForAlarmEvent
	 extends PB01CRESTCRUDResourceBase<PB01AAlarmEventOID,PB01AAlarmEvent> {
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public PB01CRESTCRUDResourceForAlarmEvent(final PB01ACRUDServicesForAlarmEvent crudServices,
											 final PB01ANotifyServicesForAlarmEvent notifyServices) {
		super(new PB01CRESTCRUDDelegateForAlarmEvent(crudServices,
													notifyServices));
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  EXTENSION METHODS
//	This methods come from the PB01ANotifyServicesForAlarmEvent interface BUT they're normal
//	CRUD methods so the correct place to be exposed as REST service is this resource
//
//	BEWARE that the normal CRUD create / update method DO NOT send any notification
/////////////////////////////////////////////////////////////////////////////////////////
	@POST @Path("bySourceId")
	@Produces(MediaType.APPLICATION_XML)
	public Response raiseAlarm(@HeaderParam("securityContext")	final PB01ASecurityContext securityContext,
							    							final PB01AWorkPlaceID id) {	// REST endpoint methods MUST be defined with concrete impls
		return this.getDelegateAs(PB01CRESTCRUDDelegateForAlarmEvent.class)
						.raiseAlarm(securityContext,_req.getPathInfo(),
									id);
	}
	@POST @Path("bySourceHost")
	@Produces(MediaType.APPLICATION_XML)
	public Response raiseAlarm(@HeaderParam("securityContext")	final PB01ASecurityContext securityContext,
							    							final PB01AWorkPlaceOID oid) {
		return this.getDelegateAs(PB01CRESTCRUDDelegateForAlarmEvent.class)
						.raiseAlarm(securityContext,_req.getPathInfo(),
									oid);
	}
	@DELETE @Path("{oid}")
	public Response cancelAlarm(@HeaderParam("securityContext")	final PB01ASecurityContext securityContext,
							    @PathParam("id") 			final PB01AAlarmEventOID oid) {
		return this.getDelegateAs(PB01CRESTCRUDDelegateForAlarmEvent.class)
						.cancelAlarm(securityContext,_req.getPathInfo(),
									 oid);
	}
}
