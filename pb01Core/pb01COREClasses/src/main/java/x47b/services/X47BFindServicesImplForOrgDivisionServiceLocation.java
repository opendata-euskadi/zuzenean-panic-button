package x47b.services;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import javax.persistence.EntityManager;

import com.google.common.eventbus.EventBus;

import lombok.Getter;
import lombok.experimental.Accessors;
import r01f.bootstrap.services.config.core.ServicesCoreBootstrapConfigWhenBeanExposed;
import r01f.locale.Language;
import r01f.model.annotations.ModelObjectsMarshaller;
import r01f.model.persistence.FindResult;
import r01f.model.persistence.FindSummariesResult;
import r01f.objectstreamer.Marshaller;
import r01f.securitycontext.SecurityContext;
import r01f.services.persistence.ServiceDelegateProvider;
import x47b.api.interfaces.X47BFindServicesForOrgDivisionServiceLocation;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionServiceLocationID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceLocationOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceOID;
import x47b.model.org.X47BOrgDivisionServiceLocation;
import x47b.services.delegates.persistence.X47BCRUDServicesDelegateForOrgDivisionServiceLocation;
import x47b.services.delegates.persistence.X47BFindServicesDelegateForOrgDivisionServiceLocation;


/**
 * Implements the find-related services which in turn are
 * delegated to {@link X47BCRUDServicesDelegateForOrgDivisionServiceLocation}
 */
@Singleton
@Accessors(prefix="_")
public class X47BFindServicesImplForOrgDivisionServiceLocation
     extends X47BFindServicesImplForOrganizationalEntityBase<X47BOrgDivisionServiceLocationOID,X47BOrgDivisionServiceLocationID,X47BOrgDivisionServiceLocation>
  implements X47BFindServicesForOrgDivisionServiceLocation {
/////////////////////////////////////////////////////////////////////////////////////////
//	DELEGATE PROVIDER
/////////////////////////////////////////////////////////////////////////////////////////
	@Getter private final ServiceDelegateProvider<X47BFindServicesDelegateForOrgDivisionServiceLocation> _delegateProvider;
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public X47BFindServicesImplForOrgDivisionServiceLocation(							final ServicesCoreBootstrapConfigWhenBeanExposed coreCfg,
															 @ModelObjectsMarshaller 	final Marshaller modelObjectsMarshaller,
															 							final EventBus eventBus,
									   		  								 		    final Provider<EntityManager> entityManagerProvider) {
		super(coreCfg,
			  modelObjectsMarshaller,
			  eventBus,
			  entityManagerProvider);
		_delegateProvider = new ServiceDelegateProvider<X47BFindServicesDelegateForOrgDivisionServiceLocation>() {
									@Override
									public X47BFindServicesDelegateForOrgDivisionServiceLocation createDelegate(final SecurityContext securityContext) {
										return new X47BFindServicesDelegateForOrgDivisionServiceLocation(_coreConfig,
																										 X47BFindServicesImplForOrgDivisionServiceLocation.this.getFreshNewEntityManager(),
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
	public FindResult<X47BOrgDivisionServiceLocation> findByOrgDivisionService(final SecurityContext securityContext,
										   					 				   final X47BOrgDivisionServiceOID serviceOid) {
		return this.forSecurityContext(securityContext)
						.createDelegateAs(X47BFindServicesForOrgDivisionServiceLocation.class)
							.findByOrgDivisionService(securityContext,
											   		  serviceOid);
	}
	@Override
	public FindSummariesResult<X47BOrgDivisionServiceLocation> findSummariesByOrgDivisionService(final SecurityContext securityContext,
																  			   	  				 final X47BOrgDivisionServiceOID serviceOid,
																  			   	  				 final Language lang) {
		return this.forSecurityContext(securityContext)
						.createDelegateAs(X47BFindServicesForOrgDivisionServiceLocation.class)
							.findSummariesByOrgDivisionService(securityContext,
													 		   serviceOid,
													 		   lang);
	}
}
