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
import x47b.api.interfaces.X47BFindServicesForOrgDivisionService;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionServiceID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceOID;
import x47b.model.org.X47BOrgDivisionService;
import x47b.services.delegates.persistence.X47BCRUDServicesDelegateForOrgDivisionService;
import x47b.services.delegates.persistence.X47BFindServicesDelegateForOrgDivisionService;


/**
 * Implements the find-related services which in turn are
 * delegated to {@link X47BCRUDServicesDelegateForOrgDivisionService}
 */
@Singleton
@Accessors(prefix="_")
public class X47BFindServicesImplForOrgDivisionService
     extends X47BFindServicesImplForOrganizationalEntityBase<X47BOrgDivisionServiceOID,X47BOrgDivisionServiceID,X47BOrgDivisionService>
  implements X47BFindServicesForOrgDivisionService {
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public X47BFindServicesImplForOrgDivisionService(							final ServicesCoreBootstrapConfigWhenBeanExposed coreCfg,
											  	     @ModelObjectsMarshaller 	final Marshaller modelObjectsMarshaller,
									   		  									final EventBus eventBus,
									   		  									final Provider<EntityManager> entityManagerProvider) {
		super(coreCfg,
			  modelObjectsMarshaller,
			  eventBus,
			  entityManagerProvider);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	DELEGATE PROVIDER
/////////////////////////////////////////////////////////////////////////////////////////
	@Getter private final ServiceDelegateProvider<X47BFindServicesDelegateForOrgDivisionService> _delegateProvider =
							new ServiceDelegateProvider<X47BFindServicesDelegateForOrgDivisionService>() {
										@Override
										public X47BFindServicesDelegateForOrgDivisionService createDelegate(final SecurityContext securityContext) {
											return new X47BFindServicesDelegateForOrgDivisionService(_coreConfig,
																									 X47BFindServicesImplForOrgDivisionService.this.getFreshNewEntityManager(),
																									 _modelObjectsMarshaller,
																									 _eventBus);
										}
							};
/////////////////////////////////////////////////////////////////////////////////////////
//	SERVICES EXTENSION
// 	IMPORTANT!!! Do NOT put any logic in these methods ONLY DELEGATE!!!
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public FindResult<X47BOrgDivisionService> findByOrgDivision(final SecurityContext securityContext,
										   					 	final X47BOrgDivisionOID orgDivisionOid) {
		return this.forSecurityContext(securityContext)
						.createDelegateAs(X47BFindServicesForOrgDivisionService.class)
							.findByOrgDivision(securityContext,
											   orgDivisionOid);
	}
	@Override
	public FindSummariesResult<X47BOrgDivisionService> findSummariesByOrgDivision(final SecurityContext securityContext,
																  			   	  final X47BOrgDivisionOID orgDivisionOid,
																  			   	  final Language lang) {
		return this.forSecurityContext(securityContext)
						.createDelegateAs(X47BFindServicesForOrgDivisionService.class)
							.findSummariesByOrgDivision(securityContext,
													 	orgDivisionOid,
													 	lang);
	}
}
