package pb01.ui.vaadin.orgentity.orgdivisionservicelocation;

import pb01.ui.vaadin.orgentity.PB01DetailViewForOrgEntityBase;
import r01f.ui.i18n.UII18NService;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceLocationOID;
import x47b.model.org.X47BOrgDivisionServiceLocation;

public class PB01DetailViewForOrgDivisionServiceLocation
	 extends PB01DetailViewForOrgEntityBase<X47BOrgDivisionServiceLocationOID,X47BOrgDivisionServiceLocation,
	 										PB01ViewObjForOrgDivisionServiceLocation> {

	private static final long serialVersionUID = 1763832401233570646L;

/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01DetailViewForOrgDivisionServiceLocation(final UII18NService i18n) {
		super(i18n,
			  PB01ViewObjForOrgDivisionServiceLocation.class);
	}
}