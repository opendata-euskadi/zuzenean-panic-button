package x47b.rest.resources.delegates;

import java.net.URI;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import r01f.locale.Language;
import r01f.model.persistence.FindResult;
import r01f.model.persistence.FindSummariesResult;
import r01f.rest.RESTOperationsResponseBuilder;
import r01f.securitycontext.SecurityContext;
import x47b.api.interfaces.X47BFindServicesForWorkPlace;
import x47b.model.oids.X47BOrganizationalIDs.X47BWorkPlaceID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceLocationOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BWorkPlaceOID;
import x47b.model.org.X47BWorkPlace;
import x47b.server.rest.resources.delegates.X47BRESTFindDelegateBaseForEntity;

public class X47BRESTFindDelegateForWorkPlace
	 extends X47BRESTFindDelegateBaseForEntity<X47BWorkPlaceOID,X47BWorkPlaceID,X47BWorkPlace> {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BRESTFindDelegateForWorkPlace(final X47BFindServicesForWorkPlace findServices) {
		super(X47BWorkPlace.class,
			  findServices);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	public Response findByOrgDivisionServiceLocation(final SecurityContext securityContext,final String resourcePath,
			  					   					 final X47BOrgDivisionServiceLocationOID locOid) {
		FindResult<X47BWorkPlace> findResult = this.getFindServicesAs(X47BFindServicesForWorkPlace.class)
													.findByOrgDivisionServiceLocation(securityContext,
																	locOid);
		return RESTOperationsResponseBuilder.findOn(_modelObjectType)
											.at(URI.create(resourcePath))
											.mediaType(MediaType.TEXT_XML_TYPE)
											.build(findResult);
	}
	public Response findSummariesByOrgDivisionServiceLocation(final SecurityContext securityContext,final String resourcePath,
															  final X47BOrgDivisionServiceLocationOID locOid,
															  final Language lang) {
		FindSummariesResult<X47BWorkPlace> findResult = this.getFindServicesAs(X47BFindServicesForWorkPlace.class)
															   .findSummariesByOrgDivisionServiceLocation(securityContext,
																			  			locOid,lang);
		return RESTOperationsResponseBuilder.findOn(_modelObjectType)
											.at(URI.create(resourcePath))
											.mediaType(MediaType.TEXT_XML_TYPE)
											.build(findResult);
	}
}
