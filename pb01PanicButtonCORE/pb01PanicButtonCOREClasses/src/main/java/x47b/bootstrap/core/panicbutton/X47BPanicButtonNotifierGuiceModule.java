package x47b.bootstrap.core.panicbutton;

import java.util.Properties;

import javax.inject.Provider;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.springframework.mail.javamail.JavaMailSender;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Provides;
import com.google.inject.Singleton;

import lombok.EqualsAndHashCode;
import r01f.cloud.aws.sns.AWSSNSClient;
import r01f.cloud.aws.sns.AWSSNSClientConfig;
import r01f.cloud.aws.sns.notifier.AWSSNSNotifierServices;
import r01f.cloud.twilio.TwilioConfig;
import r01f.cloud.twilio.TwilioService;
import r01f.cloud.twilio.TwilioServiceProvider;
import r01f.cloud.twilio.notifier.TwilioNotifierServices;
import r01f.core.services.mail.JavaMailSenderProvider;
import r01f.core.services.mail.config.JavaMailSenderConfig;
import r01f.core.services.mail.config.JavaMailSenderConfigBuilder;
import r01f.core.services.mail.config.JavaMailSenderImpl;
import r01f.core.services.mail.notifier.JavaMailSenderNotifierServices;
import r01f.core.services.notifier.NotifierServicesForEMail;
import r01f.core.services.notifier.NotifierServicesForSMS;
import r01f.core.services.notifier.NotifierServicesForVoicePhoneCall;
import r01f.core.services.notifier.config.NotifierEnums.SMSNotifierImpl;
import r01f.core.services.notifier.config.NotifierEnums.VoiceNotifierImpl;
import r01f.guids.CommonOIDs.AppComponent;
import r01f.model.annotations.ModelObjectsMarshaller;
import r01f.notifier.UseEMailNotifier;
import r01f.notifier.UseLogNotifier;
import r01f.notifier.UseMessagingNotifier;
import r01f.notifier.UseVoiceNotifier;
import r01f.objectstreamer.Marshaller;
import r01f.services.latinia.LatiniaService;
import r01f.services.latinia.LatiniaServiceAPIData;
import r01f.services.latinia.LatiniaServiceProvider;
import r01f.services.latinia.notifier.LatiniaNotifierServices;
import r01f.xmlproperties.XMLProperties;
import r01f.xmlproperties.XMLPropertiesForAppComponent;
import x47b.common.internal.X47BAppCodes;
import x47b.internal.services.X47BPanicButtonNotifierServices;
import x47b.internal.services.X47BPanicButtonNotifierServicesEMailImpl;
import x47b.internal.services.X47BPanicButtonNotifierServicesLoggerImpl;
import x47b.internal.services.X47BPanicButtonNotifierServicesSMSImpl;
import x47b.internal.services.X47BPanicButtonNotifierServicesVoiceImpl;
import x47b.internal.services.config.X47BNotifierConfigForEMail;
import x47b.internal.services.config.X47BNotifierConfigForLog;
import x47b.internal.services.config.X47BNotifierConfigForSMS;
import x47b.internal.services.config.X47BNotifierConfigForVoice;
import x47b.internal.services.config.X47BNotifiersConfig;

@EqualsAndHashCode				// This is important for guice modules
     class X47BPanicButtonNotifierGuiceModule
implements Module {
/////////////////////////////////////////////////////////////////////////////////////////
//	CONFIG
/////////////////////////////////////////////////////////////////////////////////////////
	private final X47BNotifiersConfig _notifiersConfig;
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BPanicButtonNotifierGuiceModule(final X47BNotifiersConfig notifiersConfig) {
		_notifiersConfig = notifiersConfig;
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  MODULE
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void configure(final Binder binder) {
		// [0] - Log (event notifier services that just logs the event)
		binder.bind(X47BNotifierConfigForLog.class)
			  .toInstance(_notifiersConfig.getForLog());

		binder.bind(X47BPanicButtonNotifierServices.class)
			  .annotatedWith(UseLogNotifier.class)
			  .to(X47BPanicButtonNotifierServicesLoggerImpl.class)
			  .in(Singleton.class);

		// [1] - SMS (see provider method below)
		binder.bind(X47BNotifierConfigForSMS.class)
			  .toInstance(_notifiersConfig.getForSMS());

		binder.bind(X47BPanicButtonNotifierServices.class)
			  .annotatedWith(UseMessagingNotifier.class)
			  .to(X47BPanicButtonNotifierServicesSMSImpl.class)		// gets injected with SMS notifier service (see provider below)
			  .in(Singleton.class);

		// [2] - EMail (see provider method below)
		binder.bind(X47BNotifierConfigForEMail.class)
			  .toInstance(_notifiersConfig.getForEmail());

		binder.bind(X47BPanicButtonNotifierServices.class)
			  .annotatedWith(UseEMailNotifier.class)
			  .to(X47BPanicButtonNotifierServicesEMailImpl.class)	// gets injected with a java mail sender (see provider below)
			  .in(Singleton.class);

		// [3] - Voice (see provider method below)
		binder.bind(X47BNotifierConfigForVoice.class)
			  .toInstance(_notifiersConfig.getForVoice());

		binder.bind(X47BPanicButtonNotifierServices.class)
			  .annotatedWith(UseVoiceNotifier.class)
			  .to(X47BPanicButtonNotifierServicesVoiceImpl.class)	// gets injected with voice service (see provider below)
			  .in(Singleton.class);


		// Velocity engine to create the messages
		binder.bind(VelocityEngine.class)
			  .toProvider(new Provider<VelocityEngine>() {
									@Override
									public VelocityEngine get() {
										Properties velocityProps = new Properties();
										velocityProps.put(RuntimeConstants.DEFAULT_RUNTIME_LOG_NAME,"pb01velocity");
										velocityProps.put(RuntimeConstants.RESOURCE_LOADER,"class");
										velocityProps.setProperty("class.resource.loader.class",ClasspathResourceLoader.class.getName());

										VelocityEngine outVelocityEngine = new VelocityEngine();
										outVelocityEngine.setProperties(velocityProps);
										return outVelocityEngine;
									}
						  })
			  .in(Singleton.class);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  NOTIFIER SERVICE PROVIDER
/////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Provides a {@link NotifierServicesForEMail} implementation
	 * @param props
	 * @return
	 */
	@Provides @Singleton	// creates a single instance of the java mail sender
	NotifierServicesForEMail _provideEMailNotifier(final XMLProperties xmlProps) {
		// [1] - Get the spring mail sender impl
		JavaMailSenderImpl springMailSenderImpl = JavaMailSenderImpl.from(_notifiersConfig.getForEmail().getImpl());

		// [2] - Load the properties
		XMLPropertiesForAppComponent props = xmlProps.forAppComponent(X47BAppCodes.CORE_APPCODE,
																	 AppComponent.forId("notifier"));
		JavaMailSenderConfig springMailSenderCfg = JavaMailSenderConfigBuilder.of(springMailSenderImpl)
																			  .from(props,
																					"notifier/email");
		// [3] - Build the spring mail sender
		JavaMailSenderProvider springMailSenderProvider = new JavaMailSenderProvider(springMailSenderCfg);
		JavaMailSender sprigMailSender = springMailSenderProvider.get();

		// [4] - Build the notifier service
		return new JavaMailSenderNotifierServices(sprigMailSender);
	}
	/**
	 * Provides a {@link NotifierServicesForSMS} implementation
	 * @param props
	 * @return
	 */
	@Provides @Singleton	// creates a single instance of the java mail sender
	NotifierServicesForSMS _provideSMSNotifier(final XMLProperties xmlProps,
											   @ModelObjectsMarshaller final Marshaller marshaller) {
		XMLPropertiesForAppComponent props = xmlProps.forAppComponent(X47BAppCodes.CORE_APPCODE,
																	  AppComponent.forId("notifier"));
		NotifierServicesForSMS outSrvc = null;
		if (SMSNotifierImpl.LATINIA.is(_notifiersConfig.getForSMS().getImpl())) {
			// [1] - Get the latinia service config
			LatiniaServiceAPIData apiData = LatiniaServiceAPIData.createFrom(props,
																			 "notifier/sms");
			// [2] - Create a Latinia Service
			LatiniaServiceProvider latiniaServiceProvider = new LatiniaServiceProvider(apiData,
																			   		   marshaller);
			LatiniaService latiniaService = latiniaServiceProvider.get();

			// [3] - Build the notifier service
			outSrvc = new LatiniaNotifierServices(latiniaService);
		}
		else if (SMSNotifierImpl.AWS.is(_notifiersConfig.getForSMS().getImpl())) {
			// [1] - Get the aws sns service config
			AWSSNSClientConfig awsSNSCfg = AWSSNSClientConfig.fromXMLProperties(props,
																				"notifier/sms");
			// [2] - Create a aws sns client
			AWSSNSClient awsSNSCli = new AWSSNSClient(awsSNSCfg);

			// [3] - Build the notifier service
			outSrvc = new AWSSNSNotifierServices(awsSNSCli);
		}
		else {
			throw new IllegalStateException(_notifiersConfig.getForSMS().getImpl() + " is NOT a supported SMS notifier");
		}
		return outSrvc;
	}
	/**
	 * Provides a {@link NotifierServicesForVoice} implementation
	 * @param props
	 * @return
	 */
	@Provides @Singleton	// creates a single instance of the twilio service
	NotifierServicesForVoicePhoneCall _provideVoiceNotifier(final XMLProperties xmlProps) {
		XMLPropertiesForAppComponent props = xmlProps.forAppComponent(X47BAppCodes.CORE_APPCODE,
																	  AppComponent.forId("notifier"));
		NotifierServicesForVoicePhoneCall outSrvc = null;
		if (VoiceNotifierImpl.TWILIO.is(_notifiersConfig.getForVoice().getImpl())) {
			// [1] - get the twilio config
			TwilioConfig twCfg = TwilioConfig.createFrom(props,
														 "notifier/voice");
			// [2] - Create the twilio service
			TwilioServiceProvider twServiceProvider = new TwilioServiceProvider(twCfg);
			TwilioService twService = twServiceProvider.get();

			// [3] - Build the notifier service
			outSrvc = new TwilioNotifierServices(twService);
		}
		else {
			throw new IllegalStateException(_notifiersConfig.getForVoice().getImpl() + " is NOT a supported voice notifier");
		}
		return outSrvc;
	}
}
