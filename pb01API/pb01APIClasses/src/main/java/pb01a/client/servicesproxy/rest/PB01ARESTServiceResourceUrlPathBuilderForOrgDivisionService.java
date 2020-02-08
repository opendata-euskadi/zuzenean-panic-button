package pb01a.client.servicesproxy.rest;

import pb01a.client.servicesproxy.rest.PB01ARESTServiceResourceUrlPathBuildersBases.PB01ARESTServiceResourceUrlPathBuilderForEntityPersistenceBase;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionServiceID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionServiceOID;
import r01f.services.client.servicesproxy.rest.RESTServiceResourceUrlPathBuilders.RESTServiceEndPointUrl;
import r01f.types.url.UrlPath;
import r01f.xmlproperties.XMLPropertiesForAppComponent;

  class PB01ARESTServiceResourceUrlPathBuilderForOrgDivisionService
extends PB01ARESTServiceResourceUrlPathBuilderForEntityPersistenceBase<PB01AOrgDivisionServiceOID,PB01AOrgDivisionServiceID> {
/////////////////////////////////////////////////////////////////////////////////////////
//  
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01ARESTServiceResourceUrlPathBuilderForOrgDivisionService(final XMLPropertiesForAppComponent clientProps) {
		super(new RESTServiceEndPointUrl(clientProps,
										 "persistence"),
			  UrlPath.from("services"));
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  
/////////////////////////////////////////////////////////////////////////////////////////
	public UrlPath pathOfEntityListByOrgDivision(final PB01AOrgDivisionOID divisionOid) {
		return this.pathOfEntityList().joinedWith("byDivision",divisionOid);
	}
	public UrlPath pathOfSummariesByOrgDivision(final PB01AOrgDivisionOID divisionOid) {
		return this.pathOfEntityListByOrgDivision(divisionOid).joinedWith("summarized");
	}
}