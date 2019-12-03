package x47b.bootstrap.rest.panicbutton;


import com.google.inject.Binder;

import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import r01f.bootstrap.services.config.core.ServicesCoreBootstrapConfigWhenRESTExposed;
import r01f.bootstrap.services.core.RESTImplementedServicesCoreBootstrapGuiceModuleBase;
import x47b.rest.X47BPanicButtonRESTJerseyServletGuiceModuleForServiceRequests;
import x47b.rest.X47BPanicButtonRESTResourcesAndDelegatesGuiceModuleForServiceRequests;

/**
 * This guice module is automatically discovered and bootstraped based on 
 * the @ServicesCore annotation
 */
@Slf4j
@EqualsAndHashCode(callSuper=true)		// This is important for guice modules
public class X47BPanicButtonRESTBootstrapGuiceModule
     extends RESTImplementedServicesCoreBootstrapGuiceModuleBase {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////	
	public X47BPanicButtonRESTBootstrapGuiceModule(final ServicesCoreBootstrapConfigWhenRESTExposed coreBootstrapCfg) {
		super(coreBootstrapCfg);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void configure(final Binder binder) {
		log.warn("START____________ X47B PanicButton: ServiceRequests REST Bootstraping _____________________________");

		binder.install(new X47BPanicButtonRESTJerseyServletGuiceModuleForServiceRequests());				// Servlet Modules for GUICE-Jersey
		binder.install(new X47BPanicButtonRESTResourcesAndDelegatesGuiceModuleForServiceRequests());		// REST Resource mappings

		log.warn("END_______________ X47B PanicButton: ServiceRequests REST Bootstraping _____________________________");
	}
}
