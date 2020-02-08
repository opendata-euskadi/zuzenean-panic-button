package pb01a.model.org.summaries;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import pb01a.model.PB01APersistableObject;
import pb01a.model.oids.PB01AIDs.PB01APersistableObjectID;
import pb01a.model.oids.PB01AOIDs.PB01APersistableObjectOID;
import r01f.objectstreamer.annotations.MarshallField;
import r01f.objectstreamer.annotations.MarshallField.MarshallFieldAsXml;

@Accessors(prefix="_")
@RequiredArgsConstructor(access=AccessLevel.MODULE)
public abstract class PB01ASummarizedObjectBase<O extends PB01APersistableObjectOID,I extends PB01APersistableObjectID<O>,M extends PB01APersistableObject<O,I>,
											   SELF_TYPE extends PB01ASummarizedObjectBase<O,I,M,SELF_TYPE>>
    	   implements PB01ASummarizedObject<O,I,M> {

	private static final long serialVersionUID = -8203773765925528330L;

/////////////////////////////////////////////////////////////////////////////////////////
//  NON SERIALIZABLE FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	@Getter private final transient Class<M> _modelObjectType;

/////////////////////////////////////////////////////////////////////////////////////////
//  SERIALIZABLE FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	@MarshallField(as="oid",
				   whenXml=@MarshallFieldAsXml(attr=true))
	@Getter @Setter private O _oid;

	@MarshallField(as="id",
			   	   whenXml=@MarshallFieldAsXml(attr=true))
	@Getter @Setter private I _id;
/////////////////////////////////////////////////////////////////////////////////////////
//  FLUENT API
/////////////////////////////////////////////////////////////////////////////////////////
	@SuppressWarnings("unchecked")
	public SELF_TYPE withOid(final O oid) {
		_oid = oid;
		return (SELF_TYPE)this;
	}
	@SuppressWarnings("unchecked")
	public SELF_TYPE withId(final I id) {
		_id = id;
		return (SELF_TYPE)this;
	}
}
