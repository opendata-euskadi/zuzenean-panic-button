package x47b.bootstrap.core.panicbutton;

import com.google.inject.Binder;
import com.google.inject.Module;

import lombok.EqualsAndHashCode;
import r01f.bootstrap.BeanImplementedPersistenceServicesCoreBootstrapGuiceModuleBase;
import r01f.bootstrap.ServicesBootstrapGuiceModuleBindsCRUDEventListeners;
import r01f.bootstrap.services.config.core.ServicesCoreBootstrapConfigWhenBeanExposed;
import r01f.guids.CommonOIDs.AppComponent;
import r01f.persistence.db.config.DBModuleConfigBuilder;
import r01f.persistence.search.config.SearchModuleConfigBuilder;
import x47b.events.X47BCRUDOKEventListenersForAlarmEvents.X47BCRUDOKEventListenerForAlarmEvents;
import x47b.events.X47BCRUDOKEventListenersForAlarmEvents.X47BCRUDOKEventListenersForAlarmMessageLog;
import x47b.events.X47BCRUDOKEventListenersForAlarmEvents.X47BCRUDOKEventListenersForAlarmMessageNotifyByEMail;
import x47b.events.X47BCRUDOKEventListenersForAlarmEvents.X47BCRUDOKEventListenersForAlarmMessageNotifyByMessaging;
import x47b.events.X47BCRUDOKEventListenersForAlarmEvents.X47BCRUDOKEventListenersForAlarmMessageNotifyByVoice;
import x47b.internal.services.X47BNotifierConfigForEMail;
import x47b.internal.services.X47BNotifierConfigForLatinia;
import x47b.internal.services.X47BNotifierConfigForLog;
import x47b.internal.services.X47BNotifierConfigForTwilio;


@EqualsAndHashCode(callSuper=true)									// This is important for guice modules
public class X47BPanicButtonServicesBootstrapGuiceModule
     extends BeanImplementedPersistenceServicesCoreBootstrapGuiceModuleBase
  implements ServicesBootstrapGuiceModuleBindsCRUDEventListeners {
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BPanicButtonServicesBootstrapGuiceModule(final ServicesCoreBootstrapConfigWhenBeanExposed coreBootstrapCfg) {
		super(coreBootstrapCfg,
			  // db module
			  new X47BPanicButtonDBGuiceModule(DBModuleConfigBuilder.dbModuleConfigFrom(coreBootstrapCfg)),
			  // search module
			  new X47BPanicButtonSearchGuiceModule(SearchModuleConfigBuilder.searchModuleConfigFrom(coreBootstrapCfg)),
			  // other modules: notifier
			  new Module[] {
					  new X47BPanicButtonNotifierGuiceModule((X47BNotifierConfigForLog)coreBootstrapCfg.getSubModuleConfigFor(AppComponent.forId("logNotifier")),
							  								 (X47BNotifierConfigForEMail)coreBootstrapCfg.getSubModuleConfigFor(AppComponent.forId("mailNotifier")),
							  								 (X47BNotifierConfigForLatinia)coreBootstrapCfg.getSubModuleConfigFor(AppComponent.forId("latiniaNotifier")),
							  								 (X47BNotifierConfigForTwilio)coreBootstrapCfg.getSubModuleConfigFor(AppComponent.forId("twilioNotifier")))
			  });
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void bindCRUDEventListeners(final Binder binder) {
		// STAGE I:   transforms the X47BAlarmEvent into an X47BAlarmMessage and post it again
		//		      to the event bus
		binder.bind(X47BCRUDOKEventListenerForAlarmEvents.class)
			  .asEagerSingleton();

		// STAGE II:  consumes X47BAlarmMessage objects and uses a concrete X47BPanicButtonNotifierServices (email / sms / voice...)
		//			  to post a notification
		// ... messaging (latinia)
		binder.bind(X47BCRUDOKEventListenersForAlarmMessageNotifyByMessaging.class)
			  .asEagerSingleton();
		// ... mail
		binder.bind(X47BCRUDOKEventListenersForAlarmMessageNotifyByEMail.class)
			  .asEagerSingleton();
		// ... voice (twilio)
		binder.bind(X47BCRUDOKEventListenersForAlarmMessageNotifyByVoice.class)
			  .asEagerSingleton();
		// ... log
		binder.bind(X47BCRUDOKEventListenersForAlarmMessageLog.class)
			  .asEagerSingleton();
	}
}
