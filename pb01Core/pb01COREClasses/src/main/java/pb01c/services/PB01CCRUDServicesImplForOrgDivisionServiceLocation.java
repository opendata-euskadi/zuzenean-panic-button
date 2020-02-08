package pb01c.services;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import javax.persistence.EntityManager;

import com.google.common.eventbus.EventBus;

import lombok.Getter;
import lombok.experimental.Accessors;
import pb01a.api.interfaces.PB01ACRUDServicesForOrgDivisionServiceLocation;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionServiceLocationID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionServiceLocationOID;
import pb01a.model.org.PB01AOrgDivisionServiceLocation;
import pb01c.services.delegates.persistence.PB01CCRUDServicesDelegateForOrgDivisionServiceLocation;
import r01f.bootstrap.services.config.core.ServicesCoreBootstrapConfigWhenBeanExposed;
import r01f.model.annotations.ModelObjectsMarshaller;
import r01f.objectstreamer.Marshaller;
import r01f.securitycontext.SecurityContext;
import r01f.services.persistence.ServiceDelegateProvider;

/**
 * Implements the persistence-related services which in turn are
 * delegated to {@link PB01CCRUDServicesDelegateForOrgDivisionServiceLocation}
 */
@Singleton
@Accessors(prefix="_")
public class PB01CCRUDServicesImplForOrgDivisionServiceLocation
     extends PB01CCRUDServicesImplForOrganizationalEntityBase<PB01AOrgDivisionServiceLocationOID,PB01AOrgDivisionServiceLocationID,PB01AOrgDivisionServiceLocation>
  implements PB01ACRUDServicesForOrgDivisionServiceLocation {
/////////////////////////////////////////////////////////////////////////////////////////
//	DELEGATE PROVIDER: called at every services impl method to create a fresh new
//					   EntityManager and avoid transactional issues
/////////////////////////////////////////////////////////////////////////////////////////
	@Getter private final ServiceDelegateProvider<PB01CCRUDServicesDelegateForOrgDivisionServiceLocation> _delegateProvider;
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public PB01CCRUDServicesImplForOrgDivisionServiceLocation(							final ServicesCoreBootstrapConfigWhenBeanExposed coreCfg,
											  				 @ModelObjectsMarshaller 	final Marshaller modelObjectsMarshaller,
									   		  									  		final EventBus eventBus,
									   		  								 		   	final Provider<EntityManager> entityManagerProvider) {
		super(coreCfg,
			  modelObjectsMarshaller,
			  eventBus,
			  entityManagerProvider);
		_delegateProvider = new ServiceDelegateProvider<PB01CCRUDServicesDelegateForOrgDivisionServiceLocation>() {
									@Override
									public PB01CCRUDServicesDelegateForOrgDivisionServiceLocation createDelegate(final SecurityContext securityContext) {
										return new PB01CCRUDServicesDelegateForOrgDivisionServiceLocation(_coreConfig,
																										 PB01CCRUDServicesImplForOrgDivisionServiceLocation.this.getFreshNewEntityManager(),
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
