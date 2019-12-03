package x47b.client.api.sub.delegates;

import java.util.Collection;

import javax.inject.Provider;

import r01f.locale.Language;
import r01f.model.persistence.FindResult;
import r01f.model.persistence.FindSummariesResult;
import r01f.objectstreamer.Marshaller;
import r01f.securitycontext.SecurityContext;
import x47b.api.interfaces.X47BFindServicesForOrgDivision;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrganizationOID;
import x47b.model.org.X47BOrgDivision;
import x47b.model.org.summaries.X47BSummarizedOrgDivision;

public class X47BClientAPIDelegateForOrgDivisionFindServices
	 extends X47BClientAPIDelegateForOrganizationalEntityFindServicesBase<X47BOrgDivisionOID,X47BOrgDivisionID,X47BOrgDivision> {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BClientAPIDelegateForOrgDivisionFindServices(final Provider<SecurityContext> securityContextProvider,
														   final Marshaller modelObjectsMarshaller,
														   final X47BFindServicesForOrgDivision findServicesProxy) {
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
	public Collection<X47BOrgDivision> findByOrganization(final X47BOrganizationOID orgOid) {
		FindResult<X47BOrgDivision> findResult = this.getServiceProxyAs(X47BFindServicesForOrgDivision.class)
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
	public Collection<X47BSummarizedOrgDivision> findSummariesByOrganization(final X47BOrganizationOID orgOid,
																		     final Language lang) {
		FindSummariesResult<X47BOrgDivision> findResult = this.getServiceProxyAs(X47BFindServicesForOrgDivision.class)
																.findSummariesByOrganization(this.getSecurityContext(),
																							 orgOid,
																							 lang);
		return findResult.getSummariesOrThrow();
	}
}
