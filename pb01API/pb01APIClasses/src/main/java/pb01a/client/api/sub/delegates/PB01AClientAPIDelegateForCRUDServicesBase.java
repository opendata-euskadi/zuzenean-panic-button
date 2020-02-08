package pb01a.client.api.sub.delegates;

import javax.inject.Provider;

import pb01a.api.interfaces.PB01ACRUDServicesBase;
import pb01a.model.oids.PB01AIDs.PB01APersistableObjectID;
import pb01a.model.oids.PB01AOIDs.PB01APersistableObjectOID;
import r01f.model.PersistableModelObject;
import r01f.model.persistence.CRUDResult;
import r01f.objectstreamer.Marshaller;
import r01f.securitycontext.SecurityContext;
import r01f.services.client.api.delegates.ClientAPIDelegateForModelObjectCRUDServices;

public class PB01AClientAPIDelegateForCRUDServicesBase<O extends PB01APersistableObjectOID,ID extends PB01APersistableObjectID<O>,M extends PersistableModelObject<O>>
	 extends ClientAPIDelegateForModelObjectCRUDServices<O,M> {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01AClientAPIDelegateForCRUDServicesBase(final Provider<SecurityContext> securityContextProvider,
													final Marshaller modelObjectsMarshaller,
												    final PB01ACRUDServicesBase<O,ID,M> crudServicesProxy) {
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
	@SuppressWarnings("unchecked")
	public M loadById(final ID id) {
		CRUDResult<M> opResult = this.getServiceProxyAs(PB01ACRUDServicesBase.class)
											.loadById(this.getSecurityContext(),
												   	  id);
		M outEntity = opResult.getOrThrow();
		return outEntity;
	}	
	@SuppressWarnings("unchecked")
	public M loadByIdOrNull(final ID id) {
		CRUDResult<M> opResult = this.getServiceProxyAs(PB01ACRUDServicesBase.class)
											.loadById(this.getSecurityContext(),
												   	  id);
		M outEntity = null;
		if (opResult.hasSucceeded()) {
			outEntity = opResult.getOrThrow();
		} else if (!opResult.asCRUDError()
							.wasBecauseClientRequestedEntityWasNOTFound()) {
			opResult.asCRUDError().throwAsPersistenceException();
		}
		return outEntity;
	}
}
