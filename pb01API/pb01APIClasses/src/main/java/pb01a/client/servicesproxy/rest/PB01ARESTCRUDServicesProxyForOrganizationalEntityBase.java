package pb01a.client.servicesproxy.rest;

import pb01a.api.interfaces.PB01ACRUDServicesForOrganizationalModelObjectBase;
import pb01a.client.servicesproxy.rest.PB01ARESTServiceResourceUrlPathBuildersBases.PB01ARESTServiceResourceUrlPathBuilderForEntityPersistenceBase;
import pb01a.model.oids.PB01AIDs.PB01APersistableObjectID;
import pb01a.model.oids.PB01AOIDs.PB01APersistableObjectOID;
import r01f.model.PersistableModelObject;
import r01f.objectstreamer.Marshaller;


abstract class PB01ARESTCRUDServicesProxyForOrganizationalEntityBase<O extends PB01APersistableObjectOID,ID extends PB01APersistableObjectID<O>,M extends PersistableModelObject<O>>
	   extends PB01ARESTCRUDServicesProxyBase<O,ID,M>
    implements PB01ACRUDServicesForOrganizationalModelObjectBase<O,ID,M>,
    		   PB01APanicButtonRESTProxy {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public <P extends PB01ARESTServiceResourceUrlPathBuilderForEntityPersistenceBase<O,ID>>
		   PB01ARESTCRUDServicesProxyForOrganizationalEntityBase(final Marshaller marshaller,
												  				final Class<M> modelObjectType,
												  				final P servicesRESTResourceUrlPathBuilder) {
		super(marshaller,
			  modelObjectType,
			  servicesRESTResourceUrlPathBuilder);
	}
}
