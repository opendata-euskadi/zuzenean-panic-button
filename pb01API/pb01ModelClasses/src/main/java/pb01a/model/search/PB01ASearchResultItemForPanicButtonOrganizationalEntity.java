package pb01a.model.search;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import pb01a.model.PB01APersistableObject;
import pb01a.model.org.PB01AOrgDivisionServiceLocation;
import pb01a.model.org.PB01AOrgObjectType;
import pb01a.model.org.PB01AOrganization;
import pb01a.model.org.PB01AOrganizationalPersistableObjectBase;
import pb01a.model.org.PB01AWorkPlace;
import pb01a.model.org.summaries.PB01ASummarizedOrgDivision;
import pb01a.model.org.summaries.PB01ASummarizedOrgDivisionService;
import pb01a.model.org.summaries.PB01ASummarizedOrgDivisionServiceLocation;
import pb01a.model.org.summaries.PB01ASummarizedOrganization;
import pb01a.model.org.summaries.PB01ASummarizedWorkPlace;
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
import r01f.util.types.collections.CollectionUtils;

/**
 * A search result item for an organizational entity like {@link PB01AOrganization}, {@link PB01AOrgDivisionServiceLocation} or {@link PB01AWorkPlace}
 */
@MarshallType(as="searchResultItemForOrganizationalEntity")
@Accessors(prefix="_")
@NoArgsConstructor
public class PB01ASearchResultItemForPanicButtonOrganizationalEntity
     extends SearchResultItemForModelObjectBase<PB01APersistableObject<?,?>> {

    private static final long serialVersionUID = 4169587420774250028L;
/////////////////////////////////////////////////////////////////////////////////////////
//  FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
    @MarshallField(as="org")
    @Getter @Setter private PB01ASummarizedOrganization _organization;

    @MarshallField(as="orgDivision")
    @Getter @Setter private PB01ASummarizedOrgDivision _orgDivision;

    @MarshallField(as="orgDivisionService")
    @Getter @Setter private PB01ASummarizedOrgDivisionService _orgDivisionService;

    @MarshallField(as="orgDivisionServiceLocation")
    @Getter @Setter private PB01ASummarizedOrgDivisionServiceLocation _orgDivisionServiceLocation;

    @MarshallField(as="workPlace")
    @Getter @Setter private PB01ASummarizedWorkPlace _workPlace;

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
    public PB01AOrgObjectType getOrgObjectType() {
        return PB01AOrgObjectType.of(this);
    }
    public int getOrgHierarchyLevel() {
        return this.getOrgObjectType()
                   .getOrgHierarchyLevel();
    }
    public boolean isSameLevelAs(final PB01ASearchResultItemForPanicButtonOrganizationalEntity other) {
        if (this.getOrgObjectType() != other.getOrgObjectType()) return false;
        return (this.isOrganization() && other.isOrganization())
            || (this.isOrgDivision() && other.isOrgDivision())
            || (this.isOrgDivisionService() && other.isOrgDivisionService())
            || (this.isOrgDivisionServiceLocation() && other.isOrgDivisionServiceLocation())
            || (this.isWorkPlace() && other.isWorkPlace());
    }
    public boolean isAncestorOf(final PB01ASearchResultItemForPanicButtonOrganizationalEntity other) {
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
    public boolean isDescendantOf(final PB01ASearchResultItemForPanicButtonOrganizationalEntity other) {
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
        return this.getOrgObjectType() == PB01AOrgObjectType.ORGANIZATION;
    }
    public boolean isOrgDivision() {
        return this.getOrgObjectType() == PB01AOrgObjectType.ORG_DIVISION;
    }
    public boolean isOrgDivisionService() {
        return this.getOrgObjectType() == PB01AOrgObjectType.ORG_DIVISION_SERVICE;
    }
    public boolean isOrgDivisionServiceLocation() {
        return this.getOrgObjectType() == PB01AOrgObjectType.ORG_DIVISION_SERVICE_LOCATION;
    }
    public boolean isWorkPlace() {
        return this.getOrgObjectType() == PB01AOrgObjectType.WORKPLACE;
    }
/////////////////////////////////////////////////////////////////////////////////////////
//	PHONES & EMAILS
/////////////////////////////////////////////////////////////////////////////////////////
    public String getPhonesAsString() {
    	return PB01AOrganizationalPersistableObjectBase.phonesAsString(this.getPhones());
    }
    public String getEMailsAsString() {
    	return PB01AOrganizationalPersistableObjectBase.emailsAsString(this.getEmails());
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
    	Collection<EMail> orgDivEMails = this.getOrgDivision() != null ? this.getOrgDivision().getEmails() : null;
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
    	return PB01AOrganizationalPersistableObjectBase.phonesAsString(this.getEffectivePhones());
    }

    public String getEffectiveEMailsAsString() {
    	return PB01AOrganizationalPersistableObjectBase.emailsAsString(this.getEffectiveEMails());
    }

    public Map<PB01AOrgObjectType,Collection<EMail>> getEffectiveEmailsByOrgEntityType() {
    	Map<PB01AOrgObjectType,Collection<EMail>> outMap = Maps.newHashMapWithExpectedSize(5);
    	if (_organization != null
    	 && CollectionUtils.hasData(_organization.getEmails())) {
    		outMap.put(PB01AOrgObjectType.ORGANIZATION,_organization.getEmails());
    	}

    	if (_orgDivision != null
    	 && CollectionUtils.hasData(_orgDivision.getEmails())) {
    		outMap.put(PB01AOrgObjectType.ORG_DIVISION,_orgDivision.getEmails());
    	}

    	if (_orgDivisionService != null
    	 && CollectionUtils.hasData(_orgDivisionService.getEmails())) {
    		outMap.put(PB01AOrgObjectType.ORG_DIVISION_SERVICE,_orgDivisionService.getEmails());
    	}

    	if (_orgDivisionServiceLocation != null
    	 && CollectionUtils.hasData(_orgDivisionServiceLocation.getEmails())) {
    		outMap.put(PB01AOrgObjectType.ORG_DIVISION_SERVICE_LOCATION,_orgDivisionServiceLocation.getEmails());
    	}

    	if (_workPlace != null
    	 && CollectionUtils.hasData(_workPlace.getEmails())) {
    		outMap.put(PB01AOrgObjectType.WORKPLACE,_workPlace.getEmails());
    	}

    	return outMap;
    }
    public Map<PB01AOrgObjectType,Collection<Phone>> getEffectivePhonesByOrgEntityType() {
    	Map<PB01AOrgObjectType,Collection<Phone>> outMap = Maps.newHashMapWithExpectedSize(5);
    	if (_organization != null
    	 && CollectionUtils.hasData(_organization.getPhones())) {
    		outMap.put(PB01AOrgObjectType.ORGANIZATION,_organization.getPhones());
    	}

    	if (_orgDivision != null
    	 && CollectionUtils.hasData(_orgDivision.getPhones())) {
    		outMap.put(PB01AOrgObjectType.ORG_DIVISION,_orgDivision.getPhones());
    	}

    	if (_orgDivisionService != null
    	 && CollectionUtils.hasData(_orgDivisionService.getPhones())) {
    		outMap.put(PB01AOrgObjectType.ORG_DIVISION_SERVICE,_orgDivisionService.getPhones());
    	}

    	if (_orgDivisionServiceLocation != null
    	 && CollectionUtils.hasData(_orgDivisionServiceLocation.getPhones())) {
    		outMap.put(PB01AOrgObjectType.ORG_DIVISION_SERVICE_LOCATION,_orgDivisionServiceLocation.getPhones());
    	}

    	if (_workPlace != null
    	 && CollectionUtils.hasData(_workPlace.getPhones())) {
    		outMap.put(PB01AOrgObjectType.WORKPLACE,_workPlace.getPhones());
    	}

    	return outMap;
    }
}
