package x47b.client.api.sub.delegates;

import java.util.Collection;

import javax.inject.Provider;

import r01f.locale.Language;
import r01f.model.persistence.FindResult;
import r01f.model.persistence.FindSummariesResult;
import r01f.objectstreamer.Marshaller;
import r01f.securitycontext.SecurityContext;
import x47b.api.interfaces.X47BFindServicesForOrgDivisionServiceLocation;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionServiceLocationID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceLocationOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceOID;
import x47b.model.org.X47BOrgDivisionServiceLocation;
import x47b.model.org.summaries.X47BSummarizedOrgDivisionServiceLocation;

public class X47BClientAPIDelegateForOrgDivisionServiceLocationFindServices
	 extends X47BClientAPIDelegateForOrganizationalEntityFindServicesBase<X47BOrgDivisionServiceLocationOID,X47BOrgDivisionServiceLocationID,X47BOrgDivisionServiceLocation> {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BClientAPIDelegateForOrgDivisionServiceLocationFindServices(final Provider<SecurityContext> securityContextProvider,
																		  final Marshaller modelObjectsMarshaller,
													 			  		  final X47BFindServicesForOrgDivisionServiceLocation findServicesProxy) {
		super(securityContextProvider,
			  modelObjectsMarshaller,
			  findServicesProxy);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  EXTENSION METHODS
/////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Return all locations in a service
	 * @param serviceOid
	 * @return
	 */
	public Collection<X47BOrgDivisionServiceLocation> findByOrgDivisionService(final X47BOrgDivisionServiceOID serviceOid) {
		FindResult<X47BOrgDivisionServiceLocation> findResult = this.getServiceProxyAs(X47BFindServicesForOrgDivisionServiceLocation.class)
																		.findByOrgDivisionService(this.getSecurityContext(),
																					   		  	  serviceOid);
		return findResult.getOrThrow();
	}
	/**
	 * Return summaries for all locations in a service
	 * @param serviceOid
	 * @param lang
	 * @return
	 */
	public Collection<X47BSummarizedOrgDivisionServiceLocation> findSummariesByOrgDivisionService(final X47BOrgDivisionServiceOID serviceOid,
																   				   final Language lang) {
		FindSummariesResult<X47BOrgDivisionServiceLocation> findResult = this.getServiceProxyAs(X47BFindServicesForOrgDivisionServiceLocation.class)
																				.findSummariesByOrgDivisionService(this.getSecurityContext(),
																							   			 		   serviceOid,
																							   			 		   lang);
		return findResult.getSummariesOrThrow();
	}
}
