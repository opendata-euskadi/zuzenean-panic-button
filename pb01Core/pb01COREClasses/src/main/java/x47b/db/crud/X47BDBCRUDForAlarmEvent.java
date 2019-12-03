package x47b.db.crud;

import javax.persistence.EntityManager;

import r01f.objectstreamer.Marshaller;
import r01f.persistence.db.DBCRUDForModelObjectBase;
import r01f.persistence.db.config.DBModuleConfig;
import r01f.persistence.db.entities.primarykeys.DBPrimaryKeyForModelObject;
import r01f.securitycontext.SecurityContext;
import x47b.api.interfaces.X47BCRUDServicesForAlarmEvent;
import x47b.db.X47BAlarmEventDBEntityToAndFromModelObject;
import x47b.db.entities.X47BDBEntityForAlarmEvent;
import x47b.model.X47BAlarmEvent;
import x47b.model.oids.X47BPanicButtonOIDs.X47BAlarmEventOID;

/**
 * Persistence layer
 */
public class X47BDBCRUDForAlarmEvent
	 extends DBCRUDForModelObjectBase<X47BAlarmEventOID,X47BAlarmEvent,
	 								  DBPrimaryKeyForModelObject,X47BDBEntityForAlarmEvent>
  implements X47BCRUDServicesForAlarmEvent {
/////////////////////////////////////////////////////////////////////////////////////////
//  FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	private final X47BAlarmEventDBEntityToAndFromModelObject _transformer;
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BDBCRUDForAlarmEvent(final DBModuleConfig dbCfg,
								   final EntityManager entityManager,
								   final Marshaller marshaller) {
		super(X47BAlarmEvent.class,X47BDBEntityForAlarmEvent.class,
			  dbCfg,
			  entityManager,
			  marshaller);
		_transformer = new X47BAlarmEventDBEntityToAndFromModelObject();
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void setDBEntityFieldsFromModelObject(final SecurityContext securityContext,
												 final X47BAlarmEvent alarmEvent,final X47BDBEntityForAlarmEvent dbEntity) {
		_transformer.setDBEntityFieldsFromModelObject(securityContext,
													  alarmEvent,dbEntity);
	}
	@Override
	public X47BAlarmEvent dbEntityToModelObject(final SecurityContext securityContext,
								   				final X47BDBEntityForAlarmEvent dbAlarm) {
		return _transformer.dbEntityToModelObject(securityContext,
										   		  dbAlarm);
	}
}
