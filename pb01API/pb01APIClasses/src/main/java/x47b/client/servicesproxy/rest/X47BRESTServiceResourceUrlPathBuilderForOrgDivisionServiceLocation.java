package x47b.client.servicesproxy.rest;

import r01f.services.client.servicesproxy.rest.RESTServiceResourceUrlPathBuilders.RESTServiceEndPointUrl;
import r01f.types.url.UrlPath;
import r01f.xmlproperties.XMLPropertiesForAppComponent;
import x47b.client.servicesproxy.rest.X47BRESTServiceResourceUrlPathBuildersBases.X47BRESTServiceResourceUrlPathBuilderForEntityPersistenceBase;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionServiceLocationID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceLocationOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceOID;

  class X47BRESTServiceResourceUrlPathBuilderForOrgDivisionServiceLocation
extends X47BRESTServiceResourceUrlPathBuilderForEntityPersistenceBase<X47BOrgDivisionServiceLocationOID,X47BOrgDivisionServiceLocationID> {
/////////////////////////////////////////////////////////////////////////////////////////
//  
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BRESTServiceResourceUrlPathBuilderForOrgDivisionServiceLocation(final XMLPropertiesForAppComponent clientProps) {
		super(new RESTServiceEndPointUrl(clientProps,
										 "persistence"),
			  UrlPath.from("locations"));
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  
/////////////////////////////////////////////////////////////////////////////////////////
	public UrlPath pathOfEntityListByOrgDivisionService(final X47BOrgDivisionServiceOID serviceOid) {
		return this.pathOfEntityList().joinedWith("byService",serviceOid);
	}
	public UrlPath pathOfSummariesByOrgDivisionService(final X47BOrgDivisionServiceOID serviceOid) {
		return this.pathOfEntityListByOrgDivisionService(serviceOid).joinedWith("summarized");
	}
}