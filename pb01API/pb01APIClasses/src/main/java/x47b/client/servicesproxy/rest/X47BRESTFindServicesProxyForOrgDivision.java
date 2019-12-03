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
import x47b.api.interfaces.X47BFindServicesForOrgDivision;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrganizationOID;
import x47b.model.org.X47BOrgDivision;


@Singleton
public class X47BRESTFindServicesProxyForOrgDivision
	 extends X47BRESTFindServicesProxyForOrganizationalEntityBase<X47BOrgDivisionOID,X47BOrgDivisionID,X47BOrgDivision>
  implements X47BFindServicesForOrgDivision {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public X47BRESTFindServicesProxyForOrgDivision(@XMLPropertiesComponent("client") final XMLPropertiesForAppComponent clientProps,
												   @ModelObjectsMarshaller 		     final Marshaller marshaller) {
		super(marshaller,
			  X47BOrgDivision.class,
			  new X47BRESTServiceResourceUrlPathBuilderForOrgDivision(clientProps));
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public FindResult<X47BOrgDivision> findByOrganization(final SecurityContext securityContext,
											  		   	  final X47BOrganizationOID orgOid) {
		Url restResourceUrl = this.composeURIFor(this.getServicesRESTResourceUrlPathBuilderAs(X47BRESTServiceResourceUrlPathBuilderForOrgDivision.class)
			  															  .pathOfEntityListByOrganization(orgOid));
		return _findDelegate.doFindEntities(securityContext,
											restResourceUrl);
	}
	@Override
	public FindSummariesResult<X47BOrgDivision> findSummariesByOrganization(final SecurityContext securityContext,
																		 	final X47BOrganizationOID orgOid,
																		 	final Language lang) {
		Url restResourceUrl = this.composeURIFor(this.getServicesRESTResourceUrlPathBuilderAs(X47BRESTServiceResourceUrlPathBuilderForOrgDivision.class)
			  													  .pathOfSummariesByOrganization(orgOid),
			  									 UrlQueryString.fromParams(UrlQueryStringParam.of(lang)));
		return _findDelegate.doFindSummaries(securityContext,
											 restResourceUrl);
	}

}
