package pb01.ui.vaadin.orgentity.orgdivisionservicelocation;

import pb01.ui.vaadin.orgentity.PB01DetailViewForOrgObjectBase;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionServiceLocationOID;
import pb01a.model.org.PB01AOrgDivisionServiceLocation;
import r01f.ui.i18n.UII18NService;

public class PB01DetailViewForOrgDivisionServiceLocation
	 extends PB01DetailViewForOrgObjectBase<PB01AOrgDivisionServiceLocationOID,PB01AOrgDivisionServiceLocation,
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