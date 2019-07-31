package pb01.ui.vaadin.orgentity;

import lombok.extern.slf4j.Slf4j;
import r01f.patterns.Factory;
import r01f.types.dirtytrack.DirtyTrackAdapter;
import r01f.ui.coremediator.UICOREMediatorForPersistableObjectBase;
import r01f.ui.coremediator.UICOREMediatorSubscriber;
import x47b.client.api.X47BPanicButtonClientAPI;
import x47b.model.oids.X47BOIDs.X47BPersistableObjectOID;
import x47b.model.org.X47BOrganizationalPersistableObject;

@Slf4j
public abstract class PB01COREMediatorForOrganizationalEntityBase<O extends X47BPersistableObjectOID,M extends X47BOrganizationalPersistableObject<O,?>>
	 		  extends UICOREMediatorForPersistableObjectBase<O,M,
	 		  												 X47BPanicButtonClientAPI> {
/////////////////////////////////////////////////////////////////////////////////////////
//	FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	private final Factory<M> _modelObjFactory;
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01COREMediatorForOrganizationalEntityBase(final X47BPanicButtonClientAPI api,
													   final Factory<M> modelObjFactory) {
		super(api);
		_modelObjFactory = modelObjFactory;
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  CRUD
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void load(final O oid,
					 final UICOREMediatorSubscriber<M> subscriber) {
		log.info("Load object with oid={} ({})",
				 oid,
				 oid == null ? "EXISTING" : "NEW");
		M loadedObj = null;
		try {
			if (oid == null) {
				loadedObj = _modelObjFactory.create();	// just create a bare new obj
			} else {
				loadedObj = this.load(oid);				// load the required object
			}
			// return
			subscriber.onSuccess(loadedObj);
		} catch(Throwable th) {
			// error
			subscriber.onError(th);
		}
	}
	@Override
	public void save(final M obj,
					 final UICOREMediatorSubscriber<M> subscriber) {
		boolean isNewObj = DirtyTrackAdapter.adapt(obj).getTrackingStatus()	// ((DirtyStateTrackable)obj).getTrackingStatus()
													   .isThisNew();		//							 .isThisNew();
		log.info("save object with oid={} {}",
				 obj.getOid(),
				 isNewObj ? "NEW" : "PREVIOUSLY EXISTING");
		try {
			M savedObj = this.save(obj);
			// return
			subscriber.onSuccess(savedObj);
		} catch(Throwable th) {
			// error
			subscriber.onError(th);
		}
	}
	@Override
	public void delete(final O oid,
					   final UICOREMediatorSubscriber<M> subscriber) {
		log.info("delete object with oid={}",oid);
		try {
			M deletedObj = this.delete(oid);
			// return
			subscriber.onSuccess(deletedObj);
		} catch(Throwable th) {
			// error
			subscriber.onError(th);
		}
	}
}
