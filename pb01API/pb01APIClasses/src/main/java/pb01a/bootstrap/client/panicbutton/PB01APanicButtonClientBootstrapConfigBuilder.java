package pb01a.bootstrap.client.panicbutton;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pb01a.api.interfaces.PB01APanicButtonServiceInterface;
import pb01a.client.api.PB01APanicButtonClientAPI;
import pb01a.common.internal.P01AAppCodes;
import r01f.bootstrap.services.config.client.ServicesClientBootstrapConfig;
import r01f.bootstrap.services.config.client.ServicesClientBootstrapConfigBuilder;
import r01f.patterns.IsBuilder;

/**
 * Builds bootstrap config
 */
@NoArgsConstructor(access=AccessLevel.PRIVATE)
public abstract class PB01APanicButtonClientBootstrapConfigBuilder
		   implements IsBuilder {
/////////////////////////////////////////////////////////////////////////////////////////
//  
/////////////////////////////////////////////////////////////////////////////////////////
	public static ServicesClientBootstrapConfig buildClientBootstrapConfig() {
		return  ServicesClientBootstrapConfigBuilder.forClientApiAppCode(P01AAppCodes.API_APPCODE)
						  .exposingApi(PB01APanicButtonClientAPI.class)
						  .ofServiceInterfacesExtending(PB01APanicButtonServiceInterface.class)
						  .bootstrappedWith(PB01APanicButtonClientBootstrapGuiceModule.class)
						  .build();
	}
}
