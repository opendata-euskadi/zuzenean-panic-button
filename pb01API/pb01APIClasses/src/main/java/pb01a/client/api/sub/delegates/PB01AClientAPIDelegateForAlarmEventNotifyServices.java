package pb01a.client.api.sub.delegates;

import javax.inject.Provider;

import pb01a.api.interfaces.PB01ACRUDServicesForAlarmEvent;
import pb01a.api.interfaces.PB01ANotifyServicesForAlarmEvent;
import pb01a.model.PB01AAlarmEvent;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AWorkPlaceID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AWorkPlaceOID;
import pb01a.model.oids.PB01APanicButtonOIDs.PB01AAlarmEventOID;
import r01f.model.persistence.CRUDResult;
import r01f.objectstreamer.Marshaller;
import r01f.securitycontext.SecurityContext;
import r01f.services.client.api.delegates.ClientAPIServiceDelegateBase;

public class PB01AClientAPIDelegateForAlarmEventNotifyServices
	 extends ClientAPIServiceDelegateBase<PB01ANotifyServicesForAlarmEvent> {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01AClientAPIDelegateForAlarmEventNotifyServices(final Provider<SecurityContext> securityContextProvider,
															final Marshaller modelObjectsMarshaller,
															final PB01ANotifyServicesForAlarmEvent crudServicesProxy) {
		super(securityContextProvider,
			  modelObjectsMarshaller,
			  crudServicesProxy);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	EXTENSION METHODS
/////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Creates an alarm event using the {@link PB01AWorkPlaceID}
	 * @param id 
	 * @return 
	 */
	public PB01AAlarmEvent raiseAlarm(final PB01AWorkPlaceID id) {
		CRUDResult<PB01AAlarmEvent> alarmNotifyResult = this.getServiceProxyAs(PB01ANotifyServicesForAlarmEvent.class)
																.raiseAlarm(this.getSecurityContext(),
																			id);
		PB01AAlarmEvent outAlarm = alarmNotifyResult.getOrThrow();
		return outAlarm;
	}
	/**
	 * Creates an alarm event using the {@link PB01AWorkPlaceOID}
	 * @param oid 
	 * @return 
	 */
	public PB01AAlarmEvent raiseAlarm(final PB01AWorkPlaceOID oid) {
		CRUDResult<PB01AAlarmEvent> alarmNotifyResult = this.getServiceProxyAs(PB01ANotifyServicesForAlarmEvent.class)
																.raiseAlarm(this.getSecurityContext(),
																			oid);
		PB01AAlarmEvent outAlarm = alarmNotifyResult.getOrThrow();
		return outAlarm;
	}
	/**
	 * The {@link CRUDResult} of the cancellation of a previously raised {@link PB01AAlarmEvent} entity
	 * This method could be located at {@link PB01ACRUDServicesForAlarmEvent} but it's at a specialized service
	 * @param securityContext
	 * @param oid the alarm event oid
	 * @return 
	 */
	public PB01AAlarmEvent cancelAlarm(final PB01AAlarmEventOID oid) {
		CRUDResult<PB01AAlarmEvent> alarmCancelResult = this.getServiceProxyAs(PB01ANotifyServicesForAlarmEvent.class)
																.cancelAlarm(this.getSecurityContext(),
																			 oid);
		PB01AAlarmEvent outAlarm = alarmCancelResult.getOrThrow();
		return outAlarm;
	}
}
