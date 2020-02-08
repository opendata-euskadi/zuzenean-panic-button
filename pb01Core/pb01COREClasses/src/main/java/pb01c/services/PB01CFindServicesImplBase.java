package pb01c.services;

import javax.inject.Provider;
import javax.persistence.EntityManager;

import com.google.common.eventbus.EventBus;

import lombok.experimental.Accessors;
import pb01a.api.interfaces.PB01AFindServicesBase;
import pb01a.model.PB01APersistableObject;
import pb01a.model.oids.PB01AIDs.PB01APersistableObjectID;
import pb01a.model.oids.PB01AOIDs.PB01APersistableObjectOID;
import pb01c.services.delegates.persistence.PB01CCRUDServicesDelegateForWorkPlace;
import r01f.bootstrap.services.config.core.ServicesCoreBootstrapConfigWhenBeanExposed;
import r01f.objectstreamer.Marshaller;
import r01f.services.persistence.CoreFindServicesForModelObjectBase;

/**
 * Implements the find-related services which in turn are
 * delegated to {@link PB01CCRUDServicesDelegateForWorkPlace}
 */
@Accessors(prefix="_")
public abstract class PB01CFindServicesImplBase<O extends PB01APersistableObjectOID,ID extends PB01APersistableObjectID<O>,M extends PB01APersistableObject<O,ID>>
     		  extends CoreFindServicesForModelObjectBase<O,M>
  		   implements PB01AFindServicesBase<O,ID,M>,
  		   			  PB01CPanicButtonCoreServiceImpl {
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01CFindServicesImplBase(final ServicesCoreBootstrapConfigWhenBeanExposed coreCfg,
									final Marshaller modelObjectsMarshaller,
									final EventBus eventBus,
									final Provider<EntityManager> entityManagerProvider) {
		super(coreCfg,
			  modelObjectsMarshaller,
			  eventBus,
			  entityManagerProvider);
	}
}
