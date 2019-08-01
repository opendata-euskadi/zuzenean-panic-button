package x47b.model.org.summaries;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import r01f.objectstreamer.annotations.MarshallField;
import r01f.objectstreamer.annotations.MarshallField.MarshallFieldAsXml;
import x47b.model.X47BPersistableObject;
import x47b.model.oids.X47BIDs.X47BPersistableObjectID;
import x47b.model.oids.X47BOIDs.X47BPersistableObjectOID;

@Accessors(prefix="_")
@RequiredArgsConstructor(access=AccessLevel.MODULE)
public abstract class X47BSummarizedObjectBase<O extends X47BPersistableObjectOID,I extends X47BPersistableObjectID<O>,M extends X47BPersistableObject<O,I>,
											   SELF_TYPE extends X47BSummarizedObjectBase<O,I,M,SELF_TYPE>>
    	   implements X47BSummarizedObject<O,I,M> {

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
