package x47b.model.search;

import java.util.Collection;
import java.util.Date;

import com.google.common.collect.Sets;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import r01f.locale.LanguageTexts;
import r01f.model.search.SearchResultItemForModelObjectBase;
import r01f.objectstreamer.annotations.MarshallField;
import r01f.objectstreamer.annotations.MarshallField.DateFormat;
import r01f.objectstreamer.annotations.MarshallField.MarshallDateFormat;
import r01f.objectstreamer.annotations.MarshallField.MarshallFieldAsXml;
import r01f.objectstreamer.annotations.MarshallType;
import r01f.types.Path;
import r01f.types.contact.EMail;
import r01f.types.contact.Phone;
import r01f.util.types.Paths;
import x47b.model.X47BPersistableObject;
import x47b.model.org.X47BOrgObjectType;
import x47b.model.org.X47BOrganization;
import x47b.model.org.X47BOrganizationalPersistableObjectBase;
import x47b.model.org.X47BWorkPlace;
import x47b.model.org.summaries.X47BSummarizedOrgDivision;
import x47b.model.org.summaries.X47BSummarizedOrgDivisionService;
import x47b.model.org.summaries.X47BSummarizedOrgDivisionServiceLocation;
import x47b.model.org.summaries.X47BSummarizedOrganization;
import x47b.model.org.summaries.X47BSummarizedWorkPlace;

/**
 * A search result item for an organizational entity like {@link X47BOrganization}, {@link X47BLocation} or {@link X47BWorkPlace}
 */
@MarshallType(as="searchResultItemForOrganizationalEntity")
@Accessors(prefix="_")
@NoArgsConstructor
public class X47BSearchResultItemForPanicButtonOrganizationalEntity
     extends SearchResultItemForModelObjectBase<X47BPersistableObject<?,?>> {

    private static final long serialVersionUID = 4169587420774250028L;
/////////////////////////////////////////////////////////////////////////////////////////
//  FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
    @MarshallField(as="org")
    @Getter @Setter private X47BSummarizedOrganization _organization;

    @MarshallField(as="orgDivision")
    @Getter @Setter private X47BSummarizedOrgDivision _orgDivision;

    @MarshallField(as="orgDivisionService")
    @Getter @Setter private X47BSummarizedOrgDivisionService _orgDivisionService;

    @MarshallField(as="orgDivisionServiceLocation")
    @Getter @Setter private X47BSummarizedOrgDivisionServiceLocation _orgDivisionServiceLocation;

    @MarshallField(as="workPlace")
    @Getter @Setter private X47BSummarizedWorkPlace _workPlace;

    @MarshallField(as="name")
    @Getter @Setter private LanguageTexts _name;

    @MarshallField(as="lastAlarmRaiseDate",
                   dateFormat=@MarshallDateFormat(use=DateFormat.CUSTOM,format="dd/MM/yyyy hh:mm:ss"))
    @Getter @Setter private Date _lastAlarmRaiseDate;

    @MarshallField(as="alarmRaiseCount")
    @Getter @Setter private long _alarmRaiseCount;

    @MarshallField(as="phones",
                   whenXml=@MarshallFieldAsXml(collectionElementName="phone"))
    @Getter @Setter private Collection<Phone> _phones;

    @MarshallField(as="emails",
                   whenXml=@MarshallFieldAsXml(collectionElementName="email"))
    @Getter @Setter private Collection<EMail> _emails;
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
    public String getOrgHierarchyExplained() {
        StringBuilder description = new StringBuilder();
        if (_organization != null) {
            description.append(_organization.getName());
        }
        if (_orgDivision != null) {
            description.append(" / ")
                       .append(_orgDivision.getName());
        }
        if (_orgDivisionService != null) {
            description.append(" / ")
                       .append(_orgDivisionService.getName());
        }
        if (_orgDivisionServiceLocation != null) {
            description.append(" / ")
                       .append(_orgDivisionServiceLocation.getName());
        }
        if (_workPlace != null) {
            description.append(" / ")
                       .append(_workPlace.getName());
        }
        return description.toString();
    }
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
    public Path getHierarchyPath() {
        return Paths.forPaths().join(_organization.getId(),
                                     _orgDivision.getId(),
                                     _orgDivisionService.getId(),
                                     _orgDivisionServiceLocation.getId(),
                                     _workPlace.getId());
    }
    public X47BOrgObjectType getOrgObjectType() {
        return X47BOrgObjectType.of(this);
    }
    public int getOrgHierarchyLevel() {
        return this.getOrgObjectType()
                   .getOrgHierarchyLevel();
    }
    public boolean isSameLevelAs(final X47BSearchResultItemForPanicButtonOrganizationalEntity other) {
        if (this.getOrgObjectType() != other.getOrgObjectType()) return false;
        return (this.isOrganization() && other.isOrganization())
            || (this.isOrgDivision() && other.isOrgDivision())
            || (this.isOrgDivisionService() && other.isOrgDivisionService())
            || (this.isOrgDivisionServiceLocation() && other.isOrgDivisionServiceLocation())
            || (this.isWorkPlace() && other.isWorkPlace());
    }
    public boolean isAncestorOf(final X47BSearchResultItemForPanicButtonOrganizationalEntity other) {
        if (this.isWorkPlace()
         || other.isOrganization()) return false;
        if (this.getOrgHierarchyLevel() > other.getOrgHierarchyLevel()) return false;
        if (this.isSameLevelAs(other))  return false;

        boolean outAncestor = false;
        if (other.isWorkPlace()) {
            if (this.isOrgDivisionServiceLocation()) {
                outAncestor = other.getOrgDivisionServiceLocation().is(this.getOrgDivisionServiceLocation())
                           && other.getOrgDivisionService().is(this.getOrgDivisionService())
                           && other.getOrgDivision().is(this.getOrgDivision())
                           && other.getOrganization().is(this.getOrganization());
            } else if (this.isOrgDivisionService()) {
                outAncestor = other.getOrgDivisionService().is(this.getOrgDivisionService())
                           && other.getOrgDivision().is(this.getOrgDivision())
                           && other.getOrganization().is(this.getOrganization());
            } else if (this.isOrgDivision()) {
                outAncestor = other.getOrgDivision().is(this.getOrgDivision())
                           && other.getOrganization().is(this.getOrganization());
            } else if (this.isOrganization()) {
                outAncestor = other.getOrganization().is(this.getOrganization());
            }
        }
        else if (other.isOrgDivisionServiceLocation()) {
            if (this.isOrgDivisionService()) {
                outAncestor = other.getOrgDivisionService().is(this.getOrgDivisionService())
                           && other.getOrgDivision().is(this.getOrgDivision())
                           && other.getOrganization().is(this.getOrganization());
            } else if (this.isOrgDivision()) {
                outAncestor = other.getOrgDivision().is(this.getOrgDivision())
                           && other.getOrganization().is(this.getOrganization());
            } else if (this.isOrganization()) {
                outAncestor = other.getOrganization().is(this.getOrganization());
            }
        }
        else if (other.isOrgDivisionService()) {
            if (this.isOrgDivision()) {
                outAncestor = other.getOrgDivision().is(this.getOrgDivision())
                           && other.getOrganization().is(this.getOrganization());
            } else if (this.isOrganization()) {
                outAncestor = other.getOrganization().is(this.getOrganization());
            }
        }
        else if (other.isOrgDivision()) {
            if (this.isOrganization()) {
                outAncestor = other.getOrganization().is(this.getOrganization());
            }
        }
        return outAncestor;
    }
    public boolean isDescendantOf(final X47BSearchResultItemForPanicButtonOrganizationalEntity other) {
        if (this.isOrganization()
         || other.isWorkPlace()) return false;
        if (this.getOrgHierarchyLevel() < other.getOrgHierarchyLevel()) return false;
        if (other.isSameLevelAs(this))  return false;

        boolean outDescendant = false;
        if (this.isWorkPlace()) {
            if (other.isOrgDivisionServiceLocation()) {
                outDescendant = other.getOrgDivisionServiceLocation().is(this.getOrgDivisionServiceLocation())
                             && other.getOrgDivisionService().is(this.getOrgDivisionService())
                             && other.getOrgDivision().is(this.getOrgDivision())
                             && other.getOrganization().is(this.getOrganization());
            } else if (other.isOrgDivisionService()) {
                outDescendant = other.getOrgDivisionService().is(this.getOrgDivisionService())
                           && other.getOrgDivision().is(this.getOrgDivision())
                           && other.getOrganization().is(this.getOrganization());
            } else if (other.isOrgDivision()) {
                outDescendant = other.getOrgDivision().is(this.getOrgDivision())
                           && other.getOrganization().is(this.getOrganization());
            } else if (other.isOrganization()) {
                outDescendant = other.getOrganization().is(this.getOrganization());
            }
        }
        else if (this.isOrgDivisionServiceLocation()) {
            if (other.isOrgDivisionService()) {
                outDescendant = other.getOrgDivisionService().is(this.getOrgDivisionService())
                           && other.getOrgDivision().is(this.getOrgDivision())
                           && other.getOrganization().is(this.getOrganization());
            } else if (other.isOrgDivision()) {
                outDescendant = other.getOrgDivision().is(this.getOrgDivision())
                           && other.getOrganization().is(this.getOrganization());
            } else if (other.isOrganization()) {
                outDescendant = other.getOrganization().is(this.getOrganization());
            }
        }
        else if (this.isOrgDivisionService()) {
            if (other.isOrgDivision()) {
                outDescendant = other.getOrgDivision().is(this.getOrgDivision())
                           && other.getOrganization().is(this.getOrganization());
            } else if (other.isOrganization()) {
                outDescendant = other.getOrganization().is(this.getOrganization());
            }
        }
        else if (this.isOrgDivision()) {
            if (other.isOrganization()) {
                outDescendant = other.getOrganization().is(this.getOrganization());
            }
        }
        return outDescendant;
    }
    public boolean isOrganization() {
        return this.getOrgObjectType() == X47BOrgObjectType.ORGANIZATION;
    }
    public boolean isOrgDivision() {
        return this.getOrgObjectType() == X47BOrgObjectType.ORG_DIVISION;
    }
    public boolean isOrgDivisionService() {
        return this.getOrgObjectType() == X47BOrgObjectType.ORG_DIVISION_SERVICE;
    }
    public boolean isOrgDivisionServiceLocation() {
        return this.getOrgObjectType() == X47BOrgObjectType.ORG_DIVISION_SERVICE_LOCATION;
    }
    public boolean isWorkPlace() {
        return this.getOrgObjectType() == X47BOrgObjectType.WORKPLACE;
    }
/////////////////////////////////////////////////////////////////////////////////////////
//	PHONES & EMAILS
/////////////////////////////////////////////////////////////////////////////////////////
    public String getPhonesAsString() {
    	return X47BOrganizationalPersistableObjectBase.phonesAsString(this.getPhones());
    }
    public String getEMailsAsString() {
    	return X47BOrganizationalPersistableObjectBase.emailsAsString(this.getEmails());
    }
    public Collection<Phone> getEffectivePhones() {
    	Collection<Phone> orgPhones = this.getOrganization() != null ? this.getOrganization().getPhones() : null;
    	Collection<Phone> orgDivPhones = this.getOrgDivision() != null ? this.getOrgDivision().getPhones() : null;
    	Collection<Phone> orgDivSrvcPhones = this.getOrgDivisionService() != null ? this.getOrgDivisionService().getPhones() : null;
    	Collection<Phone> orgDivSrvcLocPhones = this.getOrgDivisionServiceLocation() != null ? this.getOrgDivisionServiceLocation().getPhones() : null;
    	Collection<Phone> workPlacePhones = this.getWorkPlace() != null ? this.getWorkPlace().getPhones() : null;

    	int phoneNum = (orgPhones != null ? orgPhones.size() : 0)
    				 + (orgDivPhones != null ? orgDivPhones.size() : 0)
    				 + (orgDivSrvcPhones != null ? orgDivSrvcPhones.size() : 0)
    				 + (orgDivSrvcLocPhones != null ? orgDivSrvcLocPhones.size() : 0)
    				 + (workPlacePhones != null ? workPlacePhones.size() : 0);
    	Collection<Phone> outPhones = Sets.newLinkedHashSetWithExpectedSize(phoneNum);
    	if (orgPhones != null) 				outPhones.addAll(orgPhones);
    	if (orgDivPhones != null)			outPhones.addAll(orgDivPhones);
    	if (orgDivSrvcPhones != null)		outPhones.addAll(orgDivSrvcPhones);
    	if (orgDivSrvcLocPhones != null)	outPhones.addAll(orgDivSrvcLocPhones);
    	if (workPlacePhones != null)		outPhones.addAll(workPlacePhones);
    	return outPhones;
    }
    public Collection<EMail> getEffectiveEMails() {
    	Collection<EMail> orgEMails = this.getOrganization() != null ? this.getOrganization().getEmails() : null;
    	Collection<EMail> orgDivEMails = this.getOrganization() != null ? this.getOrgDivision().getEmails() : null;
    	Collection<EMail> orgDivSrvcEMails = this.getOrgDivisionService() != null ? this.getOrgDivisionService().getEmails() : null;
    	Collection<EMail> orgDivSrvcLocEMails = this.getOrgDivisionServiceLocation() != null ? this.getOrgDivisionServiceLocation().getEmails() : null;
    	Collection<EMail> workPlaceEMails = this.getWorkPlace() != null ? this.getWorkPlace().getEmails() : null;

    	int EMailNum = (orgEMails != null ? orgEMails.size() : 0)
    				 + (orgDivEMails != null ? orgDivEMails.size() : 0)
    				 + (orgDivSrvcEMails != null ? orgDivSrvcEMails.size() : 0)
    				 + (orgDivSrvcLocEMails != null ? orgDivSrvcLocEMails.size() : 0)
    				 + (workPlaceEMails != null ? workPlaceEMails.size() : 0);
    	Collection<EMail> outEMails = Sets.newLinkedHashSetWithExpectedSize(EMailNum);
    	if (orgEMails != null) 				outEMails.addAll(orgEMails);
    	if (orgDivEMails != null)			outEMails.addAll(orgDivEMails);
    	if (orgDivSrvcEMails != null)		outEMails.addAll(orgDivSrvcEMails);
    	if (orgDivSrvcLocEMails != null)	outEMails.addAll(orgDivSrvcLocEMails);
    	if (workPlaceEMails != null)		outEMails.addAll(workPlaceEMails);
    	return outEMails;
    }
    public String getEffectivePhonesAsString() {
    	return X47BOrganizationalPersistableObjectBase.phonesAsString(this.getEffectivePhones());
    }
    public String getEffectiveEMailsAsString() {
    	return X47BOrganizationalPersistableObjectBase.emailsAsString(this.getEffectiveEMails());
    }
}
