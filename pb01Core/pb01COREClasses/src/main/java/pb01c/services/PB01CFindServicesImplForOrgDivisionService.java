package pb01c.services;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import javax.persistence.EntityManager;

import com.google.common.eventbus.EventBus;

import lombok.Getter;
import lombok.experimental.Accessors;
import pb01a.api.interfaces.PB01AFindServicesForOrgDivisionService;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionServiceID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionServiceOID;
import pb01a.model.org.PB01AOrgDivisionService;
import pb01c.services.delegates.persistence.PB01CCRUDServicesDelegateForOrgDivisionService;
import pb01c.services.delegates.persistence.PB01CFindServicesDelegateForOrgDivisionService;
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
 * delegated to {@link PB01CCRUDServicesDelegateForOrgDivisionService}
 */
@Singleton
@Accessors(prefix="_")
public class PB01CFindServicesImplForOrgDivisionService
     extends PB01CFindServicesImplForOrganizationalEntityBase<PB01AOrgDivisionServiceOID,PB01AOrgDivisionServiceID,PB01AOrgDivisionService>
  implements PB01AFindServicesForOrgDivisionService {
/////////////////////////////////////////////////////////////////////////////////////////
//	DELEGATE PROVIDER
/////////////////////////////////////////////////////////////////////////////////////////
	@Getter private final ServiceDelegateProvider<PB01CFindServicesDelegateForOrgDivisionService> _delegateProvider;
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public PB01CFindServicesImplForOrgDivisionService(							final ServicesCoreBootstrapConfigWhenBeanExposed coreCfg,
											  	     @ModelObjectsMarshaller 	final Marshaller modelObjectsMarshaller,
									   		  									final EventBus eventBus,
									   		  									final Provider<EntityManager> entityManagerProvider) {
		super(coreCfg,
			  modelObjectsMarshaller,
			  eventBus,
			  entityManagerProvider);
		_delegateProvider = new ServiceDelegateProvider<PB01CFindServicesDelegateForOrgDivisionService>() {
									@Override
									public PB01CFindServicesDelegateForOrgDivisionService createDelegate(final SecurityContext securityContext) {
										return new PB01CFindServicesDelegateForOrgDivisionService(_coreConfig,
																								 PB01CFindServicesImplForOrgDivisionService.this.getFreshNewEntityManager(),
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
	public FindResult<PB01AOrgDivisionService> findByOrgDivision(final SecurityContext securityContext,
										   					 	final PB01AOrgDivisionOID orgDivisionOid) {
		return this.forSecurityContext(securityContext)
						.createDelegateAs(PB01AFindServicesForOrgDivisionService.class)
							.findByOrgDivision(securityContext,
											   orgDivisionOid);
	}
	@Override
	public FindSummariesResult<PB01AOrgDivisionService> findSummariesByOrgDivision(final SecurityContext securityContext,
																  			   	  final PB01AOrgDivisionOID orgDivisionOid,
																  			   	  final Language lang) {
		return this.forSecurityContext(securityContext)
						.createDelegateAs(PB01AFindServicesForOrgDivisionService.class)
							.findSummariesByOrgDivision(securityContext,
													 	orgDivisionOid,
													 	lang);
	}
}
