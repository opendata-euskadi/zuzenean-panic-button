package pb01c.db.find;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

import com.google.common.base.Function;

import lombok.extern.slf4j.Slf4j;
import pb01a.api.interfaces.PB01AFindServicesForOrganizationalModelObjectBase;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgObjectID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgObjectOID;
import pb01a.model.org.PB01AOrganizationalPersistableObject;
import pb01a.model.org.summaries.PB01ASummarizedOrganizationalObject;
import pb01c.db.entities.PB01CDBEntityForOrganizationalEntityBase;
import r01f.locale.Language;
import r01f.model.persistence.FindResult;
import r01f.model.persistence.FindResultBuilder;
import r01f.objectstreamer.Marshaller;
import r01f.persistence.db.config.DBModuleConfig;
import r01f.persistence.db.entities.DBEntityForModelObject;
import r01f.persistence.db.entities.primarykeys.DBPrimaryKeyForModelObject;
import r01f.securitycontext.SecurityContext;
import r01f.util.types.Strings;

/**
 * Persistence layer
 */
@Slf4j
abstract class PB01CDBFindForOrganizationalEntityBase<O extends PB01AOrgObjectOID,ID extends PB01AOrgObjectID<O>,M extends PB01AOrganizationalPersistableObject<O,ID>,
									   				 DB extends DBEntityForModelObject<DBPrimaryKeyForModelObject>>
	   extends PB01CDBFindBase<O,ID,M,
	 						  DB>
    implements PB01AFindServicesForOrganizationalModelObjectBase<O,ID,M> {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01CDBFindForOrganizationalEntityBase(final Class<M> modelObjectType,final Class<DB> dbEntityType,
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
	protected <S extends PB01ASummarizedOrganizationalObject<O,ID,M>>
			  Function<PB01CDBEntityForOrganizationalEntityBase,S> dbEntityToSummaryTransformFunction(final Language lang) {
		return new Function<PB01CDBEntityForOrganizationalEntityBase,S>() {
						@Override @SuppressWarnings("unchecked")
						public S apply(final PB01CDBEntityForOrganizationalEntityBase dbEntity) {
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
		// If the db entity type name is: PB01CDBEntityForXXX, the @NamedQuery MUST be named: PB01CDBEntitiesForXXXsByName{language}
		// ie: for PB01CDBEntityForOrganization > PB01CDBEntitiesForOrganizationsByNameSPANISH and PB01CDBEntitiesForOrganizationsByNameBASQUE
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
