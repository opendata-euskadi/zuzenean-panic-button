package pb01a.client.api.sub.delegates;

import javax.inject.Provider;

import pb01a.api.interfaces.PB01AFindServicesBase;
import pb01a.model.oids.PB01AIDs.PB01APersistableObjectID;
import pb01a.model.oids.PB01AOIDs.PB01APersistableObjectOID;
import r01f.model.PersistableModelObject;
import r01f.objectstreamer.Marshaller;
import r01f.securitycontext.SecurityContext;
import r01f.services.client.api.delegates.ClientAPIDelegateForModelObjectFindServices;

public abstract class PB01AClientAPIDelegateForFindServicesBase<O extends PB01APersistableObjectOID,ID extends PB01APersistableObjectID<O>,
															   M extends PersistableModelObject<O>>
	 		  extends ClientAPIDelegateForModelObjectFindServices<O,M> {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01AClientAPIDelegateForFindServicesBase(final Provider<SecurityContext> securityContextProvider,
													final Marshaller modelObjectsMarshaller,
													final PB01AFindServicesBase<O,ID,M> findServicesProxy) {
		super(securityContextProvider,
			  modelObjectsMarshaller,
			  findServicesProxy);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  EXTENSION METHODS
/////////////////////////////////////////////////////////////////////////////////////////
}
