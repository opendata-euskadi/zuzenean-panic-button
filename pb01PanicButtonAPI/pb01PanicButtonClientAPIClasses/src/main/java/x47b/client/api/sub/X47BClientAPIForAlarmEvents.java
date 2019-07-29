package x47b.client.api.sub;

import java.util.Map;

import javax.inject.Provider;

import lombok.Getter;
import lombok.experimental.Accessors;
import r01f.objectstreamer.Marshaller;
import r01f.securitycontext.SecurityContext;
import r01f.services.client.ClientSubAPIBase;
import r01f.services.interfaces.ServiceInterface;
import x47b.api.interfaces.X47BCRUDServicesForAlarmEvent;
import x47b.api.interfaces.X47BFindServicesForAlarmEvent;
import x47b.api.interfaces.X47BNotifyServicesForAlarmEvent;
import x47b.client.api.sub.delegates.X47BClientAPIDelegateForAlarmEventCRUDServices;
import x47b.client.api.sub.delegates.X47BClientAPIDelegateForAlarmEventFindServices;
import x47b.client.api.sub.delegates.X47BClientAPIDelegateForAlarmEventNotifyServices;

/**
 * Client implementation for the alarm events managing
 */
@Accessors(prefix="_")
public class X47BClientAPIForAlarmEvents
     extends ClientSubAPIBase {	
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	@Getter private X47BClientAPIDelegateForAlarmEventCRUDServices _forCRUD;
	@Getter private X47BClientAPIDelegateForAlarmEventFindServices _forFind;
	@Getter private X47BClientAPIDelegateForAlarmEventNotifyServices _forNotify;
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@SuppressWarnings("rawtypes")
	public X47BClientAPIForAlarmEvents(final Provider<SecurityContext> securityContextProvider,
									   final Marshaller modelObjectsMarshaller,
								  	   Map<Class,ServiceInterface> srvcIfaceMappings) {
		super(securityContextProvider,
			  modelObjectsMarshaller,
			  srvcIfaceMappings);
		_forCRUD = new X47BClientAPIDelegateForAlarmEventCRUDServices(securityContextProvider,
																	  modelObjectsMarshaller,
													 			 	  this.getServiceInterfaceCoreImplOrProxy(X47BCRUDServicesForAlarmEvent.class));
		_forFind = new X47BClientAPIDelegateForAlarmEventFindServices(securityContextProvider,
																	  modelObjectsMarshaller,
											 			 	  		  this.getServiceInterfaceCoreImplOrProxy(X47BFindServicesForAlarmEvent.class));
		_forNotify = new X47BClientAPIDelegateForAlarmEventNotifyServices(securityContextProvider,
																		  modelObjectsMarshaller,
															  		      this.getServiceInterfaceCoreImplOrProxy(X47BNotifyServicesForAlarmEvent.class));
	}
}
