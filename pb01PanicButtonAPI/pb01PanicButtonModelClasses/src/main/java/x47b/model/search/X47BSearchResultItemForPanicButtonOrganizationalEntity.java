package x47b.model.search;

import java.util.Collection;
import java.util.Date;

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
}
