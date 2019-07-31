package pb01.ui.vaadin.orgentity.orgdivision;

import lombok.experimental.Accessors;
import pb01.ui.vaadin.orgentity.PB01ViewObjForOrganizationalEntityBase;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionOID;
import x47b.model.org.X47BOrgDivision;

@Accessors(prefix="_")
public class PB01ViewObjForOrgDivision
	 extends PB01ViewObjForOrganizationalEntityBase<X47BOrgDivisionOID,X47BOrgDivisionID,X47BOrgDivision> {

	private static final long serialVersionUID = -944690627430244570L;
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTORS
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01ViewObjForOrgDivision(final X47BOrgDivision obj) {
		super(obj);
	}
	public static final PB01ViewObjForOrgDivision from(final X47BOrgDivision obj) {
		PB01ViewObjForOrgDivision viewObj = new PB01ViewObjForOrgDivision(obj);
		return viewObj;
	}
}
