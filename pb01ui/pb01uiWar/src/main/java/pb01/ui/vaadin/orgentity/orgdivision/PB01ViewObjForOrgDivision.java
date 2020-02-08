package pb01.ui.vaadin.orgentity.orgdivision;

import lombok.experimental.Accessors;
import pb01.ui.vaadin.orgentity.PB01ViewObjForOrganizationalEntityBase;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionOID;
import pb01a.model.org.PB01AOrgDivision;

@Accessors(prefix="_")
public class PB01ViewObjForOrgDivision
	 extends PB01ViewObjForOrganizationalEntityBase<PB01AOrgDivisionOID,PB01AOrgDivisionID,PB01AOrgDivision> {

	private static final long serialVersionUID = -944690627430244570L;
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTORS
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01ViewObjForOrgDivision(final PB01AOrgDivision obj) {
		super(obj);
	}
	public static final PB01ViewObjForOrgDivision from(final PB01AOrgDivision obj) {
		PB01ViewObjForOrgDivision viewObj = new PB01ViewObjForOrgDivision(obj);
		return viewObj;
	}
}
