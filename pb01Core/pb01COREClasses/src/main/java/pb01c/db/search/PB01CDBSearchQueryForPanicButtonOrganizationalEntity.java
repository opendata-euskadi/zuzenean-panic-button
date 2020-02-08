package pb01c.db.search;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.google.common.collect.Lists;

import lombok.extern.slf4j.Slf4j;
import pb01a.model.org.PB01AOrgDivision;
import pb01a.model.org.PB01AOrgDivisionService;
import pb01a.model.org.PB01AOrgDivisionServiceLocation;
import pb01a.model.org.PB01AOrganization;
import pb01a.model.org.PB01AWorkPlace;
import pb01a.model.search.PB01ASearchFilterForPanicButtonOrganizationalEntity;
import pb01c.bootstrap.core.panicbutton.PB01CDBModuleConfig;
import pb01c.db.entities.PB01CDBEntityForOrgDivision;
import pb01c.db.entities.PB01CDBEntityForOrgDivisionService;
import pb01c.db.entities.PB01CDBEntityForOrgDivisionServiceLocation;
import pb01c.db.entities.PB01CDBEntityForOrganization;
import pb01c.db.entities.PB01CDBEntityForOrganizationalEntityBase;
import pb01c.db.entities.PB01CDBEntityForWorkPlace;
import r01f.locale.Language;
import r01f.model.search.query.SearchResultsOrdering;
import r01f.persistence.db.config.DBVendor;
import r01f.persistence.search.db.DBSearchQuery;
import r01f.persistence.search.db.DBSearchQueryToJPQLTranslator;
import r01f.persistence.search.db.TranslatesIndexableFieldIDToDBEntityField;
import r01f.persistence.search.db.TranslatesSearchFilterClauseToJPQLWherePredicate;
import r01f.util.types.Strings;
import r01f.util.types.collections.CollectionUtils;

@Slf4j
  class PB01CDBSearchQueryForPanicButtonOrganizationalEntity
extends DBSearchQuery<PB01ASearchFilterForPanicButtonOrganizationalEntity,
					  PB01CDBEntityForOrganizationalEntityBase> {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01CDBSearchQueryForPanicButtonOrganizationalEntity(final PB01CDBModuleConfig dbConfig,
							    							   final EntityManager entityManager,
							    							   final Language uiLanguage) {
		this(dbConfig,
			 entityManager,
			 uiLanguage,
			 // translator by default
			 new PB01CDBSearchQueryForPanicButtonOrganizationalEntityToJPQLTranslator(dbConfig,
					  																 entityManager));
	}
	public PB01CDBSearchQueryForPanicButtonOrganizationalEntity(final PB01CDBModuleConfig dbConfig,
							    							   final EntityManager entityManager,
							    							   final Language uiLanguage,
							    							   final PB01CDBSearchQueryForPanicButtonOrganizationalEntityToJPQLTranslator searchQueryToJPQLTranslator) {
		super(PB01CDBEntityForOrganizationalEntityBase.class,
			  dbConfig,
			  entityManager,
			  uiLanguage,
			  searchQueryToJPQLTranslator);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  JPQL COMPOSING
/////////////////////////////////////////////////////////////////////////////////////////
	static class PB01CDBSearchQueryForPanicButtonOrganizationalEntityToJPQLTranslator
		 extends DBSearchQueryToJPQLTranslator<PB01ASearchFilterForPanicButtonOrganizationalEntity,PB01CDBEntityForOrganizationalEntityBase> {
		public PB01CDBSearchQueryForPanicButtonOrganizationalEntityToJPQLTranslator(final PB01CDBModuleConfig dbModuleConfig,
												    							    final EntityManager entityManager) {
			super(PB01CDBEntityForOrganizationalEntityBase.class,
				  dbModuleConfig,
				  entityManager);
		}
		@Override
	    protected String _composeWhereJpqlPredicates(final PB01ASearchFilterForPanicButtonOrganizationalEntity filter,
	    											 final TranslatesSearchFilterClauseToJPQLWherePredicate filterClauseToJpqlPredicate) {
			final StringBuilder jpqlWhere = new StringBuilder();
			jpqlWhere.append("(");

			// Compose the list of entities to filter
			Collection<String> filteredDBEntities = null;
			if (CollectionUtils.hasData(filter.getFilteredModelObjectTypes())) {
				// a filter is set
				filteredDBEntities = Lists.newArrayListWithExpectedSize(filter.getFilteredModelObjectTypes().size());
				if (filter.getFilteredModelObjectTypes().contains(PB01AOrganization.class)) 		 		filteredDBEntities.add(PB01CDBEntityForOrganization.class.getSimpleName());
				if (filter.getFilteredModelObjectTypes().contains(PB01AOrgDivision.class)) 					filteredDBEntities.add(PB01CDBEntityForOrgDivision.class.getSimpleName());
				if (filter.getFilteredModelObjectTypes().contains(PB01AOrgDivisionService.class)) 			filteredDBEntities.add(PB01CDBEntityForOrgDivisionService.class.getSimpleName());
				if (filter.getFilteredModelObjectTypes().contains(PB01AOrgDivisionServiceLocation.class))	filteredDBEntities.add(PB01CDBEntityForOrgDivisionServiceLocation.class.getSimpleName());
				if (filter.getFilteredModelObjectTypes().contains(PB01AWorkPlace.class)) 					filteredDBEntities.add(PB01CDBEntityForWorkPlace.class.getSimpleName());
			} else {
				// no filter set: return all entity types
				filteredDBEntities = Lists.newArrayList(PB01CDBEntityForOrganization.class.getSimpleName(),
														PB01CDBEntityForOrgDivision.class.getSimpleName(),
														PB01CDBEntityForOrgDivisionService.class.getSimpleName(),
												   		PB01CDBEntityForOrgDivisionServiceLocation.class.getSimpleName(),
												   		PB01CDBEntityForWorkPlace.class.getSimpleName());
			}
			final String entitiesToSearch = CollectionUtils.of(filteredDBEntities)
											  		 .toStringCommaSeparated();
			log.debug("Filtering entities of types: {}",entitiesToSearch);

			jpqlWhere.append(Strings.customized("TYPE(entity) IN ({})",
												entitiesToSearch));
			jpqlWhere.append(" ");


			// Get the db colname for the name: depends on the language
			final String nameDBColName = _nameDBColum(filter);

			// [1] - Filter by text
			if (filter.hasTextFilter() && Strings.isNOTNullOrEmpty(filter.getText())) {
				final boolean fullTextSearchSupported = _dbModuleConfig.isFullTextSearchSupported(_entityManager);
				log.debug("FullText search enabled: {}",_dbModuleConfig.isFullTextSearchSupported(_entityManager));
				if (fullTextSearchSupported) {
					// Full text search is ENABLED: the filter expression is db platform-dependent
					// 								use SQL operator available since eclipselink 2.5
					// 								see http://wiki.eclipse.org/EclipseLink/UserGuide/JPA/Basic_JPA_Development/Querying/Support_for_Native_Database_Functions#SQL
					final DBVendor dbVendor = _dbModuleConfig.getDbSpec().getVendor();
					if (dbVendor.is(DBVendor.MySQL)) {
					    // IMPORTANT!! see: http://dev.mysql.com/doc/refman/5.0/en/fulltext-search.html / http://devzone.zend.com/26/using-mysql-full-text-searching/
					    //		Tables MUST be MyISAM (InnoDB)) type; to change the table type:
					    //			ALTER TABLE [table] engine=MyISAM;
					    //
					    //		also a FULLTEXT index must be added to the cols:
					    //			ALTER TABLE [table] ADD FULLTEXT [NOMBRE INDICE](col1,col2,...);
					    //
					    //		Once the above is done, a FULL-TEXT search can be executed like:
					    //			select *
					    //			  from [table]
					    //			 where MATCH(col1,col2) AGAINST ('[text]');

						// Generate:  SQL('MATCH(?) AGAINST(?),_entity._nameXX,:text
						jpqlWhere.append("AND SQL('MATCH(?) AGAINST(?)',").append(nameDBColName).append(",:text) ");
					}
					else if (dbVendor.is(DBVendor.ORACLE)) {
						// IMPORTANT!! see: http://docs.oracle.com/cd/B28359_01/text.111/b28304/csql.htm#i997503
						// 		Oracle Text MUST be enabled

						// Generate: contains(?,?,1) > 0,entity._nameXX,:text
						jpqlWhere.append("AND SQL('CONTAINS(?,?,1) > 0',").append(nameDBColName).append(",:text) ");
					}

				} else {
					// Full Text search is DISABLED: use LIKE operator
					jpqlWhere.append("AND upper(").append(nameDBColName).append(") LIKE :text ");
				}
			}

			// If we're filtering by location by text show also the location's work places
	//		if (filter.hasTextFilter() && Strings.isNOTNullOrEmpty(filter.getText())
	//		 && entitiesToSearch.equals(PB01CDBEntityForLocation.class.getSimpleName())) {
	//			jpql.append("OR TYPE(entity) IN (").append(PB01CDBEntityForWorkPlace.class.getSimpleName()).append(") ");
	//		}

			// [2] - Filter by organization & location
			if (filter.getOrganizationOid() != null) 					jpqlWhere.append("AND entity._organizationOid = :orgOid ");
			if (filter.getOrgDivisionOid() != null)						jpqlWhere.append("AND entity._orgDivisionOid = :orgDivOid ");
			if (filter.getOrgDivisionServiceOid() != null)				jpqlWhere.append("AND entity._orgDivisionServiceOid = :orgDivSrvcOid ");
			if (filter.getOrgDivisionServiceLocationOid() != null)  	jpqlWhere.append("AND entity._orgDivisionServiceLocationOid = :orgDivSrvcLocOid ");
			if (filter.getWorkPlaceOid() != null)  						jpqlWhere.append("AND entity._oid = :wrkPlcOid ");

			jpqlWhere.append(") ");

			log.debug("JPQL: {}",jpqlWhere);

			return jpqlWhere.toString();
	    }
		@Override
		protected String _composeJpqlOrderByClause(final PB01ASearchFilterForPanicButtonOrganizationalEntity filter,
											   	   final Collection<SearchResultsOrdering> ordering,
											   	   final TranslatesIndexableFieldIDToDBEntityField translatesFieldToDBEntityField) {
			final String nameDBCol = _nameDBColum(filter);
			return "ORDER BY entity._hierarchyLevel ASC," +	// return ordered results: organizations, locations and work places
							 nameDBCol + " ASC," +
							 "entity._oid ASC";
		}
		@Override
	    public void setJPAQueryParameters(final PB01ASearchFilterForPanicButtonOrganizationalEntity filter,
	    								  final Query qry) {
			if (filter.hasTextFilter() && Strings.isNOTNullOrEmpty(filter.getText())) {
				final boolean fullTextSearchSupported = _dbModuleConfig.isFullTextSearchSupported(_entityManager);
				final String text = filter.getText()
									.trim().toUpperCase();	// important!!
				String filteringText = null;
				if (fullTextSearchSupported) {
					// when full text search is enabled, multiple words are supported
					filteringText = text;
				}
				else {
					// when full text search is NOT enabled, LIKE operations ONLY supports a single word
					// (otherwise multiple LIKE clauses are needed:  X LIKE %..% OR X LIKE %..%)
					filteringText = "%" +
									text.split(" ")[0]				// use only the FIRST word (no multiple word is allowed)
									    .replaceAll("%","") +		// remove all %
								    "%";
				}
				qry.setParameter("text",filteringText);
			}
			if (filter.getOrganizationOid() != null) 				qry.setParameter("orgOid",filter.getOrganizationOid().asString());
			if (filter.getOrgDivisionOid() != null) 				qry.setParameter("orgDivOid",filter.getOrgDivisionOid().asString());
			if (filter.getOrgDivisionServiceOid() != null) 			qry.setParameter("orgDivSrvcOid",filter.getOrgDivisionServiceOid().asString());
			if (filter.getOrgDivisionServiceLocationOid() != null) 	qry.setParameter("orgDivSrvcLocOid",filter.getOrgDivisionServiceLocationOid().asString());
			if (filter.getWorkPlaceOid() != null) 					qry.setParameter("wrkPlcOid",filter.getWorkPlaceOid().asString());
		}
		private String _nameDBColum(final PB01ASearchFilterForPanicButtonOrganizationalEntity filter) {
			Language filteringLang = filter.hasTextFilter() && filter.getTextLanguage() != null ? filter.getTextLanguage()
																								: filter.getUILanguage();
			if (filteringLang == null) filteringLang = Language.DEFAULT;
			final String nameDBColName = filteringLang.is(Language.SPANISH) ? "entity._nameSpanish"
																      		: "entity._nameBasque";
			return nameDBColName;
		}
	}
}