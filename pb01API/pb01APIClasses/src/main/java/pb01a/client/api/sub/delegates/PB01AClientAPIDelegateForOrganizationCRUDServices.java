package pb01a.client.api.sub.delegates;

import javax.inject.Provider;

import pb01a.api.interfaces.PB01ACRUDServicesForOrganization;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrganizationID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrganizationOID;
import pb01a.model.org.PB01AOrganization;
import r01f.objectstreamer.Marshaller;
import r01f.securitycontext.SecurityContext;

public class PB01AClientAPIDelegateForOrganizationCRUDServices
	 extends PB01AClientAPIDelegateForOrganizationalEntityCRUDServicesBase<PB01AOrganizationOID,PB01AOrganizationID,PB01AOrganization> {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01AClientAPIDelegateForOrganizationCRUDServices(final Provider<SecurityContext> securityContextProvider,
															final Marshaller modelObjectsMarshaller,
															final PB01ACRUDServicesForOrganization crudServicesProxy) {
		super(securityContextProvider,
			  modelObjectsMarshaller,
			  crudServicesProxy,
			  () -> PB01AOrganizationOID.supply());	// oid factory
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
}
