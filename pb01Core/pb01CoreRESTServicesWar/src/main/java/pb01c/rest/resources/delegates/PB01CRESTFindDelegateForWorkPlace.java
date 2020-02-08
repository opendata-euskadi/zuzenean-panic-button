package pb01c.rest.resources.delegates;

import java.net.URI;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import pb01a.api.interfaces.PB01AFindServicesForWorkPlace;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AWorkPlaceID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionServiceLocationOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AWorkPlaceOID;
import pb01a.model.org.PB01AWorkPlace;
import pb01c.server.rest.resources.delegates.PB01CRESTFindDelegateBaseForEntity;
import r01f.locale.Language;
import r01f.model.persistence.FindResult;
import r01f.model.persistence.FindSummariesResult;
import r01f.rest.RESTOperationsResponseBuilder;
import r01f.securitycontext.SecurityContext;

public class PB01CRESTFindDelegateForWorkPlace
	 extends PB01CRESTFindDelegateBaseForEntity<PB01AWorkPlaceOID,PB01AWorkPlaceID,PB01AWorkPlace> {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01CRESTFindDelegateForWorkPlace(final PB01AFindServicesForWorkPlace findServices) {
		super(PB01AWorkPlace.class,
			  findServices);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	public Response findByOrgDivisionServiceLocation(final SecurityContext securityContext,final String resourcePath,
			  					   					 final PB01AOrgDivisionServiceLocationOID locOid) {
		FindResult<PB01AWorkPlace> findResult = this.getFindServicesAs(PB01AFindServicesForWorkPlace.class)
													.findByOrgDivisionServiceLocation(securityContext,
																	locOid);
		return RESTOperationsResponseBuilder.findOn(_modelObjectType)
											.at(URI.create(resourcePath))
											.mediaType(MediaType.TEXT_XML_TYPE)
											.build(findResult);
	}
	public Response findSummariesByOrgDivisionServiceLocation(final SecurityContext securityContext,final String resourcePath,
															  final PB01AOrgDivisionServiceLocationOID locOid,
															  final Language lang) {
		FindSummariesResult<PB01AWorkPlace> findResult = this.getFindServicesAs(PB01AFindServicesForWorkPlace.class)
															   .findSummariesByOrgDivisionServiceLocation(securityContext,
																			  			locOid,lang);
		return RESTOperationsResponseBuilder.findOn(_modelObjectType)
											.at(URI.create(resourcePath))
											.mediaType(MediaType.TEXT_XML_TYPE)
											.build(findResult);
	}
}
