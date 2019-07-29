package x47b.services.delegates.persistence;

import javax.persistence.EntityManager;

import com.google.common.eventbus.EventBus;

import r01f.bootstrap.services.config.core.ServicesCoreBootstrapConfigWhenBeanExposed;
import r01f.model.persistence.PersistenceRequestedOperation;
import r01f.objectstreamer.Marshaller;
import r01f.persistence.db.config.DBModuleConfigBuilder;
import r01f.securitycontext.SecurityContext;
import r01f.services.delegates.persistence.CRUDServicesForModelObjectDelegateBase;
import r01f.services.delegates.persistence.CompletesModelObjectBeforeCreateOrUpdate;
import r01f.services.delegates.persistence.ValidatesModelObjectBeforeCreateOrUpdate;
import r01f.validation.ObjectValidationResult;
import x47b.api.interfaces.X47BCRUDServicesForAlarmEvent;
import x47b.db.crud.X47BDBCRUDForAlarmEvent;
import x47b.model.X47BAlarmEvent;
import x47b.model.oids.X47BPanicButtonOIDs.X47BAlarmEventOID;

/**
 * Service layer delegated type for CRUD (Create/Read/Update/Delete) operations
 */
public class X47BCRUDServicesDelegateForAlarmEvent
	 extends CRUDServicesForModelObjectDelegateBase<X47BAlarmEventOID,X47BAlarmEvent>
  implements X47BCRUDServicesForAlarmEvent,
  			 ValidatesModelObjectBeforeCreateOrUpdate<X47BAlarmEvent>,
  			 CompletesModelObjectBeforeCreateOrUpdate<X47BAlarmEvent> {

/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BCRUDServicesDelegateForAlarmEvent(final ServicesCoreBootstrapConfigWhenBeanExposed coreCfg,
												 final EntityManager entityManager,
											     final Marshaller marshaller,
				  			   		   	   	     final EventBus eventBus) {
		super(coreCfg,
			  X47BAlarmEvent.class,
			  new X47BDBCRUDForAlarmEvent(DBModuleConfigBuilder.dbModuleConfigFrom(coreCfg),
					  					  entityManager,
					  					  marshaller),
			  eventBus);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  PARAMS VALIDATION ON CREATION / UPDATE
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public ObjectValidationResult<X47BAlarmEvent> validateModelObjBeforeCreateOrUpdate(final SecurityContext securityContext,
																	 				   final PersistenceRequestedOperation requestedOp,
																	 				   final X47BAlarmEvent alarmEvent) {
		return alarmEvent.validate();
	}
	@Override
	public X47BAlarmEvent completeModelObjBeforeCreateOrUpdate(final SecurityContext securityContext,
															   final PersistenceRequestedOperation requestedOp,
															   final X47BAlarmEvent alarmEvent) {
		// ensure the alarm event has an oid
		if (alarmEvent.getOid() == null) alarmEvent.setOid(X47BAlarmEventOID.supply());
		return alarmEvent;
	}
}
