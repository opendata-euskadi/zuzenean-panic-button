package pb01.ui.bootstrap;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pb01a.common.internal.P01AAppCodes;
import r01f.bootstrap.services.config.core.ServicesCoreBootstrapConfigBuilder;
import r01f.bootstrap.services.config.core.ServicesCoreBootstrapConfigWhenServletExposed;
import r01f.patterns.IsBuilder;

/**
 * Builds bootstrap config
 */
@NoArgsConstructor( access=AccessLevel.PRIVATE )
public abstract class PB01UIServletServicesBootstrapConfigBuilder
		   implements IsBuilder {
/////////////////////////////////////////////////////////////////////////////////////////
//  R01UIAppCodes
/////////////////////////////////////////////////////////////////////////////////////////
	public static ServicesCoreBootstrapConfigWhenServletExposed buildUIBootstrapConfig() {
		return ServicesCoreBootstrapConfigBuilder.forCoreAppAndModule(P01AAppCodes.UI_APPCODE,P01AAppCodes.PANICBUTTON_MOD)
								   				 .servletImplemented()
								   				 .bootstrappedBy(PB01UIWarBootstrapGuiceModule.class)
								   				 .build();
	}
}
