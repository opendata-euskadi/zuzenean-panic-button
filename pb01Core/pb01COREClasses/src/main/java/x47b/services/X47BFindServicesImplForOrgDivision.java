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
import x47b.api.interfaces.X47BFindServicesForOrgDivision;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrganizationOID;
import x47b.model.org.X47BOrgDivision;
import x47b.services.delegates.persistence.X47BCRUDServicesDelegateForOrgDivision;
import x47b.services.delegates.persistence.X47BFindServicesDelegateForOrgDivision;


/**
 * Implements the find-related services which in turn are
 * delegated to {@link X47BCRUDServicesDelegateForOrgDivision}
 */
@Singleton
@Accessors(prefix="_")
public class X47BFindServicesImplForOrgDivision
     extends X47BFindServicesImplForOrganizationalEntityBase<X47BOrgDivisionOID,X47BOrgDivisionID,X47BOrgDivision>
  implements X47BFindServicesForOrgDivision {
/////////////////////////////////////////////////////////////////////////////////////////
//	DELEGATE PROVIDER
/////////////////////////////////////////////////////////////////////////////////////////
	@Getter private final ServiceDelegateProvider<X47BFindServicesDelegateForOrgDivision> _delegateProvider;
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public X47BFindServicesImplForOrgDivision(							final ServicesCoreBootstrapConfigWhenBeanExposed coreCfg,
											  @ModelObjectsMarshaller 	final Marshaller modelObjectsMarshaller,
									   		  							final EventBus eventBus,
									   		  							final Provider<EntityManager> entityManagerProvider) {
		super(coreCfg,
			  modelObjectsMarshaller,
			  eventBus,
			  entityManagerProvider);
		_delegateProvider = new ServiceDelegateProvider<X47BFindServicesDelegateForOrgDivision>() {
									@Override
									public X47BFindServicesDelegateForOrgDivision createDelegate(final SecurityContext securityContext) {
										return new X47BFindServicesDelegateForOrgDivision(_coreConfig,
																						  X47BFindServicesImplForOrgDivision.this.getFreshNewEntityManager(),
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
	public FindResult<X47BOrgDivision> findByOrganization(final SecurityContext securityContext,
													   	  final X47BOrganizationOID orgOid) {
		return this.forSecurityContext(securityContext)
						.createDelegateAs(X47BFindServicesForOrgDivision.class)
							.findByOrganization(securityContext,
												orgOid);
	}
	@Override
	public FindSummariesResult<X47BOrgDivision> findSummariesByOrganization(final SecurityContext securityContext,
																		 	final X47BOrganizationOID orgOid,
																		 	final Language lang) {
		return this.forSecurityContext(securityContext)
						.createDelegateAs(X47BFindServicesForOrgDivision.class)
							.findSummariesByOrganization(securityContext,
														 orgOid,lang);
	}
}
