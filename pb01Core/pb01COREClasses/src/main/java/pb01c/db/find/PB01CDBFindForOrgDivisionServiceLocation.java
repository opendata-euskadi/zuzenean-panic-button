package pb01c.db.find;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

import lombok.extern.slf4j.Slf4j;
import pb01a.api.interfaces.PB01AFindServicesForOrgDivisionServiceLocation;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionServiceLocationID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionServiceLocationOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionServiceOID;
import pb01a.model.org.PB01AOrgDivisionServiceLocation;
import pb01c.db.entities.PB01CDBEntityForOrgDivisionServiceLocation;
import pb01c.db.entities.PB01CDBEntityForOrganizationalEntityBase;
import r01f.locale.Language;
import r01f.model.persistence.FindResult;
import r01f.model.persistence.FindResultBuilder;
import r01f.model.persistence.FindSummariesResult;
import r01f.model.persistence.FindSummariesResultBuilder;
import r01f.objectstreamer.Marshaller;
import r01f.persistence.db.config.DBModuleConfig;
import r01f.securitycontext.SecurityContext;

/**
 * Persistence layer
 */
@Slf4j
public class PB01CDBFindForOrgDivisionServiceLocation
	 extends PB01CDBFindForOrganizationalEntityBase<PB01AOrgDivisionServiceLocationOID,PB01AOrgDivisionServiceLocationID,PB01AOrgDivisionServiceLocation,
	 								 PB01CDBEntityForOrgDivisionServiceLocation>
  implements PB01AFindServicesForOrgDivisionServiceLocation {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01CDBFindForOrgDivisionServiceLocation(final DBModuleConfig dbCfg,
												   final EntityManager entityManager,
												   final Marshaller marshaller) {
		super(PB01AOrgDivisionServiceLocation.class,PB01CDBEntityForOrgDivisionServiceLocation.class,
			  dbCfg,
			  entityManager,
			  marshaller);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  EXTENSION METHODS
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public FindResult<PB01AOrgDivisionServiceLocation> findByOrgDivisionService(final SecurityContext securityContext,
										   					    			   final PB01AOrgDivisionServiceOID serviceOid) {
		log.debug("> loading locations in service {}",serviceOid);

		TypedQuery<PB01CDBEntityForOrgDivisionServiceLocation> query = this.getEntityManager()
																	        .createNamedQuery("PB01DBEntitiesForLocationsByService",
																			  		          PB01CDBEntityForOrgDivisionServiceLocation.class)
																			.setParameter("service",serviceOid.asString());
		query.setHint(QueryHints.READ_ONLY,HintValues.TRUE);
		Collection<PB01CDBEntityForOrgDivisionServiceLocation> entities = query.getResultList();
		FindResult<PB01AOrgDivisionServiceLocation> outEntities = FindResultBuilder.using(securityContext)
																	          	  .on(_modelObjectType)
																	          	  .foundDBEntities(entities)
																	          	  .transformedToModelObjectsUsing(this);
		return outEntities;
	}
	@Override
	public FindSummariesResult<PB01AOrgDivisionServiceLocation> findSummariesByOrgDivisionService(final SecurityContext securityContext,
																  								 final PB01AOrgDivisionServiceOID serviceOid,
																  								 final Language lang) {
		log.info("Find summaries for all locations in service {}",serviceOid);
		// [1] - Do the query
		TypedQuery<PB01CDBEntityForOrganizationalEntityBase> qry = this.getEntityManager()
																	  .createNamedQuery("PB01DBEntitiesForLocationsByService",
																				 		PB01CDBEntityForOrganizationalEntityBase.class)
																	  .setParameter("service",serviceOid.asString());
		qry.setHint(QueryHints.READ_ONLY,HintValues.TRUE);
		Collection<PB01CDBEntityForOrganizationalEntityBase> dbEntities = qry.getResultList();

		// [2] - Transform to summarized model objects
		FindSummariesResult<PB01AOrgDivisionServiceLocation> outSummaries = null;
		outSummaries = FindSummariesResultBuilder.using(securityContext)
												 .on(_modelObjectType)
												 .foundDBEntities(dbEntities)
												 .transformedToSummarizedModelObjectUsing(this.dbEntityToSummaryTransformFunction(lang));
		// [3] - Return
		return outSummaries;
	}
}
