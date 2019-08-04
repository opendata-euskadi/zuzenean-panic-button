package x47b.rest.resources.delegates;

import java.net.URI;
import java.util.Date;

import javax.ws.rs.core.Response;

import r01f.model.persistence.FindResult;
import r01f.rest.RESTOperationsResponseBuilder;
import r01f.rest.resources.delegates.RESTFindDelegateBase;
import r01f.securitycontext.SecurityContext;
import r01f.types.Range;
import r01f.types.TimeLapse;
import x47b.api.interfaces.X47BFindServicesForAlarmEvent;
import x47b.model.X47BAlarmEvent;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionID;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionServiceID;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionServiceLocationID;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrganizationID;
import x47b.model.oids.X47BOrganizationalIDs.X47BWorkPlaceID;
import x47b.model.oids.X47BPanicButtonOIDs.X47BAlarmEventOID;

public class X47BRESTFindDelegateForAlarmEvent
	 extends RESTFindDelegateBase<X47BAlarmEventOID,X47BAlarmEvent> {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BRESTFindDelegateForAlarmEvent(final X47BFindServicesForAlarmEvent findServices) {
		super(X47BAlarmEvent.class,
			  findServices);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  USING DATE RANGE
/////////////////////////////////////////////////////////////////////////////////////////
	public Response findBySourceId(final SecurityContext securityContext,final String resourcePath,
								   final X47BOrganizationID id,
								   final Range<Date> dateRange) {
		FindResult<X47BAlarmEvent> findResult = this.getFindServicesAs(X47BFindServicesForAlarmEvent.class)
													.findBySourceId(securityContext,
																    id,
																    dateRange);
		return RESTOperationsResponseBuilder.findOn(_modelObjectType)
											.at(URI.create(resourcePath))
											.build(findResult);
	}
	public Response findBySourceId(final SecurityContext securityContext,final String resourcePath,
								   final X47BOrgDivisionID id,
								   final Range<Date> dateRange) {
		FindResult<X47BAlarmEvent> findResult = this.getFindServicesAs(X47BFindServicesForAlarmEvent.class)
													.findBySourceId(securityContext,
																    id,
																    dateRange);
		return RESTOperationsResponseBuilder.findOn(_modelObjectType)
											.at(URI.create(resourcePath))
											.build(findResult);
	}
	public Response findBySourceId(final SecurityContext securityContext,final String resourcePath,
								   final X47BOrgDivisionServiceID id,
								   final Range<Date> dateRange) {
		FindResult<X47BAlarmEvent> findResult = this.getFindServicesAs(X47BFindServicesForAlarmEvent.class)
													.findBySourceId(securityContext,
																    id,
																    dateRange);
		return RESTOperationsResponseBuilder.findOn(_modelObjectType)
											.at(URI.create(resourcePath))
											.build(findResult);
	}
	public Response findBySourceId(final SecurityContext securityContext,final String resourcePath,
								   final X47BOrgDivisionServiceLocationID id,
								   final Range<Date> dateRange) {
		FindResult<X47BAlarmEvent> findResult = this.getFindServicesAs(X47BFindServicesForAlarmEvent.class)
													.findBySourceId(securityContext,
																    id,
																    dateRange);
		return RESTOperationsResponseBuilder.findOn(_modelObjectType)
											.at(URI.create(resourcePath))
											.build(findResult);
	}
	public Response findBySourceId(final SecurityContext securityContext,final String resourcePath,
								   final X47BWorkPlaceID id,
								   final Range<Date> dateRange) {
		FindResult<X47BAlarmEvent> findResult = this.getFindServicesAs(X47BFindServicesForAlarmEvent.class)
													.findBySourceId(securityContext,
																    id,
																    dateRange);
		return RESTOperationsResponseBuilder.findOn(_modelObjectType)
											.at(URI.create(resourcePath))
											.build(findResult);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  USING TIME LAPSE
/////////////////////////////////////////////////////////////////////////////////////////
	public Response findBySourceId(final SecurityContext securityContext,final String resourcePath,
								   final X47BOrganizationID id,
								   final TimeLapse timeLapse) {
		return this.findBySourceId(securityContext,resourcePath,
								   id,_createeDateRange(timeLapse));
	}
	public Response findBySourceId(final SecurityContext securityContext,final String resourcePath,
								   final X47BOrgDivisionID id,
								   final TimeLapse timeLapse) {
		return this.findBySourceId(securityContext,resourcePath,
								   id,_createeDateRange(timeLapse));
	}
	public Response findBySourceId(final SecurityContext securityContext,final String resourcePath,
								   final X47BOrgDivisionServiceID id,
								   final TimeLapse timeLapse) {
		return this.findBySourceId(securityContext,resourcePath,
								   id,_createeDateRange(timeLapse));
	}
	public Response findBySourceId(final SecurityContext securityContext,final String resourcePath,
								   final X47BOrgDivisionServiceLocationID id,
								   final TimeLapse timeLapse) {
		return this.findBySourceId(securityContext,resourcePath,
								   id,_createeDateRange(timeLapse));
	}
	public Response findBySourceId(final SecurityContext securityContext,final String resourcePath,
								   final X47BWorkPlaceID id,
								   final TimeLapse timeLapse) {
		return this.findBySourceId(securityContext,resourcePath,
								   id,_createeDateRange(timeLapse));
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	private Range<Date> _createeDateRange(final TimeLapse timeLapse) {
		TimeLapse defTimeLapse = TimeLapse.createFor("1d");	// 1 day

		TimeLapse theTimeLapse = timeLapse != null ? timeLapse : defTimeLapse;

		Date now = new Date();
		Date startDate = new Date(now.getTime() - theTimeLapse.asMilis());	// time before
		Date endDate = now;

		return Range.closed(startDate,endDate);
	}
}
