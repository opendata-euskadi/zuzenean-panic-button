package x47b.services.delegates.persistence;

import javax.persistence.EntityManager;

import com.google.common.eventbus.EventBus;

import r01f.bootstrap.services.config.core.ServicesCoreBootstrapConfigWhenBeanExposed;
import r01f.locale.Language;
import r01f.model.persistence.FindSummariesResult;
import r01f.objectstreamer.Marshaller;
import r01f.persistence.db.config.DBModuleConfigBuilder;
import r01f.securitycontext.SecurityContext;
import x47b.api.interfaces.X47BFindServicesForOrganization;
import x47b.db.find.X47BDBFindForOrganization;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrganizationID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrganizationOID;
import x47b.model.org.X47BOrganization;

/**
 * Service layer delegated type for CRUD (Create/Read/Update/Delete) operations
 */
public class X47BFindServicesDelegateForOrganization
	 extends X47BFindServicesDelegateForOrganizationalEntityBase<X47BOrganizationOID,X47BOrganizationID,X47BOrganization>
  implements X47BFindServicesForOrganization {

/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BFindServicesDelegateForOrganization(final ServicesCoreBootstrapConfigWhenBeanExposed coreCfg,
												   final EntityManager entityManager,
												   final Marshaller marshaller,
					  			   		   	   	   final EventBus eventBus) {
		super(coreCfg,
			  X47BOrganization.class,
			  new X47BDBFindForOrganization(DBModuleConfigBuilder.dbModuleConfigFrom(coreCfg),
					  						entityManager,
					  						marshaller));
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  EXTENSION METHODS
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public FindSummariesResult<X47BOrganization> findSummaries(final SecurityContext securityContext,
															   final Language lang) {
		// params checking
		Language theLang = (lang != null && lang.in(Language.SPANISH,Language.BASQUE)) ? lang 
																					   : Language.SPANISH;
		// simply delegate
		return this.getServiceImplAs(X47BFindServicesForOrganization.class)
				   .findSummaries(securityContext,
						   		  theLang);
	}
}
