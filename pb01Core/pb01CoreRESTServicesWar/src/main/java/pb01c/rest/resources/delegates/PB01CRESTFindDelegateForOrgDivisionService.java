package pb01c.rest.resources.delegates;

import java.net.URI;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import pb01a.api.interfaces.PB01AFindServicesForOrgDivisionService;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionServiceID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionServiceOID;
import pb01a.model.org.PB01AOrgDivisionService;
import pb01c.server.rest.resources.delegates.PB01CRESTFindDelegateBaseForOrganizationalEntity;
import r01f.locale.Language;
import r01f.model.persistence.FindResult;
import r01f.model.persistence.FindSummariesResult;
import r01f.rest.RESTOperationsResponseBuilder;
import r01f.securitycontext.SecurityContext;

public class PB01CRESTFindDelegateForOrgDivisionService
	 extends PB01CRESTFindDelegateBaseForOrganizationalEntity<PB01AOrgDivisionServiceOID,PB01AOrgDivisionServiceID,PB01AOrgDivisionService> {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01CRESTFindDelegateForOrgDivisionService(final PB01AFindServicesForOrgDivisionService findServices) {
		super(PB01AOrgDivisionService.class,
			  findServices);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	public Response findByOrgDivision(final SecurityContext securityContext,final String resourcePath,
			  					   	  final PB01AOrgDivisionOID divisionOid) {
		FindResult<PB01AOrgDivisionService> findResult = this.getFindServicesAs(PB01AFindServicesForOrgDivisionService.class)
																.findByOrgDivision(securityContext,
																				   divisionOid);
		return RESTOperationsResponseBuilder.findOn(_modelObjectType)
											.at(URI.create(resourcePath))
											.mediaType(MediaType.TEXT_XML_TYPE)
											.build(findResult);
	}
	public Response findSummariesByOrgDivision(final SecurityContext securityContext,final String resourcePath,
											   final PB01AOrgDivisionOID divisionOid,
								  			   final Language lang) {
		FindSummariesResult<PB01AOrgDivisionService> findResult = this.getFindServicesAs(PB01AFindServicesForOrgDivisionService.class)
																		   .findSummariesByOrgDivision(securityContext,
																						  			   divisionOid,lang);
		return RESTOperationsResponseBuilder.findOn(_modelObjectType)
											.at(URI.create(resourcePath))
											.mediaType(MediaType.TEXT_XML_TYPE)
											.build(findResult);
	}
}
