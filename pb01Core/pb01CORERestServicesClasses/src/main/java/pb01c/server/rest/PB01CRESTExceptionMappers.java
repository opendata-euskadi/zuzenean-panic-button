package pb01c.server.rest;

import javax.ws.rs.ext.Provider;

import com.sun.jndi.cosnaming.ExceptionMapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import r01f.rest.RESTExceptionMappers.RESTPersistenceExceptionMapper;
import r01f.rest.RESTExceptionMappers.RESTUncaughtExceptionMapper;

/**
 * {@link ExceptionMapper}(s) used to map {@link Exception}s to {@link Response}s
 *
 * <pre>
 * IMPORTANT!	Do NOT forget to include this types at the getClasses() method of PB01CRESTApp type
 * </pre>
 */
@NoArgsConstructor(access=AccessLevel.PRIVATE)
public abstract class PB01CRESTExceptionMappers {
	@Provider
	public static class PB01CPersistenceExceptionMapper
				extends RESTPersistenceExceptionMapper {
		/* nothing */
	}
	@Provider
	public static class PB01CUncaughtExceptionMapper
				extends RESTUncaughtExceptionMapper {
		/* nothing */
	}
}
