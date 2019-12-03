package x47b.rest.resources;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.Path;

import lombok.experimental.Accessors;
import x47b.api.interfaces.X47BSearchServicesForOrganizationalEntityObject;
import x47b.model.search.X47BSearchFilterForPanicButtonOrganizationalEntity;
import x47b.model.search.X47BSearchResultItemForPanicButtonOrganizationalEntity;
import x47b.rest.resources.delegates.X47BRESTSearchDelegate;
import x47b.server.rest.resources.X47BRESTResourceForSearchBase;

/**
 * The implementation must follow the HTTP 1.1 spec (http://www.w3.org/Protocols/rfc2616/rfc2616.html), specifically 
 * the section about the methods (GET, PUT, POST, DELETE...)
 * 
 * Log: see web.xml
 */
@Path("index")
@Singleton
@Accessors(prefix="_")
public class X47BRESTSearchResourceForEntityModelObject 
	 extends X47BRESTResourceForSearchBase<X47BSearchFilterForPanicButtonOrganizationalEntity,X47BSearchResultItemForPanicButtonOrganizationalEntity> {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public X47BRESTSearchResourceForEntityModelObject(final X47BSearchServicesForOrganizationalEntityObject searchServices) {
		super(new X47BRESTSearchDelegate(searchServices));
	}
}
