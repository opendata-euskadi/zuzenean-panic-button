package x47b.client.servicesproxy.rest;

import r01f.services.client.servicesproxy.rest.RESTServiceResourceUrlPathBuilders.RESTServiceEndPointUrl;
import r01f.types.url.UrlPath;
import r01f.xmlproperties.XMLPropertiesForAppComponent;
import x47b.client.servicesproxy.rest.X47BRESTServiceResourceUrlPathBuildersBases.X47BRESTServiceResourceUrlPathBuilderForEntityPersistenceBase;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionServiceID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceOID;

  class X47BRESTServiceResourceUrlPathBuilderForOrgDivisionService
extends X47BRESTServiceResourceUrlPathBuilderForEntityPersistenceBase<X47BOrgDivisionServiceOID,X47BOrgDivisionServiceID> {
/////////////////////////////////////////////////////////////////////////////////////////
//  
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BRESTServiceResourceUrlPathBuilderForOrgDivisionService(final XMLPropertiesForAppComponent clientProps) {
		super(new RESTServiceEndPointUrl(clientProps,
										 "persistence"),
			  UrlPath.from("services"));
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  
/////////////////////////////////////////////////////////////////////////////////////////
	public UrlPath pathOfEntityListByOrgDivision(final X47BOrgDivisionOID divisionOid) {
		return this.pathOfEntityList().joinedWith("byDivision",divisionOid);
	}
	public UrlPath pathOfSummariesByOrgDivision(final X47BOrgDivisionOID divisionOid) {
		return this.pathOfEntityListByOrgDivision(divisionOid).joinedWith("summarized");
	}
}