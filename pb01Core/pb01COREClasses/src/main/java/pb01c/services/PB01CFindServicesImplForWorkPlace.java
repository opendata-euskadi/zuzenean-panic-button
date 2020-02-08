package pb01c.services;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import javax.persistence.EntityManager;

import com.google.common.eventbus.EventBus;

import lombok.Getter;
import lombok.experimental.Accessors;
import pb01a.api.interfaces.PB01AFindServicesForWorkPlace;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AWorkPlaceID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionServiceLocationOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AWorkPlaceOID;
import pb01a.model.org.PB01AWorkPlace;
import pb01c.services.delegates.persistence.PB01CCRUDServicesDelegateForWorkPlace;
import pb01c.services.delegates.persistence.PB01CFindServicesDelegateForWorkPlace;
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
 * delegated to {@link PB01CCRUDServicesDelegateForWorkPlace}
 */
@Singleton
@Accessors(prefix="_")
public class PB01CFindServicesImplForWorkPlace
     extends PB01CFindServicesImplForOrganizationalEntityBase<PB01AWorkPlaceOID,PB01AWorkPlaceID,PB01AWorkPlace>
  implements PB01AFindServicesForWorkPlace {
/////////////////////////////////////////////////////////////////////////////////////////
//	DELEGATE PROVIDER
/////////////////////////////////////////////////////////////////////////////////////////
	@Getter private final ServiceDelegateProvider<PB01CFindServicesDelegateForWorkPlace> _delegateProvider;
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public PB01CFindServicesImplForWorkPlace(						final ServicesCoreBootstrapConfigWhenBeanExposed coreCfg,
											@ModelObjectsMarshaller final Marshaller modelObjectsMarshaller,
																	final EventBus eventBus,
									   		  						final Provider<EntityManager> entityManagerProvider) {
		super(coreCfg,
			  modelObjectsMarshaller,
			  eventBus,
			  entityManagerProvider);
		_delegateProvider = new ServiceDelegateProvider<PB01CFindServicesDelegateForWorkPlace>() {
									@Override
									public PB01CFindServicesDelegateForWorkPlace createDelegate(final SecurityContext securityContext) {
										return new PB01CFindServicesDelegateForWorkPlace(_coreConfig,
																						PB01CFindServicesImplForWorkPlace.this.getFreshNewEntityManager(),
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
	public FindResult<PB01AWorkPlace> findByOrgDivisionServiceLocation(final SecurityContext securityContext,
										   final PB01AOrgDivisionServiceLocationOID locOid) {
		return this.forSecurityContext(securityContext)
						.createDelegateAs(PB01AFindServicesForWorkPlace.class)
							.findByOrgDivisionServiceLocation(securityContext,
											locOid);
	}
	@Override
	public FindSummariesResult<PB01AWorkPlace> findSummariesByOrgDivisionServiceLocation(final SecurityContext securityContext,
																  final PB01AOrgDivisionServiceLocationOID locOid,
																  final Language lang) {
		return this.forSecurityContext(securityContext)
						.createDelegateAs(PB01AFindServicesForWorkPlace.class)
							.findSummariesByOrgDivisionServiceLocation(securityContext,
													 locOid,
													 lang);
	}
}
