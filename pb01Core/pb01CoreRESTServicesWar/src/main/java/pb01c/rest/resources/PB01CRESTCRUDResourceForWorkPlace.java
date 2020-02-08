package pb01c.rest.resources;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.Path;

import lombok.experimental.Accessors;
import pb01a.api.interfaces.PB01ACRUDServicesForWorkPlace;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AWorkPlaceID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AWorkPlaceOID;
import pb01a.model.org.PB01AWorkPlace;
import pb01c.rest.resources.delegates.PB01CRESTCRUDDelegateForWorkPlace;
import pb01c.server.rest.resources.PB01CRESTCRUDResourceBaseForOrganizationalEntity;

/**
 * The implementation must follow the HTTP 1.1 spec (http://www.w3.org/Protocols/rfc2616/rfc2616.html), specifically
 * the section about the methods (GET, PUT, POST, DELETE...)
 *
 * Log: see web.xml
 */
@Path("workPlaces")
@Singleton
@Accessors(prefix="_")
public class PB01CRESTCRUDResourceForWorkPlace
	 extends PB01CRESTCRUDResourceBaseForOrganizationalEntity<PB01AWorkPlaceOID,PB01AWorkPlaceID,PB01AWorkPlace> {
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public PB01CRESTCRUDResourceForWorkPlace(final PB01ACRUDServicesForWorkPlace crudServices) {
		super(new PB01CRESTCRUDDelegateForWorkPlace(crudServices));
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  EXTENSIONS
/////////////////////////////////////////////////////////////////////////////////////////	

	
}
