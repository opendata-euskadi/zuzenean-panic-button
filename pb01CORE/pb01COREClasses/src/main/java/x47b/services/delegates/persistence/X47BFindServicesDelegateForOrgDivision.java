package x47b.services.delegates.persistence;

import javax.persistence.EntityManager;

import com.google.common.eventbus.EventBus;

import r01f.bootstrap.services.config.core.ServicesCoreBootstrapConfigWhenBeanExposed;
import r01f.locale.Language;
import r01f.model.persistence.FindResult;
import r01f.model.persistence.FindResultBuilder;
import r01f.model.persistence.FindSummariesResult;
import r01f.model.persistence.FindSummariesResultBuilder;
import r01f.objectstreamer.Marshaller;
import r01f.persistence.db.config.DBModuleConfigBuilder;
import r01f.securitycontext.SecurityContext;
import x47b.api.interfaces.X47BFindServicesForOrgDivision;
import x47b.db.find.X47BDBFindForOrgDivision;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrganizationOID;
import x47b.model.org.X47BOrgDivision;

/**
 * Service layer delegated type for CRUD (Create/Read/Update/Delete) operations
 */
public class X47BFindServicesDelegateForOrgDivision
	 extends X47BFindServicesDelegateForOrganizationalEntityBase<X47BOrgDivisionOID,X47BOrgDivisionID,X47BOrgDivision>
  implements X47BFindServicesForOrgDivision {

/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BFindServicesDelegateForOrgDivision(final ServicesCoreBootstrapConfigWhenBeanExposed coreCfg,
												  final EntityManager entityManager,
												  final Marshaller marshaller,
					  			   		   	   	  final EventBus eventBus) {
		super(coreCfg,
			  X47BOrgDivision.class,
			  new X47BDBFindForOrgDivision(DBModuleConfigBuilder.dbModuleConfigFrom(coreCfg),
					  					   entityManager,
					  					   marshaller));
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  EXTENSION METHODS
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public FindResult<X47BOrgDivision> findByOrganization(final SecurityContext securityContext,
													   	  final X47BOrganizationOID orgOid) {
		if (orgOid == null) return FindResultBuilder.using(securityContext)
													.on(_modelObjectType)
													.errorFindingEntities()
															.causedByClientBadRequest("The organization id is mandatory to find OrgDivisions by organization");
		return this.getServiceImplAs(X47BFindServicesForOrgDivision.class)
						.findByOrganization(securityContext,
											orgOid);
	}
	@Override
	public FindSummariesResult<X47BOrgDivision> findSummariesByOrganization(final SecurityContext securityContext,
																		 	final X47BOrganizationOID orgOid,
																		 	final Language lang) {
		if (orgOid == null) return FindSummariesResultBuilder.using(securityContext)
															 .on(_modelObjectType)
															 .errorFindingSummaries()
																	.causedByClientBadRequest("The organization id is mandatory to find OrgDivisions by organization");
		Language theLang = (lang != null && lang.in(Language.SPANISH,Language.BASQUE)) ? lang 
																					   : Language.SPANISH;
		return this.getServiceImplAs(X47BFindServicesForOrgDivision.class)
					.findSummariesByOrganization(securityContext,
												 orgOid,theLang);
	}

}
