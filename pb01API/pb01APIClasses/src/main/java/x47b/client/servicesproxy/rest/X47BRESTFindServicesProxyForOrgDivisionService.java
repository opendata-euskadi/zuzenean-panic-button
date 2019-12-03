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
import r01f.xmlproperties.XMLPropertiesForAppComponent;
import r01f.xmlproperties.annotations.XMLPropertiesComponent;
import x47b.api.interfaces.X47BFindServicesForOrgDivisionService;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionServiceID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceOID;
import x47b.model.org.X47BOrgDivisionService;


@Singleton
public class X47BRESTFindServicesProxyForOrgDivisionService
	 extends X47BRESTFindServicesProxyForOrganizationalEntityBase<X47BOrgDivisionServiceOID,X47BOrgDivisionServiceID,X47BOrgDivisionService>
  implements X47BFindServicesForOrgDivisionService {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public X47BRESTFindServicesProxyForOrgDivisionService(@XMLPropertiesComponent("client") final XMLPropertiesForAppComponent clientProps,
												          @ModelObjectsMarshaller 			final Marshaller marshaller) {
		super(marshaller,
			  X47BOrgDivisionService.class,
			  new X47BRESTServiceResourceUrlPathBuilderForOrgDivisionService(clientProps));
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public FindResult<X47BOrgDivisionService> findByOrgDivision(final SecurityContext securityContext,
										   						final X47BOrgDivisionOID divisionOid) {
		Url restResourceUrl = this.composeURIFor(this.getServicesRESTResourceUrlPathBuilderAs(X47BRESTServiceResourceUrlPathBuilderForOrgDivisionService.class)
			  															  .pathOfEntityListByOrgDivision(divisionOid));
		return _findDelegate.doFindEntities(securityContext,
											restResourceUrl);
	}
	@Override
	public FindSummariesResult<X47BOrgDivisionService> findSummariesByOrgDivision(final SecurityContext securityContext,
																				  final X47BOrgDivisionOID divisionOid,
																				  final Language lang) {
		Url restResourceUrl = this.composeURIFor(this.getServicesRESTResourceUrlPathBuilderAs(X47BRESTServiceResourceUrlPathBuilderForOrgDivisionService.class)
			  											  .pathOfSummariesByOrgDivision(divisionOid),
			  									 UrlQueryString.fromParams(UrlQueryStringParam.of(lang)));
		return _findDelegate.doFindSummaries(securityContext,
											 restResourceUrl);
	}

}
