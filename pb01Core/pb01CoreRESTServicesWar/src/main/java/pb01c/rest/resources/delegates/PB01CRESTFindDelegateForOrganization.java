package pb01c.rest.resources.delegates;

import java.net.URI;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import pb01a.api.interfaces.PB01AFindServicesForOrganization;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrganizationID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrganizationOID;
import pb01a.model.org.PB01AOrganization;
import pb01c.server.rest.resources.delegates.PB01CRESTFindDelegateBaseForOrganizationalEntity;
import r01f.locale.Language;
import r01f.model.persistence.FindSummariesResult;
import r01f.rest.RESTOperationsResponseBuilder;
import r01f.securitycontext.SecurityContext;

public class PB01CRESTFindDelegateForOrganization
	 extends PB01CRESTFindDelegateBaseForOrganizationalEntity<PB01AOrganizationOID,PB01AOrganizationID,PB01AOrganization> {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01CRESTFindDelegateForOrganization(final PB01AFindServicesForOrganization findServices) {
		super(PB01AOrganization.class,
			  findServices);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	EXTENSION METHODS
/////////////////////////////////////////////////////////////////////////////////////////
	public Response findSummaries(final SecurityContext securityContext,final String resourcePath,
								  final Language lang) {
		FindSummariesResult<PB01AOrganization> findResult = this.getFindServicesAs(PB01AFindServicesForOrganization.class)
															   .findSummaries(securityContext,
																			  lang);
		return RESTOperationsResponseBuilder.findOn(_modelObjectType)
											.at(URI.create(resourcePath))
											.mediaType(MediaType.TEXT_XML_TYPE)
											.build(findResult);
	}
}
