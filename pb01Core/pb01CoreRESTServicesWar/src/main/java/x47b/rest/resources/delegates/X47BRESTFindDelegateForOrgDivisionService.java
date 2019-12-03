package x47b.rest.resources.delegates;

import java.net.URI;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import r01f.locale.Language;
import r01f.model.persistence.FindResult;
import r01f.model.persistence.FindSummariesResult;
import r01f.rest.RESTOperationsResponseBuilder;
import r01f.securitycontext.SecurityContext;
import x47b.api.interfaces.X47BFindServicesForOrgDivisionService;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionServiceID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceOID;
import x47b.model.org.X47BOrgDivisionService;
import x47b.server.rest.resources.delegates.X47BRESTFindDelegateBaseForOrganizationalEntity;

public class X47BRESTFindDelegateForOrgDivisionService
	 extends X47BRESTFindDelegateBaseForOrganizationalEntity<X47BOrgDivisionServiceOID,X47BOrgDivisionServiceID,X47BOrgDivisionService> {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BRESTFindDelegateForOrgDivisionService(final X47BFindServicesForOrgDivisionService findServices) {
		super(X47BOrgDivisionService.class,
			  findServices);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	public Response findByOrgDivision(final SecurityContext securityContext,final String resourcePath,
			  					   	  final X47BOrgDivisionOID divisionOid) {
		FindResult<X47BOrgDivisionService> findResult = this.getFindServicesAs(X47BFindServicesForOrgDivisionService.class)
																.findByOrgDivision(securityContext,
																				   divisionOid);
		return RESTOperationsResponseBuilder.findOn(_modelObjectType)
											.at(URI.create(resourcePath))
											.mediaType(MediaType.TEXT_XML_TYPE)
											.build(findResult);
	}
	public Response findSummariesByOrgDivision(final SecurityContext securityContext,final String resourcePath,
											   final X47BOrgDivisionOID divisionOid,
								  			   final Language lang) {
		FindSummariesResult<X47BOrgDivisionService> findResult = this.getFindServicesAs(X47BFindServicesForOrgDivisionService.class)
																		   .findSummariesByOrgDivision(securityContext,
																						  			   divisionOid,lang);
		return RESTOperationsResponseBuilder.findOn(_modelObjectType)
											.at(URI.create(resourcePath))
											.mediaType(MediaType.TEXT_XML_TYPE)
											.build(findResult);
	}
}
