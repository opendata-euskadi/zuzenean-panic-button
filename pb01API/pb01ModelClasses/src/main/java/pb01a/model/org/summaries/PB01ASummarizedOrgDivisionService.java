package pb01a.model.org.summaries;

import lombok.experimental.Accessors;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionServiceID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionServiceOID;
import pb01a.model.org.PB01AOrgDivisionService;
import r01f.objectstreamer.annotations.MarshallType;

@MarshallType(as="summarizedOrgDivisionService")
@Accessors(prefix="_")
public class PB01ASummarizedOrgDivisionService
	 extends PB01ASummarizedOrganizationalModelObjectBase<PB01AOrgDivisionServiceOID,PB01AOrgDivisionServiceID,PB01AOrgDivisionService,
	 											 		 PB01ASummarizedOrgDivisionService> {

	private static final long serialVersionUID = -4373243410730886004L;
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR & BUILDER
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01ASummarizedOrgDivisionService() {
		super(PB01AOrgDivisionService.class);
	}
	public static PB01ASummarizedOrgDivisionService create() {
		return new PB01ASummarizedOrgDivisionService();
	}
}
