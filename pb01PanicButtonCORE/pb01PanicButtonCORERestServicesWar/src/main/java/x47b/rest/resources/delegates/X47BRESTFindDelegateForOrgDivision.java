package x47b.rest.resources.delegates;

import java.net.URI;

import javax.ws.rs.core.Response;

import r01f.locale.Language;
import r01f.model.persistence.FindResult;
import r01f.model.persistence.FindSummariesResult;
import r01f.rest.RESTOperationsResponseBuilder;
import r01f.securitycontext.SecurityContext;
import x47b.api.interfaces.X47BFindServicesForOrgDivision;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrganizationOID;
import x47b.model.org.X47BOrgDivision;
import x47b.server.rest.resources.delegates.X47BRESTFindDelegateBaseForOrganizationalEntity;

public class X47BRESTFindDelegateForOrgDivision
	 extends X47BRESTFindDelegateBaseForOrganizationalEntity<X47BOrgDivisionOID,X47BOrgDivisionID,X47BOrgDivision> {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BRESTFindDelegateForOrgDivision(final X47BFindServicesForOrgDivision findServices) {
		super(X47BOrgDivision.class,
			  findServices);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	public Response findByOrganization(final SecurityContext securityContext,final String resourcePath,
			  					   	   final X47BOrganizationOID orgOid) {
		FindResult<X47BOrgDivision> findResult = this.getFindServicesAs(X47BFindServicesForOrgDivision.class)
													 .findByOrganization(securityContext,
																		 orgOid);
		return RESTOperationsResponseBuilder.findOn(_modelObjectType)
											.at(URI.create(resourcePath))
											.build(findResult);
	}
	public Response findSummariesByOrganization(final SecurityContext securityContext,final String resourcePath,
												final X47BOrganizationOID orgOid,
								  				final Language lang) {
		FindSummariesResult<X47BOrgDivision> findResult = this.getFindServicesAs(X47BFindServicesForOrgDivision.class)
															   .findSummariesByOrganization(securityContext,
																			  			    orgOid,lang);
		return RESTOperationsResponseBuilder.findOn(_modelObjectType)
											.at(URI.create(resourcePath))
											.build(findResult);
	}
}
