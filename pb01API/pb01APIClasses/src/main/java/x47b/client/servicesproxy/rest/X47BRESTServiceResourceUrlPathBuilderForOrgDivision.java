package x47b.client.servicesproxy.rest;

import r01f.services.client.servicesproxy.rest.RESTServiceResourceUrlPathBuilders.RESTServiceEndPointUrl;
import r01f.types.url.UrlPath;
import r01f.xmlproperties.XMLPropertiesForAppComponent;
import x47b.client.servicesproxy.rest.X47BRESTServiceResourceUrlPathBuildersBases.X47BRESTServiceResourceUrlPathBuilderForEntityPersistenceBase;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrganizationOID;

  class X47BRESTServiceResourceUrlPathBuilderForOrgDivision
extends X47BRESTServiceResourceUrlPathBuilderForEntityPersistenceBase<X47BOrgDivisionOID,X47BOrgDivisionID> {
/////////////////////////////////////////////////////////////////////////////////////////
//  
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BRESTServiceResourceUrlPathBuilderForOrgDivision(final XMLPropertiesForAppComponent clientProps) {
		super(new RESTServiceEndPointUrl(clientProps,
										 "persistence"),
			  UrlPath.from("divisions"));
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  
/////////////////////////////////////////////////////////////////////////////////////////
	public UrlPath pathOfEntityListByOrganization(final X47BOrganizationOID orgOid) {
		return this.pathOfEntityList().joinedWith("byOrganization",orgOid);
	}
	public UrlPath pathOfSummariesByOrganization(final X47BOrganizationOID orgOid) {
		return this.pathOfEntityListByOrganization(orgOid).joinedWith("summarized");
	}
}