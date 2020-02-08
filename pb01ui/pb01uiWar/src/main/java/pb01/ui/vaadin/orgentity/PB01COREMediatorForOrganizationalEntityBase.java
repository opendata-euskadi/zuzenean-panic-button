package pb01.ui.vaadin.orgentity;

import lombok.extern.slf4j.Slf4j;
import pb01a.client.api.PB01APanicButtonClientAPI;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgObjectOID;
import pb01a.model.org.PB01AOrganizationalPersistableObject;
import r01f.types.dirtytrack.DirtyTrackAdapter;
import r01f.ui.coremediator.UICOREMediatorForPersistableObjectBase;
import r01f.ui.coremediator.UICOREMediatorSubscriber;

@Slf4j
public abstract class PB01COREMediatorForOrganizationalEntityBase<O extends PB01AOrgObjectOID,M extends PB01AOrganizationalPersistableObject<O,?>>
	 		  extends UICOREMediatorForPersistableObjectBase<O,M,
	 		  												 PB01APanicButtonClientAPI> {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01COREMediatorForOrganizationalEntityBase(final PB01APanicButtonClientAPI api) {
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
