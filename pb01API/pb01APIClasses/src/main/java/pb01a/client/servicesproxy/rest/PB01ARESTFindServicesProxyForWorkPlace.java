package pb01a.client.servicesproxy.rest;

import javax.inject.Inject;
import javax.inject.Singleton;

import pb01a.api.interfaces.PB01AFindServicesForWorkPlace;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AWorkPlaceID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionServiceLocationOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AWorkPlaceOID;
import pb01a.model.org.PB01AWorkPlace;
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
public class PB01ARESTFindServicesProxyForWorkPlace
	 extends PB01ARESTFindServicesProxyBaseForModelObject<PB01AWorkPlaceOID,PB01AWorkPlaceID,PB01AWorkPlace>
  implements PB01AFindServicesForWorkPlace {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public PB01ARESTFindServicesProxyForWorkPlace(@XMLPropertiesComponent("client") final XMLPropertiesForAppComponent clientProps,
										     @ModelObjectsMarshaller 		   final Marshaller marshaller) {
		super(marshaller,
			  PB01AWorkPlace.class,
			  new PB01ARESTServiceResourceUrlPathBuilderForWorkPlace(clientProps));
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public FindResult<PB01AWorkPlace> findByOrgDivisionServiceLocation(final SecurityContext securityContext,
										   							  final PB01AOrgDivisionServiceLocationOID locOid) {
		Url restResourceUrl = this.composeURIFor(this.getServicesRESTResourceUrlPathBuilderAs(PB01ARESTServiceResourceUrlPathBuilderForWorkPlace.class)
			  															  .pathOfEntityListByOrgDivisionServiceLocation(locOid));
		return _findDelegate.doFindEntities(securityContext,
											restResourceUrl);
	}
	@Override
	public FindSummariesResult<PB01AWorkPlace> findSummariesByOrgDivisionServiceLocation(final SecurityContext securityContext,
																  	  					final PB01AOrgDivisionServiceLocationOID locOid,
																  	  					final Language lang) {
		Url restResourceUrl = this.composeURIFor(this.getServicesRESTResourceUrlPathBuilderAs(PB01ARESTServiceResourceUrlPathBuilderForWorkPlace.class)
			  															  .pathOfSummariesByOrgDivisionServiceLocation(locOid),
			  									 UrlQueryString.fromParams(new UrlQueryStringParam("lang",lang.name())));
		return _findDelegate.doFindSummaries(securityContext,
											 restResourceUrl);
	}

}
