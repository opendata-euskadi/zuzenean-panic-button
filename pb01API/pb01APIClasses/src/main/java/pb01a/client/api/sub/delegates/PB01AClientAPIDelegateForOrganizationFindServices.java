package pb01a.client.api.sub.delegates;

import java.util.Collection;

import javax.inject.Provider;

import pb01a.api.interfaces.PB01AFindServicesForOrganization;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrganizationID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrganizationOID;
import pb01a.model.org.PB01AOrganization;
import pb01a.model.org.summaries.PB01ASummarizedOrganization;
import r01f.guids.OID;
import r01f.locale.Language;
import r01f.model.persistence.FindSummariesResult;
import r01f.objectstreamer.Marshaller;
import r01f.securitycontext.SecurityContext;

public class PB01AClientAPIDelegateForOrganizationFindServices
	 extends PB01AClientAPIDelegateForOrganizationalEntityFindServicesBase<PB01AOrganizationOID,PB01AOrganizationID,PB01AOrganization> {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01AClientAPIDelegateForOrganizationFindServices(final Provider<SecurityContext> securityContextProvider,
															final Marshaller modelObjectsMarshaller,
															final PB01AFindServicesForOrganization findServicesProxy) {
		super(securityContextProvider,
			  modelObjectsMarshaller,
			  findServicesProxy);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  EXTENSION METHODS
/////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Returns all organizations summary that contains their {@link OID} and ID
	 * alongside their name in the provided language
	 * @param lang
	 * @return
	 */
	public Collection<PB01ASummarizedOrganization> findSummaries(final Language lang) {
		FindSummariesResult<PB01AOrganization> findSummaryResult = this.getServiceProxyAs(PB01AFindServicesForOrganization.class)
																			.findSummaries(this.getSecurityContext(),
																						   lang);
		Collection<PB01ASummarizedOrganization> orgSummaries = findSummaryResult.getSummariesOrThrow();
		return orgSummaries;
	}
}
