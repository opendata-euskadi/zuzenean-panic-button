package x47b.client.servicesproxy.rest;

import r01f.model.PersistableModelObject;
import r01f.objectstreamer.Marshaller;
import r01f.services.client.servicesproxy.rest.RESTServicesForDBFindProxyBase;
import x47b.api.interfaces.X47BFindServicesBase;
import x47b.client.servicesproxy.rest.X47BRESTServiceResourceUrlPathBuildersBases.X47BRESTServiceResourceUrlPathBuilderForEntityPersistenceBase;
import x47b.model.oids.X47BIDs.X47BModelObjectID;
import x47b.model.oids.X47BOIDs.X47BPersistableObjectOID;


abstract class X47BRESTFindServicesProxyBase<O extends X47BPersistableObjectOID,ID extends X47BModelObjectID<O>,M extends PersistableModelObject<O>>
	   extends RESTServicesForDBFindProxyBase<O,M>
    implements X47BFindServicesBase<O,ID,M>,
    		   X47BPanicButtonRESTProxy {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public <P extends X47BRESTServiceResourceUrlPathBuilderForEntityPersistenceBase<O,ID>>
		   X47BRESTFindServicesProxyBase(final Marshaller marshaller,
										 final Class<M> modelObjectType,
										 final P servicesRESTResourceUrlPathBuilder) {
		super(marshaller,
			  modelObjectType,
			  servicesRESTResourceUrlPathBuilder);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
}
