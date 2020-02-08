package pb01.ui.vaadin.orgentity.organization;

import pb01.ui.vaadin.orgentity.PB01DetailViewForOrgObjectBase;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrganizationOID;
import pb01a.model.org.PB01AOrganization;
import r01f.ui.i18n.UII18NService;

public class PB01DetailViewForOrganization
	 extends PB01DetailViewForOrgObjectBase<PB01AOrganizationOID,PB01AOrganization,
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