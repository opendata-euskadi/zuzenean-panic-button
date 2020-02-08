package pb01a.client.servicesproxy.rest;

import pb01a.client.servicesproxy.rest.PB01ARESTServiceResourceUrlPathBuildersBases.PB01ARESTServiceResourceUrlPathBuilderForEntityPersistenceBase;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrganizationOID;
import r01f.services.client.servicesproxy.rest.RESTServiceResourceUrlPathBuilders.RESTServiceEndPointUrl;
import r01f.types.url.UrlPath;
import r01f.xmlproperties.XMLPropertiesForAppComponent;

  class PB01ARESTServiceResourceUrlPathBuilderForOrgDivision
extends PB01ARESTServiceResourceUrlPathBuilderForEntityPersistenceBase<PB01AOrgDivisionOID,PB01AOrgDivisionID> {
/////////////////////////////////////////////////////////////////////////////////////////
//  
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01ARESTServiceResourceUrlPathBuilderForOrgDivision(final XMLPropertiesForAppComponent clientProps) {
		super(new RESTServiceEndPointUrl(clientProps,
										 "persistence"),
			  UrlPath.from("divisions"));
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  
/////////////////////////////////////////////////////////////////////////////////////////
	public UrlPath pathOfEntityListByOrganization(final PB01AOrganizationOID orgOid) {
		return this.pathOfEntityList().joinedWith("byOrganization",orgOid);
	}
	public UrlPath pathOfSummariesByOrganization(final PB01AOrganizationOID orgOid) {
		return this.pathOfEntityListByOrganization(orgOid).joinedWith("summarized");
	}
}