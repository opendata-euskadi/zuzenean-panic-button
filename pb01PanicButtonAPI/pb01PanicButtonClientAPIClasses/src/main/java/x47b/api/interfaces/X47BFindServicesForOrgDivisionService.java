package x47b.api.interfaces;

import r01f.locale.Language;
import r01f.model.persistence.FindResult;
import r01f.model.persistence.FindSummariesResult;
import r01f.securitycontext.SecurityContext;
import r01f.services.interfaces.ExposedServiceInterface;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionServiceID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceOID;
import x47b.model.org.X47BOrgDivisionService;

@ExposedServiceInterface
public interface X47BFindServicesForOrgDivisionService
         extends X47BFindServicesForOrganizationalModelObjectBase<X47BOrgDivisionServiceOID,X47BOrgDivisionServiceID,X47BOrgDivisionService>,
         		 X47BPanicButtonServiceInterface {
	/**
	 * Return all services in a division.
	 * @param securityContext
	 * @param divisionOid
	 * @return
	 */
	public FindResult<X47BOrgDivisionService> findByOrgDivision(final SecurityContext securityContext,
			  								    			 	final X47BOrgDivisionOID divisionOid);
	/**
	 * Returns summaries for all services in a division
	 * @param securityContext
	 * @param divisionOid
	 * @param lang
	 * @return
	 */
	public FindSummariesResult<X47BOrgDivisionService> findSummariesByOrgDivision(final SecurityContext securityContext,
																  			  	  final X47BOrgDivisionOID divisionOid,
																  			  	  final Language lang);
}