package x47b.client.api.sub.delegates;

import javax.inject.Provider;

import r01f.objectstreamer.Marshaller;
import r01f.securitycontext.SecurityContext;
import x47b.api.interfaces.X47BCRUDServicesForOrgDivisionService;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionServiceID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceOID;
import x47b.model.org.X47BOrgDivisionService;

public class X47BClientAPIDelegateForOrgDivisionServiceCRUDServices
	 extends X47BClientAPIDelegateForOrganizationalEntityCRUDServicesBase<X47BOrgDivisionServiceOID,X47BOrgDivisionServiceID,X47BOrgDivisionService> {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BClientAPIDelegateForOrgDivisionServiceCRUDServices(final Provider<SecurityContext> securityContextProvider,
																  final Marshaller modelObjectsMarshaller,
															 	  final X47BCRUDServicesForOrgDivisionService crudServicesProxy) {
		super(securityContextProvider,
			  modelObjectsMarshaller,
			  crudServicesProxy);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
}
