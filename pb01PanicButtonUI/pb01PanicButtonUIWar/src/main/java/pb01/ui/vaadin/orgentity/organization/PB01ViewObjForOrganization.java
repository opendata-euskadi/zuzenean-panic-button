package pb01.ui.vaadin.orgentity.organization;

import lombok.experimental.Accessors;
import pb01.ui.vaadin.orgentity.PB01ViewObjForOrganizationalEntityBase;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrganizationID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrganizationOID;
import x47b.model.org.X47BOrganization;

@Accessors(prefix="_")
public class PB01ViewObjForOrganization
	 extends PB01ViewObjForOrganizationalEntityBase<X47BOrganizationOID,X47BOrganizationID,X47BOrganization> {

	private static final long serialVersionUID = -2501624299666610701L;

/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTORS
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01ViewObjForOrganization(final X47BOrganization obj) {
		super(obj);
	}
	public static final PB01ViewObjForOrganization from(final X47BOrganization obj) {
		PB01ViewObjForOrganization viewObj = new PB01ViewObjForOrganization(obj);
		return viewObj;
	}
}
