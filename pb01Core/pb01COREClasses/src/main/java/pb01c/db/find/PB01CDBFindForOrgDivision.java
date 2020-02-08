package pb01c.db.find;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

import lombok.extern.slf4j.Slf4j;
import pb01a.api.interfaces.PB01AFindServicesForOrgDivision;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrganizationOID;
import pb01a.model.org.PB01AOrgDivision;
import pb01c.db.entities.PB01CDBEntityForOrgDivision;
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
public class PB01CDBFindForOrgDivision
	 extends PB01CDBFindForOrganizationalEntityBase<PB01AOrgDivisionOID,PB01AOrgDivisionID,PB01AOrgDivision,
	 								 PB01CDBEntityForOrgDivision>
  implements PB01AFindServicesForOrgDivision {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01CDBFindForOrgDivision(final DBModuleConfig dbCfg,
									final EntityManager entityManager,
									final Marshaller marshaller) {
		super(PB01AOrgDivision.class,PB01CDBEntityForOrgDivision.class,
			  dbCfg,
			  entityManager,
			  marshaller);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  EXTENSION METHODS
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public FindResult<PB01AOrgDivision> findByOrganization(final SecurityContext securityContext,
											  		   final PB01AOrganizationOID orgOid) {
		log.debug("> loading OrgDivisions in organization {}",orgOid);

		TypedQuery<PB01CDBEntityForOrgDivision> query = this.getEntityManager()
													        .createNamedQuery("PB01DBEntitiesForDivisionsByOrganization",
															  		          PB01CDBEntityForOrgDivision.class)
															.setParameter("org",orgOid.asString());
		query.setHint(QueryHints.READ_ONLY,HintValues.TRUE);
		Collection<PB01CDBEntityForOrgDivision> entities = query.getResultList();

		FindResult<PB01AOrgDivision> outEntities = FindResultBuilder.using(securityContext)
													          	    .on(_modelObjectType)
													          	    .foundDBEntities(entities)
													          	    .transformedToModelObjectsUsing(this);
		return outEntities;
	}
	@Override
	public FindSummariesResult<PB01AOrgDivision> findSummariesByOrganization(final SecurityContext securityContext,
																		 final PB01AOrganizationOID orgOid,
																		 final Language lang) {
		log.info("Find summaries for all OrgDivisions in an organization");
		// [1] - Do the query
		TypedQuery<PB01CDBEntityForOrganizationalEntityBase> qry = this.getEntityManager()
																	  .createNamedQuery("PB01DBEntitiesForDivisionsByOrganization",
																				 		PB01CDBEntityForOrganizationalEntityBase.class)
																	  .setParameter("org",orgOid.asString());
		qry.setHint(QueryHints.READ_ONLY,HintValues.TRUE);
		Collection<PB01CDBEntityForOrganizationalEntityBase> dbEntities = qry.getResultList();

		// [2] - Transform to summarized model objects
		FindSummariesResult<PB01AOrgDivision> outSummaries = null;
		outSummaries = FindSummariesResultBuilder.using(securityContext)
												 .on(_modelObjectType)
												 .foundDBEntities(dbEntities)
												 .transformedToSummarizedModelObjectUsing(this.dbEntityToSummaryTransformFunction(lang));
		// [3] - Return
		return outSummaries;
	}
}
