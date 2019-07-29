package x47b.client.api.sub.delegates;

import javax.inject.Provider;

import r01f.model.PersistableModelObject;
import r01f.model.persistence.CRUDResult;
import r01f.objectstreamer.Marshaller;
import r01f.securitycontext.SecurityContext;
import r01f.services.client.api.delegates.ClientAPIDelegateForModelObjectCRUDServices;
import x47b.api.interfaces.X47BCRUDServicesBaseForModelObject;
import x47b.model.oids.X47BIDs.X47BModelObjectID;
import x47b.model.oids.X47BOIDs.X47BPersistableObjectOID;

public class X47BClientAPIDelegateBaseForModelObjectCRUDServices<O extends X47BPersistableObjectOID,ID extends X47BModelObjectID<O>,M extends PersistableModelObject<O>>
	 extends ClientAPIDelegateForModelObjectCRUDServices<O,M> {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BClientAPIDelegateBaseForModelObjectCRUDServices(final Provider<SecurityContext> securityContextProvider,
														  	   final Marshaller modelObjectsMarshaller,
														  	   final X47BCRUDServicesBaseForModelObject<O,ID,M> crudServicesProxy) {
		super(securityContextProvider,
			  modelObjectsMarshaller,
			  crudServicesProxy);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Loads an entity by it's id
	 * @param id
	 * @return
	 */
	public M loadById(final ID id) {
		CRUDResult<M> opResult = this.getServiceProxyAs(X47BCRUDServicesBaseForModelObject.class)
											.loadById(this.getSecurityContext(),
												   	  id);
		M outEntity = opResult.getOrThrow();
		return outEntity;
	}
}
