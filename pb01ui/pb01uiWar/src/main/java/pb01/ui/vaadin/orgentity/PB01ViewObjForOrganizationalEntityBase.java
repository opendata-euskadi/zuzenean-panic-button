package pb01.ui.vaadin.orgentity;

import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgObjectID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgObjectOID;
import pb01a.model.org.PB01AOrgObjectRef;
import pb01a.model.org.PB01AOrganizationalPersistableObject;
import r01f.locale.Language;
import r01f.ui.viewobject.UIViewObjectBase;

public abstract class PB01ViewObjForOrganizationalEntityBase<O extends PB01AOrgObjectOID,ID extends PB01AOrgObjectID<O>,M extends PB01AOrganizationalPersistableObject<O,ID>>
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
	public PB01AOrgObjectRef<O,ID> getRef() {
		return new PB01AOrgObjectRef<>(_wrappedModelObject.getOid(),_wrappedModelObject.getId());
	}
}
