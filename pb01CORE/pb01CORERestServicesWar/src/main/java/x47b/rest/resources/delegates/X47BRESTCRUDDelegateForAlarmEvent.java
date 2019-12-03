package x47b.rest.resources.delegates;

import java.net.URI;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import r01f.model.persistence.CRUDResult;
import r01f.model.persistence.PersistenceException;
import r01f.rest.RESTOperationsResponseBuilder;
import r01f.rest.resources.delegates.RESTCRUDDelegateBase;
import x47b.api.context.X47BSecurityContext;
import x47b.api.interfaces.X47BCRUDServicesForAlarmEvent;
import x47b.api.interfaces.X47BNotifyServicesForAlarmEvent;
import x47b.model.X47BAlarmEvent;
import x47b.model.oids.X47BOrganizationalIDs.X47BWorkPlaceID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BWorkPlaceOID;
import x47b.model.oids.X47BPanicButtonOIDs.X47BAlarmEventOID;

public class X47BRESTCRUDDelegateForAlarmEvent
	 extends RESTCRUDDelegateBase<X47BAlarmEventOID,X47BAlarmEvent> {
/////////////////////////////////////////////////////////////////////////////////////////
//  FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Alarm event notify services
	 */
	private final X47BNotifyServicesForAlarmEvent _notifyServices;
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BRESTCRUDDelegateForAlarmEvent(final X47BCRUDServicesForAlarmEvent crudServices,
											 final X47BNotifyServicesForAlarmEvent notifyServices) {
		super(X47BAlarmEvent.class,
			  crudServices);
		_notifyServices = notifyServices;
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  EXTENSION METHODS
//	This methods come from the X47BNotifyServicesForAlarmEvent interface BUT they're normal
//	CRUD methods so the correct place to be exposed as REST service is this resource
//
//	BEWARE that the normal CRUD create / update method DO NOT send any notification
/////////////////////////////////////////////////////////////////////////////////////////
	public Response raiseAlarm(final X47BSecurityContext securityContext,final String resourcePath,
						 	   final X47BWorkPlaceID id) throws PersistenceException {
		CRUDResult<X47BAlarmEvent> createResult = _notifyServices.raiseAlarm(securityContext,
										   	   					 			 id);
		Response outResponse = RESTOperationsResponseBuilder.crudOn(_modelObjectType)
														    .at(URI.create(resourcePath))
														    .mediaType(MediaType.TEXT_XML_TYPE)
														    .build(createResult);
		return outResponse;

	}
	public Response raiseAlarm(final X47BSecurityContext securityContext,final String resourcePath,
						 	   final X47BWorkPlaceOID oid) throws PersistenceException {
		CRUDResult<X47BAlarmEvent> createResult = _notifyServices.raiseAlarm(securityContext,
										   	   					 			 oid);
		Response outResponse = RESTOperationsResponseBuilder.crudOn(_modelObjectType)
														    .at(URI.create(resourcePath))
														    .mediaType(MediaType.TEXT_XML_TYPE)
														    .build(createResult);
		return outResponse;

	}
	public Response cancelAlarm(final X47BSecurityContext securityContext,final String resourcePath,
						 	    final X47BAlarmEventOID oid) throws PersistenceException {
		CRUDResult<X47BAlarmEvent> createResult = _notifyServices.cancelAlarm(securityContext,
										   	   					 			  oid);
		Response outResponse = RESTOperationsResponseBuilder.crudOn(_modelObjectType)
														    .at(URI.create(resourcePath))
														    .mediaType(MediaType.TEXT_XML_TYPE)
														    .build(createResult);
		return outResponse;
	}
}
