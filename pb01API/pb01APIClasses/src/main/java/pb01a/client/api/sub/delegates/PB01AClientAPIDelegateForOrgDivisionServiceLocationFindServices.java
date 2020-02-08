package pb01a.client.api.sub.delegates;

import java.util.Collection;

import javax.inject.Provider;

import pb01a.api.interfaces.PB01AFindServicesForOrgDivisionServiceLocation;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionServiceLocationID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionServiceLocationOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionServiceOID;
import pb01a.model.org.PB01AOrgDivisionServiceLocation;
import pb01a.model.org.summaries.PB01ASummarizedOrgDivisionServiceLocation;
import r01f.locale.Language;
import r01f.model.persistence.FindResult;
import r01f.model.persistence.FindSummariesResult;
import r01f.objectstreamer.Marshaller;
import r01f.securitycontext.SecurityContext;

public class PB01AClientAPIDelegateForOrgDivisionServiceLocationFindServices
	 extends PB01AClientAPIDelegateForOrganizationalEntityFindServicesBase<PB01AOrgDivisionServiceLocationOID,PB01AOrgDivisionServiceLocationID,PB01AOrgDivisionServiceLocation> {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01AClientAPIDelegateForOrgDivisionServiceLocationFindServices(final Provider<SecurityContext> securityContextProvider,
																		  final Marshaller modelObjectsMarshaller,
													 			  		  final PB01AFindServicesForOrgDivisionServiceLocation findServicesProxy) {
		super(securityContextProvider,
			  modelObjectsMarshaller,
			  findServicesProxy);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  EXTENSION METHODS
/////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Return all locations in a service
	 * @param serviceOid
	 * @return
	 */
	public Collection<PB01AOrgDivisionServiceLocation> findByOrgDivisionService(final PB01AOrgDivisionServiceOID serviceOid) {
		FindResult<PB01AOrgDivisionServiceLocation> findResult = this.getServiceProxyAs(PB01AFindServicesForOrgDivisionServiceLocation.class)
																		.findByOrgDivisionService(this.getSecurityContext(),
																					   		  	  serviceOid);
		return findResult.getOrThrow();
	}
	/**
	 * Return summaries for all locations in a service
	 * @param serviceOid
	 * @param lang
	 * @return
	 */
	public Collection<PB01ASummarizedOrgDivisionServiceLocation> findSummariesByOrgDivisionService(final PB01AOrgDivisionServiceOID serviceOid,
																   				   final Language lang) {
		FindSummariesResult<PB01AOrgDivisionServiceLocation> findResult = this.getServiceProxyAs(PB01AFindServicesForOrgDivisionServiceLocation.class)
																				.findSummariesByOrgDivisionService(this.getSecurityContext(),
																							   			 		   serviceOid,
																							   			 		   lang);
		return findResult.getSummariesOrThrow();
	}
}
