package x47b.api.interfaces;

import r01f.services.interfaces.ExposedServiceInterface;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrganizationID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrganizationOID;
import x47b.model.org.X47BOrganization;

@ExposedServiceInterface
public interface X47BCRUDServicesForOrganization
         extends X47BCRUDServicesForOrganizationalModelObjectBase<X47BOrganizationOID,X47BOrganizationID,X47BOrganization>,
         		 X47BPanicButtonServiceInterface {
	// nothing specific
}