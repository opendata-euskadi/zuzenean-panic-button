package x47b.bootstrap.client.panicbutton;

import lombok.EqualsAndHashCode;
import r01f.bootstrap.services.config.client.ServicesClientGuiceBootstrapConfig;
import x47b.api.context.X47BMockSecurityContextProvider;
import x47b.bootstrap.client.X47BClientBootstrapGuiceModuleBase;

@EqualsAndHashCode(callSuper=true) // This is important for guice modules
public class X47BPanicButtonClientBootstrapGuiceModule
	 extends X47BClientBootstrapGuiceModuleBase {
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	protected X47BPanicButtonClientBootstrapGuiceModule(final ServicesClientGuiceBootstrapConfig servicesClientBootstrapCfg) {
		super(servicesClientBootstrapCfg,
			  new X47BMockSecurityContextProvider());		// TODO change for the real impl
	}
}
