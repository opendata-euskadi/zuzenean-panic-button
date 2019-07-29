package x47b.api.context;


import javax.inject.Provider;

/**
 * Mock provider for user contexts
 */
public class X47BMockSecurityContextProvider
  implements Provider<X47BSecurityContext> {
/////////////////////////////////////////////////////////////////////////////////////////
//  Provider
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public X47BSecurityContext get() {
		return new X47BSecurityContext();
	}

}
