package x47b.client.servicesproxy.rest;

import javax.inject.Inject;
import javax.inject.Singleton;

import r01f.model.annotations.ModelObjectsMarshaller;
import r01f.objectstreamer.Marshaller;
import r01f.xmlproperties.XMLPropertiesForAppComponent;
import r01f.xmlproperties.annotations.XMLPropertiesComponent;
import x47b.api.interfaces.X47BCRUDServicesForOrgDivisionServiceLocation;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionServiceLocationID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceLocationOID;
import x47b.model.org.X47BOrgDivisionServiceLocation;


@Singleton
public class X47BRESTCRUDServicesProxyForOrgDivsionServiceLocation
	 extends X47BRESTCRUDServicesProxyForOrganizationalEntityBase<X47BOrgDivisionServiceLocationOID,X47BOrgDivisionServiceLocationID,X47BOrgDivisionServiceLocation>
  implements X47BCRUDServicesForOrgDivisionServiceLocation {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public X47BRESTCRUDServicesProxyForOrgDivsionServiceLocation(@XMLPropertiesComponent("client") final XMLPropertiesForAppComponent clientProps,
												    			 @ModelObjectsMarshaller 		   final Marshaller marshaller) {
		super(marshaller,
			  X47BOrgDivisionServiceLocation.class,
			  new X47BRESTServiceResourceUrlPathBuilderForOrgDivisionServiceLocation(clientProps));
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////

}
