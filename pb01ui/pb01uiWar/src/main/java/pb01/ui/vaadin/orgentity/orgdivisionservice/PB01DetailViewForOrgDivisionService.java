package pb01.ui.vaadin.orgentity.orgdivisionservice;

import pb01.ui.vaadin.orgentity.PB01DetailViewForOrgObjectBase;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionServiceOID;
import pb01a.model.org.PB01AOrgDivisionService;
import r01f.ui.i18n.UII18NService;

public class PB01DetailViewForOrgDivisionService
	 extends PB01DetailViewForOrgObjectBase<PB01AOrgDivisionServiceOID,PB01AOrgDivisionService,
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