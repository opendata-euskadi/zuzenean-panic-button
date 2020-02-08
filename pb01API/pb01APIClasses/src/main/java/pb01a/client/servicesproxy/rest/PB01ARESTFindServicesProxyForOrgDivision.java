package pb01a.client.servicesproxy.rest;

import javax.inject.Inject;
import javax.inject.Singleton;

import pb01a.api.interfaces.PB01AFindServicesForOrgDivision;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrganizationOID;
import pb01a.model.org.PB01AOrgDivision;
import r01f.locale.Language;
import r01f.model.annotations.ModelObjectsMarshaller;
import r01f.model.persistence.FindResult;
import r01f.model.persistence.FindSummariesResult;
import r01f.objectstreamer.Marshaller;
import r01f.securitycontext.SecurityContext;
import r01f.types.url.Url;
import r01f.types.url.UrlQueryString;
import r01f.types.url.UrlQueryStringParam;
import r01f.xmlproperties.XMLPropertiesForAppComponent;
import r01f.xmlproperties.annotations.XMLPropertiesComponent;


@Singleton
public class PB01ARESTFindServicesProxyForOrgDivision
	 extends PB01ARESTFindServicesProxyForOrganizationalEntityBase<PB01AOrgDivisionOID,PB01AOrgDivisionID,PB01AOrgDivision>
  implements PB01AFindServicesForOrgDivision {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public PB01ARESTFindServicesProxyForOrgDivision(@XMLPropertiesComponent("client") final XMLPropertiesForAppComponent clientProps,
												   @ModelObjectsMarshaller 		     final Marshaller marshaller) {
		super(marshaller,
			  PB01AOrgDivision.class,
			  new PB01ARESTServiceResourceUrlPathBuilderForOrgDivision(clientProps));
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public FindResult<PB01AOrgDivision> findByOrganization(final SecurityContext securityContext,
											  		   	  final PB01AOrganizationOID orgOid) {
		Url restResourceUrl = this.composeURIFor(this.getServicesRESTResourceUrlPathBuilderAs(PB01ARESTServiceResourceUrlPathBuilderForOrgDivision.class)
			  															  .pathOfEntityListByOrganization(orgOid));
		return _findDelegate.doFindEntities(securityContext,
											restResourceUrl);
	}
	@Override
	public FindSummariesResult<PB01AOrgDivision> findSummariesByOrganization(final SecurityContext securityContext,
																		 	final PB01AOrganizationOID orgOid,
																		 	final Language lang) {
		Url restResourceUrl = this.composeURIFor(this.getServicesRESTResourceUrlPathBuilderAs(PB01ARESTServiceResourceUrlPathBuilderForOrgDivision.class)
			  													  .pathOfSummariesByOrganization(orgOid),
			  									 UrlQueryString.fromParams(UrlQueryStringParam.of(lang)));
		return _findDelegate.doFindSummaries(securityContext,
											 restResourceUrl);
	}

}
