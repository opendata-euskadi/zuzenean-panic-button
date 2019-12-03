package x47b.rest.resources;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.Path;

import lombok.experimental.Accessors;
import x47b.api.interfaces.X47BCRUDServicesForWorkPlace;
import x47b.model.oids.X47BOrganizationalIDs.X47BWorkPlaceID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BWorkPlaceOID;
import x47b.model.org.X47BWorkPlace;
import x47b.rest.resources.delegates.X47BRESTCRUDDelegateForWorkPlace;
import x47b.server.rest.resources.X47BRESTCRUDResourceBaseForOrganizationalEntity;

/**
 * The implementation must follow the HTTP 1.1 spec (http://www.w3.org/Protocols/rfc2616/rfc2616.html), specifically
 * the section about the methods (GET, PUT, POST, DELETE...)
 *
 * Log: see web.xml
 */
@Path("workPlaces")
@Singleton
@Accessors(prefix="_")
public class X47BRESTCRUDResourceForWorkPlace
	 extends X47BRESTCRUDResourceBaseForOrganizationalEntity<X47BWorkPlaceOID,X47BWorkPlaceID,X47BWorkPlace> {
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public X47BRESTCRUDResourceForWorkPlace(final X47BCRUDServicesForWorkPlace crudServices) {
		super(new X47BRESTCRUDDelegateForWorkPlace(crudServices));
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  EXTENSIONS
/////////////////////////////////////////////////////////////////////////////////////////	

	
}
