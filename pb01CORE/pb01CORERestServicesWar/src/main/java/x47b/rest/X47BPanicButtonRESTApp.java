package x47b.rest;

import java.util.Set;

import javax.inject.Singleton;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;

import com.google.api.client.util.Sets;

import lombok.NoArgsConstructor;
import r01f.rest.RESTAppBase;
import r01f.rest.RESTResource;
import x47b.rest.resources.X47BRESTCRUDResourceForAlarmEvent;
import x47b.rest.resources.X47BRESTCRUDResourceForOrgDivision;
import x47b.rest.resources.X47BRESTCRUDResourceForOrgDivisionService;
import x47b.rest.resources.X47BRESTCRUDResourceForOrgDivisionServiceLocation;
import x47b.rest.resources.X47BRESTCRUDResourceForOrganization;
import x47b.rest.resources.X47BRESTCRUDResourceForWorkPlace;
import x47b.rest.resources.X47BRESTFindResourceForAlarmEvent;
import x47b.rest.resources.X47BRESTFindResourceForOrgDivision;
import x47b.rest.resources.X47BRESTFindResourceForOrgDivisionService;
import x47b.rest.resources.X47BRESTFindResourceForOrgDivisionServiceLocation;
import x47b.rest.resources.X47BRESTFindResourceForOrganization;
import x47b.rest.resources.X47BRESTFindResourceForWorkPlace;
import x47b.rest.resources.X47BRESTResourceForAlarmEventClient;
import x47b.rest.resources.X47BRESTSearchResourceForEntityModelObject;
import x47b.server.rest.X47BRESTExceptionMappers.X47BPersistenceExceptionMapper;
import x47b.server.rest.X47BRESTExceptionMappers.X47BUncaughtExceptionMapper;
import x47b.server.rest.X47BRESTRequestTypeMappers.X47BEnqueuedJobRequestTypeMapper;
import x47b.server.rest.X47BRESTRequestTypeMappers.X47BIndexManagementCommandRequestTypeMapper;
import x47b.server.rest.X47BRESTRequestTypeMappers.X47BModelObjectRequestTypeMapper;
import x47b.server.rest.X47BRESTRequestTypeMappers.X47BOIDRequestTypeMapper;
import x47b.server.rest.X47BRESTResponseTypeMappers.BooleanResponseTypeMapper;
import x47b.server.rest.X47BRESTResponseTypeMappers.CollectionResponseTypeMapper;
import x47b.server.rest.X47BRESTResponseTypeMappers.DateResponseTypeMapper;
import x47b.server.rest.X47BRESTResponseTypeMappers.LongResponseTypeMapper;
import x47b.server.rest.X47BRESTResponseTypeMappers.MapResponseTypeMapper;
import x47b.server.rest.X47BRESTResponseTypeMappers.X47BEnqueuedJobdResponseTypeMapper;
import x47b.server.rest.X47BRESTResponseTypeMappers.X47BIndexManagementCommandResponseTypeMapper;
import x47b.server.rest.X47BRESTResponseTypeMappers.X47BModelObjectResponseTypeMapper;
import x47b.server.rest.X47BRESTResponseTypeMappers.X47BOIDResponseTypeMapper;
import x47b.server.rest.X47BRESTResponseTypeMappers.X47BPersistenceOperationResultTypeMapper;
import x47b.server.rest.X47BRESTResponseTypeMappers.X47BSearchModelObjectResponseTypeMapper;


/**
 * Rest app referenced at {@link X47BPanicButtonRESTJerseyServletGuiceModuleForServiceRequests} (Guice is in use)
 * in order to load the REST resources
 *
 * <pre>
 * NOTE:	If Guice was not used, the REST App should be defined in WEB-INF/web.xml
 * </pre>
 */
@Singleton
@NoArgsConstructor
public class X47BPanicButtonRESTApp
     extends RESTAppBase {
/////////////////////////////////////////////////////////////////////////////////////////
//  REST RESOURCES
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public Set<Class<? extends RESTResource>> getRESTResourceTypes() {
		Set<Class<? extends RESTResource>> outResTypes = Sets.newHashSet();
		
		outResTypes.add(X47BRESTCRUDResourceForOrganization.class);
		outResTypes.add(X47BRESTCRUDResourceForOrgDivision.class);
		outResTypes.add(X47BRESTCRUDResourceForOrgDivisionService.class);
		outResTypes.add(X47BRESTCRUDResourceForOrgDivisionServiceLocation.class);
		outResTypes.add(X47BRESTCRUDResourceForWorkPlace.class);
		
		outResTypes.add(X47BRESTFindResourceForOrganization.class);
		outResTypes.add(X47BRESTFindResourceForOrgDivision.class);
		outResTypes.add(X47BRESTFindResourceForOrgDivisionService.class);
		outResTypes.add(X47BRESTFindResourceForOrgDivisionServiceLocation.class);
		outResTypes.add(X47BRESTFindResourceForWorkPlace.class);
		
		outResTypes.add(X47BRESTSearchResourceForEntityModelObject.class);
		
		outResTypes.add(X47BRESTCRUDResourceForAlarmEvent.class);
		outResTypes.add(X47BRESTFindResourceForAlarmEvent.class);
		outResTypes.add(X47BRESTResourceForAlarmEventClient.class);
		
		return outResTypes;
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  MAPPERS
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public Set<Class<? extends MessageBodyReader<?>>> getRequestReceivedTypesMappers() {
		Set<Class<? extends MessageBodyReader<?>>> outMappers = super.getRequestReceivedTypesMappers();
		
		outMappers.add(X47BModelObjectRequestTypeMapper.class);
		outMappers.add(X47BOIDRequestTypeMapper.class);
		outMappers.add(X47BIndexManagementCommandRequestTypeMapper.class);
		outMappers.add(X47BEnqueuedJobRequestTypeMapper.class);
		
		return outMappers;
	}

	@Override
	public Set<Class<? extends MessageBodyWriter<?>>> getResponseSentTypesMappers() {
		Set<Class<? extends MessageBodyWriter<?>>> outMappers = super.getResponseSentTypesMappers();
		
		// Common response type mappers
		outMappers.add(BooleanResponseTypeMapper.class);
		outMappers.add(LongResponseTypeMapper.class);
		outMappers.add(DateResponseTypeMapper.class);
		outMappers.add(CollectionResponseTypeMapper.class);
		outMappers.add(MapResponseTypeMapper.class);
		
		// Persistence operation results
		outMappers.add(X47BPersistenceOperationResultTypeMapper.class);

		// Model object type mappers
		outMappers.add(X47BOIDResponseTypeMapper.class);
		outMappers.add(X47BModelObjectResponseTypeMapper.class);
		outMappers.add(X47BSearchModelObjectResponseTypeMapper.class);
		outMappers.add(X47BIndexManagementCommandResponseTypeMapper.class);
		outMappers.add(X47BEnqueuedJobdResponseTypeMapper.class);
		
		return outMappers;
	}

	@Override
	public Set<Class<? extends ExceptionMapper<?>>> getExceptionsMappers() {
		Set<Class<? extends ExceptionMapper<?>>> outMappers = super.getExceptionsMappers();
		
		outMappers.add(X47BPersistenceExceptionMapper.class);
		outMappers.add(X47BUncaughtExceptionMapper.class);
		
		return outMappers;
	}
}
