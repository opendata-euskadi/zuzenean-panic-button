package pb01a.client.servicesproxy.rest;

import javax.inject.Inject;
import javax.inject.Singleton;

import pb01a.api.interfaces.PB01ACRUDServicesForWorkPlace;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AWorkPlaceID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AWorkPlaceOID;
import pb01a.model.org.PB01AWorkPlace;
import r01f.model.annotations.ModelObjectsMarshaller;
import r01f.objectstreamer.Marshaller;
import r01f.xmlproperties.XMLPropertiesForAppComponent;
import r01f.xmlproperties.annotations.XMLPropertiesComponent;


@Singleton
public class PB01ARESTCRUDServicesProxyForWorkPlace
	 extends PB01ARESTCRUDServicesProxyForOrganizationalEntityBase<PB01AWorkPlaceOID,PB01AWorkPlaceID,PB01AWorkPlace>
  implements PB01ACRUDServicesForWorkPlace {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public PB01ARESTCRUDServicesProxyForWorkPlace(@XMLPropertiesComponent("client") final XMLPropertiesForAppComponent props,
											     @ModelObjectsMarshaller 		   final Marshaller marshaller) {
		super(marshaller,
			  PB01AWorkPlace.class,
			  new PB01ARESTServiceResourceUrlPathBuilderForWorkPlace(props));
	}
}
