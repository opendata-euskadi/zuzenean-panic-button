package pb01a.client.servicesproxy.rest;

import javax.inject.Inject;
import javax.inject.Singleton;

import pb01a.api.interfaces.PB01ACRUDServicesForOrgDivisionService;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionServiceID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionServiceOID;
import pb01a.model.org.PB01AOrgDivisionService;
import r01f.model.annotations.ModelObjectsMarshaller;
import r01f.objectstreamer.Marshaller;
import r01f.xmlproperties.XMLPropertiesForAppComponent;
import r01f.xmlproperties.annotations.XMLPropertiesComponent;


@Singleton
public class PB01ARESTCRUDServicesProxyForOrgDivsionService
	 extends PB01ARESTCRUDServicesProxyForOrganizationalEntityBase<PB01AOrgDivisionServiceOID,PB01AOrgDivisionServiceID,PB01AOrgDivisionService>
  implements PB01ACRUDServicesForOrgDivisionService {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public PB01ARESTCRUDServicesProxyForOrgDivsionService(@XMLPropertiesComponent("client") final XMLPropertiesForAppComponent clientProps,
												    	 @ModelObjectsMarshaller 		   final Marshaller marshaller) {
		super(marshaller,
			  PB01AOrgDivisionService.class,
			  new PB01ARESTServiceResourceUrlPathBuilderForOrgDivisionService(clientProps));
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////

}
