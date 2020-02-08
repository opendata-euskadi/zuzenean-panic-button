package pb01c.rest.resources.delegates;

import java.net.URI;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import pb01a.api.interfaces.PB01AFindServicesForOrgDivision;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrganizationOID;
import pb01a.model.org.PB01AOrgDivision;
import pb01c.server.rest.resources.delegates.PB01CRESTFindDelegateBaseForOrganizationalEntity;
import r01f.locale.Language;
import r01f.model.persistence.FindResult;
import r01f.model.persistence.FindSummariesResult;
import r01f.rest.RESTOperationsResponseBuilder;
import r01f.securitycontext.SecurityContext;

public class PB01CRESTFindDelegateForOrgDivision
	 extends PB01CRESTFindDelegateBaseForOrganizationalEntity<PB01AOrgDivisionOID,PB01AOrgDivisionID,PB01AOrgDivision> {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01CRESTFindDelegateForOrgDivision(final PB01AFindServicesForOrgDivision findServices) {
		super(PB01AOrgDivision.class,
			  findServices);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	public Response findByOrganization(final SecurityContext securityContext,final String resourcePath,
			  					   	   final PB01AOrganizationOID orgOid) {
		FindResult<PB01AOrgDivision> findResult = this.getFindServicesAs(PB01AFindServicesForOrgDivision.class)
													 .findByOrganization(securityContext,
																		 orgOid);
		return RESTOperationsResponseBuilder.findOn(_modelObjectType)
											.at(URI.create(resourcePath))
											.mediaType(MediaType.TEXT_XML_TYPE)
											.build(findResult);
	}
	public Response findSummariesByOrganization(final SecurityContext securityContext,final String resourcePath,
												final PB01AOrganizationOID orgOid,
								  				final Language lang) {
		FindSummariesResult<PB01AOrgDivision> findResult = this.getFindServicesAs(PB01AFindServicesForOrgDivision.class)
															   .findSummariesByOrganization(securityContext,
																			  			    orgOid,lang);
		return RESTOperationsResponseBuilder.findOn(_modelObjectType)
											.at(URI.create(resourcePath))
											.mediaType(MediaType.TEXT_XML_TYPE)
											.build(findResult);
	}
}
