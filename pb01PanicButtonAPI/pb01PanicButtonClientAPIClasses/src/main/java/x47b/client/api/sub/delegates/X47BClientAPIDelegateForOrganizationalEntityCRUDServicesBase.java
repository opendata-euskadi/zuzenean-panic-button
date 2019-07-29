package x47b.client.api.sub.delegates;

import javax.inject.Provider;

import r01f.model.PersistableModelObject;
import r01f.objectstreamer.Marshaller;
import r01f.securitycontext.SecurityContext;
import x47b.api.interfaces.X47BCRUDServicesForOrganizationalModelObjectBase;
import x47b.model.oids.X47BIDs.X47BModelObjectID;
import x47b.model.oids.X47BOIDs.X47BPersistableObjectOID;

public class X47BClientAPIDelegateForOrganizationalEntityCRUDServicesBase<O extends X47BPersistableObjectOID,ID extends X47BModelObjectID<O>,M extends PersistableModelObject<O>>
	 extends X47BClientAPIDelegateForCRUDServicesBase<O,ID,M> {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BClientAPIDelegateForOrganizationalEntityCRUDServicesBase(final Provider<SecurityContext> securityContextProvider,
																		final Marshaller modelObjectsMarshaller,
														  				final X47BCRUDServicesForOrganizationalModelObjectBase<O,ID,M> crudServicesProxy) {
		super(securityContextProvider,
			  modelObjectsMarshaller,
			  crudServicesProxy);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////	
}
