package x47b.services;

import javax.inject.Provider;
import javax.persistence.EntityManager;

import com.google.common.eventbus.EventBus;

import lombok.experimental.Accessors;
import r01f.bootstrap.services.config.core.ServicesCoreBootstrapConfigWhenBeanExposed;
import r01f.objectstreamer.Marshaller;
import r01f.services.persistence.CoreFindServicesForModelObjectBase;
import x47b.api.interfaces.X47BFindServicesBase;
import x47b.model.X47BPersistableObject;
import x47b.model.oids.X47BIDs.X47BPersistableObjectID;
import x47b.model.oids.X47BOIDs.X47BPersistableObjectOID;
import x47b.services.delegates.persistence.X47BCRUDServicesDelegateForWorkPlace;

/**
 * Implements the find-related services which in turn are
 * delegated to {@link X47BCRUDServicesDelegateForWorkPlace}
 */
@Accessors(prefix="_")
public abstract class X47BFindServicesImplBase<O extends X47BPersistableObjectOID,ID extends X47BPersistableObjectID<O>,M extends X47BPersistableObject<O,ID>>
     		  extends CoreFindServicesForModelObjectBase<O,M>
  		   implements X47BFindServicesBase<O,ID,M>,
  		   			  X47BPanicButtonCoreServiceImpl {
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BFindServicesImplBase(final ServicesCoreBootstrapConfigWhenBeanExposed coreCfg,
									final Marshaller modelObjectsMarshaller,
									final EventBus eventBus,
									final Provider<EntityManager> entityManagerProvider) {
		super(coreCfg,
			  modelObjectsMarshaller,
			  eventBus,
			  entityManagerProvider);
	}
}
