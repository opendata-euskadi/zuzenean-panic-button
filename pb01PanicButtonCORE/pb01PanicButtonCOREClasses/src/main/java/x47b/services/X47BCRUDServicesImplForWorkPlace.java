package x47b.services;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import javax.persistence.EntityManager;

import com.google.common.eventbus.EventBus;

import lombok.Getter;
import lombok.experimental.Accessors;
import r01f.bootstrap.services.config.core.ServicesCoreBootstrapConfigWhenBeanExposed;
import r01f.model.annotations.ModelObjectsMarshaller;
import r01f.objectstreamer.Marshaller;
import r01f.securitycontext.SecurityContext;
import r01f.services.persistence.ServiceDelegateProvider;
import x47b.api.interfaces.X47BCRUDServicesForWorkPlace;
import x47b.model.oids.X47BOrganizationalIDs.X47BWorkPlaceID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BWorkPlaceOID;
import x47b.model.org.X47BWorkPlace;
import x47b.services.delegates.persistence.X47BCRUDServicesDelegateForWorkPlace;

/**
 * Implements the persistence-related services which in turn are
 * delegated to {@link X47BCRUDServicesDelegateForWorkPlace}
 */
@Singleton
@Accessors(prefix="_")
public class X47BCRUDServicesImplForWorkPlace
     extends X47BCRUDServicesImplForOrganizationalEntityBase<X47BWorkPlaceOID,X47BWorkPlaceID,X47BWorkPlace>
  implements X47BCRUDServicesForWorkPlace {
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public X47BCRUDServicesImplForWorkPlace(						final ServicesCoreBootstrapConfigWhenBeanExposed coreCfg,
											@ModelObjectsMarshaller final Marshaller modelObjectsMarshaller,
									   		  						final EventBus eventBus,
									   		  						final Provider<EntityManager> entityManagerProvider) {
		super(coreCfg,
			  modelObjectsMarshaller,
			  eventBus,
			  entityManagerProvider);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	DELEGATE PROVIDER: called at every services impl method to create a fresh new 
//					   EntityManager and avoid transactional issues
/////////////////////////////////////////////////////////////////////////////////////////
	@Getter private final ServiceDelegateProvider<X47BCRUDServicesDelegateForWorkPlace> _delegateProvider =
							new ServiceDelegateProvider<X47BCRUDServicesDelegateForWorkPlace>() {
										@Override
										public X47BCRUDServicesDelegateForWorkPlace createDelegate(final SecurityContext securityContext) {
											return new X47BCRUDServicesDelegateForWorkPlace(_coreConfig,
																							X47BCRUDServicesImplForWorkPlace.this.getFreshNewEntityManager(),
																							_modelObjectsMarshaller,
																							_eventBus);
										}
								};
}
