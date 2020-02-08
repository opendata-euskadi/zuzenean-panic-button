package pb01a.client.servicesproxy.rest;

import pb01a.api.interfaces.PB01AFindServicesForOrganizationalModelObjectBase;
import pb01a.client.servicesproxy.rest.PB01ARESTServiceResourceUrlPathBuildersBases.PB01ARESTServiceResourceUrlPathBuilderForEntityPersistenceBase;
import pb01a.model.oids.PB01AIDs.PB01APersistableObjectID;
import pb01a.model.oids.PB01AOIDs.PB01APersistableObjectOID;
import r01f.locale.Language;
import r01f.model.PersistableModelObject;
import r01f.model.persistence.FindResult;
import r01f.objectstreamer.Marshaller;
import r01f.securitycontext.SecurityContext;
import r01f.types.url.Url;


abstract class PB01ARESTFindServicesProxyForOrganizationalEntityBase<O extends PB01APersistableObjectOID,ID extends PB01APersistableObjectID<O>,M extends PersistableModelObject<O>>
	   extends PB01ARESTFindServicesProxyBase<O,ID,M>
    implements PB01AFindServicesForOrganizationalModelObjectBase<O,ID,M> {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public <P extends PB01ARESTServiceResourceUrlPathBuilderForEntityPersistenceBase<O,ID>>
		   PB01ARESTFindServicesProxyForOrganizationalEntityBase(final Marshaller marshaller,
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
		Url restResourceUrl = this.composeURIFor(this.getServicesRESTResourceUrlPathBuilderAs(PB01ARESTServiceResourceUrlPathBuilderForEntityPersistenceBase.class)
															   			.pathOfEntityListByNameInLanguage(lang,name));
		return _findDelegate.doFindEntities(securityContext,
											restResourceUrl);
	}

}
