package x47b.db.find;

import java.util.Collection;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

import lombok.extern.slf4j.Slf4j;
import r01f.locale.Language;
import r01f.model.persistence.FindSummariesResult;
import r01f.model.persistence.FindSummariesResultBuilder;
import r01f.objectstreamer.Marshaller;
import r01f.persistence.db.config.DBModuleConfig;
import r01f.securitycontext.SecurityContext;
import x47b.api.interfaces.X47BFindServicesForOrganization;
import x47b.db.entities.X47BDBEntityForOrganization;
import x47b.db.entities.X47BDBEntityForOrganizationalEntityBase;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrganizationID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrganizationOID;
import x47b.model.org.X47BOrganization;

/**
 * Persistence layer
 */
@Slf4j
public class X47BDBFindForOrganization
	 extends X47BDBFindForOrganizationalEntityBase<X47BOrganizationOID,X47BOrganizationID,X47BOrganization,
	 								 			   X47BDBEntityForOrganization>
  implements X47BFindServicesForOrganization {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public X47BDBFindForOrganization(final DBModuleConfig dbCfg,
									 final EntityManager entityManager,
									 final Marshaller marshaller) {
		super(X47BOrganization.class,X47BDBEntityForOrganization.class,
			  dbCfg,
			  entityManager,
			  marshaller);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  EXTENSION METHODS
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public FindSummariesResult<X47BOrganization> findSummaries(final SecurityContext securityContext,
															   final Language lang) {
		log.info("Find all organization's summaries");
		// [1] - Do the query
		TypedQuery<X47BDBEntityForOrganizationalEntityBase> qry = this.getEntityManager()
																	  .createNamedQuery("X47BDBEntitiesForOrganization",
																				 		X47BDBEntityForOrganizationalEntityBase.class);
		qry.setHint(QueryHints.READ_ONLY,HintValues.TRUE);
		Collection<X47BDBEntityForOrganizationalEntityBase> dbEntities = qry.getResultList();
		
		// [2] - Transform to summarized model objects
		FindSummariesResult<X47BOrganization> outSummaries = null; 
		outSummaries = FindSummariesResultBuilder.using(securityContext)
											   .on(_modelObjectType)
											   .foundDBEntities(dbEntities)
											   .transformedToSummarizedModelObjectUsing(this.dbEntityToSummaryTransformFunction(lang));
		// [3] - Return
		return outSummaries;
	}
}
