package x47b.rest.resources.delegates;

import java.net.URI;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import r01f.locale.Language;
import r01f.model.persistence.FindSummariesResult;
import r01f.rest.RESTOperationsResponseBuilder;
import r01f.securitycontext.SecurityContext;
import x47b.api.interfaces.X47BFindServicesForOrganization;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrganizationID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrganizationOID;
import x47b.model.org.X47BOrganization;
import x47b.server.rest.resources.delegates.X47BRESTFindDelegateBaseForOrganizationalEntity;

public class X47BRESTFindDelegateForOrganization
	 extends X47BRESTFindDelegateBaseForOrganizationalEntity<X47BOrganizationOID,X47BOrganizationID,X47BOrganization> {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BRESTFindDelegateForOrganization(final X47BFindServicesForOrganization findServices) {
		super(X47BOrganization.class,
			  findServices);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	EXTENSION METHODS
/////////////////////////////////////////////////////////////////////////////////////////
	public Response findSummaries(final SecurityContext securityContext,final String resourcePath,
								  final Language lang) {
		FindSummariesResult<X47BOrganization> findResult = this.getFindServicesAs(X47BFindServicesForOrganization.class)
															   .findSummaries(securityContext,
																			  lang);
		return RESTOperationsResponseBuilder.findOn(_modelObjectType)
											.at(URI.create(resourcePath))
											.mediaType(MediaType.TEXT_XML_TYPE)
											.build(findResult);
	}
}
