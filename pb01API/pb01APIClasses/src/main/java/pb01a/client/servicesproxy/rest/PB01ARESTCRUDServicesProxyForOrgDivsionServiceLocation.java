package pb01a.client.servicesproxy.rest;

import javax.inject.Inject;
import javax.inject.Singleton;

import pb01a.api.interfaces.PB01ACRUDServicesForOrgDivisionServiceLocation;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionServiceLocationID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionServiceLocationOID;
import pb01a.model.org.PB01AOrgDivisionServiceLocation;
import r01f.model.annotations.ModelObjectsMarshaller;
import r01f.objectstreamer.Marshaller;
import r01f.xmlproperties.XMLPropertiesForAppComponent;
import r01f.xmlproperties.annotations.XMLPropertiesComponent;


@Singleton
public class PB01ARESTCRUDServicesProxyForOrgDivsionServiceLocation
	 extends PB01ARESTCRUDServicesProxyForOrganizationalEntityBase<PB01AOrgDivisionServiceLocationOID,PB01AOrgDivisionServiceLocationID,PB01AOrgDivisionServiceLocation>
  implements PB01ACRUDServicesForOrgDivisionServiceLocation {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public PB01ARESTCRUDServicesProxyForOrgDivsionServiceLocation(@XMLPropertiesComponent("client") final XMLPropertiesForAppComponent clientProps,
												    			 @ModelObjectsMarshaller 		   final Marshaller marshaller) {
		super(marshaller,
			  PB01AOrgDivisionServiceLocation.class,
			  new PB01ARESTServiceResourceUrlPathBuilderForOrgDivisionServiceLocation(clientProps));
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////

}
