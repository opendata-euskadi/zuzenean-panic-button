package pb01.ui.vaadin.orgentity.orgdivisionservice;

import javax.inject.Inject;

import pb01.ui.vaadin.orgentity.PB01DetailWindowForOrgObjectVisitors.PB01OrgObjectDetailWinForCreateVisitor;
import pb01.ui.vaadin.orgentity.PB01DetailWindowForOrgObjectVisitors.PB01OrgObjectDetailWinForEditVisitor;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionID;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrganizationID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionServiceOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrganizationOID;
import pb01a.model.org.PB01AOrgDivisionService;
import pb01a.model.org.PB01AOrgObjectRef;
import pb01.ui.vaadin.orgentity.PB01DetailWindowForOrganizationalEntityBase;
import r01f.ui.i18n.UII18NService;
import r01f.ui.presenter.UIPresenterSubscriber;

public class PB01DetailWindowForOrgDivisionService
	 extends PB01DetailWindowForOrganizationalEntityBase<PB01AOrgDivisionServiceOID,PB01AOrgDivisionService,
	 													 PB01ViewObjForOrgDivisionService,
	 													 PB01COREMediatorForOrgDivisionService,
	 													 PB01PresenterForOrgDivisionServiceDetailView,
	 													 PB01DetailViewForOrgDivisionService> {
	private static final long serialVersionUID = -4481398065564182519L;
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public PB01DetailWindowForOrgDivisionService(final UII18NService i18n,
											     final PB01PresenterForOrgDivisionServiceDetailView detailViewPresenter) {
		super(i18n,
			  i18n1 -> new PB01DetailViewForOrgDivisionService(i18n1),	// view factory
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
							final UIPresenterSubscriber<PB01ViewObjForOrgDivisionService> saveSubscriber) {			// what to do after saving
		_saveSubscriber = saveSubscriber;
		_deleteSubscriber = null;		// cannot delete from a create window

		// create a new org division
		PB01AOrgDivisionService obj = new PB01AOrgDivisionService();
		obj.setOid(PB01AOrgDivisionServiceOID.supply());
		obj.setOrgRef(orgRef);
		obj.setOrgDivisionRef(orgDivRef);

		// create the view object
		_viewObj = PB01ViewObjForOrgDivisionService.from(obj);

		// bind the view object to the view
		_detailView.bindViewTo(_viewObj);

		// set the buttons status
		_btnAcepCancDelete.setCreatingNewRecordStatus();
	}
}
