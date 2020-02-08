package pb01c.services.delegates.persistence;

import javax.persistence.EntityManager;

import com.google.common.eventbus.EventBus;

import pb01a.api.interfaces.PB01AFindServicesForOrgDivision;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrganizationOID;
import pb01a.model.org.PB01AOrgDivision;
import pb01c.db.find.PB01CDBFindForOrgDivision;
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
public class PB01CFindServicesDelegateForOrgDivision
	 extends PB01CFindServicesDelegateForOrganizationalEntityBase<PB01AOrgDivisionOID,PB01AOrgDivisionID,PB01AOrgDivision>
  implements PB01AFindServicesForOrgDivision {

/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01CFindServicesDelegateForOrgDivision(final ServicesCoreBootstrapConfigWhenBeanExposed coreCfg,
												  final EntityManager entityManager,
												  final Marshaller marshaller,
					  			   		   	   	  final EventBus eventBus) {
		super(coreCfg,
			  PB01AOrgDivision.class,
			  new PB01CDBFindForOrgDivision(DBModuleConfigBuilder.dbModuleConfigFrom(coreCfg),
					  					   entityManager,
					  					   marshaller));
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  EXTENSION METHODS
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public FindResult<PB01AOrgDivision> findByOrganization(final SecurityContext securityContext,
													   	  final PB01AOrganizationOID orgOid) {
		if (orgOid == null) return FindResultBuilder.using(securityContext)
													.on(_modelObjectType)
													.errorFindingEntities()
															.causedByClientBadRequest("The organization id is mandatory to find OrgDivisions by organization");
		return this.getServiceImplAs(PB01AFindServicesForOrgDivision.class)
						.findByOrganization(securityContext,
											orgOid);
	}
	@Override
	public FindSummariesResult<PB01AOrgDivision> findSummariesByOrganization(final SecurityContext securityContext,
																		 	final PB01AOrganizationOID orgOid,
																		 	final Language lang) {
		if (orgOid == null) return FindSummariesResultBuilder.using(securityContext)
															 .on(_modelObjectType)
															 .errorFindingSummaries()
																	.causedByClientBadRequest("The organization id is mandatory to find OrgDivisions by organization");
		Language theLang = (lang != null && lang.in(Language.SPANISH,Language.BASQUE)) ? lang 
																					   : Language.SPANISH;
		return this.getServiceImplAs(PB01AFindServicesForOrgDivision.class)
					.findSummariesByOrganization(securityContext,
												 orgOid,theLang);
	}

}
