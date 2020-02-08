package pb01c.db.crud;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import pb01a.api.interfaces.PB01ACRUDServicesForOrganizationalModelObjectBase;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgObjectID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgObjectOID;
import pb01a.model.org.PB01AOrganizationalPersistableObject;
import pb01c.db.entities.PB01CDBEntityForOrganizationalEntityBase;
import r01f.locale.Language;
import r01f.model.persistence.CRUDResult;
import r01f.objectstreamer.Marshaller;
import r01f.persistence.db.config.DBModuleConfig;
import r01f.securitycontext.SecurityContext;

/**
 * Persistence layer
 */
abstract class PB01CDBCRUDForOrganizationalEntityBase<O extends PB01AOrgObjectOID,ID extends PB01AOrgObjectID<O>,M extends PB01AOrganizationalPersistableObject<O,ID>,
							  						 DB extends PB01CDBEntityForOrganizationalEntityBase>
	   extends PB01CDBCRUDBase<O,ID,M,
	 						  DB>
	implements PB01ACRUDServicesForOrganizationalModelObjectBase<O,ID,M> {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01CDBCRUDForOrganizationalEntityBase(final Class<M> modelObjectType,final Class<DB> dbEntityType,
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
									    .createNamedQuery("PB01DBOrganizationalEntityById",
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
