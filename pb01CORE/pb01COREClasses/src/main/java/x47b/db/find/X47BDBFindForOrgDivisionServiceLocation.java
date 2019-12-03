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
import x47b.api.interfaces.X47BFindServicesForOrgDivisionServiceLocation;
import x47b.db.entities.X47BDBEntityForOrgDivisionServiceLocation;
import x47b.db.entities.X47BDBEntityForOrganizationalEntityBase;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionServiceLocationID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceLocationOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceOID;
import x47b.model.org.X47BOrgDivisionServiceLocation;

/**
 * Persistence layer
 */
@Slf4j
public class X47BDBFindForOrgDivisionServiceLocation
	 extends X47BDBFindForOrganizationalEntityBase<X47BOrgDivisionServiceLocationOID,X47BOrgDivisionServiceLocationID,X47BOrgDivisionServiceLocation,
	 								 X47BDBEntityForOrgDivisionServiceLocation>
  implements X47BFindServicesForOrgDivisionServiceLocation {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BDBFindForOrgDivisionServiceLocation(final DBModuleConfig dbCfg,
												   final EntityManager entityManager,
												   final Marshaller marshaller) {
		super(X47BOrgDivisionServiceLocation.class,X47BDBEntityForOrgDivisionServiceLocation.class,
			  dbCfg,
			  entityManager,
			  marshaller);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  EXTENSION METHODS
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public FindResult<X47BOrgDivisionServiceLocation> findByOrgDivisionService(final SecurityContext securityContext,
										   					    			   final X47BOrgDivisionServiceOID serviceOid) {
		log.debug("> loading locations in service {}",serviceOid);

		TypedQuery<X47BDBEntityForOrgDivisionServiceLocation> query = this.getEntityManager()
																	        .createNamedQuery("X47BDBEntitiesForLocationsByService",
																			  		          X47BDBEntityForOrgDivisionServiceLocation.class)
																			.setParameter("service",serviceOid.asString());
		query.setHint(QueryHints.READ_ONLY,HintValues.TRUE);
		Collection<X47BDBEntityForOrgDivisionServiceLocation> entities = query.getResultList();
		FindResult<X47BOrgDivisionServiceLocation> outEntities = FindResultBuilder.using(securityContext)
																	          	  .on(_modelObjectType)
																	          	  .foundDBEntities(entities)
																	          	  .transformedToModelObjectsUsing(this);
		return outEntities;
	}
	@Override
	public FindSummariesResult<X47BOrgDivisionServiceLocation> findSummariesByOrgDivisionService(final SecurityContext securityContext,
																  								 final X47BOrgDivisionServiceOID serviceOid,
																  								 final Language lang) {
		log.info("Find summaries for all locations in service {}",serviceOid);
		// [1] - Do the query
		TypedQuery<X47BDBEntityForOrganizationalEntityBase> qry = this.getEntityManager()
																	  .createNamedQuery("X47BDBEntitiesForLocationsByService",
																				 		X47BDBEntityForOrganizationalEntityBase.class)
																	  .setParameter("service",serviceOid.asString());
		qry.setHint(QueryHints.READ_ONLY,HintValues.TRUE);
		Collection<X47BDBEntityForOrganizationalEntityBase> dbEntities = qry.getResultList();
		
		// [2] - Transform to summarized model objects
		FindSummariesResult<X47BOrgDivisionServiceLocation> outSummaries = null; 
		outSummaries = FindSummariesResultBuilder.using(securityContext)
												 .on(_modelObjectType)
												 .foundDBEntities(dbEntities)
												 .transformedToSummarizedModelObjectUsing(this.dbEntityToSummaryTransformFunction(lang));
		// [3] - Return
		return outSummaries;
	}
}
