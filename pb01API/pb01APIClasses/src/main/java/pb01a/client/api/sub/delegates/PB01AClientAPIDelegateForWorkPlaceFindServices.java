package pb01a.client.api.sub.delegates;

import java.util.Collection;

import javax.inject.Provider;

import pb01a.api.interfaces.PB01AFindServicesForWorkPlace;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AWorkPlaceID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionServiceLocationOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AWorkPlaceOID;
import pb01a.model.org.PB01AWorkPlace;
import pb01a.model.org.summaries.PB01ASummarizedWorkPlace;
import r01f.locale.Language;
import r01f.model.persistence.FindResult;
import r01f.model.persistence.FindSummariesResult;
import r01f.objectstreamer.Marshaller;
import r01f.securitycontext.SecurityContext;

public class PB01AClientAPIDelegateForWorkPlaceFindServices
	 extends PB01AClientAPIDelegateForOrganizationalEntityFindServicesBase<PB01AWorkPlaceOID,PB01AWorkPlaceID,PB01AWorkPlace> {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01AClientAPIDelegateForWorkPlaceFindServices(final Provider<SecurityContext> securityContextProvider,
														 final Marshaller modelObjectsMarshaller,
													     final PB01AFindServicesForWorkPlace findServicesProxy) {
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
	public Collection<PB01AWorkPlace> findByOrgDivisionServiceLocation(final PB01AOrgDivisionServiceLocationOID locOid) {
		FindResult<PB01AWorkPlace> findResult = this.getServiceProxyAs(PB01AFindServicesForWorkPlace.class)
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
	public Collection<PB01ASummarizedWorkPlace> findSummariesByOrgDivisionServiceLocation(final PB01AOrgDivisionServiceLocationOID locOid,
																   						 final Language lang) {
		FindSummariesResult<PB01AWorkPlace> findResult = this.getServiceProxyAs(PB01AFindServicesForWorkPlace.class)
																.findSummariesByOrgDivisionServiceLocation(this.getSecurityContext(),
																			   			 locOid,
																			   			 lang);
		return findResult.getSummariesOrThrow();
	}
}
