package pb01c.services;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import javax.persistence.EntityManager;

import com.google.common.eventbus.EventBus;
import com.google.inject.persist.Transactional;

import lombok.Getter;
import lombok.experimental.Accessors;
import pb01a.api.interfaces.PB01AFindServicesForOrganization;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrganizationID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrganizationOID;
import pb01a.model.org.PB01AOrganization;
import pb01c.services.delegates.persistence.PB01CCRUDServicesDelegateForOrganization;
import pb01c.services.delegates.persistence.PB01CFindServicesDelegateForOrganization;
import r01f.bootstrap.services.config.core.ServicesCoreBootstrapConfigWhenBeanExposed;
import r01f.locale.Language;
import r01f.model.annotations.ModelObjectsMarshaller;
import r01f.model.persistence.FindSummariesResult;
import r01f.objectstreamer.Marshaller;
import r01f.securitycontext.SecurityContext;
import r01f.services.persistence.ServiceDelegateProvider;

/**
 * Implements the find-related services which in turn are
 * delegated to {@link PB01CCRUDServicesDelegateForOrganization}
 */
@Singleton
@Accessors(prefix="_")
public class PB01CFindServicesImplForOrganization
     extends PB01CFindServicesImplForOrganizationalEntityBase<PB01AOrganizationOID,PB01AOrganizationID,PB01AOrganization>
  implements PB01AFindServicesForOrganization {
/////////////////////////////////////////////////////////////////////////////////////////
//	DELEGATE PROVIDER
/////////////////////////////////////////////////////////////////////////////////////////
	@Getter private final ServiceDelegateProvider<PB01CFindServicesDelegateForOrganization> _delegateProvider;
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public PB01CFindServicesImplForOrganization(							final ServicesCoreBootstrapConfigWhenBeanExposed coreCfg,
											   @ModelObjectsMarshaller 	final Marshaller modelObjectsMarshaller,
									   		  							final EventBus eventBus,
									   		  							final Provider<EntityManager> entityManagerProvider) {
		super(coreCfg,
			  modelObjectsMarshaller,
			  eventBus,
			  entityManagerProvider);
		_delegateProvider = new ServiceDelegateProvider<PB01CFindServicesDelegateForOrganization>() {
									@Override
									public PB01CFindServicesDelegateForOrganization createDelegate(final SecurityContext securityContext) {
										return new PB01CFindServicesDelegateForOrganization(_coreConfig,
																						   PB01CFindServicesImplForOrganization.this.getFreshNewEntityManager(),
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
	public FindSummariesResult<PB01AOrganization> findSummaries(final SecurityContext securityContext,
															   final Language lang) {
		// simply delegate
		return this.forSecurityContext(securityContext)
						.createDelegateAs(PB01AFindServicesForOrganization.class)
							   .findSummaries(securityContext,
									   		  lang);

	}
}
