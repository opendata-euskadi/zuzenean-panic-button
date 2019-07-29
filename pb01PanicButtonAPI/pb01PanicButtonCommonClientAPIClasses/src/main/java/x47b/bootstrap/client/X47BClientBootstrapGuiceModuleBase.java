package x47b.bootstrap.client;

import com.google.inject.Binder;
import com.google.inject.Provides;

import r01f.bootstrap.services.client.ServicesClientAPIBootstrapGuiceModuleBase;
import r01f.bootstrap.services.config.client.ServicesClientGuiceBootstrapConfig;
import r01f.inject.HasMoreBindings;
import r01f.model.annotations.ModelObjectsMarshaller;
import r01f.objectstreamer.Marshaller;
import r01f.objectstreamer.MarshallerBuilder;
import r01f.securitycontext.SecurityContext;
import x47b.api.context.X47BMockSecurityContextProvider;
import x47b.common.internal.X47BAppCodes;

/**
 * Client-API bindings
 */
public abstract class X47BClientBootstrapGuiceModuleBase 
 	          extends ServicesClientAPIBootstrapGuiceModuleBase 	// this is a client guice bindings module
           implements HasMoreBindings {
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////	
	protected X47BClientBootstrapGuiceModuleBase(final ServicesClientGuiceBootstrapConfig servicesClientBootstrapCfg) {
		super(servicesClientBootstrapCfg);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  GUICE MODULE
/////////////////////////////////////////////////////////////////////////////////////////	
	@Override
	public void configureMoreBindings(final Binder binder) {
		_bindModelObjectsMarshaller(binder);
		_bindModelObjectExtensionsModule(binder);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  USER CONTEXT PROVIDERS
/////////////////////////////////////////////////////////////////////////////////////////
	@Provides @SuppressWarnings("static-method")
	SecurityContext provideSecurityContext() {
		X47BMockSecurityContextProvider provider = new X47BMockSecurityContextProvider();
		return provider.get();
	}
/////////////////////////////////////////////////////////////////////////////////////////
// 	COMMON BINDINGS
/////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * bindings for the marshaller
	 */
	private static void _bindModelObjectsMarshaller(final Binder binder) {
		// Create the model objects marshaller
		Marshaller marshaller = MarshallerBuilder.findTypesToMarshallAt(X47BAppCodes.API_APPCODE)
												 .build();
		// Bind this instance to the model object's marshaller
		binder.bind(Marshaller.class).annotatedWith(ModelObjectsMarshaller.class)
									 .toInstance(marshaller);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  MODEL EXTENSIONS
/////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * @param binder 
	 * @return bindings for the model extensions
	 */
	private static void _bindModelObjectExtensionsModule(final Binder binder) {
		// nothing
	}
}
