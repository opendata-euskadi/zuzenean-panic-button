package pb01.ui.vaadin.orgentity.organization;

import lombok.experimental.Accessors;
import pb01.ui.vaadin.orgentity.PB01ViewObjForOrganizationalEntityBase;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrganizationID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrganizationOID;
import pb01a.model.org.PB01AOrganization;

@Accessors(prefix="_")
public class PB01ViewObjForOrganization
	 extends PB01ViewObjForOrganizationalEntityBase<PB01AOrganizationOID,PB01AOrganizationID,PB01AOrganization> {

	private static final long serialVersionUID = -2501624299666610701L;

/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTORS
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01ViewObjForOrganization(final PB01AOrganization obj) {
		super(obj);
	}
	public static final PB01ViewObjForOrganization from(final PB01AOrganization obj) {
		final PB01ViewObjForOrganization viewObj = new PB01ViewObjForOrganization(obj);
		return viewObj;
	}
}
