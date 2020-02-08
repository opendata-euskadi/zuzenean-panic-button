package pb01c.db.find;

import java.util.Collection;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

import lombok.extern.slf4j.Slf4j;
import pb01a.api.interfaces.PB01AFindServicesForOrganization;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrganizationID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrganizationOID;
import pb01a.model.org.PB01AOrganization;
import pb01c.db.entities.PB01CDBEntityForOrganization;
import pb01c.db.entities.PB01CDBEntityForOrganizationalEntityBase;
import r01f.locale.Language;
import r01f.model.persistence.FindSummariesResult;
import r01f.model.persistence.FindSummariesResultBuilder;
import r01f.objectstreamer.Marshaller;
import r01f.persistence.db.config.DBModuleConfig;
import r01f.securitycontext.SecurityContext;

/**
 * Persistence layer
 */
@Slf4j
public class PB01CDBFindForOrganization
	 extends PB01CDBFindForOrganizationalEntityBase<PB01AOrganizationOID,PB01AOrganizationID,PB01AOrganization,
	 								 			   PB01CDBEntityForOrganization>
  implements PB01AFindServicesForOrganization {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public PB01CDBFindForOrganization(final DBModuleConfig dbCfg,
									 final EntityManager entityManager,
									 final Marshaller marshaller) {
		super(PB01AOrganization.class,PB01CDBEntityForOrganization.class,
			  dbCfg,
			  entityManager,
			  marshaller);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  EXTENSION METHODS
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public FindSummariesResult<PB01AOrganization> findSummaries(final SecurityContext securityContext,
															   final Language lang) {
		log.info("Find all organization's summaries");
		// [1] - Do the query
		TypedQuery<PB01CDBEntityForOrganizationalEntityBase> qry = this.getEntityManager()
																	  .createNamedQuery("PB01DBEntitiesForOrganization",
																				 		PB01CDBEntityForOrganizationalEntityBase.class);
		qry.setHint(QueryHints.READ_ONLY,HintValues.TRUE);
		Collection<PB01CDBEntityForOrganizationalEntityBase> dbEntities = qry.getResultList();

		// [2] - Transform to summarized model objects
		FindSummariesResult<PB01AOrganization> outSummaries = null;
		outSummaries = FindSummariesResultBuilder.using(securityContext)
											   .on(_modelObjectType)
											   .foundDBEntities(dbEntities)
											   .transformedToSummarizedModelObjectUsing(this.dbEntityToSummaryTransformFunction(lang));
		// [3] - Return
		return outSummaries;
	}
}
