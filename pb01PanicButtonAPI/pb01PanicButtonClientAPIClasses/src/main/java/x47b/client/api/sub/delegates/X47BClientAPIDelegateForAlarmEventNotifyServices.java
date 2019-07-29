package x47b.client.api.sub.delegates;

import javax.inject.Provider;

import r01f.model.persistence.CRUDResult;
import r01f.objectstreamer.Marshaller;
import r01f.securitycontext.SecurityContext;
import r01f.services.client.api.delegates.ClientAPIServiceDelegateBase;
import x47b.api.interfaces.X47BCRUDServicesForAlarmEvent;
import x47b.api.interfaces.X47BNotifyServicesForAlarmEvent;
import x47b.model.X47BAlarmEvent;
import x47b.model.oids.X47BOrganizationalIDs.X47BWorkPlaceID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BWorkPlaceOID;
import x47b.model.oids.X47BPanicButtonOIDs.X47BAlarmEventOID;

public class X47BClientAPIDelegateForAlarmEventNotifyServices
	 extends ClientAPIServiceDelegateBase<X47BNotifyServicesForAlarmEvent> {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BClientAPIDelegateForAlarmEventNotifyServices(final Provider<SecurityContext> securityContextProvider,
															final Marshaller modelObjectsMarshaller,
															final X47BNotifyServicesForAlarmEvent crudServicesProxy) {
		super(securityContextProvider,
			  modelObjectsMarshaller,
			  crudServicesProxy);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	EXTENSION METHODS
/////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Creates an alarm event using the {@link X47BWorkPlaceID}
	 * @param id 
	 * @return 
	 */
	public X47BAlarmEvent raiseAlarm(final X47BWorkPlaceID id) {
		CRUDResult<X47BAlarmEvent> alarmNotifyResult = this.getServiceProxyAs(X47BNotifyServicesForAlarmEvent.class)
																.raiseAlarm(this.getSecurityContext(),
																			id);
		X47BAlarmEvent outAlarm = alarmNotifyResult.getOrThrow();
		return outAlarm;
	}
	/**
	 * Creates an alarm event using the {@link X47BWorkPlaceOID}
	 * @param oid 
	 * @return 
	 */
	public X47BAlarmEvent raiseAlarm(final X47BWorkPlaceOID oid) {
		CRUDResult<X47BAlarmEvent> alarmNotifyResult = this.getServiceProxyAs(X47BNotifyServicesForAlarmEvent.class)
																.raiseAlarm(this.getSecurityContext(),
																			oid);
		X47BAlarmEvent outAlarm = alarmNotifyResult.getOrThrow();
		return outAlarm;
	}
	/**
	 * The {@link CRUDResult} of the cancellation of a previously raised {@link X47BAlarmEvent} entity
	 * This method could be located at {@link X47BCRUDServicesForAlarmEvent} but it's at a specialized service
	 * @param securityContext
	 * @param oid the alarm event oid
	 * @return 
	 */
	public X47BAlarmEvent cancelAlarm(final X47BAlarmEventOID oid) {
		CRUDResult<X47BAlarmEvent> alarmCancelResult = this.getServiceProxyAs(X47BNotifyServicesForAlarmEvent.class)
																.cancelAlarm(this.getSecurityContext(),
																			 oid);
		X47BAlarmEvent outAlarm = alarmCancelResult.getOrThrow();
		return outAlarm;
	}
}
