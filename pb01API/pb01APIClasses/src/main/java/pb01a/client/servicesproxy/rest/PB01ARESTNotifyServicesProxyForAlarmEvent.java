package pb01a.client.servicesproxy.rest;

import javax.inject.Inject;
import javax.inject.Singleton;

import pb01a.api.interfaces.PB01ANotifyServicesForAlarmEvent;
import pb01a.model.PB01AAlarmEvent;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AWorkPlaceID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AWorkPlaceOID;
import pb01a.model.oids.PB01APanicButtonOIDs.PB01AAlarmEventOID;
import r01f.httpclient.HttpResponse;
import r01f.model.annotations.ModelObjectsMarshaller;
import r01f.model.persistence.CRUDResult;
import r01f.model.persistence.PersistenceRequestedOperation;
import r01f.objectstreamer.Marshaller;
import r01f.securitycontext.SecurityContext;
import r01f.services.client.servicesproxy.rest.DelegateForRawREST;
import r01f.services.client.servicesproxy.rest.RESTResponseToCRUDResultMapperForModelObject;
import r01f.services.client.servicesproxy.rest.RESTServicesProxyBase;
import r01f.types.url.Url;
import r01f.xmlproperties.XMLPropertiesForAppComponent;
import r01f.xmlproperties.annotations.XMLPropertiesComponent;


@Singleton
public class PB01ARESTNotifyServicesProxyForAlarmEvent
	 extends RESTServicesProxyBase
  implements PB01ANotifyServicesForAlarmEvent,
  			 PB01APanicButtonRESTProxy {
/////////////////////////////////////////////////////////////////////////////////////////
//  FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Maps from the REST response object to the CRUDResult object
	 */
	private final RESTResponseToCRUDResultMapperForModelObject<PB01AAlarmEventOID,PB01AAlarmEvent> _responseToCRUDResultMapper;
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public PB01ARESTNotifyServicesProxyForAlarmEvent(@XMLPropertiesComponent("client") final XMLPropertiesForAppComponent clientProps,
												    @ModelObjectsMarshaller 		  final Marshaller marshaller) {
		super(marshaller,
			  new PB01ARESTServiceResourceUrlPathBuilderForAlarmEvent(clientProps));
		_responseToCRUDResultMapper = new RESTResponseToCRUDResultMapperForModelObject<PB01AAlarmEventOID,PB01AAlarmEvent>(marshaller,
																														 PB01AAlarmEvent.class);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  GET
/////////////////////////////////////////////////////////////////////////////////////////
	private RESTResponseToCRUDResultMapperForModelObject<PB01AAlarmEventOID,PB01AAlarmEvent> getResponseToCRUDResultMapper() {
		return _responseToCRUDResultMapper;
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public CRUDResult<PB01AAlarmEvent> raiseAlarm(final SecurityContext securityContext,
												 final PB01AWorkPlaceID id) {
		// An alarm raising is simply a PB01AAlarmEvent create (POST)
		// do the http call
		Url restResourceUrl = this.composeURIFor(this.getServicesRESTResourceUrlPathBuilderAs(PB01ARESTServiceResourceUrlPathBuilderForAlarmEvent.class)
																.pathOfAlarmsBySourceId());
		// An alarm raising is simply a PB01AAlarmEvent create (POST)
		// do the http call
		String ctxXml = _marshaller.forWriting().toXml(securityContext);
		HttpResponse httpResponse = DelegateForRawREST.POST(restResourceUrl,
										  					ctxXml,
										  					id.asString());		// a POST with the source id
		// map the response
		CRUDResult<PB01AAlarmEvent> outResponse = this.getResponseToCRUDResultMapper()
														.mapHttpResponseForEntity(securityContext,
															  			  		  PersistenceRequestedOperation.CREATE,
															  			  		  id,
															  			  		  restResourceUrl,httpResponse);
		return outResponse;
	}
	@Override
	public CRUDResult<PB01AAlarmEvent> raiseAlarm(final SecurityContext securityContext,
												 final PB01AWorkPlaceOID oid) {
		// An alarm raising is simply a PB01AAlarmEvent create (POST)
		// do the http call
		Url restResourceUrl = this.composeURIFor(this.getServicesRESTResourceUrlPathBuilderAs(PB01ARESTServiceResourceUrlPathBuilderForAlarmEvent.class)
																.pathOfAlarmsBySourceOid());
		// An alarm raising is simply a PB01AAlarmEvent create (POST)
		// do the http call
		String ctxXml = _marshaller.forWriting().toXml(securityContext);
		HttpResponse httpResponse = DelegateForRawREST.POST(restResourceUrl,
										  					ctxXml,
										  					oid.asString());	// a POST with the source oid
		// map the response
		CRUDResult<PB01AAlarmEvent> outResponse = this.getResponseToCRUDResultMapper()
														.mapHttpResponseForEntity(securityContext,
															  			  		  PersistenceRequestedOperation.CREATE,
															  			  		  oid,
															  			  		  restResourceUrl,httpResponse);
		return outResponse;
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public CRUDResult<PB01AAlarmEvent> cancelAlarm(final SecurityContext securityContext,
												  final PB01AAlarmEventOID oid) {
		// An alarm cancellation is simply a PB01AAlarmEvent delete (DELETE)
		// do the http call
		Url restResourceUrl = this.composeURIFor(this.getServicesRESTResourceUrlPathBuilderAs(PB01ARESTServiceResourceUrlPathBuilderForAlarmEvent.class)
															   			  .pathOfEntity(oid));
		String ctxXml = _marshaller.forWriting().toXml(securityContext);
		HttpResponse httpResponse = DelegateForRawREST.DELETE(restResourceUrl,
															  ctxXml);
		// map the response
		CRUDResult<PB01AAlarmEvent> outResponse = this.getResponseToCRUDResultMapper()
															.mapHttpResponseForEntity(securityContext,
																			  		  PersistenceRequestedOperation.DELETE,
																			  		  restResourceUrl,httpResponse)
																   .identifiedOnErrorBy(oid);
		return outResponse;
	}

}
