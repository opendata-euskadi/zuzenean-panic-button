package x47b.client.api;

import java.util.Map;

import javax.inject.Named;
import javax.inject.Provider;

import com.google.inject.Inject;

import lombok.experimental.Accessors;
import r01f.model.annotations.ModelObjectsMarshaller;
import r01f.objectstreamer.Marshaller;
import r01f.securitycontext.SecurityContext;
import r01f.services.client.ClientAPIImplBase;
import r01f.services.interfaces.ServiceInterface;
import x47b.api.interfaces.X47BSearchServicesForOrganizationalEntityObject;
import x47b.client.api.sub.X47BClientAPIForAlarmEvents;
import x47b.client.api.sub.X47BClientAPIForEntityModelObjectSearch;
import x47b.client.api.sub.X47BClientAPIForOrgDivisionServiceLocations;
import x47b.client.api.sub.X47BClientAPIForOrgDivisionServices;
import x47b.client.api.sub.X47BClientAPIForOrgDivisions;
import x47b.client.api.sub.X47BClientAPIForOrganizations;
import x47b.client.api.sub.X47BClientAPIForWorkPlaces;
import x47b.common.internal.X47BAppCodes;



/**
 * Base type for every API implementation of panic button service.
 */
@Accessors(prefix="_")
public class X47BPanicButtonClientAPI
     extends ClientAPIImplBase {
/////////////////////////////////////////////////////////////////////////////////////////
//  SUB-APIs (created at the constructor)
/////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Organizations
	 */
	private final X47BClientAPIForOrganizations _organizationsAPI;
	/**
	 * Divisions
	 */
	private final X47BClientAPIForOrgDivisions _divisionsAPI;
	/**
	 * Services
	 */
	private final X47BClientAPIForOrgDivisionServices _servicesAPI;
	/**
	 * Locations
	 */
	private final X47BClientAPIForOrgDivisionServiceLocations _locationsAPI;
	/**
	 * WorkPlaces
	 */
	private final X47BClientAPIForWorkPlaces _workPlacesAPI;
	/**
	 * Entity search API
	 */
	private final X47BClientAPIForEntityModelObjectSearch _entitySearchAPI;
	/**
	 * AlarmEvents
	 */
	private final X47BClientAPIForAlarmEvents _alarmEventsAPI;

/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject @SuppressWarnings("rawtypes")
	public X47BPanicButtonClientAPI(						final Provider<SecurityContext> securityContextProvider,
									@ModelObjectsMarshaller final Marshaller modelObjectsMarshaller,
							 		@Named(X47BAppCodes.API_APPCODE_STR) final Map<Class,ServiceInterface> srvcIfaceMappings) {	// comes from injection
		// Services proxy
		super(securityContextProvider,
			  modelObjectsMarshaller,
			  srvcIfaceMappings);

		// Build every sub-api
		_organizationsAPI = new X47BClientAPIForOrganizations(securityContextProvider,	
															  modelObjectsMarshaller,
															  srvcIfaceMappings);
		_divisionsAPI = new X47BClientAPIForOrgDivisions(securityContextProvider,	
														 modelObjectsMarshaller,
														 srvcIfaceMappings);
		_servicesAPI = new X47BClientAPIForOrgDivisionServices(securityContextProvider,	
														 	   modelObjectsMarshaller,
														 	   srvcIfaceMappings);
		_locationsAPI = new X47BClientAPIForOrgDivisionServiceLocations(securityContextProvider,
																		modelObjectsMarshaller,
				  									  					srvcIfaceMappings);
		_workPlacesAPI = new X47BClientAPIForWorkPlaces(securityContextProvider,
														modelObjectsMarshaller,
														srvcIfaceMappings);
		_entitySearchAPI = new X47BClientAPIForEntityModelObjectSearch(securityContextProvider,
																	   modelObjectsMarshaller,
																	   this.getServiceInterfaceCoreImplOrProxy(X47BSearchServicesForOrganizationalEntityObject.class));
		_alarmEventsAPI = new X47BClientAPIForAlarmEvents(securityContextProvider,
														  modelObjectsMarshaller,
				  								    	  srvcIfaceMappings);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  SUB-APIs
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BClientAPIForOrganizations organizationsAPI() {
		return _organizationsAPI;
	}
	public X47BClientAPIForOrgDivisions orgDivisionsAPI() {
		return _divisionsAPI;
	}
	public X47BClientAPIForOrgDivisionServices orgDivisionServicesAPI() {
		return _servicesAPI;
	}
	public X47BClientAPIForOrgDivisionServiceLocations orgDivisionServiceLocationsAPI() {
		return _locationsAPI;
	}
	public X47BClientAPIForWorkPlaces workPlacesAPI() {
		return _workPlacesAPI;
	}
	public X47BClientAPIForEntityModelObjectSearch entitySearchAPI() {
		return _entitySearchAPI;
	}
	public X47BClientAPIForAlarmEvents alarmEventsAPI() {
		return _alarmEventsAPI;
	}
}
