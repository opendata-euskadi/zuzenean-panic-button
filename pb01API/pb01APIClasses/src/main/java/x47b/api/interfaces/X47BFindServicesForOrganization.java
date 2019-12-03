package x47b.api.interfaces;

import r01f.guids.OID;
import r01f.locale.Language;
import r01f.model.persistence.FindSummariesResult;
import r01f.securitycontext.SecurityContext;
import r01f.services.interfaces.ExposedServiceInterface;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrganizationID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrganizationOID;
import x47b.model.org.X47BOrganization;

@ExposedServiceInterface
public interface X47BFindServicesForOrganization
         extends X47BFindServicesForOrganizationalModelObjectBase<X47BOrganizationOID,X47BOrganizationID,X47BOrganization>,
         		 X47BPanicButtonServiceInterface {
	
	/**
	 * Returns all organizations summary that contains their {@link OID} and ID
	 * alongside their name in the provided language
	 * @param securityContext
	 * @param lang
	 * @return
	 */
	public FindSummariesResult<X47BOrganization> findSummaries(final SecurityContext securityContext,
															   final Language lang);
}