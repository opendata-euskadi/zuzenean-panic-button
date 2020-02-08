package pb01a.model.org.summaries;

import pb01a.model.oids.PB01AOrganizationalIDs.PB01AWorkPlaceID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AWorkPlaceOID;
import pb01a.model.org.PB01AWorkPlace;
import r01f.objectstreamer.annotations.MarshallType;

@MarshallType(as="summarizedWorkPlace")
public class PB01ASummarizedWorkPlace 
	 extends PB01ASummarizedOrganizationalModelObjectBase<PB01AWorkPlaceOID,PB01AWorkPlaceID,PB01AWorkPlace,
	 											 		 PB01ASummarizedWorkPlace> {

	private static final long serialVersionUID = -4373243410730886004L;
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR & BUILDER
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01ASummarizedWorkPlace() {
		super(PB01AWorkPlace.class);
	}
	public static PB01ASummarizedWorkPlace create() {
		return new PB01ASummarizedWorkPlace();
	}
}
