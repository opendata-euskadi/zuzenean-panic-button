package pb01c.bootstrap.rest.panicbutton;

import javax.servlet.ServletContextEvent;

import pb01a.bootstrap.client.panicbutton.PB01APanicButtonClientBootstrapConfigBuilder;
import pb01a.common.internal.P01AAppCodes;
import pb01c.bootstrap.core.panicbutton.PB01CPanicButtonCOREServicesBootstrapConfigBuilder;
import r01f.bootstrap.ServletContextListenerBase;
import r01f.bootstrap.services.config.ServicesBootstrapConfig;
import r01f.bootstrap.services.config.ServicesBootstrapConfigBuilder;
import r01f.bootstrap.services.config.client.ServicesClientBootstrapConfig;
import r01f.bootstrap.services.config.core.ServicesCoreBootstrapConfig;
import r01f.bootstrap.services.config.core.ServicesCoreModuleEventsConfig;
import r01f.xmlproperties.XMLPropertiesBuilder;
import r01f.xmlproperties.XMLPropertiesForApp;

public class PB01CPanicButtonServletContextListener
	 extends ServletContextListenerBase {
/////////////////////////////////////////////////////////////////////////////////////////
//	FIELDS  
/////////////////////////////////////////////////////////////////////////////////////////	
	private boolean _configured = false;
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01CPanicButtonServletContextListener() {
		super();
	}
	private static final ServicesBootstrapConfig _buildBootstrapConfig() {
		// [0] - Load properties
		XMLPropertiesForApp xmlProps = XMLPropertiesBuilder.createForApp(P01AAppCodes.CORE_APPCODE)
														   .notUsingCache();
		// [2] - Build the client & core boot config
		ServicesClientBootstrapConfig clientBootCfg = PB01APanicButtonClientBootstrapConfigBuilder.buildClientBootstrapConfig();
		ServicesCoreBootstrapConfig[] coreBootCfg = _buildCoreBootstrapConfigs(xmlProps);
		
		// [3] - Guess how to handle asynchronous events
		ServicesCoreModuleEventsConfig evtHandleCfg = ServicesCoreModuleEventsConfig.from(xmlProps.forComponent("notifier"));
		
		// [4] - Build the boot config 
		ServicesBootstrapConfig bootCfg = ServicesBootstrapConfigBuilder
												.forClient(clientBootCfg)
												.ofCoreModules(coreBootCfg)
												.coreEventsHandledAs(evtHandleCfg)
												.build();
		return bootCfg;
	}
	private static final ServicesCoreBootstrapConfig[] _buildCoreBootstrapConfigs(final XMLPropertiesForApp xmlProps) {
		return new ServicesCoreBootstrapConfig[] {
						   PB01CPanicButtonCOREServicesBootstrapConfigBuilder.buildCoreBootstrapConfig(xmlProps),
						   // ui servlet services
						   PB01CPanicButtonRESTServicesBootstrapConfigBuilder.buildCoreBootstrapConfig(xmlProps)
				   };
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  ServletContextListenerBase
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void contextInitialized(final ServletContextEvent servletContextEvent) {
		super.contextInitialized(servletContextEvent);
		
//		Injector injector = (Injector)servletContextEvent.getServletContext()
//														 .getAttribute(Injector.class.getName());
		
		// Ensure the initial data exists at the DB
		if (!_configured) { 
			// any task to be done when the server starts
		} 
	}
}
