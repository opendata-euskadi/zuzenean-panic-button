package x47b.services;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import javax.persistence.EntityManager;

import com.google.common.eventbus.EventBus;
import com.google.inject.persist.Transactional;

import lombok.Getter;
import lombok.experimental.Accessors;
import r01f.bootstrap.services.config.core.ServicesCoreBootstrapConfigWhenBeanExposed;
import r01f.locale.Language;
import r01f.model.annotations.ModelObjectsMarshaller;
import r01f.model.persistence.FindSummariesResult;
import r01f.objectstreamer.Marshaller;
import r01f.securitycontext.SecurityContext;
import r01f.services.persistence.ServiceDelegateProvider;
import x47b.api.interfaces.X47BFindServicesForOrganization;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrganizationID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrganizationOID;
import x47b.model.org.X47BOrganization;
import x47b.services.delegates.persistence.X47BCRUDServicesDelegateForOrganization;
import x47b.services.delegates.persistence.X47BFindServicesDelegateForOrganization;

/**
 * Implements the find-related services which in turn are
 * delegated to {@link X47BCRUDServicesDelegateForOrganization}
 */
@Singleton
@Accessors(prefix="_")
public class X47BFindServicesImplForOrganization
     extends X47BFindServicesImplForOrganizationalEntityBase<X47BOrganizationOID,X47BOrganizationID,X47BOrganization>
  implements X47BFindServicesForOrganization {
/////////////////////////////////////////////////////////////////////////////////////////
//	DELEGATE PROVIDER
/////////////////////////////////////////////////////////////////////////////////////////
	@Getter private final ServiceDelegateProvider<X47BFindServicesDelegateForOrganization> _delegateProvider;
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public X47BFindServicesImplForOrganization(							final ServicesCoreBootstrapConfigWhenBeanExposed coreCfg,
											   @ModelObjectsMarshaller 	final Marshaller modelObjectsMarshaller,
									   		  							final EventBus eventBus,
									   		  							final Provider<EntityManager> entityManagerProvider) {
		super(coreCfg,
			  modelObjectsMarshaller,
			  eventBus,
			  entityManagerProvider);
		_delegateProvider = new ServiceDelegateProvider<X47BFindServicesDelegateForOrganization>() {
									@Override
									public X47BFindServicesDelegateForOrganization createDelegate(final SecurityContext securityContext) {
										return new X47BFindServicesDelegateForOrganization(_coreConfig,
																						   X47BFindServicesImplForOrganization.this.getFreshNewEntityManager(),
																						   _modelObjectsMarshaller,
																						   _eventBus);
									}
							};
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	SERVICES EXTENSION
// 	IMPORTANT!!! Do NOT put any logic in these methods ONLY DELEGATE!!!
/////////////////////////////////////////////////////////////////////////////////////////
	@Transactional
	@Override
	public FindSummariesResult<X47BOrganization> findSummaries(final SecurityContext securityContext,
															   final Language lang) {
		// simply delegate
		return this.forSecurityContext(securityContext)
						.createDelegateAs(X47BFindServicesForOrganization.class)
							   .findSummaries(securityContext,
									   		  lang);

	}
}
