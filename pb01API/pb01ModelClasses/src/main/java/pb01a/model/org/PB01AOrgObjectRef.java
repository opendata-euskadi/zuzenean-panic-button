package pb01a.model.org;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgObjectID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgObjectOID;
import r01f.debug.Debuggable;
import r01f.objectstreamer.annotations.MarshallField;
import r01f.objectstreamer.annotations.MarshallField.MarshallFieldAsXml;
import r01f.objectstreamer.annotations.MarshallType;
import r01f.util.types.Strings;

/**
 * A reference to another org model object
 * @param <O>
 * @param <I>
 */
@MarshallType(as="orgObjectRef")
@Accessors(prefix="_")
@NoArgsConstructor @AllArgsConstructor
public class PB01AOrgObjectRef<O extends PB01AOrgObjectOID,I extends PB01AOrgObjectID<O>>
  implements Serializable,
  			 Debuggable {

	private static final long serialVersionUID = 7645814230977999836L;

/////////////////////////////////////////////////////////////////////////////////////////
//  FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	@MarshallField(as="oid",
				   whenXml=@MarshallFieldAsXml(attr=true))
	@Getter @Setter private O _oid;

	@MarshallField(as="id",
				   whenXml=@MarshallFieldAsXml(attr=true))
	@Getter @Setter private I _id;
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public CharSequence debugInfo() {
		return Strings.customized("{}/{}",_oid,_id);
	}
}
