package x47b.model.org;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import r01f.debug.Debuggable;
import r01f.guids.CommonOIDs.UserCode;
import r01f.objectstreamer.annotations.MarshallField;
import r01f.objectstreamer.annotations.MarshallField.MarshallFieldAsXml;
import r01f.objectstreamer.annotations.MarshallType;
import r01f.util.types.Strings;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionServiceID;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionServiceLocationID;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrganizationID;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrganizationServiceLocationGroupID;
import x47b.model.oids.X47BServiceIDs.X47BTerminalID;

@MarshallType(as="source")
@Accessors(prefix="_")
@NoArgsConstructor
public class X47BWorkPlaceOrgData 
  implements Serializable,
  			 Debuggable {

	private static final long serialVersionUID = -3082746450860012424L;
/////////////////////////////////////////////////////////////////////////////////////////
//  FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	@MarshallField(as="orgId",
		   	   	   whenXml=@MarshallFieldAsXml(attr=true))
	@Getter @Setter private X47BOrganizationID _orgId;
	
	@MarshallField(as="serviceId",
			   	   whenXml=@MarshallFieldAsXml(attr=true))
	@Getter @Setter private X47BOrgDivisionServiceID _serviceId;
	
	@MarshallField(as="locationGroupId",
			   	   whenXml=@MarshallFieldAsXml(attr=true))
	@Getter @Setter private X47BOrganizationServiceLocationGroupID _locationGroupId;
	
	@MarshallField(as="locationId",
			   	   whenXml=@MarshallFieldAsXml(attr=true))
	@Getter @Setter private X47BOrgDivisionServiceLocationID _locationId;
	
	@MarshallField(as="userCode",
			   	   whenXml=@MarshallFieldAsXml(attr=true))
	@Getter @Setter private UserCode _userCode;
	
	@MarshallField(as="terminalId",
			   	   whenXml=@MarshallFieldAsXml(attr=true))
	@Getter @Setter private X47BTerminalID _terminalId;
/////////////////////////////////////////////////////////////////////////////////////////
//  
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public CharSequence debugInfo() {
		return Strings.customized("{}/{}/{}/{}/{}:{}",
								  _orgId,_serviceId,_locationGroupId,_locationId,_userCode,_terminalId);
	}
}
