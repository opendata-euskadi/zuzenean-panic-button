package x47b.api.interfaces;

import r01f.services.interfaces.ExposedServiceInterface;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionServiceLocationID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceLocationOID;
import x47b.model.org.X47BOrgDivisionServiceLocation;

@ExposedServiceInterface
public interface X47BCRUDServicesForOrgDivisionServiceLocation
         extends X47BCRUDServicesForOrganizationalModelObjectBase<X47BOrgDivisionServiceLocationOID,X47BOrgDivisionServiceLocationID,X47BOrgDivisionServiceLocation>,
         		 X47BPanicButtonServiceInterface {
	// nothing specific
}