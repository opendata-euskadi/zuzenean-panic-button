package x47b.client.servicesproxy.rest;


import r01f.httpclient.HttpResponse;
import r01f.model.PersistableModelObject;
import r01f.model.persistence.CRUDResult;
import r01f.model.persistence.PersistenceRequestedOperation;
import r01f.objectstreamer.Marshaller;
import r01f.securitycontext.SecurityContext;
import r01f.services.client.servicesproxy.rest.DelegateForRawREST;
import r01f.services.client.servicesproxy.rest.RESTServicesForDBCRUDProxyBase;
import r01f.types.url.Url;
import x47b.api.interfaces.X47BCRUDServicesBaseForModelObject;
import x47b.client.servicesproxy.rest.X47BRESTServiceResourceUrlPathBuildersBases.X47BRESTServiceResourceUrlPathBuilderForEntityPersistenceBase;
import x47b.model.oids.X47BIDs.X47BModelObjectID;
import x47b.model.oids.X47BOIDs.X47BPersistableObjectOID;


abstract class X47BRESTCRUDServicesProxyBaseForModelObject<O extends X47BPersistableObjectOID,ID extends X47BModelObjectID<O>,M extends PersistableModelObject<O>>
	   extends RESTServicesForDBCRUDProxyBase<O,M>
    implements X47BCRUDServicesBaseForModelObject<O,ID,M> {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public <P extends X47BRESTServiceResourceUrlPathBuilderForEntityPersistenceBase<O,ID>>
		   X47BRESTCRUDServicesProxyBaseForModelObject(final Marshaller marshaller,
												  final Class<M> modelObjectType,
												  final P servicesRESTResourceUrlPathBuilder) {
		super(marshaller,
			  modelObjectType,
			  servicesRESTResourceUrlPathBuilder);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	@Override 
	public CRUDResult<M> loadById(final SecurityContext securityContext,
								  final ID id) {
		Url restResourceUrl = this.composeURIFor(this.getServicesRESTResourceUrlPathBuilderAs(X47BRESTServiceResourceUrlPathBuilderForEntityPersistenceBase.class)
															   			  .pathOfEntityById(id));
		String ctxXml = _marshaller.forWriting().toXml(securityContext);
		HttpResponse httpResponse = DelegateForRawREST.GET(restResourceUrl,
										 				   ctxXml);
		// map the response
		CRUDResult<M> outResponse = this.getResponseToCRUDResultMapperForModelObject()
											.mapHttpResponseForEntity(securityContext,
											  				  		  PersistenceRequestedOperation.LOAD,
											  				  		  restResourceUrl,httpResponse)
										    .identifiedOnErrorBy(id);
		// log & return
		_logResponse(restResourceUrl,outResponse);
		return outResponse;
	}
}
