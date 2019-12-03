package x47b.rest.resources;

import java.util.Collection;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.FluentIterable;

import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import r01f.locale.Language;
import r01f.rest.RESTResource;
import r01f.types.TimeLapse;
import r01f.util.types.collections.CollectionUtils;
import x47b.client.api.X47BPanicButtonClientAPI;
import x47b.model.X47BAlarmEvent;
import x47b.model.oids.X47BOrganizationalIDs.X47BWorkPlaceID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BWorkPlaceOID;
import x47b.model.org.X47BWorkPlace;

/**
 * NOT AUTHENTICATED METHODS intended to be used by the .net [Panic Button] client to
 * <ul>
 * 		<li>raising {@link X47BAlarmEvent}</li>
 * 		<li>querying {@link X47BAlarmEvent}s on the same {@link X47BLocation}</li>
 * </ul>
 * 
 * These methods are NON-STANDARD REST METHODS because:
 *		- raiseAlarm methods SHOULD be POST methods because they creates a resource
 *		- they SHOULD be authenticated
 * ... but they've been implemented as GET UN-AUTHENTICATED methods in order to
 * make the .net development easier 
 */
@Path("alarmEvents/noauth")
@Singleton
@Accessors(prefix="_")
@Slf4j
public class X47BRESTResourceForAlarmEventClient
  implements RESTResource {
/////////////////////////////////////////////////////////////////////////////////////////
//  INJECTED PER REQUEST
/////////////////////////////////////////////////////////////////////////////////////////
	@Context 
	protected HttpServletRequest _req;		// injected per request
/////////////////////////////////////////////////////////////////////////////////////////
//  FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	private final X47BPanicButtonClientAPI _clientApi;
	
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public X47BRESTResourceForAlarmEventClient(final X47BPanicButtonClientAPI clientApi) {
		_clientApi = clientApi;
	}
/////////////////////////////////////////////////////////////////////////////////////////
// NOT AUTHENTICATED METHODS
// 		These methods are NON-STANDARD REST METHODS because:
//			- raiseAlarm methods SHOULD be POST methods because they creates a resource
//			- they SHOULD be authenticated
// ... but they've been implemented as GET UN-AUTHENTICATED methods in order to 
// make the .net development easier
/////////////////////////////////////////////////////////////////////////////////////////
	@GET @Path("raise/{host}")
	@Produces(MediaType.TEXT_PLAIN)
	public boolean raiseAlarm(@PathParam("host")  final String host,
							  @QueryParam("test") final boolean test) {	
		
		X47BWorkPlaceID theHost = X47BWorkPlaceID.forId(host.toUpperCase());
		
		// use the client api to raise the alarm
		log.info("Raised an alarm for host={}",theHost);
		if (test) {
			// Check that the work place's host is registered
			return _clientApi.workPlacesAPI()
								.getForCRUD()
									.loadByIdOrNull(theHost) != null;	// returns true if the work place is found by his host name
		}
		
		// the normal case
		X47BAlarmEvent createdAlarm = _clientApi.alarmEventsAPI()
													.getForNotify()
															.raiseAlarm(theHost);
		return createdAlarm != null;
	}
	@GET @Path("find/{host}")
	@Produces(MediaType.TEXT_PLAIN)
	public String findAlarmsFromWorkPlaceLocation(@PathParam("host") 	   final String host,
											  	  @QueryParam("timeLapse") final String timeLapse) {
		X47BWorkPlaceID theHost = X47BWorkPlaceID.forId(host.toUpperCase());
		
		// Find the location's alarms
		log.info("WorkPlace with host={} location alarm events within {} ",host.toUpperCase(),timeLapse);
		Collection<X47BAlarmEvent> alarms = _clientApi.alarmEventsAPI()
															.getForFind()
																.findBySourceId(theHost,
																			    TimeLapse.createFor(timeLapse));
		String outAlarms = "null";
		if (CollectionUtils.hasData(alarms)) {
			outAlarms = Joiner.on(",")
						      .skipNulls()
						      .join(FluentIterable.from(alarms)
									   			  // Get the names of the work places
									   			  .transform(new Function<X47BAlarmEvent,String>() {
																	@Override
																	public String apply(final X47BAlarmEvent alarm) {
																		X47BWorkPlaceOID workPlaceOid = alarm.getWorkPlace().getOid();
																		X47BWorkPlace workPlace = _clientApi.workPlacesAPI()
																										.getForCRUD()
																											.load(workPlaceOid);
																		return "{\"host: \"" + workPlace.getId() + "\",\"name\":\"" + workPlace.getName().getIn(Language.SPANISH) + "\"}";
																	}
									   			   			  })
									   			  .iterator());
		}
		return "[" + outAlarms + "]";
	}
}
