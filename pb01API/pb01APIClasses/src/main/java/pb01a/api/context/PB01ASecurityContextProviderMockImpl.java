package pb01a.api.context;



import javax.inject.Provider;

import r01f.guids.CommonOIDs.TenantID;
import r01f.guids.CommonOIDs.UserCode;
import r01f.securitycontext.SecurityContext;

/**
 * Mock provider for user contexts
 */
public class PB01ASecurityContextProviderMockImpl
  implements Provider<SecurityContext> {
/////////////////////////////////////////////////////////////////////////////////////////
//  Provider
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public SecurityContext get() {
		PB01ASecurityContextUserData mockUserData = new PB01ASecurityContextUserData(UserCode.forId("mockUser"),null,	// no workplace
																				   "Mock User",(String)null);		// no surname
		PB01ASecurityContext outCtx = new PB01ASecurityContext(mockUserData);
		outCtx.setTenantId(TenantID.DEFAULT);
		return outCtx;
	}

}
