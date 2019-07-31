package x47b.model.org.summaries;

import r01f.objectstreamer.annotations.MarshallType;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionOID;
import x47b.model.org.X47BOrgDivision;


@MarshallType(as="summarizedOrgDivision")
public class X47BSummarizedOrgDivision 
	 extends X47BSummarizedOrganizationalModelObjectBase<X47BOrgDivisionOID,X47BOrgDivisionID,X47BOrgDivision,
	 											 		 X47BSummarizedOrgDivision> {

	private static final long serialVersionUID = -3813974507633776222L;
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR & BUILDER
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BSummarizedOrgDivision() {
		super(X47BOrgDivision.class);
	}
	public static X47BSummarizedOrgDivision create() {
		return new X47BSummarizedOrgDivision();
	}
}
