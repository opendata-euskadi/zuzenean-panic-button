package pb01c.server.rest.resources.delegates;

import java.net.URI;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import pb01a.api.context.PB01ASecurityContext;
import pb01a.api.interfaces.PB01ACRUDServicesBase;
import pb01a.api.interfaces.PB01ACRUDServicesBaseForModelObject;
import pb01a.model.oids.PB01AIDs.PB01APersistableObjectID;
import pb01a.model.oids.PB01AOIDs.PB01APersistableObjectOID;
import r01f.model.PersistableModelObject;
import r01f.model.persistence.CRUDResult;
import r01f.model.persistence.PersistenceException;
import r01f.rest.RESTOperationsResponseBuilder;
import r01f.rest.resources.delegates.RESTCRUDDelegateBase;

public abstract class PB01CRESTCRUDDelegateBaseForEntity<O extends PB01APersistableObjectOID,ID extends PB01APersistableObjectID<O>,M extends PersistableModelObject<O>>
	          extends RESTCRUDDelegateBase<O,M> {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01CRESTCRUDDelegateBaseForEntity(final Class<M> modelObjectType,
											 final PB01ACRUDServicesBase<O,ID,M> crudServices) {
		super(modelObjectType,
			  crudServices);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	@SuppressWarnings("unchecked")
	public Response loadById(final PB01ASecurityContext securityContext,final String resourcePath,
						 	 final ID id) throws PersistenceException {
		CRUDResult<M> loadResult = this.getCRUDServicesAs(PB01ACRUDServicesBaseForModelObject.class)
												.loadById(securityContext,
						  					     	 	  id);
		Response outResponse = RESTOperationsResponseBuilder.crudOn(_modelObjectType)
														    .at(URI.create(resourcePath))
														    .mediaType(MediaType.TEXT_XML_TYPE)
															.build(loadResult);
		return outResponse;
	}
}
