package x47b.api.interfaces;

import r01f.locale.Language;
import r01f.model.persistence.FindResult;
import r01f.model.persistence.FindSummariesResult;
import r01f.securitycontext.SecurityContext;
import r01f.services.interfaces.ExposedServiceInterface;
import x47b.model.oids.X47BOrganizationalIDs.X47BWorkPlaceID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceLocationOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BWorkPlaceOID;
import x47b.model.org.X47BWorkPlace;

@ExposedServiceInterface
public interface X47BFindServicesForWorkPlace
         extends X47BFindServicesForOrganizationalModelObjectBase<X47BWorkPlaceOID,X47BWorkPlaceID,X47BWorkPlace>,
         		 X47BPanicButtonServiceInterface {
	/**
	 * Return all work places in one location.
	 * @param securityContext
	 * @param locOid
	 * @return
	 */
	public FindResult<X47BWorkPlace> findByOrgDivisionServiceLocation(final SecurityContext securityContext,
			  								    					  final X47BOrgDivisionServiceLocationOID locOid);
	/**
	 * Returns summaries for all work places in a location
	 * @param securityContext
	 * @param locOid
	 * @param lang
	 * @return
	 */
	public FindSummariesResult<X47BWorkPlace> findSummariesByOrgDivisionServiceLocation(final SecurityContext securityContext,
																					    final X47BOrgDivisionServiceLocationOID locOid,
																					    final Language lang);
}