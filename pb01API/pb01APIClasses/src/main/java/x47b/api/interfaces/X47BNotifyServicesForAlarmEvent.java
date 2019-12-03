package x47b.api.interfaces;

import r01f.model.persistence.CRUDResult;
import r01f.securitycontext.SecurityContext;
import r01f.services.interfaces.ExposedServiceInterface;
import x47b.model.X47BAlarmEvent;
import x47b.model.oids.X47BOrganizationalIDs.X47BWorkPlaceID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BWorkPlaceOID;
import x47b.model.oids.X47BPanicButtonOIDs.X47BAlarmEventOID;

@ExposedServiceInterface
public interface X47BNotifyServicesForAlarmEvent
		 extends X47BPanicButtonServiceInterface {
	/**
	 * Creates an alarm event using the {@link X47BWorkPlaceID}
	 * This method might be located at {@link X47BCRUDServicesForAlarmEvent} but it's at a specialized service
	 * @param securityContext
	 * @param id work place's id
	 * @return
	 */
	public CRUDResult<X47BAlarmEvent> raiseAlarm(final SecurityContext securityContext,
												 final X47BWorkPlaceID id);
	/**
	 * Creates an alarm event using the {@link X47BWorkPlaceOID}
	 * This method might be located at {@link X47BCRUDServicesForAlarmEvent} but it's at a specialized service
	 * @param securityContext
	 * @param oid the work place's oid
	 * @return
	 */
	public CRUDResult<X47BAlarmEvent> raiseAlarm(final SecurityContext securityContext,
												 final X47BWorkPlaceOID oid);
	/**
	 * The {@link CRUDResult} of the cancellation of a previously raised {@link X47BAlarmEvent} entity
	 * This method could be located at {@link X47BCRUDServicesForAlarmEvent} but it's at a specialized service
	 * @param securityContext
	 * @param oid the alarm event oid
	 * @return 
	 */
	public CRUDResult<X47BAlarmEvent> cancelAlarm(final SecurityContext securityContext,
												  final X47BAlarmEventOID oid);
}
