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
import x47b.api.interfaces.X47BFindServicesForOrgDivisionServiceLocation;
import x47b.db.find.X47BDBFindForOrgDivisionServiceLocation;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionServiceLocationID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceLocationOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceOID;
import x47b.model.org.X47BOrgDivisionServiceLocation;

/**
 * Service layer delegated type for CRUD (Create/Read/Update/Delete) operations
 */
public class X47BFindServicesDelegateForOrgDivisionServiceLocation
	 extends X47BFindServicesDelegateForOrganizationalEntityBase<X47BOrgDivisionServiceLocationOID,X47BOrgDivisionServiceLocationID,X47BOrgDivisionServiceLocation>
  implements X47BFindServicesForOrgDivisionServiceLocation {

/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BFindServicesDelegateForOrgDivisionServiceLocation(final ServicesCoreBootstrapConfigWhenBeanExposed coreCfg,
																 final EntityManager entityManager,
															     final Marshaller marshaller,
								  			   		   	   	     final EventBus eventBus) {
		super(coreCfg,
			  X47BOrgDivisionServiceLocation.class,
			  new X47BDBFindForOrgDivisionServiceLocation(DBModuleConfigBuilder.dbModuleConfigFrom(coreCfg),
					  									  entityManager,
					  									  marshaller));
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  EXTENSION METHODS
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public FindResult<X47BOrgDivisionServiceLocation> findByOrgDivisionService(final SecurityContext securityContext,
															  				   final X47BOrgDivisionServiceOID serviceOid) {
		if (serviceOid == null) return FindResultBuilder.using(securityContext)
															.on(_modelObjectType)
															.errorFindingEntities()
																	.causedByClientBadRequest("The service id is mandatory to find locations by service");
		return this.getServiceImplAs(X47BFindServicesForOrgDivisionServiceLocation.class)
						.findByOrgDivisionService(securityContext,
										   		  serviceOid);
	}
	@Override
	public FindSummariesResult<X47BOrgDivisionServiceLocation> findSummariesByOrgDivisionService(final SecurityContext securityContext,
																  				  		 		 final X47BOrgDivisionServiceOID serviceOid,
																  				  		 		 final Language lang) {
		if (serviceOid == null) return FindSummariesResultBuilder.using(securityContext)
																	 .on(_modelObjectType)
																	 .errorFindingSummaries()
																				.causedByClientBadRequest("The service id is mandatory to find locations by service");
		Language theLang = (lang != null && lang.in(Language.SPANISH,Language.BASQUE)) ? lang 
																					   : Language.SPANISH;
		return this.getServiceImplAs(X47BFindServicesForOrgDivisionServiceLocation.class)
						.findSummariesByOrgDivisionService(securityContext,
										         		   serviceOid,theLang);
	}

}
