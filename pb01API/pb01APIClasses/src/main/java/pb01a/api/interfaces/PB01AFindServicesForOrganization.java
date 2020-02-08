package pb01a.api.interfaces;

import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrganizationID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrganizationOID;
import pb01a.model.org.PB01AOrganization;
import r01f.guids.OID;
import r01f.locale.Language;
import r01f.model.persistence.FindSummariesResult;
import r01f.securitycontext.SecurityContext;
import r01f.services.interfaces.ExposedServiceInterface;

@ExposedServiceInterface
public interface PB01AFindServicesForOrganization
         extends PB01AFindServicesForOrganizationalModelObjectBase<PB01AOrganizationOID,PB01AOrganizationID,PB01AOrganization>,
         		 PB01APanicButtonServiceInterface {
	
	/**
	 * Returns all organizations summary that contains their {@link OID} and ID
	 * alongside their name in the provided language
	 * @param securityContext
	 * @param lang
	 * @return
	 */
	public FindSummariesResult<PB01AOrganization> findSummaries(final SecurityContext securityContext,
															   final Language lang);
}