package pb01a.model.org.summaries;

import java.util.Collection;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgObjectID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgObjectOID;
import pb01a.model.org.PB01AOrganizationalPersistableObject;
import r01f.objectstreamer.annotations.MarshallField;
import r01f.objectstreamer.annotations.MarshallField.MarshallFieldAsXml;
import r01f.types.contact.EMail;
import r01f.types.contact.Phone;

@Accessors(prefix="_")
public abstract class PB01ASummarizedOrganizationalModelObjectBase<O extends PB01AOrgObjectOID,I extends PB01AOrgObjectID<O>,M extends PB01AOrganizationalPersistableObject<O,I>,
														  		  SELF_TYPE extends PB01ASummarizedOrganizationalModelObjectBase<O,I,M,SELF_TYPE>>
			  extends PB01ASummarizedObjectBase<O,I,M,SELF_TYPE>
    	   implements PB01ASummarizedOrganizationalObject<O,I,M> {

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
	PB01ASummarizedOrganizationalModelObjectBase(final Class<M> modelObjectType) {
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
