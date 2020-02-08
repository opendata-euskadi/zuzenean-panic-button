package pb01c.services;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import javax.persistence.EntityManager;

import com.google.common.eventbus.EventBus;

import lombok.Getter;
import lombok.experimental.Accessors;
import pb01a.api.interfaces.PB01ACRUDServicesForOrgDivisionService;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionServiceID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionServiceOID;
import pb01a.model.org.PB01AOrgDivisionService;
import pb01c.services.delegates.persistence.PB01CCRUDServicesDelegateForOrgDivisionService;
import r01f.bootstrap.services.config.core.ServicesCoreBootstrapConfigWhenBeanExposed;
import r01f.model.annotations.ModelObjectsMarshaller;
import r01f.objectstreamer.Marshaller;
import r01f.securitycontext.SecurityContext;
import r01f.services.persistence.ServiceDelegateProvider;

/**
 * Implements the persistence-related services which in turn are
 * delegated to {@link PB01CCRUDServicesDelegateForOrgDivisionService}
 */
@Singleton
@Accessors(prefix="_")
public class PB01CCRUDServicesImplForOrgDivisionService
     extends PB01CCRUDServicesImplForOrganizationalEntityBase<PB01AOrgDivisionServiceOID,PB01AOrgDivisionServiceID,PB01AOrgDivisionService>
  implements PB01ACRUDServicesForOrgDivisionService {
/////////////////////////////////////////////////////////////////////////////////////////
//	DELEGATE PROVIDER: called at every services impl method to create a fresh new
//					   EntityManager and avoid transactional issues
/////////////////////////////////////////////////////////////////////////////////////////
	@Getter private final ServiceDelegateProvider<PB01CCRUDServicesDelegateForOrgDivisionService> _delegateProvider;
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public PB01CCRUDServicesImplForOrgDivisionService(							final ServicesCoreBootstrapConfigWhenBeanExposed coreCfg,
											  		 @ModelObjectsMarshaller 	final Marshaller modelObjectsMarshaller,
									   		  									final EventBus eventBus,
									   		  									final Provider<EntityManager> entityManagerProvider) {
		super(coreCfg,
			  modelObjectsMarshaller,
			  eventBus,
			  entityManagerProvider);
		_delegateProvider = new ServiceDelegateProvider<PB01CCRUDServicesDelegateForOrgDivisionService>() {
									@Override
									public PB01CCRUDServicesDelegateForOrgDivisionService createDelegate(final SecurityContext securityContext) {
										return new PB01CCRUDServicesDelegateForOrgDivisionService(_coreConfig,
																								 PB01CCRUDServicesImplForOrgDivisionService.this.getFreshNewEntityManager(),
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
