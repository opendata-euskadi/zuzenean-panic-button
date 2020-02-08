package pb01c.services;

import javax.inject.Provider;
import javax.persistence.EntityManager;

import com.google.common.eventbus.EventBus;

import lombok.experimental.Accessors;
import pb01a.api.interfaces.PB01ACRUDServicesForOrganizationalModelObjectBase;
import pb01a.model.PB01APersistableObject;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgObjectID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgObjectOID;
import r01f.bootstrap.services.config.core.ServicesCoreBootstrapConfigWhenBeanExposed;
import r01f.objectstreamer.Marshaller;

@Accessors(prefix="_")
public abstract class PB01CCRUDServicesImplForOrganizationalEntityBase<O extends PB01AOrgObjectOID,ID extends PB01AOrgObjectID<O>,M extends PB01APersistableObject<O,ID>>
       		  extends PB01CCRUDServicesImplBase<O,ID,M>
    	   implements PB01ACRUDServicesForOrganizationalModelObjectBase<O,ID,M> {
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01CCRUDServicesImplForOrganizationalEntityBase(final ServicesCoreBootstrapConfigWhenBeanExposed coreCfg,
														   final Marshaller modelObjectsMarshaller,
														   final EventBus eventBus,
														   final Provider<EntityManager> entityManagerProvider) {
		super(coreCfg,
			  modelObjectsMarshaller,
			  eventBus,
			  entityManagerProvider);
	}
}
