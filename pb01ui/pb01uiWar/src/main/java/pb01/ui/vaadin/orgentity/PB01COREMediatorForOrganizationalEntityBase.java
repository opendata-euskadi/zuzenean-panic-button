package pb01.ui.vaadin.orgentity;

import lombok.extern.slf4j.Slf4j;
import r01f.types.dirtytrack.DirtyTrackAdapter;
import r01f.ui.coremediator.UICOREMediatorForPersistableObjectBase;
import r01f.ui.coremediator.UICOREMediatorSubscriber;
import x47b.client.api.X47BPanicButtonClientAPI;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgObjectOID;
import x47b.model.org.X47BOrganizationalPersistableObject;

@Slf4j
public abstract class PB01COREMediatorForOrganizationalEntityBase<O extends X47BOrgObjectOID,M extends X47BOrganizationalPersistableObject<O,?>>
	 		  extends UICOREMediatorForPersistableObjectBase<O,M,
	 		  												 X47BPanicButtonClientAPI> {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01COREMediatorForOrganizationalEntityBase(final X47BPanicButtonClientAPI api) {
		super(api);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  CRUD
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void load(final O oid,
					 final UICOREMediatorSubscriber<M> subscriber) {
		log.info("Load object with oid={}",
				 oid);
		M loadedObj = null;
		try {
			loadedObj = this.load(oid);				// load the required object
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
