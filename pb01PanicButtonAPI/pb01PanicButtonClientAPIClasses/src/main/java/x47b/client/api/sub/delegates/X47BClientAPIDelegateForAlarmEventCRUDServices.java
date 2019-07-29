package x47b.client.api.sub.delegates;

import javax.inject.Provider;

import r01f.objectstreamer.Marshaller;
import r01f.securitycontext.SecurityContext;
import r01f.services.client.api.delegates.ClientAPIDelegateForModelObjectCRUDServices;
import x47b.api.interfaces.X47BCRUDServicesForAlarmEvent;
import x47b.model.X47BAlarmEvent;
import x47b.model.oids.X47BPanicButtonOIDs.X47BAlarmEventOID;

public class X47BClientAPIDelegateForAlarmEventCRUDServices
	 extends ClientAPIDelegateForModelObjectCRUDServices<X47BAlarmEventOID,X47BAlarmEvent> {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BClientAPIDelegateForAlarmEventCRUDServices(final Provider<SecurityContext> securityContextProvider,
														  final Marshaller modelObjectsMarshaller,
														  final X47BCRUDServicesForAlarmEvent crudServicesProxy) {
		super(securityContextProvider,
			  modelObjectsMarshaller,
			  crudServicesProxy);
	}
}
