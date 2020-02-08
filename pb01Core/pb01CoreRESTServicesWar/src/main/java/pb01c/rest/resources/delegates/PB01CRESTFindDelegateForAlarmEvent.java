package pb01c.rest.resources.delegates;

import java.net.URI;
import java.util.Date;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import pb01a.api.interfaces.PB01AFindServicesForAlarmEvent;
import pb01a.model.PB01AAlarmEvent;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionID;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionServiceID;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionServiceLocationID;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrganizationID;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AWorkPlaceID;
import pb01a.model.oids.PB01APanicButtonOIDs.PB01AAlarmEventOID;
import r01f.model.persistence.FindResult;
import r01f.rest.RESTOperationsResponseBuilder;
import r01f.rest.resources.delegates.RESTFindDelegateBase;
import r01f.securitycontext.SecurityContext;
import r01f.types.Range;
import r01f.types.TimeLapse;

public class PB01CRESTFindDelegateForAlarmEvent
	 extends RESTFindDelegateBase<PB01AAlarmEventOID,PB01AAlarmEvent> {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01CRESTFindDelegateForAlarmEvent(final PB01AFindServicesForAlarmEvent findServices) {
		super(PB01AAlarmEvent.class,
			  findServices);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  USING DATE RANGE
/////////////////////////////////////////////////////////////////////////////////////////
	public Response findBySourceId(final SecurityContext securityContext,final String resourcePath,
								   final PB01AOrganizationID id,
								   final Range<Date> dateRange) {
		FindResult<PB01AAlarmEvent> findResult = this.getFindServicesAs(PB01AFindServicesForAlarmEvent.class)
													.findBySourceId(securityContext,
																    id,
																    dateRange);
		return RESTOperationsResponseBuilder.findOn(_modelObjectType)
											.at(URI.create(resourcePath))
											.mediaType(MediaType.TEXT_XML_TYPE)
											.build(findResult);
	}
	public Response findBySourceId(final SecurityContext securityContext,final String resourcePath,
								   final PB01AOrgDivisionID id,
								   final Range<Date> dateRange) {
		FindResult<PB01AAlarmEvent> findResult = this.getFindServicesAs(PB01AFindServicesForAlarmEvent.class)
													.findBySourceId(securityContext,
																    id,
																    dateRange);
		return RESTOperationsResponseBuilder.findOn(_modelObjectType)
											.at(URI.create(resourcePath))
											.mediaType(MediaType.TEXT_XML_TYPE)
											.build(findResult);
	}
	public Response findBySourceId(final SecurityContext securityContext,final String resourcePath,
								   final PB01AOrgDivisionServiceID id,
								   final Range<Date> dateRange) {
		FindResult<PB01AAlarmEvent> findResult = this.getFindServicesAs(PB01AFindServicesForAlarmEvent.class)
													.findBySourceId(securityContext,
																    id,
																    dateRange);
		return RESTOperationsResponseBuilder.findOn(_modelObjectType)
											.at(URI.create(resourcePath))
											.mediaType(MediaType.TEXT_XML_TYPE)
											.build(findResult);
	}
	public Response findBySourceId(final SecurityContext securityContext,final String resourcePath,
								   final PB01AOrgDivisionServiceLocationID id,
								   final Range<Date> dateRange) {
		FindResult<PB01AAlarmEvent> findResult = this.getFindServicesAs(PB01AFindServicesForAlarmEvent.class)
													.findBySourceId(securityContext,
																    id,
																    dateRange);
		return RESTOperationsResponseBuilder.findOn(_modelObjectType)
											.at(URI.create(resourcePath))
											.mediaType(MediaType.TEXT_XML_TYPE)
											.build(findResult);
	}
	public Response findBySourceId(final SecurityContext securityContext,final String resourcePath,
								   final PB01AWorkPlaceID id,
								   final Range<Date> dateRange) {
		FindResult<PB01AAlarmEvent> findResult = this.getFindServicesAs(PB01AFindServicesForAlarmEvent.class)
													.findBySourceId(securityContext,
																    id,
																    dateRange);
		return RESTOperationsResponseBuilder.findOn(_modelObjectType)
											.at(URI.create(resourcePath))
											.mediaType(MediaType.TEXT_XML_TYPE)
											.build(findResult);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  USING TIME LAPSE
/////////////////////////////////////////////////////////////////////////////////////////
	public Response findBySourceId(final SecurityContext securityContext,final String resourcePath,
								   final PB01AOrganizationID id,
								   final TimeLapse timeLapse) {
		return this.findBySourceId(securityContext,resourcePath,
								   id,_createeDateRange(timeLapse));
	}
	public Response findBySourceId(final SecurityContext securityContext,final String resourcePath,
								   final PB01AOrgDivisionID id,
								   final TimeLapse timeLapse) {
		return this.findBySourceId(securityContext,resourcePath,
								   id,_createeDateRange(timeLapse));
	}
	public Response findBySourceId(final SecurityContext securityContext,final String resourcePath,
								   final PB01AOrgDivisionServiceID id,
								   final TimeLapse timeLapse) {
		return this.findBySourceId(securityContext,resourcePath,
								   id,_createeDateRange(timeLapse));
	}
	public Response findBySourceId(final SecurityContext securityContext,final String resourcePath,
								   final PB01AOrgDivisionServiceLocationID id,
								   final TimeLapse timeLapse) {
		return this.findBySourceId(securityContext,resourcePath,
								   id,_createeDateRange(timeLapse));
	}
	public Response findBySourceId(final SecurityContext securityContext,final String resourcePath,
								   final PB01AWorkPlaceID id,
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
