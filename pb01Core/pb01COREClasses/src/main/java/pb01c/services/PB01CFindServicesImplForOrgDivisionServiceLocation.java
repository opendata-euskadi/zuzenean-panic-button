package pb01c.services;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import javax.persistence.EntityManager;

import com.google.common.eventbus.EventBus;

import lombok.Getter;
import lombok.experimental.Accessors;
import pb01a.api.interfaces.PB01AFindServicesForOrgDivisionServiceLocation;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionServiceLocationID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionServiceLocationOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionServiceOID;
import pb01a.model.org.PB01AOrgDivisionServiceLocation;
import pb01c.services.delegates.persistence.PB01CCRUDServicesDelegateForOrgDivisionServiceLocation;
import pb01c.services.delegates.persistence.PB01CFindServicesDelegateForOrgDivisionServiceLocation;
import r01f.bootstrap.services.config.core.ServicesCoreBootstrapConfigWhenBeanExposed;
import r01f.locale.Language;
import r01f.model.annotations.ModelObjectsMarshaller;
import r01f.model.persistence.FindResult;
import r01f.model.persistence.FindSummariesResult;
import r01f.objectstreamer.Marshaller;
import r01f.securitycontext.SecurityContext;
import r01f.services.persistence.ServiceDelegateProvider;


/**
 * Implements the find-related services which in turn are
 * delegated to {@link PB01CCRUDServicesDelegateForOrgDivisionServiceLocation}
 */
@Singleton
@Accessors(prefix="_")
public class PB01CFindServicesImplForOrgDivisionServiceLocation
     extends PB01CFindServicesImplForOrganizationalEntityBase<PB01AOrgDivisionServiceLocationOID,PB01AOrgDivisionServiceLocationID,PB01AOrgDivisionServiceLocation>
  implements PB01AFindServicesForOrgDivisionServiceLocation {
/////////////////////////////////////////////////////////////////////////////////////////
//	DELEGATE PROVIDER
/////////////////////////////////////////////////////////////////////////////////////////
	@Getter private final ServiceDelegateProvider<PB01CFindServicesDelegateForOrgDivisionServiceLocation> _delegateProvider;
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public PB01CFindServicesImplForOrgDivisionServiceLocation(							final ServicesCoreBootstrapConfigWhenBeanExposed coreCfg,
															 @ModelObjectsMarshaller 	final Marshaller modelObjectsMarshaller,
															 							final EventBus eventBus,
									   		  								 		    final Provider<EntityManager> entityManagerProvider) {
		super(coreCfg,
			  modelObjectsMarshaller,
			  eventBus,
			  entityManagerProvider);
		_delegateProvider = new ServiceDelegateProvider<PB01CFindServicesDelegateForOrgDivisionServiceLocation>() {
									@Override
									public PB01CFindServicesDelegateForOrgDivisionServiceLocation createDelegate(final SecurityContext securityContext) {
										return new PB01CFindServicesDelegateForOrgDivisionServiceLocation(_coreConfig,
																										 PB01CFindServicesImplForOrgDivisionServiceLocation.this.getFreshNewEntityManager(),
																										 _modelObjectsMarshaller,
																										 _eventBus);
									}
							};
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	SERVICES EXTENSION
// 	IMPORTANT!!! Do NOT put any logic in these methods ONLY DELEGATE!!!
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public FindResult<PB01AOrgDivisionServiceLocation> findByOrgDivisionService(final SecurityContext securityContext,
										   					 				   final PB01AOrgDivisionServiceOID serviceOid) {
		return this.forSecurityContext(securityContext)
						.createDelegateAs(PB01AFindServicesForOrgDivisionServiceLocation.class)
							.findByOrgDivisionService(securityContext,
											   		  serviceOid);
	}
	@Override
	public FindSummariesResult<PB01AOrgDivisionServiceLocation> findSummariesByOrgDivisionService(final SecurityContext securityContext,
																  			   	  				 final PB01AOrgDivisionServiceOID serviceOid,
																  			   	  				 final Language lang) {
		return this.forSecurityContext(securityContext)
						.createDelegateAs(PB01AFindServicesForOrgDivisionServiceLocation.class)
							.findSummariesByOrgDivisionService(securityContext,
													 		   serviceOid,
													 		   lang);
	}
}
