package pb01.ui.bootstrap;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import r01f.bootstrap.services.config.core.ServicesCoreBootstrapConfigBuilder;
import r01f.bootstrap.services.config.core.ServicesCoreBootstrapConfigWhenServletExposed;
import r01f.patterns.IsBuilder;
import x47b.common.internal.X47BAppCodes;

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
		return ServicesCoreBootstrapConfigBuilder.forCoreAppAndModule(X47BAppCodes.UI_APPCODE,X47BAppCodes.PANICBUTTON_MOD)
								   				 .servletImplemented()
								   				 .bootstrappedBy(PB01UIWarBootstrapGuiceModule.class)
								   				 .build();
	}
}
