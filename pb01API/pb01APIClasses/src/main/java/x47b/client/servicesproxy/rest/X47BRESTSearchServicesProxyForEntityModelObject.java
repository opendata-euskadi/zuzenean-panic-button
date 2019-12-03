package x47b.client.servicesproxy.rest;

import javax.inject.Inject;
import javax.inject.Singleton;

import r01f.model.annotations.ModelObjectsMarshaller;
import r01f.objectstreamer.Marshaller;
import r01f.services.client.servicesproxy.rest.RESTServicesForSearchProxyBase;
import r01f.xmlproperties.XMLPropertiesForAppComponent;
import r01f.xmlproperties.annotations.XMLPropertiesComponent;
import x47b.api.interfaces.X47BSearchServicesForOrganizationalEntityObject;
import x47b.model.search.X47BSearchFilterForPanicButtonOrganizationalEntity;
import x47b.model.search.X47BSearchResultItemForPanicButtonOrganizationalEntity;


@Singleton
public class X47BRESTSearchServicesProxyForEntityModelObject 
	 extends RESTServicesForSearchProxyBase<X47BSearchFilterForPanicButtonOrganizationalEntity,X47BSearchResultItemForPanicButtonOrganizationalEntity> 
  implements X47BSearchServicesForOrganizationalEntityObject,
  		     X47BPanicButtonRESTProxy {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public X47BRESTSearchServicesProxyForEntityModelObject(@XMLPropertiesComponent("client") final XMLPropertiesForAppComponent clientProps,
												           @ModelObjectsMarshaller 			 final Marshaller marshaller) {
		super(marshaller,
			  // all entities are searched under the same rest resource module called (entities)
			  // ... no matter if they are organizations, locations or work places
			  new X47BRESTServiceResourceUrlPathBuilderForModelObjectSearchIndexPersistence(clientProps));
	}
}
