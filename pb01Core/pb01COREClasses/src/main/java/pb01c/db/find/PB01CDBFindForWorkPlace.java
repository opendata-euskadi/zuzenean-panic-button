package pb01c.db.find;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

import lombok.extern.slf4j.Slf4j;
import pb01a.api.interfaces.PB01AFindServicesForWorkPlace;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AWorkPlaceID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionServiceLocationOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AWorkPlaceOID;
import pb01a.model.org.PB01AWorkPlace;
import pb01c.db.entities.PB01CDBEntityForOrganizationalEntityBase;
import pb01c.db.entities.PB01CDBEntityForWorkPlace;
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
public class PB01CDBFindForWorkPlace
	 extends PB01CDBFindForOrganizationalEntityBase<PB01AWorkPlaceOID,PB01AWorkPlaceID,PB01AWorkPlace,
	 								 			   PB01CDBEntityForWorkPlace>
  implements PB01AFindServicesForWorkPlace {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01CDBFindForWorkPlace(final DBModuleConfig dbCfg,
								  final EntityManager entityManager,
								  final Marshaller marshaller) {
		super(PB01AWorkPlace.class,PB01CDBEntityForWorkPlace.class,
			  dbCfg,
			  entityManager,
			  marshaller);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  EXTENSION METHODS
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public FindResult<PB01AWorkPlace> findByOrgDivisionServiceLocation(final SecurityContext securityContext,
										   		final PB01AOrgDivisionServiceLocationOID locOid) {
		log.debug("> loading work places in location {}",locOid);

		TypedQuery<PB01CDBEntityForWorkPlace> query = this.getEntityManager()
													        .createNamedQuery("PB01DBEntitiesForWorkPlacesByLocation",
															  		          PB01CDBEntityForWorkPlace.class)
															.setParameter("loc",locOid.asString());
		query.setHint(QueryHints.READ_ONLY,HintValues.TRUE);
		Collection<PB01CDBEntityForWorkPlace> entities = query.getResultList();
		FindResult<PB01AWorkPlace> outEntities = FindResultBuilder.using(securityContext)
												          	    .on(_modelObjectType)
												          	    .foundDBEntities(entities)
												          	    .transformedToModelObjectsUsing(this);
		return outEntities;
	}
	@Override
	public FindSummariesResult<PB01AWorkPlace> findSummariesByOrgDivisionServiceLocation(final SecurityContext securityContext,
																  final PB01AOrgDivisionServiceLocationOID locOid,
																  final Language lang) {
		log.info("Find summaries for all work places in a location");
		// [1] - Do the query
		TypedQuery<PB01CDBEntityForOrganizationalEntityBase> qry = this.getEntityManager()
																	  .createNamedQuery("PB01DBEntitiesForWorkPlacesByLocation",
																				 		PB01CDBEntityForOrganizationalEntityBase.class)
																	  .setParameter("loc",locOid.asString());
		Collection<PB01CDBEntityForOrganizationalEntityBase> dbEntities = qry.getResultList();

		// [2] - Transform to summarized model objects
		FindSummariesResult<PB01AWorkPlace> outSummaries = null;
		outSummaries = FindSummariesResultBuilder.using(securityContext)
												 .on(_modelObjectType)
												 .foundDBEntities(dbEntities)
												 .transformedToSummarizedModelObjectUsing(this.dbEntityToSummaryTransformFunction(lang));
		// [3] - Return
		return outSummaries;
	}
}
