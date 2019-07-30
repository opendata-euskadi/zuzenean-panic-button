package pb01.ui.vaadin.org;

import lombok.experimental.Accessors;
import r01f.locale.Language;
import r01f.ui.viewobject.UIViewObjectBase;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrganizationOID;
import x47b.model.org.X47BOrganization;

@Accessors(prefix="_")
public class PB01UIViewOrganization
	 extends UIViewObjectBase<X47BOrganization> {

	private static final long serialVersionUID = -2501624299666610701L;

/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTORS
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01UIViewOrganization(final X47BOrganization obj) {
		super(obj);
	}
	public static final PB01UIViewOrganization from(final X47BOrganization person) {
		PB01UIViewOrganization viewObj = new PB01UIViewOrganization(person);
		return viewObj;
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  OID
/////////////////////////////////////////////////////////////////////////////////////////
	public static final String OID_FIELD = "oid";

	public X47BOrganizationOID getOid() {
		return _wrappedModelObject.getOid();
	}
	public void setOid(final X47BOrganizationOID oid) {
		_wrappedModelObject.setOid(oid);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  NAME
/////////////////////////////////////////////////////////////////////////////////////////
	public static final String NAME_FIELD = "name";

	public String getNameES() {
		return _wrappedModelObject.getName().getInOrNull(Language.SPANISH);
	}
	public void setNameES(final String value) {
		_wrappedModelObject.getName().setIn(Language.SPANISH,value);
	}
	public String getNameEU() {
		return _wrappedModelObject.getName().getInOrNull(Language.BASQUE);
	}
	public void setNameEU(final String value) {
		_wrappedModelObject.getName().setIn(Language.BASQUE,value);
	}
}
