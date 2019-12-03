package x47b.server.rest.resources;


import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import r01f.model.PersistableModelObject;
import r01f.model.persistence.PersistenceException;
import r01f.rest.resources.delegates.RESTDelegate;
import x47b.api.context.X47BSecurityContext;
import x47b.model.oids.X47BIDs.X47BPersistableObjectID;
import x47b.model.oids.X47BOIDs.X47BPersistableObjectOID;
import x47b.server.rest.resources.delegates.X47BRESTCRUDDelegateBaseForEntity;

/**
 * This base type MUST be public... otherwise jersey throws a null exception WTF!
 * @param <O>
 * @param <M>
 */
public abstract class X47BRESTCRUDResourceBaseForEntity<O extends X47BPersistableObjectOID,ID extends X47BPersistableObjectID<O>,M extends PersistableModelObject<O>>
		      extends X47BRESTCRUDResourceBase<O,M> {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BRESTCRUDResourceBaseForEntity(final RESTDelegate crudDelegate) {
		super(crudDelegate);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  CRUD EXTENSIONS
/////////////////////////////////////////////////////////////////////////////////////////	
	@GET @Path("byId/{id: .+}")
	@Produces(MediaType.APPLICATION_XML)
	@SuppressWarnings("unchecked")
	public Response loadById(@HeaderParam("securityContext")	final X47BSecurityContext securityContext,
						 	 @PathParam("id")  				final ID id) throws PersistenceException {
		Response outResponse = this.getDelegateAs(X47BRESTCRUDDelegateBaseForEntity.class)
										.loadById(securityContext,_req.getPathInfo(),
												  id);
		return outResponse;
	}
}
