package x47b.api.interfaces;

import r01f.services.interfaces.ExposedServiceInterface;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionOID;
import x47b.model.org.X47BOrgDivision;

@ExposedServiceInterface
public interface X47BCRUDServicesForOrgDivision
         extends X47BCRUDServicesForOrganizationalModelObjectBase<X47BOrgDivisionOID,X47BOrgDivisionID,X47BOrgDivision>,
         		 X47BPanicButtonServiceInterface {
	// nothing specific
}