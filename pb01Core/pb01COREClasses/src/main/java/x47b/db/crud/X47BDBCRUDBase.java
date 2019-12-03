package x47b.db.crud;

import javax.persistence.EntityManager;

import r01f.objectstreamer.Marshaller;
import r01f.persistence.db.CompletesDBEntityBeforeCreateOrUpdate;
import r01f.persistence.db.DBCRUDForModelObjectBase;
import r01f.persistence.db.config.DBModuleConfig;
import r01f.persistence.db.entities.DBEntityForModelObject;
import r01f.persistence.db.entities.primarykeys.DBPrimaryKeyForModelObject;
import x47b.api.interfaces.X47BCRUDServicesBase;
import x47b.model.X47BPersistableObject;
import x47b.model.oids.X47BIDs.X47BPersistableObjectID;
import x47b.model.oids.X47BOIDs.X47BPersistableObjectOID;

/**
 * Persistence layer
 */
abstract class X47BDBCRUDBase<O extends X47BPersistableObjectOID,ID extends X47BPersistableObjectID<O>,M extends X47BPersistableObject<O,ID>,
							  DB extends DBEntityForModelObject<DBPrimaryKeyForModelObject>>
	   extends DBCRUDForModelObjectBase<O,M,
	 								    DBPrimaryKeyForModelObject,DB>
    implements CompletesDBEntityBeforeCreateOrUpdate<M,DB>,
    		   X47BCRUDServicesBase<O,ID,M> {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BDBCRUDBase(final Class<M> modelObjectType,final Class<DB> dbEntityType,
						  final DBModuleConfig dbCfg,
						  final EntityManager entityManager,
						  final Marshaller marshaller) {
		super(modelObjectType,dbEntityType,
			  dbCfg,
			  entityManager,
			  marshaller);
	}
}
