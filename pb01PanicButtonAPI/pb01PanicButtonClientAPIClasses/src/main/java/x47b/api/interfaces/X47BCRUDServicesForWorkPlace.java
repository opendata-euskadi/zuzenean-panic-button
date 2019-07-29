package x47b.api.interfaces;

import r01f.services.interfaces.ExposedServiceInterface;
import x47b.model.oids.X47BOrganizationalIDs.X47BWorkPlaceID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BWorkPlaceOID;
import x47b.model.org.X47BWorkPlace;

@ExposedServiceInterface
public interface X47BCRUDServicesForWorkPlace
         extends X47BCRUDServicesForOrganizationalModelObjectBase<X47BWorkPlaceOID,X47BWorkPlaceID,X47BWorkPlace>,
         		 X47BPanicButtonServiceInterface {
	// nothing special
}