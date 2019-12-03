package x47b.model.org.summaries;

import java.util.Collection;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import r01f.objectstreamer.annotations.MarshallField;
import r01f.objectstreamer.annotations.MarshallField.MarshallFieldAsXml;
import r01f.types.contact.EMail;
import r01f.types.contact.Phone;
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

	@MarshallField(as="phones",
				   whenXml=@MarshallFieldAsXml(collectionElementName="phone"))
	@Getter @Setter private Collection<Phone> _phones;

	@MarshallField(as="emails",
				   whenXml=@MarshallFieldAsXml(collectionElementName="email"))
	@Getter @Setter private Collection<EMail> _emails;
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
	@SuppressWarnings("unchecked")
	public SELF_TYPE withPhones(final Collection<Phone> phones) {
		_phones = phones;
		return (SELF_TYPE)this;
	}
	@SuppressWarnings("unchecked")
	public SELF_TYPE withEmails(final Collection<EMail> emails) {
		_emails = emails;
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
