package pb01a.api.context;

import r01f.guids.CommonOIDs.AppCode;
import r01f.guids.CommonOIDs.UserCode;
import r01f.patterns.IsBuilder;

public class PB01ASecurityContextBuilder
  implements IsBuilder {
	
	/**
	 * Creates a {@link UserContext} for a physical user
	 * @param userCode
	 * @return
	 */
	public static PB01ASecurityContext createFor(final UserCode userCode) {
		return new PB01ASecurityContext(userCode);
	}
	/**
	 * Creates a {@link UserContext} for an app
	 * @param appCode
	 * @return
	 */
	public static PB01ASecurityContext createForApp(final AppCode appCode) {
		return new PB01ASecurityContext(appCode);
	}
}
