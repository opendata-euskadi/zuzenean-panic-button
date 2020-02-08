package pb01c.services;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import javax.persistence.EntityManager;

import com.google.common.eventbus.EventBus;

import lombok.Getter;
import lombok.experimental.Accessors;
import pb01a.api.interfaces.PB01AFindServicesForOrgDivision;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrganizationOID;
import pb01a.model.org.PB01AOrgDivision;
import pb01c.services.delegates.persistence.PB01CCRUDServicesDelegateForOrgDivision;
import pb01c.services.delegates.persistence.PB01CFindServicesDelegateForOrgDivision;
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
 * delegated to {@link PB01CCRUDServicesDelegateForOrgDivision}
 */
@Singleton
@Accessors(prefix="_")
public class PB01CFindServicesImplForOrgDivision
     extends PB01CFindServicesImplForOrganizationalEntityBase<PB01AOrgDivisionOID,PB01AOrgDivisionID,PB01AOrgDivision>
  implements PB01AFindServicesForOrgDivision {
/////////////////////////////////////////////////////////////////////////////////////////
//	DELEGATE PROVIDER
/////////////////////////////////////////////////////////////////////////////////////////
	@Getter private final ServiceDelegateProvider<PB01CFindServicesDelegateForOrgDivision> _delegateProvider;
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public PB01CFindServicesImplForOrgDivision(							final ServicesCoreBootstrapConfigWhenBeanExposed coreCfg,
											  @ModelObjectsMarshaller 	final Marshaller modelObjectsMarshaller,
									   		  							final EventBus eventBus,
									   		  							final Provider<EntityManager> entityManagerProvider) {
		super(coreCfg,
			  modelObjectsMarshaller,
			  eventBus,
			  entityManagerProvider);
		_delegateProvider = new ServiceDelegateProvider<PB01CFindServicesDelegateForOrgDivision>() {
									@Override
									public PB01CFindServicesDelegateForOrgDivision createDelegate(final SecurityContext securityContext) {
										return new PB01CFindServicesDelegateForOrgDivision(_coreConfig,
																						  PB01CFindServicesImplForOrgDivision.this.getFreshNewEntityManager(),
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
	public FindResult<PB01AOrgDivision> findByOrganization(final SecurityContext securityContext,
													   	  final PB01AOrganizationOID orgOid) {
		return this.forSecurityContext(securityContext)
						.createDelegateAs(PB01AFindServicesForOrgDivision.class)
							.findByOrganization(securityContext,
												orgOid);
	}
	@Override
	public FindSummariesResult<PB01AOrgDivision> findSummariesByOrganization(final SecurityContext securityContext,
																		 	final PB01AOrganizationOID orgOid,
																		 	final Language lang) {
		return this.forSecurityContext(securityContext)
						.createDelegateAs(PB01AFindServicesForOrgDivision.class)
							.findSummariesByOrganization(securityContext,
														 orgOid,lang);
	}
}
