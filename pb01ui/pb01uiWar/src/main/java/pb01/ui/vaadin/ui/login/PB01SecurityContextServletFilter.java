package pb01.ui.vaadin.ui.login;

import java.util.regex.Pattern;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.google.common.collect.Lists;

import pb01.PB01UI;
import r01f.securitycontext.SecurityContext;
import r01f.servlet.SecurityContextServletFilterBase;
import r01f.util.types.Strings;
import x47b.api.context.X47BSecurityContextProviderFromThreadLocalStorage;

/**
 * This filter does TWO things:
 * a) stores a {@link SecurityContext} at a {@link ThreadLocal} storage used by {@link X47BSecurityContextProviderFromThreadLocalStorage}
 *    to get the security context
 *
 * b) creates a cookie with a value that hints any other part that the user was authenticated
 */
@Singleton
public class PB01SecurityContextServletFilter
     extends SecurityContextServletFilterBase {
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public PB01SecurityContextServletFilter() {
		super(Strings.customized("/{}/login/",
								 PB01UI.WEB_APP_NAME),
			  // not filtered resources
				Lists.newArrayList(Pattern.compile(Strings.customized("/{}/VAADIN/?.*",
																	  PB01UI.WEB_APP_NAME)),
						 		   Pattern.compile(Strings.customized("/{}/UIDL/?.*",
						 				   							  PB01UI.WEB_APP_NAME)),
						 		   Pattern.compile(Strings.customized("/{}/login/?.*",
						 				   							  PB01UI.WEB_APP_NAME))));
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  METHODS
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	protected String _createAuthCookieSecretToken() {
		return "_a_private_key_generated_hash_";
	}
}
