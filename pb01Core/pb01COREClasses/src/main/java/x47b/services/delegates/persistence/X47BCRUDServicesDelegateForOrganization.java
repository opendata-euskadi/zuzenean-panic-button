package x47b.services.delegates.persistence;

import javax.persistence.EntityManager;

import com.google.common.eventbus.EventBus;

import r01f.bootstrap.services.config.core.ServicesCoreBootstrapConfigWhenBeanExposed;
import r01f.model.persistence.PersistenceRequestedOperation;
import r01f.objectstreamer.Marshaller;
import r01f.persistence.db.config.DBModuleConfigBuilder;
import r01f.securitycontext.SecurityContext;
import r01f.validation.ObjectValidationResult;
import x47b.api.interfaces.X47BCRUDServicesForOrganization;
import x47b.db.crud.X47BDBCRUDForOrganization;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrganizationID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrganizationOID;
import x47b.model.org.X47BOrganization;

/**
 * Service layer delegated type for CRUD (Create/Read/Update/Delete) operations
 */
public class X47BCRUDServicesDelegateForOrganization
	 extends X47BCRUDServicesDelegateForOrganizationalEntityBase<X47BOrganizationOID,X47BOrganizationID,X47BOrganization>
  implements X47BCRUDServicesForOrganization {

/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BCRUDServicesDelegateForOrganization(final ServicesCoreBootstrapConfigWhenBeanExposed coreCfg,
												   final EntityManager entityManager,
											       final Marshaller marshaller,
				  			   		   	   	       final EventBus eventBus) {
		super(coreCfg,
			  X47BOrganization.class,
			  new X47BDBCRUDForOrganization(DBModuleConfigBuilder.dbModuleConfigFrom(coreCfg),
					  						entityManager,
					  						marshaller),
			  eventBus);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  PARAMS VALIDATION ON CREATION / UPDATE
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public ObjectValidationResult<X47BOrganization> validateModelObjBeforeCreateOrUpdate(final SecurityContext securityContext,
																	 	  				 final PersistenceRequestedOperation requestedOp,
																	 	  				 final X47BOrganization org) {
		return super.validateModelObjBeforeCreateOrUpdate(securityContext,
														  requestedOp,
														  org);
	}
}
