package pb01.ui.vaadin.alarmevent;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import pb01a.model.PB01AAlarmEvent;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionID;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionServiceID;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionServiceLocationID;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrganizationID;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AWorkPlaceID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionServiceLocationOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionServiceOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrganizationOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AWorkPlaceOID;
import pb01a.model.oids.PB01APanicButtonOIDs.PB01AAlarmEventOID;
import r01f.ui.viewobject.UIViewObjectBase;

@Accessors(prefix="_")
public class PB01ViewAlarmEvent
     extends UIViewObjectBase<PB01AAlarmEvent> {

    private static final long serialVersionUID = 4731351923113134775L;
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
    public PB01ViewAlarmEvent(final PB01AAlarmEvent obj) {
        super(obj);
    }
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
    public PB01AAlarmEventOID getOid() {
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
    public PB01AOrganizationOID getOrganizationOid() {
        return _wrappedModelObject.getOrganization() != null ? _wrappedModelObject.getOrganization().getOid()
                                                             : null;
    }
    public PB01AOrganizationID getOrganizationId() {
        return _wrappedModelObject.getOrganization() != null ? _wrappedModelObject.getOrganization().getId()
                                                             : null;
    }
    @Getter @Setter private String _organizationName;
/////////////////////////////////////////////////////////////////////////////////////////
//	DIVISION
/////////////////////////////////////////////////////////////////////////////////////////
    public PB01AOrgDivisionOID getDivisionOid() {
        return _wrappedModelObject.getDivision() != null ? _wrappedModelObject.getDivision().getOid()
                                                         : null;
    }
    public PB01AOrgDivisionID getDivisionId() {
        return _wrappedModelObject.getDivision() != null ? _wrappedModelObject.getDivision().getId()
                                                         : null;
    }
    @Getter @Setter private String _divisionName;
/////////////////////////////////////////////////////////////////////////////////////////
//	SERVICE
/////////////////////////////////////////////////////////////////////////////////////////
    public PB01AOrgDivisionServiceOID getServiceOid() {
        return _wrappedModelObject.getService() != null ? _wrappedModelObject.getService().getOid()
                                                        : null;
    }
    public PB01AOrgDivisionServiceID getServiceId() {
        return _wrappedModelObject.getService() != null ? _wrappedModelObject.getService().getId()
                                                        : null;
    }
    @Getter @Setter private String _serviceName;
/////////////////////////////////////////////////////////////////////////////////////////
//	LOCATION
/////////////////////////////////////////////////////////////////////////////////////////
    public PB01AOrgDivisionServiceLocationOID getLocationOid() {
        return _wrappedModelObject.getLocation() != null ? _wrappedModelObject.getLocation().getOid()
                                                         : null;
    }
    public PB01AOrgDivisionServiceLocationID getLocationId() {
        return _wrappedModelObject.getLocation() != null ? _wrappedModelObject.getLocation().getId()
                                                         : null;
    }
    @Getter @Setter private String _locationName;
/////////////////////////////////////////////////////////////////////////////////////////
//	WORKPLACE
/////////////////////////////////////////////////////////////////////////////////////////
    public PB01AWorkPlaceOID getWorkPlaceOid() {
        return _wrappedModelObject.getWorkPlace() != null ? _wrappedModelObject.getWorkPlace().getOid()
                                                          : null;
    }
    public PB01AWorkPlaceID getWorkPlaceId() {
        return _wrappedModelObject.getWorkPlace() != null ? _wrappedModelObject.getWorkPlace().getId()
                                                          : null;
    }
    @Getter @Setter private String _workPlaceName;
}
