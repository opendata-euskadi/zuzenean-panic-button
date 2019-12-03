package x47b.server.rest.resources;


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

import r01f.model.PersistableModelObject;
import r01f.model.persistence.PersistenceException;
import r01f.rest.RESTResourceBase;
import r01f.rest.resources.delegates.RESTCRUDDelegateBase;
import r01f.rest.resources.delegates.RESTDelegate;
import x47b.api.context.X47BSecurityContext;
import x47b.model.oids.X47BOIDs.X47BPersistableObjectOID;

/**
 * This base type MUST be public... otherwise jersey throws a null exception WTF!
 * @param <O>
 * @param <M>
 */
public abstract class X47BRESTCRUDResourceBase<O extends X47BPersistableObjectOID,M extends PersistableModelObject<O>>
		      extends RESTResourceBase {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BRESTCRUDResourceBase(final RESTDelegate crudDelegate) {
		super(crudDelegate);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  CRUD
/////////////////////////////////////////////////////////////////////////////////////////
	@GET @Path("{oid: .+}")
	@Produces(MediaType.APPLICATION_XML)
	@SuppressWarnings({ "unchecked" })
	public Response load(@HeaderParam("securityContext")	final X47BSecurityContext securityContext,
						 @PathParam("oid")  				final O oid) throws PersistenceException {
		return this.getDelegateAs(RESTCRUDDelegateBase.class)
						.load(securityContext,_req.getPathInfo(),
					 		  oid);
	}
	@POST
	@Produces(MediaType.APPLICATION_XML)
	@SuppressWarnings({ "unchecked" })
	public Response create(@HeaderParam("securityContext")  final X47BSecurityContext securityContext,
						   @PathParam("oid") 	 		 	final O requestOid,
								 		 	 			 	final M modelObj) throws PersistenceException {
		return this.getDelegateAs(RESTCRUDDelegateBase.class)
						.create(securityContext,_req.getPathInfo(),
					 	  		modelObj);
	}
	@PUT @Path("{oid: .+}")
	@Produces(MediaType.APPLICATION_XML)
	@SuppressWarnings({ "unchecked" })
	public Response update(@HeaderParam("securityContext") 	 final X47BSecurityContext securityContext,
						   @PathParam("oid") 	 		 	 final O oid,
							 	 		 	 			 	 final M modelObj) throws PersistenceException {
		return this.getDelegateAs(RESTCRUDDelegateBase.class)
						.update(securityContext,_req.getPathInfo(),
					 	  		modelObj);
	}
	@DELETE @Path("{oid: .+}")
	@Produces(MediaType.APPLICATION_XML)
	@SuppressWarnings({ "unchecked" })
	public Response delete(@HeaderParam("securityContext") 	final X47BSecurityContext securityContext,
						   @PathParam("oid") 				final O oid) throws PersistenceException {
		return this.getDelegateAs(RESTCRUDDelegateBase.class)
						.delete(securityContext,_req.getPathInfo(),
					   		    oid);
	}
}
