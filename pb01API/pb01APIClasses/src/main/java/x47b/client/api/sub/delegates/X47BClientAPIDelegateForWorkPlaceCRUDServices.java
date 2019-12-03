package x47b.client.api.sub.delegates;

import javax.inject.Provider;

import r01f.objectstreamer.Marshaller;
import r01f.securitycontext.SecurityContext;
import x47b.api.interfaces.X47BCRUDServicesForWorkPlace;
import x47b.model.oids.X47BOrganizationalIDs.X47BWorkPlaceID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BWorkPlaceOID;
import x47b.model.org.X47BWorkPlace;

public class X47BClientAPIDelegateForWorkPlaceCRUDServices
	 extends X47BClientAPIDelegateForOrganizationalEntityCRUDServicesBase<X47BWorkPlaceOID,X47BWorkPlaceID,X47BWorkPlace> {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BClientAPIDelegateForWorkPlaceCRUDServices(final Provider<SecurityContext> securityContextProvider,
														 final Marshaller modelObjectsMarshaller,
													     final X47BCRUDServicesForWorkPlace crudServicesProxy) {
		super(securityContextProvider,
			  modelObjectsMarshaller,
			  crudServicesProxy,
			  () -> X47BWorkPlaceOID.supply());
	}
}
