package pb01a.client.api.sub;

import java.util.Map;

import javax.inject.Provider;

import lombok.Getter;
import lombok.experimental.Accessors;
import pb01a.api.interfaces.PB01ACRUDServicesForAlarmEvent;
import pb01a.api.interfaces.PB01AFindServicesForAlarmEvent;
import pb01a.api.interfaces.PB01ANotifyServicesForAlarmEvent;
import pb01a.client.api.sub.delegates.PB01AClientAPIDelegateForAlarmEventCRUDServices;
import pb01a.client.api.sub.delegates.PB01AClientAPIDelegateForAlarmEventFindServices;
import pb01a.client.api.sub.delegates.PB01AClientAPIDelegateForAlarmEventNotifyServices;
import r01f.objectstreamer.Marshaller;
import r01f.securitycontext.SecurityContext;
import r01f.services.client.ClientSubAPIBase;
import r01f.services.interfaces.ServiceInterface;

/**
 * Client implementation for the alarm events managing
 */
@Accessors(prefix="_")
public class PB01AClientAPIForAlarmEvents
     extends ClientSubAPIBase {	
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	@Getter private PB01AClientAPIDelegateForAlarmEventCRUDServices _forCRUD;
	@Getter private PB01AClientAPIDelegateForAlarmEventFindServices _forFind;
	@Getter private PB01AClientAPIDelegateForAlarmEventNotifyServices _forNotify;
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@SuppressWarnings("rawtypes")
	public PB01AClientAPIForAlarmEvents(final Provider<SecurityContext> securityContextProvider,
									   final Marshaller modelObjectsMarshaller,
								  	   Map<Class,ServiceInterface> srvcIfaceMappings) {
		super(securityContextProvider,
			  modelObjectsMarshaller,
			  srvcIfaceMappings);
		_forCRUD = new PB01AClientAPIDelegateForAlarmEventCRUDServices(securityContextProvider,
																	  modelObjectsMarshaller,
													 			 	  this.getServiceInterfaceCoreImplOrProxy(PB01ACRUDServicesForAlarmEvent.class));
		_forFind = new PB01AClientAPIDelegateForAlarmEventFindServices(securityContextProvider,
																	  modelObjectsMarshaller,
											 			 	  		  this.getServiceInterfaceCoreImplOrProxy(PB01AFindServicesForAlarmEvent.class));
		_forNotify = new PB01AClientAPIDelegateForAlarmEventNotifyServices(securityContextProvider,
																		  modelObjectsMarshaller,
															  		      this.getServiceInterfaceCoreImplOrProxy(PB01ANotifyServicesForAlarmEvent.class));
	}
}
