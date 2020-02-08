package pb01c.bootstrap.core.panicbutton;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pb01a.common.internal.P01AAppCodes;
import pb01c.internal.notifier.config.PB01CNotifierConfigForEMail;
import pb01c.internal.notifier.config.PB01CNotifierConfigForSMS;
import pb01c.internal.notifier.config.PB01CNotifierConfigForVoice;
import pb01c.services.PB01CPanicButtonCoreServiceImpl;
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

/**
 * Builds bootstrap confif
 */
@Slf4j
@NoArgsConstructor(access=AccessLevel.PRIVATE)
public abstract class PB01CPanicButtonCOREServicesBootstrapConfigBuilder
		   implements IsBuilder {
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	public static ServicesCoreBootstrapConfigWhenBeanExposed buildCoreBootstrapConfig(final XMLPropertiesForApp xmlProps) {
		// load the properties for the db
		XMLPropertiesForAppComponent dbXmlProps = xmlProps.forComponent(AppComponent.compose(P01AAppCodes.PANICBUTTON_MOD,
																							 CoreModule.DBPERSISTENCE));
		// load the properties for the notifier
		XMLPropertiesForAppComponent notifierXmlProps = xmlProps.forComponent(AppComponent.forId("notifier"));

		// Load the notifiers config
		NotifiersConfigs notifiersCfg = _loadNotifiersConfig(notifierXmlProps);

		// return the config
		return ServicesCoreBootstrapConfigBuilder.forCoreAppAndModule(P01AAppCodes.CORE_APPCODE,P01AAppCodes.PANICBUTTON_MOD)
					.beanImplemented()
						.bootstrappedBy(PB01CPanicButtonServicesBootstrapGuiceModule.class)
						.findServicesExtending(PB01CPanicButtonCoreServiceImpl.class)
						.withSubModulesConfigs(
								// db config
								ServicesCoreSubModuleBootstrapConfig.createForDBPersistenceSubModule(PB01CDBModuleConfig.dbConfigFor(dbXmlProps)),

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
																								return new PB01CNotifierConfigForEMail(props);
																							}
																				  });
		NotifierConfigForSMS forSMS = NotifierSPIUtil.smsNotifierConfigFrom(props,
																			// creates the app dependent config part
																			new NotifierAppDependentConfigProviderFromProperties() {
																						@Override
																						public ContainsConfigData provideConfigUsing(final NotifierImpl impl,
																																	 final XMLPropertiesForAppComponent props) {
																							return new PB01CNotifierConfigForSMS(props);
																						}
																			});
		NotifierConfigForVoice forVoice = NotifierSPIUtil.voiceNotifierConfigFrom(props,
																				  // creates the app dependent config part
																				  new NotifierAppDependentConfigProviderFromProperties() {
																							@Override
																							public ContainsConfigData provideConfigUsing(final NotifierImpl impl,
																																		 final XMLPropertiesForAppComponent props) {
																								return new PB01CNotifierConfigForVoice(props);
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
