package pb01a.client.servicesproxy.rest;

import javax.inject.Inject;
import javax.inject.Singleton;

import pb01a.api.interfaces.PB01AFindServicesForOrgDivisionServiceLocation;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionServiceLocationID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionServiceLocationOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionServiceOID;
import pb01a.model.org.PB01AOrgDivisionServiceLocation;
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
public class PB01ARESTFindServicesProxyForOrgDivisionServiceLocation
	 extends PB01ARESTFindServicesProxyForOrganizationalEntityBase<PB01AOrgDivisionServiceLocationOID,PB01AOrgDivisionServiceLocationID,PB01AOrgDivisionServiceLocation>
  implements PB01AFindServicesForOrgDivisionServiceLocation {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public PB01ARESTFindServicesProxyForOrgDivisionServiceLocation(@XMLPropertiesComponent("client") final XMLPropertiesForAppComponent clientProps,
												    			  @ModelObjectsMarshaller 			final Marshaller marshaller) {
		super(marshaller,
			  PB01AOrgDivisionServiceLocation.class,
			  new PB01ARESTServiceResourceUrlPathBuilderForOrgDivisionServiceLocation(clientProps));
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public FindResult<PB01AOrgDivisionServiceLocation> findByOrgDivisionService(final SecurityContext securityContext,
										   									   final PB01AOrgDivisionServiceOID serviceOid) {
		Url restResourceUrl = this.composeURIFor(this.getServicesRESTResourceUrlPathBuilderAs(PB01ARESTServiceResourceUrlPathBuilderForOrgDivisionServiceLocation.class)
			  															  .pathOfEntityListByOrgDivisionService(serviceOid));
		return _findDelegate.doFindEntities(securityContext,
											restResourceUrl);
	}
	@Override
	public FindSummariesResult<PB01AOrgDivisionServiceLocation> findSummariesByOrgDivisionService(final SecurityContext securityContext,
																				  				 final PB01AOrgDivisionServiceOID serviceOid,
																				  				 final Language lang) {
		Url restResourceUrl = this.composeURIFor(this.getServicesRESTResourceUrlPathBuilderAs(PB01ARESTServiceResourceUrlPathBuilderForOrgDivisionServiceLocation.class)
			  												  .pathOfSummariesByOrgDivisionService(serviceOid),
			  									 UrlQueryString.fromParams(UrlQueryStringParam.of(lang)));
		return _findDelegate.doFindSummaries(securityContext,
											 restResourceUrl);
	}

}
