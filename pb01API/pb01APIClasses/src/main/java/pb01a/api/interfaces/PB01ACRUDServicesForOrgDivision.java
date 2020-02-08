package pb01a.api.interfaces;

import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionOID;
import pb01a.model.org.PB01AOrgDivision;
import r01f.services.interfaces.ExposedServiceInterface;

@ExposedServiceInterface
public interface PB01ACRUDServicesForOrgDivision
         extends PB01ACRUDServicesForOrganizationalModelObjectBase<PB01AOrgDivisionOID,PB01AOrgDivisionID,PB01AOrgDivision>,
         		 PB01APanicButtonServiceInterface {
	// nothing specific
}