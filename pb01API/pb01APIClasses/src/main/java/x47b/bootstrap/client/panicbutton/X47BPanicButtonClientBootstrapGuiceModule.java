package x47b.bootstrap.client.panicbutton;

import java.util.Map;

import javax.inject.Named;
import javax.inject.Provider;
import javax.inject.Singleton;

import com.google.inject.Binder;
import com.google.inject.Provides;

import lombok.EqualsAndHashCode;
import r01f.bootstrap.services.client.ServicesClientAPIBootstrapGuiceModuleBase;
import r01f.bootstrap.services.config.client.ServicesClientGuiceBootstrapConfig;
import r01f.inject.HasMoreBindings;
import r01f.model.annotations.ModelObjectsMarshaller;
import r01f.objectstreamer.Marshaller;
import r01f.securitycontext.SecurityContext;
import r01f.services.annotations.ClientAPIForMasterUser;
import r01f.services.annotations.SecurityContextProviderForMasterUser;
import r01f.services.interfaces.ServiceInterface;
import x47b.client.api.X47BPanicButtonClientAPI;
import x47b.common.internal.X47BAppCodes;

@EqualsAndHashCode(callSuper=true) // This is important for guice modules
public class X47BPanicButtonClientBootstrapGuiceModule
	 extends ServicesClientAPIBootstrapGuiceModuleBase
  implements HasMoreBindings {
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	protected X47BPanicButtonClientBootstrapGuiceModule(final ServicesClientGuiceBootstrapConfig servicesClientBootstrapCfg) {
		super(servicesClientBootstrapCfg);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	BINDINGS
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void configureMoreBindings(final Binder binder) {
		// nothing
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	MASTER CLIENT API
/////////////////////////////////////////////////////////////////////////////////////////
 	@Provides					// provides a client api
 	@ClientAPIForMasterUser		// for MASTER system usage
 	@Singleton		// BEWARE!!!
 	private X47BPanicButtonClientAPI _provideMasterClientAPI(@SecurityContextProviderForMasterUser final Provider<SecurityContext> securityContextProvider,
 															 @ModelObjectsMarshaller final Marshaller modelObjectsMarshaller,
 															 @Named(X47BAppCodes.API_APPCODE_STR) final Map<Class,ServiceInterface> srvcIfaceMappings) {
 		// This creates an ad-hoc client api that uses a
 		// legitimated SecurityContext provider
 		return new X47BPanicButtonClientAPI(securityContextProvider,
 							   				modelObjectsMarshaller,
 							   				srvcIfaceMappings);
    }
}
