package pb01c.db.find;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

import lombok.extern.slf4j.Slf4j;
import pb01a.api.interfaces.PB01AFindServicesBase;
import pb01a.model.PB01APersistableObject;
import pb01a.model.oids.PB01AIDs.PB01APersistableObjectID;
import pb01a.model.oids.PB01AOIDs.PB01APersistableObjectOID;
import r01f.locale.Language;
import r01f.model.persistence.FindResult;
import r01f.model.persistence.FindResultBuilder;
import r01f.objectstreamer.Marshaller;
import r01f.persistence.db.DBFindForModelObjectBase;
import r01f.persistence.db.config.DBModuleConfig;
import r01f.persistence.db.entities.DBEntityForModelObject;
import r01f.persistence.db.entities.primarykeys.DBPrimaryKeyForModelObject;
import r01f.securitycontext.SecurityContext;
import r01f.util.types.Strings;

/**
 * Find layer
 */
@Slf4j
abstract class PB01CDBFindBase<O extends PB01APersistableObjectOID,ID extends PB01APersistableObjectID<O>,M extends PB01APersistableObject<O,ID>,
							  DB extends DBEntityForModelObject<DBPrimaryKeyForModelObject>>
	   extends DBFindForModelObjectBase<O,M,
	 								    DBPrimaryKeyForModelObject,DB>
    implements PB01AFindServicesBase<O,ID,M> {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01CDBFindBase(final Class<M> modelObjectType,final Class<DB> dbEntityType,
						  final DBModuleConfig dbCfg,
						  final EntityManager entityManager,
						  final Marshaller marshaller) {
		super(modelObjectType,dbEntityType,
			  dbCfg,
			  entityManager,
			  marshaller);
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
