package pb01.ui.vaadin.view;

import java.util.Collection;
import java.util.Date;

import r01f.types.Path;
import r01f.types.contact.EMail;
import r01f.types.contact.Phone;
import r01f.ui.viewobject.UIViewObjectBase;
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
import x47b.model.org.X47BOrgObjectType;
import x47b.model.org.summaries.X47BSummarizedOrgDivision;
import x47b.model.org.summaries.X47BSummarizedOrgDivisionService;
import x47b.model.org.summaries.X47BSummarizedOrgDivisionServiceLocation;
import x47b.model.org.summaries.X47BSummarizedOrganization;
import x47b.model.org.summaries.X47BSummarizedWorkPlace;
import x47b.model.search.X47BSearchResultItemForPanicButtonOrganizationalEntity;

public class PB01ViewObjForSearchResultItem
     extends UIViewObjectBase<X47BSearchResultItemForPanicButtonOrganizationalEntity> {

    private static final long serialVersionUID = 7691762961362543966L;

/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
    public PB01ViewObjForSearchResultItem(final X47BSearchResultItemForPanicButtonOrganizationalEntity wrappedModelObject) {
        super(wrappedModelObject);
    }
/////////////////////////////////////////////////////////////////////////////////////////
//	OBJECT TYPE
/////////////////////////////////////////////////////////////////////////////////////////
    public X47BOrgObjectType getOrgObjectType() {
        return this.getWrappedModelObject()
                   .getOrgObjectType();
    }
/////////////////////////////////////////////////////////////////////////////////////////
//	ORGANIZATION
/////////////////////////////////////////////////////////////////////////////////////////
    public X47BSummarizedOrganization getOrganization() {
    	return this.getWrappedModelObject()
                   .getOrganization();
    }
    public X47BOrganizationOID getOrganizationOid() {
        return this.getOrganization() != null ? this.getOrganization().getOid() : null;
    }
    public X47BOrganizationID getOrganizationId() {
        return this.getOrganization() != null ? this.getOrganization().getId() : null;
    }
    public String getOrganizationName() {
        return this.getOrganization() != null ? this.getOrganization().getName() : null;
    }
/////////////////////////////////////////////////////////////////////////////////////////
//	ORG DIVISION
/////////////////////////////////////////////////////////////////////////////////////////
    public X47BSummarizedOrgDivision getOrgDivision() {
        return this.getWrappedModelObject()
                   .getOrgDivision();
    }
    public X47BOrgDivisionOID getOrgDivisionOid() {
        return this.getOrgDivision() != null ? this.getOrgDivision().getOid() : null;
    }
    public X47BOrgDivisionID getOrgDivisionId() {
        return this.getOrgDivision() != null ? this.getOrgDivision().getId() : null;
    }
    public String getOrgDivisionName() {
        return this.getOrgDivision() != null ? this.getOrgDivision().getName() : null;
    }
/////////////////////////////////////////////////////////////////////////////////////////
//	ORG DIVISION SERVICE
/////////////////////////////////////////////////////////////////////////////////////////
    public X47BSummarizedOrgDivisionService getOrgDivisionService() {
        return this.getWrappedModelObject()
                   .getOrgDivisionService();
    }
    public X47BOrgDivisionServiceOID getOrgDivisionServiceOid() {
        return this.getOrgDivisionService() != null ? this.getOrgDivisionService().getOid() : null;
    }
    public X47BOrgDivisionServiceID getOrgDivisionServiceId() {
        return this.getOrgDivisionService() != null ? this.getOrgDivisionService().getId() : null;
    }
    public String getOrgDivisionServiceName() {
        return this.getOrgDivisionService() != null ? this.getOrgDivisionService().getName() : null;
    }
/////////////////////////////////////////////////////////////////////////////////////////
//	ORG DIVISION SERVICE LOCATION
/////////////////////////////////////////////////////////////////////////////////////////
    public X47BSummarizedOrgDivisionServiceLocation getOrgDivisionServiceLocation() {
        return this.getWrappedModelObject()
                   .getOrgDivisionServiceLocation();
    }
    public X47BOrgDivisionServiceLocationOID getOrgDivisionServiceLocationOid() {
        return this.getOrgDivisionServiceLocation() != null ? this.getOrgDivisionServiceLocation().getOid() : null;
    }
    public X47BOrgDivisionServiceLocationID getOrgDivisionServiceLocationId() {
        return this.getOrgDivisionServiceLocation() != null ? this.getOrgDivisionServiceLocation().getId() : null;
    }
    public String getOrgDivisionServiceLocationName() {
        return this.getOrgDivisionServiceLocation() != null ? this.getOrgDivisionServiceLocation().getName() : null;
    }
/////////////////////////////////////////////////////////////////////////////////////////
//	WORKPLACE
/////////////////////////////////////////////////////////////////////////////////////////
    public X47BSummarizedWorkPlace getWorkPlace() {
        return this.getWrappedModelObject()
                   .getWorkPlace();
    }
    public X47BWorkPlaceOID getWorkPlaceOid() {
        return this.getWorkPlace() != null ? this.getWorkPlace().getOid() : null;
    }
    public X47BWorkPlaceID getWorkPlaceId() {
        return this.getWorkPlace() != null ? this.getWorkPlace().getId() : null;    }
    public String getWorkPlaceName() {
        return this.getWorkPlace() != null ? this.getWorkPlace().getName() : null;
    }
/////////////////////////////////////////////////////////////////////////////////////////
//	HIERARCHY
/////////////////////////////////////////////////////////////////////////////////////////
    public Path getHierarchyPath() {
        return this.getWrappedModelObject()
                   .getHierarchyPath();
    }
/////////////////////////////////////////////////////////////////////////////////////////
//	ALARMS
/////////////////////////////////////////////////////////////////////////////////////////
    public long getAlarmRaiseCount() {
        return this.getWrappedModelObject()
                   .getAlarmRaiseCount();
    }
    public Date getLastAlarmRaiseDate() {
        return this.getWrappedModelObject()
                   .getLastAlarmRaiseDate();
    }
/////////////////////////////////////////////////////////////////////////////////////////
//	CONTACT
/////////////////////////////////////////////////////////////////////////////////////////
    public Collection<Phone> getPhones() {
        return this.getWrappedModelObject()
                   .getPhones();
    }
    public Collection<EMail> getEMails() {
        return this.getWrappedModelObject()
                   .getEmails();
    }
}
