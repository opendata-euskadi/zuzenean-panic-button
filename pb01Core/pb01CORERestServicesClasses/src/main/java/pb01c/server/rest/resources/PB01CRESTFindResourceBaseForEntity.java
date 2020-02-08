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
import pb01c.server.rest.resources.delegates.PB01CRESTFindDelegateBaseForEntity;
import r01f.locale.Language;
import r01f.model.PersistableModelObject;
import r01f.model.persistence.PersistenceException;
import r01f.rest.resources.delegates.RESTDelegate;

/**
 * This base type MUST be public... otherwise jersey throws a null exception WTF!
 * @param <O>
 * @param <M>
 */
@SuppressWarnings("unused")
public abstract class PB01CRESTFindResourceBaseForEntity<O extends PB01APersistableObjectOID,ID extends PB01APersistableObjectID<O>,M extends PersistableModelObject<O>>
		      extends PB01CRESTFindResourceBase<O,M> {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01CRESTFindResourceBaseForEntity(final RESTDelegate persistenceDelegate) {
		super(persistenceDelegate);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  FIND
/////////////////////////////////////////////////////////////////////////////////////////
	@GET @Path("byNameIn/{lang}/{name}")
	@Produces(MediaType.APPLICATION_XML)
	public Response findByNameIn(@HeaderParam("securityContext")	final PB01ASecurityContext securityContext,
							 	 @PathParam("lang")				final Language lang,
							 	 @PathParam("name")  			final String name) throws PersistenceException {
		return this.getDelegateAs(PB01CRESTFindDelegateBaseForEntity.class)
						.findByNameIn(securityContext,_req.getPathInfo(),
									  lang,name);
	}
}
