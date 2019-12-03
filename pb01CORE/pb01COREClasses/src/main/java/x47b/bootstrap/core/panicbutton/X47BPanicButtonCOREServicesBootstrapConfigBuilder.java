package x47b.bootstrap.core.panicbutton;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import r01f.bootstrap.services.config.core.ServicesCoreBootstrapConfigBuilder;
import r01f.bootstrap.services.config.core.ServicesCoreBootstrapConfigWhenBeanExposed;
import r01f.bootstrap.services.config.core.ServicesCoreSubModuleBootstrapConfig;
import r01f.config.ContainsConfigData;
import r01f.core.services.notifier.config.NotifierConfigForEMail;
import r01f.core.services.notifier.config.NotifierConfigForLog;
import r01f.core.services.notifier.config.NotifierConfigForSMS;
import r01f.core.services.notifier.config.NotifierConfigForVoice;
import r01f.core.services.notifier.config.NotifierConfigProviders.NotifierAppDependentConfigProviderFromProperties;
import r01f.core.services.notifier.config.NotifierEnums.NotifierImpl;
import r01f.core.services.notifier.config.NotifiersConfigs;
import r01f.core.services.notifier.spi.NotifierSPIUtil;
import r01f.guids.CommonOIDs.AppComponent;
import r01f.patterns.IsBuilder;
import r01f.services.ids.ServiceIDs.CoreModule;
import r01f.xmlproperties.XMLPropertiesForApp;
import r01f.xmlproperties.XMLPropertiesForAppComponent;
import x47b.common.internal.X47BAppCodes;
import x47b.internal.notifier.config.X47BNotifierConfigForEMail;
import x47b.internal.notifier.config.X47BNotifierConfigForSMS;
import x47b.internal.notifier.config.X47BNotifierConfigForVoice;
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
		NotifiersConfigs notifiersCfg = _loadNotifiersConfig(notifierXmlProps);

		// return the config
		return ServicesCoreBootstrapConfigBuilder.forCoreAppAndModule(X47BAppCodes.CORE_APPCODE,X47BAppCodes.PANICBUTTON_MOD)
					.beanImplemented()
						.bootstrappedBy(X47BPanicButtonServicesBootstrapGuiceModule.class)
						.findServicesExtending(X47BPanicButtonCoreServiceImpl.class)
						.withSubModulesConfigs(
								// db config
								ServicesCoreSubModuleBootstrapConfig.createForDBPersistenceSubModule(X47BDBModuleConfig.dbConfigFor(dbXmlProps)),

								// log notifier config
								new ServicesCoreSubModuleBootstrapConfig<NotifiersConfigs>(AppComponent.forId("notifier"),
																					  	   notifiersCfg)
						)
						.build();
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  PROPERTIES FOR PROVIDERS
/////////////////////////////////////////////////////////////////////////////////////////
	private static final NotifiersConfigs _loadNotifiersConfig(final XMLPropertiesForAppComponent props) {
		// Use java's SPI to get the config
		NotifierConfigForEMail forEMail = NotifierSPIUtil.emailNotifierConfigFrom(props,
																				  // creates the app dependent config part
																				  new NotifierAppDependentConfigProviderFromProperties() {
																							@Override
																							public ContainsConfigData provideConfigUsing(final NotifierImpl impl,
																																		 final XMLPropertiesForAppComponent props) {
																								return new X47BNotifierConfigForEMail(props);
																							}
																				  });
		NotifierConfigForSMS forSMS = NotifierSPIUtil.smsNotifierConfigFrom(props,
																			// creates the app dependent config part
																			new NotifierAppDependentConfigProviderFromProperties() {
																						@Override
																						public ContainsConfigData provideConfigUsing(final NotifierImpl impl,
																																	 final XMLPropertiesForAppComponent props) {
																							return new X47BNotifierConfigForSMS(props);
																						}
																			});
		NotifierConfigForVoice forVoice = NotifierSPIUtil.voiceNotifierConfigFrom(props,
																				  // creates the app dependent config part
																				  new NotifierAppDependentConfigProviderFromProperties() {
																							@Override
																							public ContainsConfigData provideConfigUsing(final NotifierImpl impl,
																																		 final XMLPropertiesForAppComponent props) {
																								return new X47BNotifierConfigForVoice(props);
																							}
																				  });

		NotifierConfigForLog forLog = new NotifierConfigForLog(props);


		// Assemble all notifier configs
		NotifiersConfigs outCfg = new NotifiersConfigs(forEMail,forSMS,forVoice,forLog);
		log.warn("Notifiers config: ");
		log.warn("\t\t-EMail: {}",forEMail != null
										? forEMail.isEnabled() ? "ENABLED" : "DISABLED"
										: "NULL");
		log.warn("\t\t-  SMS: {}",forSMS != null
										? forSMS.isEnabled() ? "ENABLED" : "DISABLED"
										: "NULL");
		log.warn("\t\t-Voice: {}",forVoice != null
										? forVoice.isEnabled() ? "ENABLED" : "DISABLED"
										: "NULL");
		log.warn("\t\t-  Log: {}",forLog != null
										? forLog.isEnabled() ? "ENABLED" : "DISABLED"
										: "NULL");

		return outCfg;
	}
}
