package x47b.bootstrap.client.panicbutton;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import r01f.bootstrap.services.config.client.ServicesClientBootstrapConfig;
import r01f.bootstrap.services.config.client.ServicesClientBootstrapConfigBuilder;
import r01f.patterns.IsBuilder;
import x47b.api.interfaces.X47BPanicButtonServiceInterface;
import x47b.client.api.X47BPanicButtonClientAPI;
import x47b.common.internal.X47BAppCodes;

/**
 * Builds bootstrap config
 */
@NoArgsConstructor(access=AccessLevel.PRIVATE)
public abstract class X47BPanicButtonClientBootstrapConfigBuilder
		   implements IsBuilder {
/////////////////////////////////////////////////////////////////////////////////////////
//  
/////////////////////////////////////////////////////////////////////////////////////////
	public static ServicesClientBootstrapConfig buildClientBootstrapConfig() {
		return  ServicesClientBootstrapConfigBuilder.forClientApiAppCode(X47BAppCodes.API_APPCODE)
						  .exposingApi(X47BPanicButtonClientAPI.class)
						  .ofServiceInterfacesExtending(X47BPanicButtonServiceInterface.class)
						  .bootstrappedWith(X47BPanicButtonClientBootstrapGuiceModule.class)
						  .build();
	}
}
