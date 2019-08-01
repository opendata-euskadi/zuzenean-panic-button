package x47b.search.db;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.google.common.collect.Lists;

import lombok.extern.slf4j.Slf4j;
import r01f.locale.Language;
import r01f.model.search.query.SearchResultsOrdering;
import r01f.persistence.db.config.DBVendor;
import r01f.persistence.search.db.DBSearchQuery;
import r01f.persistence.search.db.DBSearchQueryToJPQLTranslator;
import r01f.util.types.Strings;
import r01f.util.types.collections.CollectionUtils;
import x47b.bootstrap.core.panicbutton.X42TDBModuleConfig;
import x47b.db.entities.X47BDBEntityForOrgDivision;
import x47b.db.entities.X47BDBEntityForOrgDivisionService;
import x47b.db.entities.X47BDBEntityForOrgDivisionServiceLocation;
import x47b.db.entities.X47BDBEntityForOrganization;
import x47b.db.entities.X47BDBEntityForOrganizationalEntityBase;
import x47b.db.entities.X47BDBEntityForWorkPlace;
import x47b.model.org.X47BOrgDivision;
import x47b.model.org.X47BOrgDivisionService;
import x47b.model.org.X47BOrgDivisionServiceLocation;
import x47b.model.org.X47BOrganization;
import x47b.model.org.X47BWorkPlace;
import x47b.model.search.X47BSearchFilterForPanicButtonOrganizationalEntity;

@Slf4j
  class X47BDBSearchQueryForPanicButtonOrganizationalEntity
extends DBSearchQuery<X47BSearchFilterForPanicButtonOrganizationalEntity,
					  X47BDBEntityForOrganizationalEntityBase> {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BDBSearchQueryForPanicButtonOrganizationalEntity(final X42TDBModuleConfig dbConfig,
							    							   final EntityManager entityManager,
							    							   final Language uiLanguage) {
		this(dbConfig,
			 entityManager,
			 uiLanguage,
			 // translator by default
			 new X47BDBSearchQueryForPanicButtonOrganizationalEntityToJPQLTranslator(dbConfig,
					  																 entityManager));
	}
	public X47BDBSearchQueryForPanicButtonOrganizationalEntity(final X42TDBModuleConfig dbConfig,
							    							   final EntityManager entityManager,
							    							   final Language uiLanguage,
							    							   final X47BDBSearchQueryForPanicButtonOrganizationalEntityToJPQLTranslator searchQueryToJPQLTranslator) {
		super(X47BDBEntityForOrganizationalEntityBase.class,
			  dbConfig,
			  entityManager,
			  uiLanguage,
			  searchQueryToJPQLTranslator);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  JPQL COMPOSING
/////////////////////////////////////////////////////////////////////////////////////////
	static class X47BDBSearchQueryForPanicButtonOrganizationalEntityToJPQLTranslator
		 extends DBSearchQueryToJPQLTranslator<X47BSearchFilterForPanicButtonOrganizationalEntity,X47BDBEntityForOrganizationalEntityBase> {
		public X47BDBSearchQueryForPanicButtonOrganizationalEntityToJPQLTranslator(final X42TDBModuleConfig dbModuleConfig,
												    							   final EntityManager entityManager) {
			super(X47BDBEntityForOrganizationalEntityBase.class,
				  dbModuleConfig,
				  entityManager);
		}
		@Override
	    protected String _composeWhereJpqlPredicates(final X47BSearchFilterForPanicButtonOrganizationalEntity filter,
	    											 final SearchFilterClauseToJPQLWherePredicate filterClauseToJpqlPredicate) {
			final StringBuilder jpqlWhere = new StringBuilder();
			jpqlWhere.append("(");

			// Compose the list of entities to filter
			Collection<String> filteredDBEntities = null;
			if (CollectionUtils.hasData(filter.getFilteredModelObjectTypes())) {
				// a filter is set
				filteredDBEntities = Lists.newArrayListWithExpectedSize(filter.getFilteredModelObjectTypes().size());
				if (filter.getFilteredModelObjectTypes().contains(X47BOrganization.class)) 		 			filteredDBEntities.add(X47BDBEntityForOrganization.class.getSimpleName());
				if (filter.getFilteredModelObjectTypes().contains(X47BOrgDivision.class)) 					filteredDBEntities.add(X47BDBEntityForOrgDivision.class.getSimpleName());
				if (filter.getFilteredModelObjectTypes().contains(X47BOrgDivisionService.class)) 			filteredDBEntities.add(X47BDBEntityForOrgDivisionService.class.getSimpleName());
				if (filter.getFilteredModelObjectTypes().contains(X47BOrgDivisionServiceLocation.class))	filteredDBEntities.add(X47BDBEntityForOrgDivisionServiceLocation.class.getSimpleName());
				if (filter.getFilteredModelObjectTypes().contains(X47BWorkPlace.class)) 					filteredDBEntities.add(X47BDBEntityForWorkPlace.class.getSimpleName());
			} else {
				// no filter set: return all entity types
				filteredDBEntities = Lists.newArrayList(X47BDBEntityForOrganization.class.getSimpleName(),
														X47BDBEntityForOrgDivision.class.getSimpleName(),
														X47BDBEntityForOrgDivisionService.class.getSimpleName(),
												   		X47BDBEntityForOrgDivisionServiceLocation.class.getSimpleName(),
												   		X47BDBEntityForWorkPlace.class.getSimpleName());
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
	//		 && entitiesToSearch.equals(X47BDBEntityForLocation.class.getSimpleName())) {
	//			jpql.append("OR TYPE(entity) IN (").append(X47BDBEntityForWorkPlace.class.getSimpleName()).append(") ");
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
		protected String _composeJpqlOrderByClause(final X47BSearchFilterForPanicButtonOrganizationalEntity filter,
											   	   final Collection<SearchResultsOrdering> ordering) {
			final String nameDBCol = _nameDBColum(filter);
			return "ORDER BY entity._hierarchyLevel ASC," +	// return ordered results: organizations, locations and work places
							 nameDBCol + " ASC," +
							 "entity._oid ASC";
		}
		@Override
	    public void setJPAQueryParameters(final X47BSearchFilterForPanicButtonOrganizationalEntity filter,
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
		private String _nameDBColum(final X47BSearchFilterForPanicButtonOrganizationalEntity filter) {
			Language filteringLang = filter.hasTextFilter() && filter.getTextLanguage() != null ? filter.getTextLanguage()
																								: filter.getUILanguage();
			if (filteringLang == null) filteringLang = Language.DEFAULT;
			final String nameDBColName = filteringLang.is(Language.SPANISH) ? "entity._nameSpanish"
																      		: "entity._nameBasque";
			return nameDBColName;
		}
	}
}