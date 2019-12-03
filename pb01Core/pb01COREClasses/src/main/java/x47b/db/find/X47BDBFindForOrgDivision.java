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
import x47b.api.interfaces.X47BFindServicesForOrgDivision;
import x47b.db.entities.X47BDBEntityForOrgDivision;
import x47b.db.entities.X47BDBEntityForOrganizationalEntityBase;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrganizationOID;
import x47b.model.org.X47BOrgDivision;

/**
 * Persistence layer
 */
@Slf4j
public class X47BDBFindForOrgDivision
	 extends X47BDBFindForOrganizationalEntityBase<X47BOrgDivisionOID,X47BOrgDivisionID,X47BOrgDivision,
	 								 X47BDBEntityForOrgDivision>
  implements X47BFindServicesForOrgDivision {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BDBFindForOrgDivision(final DBModuleConfig dbCfg,
									final EntityManager entityManager,
									final Marshaller marshaller) {
		super(X47BOrgDivision.class,X47BDBEntityForOrgDivision.class,
			  dbCfg,
			  entityManager,
			  marshaller);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  EXTENSION METHODS
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public FindResult<X47BOrgDivision> findByOrganization(final SecurityContext securityContext,
											  		   final X47BOrganizationOID orgOid) {
		log.debug("> loading OrgDivisions in organization {}",orgOid);

		TypedQuery<X47BDBEntityForOrgDivision> query = this.getEntityManager()
													        .createNamedQuery("X47BDBEntitiesForDivisionsByOrganization",
															  		          X47BDBEntityForOrgDivision.class)
															.setParameter("org",orgOid.asString());
		query.setHint(QueryHints.READ_ONLY,HintValues.TRUE);
		Collection<X47BDBEntityForOrgDivision> entities = query.getResultList();

		FindResult<X47BOrgDivision> outEntities = FindResultBuilder.using(securityContext)
													          	    .on(_modelObjectType)
													          	    .foundDBEntities(entities)
													          	    .transformedToModelObjectsUsing(this);
		return outEntities;
	}
	@Override
	public FindSummariesResult<X47BOrgDivision> findSummariesByOrganization(final SecurityContext securityContext,
																		 final X47BOrganizationOID orgOid,
																		 final Language lang) {
		log.info("Find summaries for all OrgDivisions in an organization");
		// [1] - Do the query
		TypedQuery<X47BDBEntityForOrganizationalEntityBase> qry = this.getEntityManager()
																	  .createNamedQuery("X47BDBEntitiesForDivisionsByOrganization",
																				 		X47BDBEntityForOrganizationalEntityBase.class)
																	  .setParameter("org",orgOid.asString());
		qry.setHint(QueryHints.READ_ONLY,HintValues.TRUE);
		Collection<X47BDBEntityForOrganizationalEntityBase> dbEntities = qry.getResultList();
		
		// [2] - Transform to summarized model objects
		FindSummariesResult<X47BOrgDivision> outSummaries = null; 
		outSummaries = FindSummariesResultBuilder.using(securityContext)
												 .on(_modelObjectType)
												 .foundDBEntities(dbEntities)
												 .transformedToSummarizedModelObjectUsing(this.dbEntityToSummaryTransformFunction(lang));
		// [3] - Return
		return outSummaries;
	}
}
