package pb01c.services;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.google.common.eventbus.EventBus;

import lombok.Getter;
import lombok.experimental.Accessors;
import pb01a.api.interfaces.PB01ASearchServicesForOrganizationalEntityObject;
import pb01a.model.search.PB01ASearchFilterForPanicButtonOrganizationalEntity;
import pb01a.model.search.PB01ASearchResultItemForPanicButtonOrganizationalEntity;
import pb01c.db.search.PB01CPanicButtonSearcherProviders.PB01CDBSearcherProviderForPanicButtonObject;
import pb01c.services.delegates.search.PB01CSearchServicesDelegateForEntityModelObject;
import r01f.bootstrap.services.config.core.ServicesCoreBootstrapConfigWhenBeanExposed;
import r01f.model.annotations.ModelObjectsMarshaller;
import r01f.objectstreamer.Marshaller;
import r01f.securitycontext.SecurityContext;
import r01f.services.persistence.CoreSearchServicesForModelObjectBase;
import r01f.services.persistence.ServiceDelegateProvider;

/**
 * Implements the {@link PB01ASearchServicesForOrganizationalEntityObject}s search-related services which in turn are delegated
 * {@link PB01CSearchServicesDelegateForEntityModelObject}
 */
@Singleton
@Accessors(prefix="_")
public class PB01CSearchServicesImplForEntityModelObject
     extends CoreSearchServicesForModelObjectBase<PB01ASearchFilterForPanicButtonOrganizationalEntity,PB01ASearchResultItemForPanicButtonOrganizationalEntity>
  implements PB01ASearchServicesForOrganizationalEntityObject,
  			 PB01CPanicButtonCoreServiceImpl {
/////////////////////////////////////////////////////////////////////////////////////////
//	DELEGATE PROVIDER
/////////////////////////////////////////////////////////////////////////////////////////
	@Getter private final ServiceDelegateProvider<PB01CSearchServicesDelegateForEntityModelObject> _delegateProvider;
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public PB01CSearchServicesImplForEntityModelObject(							final ServicesCoreBootstrapConfigWhenBeanExposed coreCfg,
													  @ModelObjectsMarshaller	final Marshaller modelObjectsMarshaller,
																				final EventBus eventBus,
										   	   									final PB01CDBSearcherProviderForPanicButtonObject searcher) {
		super(coreCfg,
			  modelObjectsMarshaller,
			  eventBus,
			  searcher);
		_delegateProvider = new ServiceDelegateProvider<PB01CSearchServicesDelegateForEntityModelObject>() {
									@Override
									public PB01CSearchServicesDelegateForEntityModelObject createDelegate(final SecurityContext securityContext) {
										return new PB01CSearchServicesDelegateForEntityModelObject(_coreConfig,
																								  PB01CSearchServicesImplForEntityModelObject.this.getFreshNewSearcher(),
																								  _modelObjectsMarshaller,
																								  _eventBus);
									}
						  	};
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	SERVICES EXTENSION
// 	IMPORTANT!!! Do NOT put any logic in these methods ONLY DELEGATE!!!
/////////////////////////////////////////////////////////////////////////////////////////
}
