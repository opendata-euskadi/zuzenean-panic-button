package x47b.client.servicesproxy.rest;

import r01f.services.client.servicesproxy.rest.RESTServiceResourceUrlPathBuilders.RESTServiceEndPointUrl;
import r01f.types.url.UrlPath;
import r01f.xmlproperties.XMLPropertiesForAppComponent;
import x47b.client.servicesproxy.rest.X47BRESTServiceResourceUrlPathBuildersBases.X47BRESTServiceResourceUrlPathBuilderForPersistenceBase;
import x47b.model.oids.X47BOIDs.X47BPersistableObjectOID;

/**
 * Search index persistence
 */
class X47BRESTServiceResourceUrlPathBuilderForModelObjectSearchIndexPersistence
	 extends X47BRESTServiceResourceUrlPathBuilderForPersistenceBase<X47BPersistableObjectOID> {
/////////////////////////////////////////////////////////////////////////////////////////
//  
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BRESTServiceResourceUrlPathBuilderForModelObjectSearchIndexPersistence(final XMLPropertiesForAppComponent clientProps) {
		super(new RESTServiceEndPointUrl(clientProps,
										 "panicButton"),
			  UrlPath.from("index"));
	}
}