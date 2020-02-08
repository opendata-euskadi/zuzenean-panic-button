package pb01c.rest;

import r01f.bootstrap.rest.RESTJerseyServletGuiceModuleBase;

/**
 * Every GUICE module is installed at {appCode}Injector singleton holder
 */
public class PB01CPanicButtonRESTJerseyServletGuiceModuleForServiceRequests
	 extends RESTJerseyServletGuiceModuleBase {
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01CPanicButtonRESTJerseyServletGuiceModuleForServiceRequests() {
		super(PB01CPanicButtonRESTApp.class);
	}
}
