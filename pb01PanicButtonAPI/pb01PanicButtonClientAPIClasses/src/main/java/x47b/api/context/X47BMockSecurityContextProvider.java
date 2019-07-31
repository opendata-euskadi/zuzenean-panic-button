package x47b.api.context;



import javax.inject.Provider;

import r01f.guids.CommonOIDs.TenantID;
import r01f.securitycontext.SecurityContext;

/**
 * Mock provider for user contexts
 */
public class X47BMockSecurityContextProvider
  implements Provider<SecurityContext> {
/////////////////////////////////////////////////////////////////////////////////////////
//  Provider
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public SecurityContext get() {
		X47BSecurityContext outCtx = new X47BSecurityContext();
		outCtx.setTenantId(TenantID.DEFAULT);
		return outCtx;
	}

}
