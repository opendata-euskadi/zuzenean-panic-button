package pb01c.services.delegates.persistence;

import javax.persistence.EntityManager;

import com.google.common.eventbus.EventBus;

import pb01a.api.interfaces.PB01ACRUDServicesForOrganization;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrganizationID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrganizationOID;
import pb01a.model.org.PB01AOrganization;
import pb01c.db.crud.PB01CDBCRUDForOrganization;
import r01f.bootstrap.services.config.core.ServicesCoreBootstrapConfigWhenBeanExposed;
import r01f.model.persistence.PersistenceRequestedOperation;
import r01f.objectstreamer.Marshaller;
import r01f.persistence.db.config.DBModuleConfigBuilder;
import r01f.securitycontext.SecurityContext;
import r01f.validation.ObjectValidationResult;

/**
 * Service layer delegated type for CRUD (Create/Read/Update/Delete) operations
 */
public class PB01CCRUDServicesDelegateForOrganization
	 extends PB01CCRUDServicesDelegateForOrganizationalEntityBase<PB01AOrganizationOID,PB01AOrganizationID,PB01AOrganization>
  implements PB01ACRUDServicesForOrganization {

/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01CCRUDServicesDelegateForOrganization(final ServicesCoreBootstrapConfigWhenBeanExposed coreCfg,
												   final EntityManager entityManager,
											       final Marshaller marshaller,
				  			   		   	   	       final EventBus eventBus) {
		super(coreCfg,
			  PB01AOrganization.class,
			  new PB01CDBCRUDForOrganization(DBModuleConfigBuilder.dbModuleConfigFrom(coreCfg),
					  						entityManager,
					  						marshaller),
			  eventBus);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  PARAMS VALIDATION ON CREATION / UPDATE
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public ObjectValidationResult<PB01AOrganization> validateModelObjBeforeCreateOrUpdate(final SecurityContext securityContext,
																	 	  				 final PersistenceRequestedOperation requestedOp,
																	 	  				 final PB01AOrganization org) {
		return super.validateModelObjBeforeCreateOrUpdate(securityContext,
														  requestedOp,
														  org);
	}
}
