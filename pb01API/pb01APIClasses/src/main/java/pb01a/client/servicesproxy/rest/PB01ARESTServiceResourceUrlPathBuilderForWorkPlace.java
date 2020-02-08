package pb01a.client.servicesproxy.rest;

import pb01a.client.servicesproxy.rest.PB01ARESTServiceResourceUrlPathBuildersBases.PB01ARESTServiceResourceUrlPathBuilderForEntityPersistenceBase;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AWorkPlaceID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionServiceLocationOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AWorkPlaceOID;
import r01f.services.client.servicesproxy.rest.RESTServiceResourceUrlPathBuilders.RESTServiceEndPointUrl;
import r01f.types.url.UrlPath;
import r01f.xmlproperties.XMLPropertiesForAppComponent;

  class PB01ARESTServiceResourceUrlPathBuilderForWorkPlace
extends PB01ARESTServiceResourceUrlPathBuilderForEntityPersistenceBase<PB01AWorkPlaceOID,PB01AWorkPlaceID> {
/////////////////////////////////////////////////////////////////////////////////////////
//  
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01ARESTServiceResourceUrlPathBuilderForWorkPlace(final XMLPropertiesForAppComponent clientProps) {
		super(new RESTServiceEndPointUrl(clientProps,
										 "persistence"),
			  UrlPath.from("workPlaces"));
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  
/////////////////////////////////////////////////////////////////////////////////////////
	public UrlPath pathOfEntityListByOrgDivisionServiceLocation(final PB01AOrgDivisionServiceLocationOID locationOid) {
		return this.pathOfEntityList().joinedWith("byLocation",locationOid);
	}
	public UrlPath pathOfSummariesByOrgDivisionServiceLocation(final PB01AOrgDivisionServiceLocationOID locationOid) {
		return this.pathOfEntityListByOrgDivisionServiceLocation(locationOid).joinedWith("summarized");
	}
}