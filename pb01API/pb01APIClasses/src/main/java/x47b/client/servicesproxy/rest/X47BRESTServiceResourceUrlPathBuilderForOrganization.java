package x47b.client.servicesproxy.rest;

import r01f.services.client.servicesproxy.rest.RESTServiceResourceUrlPathBuilders.RESTServiceEndPointUrl;
import r01f.types.url.UrlPath;
import r01f.xmlproperties.XMLPropertiesForAppComponent;
import x47b.client.servicesproxy.rest.X47BRESTServiceResourceUrlPathBuildersBases.X47BRESTServiceResourceUrlPathBuilderForEntityPersistenceBase;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrganizationID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrganizationOID;

  class X47BRESTServiceResourceUrlPathBuilderForOrganization
extends X47BRESTServiceResourceUrlPathBuilderForEntityPersistenceBase<X47BOrganizationOID,X47BOrganizationID> {
/////////////////////////////////////////////////////////////////////////////////////////
//  
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BRESTServiceResourceUrlPathBuilderForOrganization(final XMLPropertiesForAppComponent clientProps) {
		super(new RESTServiceEndPointUrl(clientProps,
										 "persistence"),
			  UrlPath.from("organizations"));
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  
/////////////////////////////////////////////////////////////////////////////////////////
	public UrlPath pathOfSummaries() {
		return this.pathOfEntityList().joinedWith("summarized");
	}
}