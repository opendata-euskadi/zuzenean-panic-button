package pb01c.services;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.persistence.EntityManager;

import com.google.common.eventbus.EventBus;
import com.google.inject.persist.Transactional;

import lombok.experimental.Accessors;
import pb01a.api.interfaces.PB01AFindServicesBaseForModelObject;
import pb01a.model.PB01APersistableObject;
import pb01a.model.oids.PB01AIDs.PB01APersistableObjectID;
import pb01a.model.oids.PB01AOIDs.PB01APersistableObjectOID;
import pb01c.services.delegates.persistence.PB01CCRUDServicesDelegateForWorkPlace;
import r01f.bootstrap.services.config.core.ServicesCoreBootstrapConfigWhenBeanExposed;
import r01f.locale.Language;
import r01f.model.annotations.ModelObjectsMarshaller;
import r01f.model.persistence.FindResult;
import r01f.objectstreamer.Marshaller;
import r01f.securitycontext.SecurityContext;
import r01f.services.persistence.CoreFindServicesForModelObjectBase;

/**
 * Implements the find-related services which in turn are
 * delegated to {@link PB01CCRUDServicesDelegateForWorkPlace}
 */
@Accessors(prefix="_")
abstract class PB01CFindServicesImplBaseForEntity<O extends PB01APersistableObjectOID,ID extends PB01APersistableObjectID<O>,M extends PB01APersistableObject<O,ID>>
 	   extends CoreFindServicesForModelObjectBase<O,M>
    implements PB01AFindServicesBaseForModelObject<O,ID,M>,
    		   PB01CPanicButtonCoreServiceImpl {
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public PB01CFindServicesImplBaseForEntity(							final ServicesCoreBootstrapConfigWhenBeanExposed coreCfg,
											 @ModelObjectsMarshaller 	final Marshaller modelObjectsMarshaller,
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
						.createDelegateAs(PB01AFindServicesBaseForModelObject.class)
							.findByNameIn(securityContext,
										  lang,name);
	}
}
