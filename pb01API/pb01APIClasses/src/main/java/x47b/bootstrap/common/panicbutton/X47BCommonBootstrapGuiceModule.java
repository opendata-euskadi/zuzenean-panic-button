package x47b.bootstrap.common.panicbutton;

import javax.inject.Provider;
import javax.inject.Singleton;

import com.google.inject.Binder;
import com.google.inject.Module;

import r01f.model.annotations.ModelObjectsMarshaller;
import r01f.objectstreamer.Marshaller;
import r01f.objectstreamer.MarshallerBuilder;
import r01f.securitycontext.SecurityContext;
import r01f.services.annotations.SecurityContextProviderForMasterUser;
import x47b.api.context.X47BSecurityContextProviderForMaster;
import x47b.api.context.X47BSecurityContextProviderMockImpl;
import x47b.common.internal.X47BAppCodes;

/**
 * Client-API bindings
 */
public class X47BCommonBootstrapGuiceModule
  implements Module {
/////////////////////////////////////////////////////////////////////////////////////////
//	FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	private final Provider<SecurityContext> _securityContextProvider;
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BCommonBootstrapGuiceModule(final Provider<SecurityContext> securityContextProvider) {
		super();
		_securityContextProvider = securityContextProvider != null ? securityContextProvider
																   : new X47BSecurityContextProviderMockImpl();
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  GUICE MODULE
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void configure(final Binder binder) {
		// Model objects marshaller
		_bindModelObjectsMarshaller(binder);

		// security context
		_bindSecurityContextProviders(binder);
	}
/////////////////////////////////////////////////////////////////////////////////////////
// 	MARSHALLER BINDINGS
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
//	SECURITY CONTEXT
/////////////////////////////////////////////////////////////////////////////////////////
	private void _bindSecurityContextProviders(final Binder binder) {
		// security context
		binder.bind(SecurityContext.class)
			  .toProvider(_securityContextProvider)
			  .in(Singleton.class);

		// Master security context
		// (see SecurityContextProviderForMasterUserBase for usage directions)
		binder.bind(SecurityContext.class)
			  .annotatedWith(SecurityContextProviderForMasterUser.class)
			  .toProvider(X47BSecurityContextProviderForMaster.class)
			  .in(Singleton.class);
	}
}
