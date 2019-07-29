package x47b.bootstrap.core.panicbutton;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import r01f.bootstrap.services.config.core.ServicesCoreBootstrapConfigBuilder;
import r01f.bootstrap.services.config.core.ServicesCoreBootstrapConfigWhenBeanExposed;
import r01f.bootstrap.services.config.core.ServicesCoreSubModuleBootstrapConfig;
import r01f.guids.CommonOIDs.AppComponent;
import r01f.patterns.IsBuilder;
import r01f.services.ids.ServiceIDs.CoreModule;
import r01f.xmlproperties.XMLPropertiesForApp;
import r01f.xmlproperties.XMLPropertiesForAppComponent;
import x47b.common.internal.X47BAppCodes;
import x47b.internal.services.X47BNotifierConfigForEMail;
import x47b.internal.services.X47BNotifierConfigForLatinia;
import x47b.internal.services.X47BNotifierConfigForLog;
import x47b.internal.services.X47BNotifierConfigForTwilio;
import x47b.services.X47BPanicButtonCoreServiceImpl;

/**
 * Builds bootstrap confif
 */
@NoArgsConstructor(access=AccessLevel.PRIVATE)
public abstract class X47BPanicButtonCOREServicesBootstrapConfigBuilder
		   implements IsBuilder {
/////////////////////////////////////////////////////////////////////////////////////////
//  
/////////////////////////////////////////////////////////////////////////////////////////
	public static ServicesCoreBootstrapConfigWhenBeanExposed buildCoreBootstrapConfig(final XMLPropertiesForApp xmlProps) {
		// load the properties for the db
		XMLPropertiesForAppComponent dbXmlProps = xmlProps.forComponent(AppComponent.compose(X47BAppCodes.PANICBUTTON_MOD,
																							 CoreModule.DBPERSISTENCE));
		// load the properties for the notifier
		XMLPropertiesForAppComponent notifierXmlProps = xmlProps.forComponent(AppComponent.forId("notifier"));		
		
		// return the config
		return ServicesCoreBootstrapConfigBuilder.forCoreAppAndModule(X47BAppCodes.CORE_APPCODE,X47BAppCodes.PANICBUTTON_MOD)
					.beanImplemented()
						.bootstrappedBy(X47BPanicButtonServicesBootstrapGuiceModule.class)
						.findServicesExtending(X47BPanicButtonCoreServiceImpl.class)
						.withSubModulesConfigs(
								// db config
								ServicesCoreSubModuleBootstrapConfig.createForDBPersistenceSubModule(X42TDBModuleConfig.dbConfigFor(dbXmlProps)),

								// log notifier config
								new ServicesCoreSubModuleBootstrapConfig<X47BNotifierConfigForLog>(AppComponent.forId("logNotifier"),
																					  		   	   X47BNotifierConfigForLog.createFrom(notifierXmlProps)),
								
								// mail notifier config
								new ServicesCoreSubModuleBootstrapConfig<X47BNotifierConfigForEMail>(AppComponent.forId("mailNotifier"),
																					  		   		 X47BNotifierConfigForEMail.createFrom(notifierXmlProps)),
								// latinia notifier config
								new ServicesCoreSubModuleBootstrapConfig<X47BNotifierConfigForLatinia>(AppComponent.forId("latiniaNotifier"),
																					    			   X47BNotifierConfigForLatinia.createFrom(notifierXmlProps)),
								// twilio notifier config
								new ServicesCoreSubModuleBootstrapConfig<X47BNotifierConfigForTwilio>(AppComponent.forId("twilioNotifier"),
																					   				  X47BNotifierConfigForTwilio.createFrom(notifierXmlProps))
						)
						.build();
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  PROPERTIES FOR PROVIDERS
/////////////////////////////////////////////////////////////////////////////////////////
}
