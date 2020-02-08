package pb01a.client.servicesproxy.rest;

import javax.inject.Inject;
import javax.inject.Singleton;

import pb01a.api.interfaces.PB01ACRUDServicesForAlarmEvent;
import pb01a.model.PB01AAlarmEvent;
import pb01a.model.oids.PB01APanicButtonOIDs.PB01AAlarmEventOID;
import r01f.model.annotations.ModelObjectsMarshaller;
import r01f.objectstreamer.Marshaller;
import r01f.services.client.servicesproxy.rest.RESTServicesForDBCRUDProxyBase;
import r01f.xmlproperties.XMLPropertiesForAppComponent;
import r01f.xmlproperties.annotations.XMLPropertiesComponent;


@Singleton
public class PB01ARESTCRUDServicesProxyForAlarmEvent
	 extends RESTServicesForDBCRUDProxyBase<PB01AAlarmEventOID,PB01AAlarmEvent>
  implements PB01ACRUDServicesForAlarmEvent,
  			 PB01APanicButtonRESTProxy {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public PB01ARESTCRUDServicesProxyForAlarmEvent(@XMLPropertiesComponent("client") final XMLPropertiesForAppComponent clientProps,
												  @ModelObjectsMarshaller 		    final Marshaller marshaller) {
		super(marshaller,
			  PB01AAlarmEvent.class,
			  new PB01ARESTServiceResourceUrlPathBuilderForAlarmEvent(clientProps));
	}
}
