package pb01.ui.vaadin.orgentity.orgdivisionservicelocation;

import lombok.experimental.Accessors;
import pb01.ui.vaadin.orgentity.PB01ViewObjForOrganizationalEntityBase;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionServiceLocationID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceLocationOID;
import x47b.model.org.X47BOrgDivisionServiceLocation;

@Accessors(prefix="_")
public class PB01ViewObjForOrgDivisionServiceLocation
	 extends PB01ViewObjForOrganizationalEntityBase<X47BOrgDivisionServiceLocationOID,X47BOrgDivisionServiceLocationID,X47BOrgDivisionServiceLocation> {

	private static final long serialVersionUID = -944690627430244570L;
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTORS
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01ViewObjForOrgDivisionServiceLocation(final X47BOrgDivisionServiceLocation obj) {
		super(obj);
	}
	public static final PB01ViewObjForOrgDivisionServiceLocation from(final X47BOrgDivisionServiceLocation obj) {
		PB01ViewObjForOrgDivisionServiceLocation viewObj = new PB01ViewObjForOrgDivisionServiceLocation(obj);
		return viewObj;
	}
}
