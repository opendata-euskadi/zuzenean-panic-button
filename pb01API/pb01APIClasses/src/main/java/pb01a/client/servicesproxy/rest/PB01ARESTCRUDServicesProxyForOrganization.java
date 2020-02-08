package pb01a.client.servicesproxy.rest;

import javax.inject.Inject;
import javax.inject.Singleton;

import pb01a.api.interfaces.PB01ACRUDServicesForOrganization;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrganizationID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrganizationOID;
import pb01a.model.org.PB01AOrganization;
import r01f.model.annotations.ModelObjectsMarshaller;
import r01f.objectstreamer.Marshaller;
import r01f.xmlproperties.XMLPropertiesForAppComponent;
import r01f.xmlproperties.annotations.XMLPropertiesComponent;


@Singleton
public class PB01ARESTCRUDServicesProxyForOrganization
	 extends PB01ARESTCRUDServicesProxyForOrganizationalEntityBase<PB01AOrganizationOID,PB01AOrganizationID,PB01AOrganization>
  implements PB01ACRUDServicesForOrganization {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public PB01ARESTCRUDServicesProxyForOrganization(@XMLPropertiesComponent("client") final XMLPropertiesForAppComponent clientProps,
												    @ModelObjectsMarshaller 		  final Marshaller marshaller) {
		super(marshaller,
			  PB01AOrganization.class,
			  new PB01ARESTServiceResourceUrlPathBuilderForOrganization(clientProps));
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
}
