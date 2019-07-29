package x47b.services;

import javax.inject.Provider;
import javax.persistence.EntityManager;

import com.google.common.eventbus.EventBus;
import com.google.inject.persist.Transactional;

import lombok.experimental.Accessors;
import r01f.bootstrap.services.config.core.ServicesCoreBootstrapConfigWhenBeanExposed;
import r01f.locale.Language;
import r01f.model.persistence.FindResult;
import r01f.objectstreamer.Marshaller;
import r01f.securitycontext.SecurityContext;
import x47b.api.interfaces.X47BFindServicesForOrganizationalModelObjectBase;
import x47b.model.X47BEntityObject;
import x47b.model.oids.X47BIDs.X47BModelObjectID;
import x47b.model.oids.X47BOIDs.X47BPersistableObjectOID;
import x47b.services.delegates.persistence.X47BCRUDServicesDelegateForWorkPlace;

/**
 * Implements the find-related services which in turn are
 * delegated to {@link X47BCRUDServicesDelegateForWorkPlace}
 */
@Accessors(prefix="_")
public abstract class X47BFindServicesImplForOrganizationalEntityBase<O extends X47BPersistableObjectOID,ID extends X47BModelObjectID<O>,M extends X47BEntityObject<O,ID>>
     		  extends X47BFindServicesImplBase<O,ID,M>
  		   implements X47BFindServicesForOrganizationalModelObjectBase<O,ID,M> {
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BFindServicesImplForOrganizationalEntityBase(final ServicesCoreBootstrapConfigWhenBeanExposed coreCfg,
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
	@Override 
	public FindResult<M> findByNameIn(final SecurityContext securityContext,
									  final Language lang,final String name) {
		return this.forSecurityContext(securityContext)
						.createDelegateAs(X47BFindServicesForOrganizationalModelObjectBase.class)
							.findByNameIn(securityContext,
										  lang,name);
	}
}
