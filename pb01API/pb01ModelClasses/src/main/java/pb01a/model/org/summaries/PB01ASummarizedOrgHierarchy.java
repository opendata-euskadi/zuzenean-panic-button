package pb01a.model.org.summaries;

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
public class PB01ASummarizedOrgHierarchy 
  implements Serializable {

	private static final long serialVersionUID = 8079707472402763241L;

/////////////////////////////////////////////////////////////////////////////////////////
//  FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	@MarshallField(as="organization")
	@Getter @Setter private PB01ASummarizedOrganization _organization;
	
	@MarshallField(as="division")
	@Getter @Setter private PB01ASummarizedOrgDivision _division;
	
	@MarshallField(as="service")
	@Getter @Setter private PB01ASummarizedOrgDivisionService _service;
	
	@MarshallField(as="location")
	@Getter @Setter private PB01ASummarizedOrgDivisionServiceLocation _location;
}
