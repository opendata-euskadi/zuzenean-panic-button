package pb01c.bootstrap.rest.panicbutton;


import com.google.inject.Binder;

import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import pb01c.rest.PB01CPanicButtonRESTJerseyServletGuiceModuleForServiceRequests;
import pb01c.rest.PB01CPanicButtonRESTResourcesAndDelegatesGuiceModuleForServiceRequests;
import r01f.bootstrap.services.config.core.ServicesCoreBootstrapConfigWhenRESTExposed;
import r01f.bootstrap.services.core.RESTImplementedServicesCoreBootstrapGuiceModuleBase;

/**
 * This guice module is automatically discovered and bootstraped based on
 * the @ServicesCore annotation
 */
@Slf4j
@EqualsAndHashCode(callSuper=true)		// This is important for guice modules
public class PB01CPanicButtonRESTBootstrapGuiceModule
     extends RESTImplementedServicesCoreBootstrapGuiceModuleBase {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01CPanicButtonRESTBootstrapGuiceModule(final ServicesCoreBootstrapConfigWhenRESTExposed coreBootstrapCfg) {
		super(coreBootstrapCfg);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void configure(final Binder binder) {
		log.warn("START____________ PB01 PanicButton: ServiceRequests REST Bootstraping _____________________________");

		binder.install(new PB01CPanicButtonRESTJerseyServletGuiceModuleForServiceRequests());				// Servlet Modules for GUICE-Jersey
		binder.install(new PB01CPanicButtonRESTResourcesAndDelegatesGuiceModuleForServiceRequests());		// REST Resource mappings

		log.warn("END_______________ PB01 PanicButton: ServiceRequests REST Bootstraping _____________________________");
	}
}
