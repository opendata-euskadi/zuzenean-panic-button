package pb01.ui.vaadin.orgentity.workplace;

import lombok.experimental.Accessors;
import pb01.ui.vaadin.orgentity.PB01ViewObjForOrganizationalEntityBase;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AWorkPlaceID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AWorkPlaceOID;
import pb01a.model.org.PB01AWorkPlace;

@Accessors(prefix="_")
public class PB01ViewObjForWorkPlace
	 extends PB01ViewObjForOrganizationalEntityBase<PB01AWorkPlaceOID,PB01AWorkPlaceID,PB01AWorkPlace> {

	private static final long serialVersionUID = -944690627430244570L;
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTORS
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01ViewObjForWorkPlace(final PB01AWorkPlace obj) {
		super(obj);
	}
	public static final PB01ViewObjForWorkPlace from(final PB01AWorkPlace obj) {
		final PB01ViewObjForWorkPlace viewObj = new PB01ViewObjForWorkPlace(obj);
		return viewObj;
	}
}
