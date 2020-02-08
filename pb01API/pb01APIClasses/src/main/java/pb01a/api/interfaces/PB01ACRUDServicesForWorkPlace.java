package pb01a.api.interfaces;

import pb01a.model.oids.PB01AOrganizationalIDs.PB01AWorkPlaceID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AWorkPlaceOID;
import pb01a.model.org.PB01AWorkPlace;
import r01f.services.interfaces.ExposedServiceInterface;

@ExposedServiceInterface
public interface PB01ACRUDServicesForWorkPlace
         extends PB01ACRUDServicesForOrganizationalModelObjectBase<PB01AWorkPlaceOID,PB01AWorkPlaceID,PB01AWorkPlace>,
         		 PB01APanicButtonServiceInterface {
	// nothing special
}