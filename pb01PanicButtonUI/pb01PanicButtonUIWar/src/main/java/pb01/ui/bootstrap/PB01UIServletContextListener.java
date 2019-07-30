package pb01.ui.bootstrap;

import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;

import com.google.inject.Module;

import r01f.bootstrap.ServletContextListenerBase;
import r01f.bootstrap.services.config.ServicesBootstrapConfig;
import r01f.bootstrap.services.config.ServicesBootstrapConfigBuilder;
import r01f.bootstrap.services.config.client.ServicesClientBootstrapConfig;
import r01f.bootstrap.services.config.core.ServicesCoreBootstrapConfig;
import r01f.bootstrap.services.config.core.ServicesCoreModuleEventsConfig;
import r01f.services.ids.ServiceIDs.CoreModule;
import r01f.xmlproperties.XMLPropertiesBuilder;
import r01f.xmlproperties.XMLPropertiesForApp;
import x47b.bootstrap.client.panicbutton.X47BPanicButtonClientBootstrapConfigBuilder;
import x47b.bootstrap.core.panicbutton.X47BPanicButtonCOREServicesBootstrapConfigBuilder;
import x47b.common.internal.X47BAppCodes;

@WebListener
public class PB01UIServletContextListener
	 extends ServletContextListenerBase {
/////////////////////////////////////////////////////////////////////////////////////////
//  FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	private boolean _configured = false;
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01UIServletContextListener() {
		super( // client & core bootstrap
			   _buildServicesBootstrapConfig(),
			   (Module[])null);

	}
	private static ServicesBootstrapConfig _buildServicesBootstrapConfig() {
		// client bootstrap cfg
		ServicesClientBootstrapConfig clientBootstrapCfg = X47BPanicButtonClientBootstrapConfigBuilder.buildClientBootstrapConfig();

		// persistence core bootstrap cfg
		XMLPropertiesForApp coreProps = XMLPropertiesBuilder.createForApp(X47BAppCodes.CORE_APPCODE)
														    .notUsingCache();
		ServicesCoreBootstrapConfig persistenceCoreBootstrapCfg = X47BPanicButtonCOREServicesBootstrapConfigBuilder.buildCoreBootstrapConfig(coreProps);

		// ui
		ServicesCoreBootstrapConfig uiBootstrapCfg = PB01UIServletServicesBootstrapConfigBuilder.buildCoreBootstrapConfig();

		// build all
		ServicesCoreModuleEventsConfig coreEventsCfg = ServicesCoreModuleEventsConfig.from(coreProps.forComponent(CoreModule.SERVICES));
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
	}
	@Override
	public void contextDestroyed(final ServletContextEvent servletContextEvent) {
		super.contextDestroyed(servletContextEvent);
	}
}
