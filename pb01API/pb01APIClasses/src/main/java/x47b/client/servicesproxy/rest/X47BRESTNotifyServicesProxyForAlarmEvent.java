package x47b.client.servicesproxy.rest;

import javax.inject.Inject;
import javax.inject.Singleton;

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
import x47b.api.interfaces.X47BNotifyServicesForAlarmEvent;
import x47b.model.X47BAlarmEvent;
import x47b.model.oids.X47BOrganizationalIDs.X47BWorkPlaceID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BWorkPlaceOID;
import x47b.model.oids.X47BPanicButtonOIDs.X47BAlarmEventOID;


@Singleton
public class X47BRESTNotifyServicesProxyForAlarmEvent
	 extends RESTServicesProxyBase
  implements X47BNotifyServicesForAlarmEvent,
  			 X47BPanicButtonRESTProxy {
/////////////////////////////////////////////////////////////////////////////////////////
//  FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Maps from the REST response object to the CRUDResult object
	 */
	private final RESTResponseToCRUDResultMapperForModelObject<X47BAlarmEventOID,X47BAlarmEvent> _responseToCRUDResultMapper;
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public X47BRESTNotifyServicesProxyForAlarmEvent(@XMLPropertiesComponent("client") final XMLPropertiesForAppComponent clientProps,
												    @ModelObjectsMarshaller 		  final Marshaller marshaller) {
		super(marshaller,
			  new X47BRESTServiceResourceUrlPathBuilderForAlarmEvent(clientProps));
		_responseToCRUDResultMapper = new RESTResponseToCRUDResultMapperForModelObject<X47BAlarmEventOID,X47BAlarmEvent>(marshaller,
																														 X47BAlarmEvent.class);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  GET
/////////////////////////////////////////////////////////////////////////////////////////
	private RESTResponseToCRUDResultMapperForModelObject<X47BAlarmEventOID,X47BAlarmEvent> getResponseToCRUDResultMapper() {
		return _responseToCRUDResultMapper;
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public CRUDResult<X47BAlarmEvent> raiseAlarm(final SecurityContext securityContext,
												 final X47BWorkPlaceID id) {
		// An alarm raising is simply a X47BAlarmEvent create (POST)
		// do the http call
		Url restResourceUrl = this.composeURIFor(this.getServicesRESTResourceUrlPathBuilderAs(X47BRESTServiceResourceUrlPathBuilderForAlarmEvent.class)
																.pathOfAlarmsBySourceId());
		// An alarm raising is simply a X47BAlarmEvent create (POST)
		// do the http call
		String ctxXml = _marshaller.forWriting().toXml(securityContext); 		
		HttpResponse httpResponse = DelegateForRawREST.POST(restResourceUrl,
										  					ctxXml,
										  					id.asString());		// a POST with the source id
		// map the response
		CRUDResult<X47BAlarmEvent> outResponse = this.getResponseToCRUDResultMapper()
														.mapHttpResponseForEntity(securityContext,
															  			  		  PersistenceRequestedOperation.CREATE,
															  			  		  id,
															  			  		  restResourceUrl,httpResponse);
		return outResponse;
	}
	@Override
	public CRUDResult<X47BAlarmEvent> raiseAlarm(final SecurityContext securityContext,
												 final X47BWorkPlaceOID oid) {
		// An alarm raising is simply a X47BAlarmEvent create (POST)
		// do the http call
		Url restResourceUrl = this.composeURIFor(this.getServicesRESTResourceUrlPathBuilderAs(X47BRESTServiceResourceUrlPathBuilderForAlarmEvent.class)
																.pathOfAlarmsBySourceOid());
		// An alarm raising is simply a X47BAlarmEvent create (POST)
		// do the http call
		String ctxXml = _marshaller.forWriting().toXml(securityContext); 		
		HttpResponse httpResponse = DelegateForRawREST.POST(restResourceUrl,
										  					ctxXml,
										  					oid.asString());	// a POST with the source oid
		// map the response
		CRUDResult<X47BAlarmEvent> outResponse = this.getResponseToCRUDResultMapper()
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
	public CRUDResult<X47BAlarmEvent> cancelAlarm(final SecurityContext securityContext,
												  final X47BAlarmEventOID oid) {
		// An alarm cancellation is simply a X47BAlarmEvent delete (DELETE)
		// do the http call
		Url restResourceUrl = this.composeURIFor(this.getServicesRESTResourceUrlPathBuilderAs(X47BRESTServiceResourceUrlPathBuilderForAlarmEvent.class)
															   			  .pathOfEntity(oid));
		String ctxXml = _marshaller.forWriting().toXml(securityContext);
		HttpResponse httpResponse = DelegateForRawREST.DELETE(restResourceUrl,
															  ctxXml);
		// map the response
		CRUDResult<X47BAlarmEvent> outResponse = this.getResponseToCRUDResultMapper()
															.mapHttpResponseForEntity(securityContext,
																			  		  PersistenceRequestedOperation.DELETE,
																			  		  restResourceUrl,httpResponse)
																   .identifiedOnErrorBy(oid);
		return outResponse;
	}

}
