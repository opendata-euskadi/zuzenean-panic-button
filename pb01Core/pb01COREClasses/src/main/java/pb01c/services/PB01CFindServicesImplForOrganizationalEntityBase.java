package pb01c.services;

import javax.inject.Provider;
import javax.persistence.EntityManager;

import com.google.common.eventbus.EventBus;
import com.google.inject.persist.Transactional;

import lombok.experimental.Accessors;
import pb01a.api.interfaces.PB01AFindServicesForOrganizationalModelObjectBase;
import pb01a.model.PB01APersistableObject;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgObjectID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgObjectOID;
import pb01c.services.delegates.persistence.PB01CCRUDServicesDelegateForWorkPlace;
import r01f.bootstrap.services.config.core.ServicesCoreBootstrapConfigWhenBeanExposed;
import r01f.locale.Language;
import r01f.model.persistence.FindResult;
import r01f.objectstreamer.Marshaller;
import r01f.securitycontext.SecurityContext;

/**
 * Implements the find-related services which in turn are
 * delegated to {@link PB01CCRUDServicesDelegateForWorkPlace}
 */
@Accessors(prefix="_")
public abstract class PB01CFindServicesImplForOrganizationalEntityBase<O extends PB01AOrgObjectOID,ID extends PB01AOrgObjectID<O>,M extends PB01APersistableObject<O,ID>>
     		  extends PB01CFindServicesImplBase<O,ID,M>
  		   implements PB01AFindServicesForOrganizationalModelObjectBase<O,ID,M> {
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01CFindServicesImplForOrganizationalEntityBase(final ServicesCoreBootstrapConfigWhenBeanExposed coreCfg,
														   final Marshaller modelObjectsMarshaller,
														   final EventBus eventBus,
														   final Provider<EntityManager> entityManagerProvider) {
		super(coreCfg,
			  modelObjectsMarshaller,
			  eventBus,
			  entityManagerProvider);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	SERVICES EXTENSION
// 	IMPORTANT!!! Do NOT put any logic in these methods ONLY DELEGATE!!!
/////////////////////////////////////////////////////////////////////////////////////////
	@Transactional
	@Override @SuppressWarnings("unchecked")
	public FindResult<M> findByNameIn(final SecurityContext securityContext,
									  final Language lang,final String name) {
		return this.forSecurityContext(securityContext)
						.createDelegateAs(PB01AFindServicesForOrganizationalModelObjectBase.class)
							.findByNameIn(securityContext,
										  lang,name);
	}
}
