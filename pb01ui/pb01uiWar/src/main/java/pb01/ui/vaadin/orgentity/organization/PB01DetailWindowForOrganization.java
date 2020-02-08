package pb01.ui.vaadin.orgentity.organization;

import javax.inject.Inject;

import pb01.ui.vaadin.orgentity.PB01DetailWindowForOrgObjectVisitors.PB01OrgObjectDetailWinForCreateVisitor;
import pb01.ui.vaadin.orgentity.PB01DetailWindowForOrgObjectVisitors.PB01OrgObjectDetailWinForEditVisitor;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrganizationOID;
import pb01a.model.org.PB01AOrganization;
import pb01.ui.vaadin.orgentity.PB01DetailWindowForOrganizationalEntityBase;
import r01f.ui.i18n.UII18NService;
import r01f.ui.presenter.UIPresenterSubscriber;

public class PB01DetailWindowForOrganization
	 extends PB01DetailWindowForOrganizationalEntityBase<PB01AOrganizationOID,PB01AOrganization,
	 													 PB01ViewObjForOrganization,
	 													 PB01COREMediatorForOrganization,
	 													 PB01PresenterForOrganizationDetailView,
	 													 PB01DetailViewForOrganization> {
	private static final long serialVersionUID = -4481398065564182519L;
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public PB01DetailWindowForOrganization(final UII18NService i18n,
										   final PB01PresenterForOrganizationDetailView detailViewPresenter) {
		super(i18n,
			  i18n1 -> new PB01DetailViewForOrganization(i18n1),	// view factory
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
	public void forCreating(final UIPresenterSubscriber<PB01ViewObjForOrganization> saveSubscriber) {			// what to do after saving
		_saveSubscriber = saveSubscriber;
		_deleteSubscriber = null;		// cannot delete from a create window

		// create a new org
		PB01AOrganization obj = new PB01AOrganization();
		obj.setOid(PB01AOrganizationOID.supply());

		// create the view object
		_viewObj = PB01ViewObjForOrganization.from(obj);

		// bind the view object to the view
		_detailView.bindViewTo(_viewObj);

		// set the buttons status
		_btnAcepCancDelete.setCreatingNewRecordStatus();
	}
}
