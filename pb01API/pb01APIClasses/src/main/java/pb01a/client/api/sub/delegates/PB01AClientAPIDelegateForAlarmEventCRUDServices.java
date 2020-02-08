package pb01a.client.api.sub.delegates;

import javax.inject.Provider;

import pb01a.api.interfaces.PB01ACRUDServicesForAlarmEvent;
import pb01a.model.PB01AAlarmEvent;
import pb01a.model.oids.PB01APanicButtonOIDs.PB01AAlarmEventOID;
import r01f.objectstreamer.Marshaller;
import r01f.securitycontext.SecurityContext;
import r01f.services.client.api.delegates.ClientAPIDelegateForModelObjectCRUDServices;

public class PB01AClientAPIDelegateForAlarmEventCRUDServices
	 extends ClientAPIDelegateForModelObjectCRUDServices<PB01AAlarmEventOID,PB01AAlarmEvent> {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01AClientAPIDelegateForAlarmEventCRUDServices(final Provider<SecurityContext> securityContextProvider,
														  final Marshaller modelObjectsMarshaller,
														  final PB01ACRUDServicesForAlarmEvent crudServicesProxy) {
		super(securityContextProvider,
			  modelObjectsMarshaller,
			  crudServicesProxy);
	}
}
