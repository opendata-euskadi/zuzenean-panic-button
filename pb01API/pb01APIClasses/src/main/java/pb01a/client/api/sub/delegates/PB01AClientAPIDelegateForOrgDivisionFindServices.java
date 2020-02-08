package pb01a.client.api.sub.delegates;

import java.util.Collection;

import javax.inject.Provider;

import pb01a.api.interfaces.PB01AFindServicesForOrgDivision;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrganizationOID;
import pb01a.model.org.PB01AOrgDivision;
import pb01a.model.org.summaries.PB01ASummarizedOrgDivision;
import r01f.locale.Language;
import r01f.model.persistence.FindResult;
import r01f.model.persistence.FindSummariesResult;
import r01f.objectstreamer.Marshaller;
import r01f.securitycontext.SecurityContext;

public class PB01AClientAPIDelegateForOrgDivisionFindServices
	 extends PB01AClientAPIDelegateForOrganizationalEntityFindServicesBase<PB01AOrgDivisionOID,PB01AOrgDivisionID,PB01AOrgDivision> {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01AClientAPIDelegateForOrgDivisionFindServices(final Provider<SecurityContext> securityContextProvider,
														   final Marshaller modelObjectsMarshaller,
														   final PB01AFindServicesForOrgDivision findServicesProxy) {
		super(securityContextProvider,
			  modelObjectsMarshaller,
			  findServicesProxy);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  EXTENSION METHODS
/////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Return all OrgDivisions of one organization.
	 * @param orgOid
	 * @return
	 */
	public Collection<PB01AOrgDivision> findByOrganization(final PB01AOrganizationOID orgOid) {
		FindResult<PB01AOrgDivision> findResult = this.getServiceProxyAs(PB01AFindServicesForOrgDivision.class)
														.findByOrganization(this.getSecurityContext(),
																			orgOid);
		return findResult.getOrThrow();
	}
	/**
	 * Return summaries for all OrgDivisions in an organization
	 * @param orgOid
	 * @param lang
	 * @return
	 */
	public Collection<PB01ASummarizedOrgDivision> findSummariesByOrganization(final PB01AOrganizationOID orgOid,
																		     final Language lang) {
		FindSummariesResult<PB01AOrgDivision> findResult = this.getServiceProxyAs(PB01AFindServicesForOrgDivision.class)
																.findSummariesByOrganization(this.getSecurityContext(),
																							 orgOid,
																							 lang);
		return findResult.getSummariesOrThrow();
	}
}
