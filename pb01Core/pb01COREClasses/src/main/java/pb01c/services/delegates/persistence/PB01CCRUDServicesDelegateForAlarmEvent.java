package pb01c.services.delegates.persistence;

import javax.persistence.EntityManager;

import com.google.common.eventbus.EventBus;

import pb01a.api.interfaces.PB01ACRUDServicesForAlarmEvent;
import pb01a.model.PB01AAlarmEvent;
import pb01a.model.oids.PB01APanicButtonOIDs.PB01AAlarmEventOID;
import pb01c.db.crud.PB01CDBCRUDForAlarmEvent;
import r01f.bootstrap.services.config.core.ServicesCoreBootstrapConfigWhenBeanExposed;
import r01f.model.persistence.PersistenceRequestedOperation;
import r01f.objectstreamer.Marshaller;
import r01f.persistence.db.config.DBModuleConfigBuilder;
import r01f.securitycontext.SecurityContext;
import r01f.services.delegates.persistence.CRUDServicesForModelObjectDelegateBase;
import r01f.services.delegates.persistence.CompletesModelObjectBeforeCreateOrUpdate;
import r01f.services.delegates.persistence.ValidatesModelObjectBeforeCreateOrUpdate;
import r01f.validation.ObjectValidationResult;

/**
 * Service layer delegated type for CRUD (Create/Read/Update/Delete) operations
 */
public class PB01CCRUDServicesDelegateForAlarmEvent
	 extends CRUDServicesForModelObjectDelegateBase<PB01AAlarmEventOID,PB01AAlarmEvent>
  implements PB01ACRUDServicesForAlarmEvent,
  			 ValidatesModelObjectBeforeCreateOrUpdate<PB01AAlarmEvent>,
  			 CompletesModelObjectBeforeCreateOrUpdate<PB01AAlarmEvent> {

/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01CCRUDServicesDelegateForAlarmEvent(final ServicesCoreBootstrapConfigWhenBeanExposed coreCfg,
												 final EntityManager entityManager,
											     final Marshaller marshaller,
				  			   		   	   	     final EventBus eventBus) {
		super(coreCfg,
			  PB01AAlarmEvent.class,
			  new PB01CDBCRUDForAlarmEvent(DBModuleConfigBuilder.dbModuleConfigFrom(coreCfg),
					  					  entityManager,
					  					  marshaller),
			  eventBus);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  PARAMS VALIDATION ON CREATION / UPDATE
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public ObjectValidationResult<PB01AAlarmEvent> validateModelObjBeforeCreateOrUpdate(final SecurityContext securityContext,
																	 				   final PersistenceRequestedOperation requestedOp,
																	 				   final PB01AAlarmEvent alarmEvent) {
		return alarmEvent.validate();
	}
	@Override
	public PB01AAlarmEvent completeModelObjBeforeCreateOrUpdate(final SecurityContext securityContext,
															   final PersistenceRequestedOperation requestedOp,
															   final PB01AAlarmEvent alarmEvent) {
		// ensure the alarm event has an oid
		if (alarmEvent.getOid() == null) alarmEvent.setOid(PB01AAlarmEventOID.supply());
		return alarmEvent;
	}
}
