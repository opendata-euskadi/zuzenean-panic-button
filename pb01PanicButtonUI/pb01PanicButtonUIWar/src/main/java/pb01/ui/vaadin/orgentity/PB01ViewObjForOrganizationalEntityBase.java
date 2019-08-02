package pb01.ui.vaadin.orgentity;

import r01f.locale.Language;
import r01f.ui.viewobject.UIViewObjectBase;
import x47b.model.oids.X47BIDs.X47BPersistableObjectID;
import x47b.model.oids.X47BOIDs.X47BPersistableObjectOID;
import x47b.model.org.X47BOrgObjectRef;
import x47b.model.org.X47BOrganizationalPersistableObject;

public abstract class PB01ViewObjForOrganizationalEntityBase<O extends X47BPersistableObjectOID,ID extends X47BPersistableObjectID<O>,M extends X47BOrganizationalPersistableObject<O,ID>>
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
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BOrgObjectRef<O,ID> getRef() {
		return new X47BOrgObjectRef<>(_wrappedModelObject.getOid(),_wrappedModelObject.getId());
	}
}
