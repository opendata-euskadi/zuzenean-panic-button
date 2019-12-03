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
import x47b.api.interfaces.X47BFindServicesForOrgDivisionService;
import x47b.db.find.X47BDBFindForOrgDivisionService;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionServiceID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceOID;
import x47b.model.org.X47BOrgDivisionService;

/**
 * Service layer delegated type for CRUD (Create/Read/Update/Delete) operations
 */
public class X47BFindServicesDelegateForOrgDivisionService
	 extends X47BFindServicesDelegateForOrganizationalEntityBase<X47BOrgDivisionServiceOID,X47BOrgDivisionServiceID,X47BOrgDivisionService>
  implements X47BFindServicesForOrgDivisionService {

/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BFindServicesDelegateForOrgDivisionService(final ServicesCoreBootstrapConfigWhenBeanExposed coreCfg,
														 final EntityManager entityManager,
													     final Marshaller marshaller,
						  			   		   	   	     final EventBus eventBus) {
		super(coreCfg,
			  X47BOrgDivisionService.class,
			  new X47BDBFindForOrgDivisionService(DBModuleConfigBuilder.dbModuleConfigFrom(coreCfg),
					  							  entityManager,
					  							  marshaller));
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  EXTENSION METHODS
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public FindResult<X47BOrgDivisionService> findByOrgDivision(final SecurityContext securityContext,
															  	final X47BOrgDivisionOID divisionOid) {
		if (divisionOid == null) return FindResultBuilder.using(securityContext)
															.on(_modelObjectType)
															.errorFindingEntities()
																	.causedByClientBadRequest("The division id is mandatory to find services by division");
		return this.getServiceImplAs(X47BFindServicesForOrgDivisionService.class)
						.findByOrgDivision(securityContext,
										   divisionOid);
	}
	@Override
	public FindSummariesResult<X47BOrgDivisionService> findSummariesByOrgDivision(final SecurityContext securityContext,
																  				  final X47BOrgDivisionOID divisionOid,
																  				  final Language lang) {
		if (divisionOid == null) return FindSummariesResultBuilder.using(securityContext)
																	 .on(_modelObjectType)
																	 .errorFindingSummaries()
																				.causedByClientBadRequest("The division id is mandatory to find services by division");
		Language theLang = (lang != null && lang.in(Language.SPANISH,Language.BASQUE)) ? lang 
																					   : Language.SPANISH;
		return this.getServiceImplAs(X47BFindServicesForOrgDivisionService.class)
						.findSummariesByOrgDivision(securityContext,
										         	divisionOid,theLang);
	}

}
