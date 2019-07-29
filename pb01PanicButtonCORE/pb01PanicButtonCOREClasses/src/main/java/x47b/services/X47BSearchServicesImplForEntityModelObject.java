package x47b.services;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.google.common.eventbus.EventBus;

import lombok.Getter;
import lombok.experimental.Accessors;
import r01f.bootstrap.services.config.core.ServicesCoreBootstrapConfigWhenBeanExposed;
import r01f.model.annotations.ModelObjectsMarshaller;
import r01f.objectstreamer.Marshaller;
import r01f.securitycontext.SecurityContext;
import r01f.services.persistence.CoreSearchServicesForModelObjectBase;
import r01f.services.persistence.ServiceDelegateProvider;
import x47b.api.interfaces.X47BSearchServicesForOrganizationalEntityObject;
import x47b.model.search.X47BSearchFilterForPanicButtonOrganizationalEntity;
import x47b.model.search.X47BSearchResultItemForPanicButtonOrganizationalEntity;
import x47b.search.db.X47BPanicButtonSearcherProviders.X47BDBSearcherProviderForPanicButtonObject;
import x47b.services.delegates.search.X47BSearchServicesDelegateForEntityModelObject;

/**
 * Implements the {@link X47BSearchServicesForOrganizationalEntityObject}s search-related services which in turn are delegated
 * {@link X47BSearchServicesDelegateForEntityModelObject} 
 */
@Singleton
@Accessors(prefix="_")
public class X47BSearchServicesImplForEntityModelObject 
     extends CoreSearchServicesForModelObjectBase<X47BSearchFilterForPanicButtonOrganizationalEntity,X47BSearchResultItemForPanicButtonOrganizationalEntity>					  
  implements X47BSearchServicesForOrganizationalEntityObject,
  			 X47BPanicButtonCoreServiceImpl {
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public X47BSearchServicesImplForEntityModelObject(							final ServicesCoreBootstrapConfigWhenBeanExposed coreCfg,
													  @ModelObjectsMarshaller	final Marshaller modelObjectsMarshaller,
																				final EventBus eventBus,
										   	   									final X47BDBSearcherProviderForPanicButtonObject searcher) {
		super(coreCfg,
			  modelObjectsMarshaller,
			  eventBus,
			  searcher);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	INJECTED STATUS 
/////////////////////////////////////////////////////////////////////////////////////////
	@Getter private final ServiceDelegateProvider<X47BSearchServicesDelegateForEntityModelObject> _delegateProvider = 
							new ServiceDelegateProvider<X47BSearchServicesDelegateForEntityModelObject>() {
									@Override
									public X47BSearchServicesDelegateForEntityModelObject createDelegate(final SecurityContext securityContext) {
										return new X47BSearchServicesDelegateForEntityModelObject(_coreConfig,
																								  X47BSearchServicesImplForEntityModelObject.this.getFreshNewSearcher(),
																								  _modelObjectsMarshaller,
																								  _eventBus);
									}
						  	};
/////////////////////////////////////////////////////////////////////////////////////////
//	SERVICES EXTENSION
// 	IMPORTANT!!! Do NOT put any logic in these methods ONLY DELEGATE!!!
/////////////////////////////////////////////////////////////////////////////////////////
}
