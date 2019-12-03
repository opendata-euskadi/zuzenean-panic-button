package x47b.server.rest;

import javax.ws.rs.ext.Provider;

import com.sun.jndi.cosnaming.ExceptionMapper;

import r01f.rest.RESTExceptionMappers.RESTPersistenceExceptionMapper;
import r01f.rest.RESTExceptionMappers.RESTUncaughtExceptionMapper;

/**
 * {@link ExceptionMapper}(s) used to map {@link Exception}s to {@link Response}s
 * 
 * <pre>
 * IMPORTANT!	Do NOT forget to include this types at the getClasses() method of X47BRESTApp type
 * </pre>
 */
public class X47BRESTExceptionMappers {
	@Provider
	public static class X47BPersistenceExceptionMapper 
				extends RESTPersistenceExceptionMapper {
		/* nothing */
	}
	@Provider
	public static class X47BUncaughtExceptionMapper 
				extends RESTUncaughtExceptionMapper {
		/* nothing */
	}
}
