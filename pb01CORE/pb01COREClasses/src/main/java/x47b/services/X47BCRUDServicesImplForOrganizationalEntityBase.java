package x47b.services;

import javax.inject.Provider;
import javax.persistence.EntityManager;

import com.google.common.eventbus.EventBus;

import lombok.experimental.Accessors;
import r01f.bootstrap.services.config.core.ServicesCoreBootstrapConfigWhenBeanExposed;
import r01f.objectstreamer.Marshaller;
import x47b.api.interfaces.X47BCRUDServicesForOrganizationalModelObjectBase;
import x47b.model.X47BPersistableObject;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgObjectID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgObjectOID;

@Accessors(prefix="_")
public abstract class X47BCRUDServicesImplForOrganizationalEntityBase<O extends X47BOrgObjectOID,ID extends X47BOrgObjectID<O>,M extends X47BPersistableObject<O,ID>>
       		  extends X47BCRUDServicesImplBase<O,ID,M>
    	   implements X47BCRUDServicesForOrganizationalModelObjectBase<O,ID,M> {
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BCRUDServicesImplForOrganizationalEntityBase(final ServicesCoreBootstrapConfigWhenBeanExposed coreCfg,
														   final Marshaller modelObjectsMarshaller,
														   final EventBus eventBus,
														   final Provider<EntityManager> entityManagerProvider) {
		super(coreCfg,
			  modelObjectsMarshaller,
			  eventBus,
			  entityManagerProvider);
	}
}
