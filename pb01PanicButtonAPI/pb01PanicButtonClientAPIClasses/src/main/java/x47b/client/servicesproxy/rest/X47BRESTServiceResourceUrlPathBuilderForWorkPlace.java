package x47b.client.servicesproxy.rest;

import r01f.services.client.servicesproxy.rest.RESTServiceResourceUrlPathBuilders.RESTServiceEndPointUrl;
import r01f.types.url.UrlPath;
import r01f.xmlproperties.XMLPropertiesForAppComponent;
import x47b.client.servicesproxy.rest.X47BRESTServiceResourceUrlPathBuildersBases.X47BRESTServiceResourceUrlPathBuilderForEntityPersistenceBase;
import x47b.model.oids.X47BOrganizationalIDs.X47BWorkPlaceID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceLocationOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BWorkPlaceOID;

  class X47BRESTServiceResourceUrlPathBuilderForWorkPlace
extends X47BRESTServiceResourceUrlPathBuilderForEntityPersistenceBase<X47BWorkPlaceOID,X47BWorkPlaceID> {
/////////////////////////////////////////////////////////////////////////////////////////
//  
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BRESTServiceResourceUrlPathBuilderForWorkPlace(final XMLPropertiesForAppComponent clientProps) {
		super(new RESTServiceEndPointUrl(clientProps,
										 "persistence"),
			  UrlPath.from("workPlaces"));
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  
/////////////////////////////////////////////////////////////////////////////////////////
	public UrlPath pathOfEntityListByOrgDivisionServiceLocation(final X47BOrgDivisionServiceLocationOID locationOid) {
		return this.pathOfEntityList().joinedWith("byLocation",locationOid);
	}
	public UrlPath pathOfSummariesByOrgDivisionServiceLocation(final X47BOrgDivisionServiceLocationOID locationOid) {
		return this.pathOfEntityListByOrgDivisionServiceLocation(locationOid).joinedWith("summarized");
	}
}