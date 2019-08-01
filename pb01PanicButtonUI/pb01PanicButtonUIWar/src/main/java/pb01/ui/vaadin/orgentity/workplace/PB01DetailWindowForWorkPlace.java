package pb01.ui.vaadin.orgentity.workplace;

import javax.inject.Inject;

import pb01.ui.vaadin.orgentity.PB01DetailWindowForOrganizationalEntityBase;
import r01f.ui.i18n.UII18NService;
import r01f.ui.presenter.UIPresenterSubscriber;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionID;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionServiceID;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionServiceLocationID;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrganizationID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceLocationOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrganizationOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BWorkPlaceOID;
import x47b.model.org.X47BOrganizationalObjectRef;
import x47b.model.org.X47BWorkPlace;

public class PB01DetailWindowForWorkPlace
	 extends PB01DetailWindowForOrganizationalEntityBase<X47BWorkPlaceOID,X47BWorkPlace,
	 													 PB01ViewObjForWorkPlace,
	 													 PB01COREMediatorForWorkPlace,
	 													 PB01DetailPresenterForWorkPlace,
	 													 PB01DetailViewForWorkPlace> {
	private static final long serialVersionUID = -4481398065564182519L;
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	protected PB01DetailWindowForWorkPlace(final UII18NService i18n,
										   final PB01DetailPresenterForWorkPlace detailViewPresenter) {
		super(i18n,
			  i18n1 -> new PB01DetailViewForWorkPlace(i18n1),	// view factory
			  detailViewPresenter);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	CREATING
/////////////////////////////////////////////////////////////////////////////////////////
	public void forCreating(final X47BOrganizationalObjectRef<X47BOrganizationOID,X47BOrganizationID> orgRef,
							final X47BOrganizationalObjectRef<X47BOrgDivisionOID,X47BOrgDivisionID> orgDivRef,
							final X47BOrganizationalObjectRef<X47BOrgDivisionServiceOID,X47BOrgDivisionServiceID> orgDivSrvcRef,
							final X47BOrganizationalObjectRef<X47BOrgDivisionServiceLocationOID,X47BOrgDivisionServiceLocationID> orgDivSrvcLocRef,
							final UIPresenterSubscriber<PB01ViewObjForWorkPlace> saveSubscriber) {			// what to do after saving
		_saveSubscriber = saveSubscriber;
		_deleteSubscriber = null;		// cannot delete from a create window

		// create a new workPlace
		final X47BWorkPlace obj = new X47BWorkPlace();
		obj.setOid(X47BWorkPlaceOID.supply());
		obj.setOrgRef(orgRef);
		obj.setOrgDivisionRef(orgDivRef);
		obj.setOrgDivisionServiceRef(orgDivSrvcRef);
		obj.setOrgDivisionServiceLocationRef(orgDivSrvcLocRef);

		// create the view object
		_viewObj = PB01ViewObjForWorkPlace.from(obj);

		// bind the view object to the view
		_detailView.bindViewTo(_viewObj);

		// set the buttons status
		_btnAcepCancDelete.setCreatingNewRecordStatus();
	}
}
