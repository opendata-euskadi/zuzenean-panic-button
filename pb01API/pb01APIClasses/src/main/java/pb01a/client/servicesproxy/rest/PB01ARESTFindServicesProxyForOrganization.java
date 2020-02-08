package pb01a.client.servicesproxy.rest;

import javax.inject.Inject;
import javax.inject.Singleton;

import pb01a.api.interfaces.PB01AFindServicesForOrganization;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrganizationID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrganizationOID;
import pb01a.model.org.PB01AOrganization;
import r01f.locale.Language;
import r01f.model.annotations.ModelObjectsMarshaller;
import r01f.model.persistence.FindSummariesResult;
import r01f.objectstreamer.Marshaller;
import r01f.securitycontext.SecurityContext;
import r01f.types.url.Url;
import r01f.types.url.UrlQueryString;
import r01f.types.url.UrlQueryStringParam;
import r01f.xmlproperties.XMLPropertiesForAppComponent;
import r01f.xmlproperties.annotations.XMLPropertiesComponent;


@Singleton
public class PB01ARESTFindServicesProxyForOrganization
	 extends PB01ARESTFindServicesProxyForOrganizationalEntityBase<PB01AOrganizationOID,PB01AOrganizationID,PB01AOrganization>
  implements PB01AFindServicesForOrganization {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public PB01ARESTFindServicesProxyForOrganization(@XMLPropertiesComponent("client") final XMLPropertiesForAppComponent clientProps,
												    @ModelObjectsMarshaller 		  final Marshaller marshaller) {
		super(marshaller,
			  PB01AOrganization.class,
			  new PB01ARESTServiceResourceUrlPathBuilderForOrganization(clientProps));
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////	
	@Override
	public FindSummariesResult<PB01AOrganization> findSummaries(final SecurityContext securityContext,
															    final Language lang) {
		Url restResourceUrl = this.composeURIFor(this.getServicesRESTResourceUrlPathBuilderAs(PB01ARESTServiceResourceUrlPathBuilderForOrganization.class)
															   			  .pathOfSummaries(),
												 UrlQueryString.fromParams(UrlQueryStringParam.of(lang)));
		return _findDelegate.doFindSummaries(securityContext,
											 restResourceUrl);
	}

}
