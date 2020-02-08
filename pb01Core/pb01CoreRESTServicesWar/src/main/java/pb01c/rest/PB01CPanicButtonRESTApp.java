package pb01c.rest;

import java.util.Set;

import javax.inject.Singleton;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;

import com.google.api.client.util.Sets;

import lombok.NoArgsConstructor;
import pb01c.rest.resources.PB01CRESTCRUDResourceForAlarmEvent;
import pb01c.rest.resources.PB01CRESTCRUDResourceForOrgDivision;
import pb01c.rest.resources.PB01CRESTCRUDResourceForOrgDivisionService;
import pb01c.rest.resources.PB01CRESTCRUDResourceForOrgDivisionServiceLocation;
import pb01c.rest.resources.PB01CRESTCRUDResourceForOrganization;
import pb01c.rest.resources.PB01CRESTCRUDResourceForWorkPlace;
import pb01c.rest.resources.PB01CRESTFindResourceForAlarmEvent;
import pb01c.rest.resources.PB01CRESTFindResourceForOrgDivision;
import pb01c.rest.resources.PB01CRESTFindResourceForOrgDivisionService;
import pb01c.rest.resources.PB01CRESTFindResourceForOrgDivisionServiceLocation;
import pb01c.rest.resources.PB01CRESTFindResourceForOrganization;
import pb01c.rest.resources.PB01CRESTFindResourceForWorkPlace;
import pb01c.rest.resources.PB01CRESTResourceForAlarmEventClient;
import pb01c.rest.resources.PB01CRESTSearchResourceForEntityModelObject;
import pb01c.server.rest.PB01CRESTExceptionMappers.PB01CPersistenceExceptionMapper;
import pb01c.server.rest.PB01CRESTExceptionMappers.PB01CUncaughtExceptionMapper;
import pb01c.server.rest.PB01CRESTRequestTypeMappers.PB01CEnqueuedJobRequestTypeMapper;
import pb01c.server.rest.PB01CRESTRequestTypeMappers.PB01CIndexManagementCommandRequestTypeMapper;
import pb01c.server.rest.PB01CRESTRequestTypeMappers.PB01CModelObjectRequestTypeMapper;
import pb01c.server.rest.PB01CRESTRequestTypeMappers.PB01COIDRequestTypeMapper;
import pb01c.server.rest.PB01CRESTResponseTypeMappers.PB01CBooleanResponseTypeMapper;
import pb01c.server.rest.PB01CRESTResponseTypeMappers.PB01CCollectionResponseTypeMapper;
import pb01c.server.rest.PB01CRESTResponseTypeMappers.DateResponseTypeMapper;
import pb01c.server.rest.PB01CRESTResponseTypeMappers.PB01CLongResponseTypeMapper;
import pb01c.server.rest.PB01CRESTResponseTypeMappers.PB01CMapResponseTypeMapper;
import pb01c.server.rest.PB01CRESTResponseTypeMappers.PB01CEnqueuedJobdResponseTypeMapper;
import pb01c.server.rest.PB01CRESTResponseTypeMappers.PB01CIndexManagementCommandResponseTypeMapper;
import pb01c.server.rest.PB01CRESTResponseTypeMappers.PB01CModelObjectResponseTypeMapper;
import pb01c.server.rest.PB01CRESTResponseTypeMappers.PB01COIDResponseTypeMapper;
import pb01c.server.rest.PB01CRESTResponseTypeMappers.PB01CPersistenceOperationResultTypeMapper;
import pb01c.server.rest.PB01CRESTResponseTypeMappers.PB01CSearchModelObjectResponseTypeMapper;
import r01f.rest.RESTAppBase;
import r01f.rest.RESTResource;


/**
 * Rest app referenced at {@link PB01CPanicButtonRESTJerseyServletGuiceModuleForServiceRequests} (Guice is in use)
 * in order to load the REST resources
 *
 * <pre>
 * NOTE:	If Guice was not used, the REST App should be defined in WEB-INF/web.xml
 * </pre>
 */
@Singleton
@NoArgsConstructor
public class PB01CPanicButtonRESTApp
     extends RESTAppBase {
/////////////////////////////////////////////////////////////////////////////////////////
//  REST RESOURCES
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public Set<Class<? extends RESTResource>> getRESTResourceTypes() {
		Set<Class<? extends RESTResource>> outResTypes = Sets.newHashSet();
		
		outResTypes.add(PB01CRESTCRUDResourceForOrganization.class);
		outResTypes.add(PB01CRESTCRUDResourceForOrgDivision.class);
		outResTypes.add(PB01CRESTCRUDResourceForOrgDivisionService.class);
		outResTypes.add(PB01CRESTCRUDResourceForOrgDivisionServiceLocation.class);
		outResTypes.add(PB01CRESTCRUDResourceForWorkPlace.class);
		
		outResTypes.add(PB01CRESTFindResourceForOrganization.class);
		outResTypes.add(PB01CRESTFindResourceForOrgDivision.class);
		outResTypes.add(PB01CRESTFindResourceForOrgDivisionService.class);
		outResTypes.add(PB01CRESTFindResourceForOrgDivisionServiceLocation.class);
		outResTypes.add(PB01CRESTFindResourceForWorkPlace.class);
		
		outResTypes.add(PB01CRESTSearchResourceForEntityModelObject.class);
		
		outResTypes.add(PB01CRESTCRUDResourceForAlarmEvent.class);
		outResTypes.add(PB01CRESTFindResourceForAlarmEvent.class);
		outResTypes.add(PB01CRESTResourceForAlarmEventClient.class);
		
		return outResTypes;
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  MAPPERS
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public Set<Class<? extends MessageBodyReader<?>>> getRequestReceivedTypesMappers() {
		Set<Class<? extends MessageBodyReader<?>>> outMappers = super.getRequestReceivedTypesMappers();
		
		outMappers.add(PB01CModelObjectRequestTypeMapper.class);
		outMappers.add(PB01COIDRequestTypeMapper.class);
		outMappers.add(PB01CIndexManagementCommandRequestTypeMapper.class);
		outMappers.add(PB01CEnqueuedJobRequestTypeMapper.class);
		
		return outMappers;
	}

	@Override
	public Set<Class<? extends MessageBodyWriter<?>>> getResponseSentTypesMappers() {
		Set<Class<? extends MessageBodyWriter<?>>> outMappers = super.getResponseSentTypesMappers();
		
		// Common response type mappers
		outMappers.add(PB01CBooleanResponseTypeMapper.class);
		outMappers.add(PB01CLongResponseTypeMapper.class);
		outMappers.add(DateResponseTypeMapper.class);
		outMappers.add(PB01CCollectionResponseTypeMapper.class);
		outMappers.add(PB01CMapResponseTypeMapper.class);
		
		// Persistence operation results
		outMappers.add(PB01CPersistenceOperationResultTypeMapper.class);

		// Model object type mappers
		outMappers.add(PB01COIDResponseTypeMapper.class);
		outMappers.add(PB01CModelObjectResponseTypeMapper.class);
		outMappers.add(PB01CSearchModelObjectResponseTypeMapper.class);
		outMappers.add(PB01CIndexManagementCommandResponseTypeMapper.class);
		outMappers.add(PB01CEnqueuedJobdResponseTypeMapper.class);
		
		return outMappers;
	}

	@Override
	public Set<Class<? extends ExceptionMapper<?>>> getExceptionsMappers() {
		Set<Class<? extends ExceptionMapper<?>>> outMappers = super.getExceptionsMappers();
		
		outMappers.add(PB01CPersistenceExceptionMapper.class);
		outMappers.add(PB01CUncaughtExceptionMapper.class);
		
		return outMappers;
	}
}
