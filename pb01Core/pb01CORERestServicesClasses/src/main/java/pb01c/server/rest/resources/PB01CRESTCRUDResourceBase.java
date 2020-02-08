package pb01c.server.rest.resources;


import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import pb01a.api.context.PB01ASecurityContext;
import pb01a.model.oids.PB01AOIDs.PB01APersistableObjectOID;
import r01f.model.PersistableModelObject;
import r01f.model.persistence.PersistenceException;
import r01f.rest.RESTResourceBase;
import r01f.rest.resources.delegates.RESTCRUDDelegateBase;
import r01f.rest.resources.delegates.RESTDelegate;

/**
 * This base type MUST be public... otherwise jersey throws a null exception WTF!
 * @param <O>
 * @param <M>
 */
public abstract class PB01CRESTCRUDResourceBase<O extends PB01APersistableObjectOID,M extends PersistableModelObject<O>>
		      extends RESTResourceBase {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01CRESTCRUDResourceBase(final RESTDelegate crudDelegate) {
		super(crudDelegate);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  CRUD
/////////////////////////////////////////////////////////////////////////////////////////
	@GET @Path("{oid: .+}")
	@Produces(MediaType.APPLICATION_XML)
	@SuppressWarnings({ "unchecked" })
	public Response load(@HeaderParam("securityContext")	final PB01ASecurityContext securityContext,
						 @PathParam("oid")  				final O oid) throws PersistenceException {
		return this.getDelegateAs(RESTCRUDDelegateBase.class)
						.load(securityContext,_req.getPathInfo(),
					 		  oid);
	}
	@POST
	@Produces(MediaType.APPLICATION_XML)
	@SuppressWarnings({ "unchecked" })
	public Response create(@HeaderParam("securityContext")  final PB01ASecurityContext securityContext,
						   @PathParam("oid") 	 		 	final O requestOid,
								 		 	 			 	final M modelObj) throws PersistenceException {
		return this.getDelegateAs(RESTCRUDDelegateBase.class)
						.create(securityContext,_req.getPathInfo(),
					 	  		modelObj);
	}
	@PUT @Path("{oid: .+}")
	@Produces(MediaType.APPLICATION_XML)
	@SuppressWarnings({ "unchecked" })
	public Response update(@HeaderParam("securityContext") 	 final PB01ASecurityContext securityContext,
						   @PathParam("oid") 	 		 	 final O oid,
							 	 		 	 			 	 final M modelObj) throws PersistenceException {
		return this.getDelegateAs(RESTCRUDDelegateBase.class)
						.update(securityContext,_req.getPathInfo(),
					 	  		modelObj);
	}
	@DELETE @Path("{oid: .+}")
	@Produces(MediaType.APPLICATION_XML)
	@SuppressWarnings({ "unchecked" })
	public Response delete(@HeaderParam("securityContext") 	final PB01ASecurityContext securityContext,
						   @PathParam("oid") 				final O oid) throws PersistenceException {
		return this.getDelegateAs(RESTCRUDDelegateBase.class)
						.delete(securityContext,_req.getPathInfo(),
					   		    oid);
	}
}
