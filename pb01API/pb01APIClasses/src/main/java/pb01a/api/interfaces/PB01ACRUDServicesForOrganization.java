package pb01a.api.interfaces;

import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrganizationID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrganizationOID;
import pb01a.model.org.PB01AOrganization;
import r01f.services.interfaces.ExposedServiceInterface;

@ExposedServiceInterface
public interface PB01ACRUDServicesForOrganization
         extends PB01ACRUDServicesForOrganizationalModelObjectBase<PB01AOrganizationOID,PB01AOrganizationID,PB01AOrganization>,
         		 PB01APanicButtonServiceInterface {
	// nothing specific
}