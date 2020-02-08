package pb01c.services.delegates.persistence;

import javax.persistence.EntityManager;

import com.google.common.eventbus.EventBus;

import pb01a.api.interfaces.PB01AFindServicesForOrgDivisionServiceLocation;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionServiceLocationID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionServiceLocationOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionServiceOID;
import pb01a.model.org.PB01AOrgDivisionServiceLocation;
import pb01c.db.find.PB01CDBFindForOrgDivisionServiceLocation;
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
public class PB01CFindServicesDelegateForOrgDivisionServiceLocation
	 extends PB01CFindServicesDelegateForOrganizationalEntityBase<PB01AOrgDivisionServiceLocationOID,PB01AOrgDivisionServiceLocationID,PB01AOrgDivisionServiceLocation>
  implements PB01AFindServicesForOrgDivisionServiceLocation {

/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01CFindServicesDelegateForOrgDivisionServiceLocation(final ServicesCoreBootstrapConfigWhenBeanExposed coreCfg,
																 final EntityManager entityManager,
															     final Marshaller marshaller,
								  			   		   	   	     final EventBus eventBus) {
		super(coreCfg,
			  PB01AOrgDivisionServiceLocation.class,
			  new PB01CDBFindForOrgDivisionServiceLocation(DBModuleConfigBuilder.dbModuleConfigFrom(coreCfg),
					  									  entityManager,
					  									  marshaller));
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  EXTENSION METHODS
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public FindResult<PB01AOrgDivisionServiceLocation> findByOrgDivisionService(final SecurityContext securityContext,
															  				   final PB01AOrgDivisionServiceOID serviceOid) {
		if (serviceOid == null) return FindResultBuilder.using(securityContext)
															.on(_modelObjectType)
															.errorFindingEntities()
																	.causedByClientBadRequest("The service id is mandatory to find locations by service");
		return this.getServiceImplAs(PB01AFindServicesForOrgDivisionServiceLocation.class)
						.findByOrgDivisionService(securityContext,
										   		  serviceOid);
	}
	@Override
	public FindSummariesResult<PB01AOrgDivisionServiceLocation> findSummariesByOrgDivisionService(final SecurityContext securityContext,
																  				  		 		 final PB01AOrgDivisionServiceOID serviceOid,
																  				  		 		 final Language lang) {
		if (serviceOid == null) return FindSummariesResultBuilder.using(securityContext)
																	 .on(_modelObjectType)
																	 .errorFindingSummaries()
																				.causedByClientBadRequest("The service id is mandatory to find locations by service");
		Language theLang = (lang != null && lang.in(Language.SPANISH,Language.BASQUE)) ? lang 
																					   : Language.SPANISH;
		return this.getServiceImplAs(PB01AFindServicesForOrgDivisionServiceLocation.class)
						.findSummariesByOrgDivisionService(securityContext,
										         		   serviceOid,theLang);
	}

}
