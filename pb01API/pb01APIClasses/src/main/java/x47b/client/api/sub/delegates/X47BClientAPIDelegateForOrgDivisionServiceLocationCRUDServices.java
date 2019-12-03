package x47b.client.api.sub.delegates;

import javax.inject.Provider;

import r01f.objectstreamer.Marshaller;
import r01f.securitycontext.SecurityContext;
import x47b.api.interfaces.X47BCRUDServicesForOrgDivisionServiceLocation;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionServiceLocationID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceLocationOID;
import x47b.model.org.X47BOrgDivisionServiceLocation;

public class X47BClientAPIDelegateForOrgDivisionServiceLocationCRUDServices
	 extends X47BClientAPIDelegateForOrganizationalEntityCRUDServicesBase<X47BOrgDivisionServiceLocationOID,X47BOrgDivisionServiceLocationID,X47BOrgDivisionServiceLocation> {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BClientAPIDelegateForOrgDivisionServiceLocationCRUDServices(final Provider<SecurityContext> securityContextProvider,
																		  final Marshaller modelObjectsMarshaller,
															 			  final X47BCRUDServicesForOrgDivisionServiceLocation crudServicesProxy) {
		super(securityContextProvider,
			  modelObjectsMarshaller,
			  crudServicesProxy,
			  () -> X47BOrgDivisionServiceLocationOID.supply());
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
}
