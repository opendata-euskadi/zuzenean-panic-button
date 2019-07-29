package x47b.model.org.summaries;

import r01f.objectstreamer.annotations.MarshallType;
import x47b.model.oids.X47BOrganizationalIDs.X47BWorkPlaceID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BWorkPlaceOID;
import x47b.model.org.X47BWorkPlace;

@MarshallType(as="summarizedWorkPlace")
public class X47BSummarizedWorkPlace 
	 extends X47BSummarizedOrganizationalModelObjectBase<X47BWorkPlaceOID,X47BWorkPlaceID,X47BWorkPlace,
	 											 		 X47BSummarizedWorkPlace> {

	private static final long serialVersionUID = -4373243410730886004L;
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR & BUILDER
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BSummarizedWorkPlace() {
		super(X47BWorkPlace.class);
	}
	public static X47BSummarizedWorkPlace create() {
		return new X47BSummarizedWorkPlace();
	}
}
