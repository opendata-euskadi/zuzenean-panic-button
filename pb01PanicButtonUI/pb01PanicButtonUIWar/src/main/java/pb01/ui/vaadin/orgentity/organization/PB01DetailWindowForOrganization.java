package pb01.ui.vaadin.orgentity.organization;

import javax.inject.Inject;

import pb01.ui.vaadin.orgentity.PB01DetailWindowForOrganizationalEntityBase;
import r01f.ui.i18n.UII18NService;
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
}
