package x47b.model;

import lombok.Getter;
import lombok.Setter;
import r01f.objectstreamer.annotations.MarshallField;
import r01f.objectstreamer.annotations.MarshallField.MarshallFieldAsXml;
import r01f.objectstreamer.annotations.MarshallType;

@MarshallType(as="notifierResponse")
public class X47BNotifierResponse 
  implements X47BModelObject {

	private static final long serialVersionUID = 8321978328505367458L;

/////////////////////////////////////////////////////////////////////////////////////////
//	FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	@MarshallField(as="responseMessage",escape=true,
				   whenXml=@MarshallFieldAsXml(asParentElementValue=true))
	@Getter @Setter private String _responseMessage;

}
