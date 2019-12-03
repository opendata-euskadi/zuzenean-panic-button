package x47b.api.context;

import javax.inject.Provider;

import lombok.extern.slf4j.Slf4j;
import r01f.securitycontext.SecurityContext;
import r01f.securitycontext.SecurityContextStoreAtThreadLocalStorage;

/**
 * Uses a {@link ThreadLocal}-stored {@link SecurityContext} that usually is put there by
 * a Servlet Filter (see R01DemoSecurityContextServletFilter)
 */
@Slf4j
public class X47BSecurityContextProviderFromThreadLocalStorage
  implements Provider<SecurityContext> {
/////////////////////////////////////////////////////////////////////////////////////////
//  METHODS
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public X47BSecurityContext get() {
		X47BSecurityContext outSecurityContext = SecurityContextStoreAtThreadLocalStorage.get();
		if (outSecurityContext != null) {
			log.trace("got a [security context] attached to the [thread local] storage for user={}",
					  outSecurityContext.getUserCode());
		} else {
			log.warn("NO [security context] attached to the [thread local] storage: no security filter in use!!");
			//outSecurityContext = new R01DemoSecurityContext();
			throw new IllegalStateException("NO [security context] attached to the [thread local] storage: no security filter in use!!");
		}
		return outSecurityContext;
	}

}
