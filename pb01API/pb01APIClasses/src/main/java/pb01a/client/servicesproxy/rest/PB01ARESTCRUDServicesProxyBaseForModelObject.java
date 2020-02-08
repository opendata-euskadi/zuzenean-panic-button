package pb01a.client.servicesproxy.rest;


import pb01a.api.interfaces.PB01ACRUDServicesBaseForModelObject;
import pb01a.client.servicesproxy.rest.PB01ARESTServiceResourceUrlPathBuildersBases.PB01ARESTServiceResourceUrlPathBuilderForEntityPersistenceBase;
import pb01a.model.oids.PB01AIDs.PB01APersistableObjectID;
import pb01a.model.oids.PB01AOIDs.PB01APersistableObjectOID;
import r01f.httpclient.HttpResponse;
import r01f.model.PersistableModelObject;
import r01f.model.persistence.CRUDResult;
import r01f.model.persistence.PersistenceRequestedOperation;
import r01f.objectstreamer.Marshaller;
import r01f.securitycontext.SecurityContext;
import r01f.services.client.servicesproxy.rest.DelegateForRawREST;
import r01f.services.client.servicesproxy.rest.RESTServicesForDBCRUDProxyBase;
import r01f.types.url.Url;


abstract class PB01ARESTCRUDServicesProxyBaseForModelObject<O extends PB01APersistableObjectOID,ID extends PB01APersistableObjectID<O>,M extends PersistableModelObject<O>>
	   extends RESTServicesForDBCRUDProxyBase<O,M>
    implements PB01ACRUDServicesBaseForModelObject<O,ID,M> {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public <P extends PB01ARESTServiceResourceUrlPathBuilderForEntityPersistenceBase<O,ID>>
		   PB01ARESTCRUDServicesProxyBaseForModelObject(final Marshaller marshaller,
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
		Url restResourceUrl = this.composeURIFor(this.getServicesRESTResourceUrlPathBuilderAs(PB01ARESTServiceResourceUrlPathBuilderForEntityPersistenceBase.class)
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
