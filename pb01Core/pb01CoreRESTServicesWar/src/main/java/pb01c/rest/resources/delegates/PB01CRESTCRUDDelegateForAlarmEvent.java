package pb01c.rest.resources.delegates;

import java.net.URI;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import pb01a.api.context.PB01ASecurityContext;
import pb01a.api.interfaces.PB01ACRUDServicesForAlarmEvent;
import pb01a.api.interfaces.PB01ANotifyServicesForAlarmEvent;
import pb01a.model.PB01AAlarmEvent;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AWorkPlaceID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AWorkPlaceOID;
import pb01a.model.oids.PB01APanicButtonOIDs.PB01AAlarmEventOID;
import r01f.model.persistence.CRUDResult;
import r01f.model.persistence.PersistenceException;
import r01f.rest.RESTOperationsResponseBuilder;
import r01f.rest.resources.delegates.RESTCRUDDelegateBase;

public class PB01CRESTCRUDDelegateForAlarmEvent
	 extends RESTCRUDDelegateBase<PB01AAlarmEventOID,PB01AAlarmEvent> {
/////////////////////////////////////////////////////////////////////////////////////////
//  FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Alarm event notify services
	 */
	private final PB01ANotifyServicesForAlarmEvent _notifyServices;
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01CRESTCRUDDelegateForAlarmEvent(final PB01ACRUDServicesForAlarmEvent crudServices,
											 final PB01ANotifyServicesForAlarmEvent notifyServices) {
		super(PB01AAlarmEvent.class,
			  crudServices);
		_notifyServices = notifyServices;
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  EXTENSION METHODS
//	This methods come from the PB01ANotifyServicesForAlarmEvent interface BUT they're normal
//	CRUD methods so the correct place to be exposed as REST service is this resource
//
//	BEWARE that the normal CRUD create / update method DO NOT send any notification
/////////////////////////////////////////////////////////////////////////////////////////
	public Response raiseAlarm(final PB01ASecurityContext securityContext,final String resourcePath,
						 	   final PB01AWorkPlaceID id) throws PersistenceException {
		CRUDResult<PB01AAlarmEvent> createResult = _notifyServices.raiseAlarm(securityContext,
										   	   					 			 id);
		Response outResponse = RESTOperationsResponseBuilder.crudOn(_modelObjectType)
														    .at(URI.create(resourcePath))
														    .mediaType(MediaType.TEXT_XML_TYPE)
														    .build(createResult);
		return outResponse;

	}
	public Response raiseAlarm(final PB01ASecurityContext securityContext,final String resourcePath,
						 	   final PB01AWorkPlaceOID oid) throws PersistenceException {
		CRUDResult<PB01AAlarmEvent> createResult = _notifyServices.raiseAlarm(securityContext,
										   	   					 			 oid);
		Response outResponse = RESTOperationsResponseBuilder.crudOn(_modelObjectType)
														    .at(URI.create(resourcePath))
														    .mediaType(MediaType.TEXT_XML_TYPE)
														    .build(createResult);
		return outResponse;

	}
	public Response cancelAlarm(final PB01ASecurityContext securityContext,final String resourcePath,
						 	    final PB01AAlarmEventOID oid) throws PersistenceException {
		CRUDResult<PB01AAlarmEvent> createResult = _notifyServices.cancelAlarm(securityContext,
										   	   					 			  oid);
		Response outResponse = RESTOperationsResponseBuilder.crudOn(_modelObjectType)
														    .at(URI.create(resourcePath))
														    .mediaType(MediaType.TEXT_XML_TYPE)
														    .build(createResult);
		return outResponse;
	}
}
