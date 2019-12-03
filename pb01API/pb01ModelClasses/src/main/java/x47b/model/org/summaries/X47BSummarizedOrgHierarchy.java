package x47b.model.org.summaries;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import r01f.objectstreamer.annotations.MarshallField;
import r01f.objectstreamer.annotations.MarshallType;

/**
 * Encapsulates the org hierarchy data
 * <pre>
 * 		[organization]
 * 			 + [division]
 * 					+ [service]
 * 						 + [location]
 */
@MarshallType(as="summarizedOrgHierarchy")
@Accessors(prefix="_")
public class X47BSummarizedOrgHierarchy 
  implements Serializable {

	private static final long serialVersionUID = 8079707472402763241L;

/////////////////////////////////////////////////////////////////////////////////////////
//  FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	@MarshallField(as="organization")
	@Getter @Setter private X47BSummarizedOrganization _organization;
	
	@MarshallField(as="division")
	@Getter @Setter private X47BSummarizedOrgDivision _division;
	
	@MarshallField(as="service")
	@Getter @Setter private X47BSummarizedOrgDivisionService _service;
	
	@MarshallField(as="location")
	@Getter @Setter private X47BSummarizedOrgDivisionServiceLocation _location;
}
