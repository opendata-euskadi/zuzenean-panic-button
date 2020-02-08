package pb01c.services;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import javax.persistence.EntityManager;

import com.google.common.eventbus.EventBus;

import lombok.Getter;
import lombok.experimental.Accessors;
import pb01a.api.interfaces.PB01ACRUDServicesForOrgDivision;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionOID;
import pb01a.model.org.PB01AOrgDivision;
import pb01c.services.delegates.persistence.PB01CCRUDServicesDelegateForOrgDivision;
import r01f.bootstrap.services.config.core.ServicesCoreBootstrapConfigWhenBeanExposed;
import r01f.model.annotations.ModelObjectsMarshaller;
import r01f.objectstreamer.Marshaller;
import r01f.securitycontext.SecurityContext;
import r01f.services.persistence.ServiceDelegateProvider;

/**
 * Implements the persistence-related services which in turn are
 * delegated to {@link PB01CCRUDServicesDelegateForOrgDivision}
 */
@Singleton
@Accessors(prefix="_")
public class PB01CCRUDServicesImplForOrgDivision
     extends PB01CCRUDServicesImplForOrganizationalEntityBase<PB01AOrgDivisionOID,PB01AOrgDivisionID,PB01AOrgDivision>
  implements PB01ACRUDServicesForOrgDivision {
/////////////////////////////////////////////////////////////////////////////////////////
//	DELEGATE PROVIDER
/////////////////////////////////////////////////////////////////////////////////////////
	@Getter private final ServiceDelegateProvider<PB01CCRUDServicesDelegateForOrgDivision> _delegateProvider;
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public PB01CCRUDServicesImplForOrgDivision(							final ServicesCoreBootstrapConfigWhenBeanExposed coreCfg,
											  @ModelObjectsMarshaller 	final Marshaller modelObjectsMarshaller,
									   		  							final EventBus eventBus,
									   		  							final Provider<EntityManager> entityManagerProvider) {
		super(coreCfg,
			  modelObjectsMarshaller,
			  eventBus,
			  entityManagerProvider);
		_delegateProvider = new ServiceDelegateProvider<PB01CCRUDServicesDelegateForOrgDivision>() {
									@Override
									public PB01CCRUDServicesDelegateForOrgDivision createDelegate(final SecurityContext securityContext) {
										return new PB01CCRUDServicesDelegateForOrgDivision(_coreConfig,
																						  PB01CCRUDServicesImplForOrgDivision.this.getFreshNewEntityManager(),
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
