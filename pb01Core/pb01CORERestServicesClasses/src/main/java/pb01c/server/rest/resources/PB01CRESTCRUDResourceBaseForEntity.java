package pb01c.server.rest.resources;


import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import pb01a.api.context.PB01ASecurityContext;
import pb01a.model.oids.PB01AIDs.PB01APersistableObjectID;
import pb01a.model.oids.PB01AOIDs.PB01APersistableObjectOID;
import pb01c.server.rest.resources.delegates.PB01CRESTCRUDDelegateBaseForEntity;
import r01f.model.PersistableModelObject;
import r01f.model.persistence.PersistenceException;
import r01f.rest.resources.delegates.RESTDelegate;

/**
 * This base type MUST be public... otherwise jersey throws a null exception WTF!
 * @param <O>
 * @param <M>
 */
public abstract class PB01CRESTCRUDResourceBaseForEntity<O extends PB01APersistableObjectOID,ID extends PB01APersistableObjectID<O>,M extends PersistableModelObject<O>>
		      extends PB01CRESTCRUDResourceBase<O,M> {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01CRESTCRUDResourceBaseForEntity(final RESTDelegate crudDelegate) {
		super(crudDelegate);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  CRUD EXTENSIONS
/////////////////////////////////////////////////////////////////////////////////////////	
	@GET @Path("byId/{id: .+}")
	@Produces(MediaType.APPLICATION_XML)
	@SuppressWarnings("unchecked")
	public Response loadById(@HeaderParam("securityContext")	final PB01ASecurityContext securityContext,
						 	 @PathParam("id")  				final ID id) throws PersistenceException {
		Response outResponse = this.getDelegateAs(PB01CRESTCRUDDelegateBaseForEntity.class)
										.loadById(securityContext,_req.getPathInfo(),
												  id);
		return outResponse;
	}
}
