package pb01.ui.vaadin.orgentity.organization;

import javax.inject.Inject;

import pb01.ui.vaadin.orgentity.PB01DetailWindowForOrganizationalEntityBase;
import r01f.ui.i18n.UII18NService;
import r01f.ui.presenter.UIPresenterSubscriber;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrganizationOID;
import x47b.model.org.X47BOrganization;

public class PB01DetailWindowForOrganization
	 extends PB01DetailWindowForOrganizationalEntityBase<X47BOrganizationOID,X47BOrganization,
	 													 PB01ViewObjForOrganization,
	 													 PB01COREMediatorForOrganization,
	 													 PB01DetailPresenterForOrganization,
	 													 PB01DetailViewForOrganization> {
	private static final long serialVersionUID = -4481398065564182519L;
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	protected PB01DetailWindowForOrganization(final UII18NService i18n,
											  final PB01DetailPresenterForOrganization detailViewPresenter) {
		super(i18n,
			  i18n1 -> new PB01DetailViewForOrganization(i18n1),	// view factory
			  detailViewPresenter);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	CREATING
/////////////////////////////////////////////////////////////////////////////////////////
	public void forCreating(final UIPresenterSubscriber<PB01ViewObjForOrganization> saveSubscriber) {			// what to do after saving
		_saveSubscriber = saveSubscriber;
		_deleteSubscriber = null;		// cannot delete from a create window

		// create a new org
		X47BOrganization obj = new X47BOrganization();
		obj.setOid(X47BOrganizationOID.supply());

		// create the view object
		_viewObj = PB01ViewObjForOrganization.from(obj);

		// bind the view object to the view
		_detailView.bindViewTo(_viewObj);

		// set the buttons status
		_btnAcepCancDelete.setCreatingNewRecordStatus();
	}
}
