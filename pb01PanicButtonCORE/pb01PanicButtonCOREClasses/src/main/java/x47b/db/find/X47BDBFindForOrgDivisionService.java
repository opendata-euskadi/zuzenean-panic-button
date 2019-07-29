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
import x47b.api.interfaces.X47BFindServicesForOrgDivisionService;
import x47b.db.entities.X47BDBEntityForOrgDivisionService;
import x47b.db.entities.X47BDBEntityForOrganizationalEntityBase;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionServiceID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceOID;
import x47b.model.org.X47BOrgDivisionService;

/**
 * Persistence layer
 */
@Slf4j
public class X47BDBFindForOrgDivisionService
	 extends X47BDBFindForOrganizationalEntityBase<X47BOrgDivisionServiceOID,X47BOrgDivisionServiceID,X47BOrgDivisionService,
	 								 X47BDBEntityForOrgDivisionService>
  implements X47BFindServicesForOrgDivisionService {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BDBFindForOrgDivisionService(final DBModuleConfig dbCfg,
										   final EntityManager entityManager,
										   final Marshaller marshaller) {
		super(X47BOrgDivisionService.class,X47BDBEntityForOrgDivisionService.class,
			  dbCfg,
			  entityManager,
			  marshaller);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  EXTENSION METHODS
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public FindResult<X47BOrgDivisionService> findByOrgDivision(final SecurityContext securityContext,
										   					 	final X47BOrgDivisionOID divisionOid) {
		log.debug("> loading services in division {}",divisionOid);

		TypedQuery<X47BDBEntityForOrgDivisionService> query = this.getEntityManager()
															      .createNamedQuery("X47BDBEntitiesForServicesByDivision",
																	 		        X47BDBEntityForOrgDivisionService.class)
																  .setParameter("division",divisionOid.asString());
		query.setHint(QueryHints.READ_ONLY,HintValues.TRUE);
		Collection<X47BDBEntityForOrgDivisionService> entities = query.getResultList();
		FindResult<X47BOrgDivisionService> outEntities = FindResultBuilder.using(securityContext)
															          	  .on(_modelObjectType)
															          	  .foundDBEntities(entities)
															          	  .transformedToModelObjectsUsing(this);
		return outEntities;
	}
	@Override
	public FindSummariesResult<X47BOrgDivisionService> findSummariesByOrgDivision(final SecurityContext securityContext,
																  				  final X47BOrgDivisionOID divisionOid,
																  				  final Language lang) {
		log.info("Find summaries for all services in division {}",divisionOid);
		// [1] - Do the query
		TypedQuery<X47BDBEntityForOrganizationalEntityBase> qry = this.getEntityManager()
																	  .createNamedQuery("X47BDBEntitiesForServicesByDivision",
																				 		X47BDBEntityForOrganizationalEntityBase.class)
																	  .setParameter("division",divisionOid.asString());
		qry.setHint(QueryHints.READ_ONLY,HintValues.TRUE);
		Collection<X47BDBEntityForOrganizationalEntityBase> dbEntities = qry.getResultList();
		
		// [2] - Transform to summarized model objects
		FindSummariesResult<X47BOrgDivisionService> outSummaries = null; 
		outSummaries = FindSummariesResultBuilder.using(securityContext)
												 .on(_modelObjectType)
												 .foundDBEntities(dbEntities)
												 .transformedToSummarizedModelObjectUsing(this.dbEntityToSummaryTransformFunction(lang));
		// [3] - Return
		return outSummaries;
	}
}
