package pb01.ui.vaadin.orgentity.orgdivisionservice;

import lombok.experimental.Accessors;
import pb01.ui.vaadin.orgentity.PB01ViewObjForOrganizationalEntityBase;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionServiceID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceOID;
import x47b.model.org.X47BOrgDivisionService;

@Accessors(prefix="_")
public class PB01ViewObjForOrgDivisionService
	 extends PB01ViewObjForOrganizationalEntityBase<X47BOrgDivisionServiceOID,X47BOrgDivisionServiceID,X47BOrgDivisionService> {

	private static final long serialVersionUID = -944690627430244570L;
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTORS
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01ViewObjForOrgDivisionService(final X47BOrgDivisionService obj) {
		super(obj);
	}
	public static final PB01ViewObjForOrgDivisionService from(final X47BOrgDivisionService obj) {
		PB01ViewObjForOrgDivisionService viewObj = new PB01ViewObjForOrgDivisionService(obj);
		return viewObj;
	}
}
