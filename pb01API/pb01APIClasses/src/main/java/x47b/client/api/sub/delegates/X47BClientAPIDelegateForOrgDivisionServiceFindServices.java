package x47b.client.api.sub.delegates;

import java.util.Collection;

import javax.inject.Provider;

import r01f.locale.Language;
import r01f.model.persistence.FindResult;
import r01f.model.persistence.FindSummariesResult;
import r01f.objectstreamer.Marshaller;
import r01f.securitycontext.SecurityContext;
import x47b.api.interfaces.X47BFindServicesForOrgDivisionService;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionServiceID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceOID;
import x47b.model.org.X47BOrgDivisionService;
import x47b.model.org.summaries.X47BSummarizedOrgDivisionService;

public class X47BClientAPIDelegateForOrgDivisionServiceFindServices
	 extends X47BClientAPIDelegateForOrganizationalEntityFindServicesBase<X47BOrgDivisionServiceOID,X47BOrgDivisionServiceID,X47BOrgDivisionService> {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BClientAPIDelegateForOrgDivisionServiceFindServices(final Provider<SecurityContext> securityContextProvider,
																  final Marshaller modelObjectsMarshaller,
													 			  final X47BFindServicesForOrgDivisionService findServicesProxy) {
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
	public Collection<X47BOrgDivisionService> findByOrgDivision(final X47BOrgDivisionOID divisionOid) {
		FindResult<X47BOrgDivisionService> findResult = this.getServiceProxyAs(X47BFindServicesForOrgDivisionService.class)
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
	public Collection<X47BSummarizedOrgDivisionService> findSummariesByOrgDivision(final X47BOrgDivisionOID divisionOid,
																   				   final Language lang) {
		FindSummariesResult<X47BOrgDivisionService> findResult = this.getServiceProxyAs(X47BFindServicesForOrgDivisionService.class)
																			.findSummariesByOrgDivision(this.getSecurityContext(),
																						   			 divisionOid,
																						   			 lang);
		return findResult.getSummariesOrThrow();
	}
}
