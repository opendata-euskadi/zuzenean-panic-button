package pb01a.model.org.summaries;

import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionOID;
import pb01a.model.org.PB01AOrgDivision;
import r01f.objectstreamer.annotations.MarshallType;


@MarshallType(as="summarizedOrgDivision")
public class PB01ASummarizedOrgDivision 
	 extends PB01ASummarizedOrganizationalModelObjectBase<PB01AOrgDivisionOID,PB01AOrgDivisionID,PB01AOrgDivision,
	 											 		 PB01ASummarizedOrgDivision> {

	private static final long serialVersionUID = -3813974507633776222L;
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR & BUILDER
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01ASummarizedOrgDivision() {
		super(PB01AOrgDivision.class);
	}
	public static PB01ASummarizedOrgDivision create() {
		return new PB01ASummarizedOrgDivision();
	}
}
