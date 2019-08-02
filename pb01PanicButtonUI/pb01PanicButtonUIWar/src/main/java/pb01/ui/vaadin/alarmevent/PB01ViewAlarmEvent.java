package pb01.ui.vaadin.alarmevent;

import java.util.Date;

import r01f.ui.viewobject.UIViewObjectBase;
import x47b.model.X47BAlarmEvent;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrganizationID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrganizationOID;
import x47b.model.oids.X47BPanicButtonOIDs.X47BAlarmEventOID;

public class PB01ViewAlarmEvent
	 extends UIViewObjectBase<X47BAlarmEvent> {

	private static final long serialVersionUID = 4731351923113134775L;
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01ViewAlarmEvent(final X47BAlarmEvent obj) {
		super(obj);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BAlarmEventOID getOid() {
		return _wrappedModelObject.getOid();
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	public Date getDate() {
		return _wrappedModelObject.getTimeStamp();
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	ORG
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BOrganizationOID getOrganizationOid() {
		return _wrappedModelObject.getOrganization() != null ? _wrappedModelObject.getOrganization().getOid()
															 : null;
	}
	public X47BOrganizationID getOrganizationId() {
		return _wrappedModelObject.getOrganization() != null ? _wrappedModelObject.getOrganization().getId()
															 : null;
	}
}
