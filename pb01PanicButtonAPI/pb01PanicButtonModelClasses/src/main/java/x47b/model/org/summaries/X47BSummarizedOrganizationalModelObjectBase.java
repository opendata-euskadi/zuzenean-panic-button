package x47b.model.org.summaries;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import r01f.objectstreamer.annotations.MarshallField;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgObjectID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgObjectOID;
import x47b.model.org.X47BOrganizationalPersistableObject;

@Accessors(prefix="_")
public abstract class X47BSummarizedOrganizationalModelObjectBase<O extends X47BOrgObjectOID,I extends X47BOrgObjectID<O>,M extends X47BOrganizationalPersistableObject<O,I>,
														  		  SELF_TYPE extends X47BSummarizedOrganizationalModelObjectBase<O,I,M,SELF_TYPE>>
			  extends X47BSummarizedObjectBase<O,I,M,SELF_TYPE>
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
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	public boolean is(final SELF_TYPE other) {
		if (other == null) return false;
		return this.getOid().is(other.getOid())
			&& this.getId().is(other.getId());
	}
}
