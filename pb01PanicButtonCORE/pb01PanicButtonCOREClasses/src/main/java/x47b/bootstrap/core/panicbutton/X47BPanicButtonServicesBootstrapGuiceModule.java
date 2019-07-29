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
import x47b.events.X47BCRUDOKEventListenersForAlarmEvents.X47BCRUDOKEventListenersForAlarmEventsLog;
import x47b.events.X47BCRUDOKEventListenersForAlarmEvents.X47BCRUDOKEventListenersForAlarmEventsNotifyByEMail;
import x47b.events.X47BCRUDOKEventListenersForAlarmEvents.X47BCRUDOKEventListenersForAlarmEventsNotifyByMessaging;
import x47b.events.X47BCRUDOKEventListenersForAlarmEvents.X47BCRUDOKEventListenersForAlarmEventsNotifyByVoice;
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
		// Bind notifiers event listeners
		// ... messaging (latinia)
		binder.bind(X47BCRUDOKEventListenersForAlarmEventsNotifyByMessaging.class)
			  .asEagerSingleton();
		// ... mail
		binder.bind(X47BCRUDOKEventListenersForAlarmEventsNotifyByEMail.class)
			  .asEagerSingleton();
		// ... voice (twilio)
		binder.bind(X47BCRUDOKEventListenersForAlarmEventsNotifyByVoice.class)
			  .asEagerSingleton();
		// ... log
		binder.bind(X47BCRUDOKEventListenersForAlarmEventsLog.class)
			  .asEagerSingleton();
	}
}
