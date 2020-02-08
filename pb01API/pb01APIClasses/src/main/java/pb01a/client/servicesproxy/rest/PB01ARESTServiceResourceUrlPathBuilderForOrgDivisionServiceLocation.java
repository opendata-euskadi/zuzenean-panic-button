package pb01a.client.servicesproxy.rest;

import pb01a.client.servicesproxy.rest.PB01ARESTServiceResourceUrlPathBuildersBases.PB01ARESTServiceResourceUrlPathBuilderForEntityPersistenceBase;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionServiceLocationID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionServiceLocationOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionServiceOID;
import r01f.services.client.servicesproxy.rest.RESTServiceResourceUrlPathBuilders.RESTServiceEndPointUrl;
import r01f.types.url.UrlPath;
import r01f.xmlproperties.XMLPropertiesForAppComponent;

  class PB01ARESTServiceResourceUrlPathBuilderForOrgDivisionServiceLocation
extends PB01ARESTServiceResourceUrlPathBuilderForEntityPersistenceBase<PB01AOrgDivisionServiceLocationOID,PB01AOrgDivisionServiceLocationID> {
/////////////////////////////////////////////////////////////////////////////////////////
//  
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01ARESTServiceResourceUrlPathBuilderForOrgDivisionServiceLocation(final XMLPropertiesForAppComponent clientProps) {
		super(new RESTServiceEndPointUrl(clientProps,
										 "persistence"),
			  UrlPath.from("locations"));
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  
/////////////////////////////////////////////////////////////////////////////////////////
	public UrlPath pathOfEntityListByOrgDivisionService(final PB01AOrgDivisionServiceOID serviceOid) {
		return this.pathOfEntityList().joinedWith("byService",serviceOid);
	}
	public UrlPath pathOfSummariesByOrgDivisionService(final PB01AOrgDivisionServiceOID serviceOid) {
		return this.pathOfEntityListByOrgDivisionService(serviceOid).joinedWith("summarized");
	}
}