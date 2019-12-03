package x47b.db.find;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

import lombok.extern.slf4j.Slf4j;
import r01f.locale.Language;
import r01f.model.persistence.FindResult;
import r01f.model.persistence.FindResultBuilder;
import r01f.model.persistence.FindSummariesResult;
import r01f.model.persistence.FindSummariesResultBuilder;
import r01f.objectstreamer.Marshaller;
import r01f.persistence.db.config.DBModuleConfig;
import r01f.securitycontext.SecurityContext;
import x47b.api.interfaces.X47BFindServicesForWorkPlace;
import x47b.db.entities.X47BDBEntityForOrganizationalEntityBase;
import x47b.db.entities.X47BDBEntityForWorkPlace;
import x47b.model.oids.X47BOrganizationalIDs.X47BWorkPlaceID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceLocationOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BWorkPlaceOID;
import x47b.model.org.X47BWorkPlace;

/**
 * Persistence layer
 */
@Slf4j
public class X47BDBFindForWorkPlace
	 extends X47BDBFindForOrganizationalEntityBase<X47BWorkPlaceOID,X47BWorkPlaceID,X47BWorkPlace,
	 								 			   X47BDBEntityForWorkPlace>
  implements X47BFindServicesForWorkPlace {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BDBFindForWorkPlace(final DBModuleConfig dbCfg,
								  final EntityManager entityManager,
								  final Marshaller marshaller) {
		super(X47BWorkPlace.class,X47BDBEntityForWorkPlace.class,
			  dbCfg,
			  entityManager,
			  marshaller);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  EXTENSION METHODS
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public FindResult<X47BWorkPlace> findByOrgDivisionServiceLocation(final SecurityContext securityContext,
										   		final X47BOrgDivisionServiceLocationOID locOid) {
		log.debug("> loading work places in location {}",locOid);

		TypedQuery<X47BDBEntityForWorkPlace> query = this.getEntityManager()
													        .createNamedQuery("X47BDBEntitiesForWorkPlacesByLocation",
															  		          X47BDBEntityForWorkPlace.class)
															.setParameter("loc",locOid.asString());
		query.setHint(QueryHints.READ_ONLY,HintValues.TRUE);
		Collection<X47BDBEntityForWorkPlace> entities = query.getResultList();
		FindResult<X47BWorkPlace> outEntities = FindResultBuilder.using(securityContext)
												          	    .on(_modelObjectType)
												          	    .foundDBEntities(entities)
												          	    .transformedToModelObjectsUsing(this);
		return outEntities;
	}
	@Override
	public FindSummariesResult<X47BWorkPlace> findSummariesByOrgDivisionServiceLocation(final SecurityContext securityContext,
																  final X47BOrgDivisionServiceLocationOID locOid,
																  final Language lang) {
		log.info("Find summaries for all work places in a location");
		// [1] - Do the query
		TypedQuery<X47BDBEntityForOrganizationalEntityBase> qry = this.getEntityManager()
																	  .createNamedQuery("X47BDBEntitiesForWorkPlacesByLocation",
																				 		X47BDBEntityForOrganizationalEntityBase.class)
																	  .setParameter("loc",locOid.asString());
		Collection<X47BDBEntityForOrganizationalEntityBase> dbEntities = qry.getResultList();
		
		// [2] - Transform to summarized model objects
		FindSummariesResult<X47BWorkPlace> outSummaries = null; 
		outSummaries = FindSummariesResultBuilder.using(securityContext)
												 .on(_modelObjectType)
												 .foundDBEntities(dbEntities)
												 .transformedToSummarizedModelObjectUsing(this.dbEntityToSummaryTransformFunction(lang));
		// [3] - Return
		return outSummaries;
	}
}
