package pb01.ui.vaadin.orgentity.orgdivision;

import pb01.ui.vaadin.orgentity.PB01DetailViewForOrgObjectBase;
import r01f.ui.i18n.UII18NService;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionOID;
import x47b.model.org.X47BOrgDivision;

public class PB01DetailViewForOrgDivision
	 extends PB01DetailViewForOrgObjectBase<X47BOrgDivisionOID,X47BOrgDivision,
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