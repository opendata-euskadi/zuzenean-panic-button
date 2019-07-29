package x47b.api.interfaces;

import r01f.locale.Language;
import r01f.model.persistence.FindResult;
import r01f.model.persistence.FindSummariesResult;
import r01f.securitycontext.SecurityContext;
import r01f.services.interfaces.ExposedServiceInterface;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionServiceLocationID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceLocationOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceOID;
import x47b.model.org.X47BOrgDivisionServiceLocation;

@ExposedServiceInterface
public interface X47BFindServicesForOrgDivisionServiceLocation
         extends X47BFindServicesForOrganizationalModelObjectBase<X47BOrgDivisionServiceLocationOID,X47BOrgDivisionServiceLocationID,X47BOrgDivisionServiceLocation>,
         		 X47BPanicButtonServiceInterface {
	/**
	 * Return all locations in a service
	 * @param securityContext
	 * @param serviceOid
	 * @return
	 */
	public FindResult<X47BOrgDivisionServiceLocation> findByOrgDivisionService(final SecurityContext securityContext,
			  								    			 				   final X47BOrgDivisionServiceOID serviceOid);
	/**
	 * Returns summaries for all locations in a service
	 * @param securityContext
	 * @param serviceOid
	 * @param lang
	 * @return
	 */
	public FindSummariesResult<X47BOrgDivisionServiceLocation> findSummariesByOrgDivisionService(final SecurityContext securityContext,
																  			  	  		  		 final X47BOrgDivisionServiceOID serviceOid,
																  			  	  		  		 final Language lang);
}