package pb01c.server.rest.resources;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import lombok.experimental.Accessors;
import pb01a.api.context.PB01ASecurityContext;
import pb01a.model.oids.PB01AIDs.PB01APersistableObjectID;
import pb01a.model.oids.PB01AOIDs.PB01APersistableObjectOID;
import pb01c.server.rest.resources.delegates.PB01CRESTFindDelegateBaseForOrganizationalEntity;
import r01f.locale.Language;
import r01f.model.PersistableModelObject;
import r01f.model.persistence.PersistenceException;
import r01f.rest.resources.delegates.RESTDelegate;

/**
 * The implementation must follow the HTTP 1.1 spec (http://www.w3.org/Protocols/rfc2616/rfc2616.html), specifically
 * the section about the methods (GET, PUT, POST, DELETE...)
 *
 * Log: see web.xml
 */
@Accessors(prefix="_")
public class PB01CRESTFindResourceBaseForOrganizationalEntity<O extends PB01APersistableObjectOID,ID extends PB01APersistableObjectID<O>,
									  						 M extends PersistableModelObject<O>>
	 extends PB01CRESTFindResourceBaseForEntity<O,ID,M> {
/////////////////////////////////////////////////////////////////////////////////////////
//  
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01CRESTFindResourceBaseForOrganizationalEntity(final RESTDelegate persistenceDelegate) {
		super(persistenceDelegate);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  
/////////////////////////////////////////////////////////////////////////////////////////
	@GET @Path("byName")	
	@Produces(MediaType.APPLICATION_XML)
	public Response findByNameIn(@HeaderParam("securityContext")	final PB01ASecurityContext securityContext,
							 	 @QueryParam("lang")			final Language lang,
							 	 @QueryParam("name")			final String name) throws PersistenceException {
		return this.getDelegateAs(PB01CRESTFindDelegateBaseForOrganizationalEntity.class)
						.findByNameIn(securityContext,_req.getPathInfo(),
									  lang,
									  name);
	}
}
