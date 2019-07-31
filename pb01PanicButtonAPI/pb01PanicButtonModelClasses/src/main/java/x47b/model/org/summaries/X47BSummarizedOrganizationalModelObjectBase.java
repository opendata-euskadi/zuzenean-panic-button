package x47b.model.org.summaries;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import r01f.objectstreamer.annotations.MarshallField;
import x47b.model.oids.X47BIDs.X47BPersistableObjectID;
import x47b.model.oids.X47BOIDs.X47BPersistableObjectOID;
import x47b.model.org.X47BOrganizationalPersistableObject;

@Accessors(prefix="_")
public abstract class X47BSummarizedOrganizationalModelObjectBase<O extends X47BPersistableObjectOID,I extends X47BPersistableObjectID<O>,M extends X47BOrganizationalPersistableObject<O,I>,
														  				SELF_TYPE extends X47BSummarizedOrganizationalModelObjectBase<O,I,M,SELF_TYPE>>
			  extends X47BSummarizedModelObjectBase<O,I,M,SELF_TYPE>
    	   implements X47BSummarizedOrganizationalObject<O,I,M> {

	private static final long serialVersionUID = 6718745572987398280L;
	
/////////////////////////////////////////////////////////////////////////////////////////
//  SERIALIZABLE FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	@MarshallField(as="name",escape=true)
	@Getter @Setter private String _name;
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	X47BSummarizedOrganizationalModelObjectBase(final Class<M> modelObjectType) {
		super(modelObjectType);
	}
	
/////////////////////////////////////////////////////////////////////////////////////////
//  FLUENT API
/////////////////////////////////////////////////////////////////////////////////////////
	@SuppressWarnings("unchecked")
	public SELF_TYPE named(final String name) {
		_name = name;
		return (SELF_TYPE)this;
	}
}
