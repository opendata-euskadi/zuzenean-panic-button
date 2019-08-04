package pb01.ui.vaadin.orgentity.organization;

import pb01.ui.vaadin.orgentity.PB01DetailViewForOrgObjectBase;
import r01f.ui.i18n.UII18NService;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrganizationOID;
import x47b.model.org.X47BOrganization;

public class PB01DetailViewForOrganization
	 extends PB01DetailViewForOrgObjectBase<X47BOrganizationOID,X47BOrganization,
	 										PB01ViewObjForOrganization> {

	private static final long serialVersionUID = 1763832401233570646L;

/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01DetailViewForOrganization(final UII18NService i18n) {
		super(i18n,
			  PB01ViewObjForOrganization.class);
	}
}