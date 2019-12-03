package x47b.client.api.sub.delegates;

import javax.inject.Provider;

import r01f.objectstreamer.Marshaller;
import r01f.securitycontext.SecurityContext;
import x47b.api.interfaces.X47BCRUDServicesForOrganization;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrganizationID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrganizationOID;
import x47b.model.org.X47BOrganization;

public class X47BClientAPIDelegateForOrganizationCRUDServices
	 extends X47BClientAPIDelegateForOrganizationalEntityCRUDServicesBase<X47BOrganizationOID,X47BOrganizationID,X47BOrganization> {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BClientAPIDelegateForOrganizationCRUDServices(final Provider<SecurityContext> securityContextProvider,
															final Marshaller modelObjectsMarshaller,
															final X47BCRUDServicesForOrganization crudServicesProxy) {
		super(securityContextProvider,
			  modelObjectsMarshaller,
			  crudServicesProxy,
			  () -> X47BOrganizationOID.supply());	// oid factory
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
}
