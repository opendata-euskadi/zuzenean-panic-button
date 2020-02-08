package pb01a.api.interfaces;

import pb01a.model.PB01AAlarmEvent;
import pb01a.model.oids.PB01APanicButtonOIDs.PB01AAlarmEventOID;
import r01f.services.interfaces.CRUDServicesForModelObject;
import r01f.services.interfaces.ExposedServiceInterface;

@ExposedServiceInterface
public interface PB01ACRUDServicesForAlarmEvent
         extends CRUDServicesForModelObject<PB01AAlarmEventOID,PB01AAlarmEvent>,
         		 PB01APanicButtonServiceInterface {
	// nothing
}