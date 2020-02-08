package pb01a.api.context;

import r01f.guids.CommonOIDs.AuthenticatedActorID;
import r01f.securitycontext.SecurityContextProviderForMasterUserBase;

/**
 * Provices a MASTER {@link SecurityContextProviderForMasterUserBase}
 * (see {@link SecurityContextProviderForMasterUserBase})
 */
public class PB01ASecurityContextProviderForMaster
	 extends SecurityContextProviderForMasterUserBase {
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01ASecurityContextProviderForMaster() {
		// TODO use any kind of token to prevent the creation of this security token from unlegitimate parts
		super(new PB01ASecurityContext(AuthenticatedActorID.MASTER));
	}

}
