package pb01c.db.crud;

import javax.persistence.EntityManager;

import pb01a.api.interfaces.PB01ACRUDServicesForAlarmEvent;
import pb01a.model.PB01AAlarmEvent;
import pb01a.model.oids.PB01APanicButtonOIDs.PB01AAlarmEventOID;
import pb01c.db.PB01CAlarmEventDBEntityToAndFromModelObject;
import pb01c.db.entities.PB01CDBEntityForAlarmEvent;
import r01f.objectstreamer.Marshaller;
import r01f.persistence.db.DBCRUDForModelObjectBase;
import r01f.persistence.db.config.DBModuleConfig;
import r01f.persistence.db.entities.primarykeys.DBPrimaryKeyForModelObject;
import r01f.securitycontext.SecurityContext;

/**
 * Persistence layer
 */
public class PB01CDBCRUDForAlarmEvent
	 extends DBCRUDForModelObjectBase<PB01AAlarmEventOID,PB01AAlarmEvent,
	 								  DBPrimaryKeyForModelObject,PB01CDBEntityForAlarmEvent>
  implements PB01ACRUDServicesForAlarmEvent {
/////////////////////////////////////////////////////////////////////////////////////////
//  FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	private final PB01CAlarmEventDBEntityToAndFromModelObject _transformer;
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01CDBCRUDForAlarmEvent(final DBModuleConfig dbCfg,
								   final EntityManager entityManager,
								   final Marshaller marshaller) {
		super(PB01AAlarmEvent.class,PB01CDBEntityForAlarmEvent.class,
			  dbCfg,
			  entityManager,
			  marshaller);
		_transformer = new PB01CAlarmEventDBEntityToAndFromModelObject();
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void setDBEntityFieldsFromModelObject(final SecurityContext securityContext,
												 final PB01AAlarmEvent alarmEvent,final PB01CDBEntityForAlarmEvent dbEntity) {
		_transformer.setDBEntityFieldsFromModelObject(securityContext,
													  alarmEvent,dbEntity);
	}
	@Override
	public PB01AAlarmEvent dbEntityToModelObject(final SecurityContext securityContext,
								   				final PB01CDBEntityForAlarmEvent dbAlarm) {
		return _transformer.dbEntityToModelObject(securityContext,
										   		  dbAlarm);
	}
}
