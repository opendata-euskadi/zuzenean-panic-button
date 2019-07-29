package x47b.client.servicesproxy.rest;

import javax.inject.Inject;
import javax.inject.Singleton;

import r01f.locale.Language;
import r01f.model.annotations.ModelObjectsMarshaller;
import r01f.model.persistence.FindResult;
import r01f.model.persistence.FindSummariesResult;
import r01f.objectstreamer.Marshaller;
import r01f.securitycontext.SecurityContext;
import r01f.types.url.Url;
import r01f.types.url.UrlQueryString;
import r01f.types.url.UrlQueryStringParam;
import r01f.xmlproperties.XMLPropertiesComponent;
import r01f.xmlproperties.XMLPropertiesForAppComponent;
import x47b.api.interfaces.X47BFindServicesForOrgDivisionServiceLocation;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionServiceLocationID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceLocationOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceOID;
import x47b.model.org.X47BOrgDivisionServiceLocation;


@Singleton
public class X47BRESTFindServicesProxyForOrgDivisionServiceLocation
	 extends X47BRESTFindServicesProxyForOrganizationalEntityBase<X47BOrgDivisionServiceLocationOID,X47BOrgDivisionServiceLocationID,X47BOrgDivisionServiceLocation>
  implements X47BFindServicesForOrgDivisionServiceLocation {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public X47BRESTFindServicesProxyForOrgDivisionServiceLocation(@XMLPropertiesComponent("client") final XMLPropertiesForAppComponent clientProps,
												    			  @ModelObjectsMarshaller 			final Marshaller marshaller) {
		super(marshaller,
			  X47BOrgDivisionServiceLocation.class,
			  new X47BRESTServiceResourceUrlPathBuilderForOrgDivisionServiceLocation(clientProps));
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public FindResult<X47BOrgDivisionServiceLocation> findByOrgDivisionService(final SecurityContext securityContext,
										   									   final X47BOrgDivisionServiceOID serviceOid) {
		Url restResourceUrl = this.composeURIFor(this.getServicesRESTResourceUrlPathBuilderAs(X47BRESTServiceResourceUrlPathBuilderForOrgDivisionServiceLocation.class)
			  															  .pathOfEntityListByOrgDivisionService(serviceOid));
		return _findDelegate.doFindEntities(securityContext,
											restResourceUrl);
	}
	@Override
	public FindSummariesResult<X47BOrgDivisionServiceLocation> findSummariesByOrgDivisionService(final SecurityContext securityContext,
																				  				 final X47BOrgDivisionServiceOID serviceOid,
																				  				 final Language lang) {
		Url restResourceUrl = this.composeURIFor(this.getServicesRESTResourceUrlPathBuilderAs(X47BRESTServiceResourceUrlPathBuilderForOrgDivisionServiceLocation.class)
			  												  .pathOfSummariesByOrgDivisionService(serviceOid),
			  									 UrlQueryString.fromParams(UrlQueryStringParam.of(lang)));
		return _findDelegate.doFindSummaries(securityContext,
											 restResourceUrl);
	}

}
