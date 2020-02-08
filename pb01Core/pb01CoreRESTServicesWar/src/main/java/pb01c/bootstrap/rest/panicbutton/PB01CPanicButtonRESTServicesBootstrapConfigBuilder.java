package pb01c.bootstrap.rest.panicbutton;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pb01a.common.internal.P01AAppCodes;
import r01f.bootstrap.services.config.core.ServicesCoreBootstrapConfigBuilder;
import r01f.bootstrap.services.config.core.ServicesCoreBootstrapConfigWhenRESTExposed;
import r01f.patterns.IsBuilder;
import r01f.xmlproperties.XMLPropertiesForApp;

/**
 * Builds bootstrap config
 */
@NoArgsConstructor(access=AccessLevel.PRIVATE)
public abstract class PB01CPanicButtonRESTServicesBootstrapConfigBuilder
		   implements IsBuilder {
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	public static ServicesCoreBootstrapConfigWhenRESTExposed buildCoreBootstrapConfig(final XMLPropertiesForApp xmlProps) {
		return ServicesCoreBootstrapConfigBuilder.forCoreAppAndModule(P01AAppCodes.CORE_APPCODE,P01AAppCodes.PANICBUTTON_MOD)
	   				.restImplemented()
	   					.bootstrappedBy(PB01CPanicButtonRESTBootstrapGuiceModule.class)
	   					.build();
	}
}
