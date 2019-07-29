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
import r01f.mail.JavaMailSenderProvider;
import r01f.model.annotations.ModelObjectsMarshaller;
import r01f.notifier.UseEMailNotifier;
import r01f.notifier.UseLogNotifier;
import r01f.notifier.UseMessagingNotifier;
import r01f.notifier.UseVoiceNotifier;
import r01f.objectstreamer.Marshaller;
import r01f.services.latinia.LatiniaService;
import r01f.services.latinia.LatiniaServiceProvider;
import r01f.twilio.TwilioService;
import r01f.twilio.TwilioServiceProvider;
import x47b.internal.services.X47BNotifierConfigForEMail;
import x47b.internal.services.X47BNotifierConfigForLatinia;
import x47b.internal.services.X47BNotifierConfigForLog;
import x47b.internal.services.X47BNotifierConfigForTwilio;
import x47b.internal.services.X47BPanicButtonNotifierServices;
import x47b.internal.services.X47BPanicButtonNotifierServicesEMailImpl;
import x47b.internal.services.X47BPanicButtonNotifierServicesLatiniaImpl;
import x47b.internal.services.X47BPanicButtonNotifierServicesLoggerImpl;
import x47b.internal.services.X47BPanicButtonNotifierServicesVoiceImpl;

@EqualsAndHashCode				// This is important for guice modules
     class X47BPanicButtonNotifierGuiceModule
implements Module {
/////////////////////////////////////////////////////////////////////////////////////////
//	CONFIG
/////////////////////////////////////////////////////////////////////////////////////////
	private final X47BNotifierConfigForLog _logNotifierConfig;
	private final X47BNotifierConfigForEMail _mailNotifierConfig;
	private final X47BNotifierConfigForLatinia _latiniaNotifierConfig;
	private final X47BNotifierConfigForTwilio _twilioNotifierConfig;
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BPanicButtonNotifierGuiceModule(final X47BNotifierConfigForLog logNotifierConfig,
											  final X47BNotifierConfigForEMail mailNotifierConfig,
											  final X47BNotifierConfigForLatinia latiniaNotifierConfig,
											  final X47BNotifierConfigForTwilio twilioNotifierConfig) {
		_logNotifierConfig = logNotifierConfig;
		_mailNotifierConfig = mailNotifierConfig;
		_latiniaNotifierConfig = latiniaNotifierConfig;
		_twilioNotifierConfig = twilioNotifierConfig;
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  MODULE
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void configure(final Binder binder) {
		// [0] - Log (event notifier services that just logs the event)
		binder.bind(X47BNotifierConfigForLog.class)
			  .toInstance(_logNotifierConfig);

		binder.bind(X47BPanicButtonNotifierServices.class)
			  .annotatedWith(UseLogNotifier.class)
			  .to(X47BPanicButtonNotifierServicesLoggerImpl.class)
			  .in(Singleton.class);

		// [1] - Latinia (see provider method below)
		binder.bind(X47BNotifierConfigForLatinia.class)
			  .toInstance(_latiniaNotifierConfig);

		binder.bind(X47BPanicButtonNotifierServices.class)
			  .annotatedWith(UseMessagingNotifier.class)
			  .to(X47BPanicButtonNotifierServicesLatiniaImpl.class)	// gets injected with Latinia service (see provider below)
			  .in(Singleton.class);

		// [2] - EMail (see provider method below)
		binder.bind(X47BNotifierConfigForEMail.class)
			  .toInstance(_mailNotifierConfig);

		binder.bind(X47BPanicButtonNotifierServices.class)
			  .annotatedWith(UseEMailNotifier.class)
			  .to(X47BPanicButtonNotifierServicesEMailImpl.class)		// gets injected with a java mail sender (see provider below)
			  .in(Singleton.class);

		// [3] - Voice (see provider method below)
		binder.bind(X47BNotifierConfigForTwilio.class)
			  .toInstance(_twilioNotifierConfig);

		binder.bind(X47BPanicButtonNotifierServices.class)
			  .annotatedWith(UseVoiceNotifier.class)
			  .to(X47BPanicButtonNotifierServicesVoiceImpl.class)		// gets injected with Twilio service (see provider below)
			  .in(Singleton.class);


		// Velocity engine to create the messages
		binder.bind(VelocityEngine.class)
			  .toProvider(new Provider<VelocityEngine>() {
									@Override
									public VelocityEngine get() {
										Properties velocityProps = new Properties();
										velocityProps.put(RuntimeConstants.DEFAULT_RUNTIME_LOG_NAME,"pb01velocity");
										velocityProps.put(RuntimeConstants.RESOURCE_LOADER,"class");
								        velocityProps.put(RuntimeConstants.RESOURCE_LOADER_CLASS,ClasspathResourceLoader.class.getName());

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
	 * Provides a {@link JavaMailSender} implementation
	 * @param props
	 * @return
	 */
	@Provides @Singleton	// creates a single instance of the java mail sender
	LatiniaService _provideLatiniaService(@ModelObjectsMarshaller final Marshaller marshaller) {
		LatiniaServiceProvider latiniaServiceProvider = new LatiniaServiceProvider(_latiniaNotifierConfig.getLatiniaApiData(),
																		   		   marshaller);
		LatiniaService outLatiniaService = latiniaServiceProvider.get();
		return outLatiniaService;
	}
	/**
	 * Provides a {@link JavaMailSender} implementation
	 * @param props
	 * @return
	 */
	@Provides @Singleton	// creates a single instance of the java mail sender
	JavaMailSender _provideJavaMailSender() {
		JavaMailSenderProvider javaMailSenderProvider = new JavaMailSenderProvider(_mailNotifierConfig.getMailSenderConfig());
		JavaMailSender outJavaMailSender = javaMailSenderProvider.get();
		return outJavaMailSender;
	}
	/**
	 * Provides a {@link TwilioService} implementation
	 * @param props
	 * @return
	 */
	@Provides @Singleton	// creates a single instance of the twilio service
	TwilioService _provideTwilioService() {
		TwilioServiceProvider twilioServiceProvider = new TwilioServiceProvider(_twilioNotifierConfig.getTwilioConfig());
		TwilioService outTwilioService = twilioServiceProvider.get();
		return outTwilioService;
	}
}
