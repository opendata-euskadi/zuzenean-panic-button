package pb01a.model.org;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionServiceID;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionServiceLocationID;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrganizationID;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrganizationServiceLocationGroupID;
import pb01a.model.oids.PB01AServiceIDs.PB01ATerminalID;
import r01f.debug.Debuggable;
import r01f.guids.CommonOIDs.UserCode;
import r01f.objectstreamer.annotations.MarshallField;
import r01f.objectstreamer.annotations.MarshallField.MarshallFieldAsXml;
import r01f.objectstreamer.annotations.MarshallType;
import r01f.util.types.Strings;

@MarshallType(as="source")
@Accessors(prefix="_")
@NoArgsConstructor
public class PB01AWorkPlaceOrgData 
  implements Serializable,
  			 Debuggable {

	private static final long serialVersionUID = -3082746450860012424L;
/////////////////////////////////////////////////////////////////////////////////////////
//  FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	@MarshallField(as="orgId",
		   	   	   whenXml=@MarshallFieldAsXml(attr=true))
	@Getter @Setter private PB01AOrganizationID _orgId;
	
	@MarshallField(as="serviceId",
			   	   whenXml=@MarshallFieldAsXml(attr=true))
	@Getter @Setter private PB01AOrgDivisionServiceID _serviceId;
	
	@MarshallField(as="locationGroupId",
			   	   whenXml=@MarshallFieldAsXml(attr=true))
	@Getter @Setter private PB01AOrganizationServiceLocationGroupID _locationGroupId;
	
	@MarshallField(as="locationId",
			   	   whenXml=@MarshallFieldAsXml(attr=true))
	@Getter @Setter private PB01AOrgDivisionServiceLocationID _locationId;
	
	@MarshallField(as="userCode",
			   	   whenXml=@MarshallFieldAsXml(attr=true))
	@Getter @Setter private UserCode _userCode;
	
	@MarshallField(as="terminalId",
			   	   whenXml=@MarshallFieldAsXml(attr=true))
	@Getter @Setter private PB01ATerminalID _terminalId;
/////////////////////////////////////////////////////////////////////////////////////////
//  
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public CharSequence debugInfo() {
		return Strings.customized("{}/{}/{}/{}/{}:{}",
								  _orgId,_serviceId,_locationGroupId,_locationId,_userCode,_terminalId);
	}
}
