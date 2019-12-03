package x47b.rest;

import r01f.bootstrap.rest.RESTJerseyServletGuiceModuleBase;

/**
 * Every GUICE module is installed at {appCode}Injector singleton holder
 */
public class X47BPanicButtonRESTJerseyServletGuiceModuleForServiceRequests
	 extends RESTJerseyServletGuiceModuleBase {
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BPanicButtonRESTJerseyServletGuiceModuleForServiceRequests() {
		super(X47BPanicButtonRESTApp.class);
	}
}
