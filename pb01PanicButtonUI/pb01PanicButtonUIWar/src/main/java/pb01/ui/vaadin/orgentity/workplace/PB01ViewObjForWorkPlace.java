package pb01.ui.vaadin.orgentity.workplace;

import lombok.experimental.Accessors;
import pb01.ui.vaadin.orgentity.PB01ViewObjForOrganizationalEntityBase;
import x47b.model.oids.X47BOrganizationalIDs.X47BWorkPlaceID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BWorkPlaceOID;
import x47b.model.org.X47BWorkPlace;

@Accessors(prefix="_")
public class PB01ViewObjForWorkPlace
	 extends PB01ViewObjForOrganizationalEntityBase<X47BWorkPlaceOID,X47BWorkPlaceID,X47BWorkPlace> {

	private static final long serialVersionUID = -944690627430244570L;
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTORS
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01ViewObjForWorkPlace(final X47BWorkPlace obj) {
		super(obj);
	}
	public static final PB01ViewObjForWorkPlace from(final X47BWorkPlace obj) {
		final PB01ViewObjForWorkPlace viewObj = new PB01ViewObjForWorkPlace(obj);
		return viewObj;
	}
}
