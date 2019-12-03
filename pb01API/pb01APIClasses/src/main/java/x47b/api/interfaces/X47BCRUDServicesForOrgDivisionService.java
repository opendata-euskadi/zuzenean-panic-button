package x47b.api.interfaces;

import r01f.services.interfaces.ExposedServiceInterface;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionServiceID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceOID;
import x47b.model.org.X47BOrgDivisionService;

@ExposedServiceInterface
public interface X47BCRUDServicesForOrgDivisionService
         extends X47BCRUDServicesForOrganizationalModelObjectBase<X47BOrgDivisionServiceOID,X47BOrgDivisionServiceID,X47BOrgDivisionService>,
         		 X47BPanicButtonServiceInterface {
	// nothing specific
}