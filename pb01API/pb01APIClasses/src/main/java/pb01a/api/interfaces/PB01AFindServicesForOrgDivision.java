package pb01a.api.interfaces;

import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrganizationOID;
import pb01a.model.org.PB01AOrgDivision;
import r01f.locale.Language;
import r01f.model.persistence.FindResult;
import r01f.model.persistence.FindSummariesResult;
import r01f.securitycontext.SecurityContext;
import r01f.services.interfaces.ExposedServiceInterface;

@ExposedServiceInterface
public interface PB01AFindServicesForOrgDivision
         extends PB01AFindServicesForOrganizationalModelObjectBase<PB01AOrgDivisionOID,PB01AOrgDivisionID,PB01AOrgDivision>,
         		 PB01APanicButtonServiceInterface {
	/**
	 * Return all divisions belonging to an organization
	 * @param securityContext
	 * @param orgOid
	 * @return
	 */
	public FindResult<PB01AOrgDivision> findByOrganization(final SecurityContext securityContext,
			  								  		   	  final PB01AOrganizationOID orgOid);
	
	/**
	 * Returns summaries for all divisions belonging to an organization
	 * @param securityContext
	 * @param orgOid
	 * @param lang
	 * @return
	 */
	public FindSummariesResult<PB01AOrgDivision> findSummariesByOrganization(final SecurityContext securityContext,
																		 	final PB01AOrganizationOID orgOid,
																		 	final Language lang);	
}