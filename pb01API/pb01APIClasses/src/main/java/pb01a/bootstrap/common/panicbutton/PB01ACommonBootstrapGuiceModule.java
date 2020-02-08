package pb01a.bootstrap.common.panicbutton;

import javax.inject.Provider;
import javax.inject.Singleton;

import com.google.inject.Binder;
import com.google.inject.Module;

import pb01a.api.context.PB01ASecurityContextProviderForMaster;
import pb01a.api.context.PB01ASecurityContextProviderMockImpl;
import pb01a.common.internal.P01AAppCodes;
import r01f.model.annotations.ModelObjectsMarshaller;
import r01f.objectstreamer.Marshaller;
import r01f.objectstreamer.MarshallerBuilder;
import r01f.securitycontext.SecurityContext;
import r01f.services.annotations.SecurityContextProviderForMasterUser;

/**
 * Client-API bindings
 */
public class PB01ACommonBootstrapGuiceModule
  implements Module {
/////////////////////////////////////////////////////////////////////////////////////////
//	FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	private final Provider<SecurityContext> _securityContextProvider;
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01ACommonBootstrapGuiceModule(final Provider<SecurityContext> securityContextProvider) {
		super();
		_securityContextProvider = securityContextProvider != null ? securityContextProvider
																   : new PB01ASecurityContextProviderMockImpl();
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
		Marshaller marshaller = MarshallerBuilder.findTypesToMarshallAt(P01AAppCodes.API_APPCODE)
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
			  .toProvider(PB01ASecurityContextProviderForMaster.class)
			  .in(Singleton.class);
	}
}
