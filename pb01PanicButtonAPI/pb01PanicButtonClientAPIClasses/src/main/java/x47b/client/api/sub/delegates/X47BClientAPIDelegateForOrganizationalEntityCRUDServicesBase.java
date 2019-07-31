package x47b.client.api.sub.delegates;

import javax.inject.Provider;

import r01f.model.PersistableModelObject;
import r01f.model.persistence.PersistenceException;
import r01f.objectstreamer.Marshaller;
import r01f.patterns.Factory;
import r01f.persistence.callback.spec.PersistenceOperationCallbackSpec;
import r01f.securitycontext.SecurityContext;
import r01f.types.dirtytrack.DirtyTrackAdapter;
import x47b.api.interfaces.X47BCRUDServicesForOrganizationalModelObjectBase;
import x47b.model.oids.X47BIDs.X47BPersistableObjectID;
import x47b.model.oids.X47BOIDs.X47BPersistableObjectOID;

public class X47BClientAPIDelegateForOrganizationalEntityCRUDServicesBase<O extends X47BPersistableObjectOID,ID extends X47BPersistableObjectID<O>,M extends PersistableModelObject<O>>
	 extends X47BClientAPIDelegateForCRUDServicesBase<O,ID,M> {
/////////////////////////////////////////////////////////////////////////////////////////
//	FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	private final Factory<O> _oidFactory;

/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BClientAPIDelegateForOrganizationalEntityCRUDServicesBase(final Provider<SecurityContext> securityContextProvider,
																		final Marshaller modelObjectsMarshaller,
														  				final X47BCRUDServicesForOrganizationalModelObjectBase<O,ID,M> crudServicesProxy,
														  				final Factory<O> oidFactory) {
		super(securityContextProvider,
			  modelObjectsMarshaller,
			  crudServicesProxy);
		_oidFactory = oidFactory;
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public M save(final M record) throws PersistenceException {
		// [0] - If the object is NEW, set the object oid
		_ensureNewObjectHasOid(record);
		// [1] - Just save
		return super.save(record);
	}
	@Override
	public M save(final M record,
				  final PersistenceOperationCallbackSpec callbackSpec) {
		// [0] - If the object is NEW, set the object oid
		_ensureNewObjectHasOid(record);
		// [1] - Just save
		return super.save(record,
						  callbackSpec);
	}
	@Override
	public M create(final M record) throws PersistenceException {
		// [0] - If the object is NEW, set the object oid
		_ensureNewObjectHasOid(record);
		// [1] - Just save
		return super.create(record);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	private void _ensureNewObjectHasOid(final M record) {
		boolean newObj = DirtyTrackAdapter.adapt(record)
										  .getTrackingStatus()
										  .isThisNew();
		if (newObj && record.getOid() == null) {
			// create a new oid for the object (this could also be done at the API or core side)
			O newOid = _oidFactory.create();
			record.setOid(newOid);
		}
	}
}
