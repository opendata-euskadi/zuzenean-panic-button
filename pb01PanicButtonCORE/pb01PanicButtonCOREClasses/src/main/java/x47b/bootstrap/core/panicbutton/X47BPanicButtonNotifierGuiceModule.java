package x47b.bootstrap.core.panicbutton;

import java.util.Properties;

import javax.inject.Provider;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Singleton;

import lombok.EqualsAndHashCode;
import r01f.core.services.notifier.UseEMailNotifier;
import r01f.core.services.notifier.UseLogNotifier;
import r01f.core.services.notifier.UseMessagingNotifier;
import r01f.core.services.notifier.UseVoiceNotifier;
import r01f.core.services.notifier.bootstrap.NotifierGuiceModule;
import r01f.core.services.notifier.config.NotifiersConfigs;
import x47b.internal.notifier.X47BPanicButtonNotifier;
import x47b.internal.notifier.X47BPanicButtonNotifierEMailImpl;
import x47b.internal.notifier.X47BPanicButtonNotifierLoggerImpl;
import x47b.internal.notifier.X47BPanicButtonNotifierSMSImpl;
import x47b.internal.notifier.X47BPanicButtonNotifiereVoiceImpl;

@EqualsAndHashCode				// This is important for guice modules
     class X47BPanicButtonNotifierGuiceModule
implements Module {
/////////////////////////////////////////////////////////////////////////////////////////
//	CONFIG
/////////////////////////////////////////////////////////////////////////////////////////
	private final NotifiersConfigs _notifiersConfig;
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BPanicButtonNotifierGuiceModule(final NotifiersConfigs notifiersConfig) {
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
		binder.bind(X47BPanicButtonNotifier.class)
			  .annotatedWith(UseLogNotifier.class)
			  .to(X47BPanicButtonNotifierLoggerImpl.class)
			  .in(Singleton.class);

		// [2] - SMS (see provider method below)
		binder.bind(X47BPanicButtonNotifier.class)
			  .annotatedWith(UseMessagingNotifier.class)
			  .to(X47BPanicButtonNotifierSMSImpl.class)		// gets injected with SMS notifier service (see provider below)
			  .in(Singleton.class);

		// [3] - EMail (see provider method below)
		binder.bind(X47BPanicButtonNotifier.class)
			  .annotatedWith(UseEMailNotifier.class)
			  .to(X47BPanicButtonNotifierEMailImpl.class)	// gets injected with a java mail sender (see provider below)
			  .in(Singleton.class);

		// [4] - Voice (see provider method below)
		binder.bind(X47BPanicButtonNotifier.class)
			  .annotatedWith(UseVoiceNotifier.class)
			  .to(X47BPanicButtonNotifiereVoiceImpl.class)	// gets injected with voice service (see provider below)
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
