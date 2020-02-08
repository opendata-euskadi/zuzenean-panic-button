package pb01a.client.api.sub.delegates;

import java.util.Collection;
import java.util.Date;

import javax.inject.Provider;

import pb01a.api.interfaces.PB01AFindServicesForAlarmEvent;
import pb01a.model.PB01AAlarmEvent;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionID;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionServiceID;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionServiceLocationID;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrganizationID;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AWorkPlaceID;
import pb01a.model.oids.PB01APanicButtonOIDs.PB01AAlarmEventOID;
import r01f.model.persistence.FindResult;
import r01f.objectstreamer.Marshaller;
import r01f.securitycontext.SecurityContext;
import r01f.services.client.api.delegates.ClientAPIDelegateForModelObjectFindServices;
import r01f.types.Range;
import r01f.types.TimeLapse;
import r01f.types.contact.EMail;
import r01f.types.contact.Phone;

public class PB01AClientAPIDelegateForAlarmEventFindServices
	 extends ClientAPIDelegateForModelObjectFindServices<PB01AAlarmEventOID,PB01AAlarmEvent> {;

/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01AClientAPIDelegateForAlarmEventFindServices(final Provider<SecurityContext> securityContextProvider,
														  final Marshaller modelObjectsMarshaller,
														  final PB01AFindServicesForAlarmEvent findServicesProxy) {
		super(securityContextProvider,
			  modelObjectsMarshaller,
			  findServicesProxy);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  EXTENSION METHODS
/////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Loads all alarms from an organization raised within the provided time lapse
	 * @param id
	 * @param dateRange
	 * @return
	 */
	public Collection<PB01AAlarmEvent> findBySourceId(final PB01AOrganizationID id,
													 final Range<Date> dateRange) {
		FindResult<PB01AAlarmEvent> findResult = this.getServiceProxyAs(PB01AFindServicesForAlarmEvent.class)
														.findBySourceId(this.getSecurityContext(),
															   	  		id,
															   	  		dateRange);
		Collection<PB01AAlarmEvent> outAlarmEvents = findResult.getOrThrow();
		return outAlarmEvents;
	}
	/**
	 * Loads all alarms from a division raised within the provided time lapse
	 * @param id
	 * @param dateRange
	 * @return
	 */
	public Collection<PB01AAlarmEvent> findBySourceId(final PB01AOrgDivisionID id,
													 final Range<Date> dateRange) {
		FindResult<PB01AAlarmEvent> findResult = this.getServiceProxyAs(PB01AFindServicesForAlarmEvent.class)
														.findBySourceId(this.getSecurityContext(),
															   	  		id,
															   	  		dateRange);
		Collection<PB01AAlarmEvent> outAlarmEvents = findResult.getOrThrow();
		return outAlarmEvents;
	}
	/**
	 * Loads all alarms from a service raised within the provided time lapse
	 * @param id
	 * @param dateRange
	 * @return
	 */
	public Collection<PB01AAlarmEvent> findBySourceId(final PB01AOrgDivisionServiceID id,
													 final Range<Date> dateRange) {
		FindResult<PB01AAlarmEvent> findResult = this.getServiceProxyAs(PB01AFindServicesForAlarmEvent.class)
														.findBySourceId(this.getSecurityContext(),
															   	  		id,
															   	  		dateRange);
		Collection<PB01AAlarmEvent> outAlarmEvents = findResult.getOrThrow();
		return outAlarmEvents;
	}
	/**
	 * Loads all alarms from a location raised within the provided time lapse
	 * @param id
	 * @param dateRange
	 * @return
	 */
	public Collection<PB01AAlarmEvent> findBySourceId(final PB01AOrgDivisionServiceLocationID id,
													 final Range<Date> dateRange) {
		FindResult<PB01AAlarmEvent> findResult = this.getServiceProxyAs(PB01AFindServicesForAlarmEvent.class)
														.findBySourceId(this.getSecurityContext(),
															   	  		id,
															   	  		dateRange);
		Collection<PB01AAlarmEvent> outAlarmEvents = findResult.getOrThrow();
		return outAlarmEvents;
	}
	/**
	 * Loads all alarms from a work place raised within the provided time lapse
	 * @param id
	 * @param dateRange
	 * @return
	 */
	public Collection<PB01AAlarmEvent> findBySourceId(final PB01AWorkPlaceID id,
													 final Range<Date> dateRange) {
		FindResult<PB01AAlarmEvent> findResult = this.getServiceProxyAs(PB01AFindServicesForAlarmEvent.class)
														.findBySourceId(this.getSecurityContext(),
															   	  		id,
															   	  		dateRange);
		Collection<PB01AAlarmEvent> outAlarmEvents = findResult.getOrThrow();
		return outAlarmEvents;
	}
	/**
	 * Loads all alarms notified to a given phone raised within the provided time lapse
	 * @param phone
	 * @param dateRange
	 * @return
	 */
	public Collection<PB01AAlarmEvent> findByNotifiedPhone(final Phone phone,
													 	  final Range<Date> dateRange) {
		FindResult<PB01AAlarmEvent> findResult = this.getServiceProxyAs(PB01AFindServicesForAlarmEvent.class)
														.findByNotifiedPhone(this.getSecurityContext(),
															   	  			 phone,
															   	  			 dateRange);
		Collection<PB01AAlarmEvent> outAlarmEvents = findResult.getOrThrow();
		return outAlarmEvents;
	}
	/**
	 * Loads all alarms notified to a given email raised within the provided time lapse
	 * @param phone
	 * @param dateRange
	 * @return
	 */
	public Collection<PB01AAlarmEvent> findByNotifiedEMail(final EMail email,
													 	  final Range<Date> dateRange) {
		FindResult<PB01AAlarmEvent> findResult = this.getServiceProxyAs(PB01AFindServicesForAlarmEvent.class)
														.findByNotifiedEMail(this.getSecurityContext(),
															   	  			 email,
															   	  			 dateRange);
		Collection<PB01AAlarmEvent> outAlarmEvents = findResult.getOrThrow();
		return outAlarmEvents;
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  EXTENSION METHODS
/////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Loads all alarms from an organization raised within the last minute
	 * @param id
	 * @return
	 */
	public Collection<PB01AAlarmEvent> findBySourceId(final PB01AOrganizationID id) {
		return this.findBySourceId(id,
								   TimeLapse.createFor("1m"));	// last minute
	}
	/**
	 * Loads all alarms from an organization raised within the provided time lapse
	 * @param id
	 * @param timeLapse
	 * @return
	 */
	public Collection<PB01AAlarmEvent> findBySourceId(final PB01AOrganizationID id,
													 final TimeLapse timeLapse) {
		return this.findBySourceId(id,_createeDateRange(timeLapse));
	}
	/**
	 * Loads all alarms from a division raised within the last minute
	 * @param id
	 * @return
	 */
	public Collection<PB01AAlarmEvent> findBySourceId(final PB01AOrgDivisionID id) {
		return this.findBySourceId(id,
								   TimeLapse.createFor("1m"));	// last minute
	}
	/**
	 * Loads all alarms from a division raised within the provided time lapse
	 * @param id
	 * @param timeLapse
	 * @return
	 */
	public Collection<PB01AAlarmEvent> findBySourceId(final PB01AOrgDivisionID id,
													 final TimeLapse timeLapse) {
		return this.findBySourceId(id,_createeDateRange(timeLapse));
	}
	/**
	 * Loads all alarms from a service raised within the last minute
	 * @param id
	 * @return
	 */
	public Collection<PB01AAlarmEvent> findBySourceId(final PB01AOrgDivisionServiceID id) {
		return this.findBySourceId(id,
								   TimeLapse.createFor("1m"));	// last minute
	}
	/**
	 * Loads all alarms from a service raised within the provided time lapse
	 * @param id
	 * @param timeLapse
	 * @return
	 */
	public Collection<PB01AAlarmEvent> findBySourceId(final PB01AOrgDivisionServiceID id,
													 final TimeLapse timeLapse) {
		return this.findBySourceId(id,_createeDateRange(timeLapse));
	}
	/**
	 * Loads all alarms from a location raised within the last minute
	 * @param id
	 * @return
	 */
	public Collection<PB01AAlarmEvent> findBySourceId(final PB01AOrgDivisionServiceLocationID id) {
		return this.findBySourceId(id,
								   TimeLapse.createFor("1m"));	// last minute
	}
	/**
	 * Loads all alarms from a location raised within the provided time lapse
	 * @param id
	 * @param timeLapse
	 * @return
	 */
	public Collection<PB01AAlarmEvent> findBySourceId(final PB01AOrgDivisionServiceLocationID id,
													 final TimeLapse timeLapse) {
		return this.findBySourceId(id,_createeDateRange(timeLapse));
	}
	/**
	 * Loads all alarms from a work place raised within the last minute
	 * @param id
	 * @return
	 */
	public Collection<PB01AAlarmEvent> findBySourceId(final PB01AWorkPlaceID id) {
		return this.findBySourceId(id,
								   TimeLapse.createFor("1m"));	// last minute
	}
	/**
	 * Loads all alarms from a work place raised within the provided time lapse
	 * @param id
	 * @param timeLapse
	 * @return
	 */
	public Collection<PB01AAlarmEvent> findBySourceId(final PB01AWorkPlaceID id,
													 final TimeLapse timeLapse) {
		return this.findBySourceId(id,_createeDateRange(timeLapse));
	}
	/**
	 * Loads all alarms notified to a given phone raised within the last minute
	 * @param phone
	 * @return
	 */
	public Collection<PB01AAlarmEvent> findByNotifiedPhone(final Phone phone) {
		return this.findByNotifiedPhone(phone,
								   		TimeLapse.createFor("1m"));	// last minute
	}
	/**
	 * Loads all alarms notified to a given phone raised within the provided time lapse
	 * @param phone
	 * @param timeLapse
	 * @return
	 */
	public Collection<PB01AAlarmEvent> findByNotifiedPhone(final Phone phone,
													 	  final TimeLapse timeLapse) {
		return this.findByNotifiedPhone(phone,_createeDateRange(timeLapse));
	}
	/**
	 * Loads all alarms notified to a given email raised within the last minute
	 * @param email
	 * @return
	 */
	public Collection<PB01AAlarmEvent> findByNotifiedEMail(final EMail email) {
		return this.findByNotifiedEMail(email,
								   		TimeLapse.createFor("1m"));	// last minute
	}
	/**
	 * Loads all alarms notified to a given email raised within the provided time lapse
	 * @param email
	 * @param timeLapse
	 * @return
	 */
	public Collection<PB01AAlarmEvent> findByNotifiedEMail(final EMail email,
													 	  final TimeLapse timeLapse) {
		return this.findByNotifiedEMail(email,_createeDateRange(timeLapse));
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
