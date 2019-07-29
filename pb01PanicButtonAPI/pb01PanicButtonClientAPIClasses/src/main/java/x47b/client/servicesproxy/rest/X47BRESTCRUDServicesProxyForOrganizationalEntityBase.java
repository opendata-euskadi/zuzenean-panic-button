package x47b.client.servicesproxy.rest;

import r01f.model.PersistableModelObject;
import r01f.objectstreamer.Marshaller;
import x47b.api.interfaces.X47BCRUDServicesForOrganizationalModelObjectBase;
import x47b.client.servicesproxy.rest.X47BRESTServiceResourceUrlPathBuildersBases.X47BRESTServiceResourceUrlPathBuilderForEntityPersistenceBase;
import x47b.model.oids.X47BIDs.X47BModelObjectID;
import x47b.model.oids.X47BOIDs.X47BPersistableObjectOID;


abstract class X47BRESTCRUDServicesProxyForOrganizationalEntityBase<O extends X47BPersistableObjectOID,ID extends X47BModelObjectID<O>,M extends PersistableModelObject<O>>
	   extends X47BRESTCRUDServicesProxyBase<O,ID,M>
    implements X47BCRUDServicesForOrganizationalModelObjectBase<O,ID,M>,
    		   X47BPanicButtonRESTProxy {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public <P extends X47BRESTServiceResourceUrlPathBuilderForEntityPersistenceBase<O,ID>>
		   X47BRESTCRUDServicesProxyForOrganizationalEntityBase(final Marshaller marshaller,
												  				final Class<M> modelObjectType,
												  				final P servicesRESTResourceUrlPathBuilder) {
		super(marshaller,
			  modelObjectType,
			  servicesRESTResourceUrlPathBuilder);
	}
}
