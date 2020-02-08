package pb01c.services;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import javax.persistence.EntityManager;

import com.google.common.eventbus.EventBus;

import lombok.Getter;
import lombok.experimental.Accessors;
import pb01a.api.interfaces.PB01ACRUDServicesForWorkPlace;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AWorkPlaceID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AWorkPlaceOID;
import pb01a.model.org.PB01AWorkPlace;
import pb01c.services.delegates.persistence.PB01CCRUDServicesDelegateForWorkPlace;
import r01f.bootstrap.services.config.core.ServicesCoreBootstrapConfigWhenBeanExposed;
import r01f.model.annotations.ModelObjectsMarshaller;
import r01f.objectstreamer.Marshaller;
import r01f.securitycontext.SecurityContext;
import r01f.services.persistence.ServiceDelegateProvider;

/**
 * Implements the persistence-related services which in turn are
 * delegated to {@link PB01CCRUDServicesDelegateForWorkPlace}
 */
@Singleton
@Accessors(prefix="_")
public class PB01CCRUDServicesImplForWorkPlace
     extends PB01CCRUDServicesImplForOrganizationalEntityBase<PB01AWorkPlaceOID,PB01AWorkPlaceID,PB01AWorkPlace>
  implements PB01ACRUDServicesForWorkPlace {
/////////////////////////////////////////////////////////////////////////////////////////
//	DELEGATE PROVIDER: called at every services impl method to create a fresh new
//					   EntityManager and avoid transactional issues
/////////////////////////////////////////////////////////////////////////////////////////
	@Getter private final ServiceDelegateProvider<PB01CCRUDServicesDelegateForWorkPlace> _delegateProvider;
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public PB01CCRUDServicesImplForWorkPlace(						final ServicesCoreBootstrapConfigWhenBeanExposed coreCfg,
											@ModelObjectsMarshaller final Marshaller modelObjectsMarshaller,
									   		  						final EventBus eventBus,
									   		  						final Provider<EntityManager> entityManagerProvider) {
		super(coreCfg,
			  modelObjectsMarshaller,
			  eventBus,
			  entityManagerProvider);
		_delegateProvider = new ServiceDelegateProvider<PB01CCRUDServicesDelegateForWorkPlace>() {
									@Override
									public PB01CCRUDServicesDelegateForWorkPlace createDelegate(final SecurityContext securityContext) {
										return new PB01CCRUDServicesDelegateForWorkPlace(_coreConfig,
																						PB01CCRUDServicesImplForWorkPlace.this.getFreshNewEntityManager(),
																						_modelObjectsMarshaller,
																						_eventBus);
									}
							};
	}
}
