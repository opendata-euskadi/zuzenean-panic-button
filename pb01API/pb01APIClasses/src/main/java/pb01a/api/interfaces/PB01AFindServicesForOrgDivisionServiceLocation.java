package pb01a.api.interfaces;

import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionServiceLocationID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionServiceLocationOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionServiceOID;
import pb01a.model.org.PB01AOrgDivisionServiceLocation;
import r01f.locale.Language;
import r01f.model.persistence.FindResult;
import r01f.model.persistence.FindSummariesResult;
import r01f.securitycontext.SecurityContext;
import r01f.services.interfaces.ExposedServiceInterface;

@ExposedServiceInterface
public interface PB01AFindServicesForOrgDivisionServiceLocation
         extends PB01AFindServicesForOrganizationalModelObjectBase<PB01AOrgDivisionServiceLocationOID,PB01AOrgDivisionServiceLocationID,PB01AOrgDivisionServiceLocation>,
         		 PB01APanicButtonServiceInterface {
	/**
	 * Return all locations in a service
	 * @param securityContext
	 * @param serviceOid
	 * @return
	 */
	public FindResult<PB01AOrgDivisionServiceLocation> findByOrgDivisionService(final SecurityContext securityContext,
			  								    			 				   final PB01AOrgDivisionServiceOID serviceOid);
	/**
	 * Returns summaries for all locations in a service
	 * @param securityContext
	 * @param serviceOid
	 * @param lang
	 * @return
	 */
	public FindSummariesResult<PB01AOrgDivisionServiceLocation> findSummariesByOrgDivisionService(final SecurityContext securityContext,
																  			  	  		  		 final PB01AOrgDivisionServiceOID serviceOid,
																  			  	  		  		 final Language lang);
}