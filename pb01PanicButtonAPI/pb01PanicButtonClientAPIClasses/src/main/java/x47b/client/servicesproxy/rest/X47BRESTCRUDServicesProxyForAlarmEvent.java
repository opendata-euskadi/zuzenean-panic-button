package x47b.client.servicesproxy.rest;

import javax.inject.Inject;
import javax.inject.Singleton;

import r01f.model.annotations.ModelObjectsMarshaller;
import r01f.objectstreamer.Marshaller;
import r01f.services.client.servicesproxy.rest.RESTServicesForDBCRUDProxyBase;
import r01f.xmlproperties.XMLPropertiesComponent;
import r01f.xmlproperties.XMLPropertiesForAppComponent;
import x47b.api.interfaces.X47BCRUDServicesForAlarmEvent;
import x47b.model.X47BAlarmEvent;
import x47b.model.oids.X47BPanicButtonOIDs.X47BAlarmEventOID;


@Singleton
public class X47BRESTCRUDServicesProxyForAlarmEvent
	 extends RESTServicesForDBCRUDProxyBase<X47BAlarmEventOID,X47BAlarmEvent>
  implements X47BCRUDServicesForAlarmEvent,
  			 X47BPanicButtonRESTProxy {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public X47BRESTCRUDServicesProxyForAlarmEvent(@XMLPropertiesComponent("client") final XMLPropertiesForAppComponent clientProps,
												  @ModelObjectsMarshaller 		    final Marshaller marshaller) {
		super(marshaller,
			  X47BAlarmEvent.class,
			  new X47BRESTServiceResourceUrlPathBuilderForAlarmEvent(clientProps));
	}
}
