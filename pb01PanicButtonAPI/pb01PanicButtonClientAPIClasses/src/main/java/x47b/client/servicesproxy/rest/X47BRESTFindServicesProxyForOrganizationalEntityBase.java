package x47b.client.servicesproxy.rest;

import r01f.locale.Language;
import r01f.model.PersistableModelObject;
import r01f.model.persistence.FindResult;
import r01f.objectstreamer.Marshaller;
import r01f.securitycontext.SecurityContext;
import r01f.types.url.Url;
import x47b.api.interfaces.X47BFindServicesForOrganizationalModelObjectBase;
import x47b.client.servicesproxy.rest.X47BRESTServiceResourceUrlPathBuildersBases.X47BRESTServiceResourceUrlPathBuilderForEntityPersistenceBase;
import x47b.model.oids.X47BIDs.X47BModelObjectID;
import x47b.model.oids.X47BOIDs.X47BPersistableObjectOID;


abstract class X47BRESTFindServicesProxyForOrganizationalEntityBase<O extends X47BPersistableObjectOID,ID extends X47BModelObjectID<O>,M extends PersistableModelObject<O>>
	   extends X47BRESTFindServicesProxyBase<O,ID,M>
    implements X47BFindServicesForOrganizationalModelObjectBase<O,ID,M> {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public <P extends X47BRESTServiceResourceUrlPathBuilderForEntityPersistenceBase<O,ID>>
		   X47BRESTFindServicesProxyForOrganizationalEntityBase(final Marshaller marshaller,
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
	public FindResult<M> findByNameIn(final SecurityContext securityContext,
									  final Language lang,final String name) {
		Url restResourceUrl = this.composeURIFor(this.getServicesRESTResourceUrlPathBuilderAs(X47BRESTServiceResourceUrlPathBuilderForEntityPersistenceBase.class)
															   			.pathOfEntityListByNameInLanguage(lang,name));
		return _findDelegate.doFindEntities(securityContext,
											restResourceUrl);
	}

}
