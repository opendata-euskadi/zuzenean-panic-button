package x47b.api.interfaces;

import r01f.locale.Language;
import r01f.model.persistence.FindResult;
import r01f.model.persistence.FindSummariesResult;
import r01f.securitycontext.SecurityContext;
import r01f.services.interfaces.ExposedServiceInterface;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrganizationOID;
import x47b.model.org.X47BOrgDivision;

@ExposedServiceInterface
public interface X47BFindServicesForOrgDivision
         extends X47BFindServicesForOrganizationalModelObjectBase<X47BOrgDivisionOID,X47BOrgDivisionID,X47BOrgDivision>,
         		 X47BPanicButtonServiceInterface {
	/**
	 * Return all divisions belonging to an organization
	 * @param securityContext
	 * @param orgOid
	 * @return
	 */
	public FindResult<X47BOrgDivision> findByOrganization(final SecurityContext securityContext,
			  								  		   	  final X47BOrganizationOID orgOid);
	
	/**
	 * Returns summaries for all divisions belonging to an organization
	 * @param securityContext
	 * @param orgOid
	 * @param lang
	 * @return
	 */
	public FindSummariesResult<X47BOrgDivision> findSummariesByOrganization(final SecurityContext securityContext,
																		 	final X47BOrganizationOID orgOid,
																		 	final Language lang);	
}