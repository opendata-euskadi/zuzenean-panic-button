package pb01.ui.vaadin.orgentity.orgdivisionservice;

import lombok.experimental.Accessors;
import pb01.ui.vaadin.orgentity.PB01ViewObjForOrganizationalEntityBase;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionServiceID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionServiceOID;
import pb01a.model.org.PB01AOrgDivisionService;

@Accessors(prefix="_")
public class PB01ViewObjForOrgDivisionService
	 extends PB01ViewObjForOrganizationalEntityBase<PB01AOrgDivisionServiceOID,PB01AOrgDivisionServiceID,PB01AOrgDivisionService> {

	private static final long serialVersionUID = -944690627430244570L;
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTORS
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01ViewObjForOrgDivisionService(final PB01AOrgDivisionService obj) {
		super(obj);
	}
	public static final PB01ViewObjForOrgDivisionService from(final PB01AOrgDivisionService obj) {
		PB01ViewObjForOrgDivisionService viewObj = new PB01ViewObjForOrgDivisionService(obj);
		return viewObj;
	}
}
