package pb01.ui.vaadin.alarmevent;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import r01f.ui.viewobject.UIViewObjectBase;
import x47b.model.X47BAlarmEvent;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionID;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionServiceID;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionServiceLocationID;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrganizationID;
import x47b.model.oids.X47BOrganizationalIDs.X47BWorkPlaceID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceLocationOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrganizationOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BWorkPlaceOID;
import x47b.model.oids.X47BPanicButtonOIDs.X47BAlarmEventOID;

@Accessors(prefix="_")
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
    public Date getDateTime() {
        return _wrappedModelObject.getDateTime();
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
    @Getter @Setter private String _organizationName;
/////////////////////////////////////////////////////////////////////////////////////////
//	DIVISION
/////////////////////////////////////////////////////////////////////////////////////////
    public X47BOrgDivisionOID getDivisionOid() {
        return _wrappedModelObject.getDivision() != null ? _wrappedModelObject.getDivision().getOid()
                                                         : null;
    }
    public X47BOrgDivisionID getDivisionId() {
        return _wrappedModelObject.getDivision() != null ? _wrappedModelObject.getDivision().getId()
                                                         : null;
    }
    @Getter @Setter private String _divisionName;
/////////////////////////////////////////////////////////////////////////////////////////
//	SERVICE
/////////////////////////////////////////////////////////////////////////////////////////
    public X47BOrgDivisionServiceOID getServiceOid() {
        return _wrappedModelObject.getService() != null ? _wrappedModelObject.getService().getOid()
                                                        : null;
    }
    public X47BOrgDivisionServiceID getServiceId() {
        return _wrappedModelObject.getService() != null ? _wrappedModelObject.getService().getId()
                                                        : null;
    }
    @Getter @Setter private String _serviceName;
/////////////////////////////////////////////////////////////////////////////////////////
//	LOCATION
/////////////////////////////////////////////////////////////////////////////////////////
    public X47BOrgDivisionServiceLocationOID getLocationOid() {
        return _wrappedModelObject.getLocation() != null ? _wrappedModelObject.getLocation().getOid()
                                                         : null;
    }
    public X47BOrgDivisionServiceLocationID getLocationId() {
        return _wrappedModelObject.getLocation() != null ? _wrappedModelObject.getLocation().getId()
                                                         : null;
    }
    @Getter @Setter private String _locationName;
/////////////////////////////////////////////////////////////////////////////////////////
//	WORKPLACE
/////////////////////////////////////////////////////////////////////////////////////////
    public X47BWorkPlaceOID getWorkPlaceOid() {
        return _wrappedModelObject.getWorkPlace() != null ? _wrappedModelObject.getWorkPlace().getOid()
                                                          : null;
    }
    public X47BWorkPlaceID getWorkPlaceId() {
        return _wrappedModelObject.getWorkPlace() != null ? _wrappedModelObject.getWorkPlace().getId()
                                                          : null;
    }
    @Getter @Setter private String _workPlaceName;
}
