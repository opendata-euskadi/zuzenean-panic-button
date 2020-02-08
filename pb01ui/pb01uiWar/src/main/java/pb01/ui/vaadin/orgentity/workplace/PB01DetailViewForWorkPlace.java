package pb01.ui.vaadin.orgentity.workplace;

import pb01.ui.vaadin.orgentity.PB01DetailViewForOrgObjectBase;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AWorkPlaceOID;
import pb01a.model.org.PB01AWorkPlace;
import r01f.ui.i18n.UII18NService;

public class PB01DetailViewForWorkPlace
	 extends PB01DetailViewForOrgObjectBase<PB01AWorkPlaceOID,PB01AWorkPlace,
	 										PB01ViewObjForWorkPlace> {

	private static final long serialVersionUID = 1763832401233570646L;

/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01DetailViewForWorkPlace(final UII18NService i18n) {
		super(i18n,
			  PB01ViewObjForWorkPlace.class);
	}
}