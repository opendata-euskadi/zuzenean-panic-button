package pb01.ui.vaadin.view;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

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
import pb01a.model.org.PB01AOrgObjectType;
import pb01a.model.org.summaries.PB01ASummarizedOrgDivision;
import pb01a.model.org.summaries.PB01ASummarizedOrgDivisionService;
import pb01a.model.org.summaries.PB01ASummarizedOrgDivisionServiceLocation;
import pb01a.model.org.summaries.PB01ASummarizedOrganization;
import pb01a.model.org.summaries.PB01ASummarizedWorkPlace;
import pb01a.model.search.PB01ASearchResultItemForPanicButtonOrganizationalEntity;
import r01f.types.Path;
import r01f.types.contact.EMail;
import r01f.types.contact.Phone;
import r01f.ui.i18n.UII18NService;
import r01f.ui.viewobject.UIViewObjectBase;
import r01f.util.types.collections.CollectionUtils;

public class PB01ViewObjForSearchResultItem
     extends UIViewObjectBase<PB01ASearchResultItemForPanicButtonOrganizationalEntity> {

    private static final long serialVersionUID = 7691762961362543966L;

/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
    public PB01ViewObjForSearchResultItem(final PB01ASearchResultItemForPanicButtonOrganizationalEntity wrappedModelObject) {
        super(wrappedModelObject);
    }
/////////////////////////////////////////////////////////////////////////////////////////
//	OBJECT TYPE
/////////////////////////////////////////////////////////////////////////////////////////
    public PB01AOrgObjectType getOrgObjectType() {
        return this.getWrappedModelObject()
                   .getOrgObjectType();
    }
/////////////////////////////////////////////////////////////////////////////////////////
//	ORGANIZATION
/////////////////////////////////////////////////////////////////////////////////////////
    public PB01ASummarizedOrganization getOrganization() {
    	return this.getWrappedModelObject()
                   .getOrganization();
    }
    public PB01AOrganizationOID getOrganizationOid() {
        return this.getOrganization() != null ? this.getOrganization().getOid() : null;
    }
    public PB01AOrganizationID getOrganizationId() {
        return this.getOrganization() != null ? this.getOrganization().getId() : null;
    }
    public String getOrganizationName() {
        return this.getOrganization() != null ? this.getOrganization().getName() : null;
    }
    public String getOrganizationHint() {
    	return this.getOrganizationId() != null ? this.getOrganizationId().asString() : null;
    }
/////////////////////////////////////////////////////////////////////////////////////////
//	ORG DIVISION
/////////////////////////////////////////////////////////////////////////////////////////
    public PB01ASummarizedOrgDivision getOrgDivision() {
        return this.getWrappedModelObject()
                   .getOrgDivision();
    }
    public PB01AOrgDivisionOID getOrgDivisionOid() {
        return this.getOrgDivision() != null ? this.getOrgDivision().getOid() : null;
    }
    public PB01AOrgDivisionID getOrgDivisionId() {
        return this.getOrgDivision() != null ? this.getOrgDivision().getId() : null;
    }
    public String getOrgDivisionName() {
        return this.getOrgDivision() != null ? this.getOrgDivision().getName() : null;
    }
    public String getOrgDivisionHint() {
    	return this.getOrgDivisionId() != null ? this.getOrgDivisionId().asString() : null;
    }
/////////////////////////////////////////////////////////////////////////////////////////
//	ORG DIVISION SERVICE
/////////////////////////////////////////////////////////////////////////////////////////
    public PB01ASummarizedOrgDivisionService getOrgDivisionService() {
        return this.getWrappedModelObject()
                   .getOrgDivisionService();
    }
    public PB01AOrgDivisionServiceOID getOrgDivisionServiceOid() {
        return this.getOrgDivisionService() != null ? this.getOrgDivisionService().getOid() : null;
    }
    public PB01AOrgDivisionServiceID getOrgDivisionServiceId() {
        return this.getOrgDivisionService() != null ? this.getOrgDivisionService().getId() : null;
    }
    public String getOrgDivisionServiceName() {
        return this.getOrgDivisionService() != null ? this.getOrgDivisionService().getName() : null;
    }
    public String getOrgDivisionServiceHint() {
    	return this.getOrgDivisionServiceId() != null ? this.getOrgDivisionServiceId().asString() : null;
    }
/////////////////////////////////////////////////////////////////////////////////////////
//	ORG DIVISION SERVICE LOCATION
/////////////////////////////////////////////////////////////////////////////////////////
    public PB01ASummarizedOrgDivisionServiceLocation getOrgDivisionServiceLocation() {
        return this.getWrappedModelObject()
                   .getOrgDivisionServiceLocation();
    }
    public PB01AOrgDivisionServiceLocationOID getOrgDivisionServiceLocationOid() {
        return this.getOrgDivisionServiceLocation() != null ? this.getOrgDivisionServiceLocation().getOid() : null;
    }
    public PB01AOrgDivisionServiceLocationID getOrgDivisionServiceLocationId() {
        return this.getOrgDivisionServiceLocation() != null ? this.getOrgDivisionServiceLocation().getId() : null;
    }
    public String getOrgDivisionServiceLocationName() {
        return this.getOrgDivisionServiceLocation() != null ? this.getOrgDivisionServiceLocation().getName() : null;
    }
    public String getOrgDivisionServiceLocationHint() {
    	return this.getOrgDivisionServiceLocationId() != null ? this.getOrgDivisionServiceLocationId().asString() : null;
    }
/////////////////////////////////////////////////////////////////////////////////////////
//	WORKPLACE
/////////////////////////////////////////////////////////////////////////////////////////
    public PB01ASummarizedWorkPlace getWorkPlace() {
        return this.getWrappedModelObject()
                   .getWorkPlace();
    }
    public PB01AWorkPlaceOID getWorkPlaceOid() {
        return this.getWorkPlace() != null ? this.getWorkPlace().getOid() : null;
    }
    public PB01AWorkPlaceID getWorkPlaceId() {
        return this.getWorkPlace() != null ? this.getWorkPlace().getId() : null;    }
    public String getWorkPlaceName() {
        return this.getWorkPlace() != null ? this.getWorkPlace().getName() : null;
    }
    public String getWorkPlaceHint() {
    	return this.getWorkPlaceId() != null ? this.getWorkPlaceId().asString() : null;
    }
/////////////////////////////////////////////////////////////////////////////////////////
//	HIERARCHY
/////////////////////////////////////////////////////////////////////////////////////////
    public String getOrgHierarchyExplained() {
    	return this.getWrappedModelObject()
    			   .getOrgHierarchyExplained();
    }
    public Path getHierarchyPath() {
        return this.getWrappedModelObject()
                   .getHierarchyPath();
    }
    public int getOrgHierarchyLevel() {
    	return this.getWrappedModelObject()
    			   .getOrgHierarchyLevel();
    }
    public boolean isSameLevelAs(final PB01ViewObjForSearchResultItem other) {
    	return this.getWrappedModelObject()
    			   .isSameLevelAs(other.getWrappedModelObject());
    }
    public boolean isAncestorOf(final PB01ViewObjForSearchResultItem other) {
    	return this.getWrappedModelObject()
    			   .isAncestorOf(other.getWrappedModelObject());
    }
    public boolean isDescendantOf(final PB01ViewObjForSearchResultItem other) {
    	return this.getWrappedModelObject()
    			   .isDescendantOf(other.getWrappedModelObject());
    }
    public boolean isOrganization() {
    	return this.getWrappedModelObject()
    			   .isOrganization();
    }
    public boolean isOrgDivision() {
    	return this.getWrappedModelObject()
    			   .isOrgDivision();
    }
    public boolean isOrgDivisionService() {
    	return this.getWrappedModelObject()
    			   .isOrgDivisionService();
    }
    public boolean isOrgDivisionServiceLocation() {
    	return this.getWrappedModelObject()
    			   .isOrgDivisionServiceLocation();
    }
    public boolean isWorkPlace() {
    	return this.getWrappedModelObject()
    			   .isWorkPlace();
    }
/////////////////////////////////////////////////////////////////////////////////////////
//	ALARMS
/////////////////////////////////////////////////////////////////////////////////////////
    public long increaseAlarmRaiseCount(final int num) {
    	long currAlarmRaiseCount = this.getAlarmRaiseCount();
    	long newAlarmRaiseCount = currAlarmRaiseCount + num;
    	this.getWrappedModelObject()
    		.setAlarmRaiseCount(newAlarmRaiseCount);
    	return newAlarmRaiseCount;
    }
    public long getAlarmRaiseCount() {
        return this.getWrappedModelObject()
                   .getAlarmRaiseCount();
    }
    public Date getLastAlarmRaiseDate() {
        return this.getWrappedModelObject()
                   .getLastAlarmRaiseDate();
    }
    public String getAlarmRaiseCountAsString() {
    	return Long.toString(this.getAlarmRaiseCount());
    }
    public String getAlarmLastRaiseDateExplained(final UII18NService i18n) {
    	if (this.getAlarmRaiseCount() == 0) return i18n.getMessage("pb01.view.noAlarmsRaised");
    	// return something like:
    	//		"x alarms raised; last was at 2019/05/01-12:00"
    	return i18n.getMessage("pb01.view.lastAlarmRaised",
    						   this.getAlarmRaiseCount(),
    						   this.getLastAlarmRaiseDate());
    }
/////////////////////////////////////////////////////////////////////////////////////////
//	CONTACT
/////////////////////////////////////////////////////////////////////////////////////////
    public Collection<Phone> getPhones() {
        return this.getWrappedModelObject()
                   .getPhones();
    }
    public Collection<Phone> getEffectivePhones() {
    	return this.getWrappedModelObject()
    			   .getEffectivePhones();
    }
    public String getPhonesAsString() {
    	return this.getWrappedModelObject()
    			   .getPhonesAsString();
    }
    public String getEffectivePhonesAsString() {
    	return this.getWrappedModelObject()
    			   .getEffectivePhonesAsString();
    }
    public Collection<EMail> getEMails() {
        return this.getWrappedModelObject()
                   .getEmails();
    }
    public Collection<EMail> getEffectiveEMails() {
    	return this.getWrappedModelObject()
    			   .getEffectiveEMails();
    }
    public String getEMailsAsString() {
    	return this.getWrappedModelObject()
    			   .getEMailsAsString();
    }
    public int getEffectiveEmailCount() {
    	if (CollectionUtils.isNullOrEmpty(this.getWrappedModelObject()
    										  .getEffectiveEMails())) return 0;
    	return this.getWrappedModelObject()
		    		.getEffectiveEMails()
		    		.size();
    }
    public int getEffectivePhoneCount() {
    	if (CollectionUtils.isNullOrEmpty(this.getWrappedModelObject()
    										  .getEffectivePhones())) return 0;
    	return this.getWrappedModelObject()
		    		.getEffectivePhones()
		    		.size();
    }
    public String getEffectiveEMailsAsString() {
    	return this.getWrappedModelObject()
    			   .getEffectiveEMailsAsString();
    }
    public Map<PB01AOrgObjectType,Collection<EMail>> getEffectiveEmailsByOrgEntityType() {
    	return this.getWrappedModelObject()
    			   .getEffectiveEmailsByOrgEntityType();
    }
    public Map<PB01AOrgObjectType,Collection<Phone>> getEffectivePhonesByOrgEntityType() {
    	return this.getWrappedModelObject()
    			   .getEffectivePhonesByOrgEntityType();
    }
}
