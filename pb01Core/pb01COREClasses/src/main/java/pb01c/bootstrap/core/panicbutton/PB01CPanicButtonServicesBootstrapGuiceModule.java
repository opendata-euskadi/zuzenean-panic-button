package pb01c.bootstrap.core.panicbutton;

import com.google.inject.Binder;
import com.google.inject.Module;

import lombok.EqualsAndHashCode;
import pb01c.events.PB01CCRUDOKEventListenersForAlarmEvents.PB01CCRUDOKEventListenerForAlarmEvents;
import pb01c.events.PB01CCRUDOKEventListenersForAlarmEvents.PB01CCRUDOKEventListenersForAlarmMessageLog;
import pb01c.events.PB01CCRUDOKEventListenersForAlarmEvents.PB01CCRUDOKEventListenersForAlarmMessageNotifyByEMail;
import pb01c.events.PB01CCRUDOKEventListenersForAlarmEvents.PB01CCRUDOKEventListenersForAlarmMessageNotifyByMessaging;
import pb01c.events.PB01CCRUDOKEventListenersForAlarmEvents.PB01CCRUDOKEventListenersForAlarmMessageNotifyByVoice;
import r01f.bootstrap.BeanImplementedPersistenceServicesCoreBootstrapGuiceModuleBase;
import r01f.bootstrap.ServicesBootstrapGuiceModuleBindsCRUDEventListeners;
import r01f.bootstrap.services.config.core.ServicesCoreBootstrapConfigWhenBeanExposed;
import r01f.core.services.notifier.config.NotifiersConfigs;
import r01f.guids.CommonOIDs.AppComponent;
import r01f.persistence.db.config.DBModuleConfigBuilder;
import r01f.persistence.search.config.SearchModuleConfigBuilder;


@EqualsAndHashCode(callSuper=true)									// This is important for guice modules
public class PB01CPanicButtonServicesBootstrapGuiceModule
     extends BeanImplementedPersistenceServicesCoreBootstrapGuiceModuleBase
  implements ServicesBootstrapGuiceModuleBindsCRUDEventListeners {
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01CPanicButtonServicesBootstrapGuiceModule(final ServicesCoreBootstrapConfigWhenBeanExposed coreBootstrapCfg) {
		super(coreBootstrapCfg,
			  // db module
			  new PB01CPanicButtonDBGuiceModule(DBModuleConfigBuilder.dbModuleConfigFrom(coreBootstrapCfg)),
			  // search module
			  new PB01CPanicButtonSearchGuiceModule(SearchModuleConfigBuilder.searchModuleConfigFrom(coreBootstrapCfg)),
			  // other modules: notifier
			  new Module[] {
					  new PB01CPanicButtonNotifierGuiceModule((NotifiersConfigs)coreBootstrapCfg.getSubModuleConfigFor(AppComponent.forId("notifier")))
			  });
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void bindCRUDEventListeners(final Binder binder) {
		// STAGE I:   transforms the PB01AAlarmEvent into an PB01AAlarmMessage and post it again
		//		      to the event bus
		binder.bind(PB01CCRUDOKEventListenerForAlarmEvents.class)
			  .asEagerSingleton();

		// STAGE II:  consumes PB01AAlarmMessage objects and uses a concrete PB01CPanicButtonNotifierServices (email / sms / voice...)
		//			  to post a notification
		// ... messaging (latinia)
		binder.bind(PB01CCRUDOKEventListenersForAlarmMessageNotifyByMessaging.class)
			  .asEagerSingleton();
		// ... mail
		binder.bind(PB01CCRUDOKEventListenersForAlarmMessageNotifyByEMail.class)
			  .asEagerSingleton();
		// ... voice (twilio)
		binder.bind(PB01CCRUDOKEventListenersForAlarmMessageNotifyByVoice.class)
			  .asEagerSingleton();
		// ... log
		binder.bind(PB01CCRUDOKEventListenersForAlarmMessageLog.class)
			  .asEagerSingleton();
	}
}
