package x47b.rest.resources.delegates;

import java.net.URI;

import javax.ws.rs.core.Response;

import r01f.locale.Language;
import r01f.model.persistence.FindResult;
import r01f.model.persistence.FindSummariesResult;
import r01f.rest.RESTOperationsResponseBuilder;
import r01f.securitycontext.SecurityContext;
import x47b.api.interfaces.X47BFindServicesForOrgDivisionServiceLocation;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionServiceLocationID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceLocationOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceOID;
import x47b.model.org.X47BOrgDivisionServiceLocation;
import x47b.server.rest.resources.delegates.X47BRESTFindDelegateBaseForOrganizationalEntity;

public class X47BRESTFindDelegateForOrgDivisionServiceLocation
	 extends X47BRESTFindDelegateBaseForOrganizationalEntity<X47BOrgDivisionServiceLocationOID,X47BOrgDivisionServiceLocationID,X47BOrgDivisionServiceLocation> {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BRESTFindDelegateForOrgDivisionServiceLocation(final X47BFindServicesForOrgDivisionServiceLocation findServices) {
		super(X47BOrgDivisionServiceLocation.class,
			  findServices);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	public Response findByOrgDivisionService(final SecurityContext securityContext,final String resourcePath,
			  					   	  		 final X47BOrgDivisionServiceOID serviceOid) {
		FindResult<X47BOrgDivisionServiceLocation> findResult = this.getFindServicesAs(X47BFindServicesForOrgDivisionServiceLocation.class)
																		.findByOrgDivisionService(securityContext,
																						   		 serviceOid);
		return RESTOperationsResponseBuilder.findOn(_modelObjectType)
											.at(URI.create(resourcePath))
											.build(findResult);
	}
	public Response findSummariesByOrgDivisionService(final SecurityContext securityContext,final String resourcePath,
											   		  final X47BOrgDivisionServiceOID serviceOid,
											   		  final Language lang) {
		FindSummariesResult<X47BOrgDivisionServiceLocation> findResult = this.getFindServicesAs(X47BFindServicesForOrgDivisionServiceLocation.class)
																			   .findSummariesByOrgDivisionService(securityContext,
																							  			   		  serviceOid,lang);
		return RESTOperationsResponseBuilder.findOn(_modelObjectType)
											.at(URI.create(resourcePath))
											.build(findResult);
	}
}
