package pb01c.services.delegates.persistence;

import javax.persistence.EntityManager;

import com.google.common.eventbus.EventBus;

import pb01a.api.interfaces.PB01AFindServicesForOrgDivisionService;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionServiceID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionServiceOID;
import pb01a.model.org.PB01AOrgDivisionService;
import pb01c.db.find.PB01CDBFindForOrgDivisionService;
import r01f.bootstrap.services.config.core.ServicesCoreBootstrapConfigWhenBeanExposed;
import r01f.locale.Language;
import r01f.model.persistence.FindResult;
import r01f.model.persistence.FindResultBuilder;
import r01f.model.persistence.FindSummariesResult;
import r01f.model.persistence.FindSummariesResultBuilder;
import r01f.objectstreamer.Marshaller;
import r01f.persistence.db.config.DBModuleConfigBuilder;
import r01f.securitycontext.SecurityContext;

/**
 * Service layer delegated type for CRUD (Create/Read/Update/Delete) operations
 */
public class PB01CFindServicesDelegateForOrgDivisionService
	 extends PB01CFindServicesDelegateForOrganizationalEntityBase<PB01AOrgDivisionServiceOID,PB01AOrgDivisionServiceID,PB01AOrgDivisionService>
  implements PB01AFindServicesForOrgDivisionService {

/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01CFindServicesDelegateForOrgDivisionService(final ServicesCoreBootstrapConfigWhenBeanExposed coreCfg,
														 final EntityManager entityManager,
													     final Marshaller marshaller,
						  			   		   	   	     final EventBus eventBus) {
		super(coreCfg,
			  PB01AOrgDivisionService.class,
			  new PB01CDBFindForOrgDivisionService(DBModuleConfigBuilder.dbModuleConfigFrom(coreCfg),
					  							  entityManager,
					  							  marshaller));
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  EXTENSION METHODS
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public FindResult<PB01AOrgDivisionService> findByOrgDivision(final SecurityContext securityContext,
															  	final PB01AOrgDivisionOID divisionOid) {
		if (divisionOid == null) return FindResultBuilder.using(securityContext)
															.on(_modelObjectType)
															.errorFindingEntities()
																	.causedByClientBadRequest("The division id is mandatory to find services by division");
		return this.getServiceImplAs(PB01AFindServicesForOrgDivisionService.class)
						.findByOrgDivision(securityContext,
										   divisionOid);
	}
	@Override
	public FindSummariesResult<PB01AOrgDivisionService> findSummariesByOrgDivision(final SecurityContext securityContext,
																  				  final PB01AOrgDivisionOID divisionOid,
																  				  final Language lang) {
		if (divisionOid == null) return FindSummariesResultBuilder.using(securityContext)
																	 .on(_modelObjectType)
																	 .errorFindingSummaries()
																				.causedByClientBadRequest("The division id is mandatory to find services by division");
		Language theLang = (lang != null && lang.in(Language.SPANISH,Language.BASQUE)) ? lang 
																					   : Language.SPANISH;
		return this.getServiceImplAs(PB01AFindServicesForOrgDivisionService.class)
						.findSummariesByOrgDivision(securityContext,
										         	divisionOid,theLang);
	}

}
