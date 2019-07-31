package x47b.model.org.summaries;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import r01f.objectstreamer.annotations.MarshallField;
import r01f.objectstreamer.annotations.MarshallType;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionServiceID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceOID;
import x47b.model.org.X47BOrgDivisionService;

@MarshallType(as="summarizedOrgDivisionService")
@Accessors(prefix="_")
public class X47BSummarizedOrgDivisionService 
	 extends X47BSummarizedOrganizationalModelObjectBase<X47BOrgDivisionServiceOID,X47BOrgDivisionServiceID,X47BOrgDivisionService,
	 											 		 X47BSummarizedOrgDivisionService> {

	private static final long serialVersionUID = -4373243410730886004L;
/////////////////////////////////////////////////////////////////////////////////////////
//  FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	@MarshallField(as="procedure",escape=true)
	@Getter @Setter private String _procedure;
	
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR & BUILDER
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BSummarizedOrgDivisionService() {
		super(X47BOrgDivisionService.class);
	}
	public static X47BSummarizedOrgDivisionService create() {
		return new X47BSummarizedOrgDivisionService();
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BSummarizedOrgDivisionService managedProcedure(final String proc) {
		_procedure = proc;
		return this;
	}
}
