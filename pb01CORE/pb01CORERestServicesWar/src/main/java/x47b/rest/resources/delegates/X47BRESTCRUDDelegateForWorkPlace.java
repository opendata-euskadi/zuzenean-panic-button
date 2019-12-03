package x47b.rest.resources.delegates;

import x47b.api.interfaces.X47BCRUDServicesForWorkPlace;
import x47b.model.oids.X47BOrganizationalIDs.X47BWorkPlaceID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BWorkPlaceOID;
import x47b.model.org.X47BWorkPlace;
import x47b.server.rest.resources.delegates.X47BRESTCRUDDelegateBaseForOrganizationalEntity;

public class X47BRESTCRUDDelegateForWorkPlace
	 extends X47BRESTCRUDDelegateBaseForOrganizationalEntity<X47BWorkPlaceOID,X47BWorkPlaceID,X47BWorkPlace> {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BRESTCRUDDelegateForWorkPlace(final X47BCRUDServicesForWorkPlace crudServices) {
		super(X47BWorkPlace.class,
			  crudServices);
	}
}
