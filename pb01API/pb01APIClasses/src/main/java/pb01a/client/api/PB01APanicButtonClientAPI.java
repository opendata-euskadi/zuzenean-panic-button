package pb01a.client.api;

import java.util.Map;

import javax.inject.Named;
import javax.inject.Provider;

import com.google.inject.Inject;

import lombok.experimental.Accessors;
import pb01a.api.interfaces.PB01ASearchServicesForOrganizationalEntityObject;
import pb01a.client.api.sub.PB01AClientAPIForAlarmEvents;
import pb01a.client.api.sub.PB01AClientAPIForEntityModelObjectSearch;
import pb01a.client.api.sub.PB01AClientAPIForOrgDivisionServiceLocations;
import pb01a.client.api.sub.PB01AClientAPIForOrgDivisionServices;
import pb01a.client.api.sub.PB01AClientAPIForOrgDivisions;
import pb01a.client.api.sub.PB01AClientAPIForOrganizations;
import pb01a.client.api.sub.PB01AClientAPIForWorkPlaces;
import pb01a.common.internal.P01AAppCodes;
import r01f.model.annotations.ModelObjectsMarshaller;
import r01f.objectstreamer.Marshaller;
import r01f.securitycontext.SecurityContext;
import r01f.services.client.ClientAPIImplBase;
import r01f.services.interfaces.ServiceInterface;



/**
 * Base type for every API implementation of panic button service.
 */
@Accessors(prefix="_")
public class PB01APanicButtonClientAPI
     extends ClientAPIImplBase {
/////////////////////////////////////////////////////////////////////////////////////////
//  SUB-APIs (created at the constructor)
/////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Organizations
	 */
	private final PB01AClientAPIForOrganizations _organizationsAPI;
	/**
	 * Divisions
	 */
	private final PB01AClientAPIForOrgDivisions _divisionsAPI;
	/**
	 * Services
	 */
	private final PB01AClientAPIForOrgDivisionServices _servicesAPI;
	/**
	 * Locations
	 */
	private final PB01AClientAPIForOrgDivisionServiceLocations _locationsAPI;
	/**
	 * WorkPlaces
	 */
	private final PB01AClientAPIForWorkPlaces _workPlacesAPI;
	/**
	 * Entity search API
	 */
	private final PB01AClientAPIForEntityModelObjectSearch _entitySearchAPI;
	/**
	 * AlarmEvents
	 */
	private final PB01AClientAPIForAlarmEvents _alarmEventsAPI;

/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject @SuppressWarnings("rawtypes")
	public PB01APanicButtonClientAPI(						final Provider<SecurityContext> securityContextProvider,
									@ModelObjectsMarshaller final Marshaller modelObjectsMarshaller,
							 		@Named(P01AAppCodes.API_APPCODE_STR) final Map<Class,ServiceInterface> srvcIfaceMappings) {	// comes from injection
		// Services proxy
		super(securityContextProvider,
			  modelObjectsMarshaller,
			  srvcIfaceMappings);

		// Build every sub-api
		_organizationsAPI = new PB01AClientAPIForOrganizations(securityContextProvider,	
															  modelObjectsMarshaller,
															  srvcIfaceMappings);
		_divisionsAPI = new PB01AClientAPIForOrgDivisions(securityContextProvider,	
														 modelObjectsMarshaller,
														 srvcIfaceMappings);
		_servicesAPI = new PB01AClientAPIForOrgDivisionServices(securityContextProvider,	
														 	   modelObjectsMarshaller,
														 	   srvcIfaceMappings);
		_locationsAPI = new PB01AClientAPIForOrgDivisionServiceLocations(securityContextProvider,
																		modelObjectsMarshaller,
				  									  					srvcIfaceMappings);
		_workPlacesAPI = new PB01AClientAPIForWorkPlaces(securityContextProvider,
														modelObjectsMarshaller,
														srvcIfaceMappings);
		_entitySearchAPI = new PB01AClientAPIForEntityModelObjectSearch(securityContextProvider,
																	   modelObjectsMarshaller,
																	   this.getServiceInterfaceCoreImplOrProxy(PB01ASearchServicesForOrganizationalEntityObject.class));
		_alarmEventsAPI = new PB01AClientAPIForAlarmEvents(securityContextProvider,
														  modelObjectsMarshaller,
				  								    	  srvcIfaceMappings);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  SUB-APIs
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01AClientAPIForOrganizations organizationsAPI() {
		return _organizationsAPI;
	}
	public PB01AClientAPIForOrgDivisions orgDivisionsAPI() {
		return _divisionsAPI;
	}
	public PB01AClientAPIForOrgDivisionServices orgDivisionServicesAPI() {
		return _servicesAPI;
	}
	public PB01AClientAPIForOrgDivisionServiceLocations orgDivisionServiceLocationsAPI() {
		return _locationsAPI;
	}
	public PB01AClientAPIForWorkPlaces workPlacesAPI() {
		return _workPlacesAPI;
	}
	public PB01AClientAPIForEntityModelObjectSearch entitySearchAPI() {
		return _entitySearchAPI;
	}
	public PB01AClientAPIForAlarmEvents alarmEventsAPI() {
		return _alarmEventsAPI;
	}
}
