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
import x47b.api.interfaces.X47BFindServicesForWorkPlace;
import x47b.db.find.X47BDBFindForWorkPlace;
import x47b.model.oids.X47BOrganizationalIDs.X47BWorkPlaceID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceLocationOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BWorkPlaceOID;
import x47b.model.org.X47BWorkPlace;

/**
 * Service layer delegated type for CRUD (Create/Read/Update/Delete) operations
 */
public class X47BFindServicesDelegateForWorkPlace
	 extends X47BFindServicesDelegateForOrganizationalEntityBase<X47BWorkPlaceOID,X47BWorkPlaceID,X47BWorkPlace>
  implements X47BFindServicesForWorkPlace {

/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BFindServicesDelegateForWorkPlace(final ServicesCoreBootstrapConfigWhenBeanExposed coreCfg,
												final EntityManager entityManager,
											    final Marshaller marshaller,
				  			   		   	   	    final EventBus eventBus) {
		super(coreCfg,
			  X47BWorkPlace.class,
			  new X47BDBFindForWorkPlace(DBModuleConfigBuilder.dbModuleConfigFrom(coreCfg),
					  					 entityManager,
					  					 marshaller));
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  EXTENSION METHODS
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public FindResult<X47BWorkPlace> findByOrgDivisionServiceLocation(final SecurityContext securityContext,
												final X47BOrgDivisionServiceLocationOID locOid) {
		if (locOid == null) return FindResultBuilder.using(securityContext)
													.on(_modelObjectType)
													.errorFindingEntities()
															.causedByClientBadRequest("The location id is mandatory to find work places by location");
		return this.getServiceImplAs(X47BFindServicesForWorkPlace.class)
						.findByOrgDivisionServiceLocation(securityContext,
										locOid);
	}
	@Override
	public FindSummariesResult<X47BWorkPlace> findSummariesByOrgDivisionServiceLocation(final SecurityContext securityContext,
																  final X47BOrgDivisionServiceLocationOID locOid,
																  final Language lang) {
		if (locOid == null) return FindSummariesResultBuilder.using(securityContext)
															 .on(_modelObjectType)
															 .errorFindingSummaries()
																		.causedByClientBadRequest("The location id is mandatory to find work places by location");
		Language theLang = (lang != null && lang.in(Language.SPANISH,Language.BASQUE)) ? lang 
																					   : Language.SPANISH;
		return this.getServiceImplAs(X47BFindServicesForWorkPlace.class)
						.findSummariesByOrgDivisionServiceLocation(securityContext,
										         locOid,theLang);
	}

}
