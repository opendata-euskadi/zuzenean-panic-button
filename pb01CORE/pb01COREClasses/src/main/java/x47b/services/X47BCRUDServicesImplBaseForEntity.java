package x47b.services;

import javax.inject.Provider;
import javax.persistence.EntityManager;

import com.google.common.eventbus.EventBus;
import com.google.inject.persist.Transactional;

import lombok.experimental.Accessors;
import r01f.bootstrap.services.config.core.ServicesCoreBootstrapConfigWhenBeanExposed;
import r01f.model.persistence.CRUDResult;
import r01f.objectstreamer.Marshaller;
import r01f.securitycontext.SecurityContext;
import r01f.services.persistence.CoreCRUDServicesForModelObjectBase;
import r01f.xmlproperties.XMLPropertiesForAppComponent;
import x47b.api.interfaces.X47BCRUDServicesBaseForModelObject;
import x47b.model.X47BPersistableObject;
import x47b.model.oids.X47BIDs.X47BPersistableObjectID;
import x47b.model.oids.X47BOIDs.X47BPersistableObjectOID;

@Accessors(prefix="_")
abstract class X47BCRUDServicesImplBaseForEntity<O extends X47BPersistableObjectOID,ID extends X47BPersistableObjectID<O>,M extends X47BPersistableObject<O,ID>>
 	   extends CoreCRUDServicesForModelObjectBase<O,M>
    implements X47BCRUDServicesBaseForModelObject<O,ID,M>,
    		   X47BPanicButtonCoreServiceImpl {
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BCRUDServicesImplBaseForEntity(final ServicesCoreBootstrapConfigWhenBeanExposed coreCfg,
											 final Marshaller modelObjectsMarshaller,
											 final EventBus eventBus,
											 final XMLPropertiesForAppComponent persistenceProperties,
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
	public CRUDResult<M> loadById(final SecurityContext securityContext,
								  final ID id) {
		CRUDResult<M> outResult = this.forSecurityContext(securityContext)
											.createDelegateAs(X47BCRUDServicesBaseForModelObject.class)
												.loadById(securityContext,
														  id);
		return outResult;
	}
}
