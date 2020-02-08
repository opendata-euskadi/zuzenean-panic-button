package pb01.ui.vaadin.orgentity.orgdivision;

import pb01.ui.vaadin.orgentity.PB01DetailViewForOrgObjectBase;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionOID;
import pb01a.model.org.PB01AOrgDivision;
import r01f.ui.i18n.UII18NService;

public class PB01DetailViewForOrgDivision
	 extends PB01DetailViewForOrgObjectBase<PB01AOrgDivisionOID,PB01AOrgDivision,
	 										PB01ViewObjForOrgDivision> {

	private static final long serialVersionUID = 1763832401233570646L;

/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01DetailViewForOrgDivision(final UII18NService i18n) {
		super(i18n,
			  PB01ViewObjForOrgDivision.class);
	}
}