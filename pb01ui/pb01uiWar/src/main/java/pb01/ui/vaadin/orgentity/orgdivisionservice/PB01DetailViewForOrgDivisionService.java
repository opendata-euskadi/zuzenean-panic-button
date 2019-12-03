package pb01.ui.vaadin.orgentity.orgdivisionservice;

import pb01.ui.vaadin.orgentity.PB01DetailViewForOrgObjectBase;
import r01f.ui.i18n.UII18NService;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceOID;
import x47b.model.org.X47BOrgDivisionService;

public class PB01DetailViewForOrgDivisionService
	 extends PB01DetailViewForOrgObjectBase<X47BOrgDivisionServiceOID,X47BOrgDivisionService,
	 										PB01ViewObjForOrgDivisionService> {

	private static final long serialVersionUID = 1763832401233570646L;

/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01DetailViewForOrgDivisionService(final UII18NService i18n) {
		super(i18n,
			  PB01ViewObjForOrgDivisionService.class);
	}
}