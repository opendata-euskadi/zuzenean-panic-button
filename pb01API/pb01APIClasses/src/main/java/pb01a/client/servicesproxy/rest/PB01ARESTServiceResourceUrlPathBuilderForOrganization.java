package pb01a.client.servicesproxy.rest;

import pb01a.client.servicesproxy.rest.PB01ARESTServiceResourceUrlPathBuildersBases.PB01ARESTServiceResourceUrlPathBuilderForEntityPersistenceBase;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrganizationID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrganizationOID;
import r01f.services.client.servicesproxy.rest.RESTServiceResourceUrlPathBuilders.RESTServiceEndPointUrl;
import r01f.types.url.UrlPath;
import r01f.xmlproperties.XMLPropertiesForAppComponent;

  class PB01ARESTServiceResourceUrlPathBuilderForOrganization
extends PB01ARESTServiceResourceUrlPathBuilderForEntityPersistenceBase<PB01AOrganizationOID,PB01AOrganizationID> {
/////////////////////////////////////////////////////////////////////////////////////////
//  
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01ARESTServiceResourceUrlPathBuilderForOrganization(final XMLPropertiesForAppComponent clientProps) {
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