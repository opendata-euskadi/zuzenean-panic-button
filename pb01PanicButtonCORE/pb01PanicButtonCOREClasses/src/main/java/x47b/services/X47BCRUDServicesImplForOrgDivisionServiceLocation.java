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
import x47b.api.interfaces.X47BCRUDServicesForOrgDivisionServiceLocation;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionServiceLocationID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceLocationOID;
import x47b.model.org.X47BOrgDivisionServiceLocation;
import x47b.services.delegates.persistence.X47BCRUDServicesDelegateForOrgDivisionServiceLocation;

/**
 * Implements the persistence-related services which in turn are
 * delegated to {@link X47BCRUDServicesDelegateForOrgDivisionServiceLocation}
 */
@Singleton
@Accessors(prefix="_")
public class X47BCRUDServicesImplForOrgDivisionServiceLocation
     extends X47BCRUDServicesImplForOrganizationalEntityBase<X47BOrgDivisionServiceLocationOID,X47BOrgDivisionServiceLocationID,X47BOrgDivisionServiceLocation>
  implements X47BCRUDServicesForOrgDivisionServiceLocation {
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public X47BCRUDServicesImplForOrgDivisionServiceLocation(							final ServicesCoreBootstrapConfigWhenBeanExposed coreCfg,
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
	@Getter private final ServiceDelegateProvider<X47BCRUDServicesDelegateForOrgDivisionServiceLocation> _delegateProvider =
								new ServiceDelegateProvider<X47BCRUDServicesDelegateForOrgDivisionServiceLocation>() {
											@Override
											public X47BCRUDServicesDelegateForOrgDivisionServiceLocation createDelegate(final SecurityContext securityContext) {
												return new X47BCRUDServicesDelegateForOrgDivisionServiceLocation(_coreConfig,
																												 X47BCRUDServicesImplForOrgDivisionServiceLocation.this.getFreshNewEntityManager(),
																												 _modelObjectsMarshaller,
																												 _eventBus);
											}
									};
/////////////////////////////////////////////////////////////////////////////////////////
//	SERVICES EXTENSION
// 	IMPORTANT!!! Do NOT put any logic in these methods ONLY DELEGATE!!!
/////////////////////////////////////////////////////////////////////////////////////////

}
