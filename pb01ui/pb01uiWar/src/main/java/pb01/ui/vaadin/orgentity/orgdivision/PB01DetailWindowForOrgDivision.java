package pb01.ui.vaadin.orgentity.orgdivision;

import javax.inject.Inject;

import pb01.ui.vaadin.orgentity.PB01DetailWindowForOrgObjectVisitors.PB01OrgObjectDetailWinForCreateVisitor;
import pb01.ui.vaadin.orgentity.PB01DetailWindowForOrgObjectVisitors.PB01OrgObjectDetailWinForEditVisitor;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrganizationID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrganizationOID;
import pb01a.model.org.PB01AOrgDivision;
import pb01a.model.org.PB01AOrgObjectRef;
import pb01.ui.vaadin.orgentity.PB01DetailWindowForOrganizationalEntityBase;
import r01f.ui.i18n.UII18NService;
import r01f.ui.presenter.UIPresenterSubscriber;

public class PB01DetailWindowForOrgDivision
	 extends PB01DetailWindowForOrganizationalEntityBase<PB01AOrgDivisionOID,PB01AOrgDivision,
	 													 PB01ViewObjForOrgDivision,
	 													 PB01COREMediatorForOrgDivision,
	 													 PB01PresenterForOrgDivisionDetailView,
	 													 PB01DetailViewForOrgDivision> {
	private static final long serialVersionUID = -4481398065564182519L;
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public PB01DetailWindowForOrgDivision(final UII18NService i18n,
										  final PB01PresenterForOrgDivisionDetailView detailViewPresenter) {
		super(i18n,
			  i18n1 -> new PB01DetailViewForOrgDivision(i18n1),	// view factory
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
							final UIPresenterSubscriber<PB01ViewObjForOrgDivision> saveSubscriber) {			// what to do after saving
		_saveSubscriber = saveSubscriber;
		_deleteSubscriber = null;		// cannot delete from a create window

		// create a new org division
		final PB01AOrgDivision obj = new PB01AOrgDivision();
		obj.setOid(PB01AOrgDivisionOID.supply());
		obj.setOrgRef(orgRef);

		// create the view object
		_viewObj = PB01ViewObjForOrgDivision.from(obj);

		// bind the view object to the view
		_detailView.bindViewTo(_viewObj);

		// set the buttons status
		_btnAcepCancDelete.setCreatingNewRecordStatus();
	}
}
