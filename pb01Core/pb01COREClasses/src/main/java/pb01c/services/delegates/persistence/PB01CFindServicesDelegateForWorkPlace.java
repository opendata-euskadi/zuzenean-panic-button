package pb01c.services.delegates.persistence;

import javax.persistence.EntityManager;

import com.google.common.eventbus.EventBus;

import pb01a.api.interfaces.PB01AFindServicesForWorkPlace;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AWorkPlaceID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionServiceLocationOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AWorkPlaceOID;
import pb01a.model.org.PB01AWorkPlace;
import pb01c.db.find.PB01CDBFindForWorkPlace;
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
public class PB01CFindServicesDelegateForWorkPlace
	 extends PB01CFindServicesDelegateForOrganizationalEntityBase<PB01AWorkPlaceOID,PB01AWorkPlaceID,PB01AWorkPlace>
  implements PB01AFindServicesForWorkPlace {

/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01CFindServicesDelegateForWorkPlace(final ServicesCoreBootstrapConfigWhenBeanExposed coreCfg,
												final EntityManager entityManager,
											    final Marshaller marshaller,
				  			   		   	   	    final EventBus eventBus) {
		super(coreCfg,
			  PB01AWorkPlace.class,
			  new PB01CDBFindForWorkPlace(DBModuleConfigBuilder.dbModuleConfigFrom(coreCfg),
					  					 entityManager,
					  					 marshaller));
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  EXTENSION METHODS
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public FindResult<PB01AWorkPlace> findByOrgDivisionServiceLocation(final SecurityContext securityContext,
												final PB01AOrgDivisionServiceLocationOID locOid) {
		if (locOid == null) return FindResultBuilder.using(securityContext)
													.on(_modelObjectType)
													.errorFindingEntities()
															.causedByClientBadRequest("The location id is mandatory to find work places by location");
		return this.getServiceImplAs(PB01AFindServicesForWorkPlace.class)
						.findByOrgDivisionServiceLocation(securityContext,
										locOid);
	}
	@Override
	public FindSummariesResult<PB01AWorkPlace> findSummariesByOrgDivisionServiceLocation(final SecurityContext securityContext,
																  final PB01AOrgDivisionServiceLocationOID locOid,
																  final Language lang) {
		if (locOid == null) return FindSummariesResultBuilder.using(securityContext)
															 .on(_modelObjectType)
															 .errorFindingSummaries()
																		.causedByClientBadRequest("The location id is mandatory to find work places by location");
		Language theLang = (lang != null && lang.in(Language.SPANISH,Language.BASQUE)) ? lang 
																					   : Language.SPANISH;
		return this.getServiceImplAs(PB01AFindServicesForWorkPlace.class)
						.findSummariesByOrgDivisionServiceLocation(securityContext,
										         locOid,theLang);
	}

}
