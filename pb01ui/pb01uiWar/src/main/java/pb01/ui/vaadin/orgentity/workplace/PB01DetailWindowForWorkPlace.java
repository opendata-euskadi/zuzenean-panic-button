package pb01.ui.vaadin.orgentity.workplace;

import javax.inject.Inject;

import pb01.ui.vaadin.orgentity.PB01DetailWindowForOrgObjectVisitors.PB01OrgObjectDetailWinForCreateVisitor;
import pb01.ui.vaadin.orgentity.PB01DetailWindowForOrgObjectVisitors.PB01OrgObjectDetailWinForEditVisitor;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionID;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionServiceID;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionServiceLocationID;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrganizationID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionServiceLocationOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionServiceOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrganizationOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AWorkPlaceOID;
import pb01a.model.org.PB01AOrgObjectRef;
import pb01a.model.org.PB01AWorkPlace;
import pb01.ui.vaadin.orgentity.PB01DetailWindowForOrganizationalEntityBase;
import r01f.ui.i18n.UII18NService;
import r01f.ui.presenter.UIPresenterSubscriber;

public class PB01DetailWindowForWorkPlace
	 extends PB01DetailWindowForOrganizationalEntityBase<PB01AWorkPlaceOID,PB01AWorkPlace,
	 													 PB01ViewObjForWorkPlace,
	 													 PB01COREMediatorForWorkPlace,
	 													 PB01PresenterForWorkPlaceDetailView,
	 													 PB01DetailViewForWorkPlace> {
	private static final long serialVersionUID = -4481398065564182519L;
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public PB01DetailWindowForWorkPlace(final UII18NService i18n,
										final PB01PresenterForWorkPlaceDetailView detailViewPresenter) {
		super(i18n,
			  i18n1 -> new PB01DetailViewForWorkPlace(i18n1),	// view factory
			  detailViewPresenter);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	OPEN
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void openForCreating(final PB01OrgObjectDetailWinForCreateVisitor openerVisitor) {
		openerVisitor.forCreatingNewOn(this);
	}
	@Override
	public void openForEdit(final PB01OrgObjectDetailWinForEditVisitor openerVisitor) {
		openerVisitor.forEditingExistentOn(this);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	CREATING
/////////////////////////////////////////////////////////////////////////////////////////
	public void forCreating(final PB01AOrgObjectRef<PB01AOrganizationOID,PB01AOrganizationID> orgRef,
							final PB01AOrgObjectRef<PB01AOrgDivisionOID,PB01AOrgDivisionID> orgDivRef,
							final PB01AOrgObjectRef<PB01AOrgDivisionServiceOID,PB01AOrgDivisionServiceID> orgDivSrvcRef,
							final PB01AOrgObjectRef<PB01AOrgDivisionServiceLocationOID,PB01AOrgDivisionServiceLocationID> orgDivSrvcLocRef,
							final UIPresenterSubscriber<PB01ViewObjForWorkPlace> saveSubscriber) {			// what to do after saving
		_saveSubscriber = saveSubscriber;
		_deleteSubscriber = null;		// cannot delete from a create window

		// create a new workPlace
		final PB01AWorkPlace obj = new PB01AWorkPlace();
		obj.setOid(PB01AWorkPlaceOID.supply());
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
