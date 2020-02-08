package pb01a.client.servicesproxy.rest;

import pb01a.client.servicesproxy.rest.PB01ARESTServiceResourceUrlPathBuildersBases.PB01ARESTServiceResourceUrlPathBuilderForPersistenceBase;
import pb01a.model.oids.PB01AOIDs.PB01APersistableObjectOID;
import r01f.services.client.servicesproxy.rest.RESTServiceResourceUrlPathBuilders.RESTServiceEndPointUrl;
import r01f.types.url.UrlPath;
import r01f.xmlproperties.XMLPropertiesForAppComponent;

/**
 * Search index persistence
 */
class PB01ARESTServiceResourceUrlPathBuilderForModelObjectSearchIndexPersistence
	 extends PB01ARESTServiceResourceUrlPathBuilderForPersistenceBase<PB01APersistableObjectOID> {
/////////////////////////////////////////////////////////////////////////////////////////
//  
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01ARESTServiceResourceUrlPathBuilderForModelObjectSearchIndexPersistence(final XMLPropertiesForAppComponent clientProps) {
		super(new RESTServiceEndPointUrl(clientProps,
										 "panicButton"),
			  UrlPath.from("index"));
	}
}