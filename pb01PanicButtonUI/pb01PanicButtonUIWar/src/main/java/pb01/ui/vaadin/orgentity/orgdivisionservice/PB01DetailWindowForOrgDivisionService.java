package pb01.ui.vaadin.orgentity.orgdivisionservice;

import javax.inject.Inject;

import pb01.ui.vaadin.orgentity.PB01DetailWindowForOrganizationalEntityBase;
import r01f.ui.i18n.UII18NService;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceOID;
import x47b.model.org.X47BOrgDivisionService;

public class PB01DetailWindowForOrgDivisionService
	 extends PB01DetailWindowForOrganizationalEntityBase<X47BOrgDivisionServiceOID,X47BOrgDivisionService,
	 													 PB01ViewObjForOrgDivisionService,
	 													 PB01COREMediatorForOrgDivisionService,
	 													 PB01DetailPresenterForOrgDivisionService,
	 													 PB01DetailViewForOrgDivisionService> {
	private static final long serialVersionUID = -4481398065564182519L;
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	protected PB01DetailWindowForOrgDivisionService(final UII18NService i18n,
											  final PB01DetailPresenterForOrgDivisionService detailViewPresenter) {
		super(i18n,
			  i18n1 -> new PB01DetailViewForOrgDivisionService(i18n1),	// view factory
			  detailViewPresenter);
	}
}
