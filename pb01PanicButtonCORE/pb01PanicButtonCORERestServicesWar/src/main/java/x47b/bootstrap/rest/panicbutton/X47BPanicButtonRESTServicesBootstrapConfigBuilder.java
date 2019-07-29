package x47b.bootstrap.rest.panicbutton;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import r01f.bootstrap.services.config.core.ServicesCoreBootstrapConfigBuilder;
import r01f.bootstrap.services.config.core.ServicesCoreBootstrapConfigWhenRESTExposed;
import r01f.patterns.IsBuilder;
import r01f.xmlproperties.XMLPropertiesForApp;
import x47b.common.internal.X47BAppCodes;

/**
 * Builds bootstrap config
 */
@NoArgsConstructor(access=AccessLevel.PRIVATE)
public abstract class X47BPanicButtonRESTServicesBootstrapConfigBuilder
		   implements IsBuilder {
/////////////////////////////////////////////////////////////////////////////////////////
//  
/////////////////////////////////////////////////////////////////////////////////////////
	public static ServicesCoreBootstrapConfigWhenRESTExposed buildCoreBootstrapConfig(final XMLPropertiesForApp xmlProps) {
		return ServicesCoreBootstrapConfigBuilder.forCoreAppAndModule(X47BAppCodes.CORE_APPCODE,X47BAppCodes.PANICBUTTON_MOD)
	   				.restImplemented()
	   					.bootstrappedBy(X47BPanicButtonRESTBootstrapGuiceModule.class)
	   					.build();
	}
}
