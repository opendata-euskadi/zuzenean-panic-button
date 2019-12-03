package x47b.api.context;

import r01f.guids.CommonOIDs.AuthenticatedActorID;
import r01f.securitycontext.SecurityContextProviderForMasterUserBase;

/**
 * Provices a MASTER {@link SecurityContextProviderForMasterUserBase}
 * (see {@link SecurityContextProviderForMasterUserBase})
 */
public class X47BSecurityContextProviderForMaster
	 extends SecurityContextProviderForMasterUserBase {
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BSecurityContextProviderForMaster() {
		// TODO use any kind of token to prevent the creation of this security token from unlegitimate parts
		super(new X47BSecurityContext(AuthenticatedActorID.MASTER));
	}

}
