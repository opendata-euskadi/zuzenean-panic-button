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
import x47b.api.interfaces.X47BCRUDServicesForOrgDivisionService;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionServiceID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceOID;
import x47b.model.org.X47BOrgDivisionService;
import x47b.services.delegates.persistence.X47BCRUDServicesDelegateForOrgDivisionService;

/**
 * Implements the persistence-related services which in turn are
 * delegated to {@link X47BCRUDServicesDelegateForOrgDivisionService}
 */
@Singleton
@Accessors(prefix="_")
public class X47BCRUDServicesImplForOrgDivisionService
     extends X47BCRUDServicesImplForOrganizationalEntityBase<X47BOrgDivisionServiceOID,X47BOrgDivisionServiceID,X47BOrgDivisionService>
  implements X47BCRUDServicesForOrgDivisionService {
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public X47BCRUDServicesImplForOrgDivisionService(							final ServicesCoreBootstrapConfigWhenBeanExposed coreCfg,
											  		 @ModelObjectsMarshaller 	final Marshaller modelObjectsMarshaller,
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
	@Getter private final ServiceDelegateProvider<X47BCRUDServicesDelegateForOrgDivisionService> _delegateProvider =
								new ServiceDelegateProvider<X47BCRUDServicesDelegateForOrgDivisionService>() {
											@Override
											public X47BCRUDServicesDelegateForOrgDivisionService createDelegate(final SecurityContext securityContext) {
												return new X47BCRUDServicesDelegateForOrgDivisionService(_coreConfig,
																										 X47BCRUDServicesImplForOrgDivisionService.this.getFreshNewEntityManager(),
																										 _modelObjectsMarshaller,
																										 _eventBus);
											}
									};
/////////////////////////////////////////////////////////////////////////////////////////
//	SERVICES EXTENSION
// 	IMPORTANT!!! Do NOT put any logic in these methods ONLY DELEGATE!!!
/////////////////////////////////////////////////////////////////////////////////////////

}
