package pb01.ui.vaadin.orgentity.orgdivision;

import javax.inject.Inject;

import pb01.ui.vaadin.orgentity.PB01DetailWindowForOrgObjectVisitors.PB01OrgObjectDetailWinForCreateVisitor;
import pb01.ui.vaadin.orgentity.PB01DetailWindowForOrgObjectVisitors.PB01OrgObjectDetailWinForEditVisitor;
import pb01.ui.vaadin.orgentity.PB01DetailWindowForOrganizationalEntityBase;
import r01f.ui.i18n.UII18NService;
import r01f.ui.presenter.UIPresenterSubscriber;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrganizationID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrganizationOID;
import x47b.model.org.X47BOrgDivision;
import x47b.model.org.X47BOrgObjectRef;

public class PB01DetailWindowForOrgDivision
	 extends PB01DetailWindowForOrganizationalEntityBase<X47BOrgDivisionOID,X47BOrgDivision,
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
	public void forCreating(final X47BOrgObjectRef<X47BOrganizationOID,X47BOrganizationID> orgRef,
							final UIPresenterSubscriber<PB01ViewObjForOrgDivision> saveSubscriber) {			// what to do after saving
		_saveSubscriber = saveSubscriber;
		_deleteSubscriber = null;		// cannot delete from a create window

		// create a new org division
		final X47BOrgDivision obj = new X47BOrgDivision();
		obj.setOid(X47BOrgDivisionOID.supply());
		obj.setOrgRef(orgRef);

		// create the view object
		_viewObj = PB01ViewObjForOrgDivision.from(obj);

		// bind the view object to the view
		_detailView.bindViewTo(_viewObj);

		// set the buttons status
		_btnAcepCancDelete.setCreatingNewRecordStatus();
	}
}
