package x47b.server.rest.resources;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import r01f.locale.Language;
import r01f.model.PersistableModelObject;
import r01f.model.persistence.PersistenceException;
import r01f.rest.resources.delegates.RESTDelegate;
import x47b.api.context.X47BSecurityContext;
import x47b.model.oids.X47BIDs.X47BPersistableObjectID;
import x47b.model.oids.X47BOIDs.X47BPersistableObjectOID;
import x47b.server.rest.resources.delegates.X47BRESTFindDelegateBaseForEntity;

/**
 * This base type MUST be public... otherwise jersey throws a null exception WTF!
 * @param <O>
 * @param <M>
 */
@SuppressWarnings("unused")
public abstract class X47BRESTFindResourceBaseForEntity<O extends X47BPersistableObjectOID,ID extends X47BPersistableObjectID<O>,M extends PersistableModelObject<O>>
		      extends X47BRESTFindResourceBase<O,M> {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BRESTFindResourceBaseForEntity(final RESTDelegate persistenceDelegate) {
		super(persistenceDelegate);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  FIND
/////////////////////////////////////////////////////////////////////////////////////////
	@GET @Path("byNameIn/{lang}/{name}")
	@Produces(MediaType.APPLICATION_XML)
	public Response findByNameIn(@HeaderParam("securityContext")	final X47BSecurityContext securityContext,
							 	 @PathParam("lang")				final Language lang,
							 	 @PathParam("name")  			final String name) throws PersistenceException {
		return this.getDelegateAs(X47BRESTFindDelegateBaseForEntity.class)
						.findByNameIn(securityContext,_req.getPathInfo(),
									  lang,name);
	}
}
