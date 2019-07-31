package pb01.ui.vaadin.orgentity.orgdivisionservicelocation;

import javax.inject.Inject;

import pb01.ui.vaadin.orgentity.PB01DetailWindowForOrganizationalEntityBase;
import r01f.ui.i18n.UII18NService;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceLocationOID;
import x47b.model.org.X47BOrgDivisionServiceLocation;

public class PB01DetailWindowForOrgDivisionServiceLocation
	 extends PB01DetailWindowForOrganizationalEntityBase<X47BOrgDivisionServiceLocationOID,X47BOrgDivisionServiceLocation,
	 													 PB01ViewObjForOrgDivisionServiceLocation,
	 													 PB01COREMediatorForOrgDivisionServiceLocation,
	 													 PB01DetailPresenterForOrgDivisionServiceLocation,
	 													 PB01DetailViewForOrgDivisionServiceLocation> {
	private static final long serialVersionUID = -4481398065564182519L;
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	protected PB01DetailWindowForOrgDivisionServiceLocation(final UII18NService i18n,
											  final PB01DetailPresenterForOrgDivisionServiceLocation detailViewPresenter) {
		super(i18n,
			  i18n1 -> new PB01DetailViewForOrgDivisionServiceLocation(i18n1),	// view factory
			  detailViewPresenter);
	}
}
