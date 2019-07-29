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
import x47b.api.interfaces.X47BFindServicesForWorkPlace;
import x47b.model.oids.X47BOrganizationalIDs.X47BWorkPlaceID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceLocationOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BWorkPlaceOID;
import x47b.model.org.X47BWorkPlace;
import x47b.services.delegates.persistence.X47BCRUDServicesDelegateForWorkPlace;
import x47b.services.delegates.persistence.X47BFindServicesDelegateForWorkPlace;


/**
 * Implements the find-related services which in turn are
 * delegated to {@link X47BCRUDServicesDelegateForWorkPlace}
 */
@Singleton
@Accessors(prefix="_")
public class X47BFindServicesImplForWorkPlace
     extends X47BFindServicesImplForOrganizationalEntityBase<X47BWorkPlaceOID,X47BWorkPlaceID,X47BWorkPlace>
  implements X47BFindServicesForWorkPlace {
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public X47BFindServicesImplForWorkPlace(						final ServicesCoreBootstrapConfigWhenBeanExposed coreCfg,
											@ModelObjectsMarshaller final Marshaller modelObjectsMarshaller,
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
	@Getter private final ServiceDelegateProvider<X47BFindServicesDelegateForWorkPlace> _delegateProvider =
							new ServiceDelegateProvider<X47BFindServicesDelegateForWorkPlace>() {
										@Override
										public X47BFindServicesDelegateForWorkPlace createDelegate(final SecurityContext securityContext) {
											return new X47BFindServicesDelegateForWorkPlace(_coreConfig,
																							X47BFindServicesImplForWorkPlace.this.getFreshNewEntityManager(),
																							_modelObjectsMarshaller,
																							_eventBus);
										}
							};
/////////////////////////////////////////////////////////////////////////////////////////
//	SERVICES EXTENSION
// 	IMPORTANT!!! Do NOT put any logic in these methods ONLY DELEGATE!!!
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public FindResult<X47BWorkPlace> findByOrgDivisionServiceLocation(final SecurityContext securityContext,
										   final X47BOrgDivisionServiceLocationOID locOid) {
		return this.forSecurityContext(securityContext)
						.createDelegateAs(X47BFindServicesForWorkPlace.class)
							.findByOrgDivisionServiceLocation(securityContext,
											locOid);
	}
	@Override
	public FindSummariesResult<X47BWorkPlace> findSummariesByOrgDivisionServiceLocation(final SecurityContext securityContext,
																  final X47BOrgDivisionServiceLocationOID locOid,
																  final Language lang) {
		return this.forSecurityContext(securityContext)
						.createDelegateAs(X47BFindServicesForWorkPlace.class)
							.findSummariesByOrgDivisionServiceLocation(securityContext,
													 locOid,
													 lang);
	}
}
