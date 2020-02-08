package pb01a.model.org.summaries;

import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrganizationID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrganizationOID;
import pb01a.model.org.PB01AOrganization;
import r01f.objectstreamer.annotations.MarshallType;


@MarshallType(as="summarizedOrg")
public class PB01ASummarizedOrganization
	 extends PB01ASummarizedOrganizationalModelObjectBase<PB01AOrganizationOID,PB01AOrganizationID,PB01AOrganization,
	 											 		 PB01ASummarizedOrganization> {

	private static final long serialVersionUID = -514447944990677006L;
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR & BUILDER
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01ASummarizedOrganization() {
		super(PB01AOrganization.class);
	}
	public static PB01ASummarizedOrganization create() {
		return new PB01ASummarizedOrganization();
	}
}
