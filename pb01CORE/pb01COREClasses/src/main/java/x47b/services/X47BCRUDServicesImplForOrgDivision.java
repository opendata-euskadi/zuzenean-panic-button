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
import x47b.api.interfaces.X47BCRUDServicesForOrgDivision;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionOID;
import x47b.model.org.X47BOrgDivision;
import x47b.services.delegates.persistence.X47BCRUDServicesDelegateForOrgDivision;

/**
 * Implements the persistence-related services which in turn are
 * delegated to {@link X47BCRUDServicesDelegateForOrgDivision}
 */
@Singleton
@Accessors(prefix="_")
public class X47BCRUDServicesImplForOrgDivision
     extends X47BCRUDServicesImplForOrganizationalEntityBase<X47BOrgDivisionOID,X47BOrgDivisionID,X47BOrgDivision>
  implements X47BCRUDServicesForOrgDivision {
/////////////////////////////////////////////////////////////////////////////////////////
//	DELEGATE PROVIDER
/////////////////////////////////////////////////////////////////////////////////////////
	@Getter private final ServiceDelegateProvider<X47BCRUDServicesDelegateForOrgDivision> _delegateProvider;
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public X47BCRUDServicesImplForOrgDivision(							final ServicesCoreBootstrapConfigWhenBeanExposed coreCfg,
											  @ModelObjectsMarshaller 	final Marshaller modelObjectsMarshaller,
									   		  							final EventBus eventBus,
									   		  							final Provider<EntityManager> entityManagerProvider) {
		super(coreCfg,
			  modelObjectsMarshaller,
			  eventBus,
			  entityManagerProvider);
		_delegateProvider = new ServiceDelegateProvider<X47BCRUDServicesDelegateForOrgDivision>() {
									@Override
									public X47BCRUDServicesDelegateForOrgDivision createDelegate(final SecurityContext securityContext) {
										return new X47BCRUDServicesDelegateForOrgDivision(_coreConfig,
																						  X47BCRUDServicesImplForOrgDivision.this.getFreshNewEntityManager(),
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
