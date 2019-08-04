package x47b.db.crud;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import r01f.locale.Language;
import r01f.model.persistence.CRUDResult;
import r01f.objectstreamer.Marshaller;
import r01f.persistence.db.config.DBModuleConfig;
import r01f.securitycontext.SecurityContext;
import x47b.api.interfaces.X47BCRUDServicesForOrganizationalModelObjectBase;
import x47b.db.entities.X47BDBEntityForOrganizationalEntityBase;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgObjectID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgObjectOID;
import x47b.model.org.X47BOrganizationalPersistableObject;

/**
 * Persistence layer
 */
abstract class X47BDBCRUDForOrganizationalEntityBase<O extends X47BOrgObjectOID,ID extends X47BOrgObjectID<O>,M extends X47BOrganizationalPersistableObject<O,ID>,
							  						 DB extends X47BDBEntityForOrganizationalEntityBase>
	   extends X47BDBCRUDBase<O,ID,M,
	 						  DB>
	implements X47BCRUDServicesForOrganizationalModelObjectBase<O,ID,M> {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BDBCRUDForOrganizationalEntityBase(final Class<M> modelObjectType,final Class<DB> dbEntityType,
												 final DBModuleConfig dbCfg,
								   				 final EntityManager entityManager,
								   				 final Marshaller marshaller) {
		super(modelObjectType,dbEntityType,
			  dbCfg,
			  entityManager,
			  marshaller);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void setDBEntityFieldsFromModelObject(final SecurityContext securityContext,
												 final M modelObj,final DB dbEntity) {
		// Oid
		dbEntity.setOid(modelObj.getOid().asString());
		dbEntity.setId(modelObj.getId().asString());

		// Name
		if (modelObj.getName() != null) {
			dbEntity.setNameSpanish(modelObj.getName().getInOrDefault(Language.SPANISH,
																	  "NO name in SPANISH"));
			dbEntity.setNameBasque(modelObj.getName().getInOrDefault(Language.BASQUE,
																	 "NO name in BASQUE"));
		}

		// Alarm raise count
		dbEntity.setAlarmRaiseCount(modelObj.getAlarmRaiseCount());

		// Descriptor
		dbEntity.setDescriptor(_modelObjectsMarshaller.forWriting().toXml(modelObj));
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public CRUDResult<M> loadById(final SecurityContext securityContext,
								  final ID id) {
		// Do the query
		TypedQuery<DB> query = this.getEntityManager()
									    .createNamedQuery("X47BDBOrganizationalEntityById",
											  		      _DBEntityType)
										.setParameter("dbType",_DBEntityType)
										.setParameter("id",id.asString());
		Collection<DB> dbEntities = query.getResultList();

		// Return
		CRUDResult<M> outResult = _crudResultForSingleEntity(securityContext,
															 id,
															 dbEntities);
		return outResult;
	}
}
