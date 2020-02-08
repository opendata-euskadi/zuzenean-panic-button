package pb01c.db.find;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

import lombok.extern.slf4j.Slf4j;
import pb01a.api.interfaces.PB01AFindServicesForOrgDivisionService;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionServiceID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionServiceOID;
import pb01a.model.org.PB01AOrgDivisionService;
import pb01c.db.entities.PB01CDBEntityForOrgDivisionService;
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
public class PB01CDBFindForOrgDivisionService
	 extends PB01CDBFindForOrganizationalEntityBase<PB01AOrgDivisionServiceOID,PB01AOrgDivisionServiceID,PB01AOrgDivisionService,
	 								 PB01CDBEntityForOrgDivisionService>
  implements PB01AFindServicesForOrgDivisionService {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01CDBFindForOrgDivisionService(final DBModuleConfig dbCfg,
										   final EntityManager entityManager,
										   final Marshaller marshaller) {
		super(PB01AOrgDivisionService.class,PB01CDBEntityForOrgDivisionService.class,
			  dbCfg,
			  entityManager,
			  marshaller);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  EXTENSION METHODS
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public FindResult<PB01AOrgDivisionService> findByOrgDivision(final SecurityContext securityContext,
										   					 	final PB01AOrgDivisionOID divisionOid) {
		log.debug("> loading services in division {}",divisionOid);

		TypedQuery<PB01CDBEntityForOrgDivisionService> query = this.getEntityManager()
															      .createNamedQuery("PB01DBEntitiesForServicesByDivision",
																	 		        PB01CDBEntityForOrgDivisionService.class)
																  .setParameter("division",divisionOid.asString());
		query.setHint(QueryHints.READ_ONLY,HintValues.TRUE);
		Collection<PB01CDBEntityForOrgDivisionService> entities = query.getResultList();
		FindResult<PB01AOrgDivisionService> outEntities = FindResultBuilder.using(securityContext)
															          	  .on(_modelObjectType)
															          	  .foundDBEntities(entities)
															          	  .transformedToModelObjectsUsing(this);
		return outEntities;
	}
	@Override
	public FindSummariesResult<PB01AOrgDivisionService> findSummariesByOrgDivision(final SecurityContext securityContext,
																  				  final PB01AOrgDivisionOID divisionOid,
																  				  final Language lang) {
		log.info("Find summaries for all services in division {}",divisionOid);
		// [1] - Do the query
		TypedQuery<PB01CDBEntityForOrganizationalEntityBase> qry = this.getEntityManager()
																	  .createNamedQuery("PB01DBEntitiesForServicesByDivision",
																				 		PB01CDBEntityForOrganizationalEntityBase.class)
																	  .setParameter("division",divisionOid.asString());
		qry.setHint(QueryHints.READ_ONLY,HintValues.TRUE);
		Collection<PB01CDBEntityForOrganizationalEntityBase> dbEntities = qry.getResultList();

		// [2] - Transform to summarized model objects
		FindSummariesResult<PB01AOrgDivisionService> outSummaries = null;
		outSummaries = FindSummariesResultBuilder.using(securityContext)
												 .on(_modelObjectType)
												 .foundDBEntities(dbEntities)
												 .transformedToSummarizedModelObjectUsing(this.dbEntityToSummaryTransformFunction(lang));
		// [3] - Return
		return outSummaries;
	}
}
