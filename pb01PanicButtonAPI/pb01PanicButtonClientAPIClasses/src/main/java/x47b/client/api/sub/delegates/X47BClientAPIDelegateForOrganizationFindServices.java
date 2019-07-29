package x47b.client.api.sub.delegates;

import java.util.Collection;

import javax.inject.Provider;

import r01f.guids.OID;
import r01f.locale.Language;
import r01f.model.persistence.FindSummariesResult;
import r01f.objectstreamer.Marshaller;
import r01f.securitycontext.SecurityContext;
import x47b.api.interfaces.X47BFindServicesForOrganization;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrganizationID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrganizationOID;
import x47b.model.org.X47BOrganization;
import x47b.model.org.summaries.X47BSummarizedOrganization;

public class X47BClientAPIDelegateForOrganizationFindServices
	 extends X47BClientAPIDelegateForOrganizationalEntityFindServicesBase<X47BOrganizationOID,X47BOrganizationID,X47BOrganization> {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BClientAPIDelegateForOrganizationFindServices(final Provider<SecurityContext> securityContextProvider,
															final Marshaller modelObjectsMarshaller,
															final X47BFindServicesForOrganization findServicesProxy) {
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
	public Collection<X47BSummarizedOrganization> findSummaries(final Language lang) {
		FindSummariesResult<X47BOrganization> findSummaryResult = this.getServiceProxyAs(X47BFindServicesForOrganization.class)
																			.findSummaries(this.getSecurityContext(),
																						   lang);
		Collection<X47BSummarizedOrganization> orgSummaries = findSummaryResult.getOrThrow();
		return orgSummaries;
	}
}
