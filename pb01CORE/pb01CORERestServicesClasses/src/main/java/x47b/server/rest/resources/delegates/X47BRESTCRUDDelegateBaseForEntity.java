package x47b.server.rest.resources.delegates;

import java.net.URI;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import r01f.model.PersistableModelObject;
import r01f.model.persistence.CRUDResult;
import r01f.model.persistence.PersistenceException;
import r01f.rest.RESTOperationsResponseBuilder;
import r01f.rest.resources.delegates.RESTCRUDDelegateBase;
import x47b.api.context.X47BSecurityContext;
import x47b.api.interfaces.X47BCRUDServicesBase;
import x47b.api.interfaces.X47BCRUDServicesBaseForModelObject;
import x47b.model.oids.X47BIDs.X47BPersistableObjectID;
import x47b.model.oids.X47BOIDs.X47BPersistableObjectOID;

public abstract class X47BRESTCRUDDelegateBaseForEntity<O extends X47BPersistableObjectOID,ID extends X47BPersistableObjectID<O>,M extends PersistableModelObject<O>>
	          extends RESTCRUDDelegateBase<O,M> {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BRESTCRUDDelegateBaseForEntity(final Class<M> modelObjectType,
											 final X47BCRUDServicesBase<O,ID,M> crudServices) {
		super(modelObjectType,
			  crudServices);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	@SuppressWarnings("unchecked")
	public Response loadById(final X47BSecurityContext securityContext,final String resourcePath,
						 	 final ID id) throws PersistenceException {
		CRUDResult<M> loadResult = this.getCRUDServicesAs(X47BCRUDServicesBaseForModelObject.class)
												.loadById(securityContext,
						  					     	 	  id);
		Response outResponse = RESTOperationsResponseBuilder.crudOn(_modelObjectType)
														    .at(URI.create(resourcePath))
														    .mediaType(MediaType.TEXT_XML_TYPE)
															.build(loadResult);
		return outResponse;
	}
}
