package pb01c.services.delegates.persistence;

import javax.persistence.EntityManager;

import com.google.common.eventbus.EventBus;

import pb01a.api.interfaces.PB01AFindServicesForOrganization;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrganizationID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrganizationOID;
import pb01a.model.org.PB01AOrganization;
import pb01c.db.find.PB01CDBFindForOrganization;
import r01f.bootstrap.services.config.core.ServicesCoreBootstrapConfigWhenBeanExposed;
import r01f.locale.Language;
import r01f.model.persistence.FindSummariesResult;
import r01f.objectstreamer.Marshaller;
import r01f.persistence.db.config.DBModuleConfigBuilder;
import r01f.securitycontext.SecurityContext;

/**
 * Service layer delegated type for CRUD (Create/Read/Update/Delete) operations
 */
public class PB01CFindServicesDelegateForOrganization
	 extends PB01CFindServicesDelegateForOrganizationalEntityBase<PB01AOrganizationOID,PB01AOrganizationID,PB01AOrganization>
  implements PB01AFindServicesForOrganization {

/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01CFindServicesDelegateForOrganization(final ServicesCoreBootstrapConfigWhenBeanExposed coreCfg,
												   final EntityManager entityManager,
												   final Marshaller marshaller,
					  			   		   	   	   final EventBus eventBus) {
		super(coreCfg,
			  PB01AOrganization.class,
			  new PB01CDBFindForOrganization(DBModuleConfigBuilder.dbModuleConfigFrom(coreCfg),
					  						entityManager,
					  						marshaller));
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  EXTENSION METHODS
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public FindSummariesResult<PB01AOrganization> findSummaries(final SecurityContext securityContext,
															   final Language lang) {
		// params checking
		Language theLang = (lang != null && lang.in(Language.SPANISH,Language.BASQUE)) ? lang 
																					   : Language.SPANISH;
		// simply delegate
		return this.getServiceImplAs(PB01AFindServicesForOrganization.class)
				   .findSummaries(securityContext,
						   		  theLang);
	}
}
