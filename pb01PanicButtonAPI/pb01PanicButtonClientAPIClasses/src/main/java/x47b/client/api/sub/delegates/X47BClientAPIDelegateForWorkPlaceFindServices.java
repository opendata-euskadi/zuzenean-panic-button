package x47b.client.api.sub.delegates;

import java.util.Collection;

import javax.inject.Provider;

import r01f.locale.Language;
import r01f.model.persistence.FindResult;
import r01f.model.persistence.FindSummariesResult;
import r01f.objectstreamer.Marshaller;
import r01f.securitycontext.SecurityContext;
import x47b.api.interfaces.X47BFindServicesForWorkPlace;
import x47b.model.oids.X47BOrganizationalIDs.X47BWorkPlaceID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceLocationOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BWorkPlaceOID;
import x47b.model.org.X47BWorkPlace;
import x47b.model.org.summaries.X47BSummarizedWorkPlace;

public class X47BClientAPIDelegateForWorkPlaceFindServices
	 extends X47BClientAPIDelegateForOrganizationalEntityFindServicesBase<X47BWorkPlaceOID,X47BWorkPlaceID,X47BWorkPlace> {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BClientAPIDelegateForWorkPlaceFindServices(final Provider<SecurityContext> securityContextProvider,
														 final Marshaller modelObjectsMarshaller,
													     final X47BFindServicesForWorkPlace findServicesProxy) {
		super(securityContextProvider,
			  modelObjectsMarshaller,
			  findServicesProxy);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  EXTENSION METHODS
/////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Return all work places in one location.
	 * @param locOid
	 * @return
	 */
	public Collection<X47BWorkPlace> findByOrgDivisionServiceLocation(final X47BOrgDivisionServiceLocationOID locOid) {
		FindResult<X47BWorkPlace> findResult = this.getServiceProxyAs(X47BFindServicesForWorkPlace.class)
													.findByOrgDivisionServiceLocation(this.getSecurityContext(),
																	locOid);
		return findResult.getOrThrow();
	}
	/**
	 * Return summaries for all work places in an location
	 * @param locOid
	 * @param lang
	 * @return
	 */
	public Collection<X47BSummarizedWorkPlace> findSummariesByOrgDivisionServiceLocation(final X47BOrgDivisionServiceLocationOID locOid,
																   						 final Language lang) {
		FindSummariesResult<X47BWorkPlace> findResult = this.getServiceProxyAs(X47BFindServicesForWorkPlace.class)
																.findSummariesByOrgDivisionServiceLocation(this.getSecurityContext(),
																			   			 locOid,
																			   			 lang);
		return findResult.getOrThrow();
	}
}
