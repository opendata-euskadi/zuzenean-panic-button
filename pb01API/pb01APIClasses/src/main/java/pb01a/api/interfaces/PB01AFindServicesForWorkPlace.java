package pb01a.api.interfaces;

import pb01a.model.oids.PB01AOrganizationalIDs.PB01AWorkPlaceID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionServiceLocationOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AWorkPlaceOID;
import pb01a.model.org.PB01AWorkPlace;
import r01f.locale.Language;
import r01f.model.persistence.FindResult;
import r01f.model.persistence.FindSummariesResult;
import r01f.securitycontext.SecurityContext;
import r01f.services.interfaces.ExposedServiceInterface;

@ExposedServiceInterface
public interface PB01AFindServicesForWorkPlace
         extends PB01AFindServicesForOrganizationalModelObjectBase<PB01AWorkPlaceOID,PB01AWorkPlaceID,PB01AWorkPlace>,
         		 PB01APanicButtonServiceInterface {
	/**
	 * Return all work places in one location.
	 * @param securityContext
	 * @param locOid
	 * @return
	 */
	public FindResult<PB01AWorkPlace> findByOrgDivisionServiceLocation(final SecurityContext securityContext,
			  								    					  final PB01AOrgDivisionServiceLocationOID locOid);
	/**
	 * Returns summaries for all work places in a location
	 * @param securityContext
	 * @param locOid
	 * @param lang
	 * @return
	 */
	public FindSummariesResult<PB01AWorkPlace> findSummariesByOrgDivisionServiceLocation(final SecurityContext securityContext,
																					    final PB01AOrgDivisionServiceLocationOID locOid,
																					    final Language lang);
}