package pb01a.client.servicesproxy.rest;

import javax.inject.Inject;
import javax.inject.Singleton;

import pb01a.api.interfaces.PB01AFindServicesForOrgDivisionService;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionServiceID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionServiceOID;
import pb01a.model.org.PB01AOrgDivisionService;
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
public class PB01ARESTFindServicesProxyForOrgDivisionService
	 extends PB01ARESTFindServicesProxyForOrganizationalEntityBase<PB01AOrgDivisionServiceOID,PB01AOrgDivisionServiceID,PB01AOrgDivisionService>
  implements PB01AFindServicesForOrgDivisionService {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public PB01ARESTFindServicesProxyForOrgDivisionService(@XMLPropertiesComponent("client") final XMLPropertiesForAppComponent clientProps,
												          @ModelObjectsMarshaller 			final Marshaller marshaller) {
		super(marshaller,
			  PB01AOrgDivisionService.class,
			  new PB01ARESTServiceResourceUrlPathBuilderForOrgDivisionService(clientProps));
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public FindResult<PB01AOrgDivisionService> findByOrgDivision(final SecurityContext securityContext,
										   						final PB01AOrgDivisionOID divisionOid) {
		Url restResourceUrl = this.composeURIFor(this.getServicesRESTResourceUrlPathBuilderAs(PB01ARESTServiceResourceUrlPathBuilderForOrgDivisionService.class)
			  															  .pathOfEntityListByOrgDivision(divisionOid));
		return _findDelegate.doFindEntities(securityContext,
											restResourceUrl);
	}
	@Override
	public FindSummariesResult<PB01AOrgDivisionService> findSummariesByOrgDivision(final SecurityContext securityContext,
																				  final PB01AOrgDivisionOID divisionOid,
																				  final Language lang) {
		Url restResourceUrl = this.composeURIFor(this.getServicesRESTResourceUrlPathBuilderAs(PB01ARESTServiceResourceUrlPathBuilderForOrgDivisionService.class)
			  											  .pathOfSummariesByOrgDivision(divisionOid),
			  									 UrlQueryString.fromParams(UrlQueryStringParam.of(lang)));
		return _findDelegate.doFindSummaries(securityContext,
											 restResourceUrl);
	}

}
