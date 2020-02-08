package pb01c.rest.resources;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.Path;

import lombok.experimental.Accessors;
import pb01a.api.interfaces.PB01ASearchServicesForOrganizationalEntityObject;
import pb01a.model.search.PB01ASearchFilterForPanicButtonOrganizationalEntity;
import pb01a.model.search.PB01ASearchResultItemForPanicButtonOrganizationalEntity;
import pb01c.rest.resources.delegates.PB01CRESTSearchDelegate;
import pb01c.server.rest.resources.PB01CRESTResourceForSearchBase;

/**
 * The implementation must follow the HTTP 1.1 spec (http://www.w3.org/Protocols/rfc2616/rfc2616.html), specifically 
 * the section about the methods (GET, PUT, POST, DELETE...)
 * 
 * Log: see web.xml
 */
@Path("index")
@Singleton
@Accessors(prefix="_")
public class PB01CRESTSearchResourceForEntityModelObject 
	 extends PB01CRESTResourceForSearchBase<PB01ASearchFilterForPanicButtonOrganizationalEntity,PB01ASearchResultItemForPanicButtonOrganizationalEntity> {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public PB01CRESTSearchResourceForEntityModelObject(final PB01ASearchServicesForOrganizationalEntityObject searchServices) {
		super(new PB01CRESTSearchDelegate(searchServices));
	}
}
