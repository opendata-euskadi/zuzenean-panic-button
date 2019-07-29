package x47b.model.org.summaries;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import r01f.objectstreamer.annotations.MarshallField;
import r01f.objectstreamer.annotations.MarshallField.MarshallFieldAsXml;
import x47b.model.X47BEntityObject;
import x47b.model.oids.X47BIDs.X47BModelObjectID;
import x47b.model.oids.X47BOIDs.X47BPersistableObjectOID;

@Accessors(prefix="_")
@RequiredArgsConstructor(access=AccessLevel.MODULE)
public abstract class X47BSummarizedModelObjectBase<O extends X47BPersistableObjectOID,I extends X47BModelObjectID<O>,M extends X47BEntityObject<O,I>,
													SELF_TYPE extends X47BSummarizedModelObjectBase<O,I,M,SELF_TYPE>>
    	   implements X47BSummarizedModelObject<O,I,M> {

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
