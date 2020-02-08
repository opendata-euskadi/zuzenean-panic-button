package pb01a.api.interfaces;

import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionServiceID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionServiceOID;
import pb01a.model.org.PB01AOrgDivisionService;
import r01f.locale.Language;
import r01f.model.persistence.FindResult;
import r01f.model.persistence.FindSummariesResult;
import r01f.securitycontext.SecurityContext;
import r01f.services.interfaces.ExposedServiceInterface;

@ExposedServiceInterface
public interface PB01AFindServicesForOrgDivisionService
         extends PB01AFindServicesForOrganizationalModelObjectBase<PB01AOrgDivisionServiceOID,PB01AOrgDivisionServiceID,PB01AOrgDivisionService>,
         		 PB01APanicButtonServiceInterface {
	/**
	 * Return all services in a division.
	 * @param securityContext
	 * @param divisionOid
	 * @return
	 */
	public FindResult<PB01AOrgDivisionService> findByOrgDivision(final SecurityContext securityContext,
			  								    			 	final PB01AOrgDivisionOID divisionOid);
	/**
	 * Returns summaries for all services in a division
	 * @param securityContext
	 * @param divisionOid
	 * @param lang
	 * @return
	 */
	public FindSummariesResult<PB01AOrgDivisionService> findSummariesByOrgDivision(final SecurityContext securityContext,
																  			  	  final PB01AOrgDivisionOID divisionOid,
																  			  	  final Language lang);
}