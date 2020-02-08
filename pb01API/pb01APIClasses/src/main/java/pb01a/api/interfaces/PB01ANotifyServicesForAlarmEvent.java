package pb01a.api.interfaces;

import pb01a.model.PB01AAlarmEvent;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AWorkPlaceID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AWorkPlaceOID;
import pb01a.model.oids.PB01APanicButtonOIDs.PB01AAlarmEventOID;
import r01f.model.persistence.CRUDResult;
import r01f.securitycontext.SecurityContext;
import r01f.services.interfaces.ExposedServiceInterface;

@ExposedServiceInterface
public interface PB01ANotifyServicesForAlarmEvent
		 extends PB01APanicButtonServiceInterface {
	/**
	 * Creates an alarm event using the {@link PB01AWorkPlaceID}
	 * This method might be located at {@link PB01ACRUDServicesForAlarmEvent} but it's at a specialized service
	 * @param securityContext
	 * @param id work place's id
	 * @return
	 */
	public CRUDResult<PB01AAlarmEvent> raiseAlarm(final SecurityContext securityContext,
												 final PB01AWorkPlaceID id);
	/**
	 * Creates an alarm event using the {@link PB01AWorkPlaceOID}
	 * This method might be located at {@link PB01ACRUDServicesForAlarmEvent} but it's at a specialized service
	 * @param securityContext
	 * @param oid the work place's oid
	 * @return
	 */
	public CRUDResult<PB01AAlarmEvent> raiseAlarm(final SecurityContext securityContext,
												 final PB01AWorkPlaceOID oid);
	/**
	 * The {@link CRUDResult} of the cancellation of a previously raised {@link PB01AAlarmEvent} entity
	 * This method could be located at {@link PB01ACRUDServicesForAlarmEvent} but it's at a specialized service
	 * @param securityContext
	 * @param oid the alarm event oid
	 * @return 
	 */
	public CRUDResult<PB01AAlarmEvent> cancelAlarm(final SecurityContext securityContext,
												  final PB01AAlarmEventOID oid);
}
