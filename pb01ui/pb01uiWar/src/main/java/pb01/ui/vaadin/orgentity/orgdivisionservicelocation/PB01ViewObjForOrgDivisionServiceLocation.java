package pb01.ui.vaadin.orgentity.orgdivisionservicelocation;

import lombok.experimental.Accessors;
import pb01.ui.vaadin.orgentity.PB01ViewObjForOrganizationalEntityBase;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionServiceLocationID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionServiceLocationOID;
import pb01a.model.org.PB01AOrgDivisionServiceLocation;

@Accessors(prefix="_")
public class PB01ViewObjForOrgDivisionServiceLocation
	 extends PB01ViewObjForOrganizationalEntityBase<PB01AOrgDivisionServiceLocationOID,PB01AOrgDivisionServiceLocationID,PB01AOrgDivisionServiceLocation> {

	private static final long serialVersionUID = -944690627430244570L;
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTORS
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01ViewObjForOrgDivisionServiceLocation(final PB01AOrgDivisionServiceLocation obj) {
		super(obj);
	}
	public static final PB01ViewObjForOrgDivisionServiceLocation from(final PB01AOrgDivisionServiceLocation obj) {
		PB01ViewObjForOrgDivisionServiceLocation viewObj = new PB01ViewObjForOrgDivisionServiceLocation(obj);
		return viewObj;
	}
}
