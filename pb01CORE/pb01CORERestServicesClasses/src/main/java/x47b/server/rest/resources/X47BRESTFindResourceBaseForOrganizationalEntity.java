package x47b.server.rest.resources;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import lombok.experimental.Accessors;
import r01f.locale.Language;
import r01f.model.PersistableModelObject;
import r01f.model.persistence.PersistenceException;
import r01f.rest.resources.delegates.RESTDelegate;
import x47b.api.context.X47BSecurityContext;
import x47b.model.oids.X47BIDs.X47BPersistableObjectID;
import x47b.model.oids.X47BOIDs.X47BPersistableObjectOID;
import x47b.server.rest.resources.delegates.X47BRESTFindDelegateBaseForOrganizationalEntity;

/**
 * The implementation must follow the HTTP 1.1 spec (http://www.w3.org/Protocols/rfc2616/rfc2616.html), specifically
 * the section about the methods (GET, PUT, POST, DELETE...)
 *
 * Log: see web.xml
 */
@Accessors(prefix="_")
public class X47BRESTFindResourceBaseForOrganizationalEntity<O extends X47BPersistableObjectOID,ID extends X47BPersistableObjectID<O>,
									  						 M extends PersistableModelObject<O>>
	 extends X47BRESTFindResourceBaseForEntity<O,ID,M> {
/////////////////////////////////////////////////////////////////////////////////////////
//  
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BRESTFindResourceBaseForOrganizationalEntity(final RESTDelegate persistenceDelegate) {
		super(persistenceDelegate);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  
/////////////////////////////////////////////////////////////////////////////////////////
	@GET @Path("byName")	
	@Produces(MediaType.APPLICATION_XML)
	public Response findByNameIn(@HeaderParam("securityContext")	final X47BSecurityContext securityContext,
							 	 @QueryParam("lang")			final Language lang,
							 	 @QueryParam("name")			final String name) throws PersistenceException {
		return this.getDelegateAs(X47BRESTFindDelegateBaseForOrganizationalEntity.class)
						.findByNameIn(securityContext,_req.getPathInfo(),
									  lang,
									  name);
	}
}
