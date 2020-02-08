package pb01c.bootstrap.core.panicbutton;

import java.util.Properties;

import javax.inject.Provider;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Singleton;

import lombok.EqualsAndHashCode;
import pb01c.internal.notifier.PB01CPanicButtonNotifier;
import pb01c.internal.notifier.PB01CPanicButtonNotifierEMailImpl;
import pb01c.internal.notifier.PB01CPanicButtonNotifierLoggerImpl;
import pb01c.internal.notifier.PB01CPanicButtonNotifierSMSImpl;
import pb01c.internal.notifier.PB01CPanicButtonNotifiereVoiceImpl;
import r01f.core.services.notifier.annotations.UseEMailNotifier;
import r01f.core.services.notifier.annotations.UseLogNotifier;
import r01f.core.services.notifier.annotations.UseMessagingNotifier;
import r01f.core.services.notifier.annotations.UseVoiceNotifier;
import r01f.core.services.notifier.bootstrap.NotifierGuiceModule;
import r01f.core.services.notifier.config.NotifiersConfigs;

@EqualsAndHashCode				// This is important for guice modules
     class PB01CPanicButtonNotifierGuiceModule
implements Module {
/////////////////////////////////////////////////////////////////////////////////////////
//	CONFIG
/////////////////////////////////////////////////////////////////////////////////////////
	private final NotifiersConfigs _notifiersConfig;
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01CPanicButtonNotifierGuiceModule(final NotifiersConfigs notifiersConfig) {
		_notifiersConfig = notifiersConfig;
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  MODULE
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void configure(final Binder binder) {
		// [0] - Notifier module
		binder.install(new NotifierGuiceModule(_notifiersConfig));

		// [1] - Log (event notifier services that just logs the event)
		binder.bind(PB01CPanicButtonNotifier.class)
			  .annotatedWith(UseLogNotifier.class)
			  .to(PB01CPanicButtonNotifierLoggerImpl.class)
			  .in(Singleton.class);

		// [2] - SMS (see provider method below)
		binder.bind(PB01CPanicButtonNotifier.class)
			  .annotatedWith(UseMessagingNotifier.class)
			  .to(PB01CPanicButtonNotifierSMSImpl.class)		// gets injected with SMS notifier service (see provider below)
			  .in(Singleton.class);

		// [3] - EMail (see provider method below)
		binder.bind(PB01CPanicButtonNotifier.class)
			  .annotatedWith(UseEMailNotifier.class)
			  .to(PB01CPanicButtonNotifierEMailImpl.class)	// gets injected with a java mail sender (see provider below)
			  .in(Singleton.class);

		// [4] - Voice (see provider method below)
		binder.bind(PB01CPanicButtonNotifier.class)
			  .annotatedWith(UseVoiceNotifier.class)
			  .to(PB01CPanicButtonNotifiereVoiceImpl.class)	// gets injected with voice service (see provider below)
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
}
