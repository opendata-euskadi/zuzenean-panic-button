package pb01.ui.vaadin.orgentity;

import r01f.locale.Language;
import r01f.ui.viewobject.UIViewObjectBase;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgObjectID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgObjectOID;
import x47b.model.org.X47BOrgObjectRef;
import x47b.model.org.X47BOrganizationalPersistableObject;

public abstract class PB01ViewObjForOrganizationalEntityBase<O extends X47BOrgObjectOID,ID extends X47BOrgObjectID<O>,M extends X47BOrganizationalPersistableObject<O,ID>>
	          extends UIViewObjectBase<M> {
	private static final long serialVersionUID = -3003439851622231243L;
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTORS
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01ViewObjForOrganizationalEntityBase(final M obj) {
		super(obj);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  OID & ID
/////////////////////////////////////////////////////////////////////////////////////////
	public static final String OID_FIELD = "oid";

	public O getOid() {
		return _wrappedModelObject.getOid();
	}
	public void setOid(final O oid) {
		_wrappedModelObject.setOid(oid);
	}

	public static final String ID_FIELD = "id";

	public ID getId() {
		return _wrappedModelObject.getId();
	}
	public void setId(final ID id) {
		_wrappedModelObject.setId(id);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  NAME
/////////////////////////////////////////////////////////////////////////////////////////
	public static final String NAME_ES_FIELD = "nameES";

	public String getNameES() {
		return _wrappedModelObject.getName().getInOrNull(Language.SPANISH);
	}
	public void setNameES(final String value) {
		_wrappedModelObject.getName().setIn(Language.SPANISH,value);
	}

	public static final String NAME_EU_FIELD = "nameEU";

	public String getNameEU() {
		return _wrappedModelObject.getName().getInOrNull(Language.BASQUE);
	}
	public void setNameEU(final String value) {
		_wrappedModelObject.getName().setIn(Language.BASQUE,value);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	PHONES & EMails
/////////////////////////////////////////////////////////////////////////////////////////
	public static final String PHONES_FIELD = "phones";

	public String getPhones() {
		return _wrappedModelObject.getPhonesAsString();
	}
	public void setPhones(final String phonesStr) {
		_wrappedModelObject.setPhonesFromString(phonesStr);
	}

	public static final String EMAIL_FIELD = "emails";

	public String getEmails() {
		return _wrappedModelObject.getEmailsAsString();
	}
	public void setEmails(final String emailsStr) {
		_wrappedModelObject.setEmailsFromString(emailsStr);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BOrgObjectRef<O,ID> getRef() {
		return new X47BOrgObjectRef<>(_wrappedModelObject.getOid(),_wrappedModelObject.getId());
	}
}
