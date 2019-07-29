package x47b.model.org.summaries;

import r01f.objectstreamer.annotations.MarshallType;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrganizationID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrganizationOID;
import x47b.model.org.X47BOrganization;


@MarshallType(as="summarizedOrg")
public class X47BSummarizedOrganization 
	 extends X47BSummarizedOrganizationalModelObjectBase<X47BOrganizationOID,X47BOrganizationID,X47BOrganization,
	 											 			   X47BSummarizedOrganization> {
	
	private static final long serialVersionUID = -514447944990677006L;
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR & BUILDER
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BSummarizedOrganization() {
		super(X47BOrganization.class);
	}
	public static X47BSummarizedOrganization create() {
		return new X47BSummarizedOrganization();
	}
}
