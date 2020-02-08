package pb01a.client.api.sub.delegates;

import javax.inject.Provider;

import pb01a.api.interfaces.PB01ACRUDServicesForOrganizationalModelObjectBase;
import pb01a.model.oids.PB01AIDs.PB01APersistableObjectID;
import pb01a.model.oids.PB01AOIDs.PB01APersistableObjectOID;
import r01f.model.PersistableModelObject;
import r01f.model.persistence.PersistenceException;
import r01f.objectstreamer.Marshaller;
import r01f.patterns.Factory;
import r01f.securitycontext.SecurityContext;
import r01f.services.callback.spec.COREServiceMethodCallbackSpec;
import r01f.types.dirtytrack.DirtyTrackAdapter;

public class PB01AClientAPIDelegateForOrganizationalEntityCRUDServicesBase<O extends PB01APersistableObjectOID,ID extends PB01APersistableObjectID<O>,M extends PersistableModelObject<O>>
	 extends PB01AClientAPIDelegateForCRUDServicesBase<O,ID,M> {
/////////////////////////////////////////////////////////////////////////////////////////
//	FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	private final Factory<O> _oidFactory;

/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01AClientAPIDelegateForOrganizationalEntityCRUDServicesBase(final Provider<SecurityContext> securityContextProvider,
																		final Marshaller modelObjectsMarshaller,
														  				final PB01ACRUDServicesForOrganizationalModelObjectBase<O,ID,M> crudServicesProxy,
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
				  final COREServiceMethodCallbackSpec callbackSpec) {
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
