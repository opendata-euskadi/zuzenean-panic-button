package x47b.bootstrap.core.panicbutton;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import r01f.bootstrap.services.config.core.ServicesCoreBootstrapConfigBuilder;
import r01f.bootstrap.services.config.core.ServicesCoreBootstrapConfigWhenBeanExposed;
import r01f.bootstrap.services.config.core.ServicesCoreSubModuleBootstrapConfig;
import r01f.guids.CommonOIDs.AppComponent;
import r01f.patterns.IsBuilder;
import r01f.services.ids.ServiceIDs.CoreModule;
import r01f.xmlproperties.XMLPropertiesForApp;
import r01f.xmlproperties.XMLPropertiesForAppComponent;
import x47b.common.internal.X47BAppCodes;
import x47b.internal.services.config.X47BNotifierConfigForEMail;
import x47b.internal.services.config.X47BNotifierConfigForLog;
import x47b.internal.services.config.X47BNotifierConfigForSMS;
import x47b.internal.services.config.X47BNotifierConfigForVoice;
import x47b.internal.services.config.X47BNotifiersConfig;
import x47b.services.X47BPanicButtonCoreServiceImpl;

/**
 * Builds bootstrap confif
 */
@Slf4j
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

		// Load the notifiers config
		X47BNotifiersConfig notifiersCfg = _loadNotifiersConfig(notifierXmlProps);

		// return the config
		return ServicesCoreBootstrapConfigBuilder.forCoreAppAndModule(X47BAppCodes.CORE_APPCODE,X47BAppCodes.PANICBUTTON_MOD)
					.beanImplemented()
						.bootstrappedBy(X47BPanicButtonServicesBootstrapGuiceModule.class)
						.findServicesExtending(X47BPanicButtonCoreServiceImpl.class)
						.withSubModulesConfigs(
								// db config
								ServicesCoreSubModuleBootstrapConfig.createForDBPersistenceSubModule(X42TDBModuleConfig.dbConfigFor(dbXmlProps)),

								// log notifier config
								new ServicesCoreSubModuleBootstrapConfig<X47BNotifiersConfig>(AppComponent.forId("notifier"),
																					  		 notifiersCfg)
						)
						.build();
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  PROPERTIES FOR PROVIDERS
/////////////////////////////////////////////////////////////////////////////////////////
	private static final X47BNotifiersConfig _loadNotifiersConfig(final XMLPropertiesForAppComponent props) {
		X47BNotifierConfigForEMail forEMail = X47BNotifierConfigForEMail.createFrom(props);
		X47BNotifierConfigForSMS forSMS = X47BNotifierConfigForSMS.createFrom(props);
		X47BNotifierConfigForVoice forVoice = X47BNotifierConfigForVoice.createFrom(props);
		X47BNotifierConfigForLog forLog = X47BNotifierConfigForLog.createFrom(props);

		X47BNotifiersConfig outCfg = new X47BNotifiersConfig(forEMail,forSMS,forVoice,forLog);
		log.warn("Notifiers config: ");
		log.warn("\t-EMail: {}",forEMail.isEnabled() ? "ENABLED" : "DISABLED");
		log.warn("\t-  SMS: {}",forSMS.isEnabled() ? "ENABLED" : "DISABLED");
		log.warn("\t-Voice: {}",forVoice.isEnabled() ? "ENABLED" : "DISABLED");
		log.warn("\t-  Log: {}",forLog.isEnabled() ? "ENABLED" : "DISABLED");

		return outCfg;
	}
}
