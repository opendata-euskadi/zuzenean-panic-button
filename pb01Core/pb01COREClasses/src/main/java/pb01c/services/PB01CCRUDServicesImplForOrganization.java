package pb01c.services;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import javax.persistence.EntityManager;

import com.google.common.eventbus.EventBus;

import lombok.Getter;
import lombok.experimental.Accessors;
import pb01a.api.interfaces.PB01ACRUDServicesForOrganization;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrganizationID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrganizationOID;
import pb01a.model.org.PB01AOrganization;
import pb01c.services.delegates.persistence.PB01CCRUDServicesDelegateForOrganization;
import r01f.bootstrap.services.config.core.ServicesCoreBootstrapConfigWhenBeanExposed;
import r01f.model.annotations.ModelObjectsMarshaller;
import r01f.objectstreamer.Marshaller;
import r01f.securitycontext.SecurityContext;
import r01f.services.persistence.ServiceDelegateProvider;

/**
 * Implements the persistence-related services which in turn are
 * delegated to {@link PB01CCRUDServicesDelegateForOrganization}
 */
@Singleton
@Accessors(prefix="_")
public class PB01CCRUDServicesImplForOrganization
     extends PB01CCRUDServicesImplForOrganizationalEntityBase<PB01AOrganizationOID,PB01AOrganizationID,PB01AOrganization>
  implements PB01ACRUDServicesForOrganization {
/////////////////////////////////////////////////////////////////////////////////////////
//	DELEGATE PROVIDER
/////////////////////////////////////////////////////////////////////////////////////////
	@Getter private final ServiceDelegateProvider<PB01CCRUDServicesDelegateForOrganization> _delegateProvider;
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public PB01CCRUDServicesImplForOrganization(							final ServicesCoreBootstrapConfigWhenBeanExposed coreCfg,
											   @ModelObjectsMarshaller 	final Marshaller modelObjectsMarshaller,
									   		  							final EventBus eventBus,
									   		  							final Provider<EntityManager> entityManagerProvider) {
		super(coreCfg,
			  modelObjectsMarshaller,
			  eventBus,
			  entityManagerProvider);
		_delegateProvider = new ServiceDelegateProvider<PB01CCRUDServicesDelegateForOrganization>() {
									@Override
									public PB01CCRUDServicesDelegateForOrganization createDelegate(final SecurityContext securityContext) {
										return new PB01CCRUDServicesDelegateForOrganization(_coreConfig,
																						   PB01CCRUDServicesImplForOrganization.this.getFreshNewEntityManager(),
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
