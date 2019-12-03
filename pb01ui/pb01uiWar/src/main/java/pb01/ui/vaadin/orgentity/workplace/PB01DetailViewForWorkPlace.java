package pb01.ui.vaadin.orgentity.workplace;

import pb01.ui.vaadin.orgentity.PB01DetailViewForOrgObjectBase;
import r01f.ui.i18n.UII18NService;
import x47b.model.oids.X47BOrganizationalOIDs.X47BWorkPlaceOID;
import x47b.model.org.X47BWorkPlace;

public class PB01DetailViewForWorkPlace
	 extends PB01DetailViewForOrgObjectBase<X47BWorkPlaceOID,X47BWorkPlace,
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