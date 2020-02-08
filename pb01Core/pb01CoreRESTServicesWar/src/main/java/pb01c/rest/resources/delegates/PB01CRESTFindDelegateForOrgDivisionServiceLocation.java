package pb01c.rest.resources.delegates;

import java.net.URI;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import pb01a.api.interfaces.PB01AFindServicesForOrgDivisionServiceLocation;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionServiceLocationID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionServiceLocationOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionServiceOID;
import pb01a.model.org.PB01AOrgDivisionServiceLocation;
import pb01c.server.rest.resources.delegates.PB01CRESTFindDelegateBaseForOrganizationalEntity;
import r01f.locale.Language;
import r01f.model.persistence.FindResult;
import r01f.model.persistence.FindSummariesResult;
import r01f.rest.RESTOperationsResponseBuilder;
import r01f.securitycontext.SecurityContext;

public class PB01CRESTFindDelegateForOrgDivisionServiceLocation
	 extends PB01CRESTFindDelegateBaseForOrganizationalEntity<PB01AOrgDivisionServiceLocationOID,PB01AOrgDivisionServiceLocationID,PB01AOrgDivisionServiceLocation> {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01CRESTFindDelegateForOrgDivisionServiceLocation(final PB01AFindServicesForOrgDivisionServiceLocation findServices) {
		super(PB01AOrgDivisionServiceLocation.class,
			  findServices);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	public Response findByOrgDivisionService(final SecurityContext securityContext,final String resourcePath,
			  					   	  		 final PB01AOrgDivisionServiceOID serviceOid) {
		FindResult<PB01AOrgDivisionServiceLocation> findResult = this.getFindServicesAs(PB01AFindServicesForOrgDivisionServiceLocation.class)
																		.findByOrgDivisionService(securityContext,
																						   		 serviceOid);
		return RESTOperationsResponseBuilder.findOn(_modelObjectType)
											.at(URI.create(resourcePath))
											.mediaType(MediaType.TEXT_XML_TYPE)
											.build(findResult);
	}
	public Response findSummariesByOrgDivisionService(final SecurityContext securityContext,final String resourcePath,
											   		  final PB01AOrgDivisionServiceOID serviceOid,
											   		  final Language lang) {
		FindSummariesResult<PB01AOrgDivisionServiceLocation> findResult = this.getFindServicesAs(PB01AFindServicesForOrgDivisionServiceLocation.class)
																			   .findSummariesByOrgDivisionService(securityContext,
																							  			   		  serviceOid,lang);
		return RESTOperationsResponseBuilder.findOn(_modelObjectType)
											.at(URI.create(resourcePath))
											.mediaType(MediaType.TEXT_XML_TYPE)
											.build(findResult);
	}
}
