package x47b.client.servicesproxy.rest;

import javax.inject.Inject;
import javax.inject.Singleton;

import r01f.model.annotations.ModelObjectsMarshaller;
import r01f.objectstreamer.Marshaller;
import r01f.xmlproperties.XMLPropertiesForAppComponent;
import r01f.xmlproperties.annotations.XMLPropertiesComponent;
import x47b.api.interfaces.X47BCRUDServicesForWorkPlace;
import x47b.model.oids.X47BOrganizationalIDs.X47BWorkPlaceID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BWorkPlaceOID;
import x47b.model.org.X47BWorkPlace;


@Singleton
public class X47BRESTCRUDServicesProxyForWorkPlace
	 extends X47BRESTCRUDServicesProxyForOrganizationalEntityBase<X47BWorkPlaceOID,X47BWorkPlaceID,X47BWorkPlace>
  implements X47BCRUDServicesForWorkPlace {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public X47BRESTCRUDServicesProxyForWorkPlace(@XMLPropertiesComponent("client") final XMLPropertiesForAppComponent props,
											     @ModelObjectsMarshaller 		   final Marshaller marshaller) {
		super(marshaller,
			  X47BWorkPlace.class,
			  new X47BRESTServiceResourceUrlPathBuilderForWorkPlace(props));
	}
}
