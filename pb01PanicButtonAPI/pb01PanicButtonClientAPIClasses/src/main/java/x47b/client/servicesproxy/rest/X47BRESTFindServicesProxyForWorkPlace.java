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
import x47b.api.interfaces.X47BFindServicesForWorkPlace;
import x47b.model.oids.X47BOrganizationalIDs.X47BWorkPlaceID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceLocationOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BWorkPlaceOID;
import x47b.model.org.X47BWorkPlace;


@Singleton
public class X47BRESTFindServicesProxyForWorkPlace
	 extends X47BRESTFindServicesProxyBaseForModelObject<X47BWorkPlaceOID,X47BWorkPlaceID,X47BWorkPlace>
  implements X47BFindServicesForWorkPlace {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public X47BRESTFindServicesProxyForWorkPlace(@XMLPropertiesComponent("client") final XMLPropertiesForAppComponent clientProps,
										     @ModelObjectsMarshaller 		   final Marshaller marshaller) {
		super(marshaller,
			  X47BWorkPlace.class,
			  new X47BRESTServiceResourceUrlPathBuilderForWorkPlace(clientProps));
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public FindResult<X47BWorkPlace> findByOrgDivisionServiceLocation(final SecurityContext securityContext,
										   							  final X47BOrgDivisionServiceLocationOID locOid) {
		Url restResourceUrl = this.composeURIFor(this.getServicesRESTResourceUrlPathBuilderAs(X47BRESTServiceResourceUrlPathBuilderForWorkPlace.class)
			  															  .pathOfEntityListByOrgDivisionServiceLocation(locOid));
		return _findDelegate.doFindEntities(securityContext,
											restResourceUrl);
	}
	@Override
	public FindSummariesResult<X47BWorkPlace> findSummariesByOrgDivisionServiceLocation(final SecurityContext securityContext,
																  	  					final X47BOrgDivisionServiceLocationOID locOid,
																  	  					final Language lang) {
		Url restResourceUrl = this.composeURIFor(this.getServicesRESTResourceUrlPathBuilderAs(X47BRESTServiceResourceUrlPathBuilderForWorkPlace.class)
			  															  .pathOfSummariesByOrgDivisionServiceLocation(locOid),
			  									 UrlQueryString.fromParams(new UrlQueryStringParam("lang",lang.name())));
		return _findDelegate.doFindSummaries(securityContext,
											 restResourceUrl);
	}

}
