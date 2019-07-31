package pb01.ui.vaadin.orgentity.orgdivision;

import javax.inject.Inject;

import pb01.ui.vaadin.orgentity.PB01DetailWindowForOrganizationalEntityBase;
import r01f.ui.i18n.UII18NService;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionOID;
import x47b.model.org.X47BOrgDivision;

public class PB01DetailWindowForOrgDivision
	 extends PB01DetailWindowForOrganizationalEntityBase<X47BOrgDivisionOID,X47BOrgDivision,
	 													 PB01ViewObjForOrgDivision,
	 													 PB01COREMediatorForOrgDivision,
	 													 PB01DetailPresenterForOrgDivision,
	 													 PB01DetailViewForOrgDivision> {
	private static final long serialVersionUID = -4481398065564182519L;
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	protected PB01DetailWindowForOrgDivision(final UII18NService i18n,
											  final PB01DetailPresenterForOrgDivision detailViewPresenter) {
		super(i18n,
			  i18n1 -> new PB01DetailViewForOrgDivision(i18n1),	// view factory
			  detailViewPresenter);
	}
}
