package x47b.client.servicesproxy.rest;

import javax.inject.Inject;
import javax.inject.Singleton;

import r01f.model.annotations.ModelObjectsMarshaller;
import r01f.objectstreamer.Marshaller;
import r01f.xmlproperties.XMLPropertiesComponent;
import r01f.xmlproperties.XMLPropertiesForAppComponent;
import x47b.api.interfaces.X47BCRUDServicesForOrganization;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrganizationID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrganizationOID;
import x47b.model.org.X47BOrganization;


@Singleton
public class X47BRESTCRUDServicesProxyForOrganization
	 extends X47BRESTCRUDServicesProxyForOrganizationalEntityBase<X47BOrganizationOID,X47BOrganizationID,X47BOrganization>
  implements X47BCRUDServicesForOrganization {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public X47BRESTCRUDServicesProxyForOrganization(@XMLPropertiesComponent("client") final XMLPropertiesForAppComponent clientProps,
												    @ModelObjectsMarshaller 		  final Marshaller marshaller) {
		super(marshaller,
			  X47BOrganization.class,
			  new X47BRESTServiceResourceUrlPathBuilderForOrganization(clientProps));
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
}
