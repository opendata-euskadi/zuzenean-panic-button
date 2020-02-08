package pb01a.client.servicesproxy.rest;

import javax.inject.Inject;
import javax.inject.Singleton;

import pb01a.api.interfaces.PB01ASearchServicesForOrganizationalEntityObject;
import pb01a.model.search.PB01ASearchFilterForPanicButtonOrganizationalEntity;
import pb01a.model.search.PB01ASearchResultItemForPanicButtonOrganizationalEntity;
import r01f.model.annotations.ModelObjectsMarshaller;
import r01f.objectstreamer.Marshaller;
import r01f.services.client.servicesproxy.rest.RESTServicesForSearchProxyBase;
import r01f.xmlproperties.XMLPropertiesForAppComponent;
import r01f.xmlproperties.annotations.XMLPropertiesComponent;


@Singleton
public class PB01ARESTSearchServicesProxyForEntityModelObject 
	 extends RESTServicesForSearchProxyBase<PB01ASearchFilterForPanicButtonOrganizationalEntity,PB01ASearchResultItemForPanicButtonOrganizationalEntity> 
  implements PB01ASearchServicesForOrganizationalEntityObject,
  		     PB01APanicButtonRESTProxy {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public PB01ARESTSearchServicesProxyForEntityModelObject(@XMLPropertiesComponent("client") final XMLPropertiesForAppComponent clientProps,
												           @ModelObjectsMarshaller 			 final Marshaller marshaller) {
		super(marshaller,
			  // all entities are searched under the same rest resource module called (entities)
			  // ... no matter if they are organizations, locations or work places
			  new PB01ARESTServiceResourceUrlPathBuilderForModelObjectSearchIndexPersistence(clientProps));
	}
}
