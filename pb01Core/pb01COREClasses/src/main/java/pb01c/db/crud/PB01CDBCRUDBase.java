package pb01c.db.crud;

import javax.persistence.EntityManager;

import pb01a.api.interfaces.PB01ACRUDServicesBase;
import pb01a.model.PB01APersistableObject;
import pb01a.model.oids.PB01AIDs.PB01APersistableObjectID;
import pb01a.model.oids.PB01AOIDs.PB01APersistableObjectOID;
import r01f.objectstreamer.Marshaller;
import r01f.persistence.db.CompletesDBEntityBeforeCreateOrUpdate;
import r01f.persistence.db.DBCRUDForModelObjectBase;
import r01f.persistence.db.config.DBModuleConfig;
import r01f.persistence.db.entities.DBEntityForModelObject;
import r01f.persistence.db.entities.primarykeys.DBPrimaryKeyForModelObject;

/**
 * Persistence layer
 */
abstract class PB01CDBCRUDBase<O extends PB01APersistableObjectOID,ID extends PB01APersistableObjectID<O>,M extends PB01APersistableObject<O,ID>,
							  DB extends DBEntityForModelObject<DBPrimaryKeyForModelObject>>
	   extends DBCRUDForModelObjectBase<O,M,
	 								    DBPrimaryKeyForModelObject,DB>
    implements CompletesDBEntityBeforeCreateOrUpdate<M,DB>,
    		   PB01ACRUDServicesBase<O,ID,M> {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01CDBCRUDBase(final Class<M> modelObjectType,final Class<DB> dbEntityType,
						  final DBModuleConfig dbCfg,
						  final EntityManager entityManager,
						  final Marshaller marshaller) {
		super(modelObjectType,dbEntityType,
			  dbCfg,
			  entityManager,
			  marshaller);
	}
}
