package x47b.api.interfaces;

import r01f.services.interfaces.CRUDServicesForModelObject;
import r01f.services.interfaces.ExposedServiceInterface;
import x47b.model.X47BAlarmEvent;
import x47b.model.oids.X47BPanicButtonOIDs.X47BAlarmEventOID;

@ExposedServiceInterface
public interface X47BCRUDServicesForAlarmEvent
         extends CRUDServicesForModelObject<X47BAlarmEventOID,X47BAlarmEvent>,
         		 X47BPanicButtonServiceInterface {
	// nothing
}