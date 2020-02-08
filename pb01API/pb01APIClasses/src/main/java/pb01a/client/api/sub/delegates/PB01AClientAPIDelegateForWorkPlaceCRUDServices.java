package pb01a.client.api.sub.delegates;

import javax.inject.Provider;

import pb01a.api.interfaces.PB01ACRUDServicesForWorkPlace;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AWorkPlaceID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AWorkPlaceOID;
import pb01a.model.org.PB01AWorkPlace;
import r01f.objectstreamer.Marshaller;
import r01f.securitycontext.SecurityContext;

public class PB01AClientAPIDelegateForWorkPlaceCRUDServices
	 extends PB01AClientAPIDelegateForOrganizationalEntityCRUDServicesBase<PB01AWorkPlaceOID,PB01AWorkPlaceID,PB01AWorkPlace> {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01AClientAPIDelegateForWorkPlaceCRUDServices(final Provider<SecurityContext> securityContextProvider,
														 final Marshaller modelObjectsMarshaller,
													     final PB01ACRUDServicesForWorkPlace crudServicesProxy) {
		super(securityContextProvider,
			  modelObjectsMarshaller,
			  crudServicesProxy,
			  () -> PB01AWorkPlaceOID.supply());
	}
}
