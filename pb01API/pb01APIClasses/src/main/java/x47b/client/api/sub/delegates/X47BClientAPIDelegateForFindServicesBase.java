package x47b.client.api.sub.delegates;

import javax.inject.Provider;

import r01f.model.PersistableModelObject;
import r01f.objectstreamer.Marshaller;
import r01f.securitycontext.SecurityContext;
import r01f.services.client.api.delegates.ClientAPIDelegateForModelObjectFindServices;
import x47b.api.interfaces.X47BFindServicesBase;
import x47b.model.oids.X47BIDs.X47BPersistableObjectID;
import x47b.model.oids.X47BOIDs.X47BPersistableObjectOID;

public abstract class X47BClientAPIDelegateForFindServicesBase<O extends X47BPersistableObjectOID,ID extends X47BPersistableObjectID<O>,
															   M extends PersistableModelObject<O>>
	 		  extends ClientAPIDelegateForModelObjectFindServices<O,M> {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BClientAPIDelegateForFindServicesBase(final Provider<SecurityContext> securityContextProvider,
													final Marshaller modelObjectsMarshaller,
													final X47BFindServicesBase<O,ID,M> findServicesProxy) {
		super(securityContextProvider,
			  modelObjectsMarshaller,
			  findServicesProxy);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  EXTENSION METHODS
/////////////////////////////////////////////////////////////////////////////////////////
}
