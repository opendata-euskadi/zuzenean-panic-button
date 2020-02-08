package pb01a.client.api.sub.delegates;

import java.util.Collection;

import javax.inject.Provider;

import pb01a.api.interfaces.PB01AFindServicesForOrgDivisionService;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionServiceID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionServiceOID;
import pb01a.model.org.PB01AOrgDivisionService;
import pb01a.model.org.summaries.PB01ASummarizedOrgDivisionService;
import r01f.locale.Language;
import r01f.model.persistence.FindResult;
import r01f.model.persistence.FindSummariesResult;
import r01f.objectstreamer.Marshaller;
import r01f.securitycontext.SecurityContext;

public class PB01AClientAPIDelegateForOrgDivisionServiceFindServices
	 extends PB01AClientAPIDelegateForOrganizationalEntityFindServicesBase<PB01AOrgDivisionServiceOID,PB01AOrgDivisionServiceID,PB01AOrgDivisionService> {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01AClientAPIDelegateForOrgDivisionServiceFindServices(final Provider<SecurityContext> securityContextProvider,
																  final Marshaller modelObjectsMarshaller,
													 			  final PB01AFindServicesForOrgDivisionService findServicesProxy) {
		super(securityContextProvider,
			  modelObjectsMarshaller,
			  findServicesProxy);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  EXTENSION METHODS
/////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Return all services in a division
	 * @param divisionOid
	 * @return
	 */
	public Collection<PB01AOrgDivisionService> findByOrgDivision(final PB01AOrgDivisionOID divisionOid) {
		FindResult<PB01AOrgDivisionService> findResult = this.getServiceProxyAs(PB01AFindServicesForOrgDivisionService.class)
																	.findByOrgDivision(this.getSecurityContext(),
																					   divisionOid);
		return findResult.getOrThrow();
	}
	/**
	 * Return summaries for all services in a division
	 * @param divisionOid
	 * @param lang
	 * @return
	 */
	public Collection<PB01ASummarizedOrgDivisionService> findSummariesByOrgDivision(final PB01AOrgDivisionOID divisionOid,
																   				   final Language lang) {
		FindSummariesResult<PB01AOrgDivisionService> findResult = this.getServiceProxyAs(PB01AFindServicesForOrgDivisionService.class)
																			.findSummariesByOrgDivision(this.getSecurityContext(),
																						   			 divisionOid,
																						   			 lang);
		return findResult.getSummariesOrThrow();
	}
}
