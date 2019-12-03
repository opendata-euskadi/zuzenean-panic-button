package pb01.ui.vaadin.ui.login;

import javax.inject.Inject;
import javax.inject.Singleton;

import r01f.guids.CommonOIDs.Password;
import r01f.guids.CommonOIDs.UserCode;
import r01f.ui.coremediator.UICOREMediator;
import x47b.api.context.X47BSecurityContextUserData;

@Singleton
public class PB01UILoginCOREMediator
  implements UICOREMediator {
/////////////////////////////////////////////////////////////////////////////////////////
//	FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
//	private final R01DemoClientAPIForAuth _authApi;
//	private final R01DemoClientAPI _api;
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
//	BEWARE!!	The apis here uses the MASTER's security context since
//				there's NO user's security context until the user is logged in
//	see:
//		- R01DemoCommonClientBootstrapGuiceModule > this is where the user's security context provider
//													and master's security context provider are binded
//		- R01DemoClientBootstrapGuiceModule > this is where user's client apis
//											  and master's client apis are binded
/////////////////////////////////////////////////////////////////////////////////////////
//	@Inject
//	PB01UILoginCOREMediator(@ClientAPIForMasterUser final R01DemoClientAPIForAuth authApi,
//							@ClientAPIForMasterUser final R01DemoClientAPI api) {
//		_authApi = authApi;
//		_api = api;
//	}
	@Inject
	PB01UILoginCOREMediator() {

	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Logs the user in and returns the security context user data if login was successful
	 * or null if the user could NOT log in
	 * @param user
	 * @param password
	 * @return
	 */
	public X47BSecurityContextUserData userPasswordLogIn(final UserCode user,final Password password) {
		// TODO use any security system to log the user in
		return new X47BSecurityContextUserData(user,null,
											   user.asString(),(String)null);
	}
}
