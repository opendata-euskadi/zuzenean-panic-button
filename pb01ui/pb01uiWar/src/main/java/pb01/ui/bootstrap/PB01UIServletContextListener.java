package pb01.ui.bootstrap;

import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;

import com.google.common.eventbus.EventBus;

import pb01.ui.vaadin.serverpush.PB01AlarmMessageEventListener;
import r01f.bootstrap.ServletContextListenerBase;
import r01f.bootstrap.services.config.ServicesBootstrapConfig;
import r01f.bootstrap.services.config.ServicesBootstrapConfigBuilder;
import r01f.bootstrap.services.config.client.ServicesClientBootstrapConfig;
import r01f.bootstrap.services.config.core.ServicesCoreBootstrapConfig;
import r01f.bootstrap.services.config.core.ServicesCoreModuleEventsConfig;
import r01f.services.ids.ServiceIDs.CoreModule;
import r01f.ui.vaadin.serverpush.VaadinServerPushedMessagesBroadcaster;
import r01f.xmlproperties.XMLPropertiesBuilder;
import r01f.xmlproperties.XMLPropertiesForApp;
import x47b.api.context.X47BSecurityContextProviderFromThreadLocalStorage;
import x47b.bootstrap.client.panicbutton.X47BPanicButtonClientBootstrapConfigBuilder;
import x47b.bootstrap.common.panicbutton.X47BCommonBootstrapGuiceModule;
import x47b.bootstrap.core.panicbutton.X47BPanicButtonCOREServicesBootstrapConfigBuilder;
import x47b.common.internal.X47BAppCodes;

@WebListener
public class PB01UIServletContextListener
	 extends ServletContextListenerBase {
/////////////////////////////////////////////////////////////////////////////////////////
//  FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	private final boolean _configured = false;
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01UIServletContextListener() {
		super(// client & core bootstrap
			  _buildServicesBootstrapConfig(),
			  // the client & core common guice module using a [security context] provider
			  new X47BCommonBootstrapGuiceModule(new X47BSecurityContextProviderFromThreadLocalStorage()));

	}
	private static ServicesBootstrapConfig _buildServicesBootstrapConfig() {
		// client bootstrap cfg
		ServicesClientBootstrapConfig clientBootstrapCfg = X47BPanicButtonClientBootstrapConfigBuilder.buildClientBootstrapConfig();

		// persistence core bootstrap cfg
		XMLPropertiesForApp coreProps = XMLPropertiesBuilder.createForApp(X47BAppCodes.CORE_APPCODE)
														    .notUsingCache();
		ServicesCoreBootstrapConfig persistenceCoreBootstrapCfg = X47BPanicButtonCOREServicesBootstrapConfigBuilder.buildCoreBootstrapConfig(coreProps);

		// ui
		ServicesCoreBootstrapConfig uiBootstrapCfg = PB01UIServletServicesBootstrapConfigBuilder.buildUIBootstrapConfig();

		// build all
		ServicesCoreModuleEventsConfig coreEventsCfg = ServicesCoreModuleEventsConfig.from(coreProps.forComponent(CoreModule.compose(X47BAppCodes.PANICBUTTON_MOD,
																																	 CoreModule.SERVICES)));
		ServicesBootstrapConfig outBootstrapCfg = ServicesBootstrapConfigBuilder
														.forClient(clientBootstrapCfg)
														.ofCoreModules(persistenceCoreBootstrapCfg,
																	   uiBootstrapCfg)
														.coreEventsHandledAs(coreEventsCfg)
														.build();
		// return
		return outBootstrapCfg;
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  ServletContextListenerBase
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void contextInitialized( final ServletContextEvent servletContextEvent ) {
		super.contextInitialized( servletContextEvent );

		// Ensure the initial data exists at the DB
		if ( !_configured ) {
			// any task to be done when the server starts
		}

		// Subcribe the UI to the X47BAlarmMessage posts at the event bus from the CORE
		// so that a [server-push] can be initiated to show a notification at the UI
		final VaadinServerPushedMessagesBroadcaster serverPushedMessagesBroadcaster = _injector.getInstance(VaadinServerPushedMessagesBroadcaster.class);
		EventBus eventBus = _injector.getInstance(EventBus.class);
		eventBus.register(new PB01AlarmMessageEventListener(serverPushedMessagesBroadcaster));
	}
	@Override
	public void contextDestroyed(final ServletContextEvent servletContextEvent) {
		super.contextDestroyed(servletContextEvent);
	}
}
