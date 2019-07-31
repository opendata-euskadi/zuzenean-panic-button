package x47b.api.context;

import r01f.guids.CommonOIDs.AppCode;
import r01f.guids.CommonOIDs.UserCode;
import r01f.patterns.IsBuilder;

public class X47BSecurityContextBuilder
  implements IsBuilder {
	
	/**
	 * Creates a {@link UserContext} for a physical user
	 * @param userCode
	 * @return
	 */
	public static X47BSecurityContext createFor(final UserCode userCode) {
		return new X47BSecurityContext(userCode);
	}
	/**
	 * Creates a {@link UserContext} for an app
	 * @param appCode
	 * @return
	 */
	public static X47BSecurityContext createForApp(final AppCode appCode) {
		return new X47BSecurityContext(appCode);
	}
}
