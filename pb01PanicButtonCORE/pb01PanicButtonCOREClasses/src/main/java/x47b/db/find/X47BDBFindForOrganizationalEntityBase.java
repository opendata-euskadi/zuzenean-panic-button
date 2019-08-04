package x47b.db.find;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

import com.google.common.base.Function;

import lombok.extern.slf4j.Slf4j;
import r01f.locale.Language;
import r01f.model.persistence.FindResult;
import r01f.model.persistence.FindResultBuilder;
import r01f.objectstreamer.Marshaller;
import r01f.persistence.db.config.DBModuleConfig;
import r01f.persistence.db.entities.DBEntityForModelObject;
import r01f.persistence.db.entities.primarykeys.DBPrimaryKeyForModelObject;
import r01f.securitycontext.SecurityContext;
import r01f.util.types.Strings;
import x47b.api.interfaces.X47BFindServicesForOrganizationalModelObjectBase;
import x47b.db.entities.X47BDBEntityForOrganizationalEntityBase;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgObjectID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgObjectOID;
import x47b.model.org.X47BOrganizationalPersistableObject;
import x47b.model.org.summaries.X47BSummarizedOrganizationalObject;

/**
 * Persistence layer
 */
@Slf4j
abstract class X47BDBFindForOrganizationalEntityBase<O extends X47BOrgObjectOID,ID extends X47BOrgObjectID<O>,M extends X47BOrganizationalPersistableObject<O,ID>,
									   				 DB extends DBEntityForModelObject<DBPrimaryKeyForModelObject>>
	   extends X47BDBFindBase<O,ID,M,
	 						  DB>
    implements X47BFindServicesForOrganizationalModelObjectBase<O,ID,M> {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BDBFindForOrganizationalEntityBase(final Class<M> modelObjectType,final Class<DB> dbEntityType,
												 final DBModuleConfig dbCfg,
								   				 final EntityManager entityManager,
								   				 final Marshaller marshaller) {
		super(modelObjectType,dbEntityType,
			  dbCfg,
			  entityManager,
			  marshaller);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  DBEntity SUMMARY
/////////////////////////////////////////////////////////////////////////////////////////
	protected <S extends X47BSummarizedOrganizationalObject<O,ID,M>>
			  Function<X47BDBEntityForOrganizationalEntityBase,S> dbEntityToSummaryTransformFunction(final Language lang) {
		return new Function<X47BDBEntityForOrganizationalEntityBase,S>() {
						@Override @SuppressWarnings("unchecked")
						public S apply(final X47BDBEntityForOrganizationalEntityBase dbEntity) {
							// Create a summary from the dbEntity: transform it to a model object and get it summarized
							M modelObject = _modelObjectsMarshaller.forReading().fromXml(dbEntity.getDescriptor(),
																						 _modelObjectType);
							return (S)modelObject.getSummarizedIn(lang);
						}
			   };
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  EXTENSION METHODS
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public FindResult<M> findByNameIn(final SecurityContext securityContext,
									  final Language lang,final String name) {
		log.debug("> loading entities with name {} in {}",name,lang);

		// The @NamedQuery name at every db entity MUST follow a convention:
		// If the db entity type name is: X47BDBEntityForXXX, the @NamedQuery MUST be named: X47BDBEntitiesForXXXsByName{language}
		// ie: for X47BDBEntityForOrganization > X47BDBEntitiesForOrganizationsByNameSPANISH and X47BDBEntitiesForOrganizationsByNameBASQUE
		String queryName = Strings.customized("{}sByName{}",
								  			  _DBEntityType.getSimpleName()
										  					  .replaceAll("Entity","Entities"),
										  	  lang);
		String nameFilter = _sanitizeNameFilter(name);
		TypedQuery<DB> query = this.getEntityManager()
								        .createNamedQuery(queryName,
										  		          _DBEntityType)
										.setParameter("name",nameFilter);
		query.setHint(QueryHints.READ_ONLY,HintValues.TRUE);
		Collection<DB> entities = query.getResultList();

		FindResult<M> outEntities = FindResultBuilder.using(securityContext)
									          	     .on(_modelObjectType)
									          	     .foundDBEntities(entities)
									          	     .transformedToModelObjectsUsing(this);
		return outEntities;
	}
	private static String _sanitizeNameFilter(final String text) {
		String outSanitizedFilter = new String(text);
		outSanitizedFilter = outSanitizedFilter.replaceAll("\\*","%");
		if (!outSanitizedFilter.startsWith("%")) {
			outSanitizedFilter = "%" + outSanitizedFilter;
		}
		if (!outSanitizedFilter.endsWith("%")) {
			outSanitizedFilter = outSanitizedFilter + "%";
		}
		return outSanitizedFilter;
	}
}
