package x47b.api.context;



import javax.inject.Provider;

import r01f.guids.CommonOIDs.TenantID;
import r01f.guids.CommonOIDs.UserCode;
import r01f.securitycontext.SecurityContext;

/**
 * Mock provider for user contexts
 */
public class X47BSecurityContextProviderMockImpl
  implements Provider<SecurityContext> {
/////////////////////////////////////////////////////////////////////////////////////////
//  Provider
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public SecurityContext get() {
		X47BSecurityContextUserData mockUserData = new X47BSecurityContextUserData(UserCode.forId("mockUser"),null,	// no workplace
																				   "Mock User",(String)null);		// no surname
		X47BSecurityContext outCtx = new X47BSecurityContext(mockUserData);
		outCtx.setTenantId(TenantID.DEFAULT);
		return outCtx;
	}

}
