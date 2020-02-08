package pb01a.client.api.sub.delegates;

import javax.inject.Provider;

import pb01a.api.interfaces.PB01ACRUDServicesBaseForModelObject;
import pb01a.model.oids.PB01AIDs.PB01APersistableObjectID;
import pb01a.model.oids.PB01AOIDs.PB01APersistableObjectOID;
import r01f.model.PersistableModelObject;
import r01f.model.persistence.CRUDResult;
import r01f.objectstreamer.Marshaller;
import r01f.securitycontext.SecurityContext;
import r01f.services.client.api.delegates.ClientAPIDelegateForModelObjectCRUDServices;

public class PB01AClientAPIDelegateBaseForModelObjectCRUDServices<O extends PB01APersistableObjectOID,ID extends PB01APersistableObjectID<O>,M extends PersistableModelObject<O>>
	 extends ClientAPIDelegateForModelObjectCRUDServices<O,M> {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01AClientAPIDelegateBaseForModelObjectCRUDServices(final Provider<SecurityContext> securityContextProvider,
														  	   final Marshaller modelObjectsMarshaller,
														  	   final PB01ACRUDServicesBaseForModelObject<O,ID,M> crudServicesProxy) {
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
		CRUDResult<M> opResult = this.getServiceProxyAs(PB01ACRUDServicesBaseForModelObject.class)
											.loadById(this.getSecurityContext(),
												   	  id);
		M outEntity = opResult.getOrThrow();
		return outEntity;
	}
}
