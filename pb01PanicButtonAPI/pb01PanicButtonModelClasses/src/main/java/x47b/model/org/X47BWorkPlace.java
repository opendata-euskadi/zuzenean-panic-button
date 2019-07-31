package x47b.model.org;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import r01f.aspects.interfaces.dirtytrack.ConvertToDirtyStateTrackable;
import r01f.locale.Language;
import r01f.model.metadata.annotations.ModelObjectData;
import r01f.objectstreamer.annotations.MarshallField;
import r01f.objectstreamer.annotations.MarshallType;
import x47b.model.metadata.X47BMetaDataForWorkPlace;
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
import x47b.model.org.summaries.X47BSummarizedWorkPlace;

@ModelObjectData(X47BMetaDataForWorkPlace.class)
@MarshallType(as="workPlace")
@ConvertToDirtyStateTrackable			// changes in state are tracked
@Accessors(prefix="_")
@NoArgsConstructor
public class X47BWorkPlace
     extends X47BOrganizationalPersistableObjectBase<X47BWorkPlaceOID,X47BWorkPlaceID,X47BWorkPlace> {

	private static final long serialVersionUID = -6014693898907709134L;
/////////////////////////////////////////////////////////////////////////////////////////
//  FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	@MarshallField(as="organization")
	@Getter @Setter private X47BOrganizationalObjectRef<X47BOrganizationOID,X47BOrganizationID> _orgRef;

	@MarshallField(as="division")
	@Getter @Setter private X47BOrganizationalObjectRef<X47BOrgDivisionOID,X47BOrgDivisionID> _orgDivisionRef;

	@MarshallField(as="service")
	@Getter @Setter private X47BOrganizationalObjectRef<X47BOrgDivisionServiceOID,X47BOrgDivisionServiceID> _orgDivisionServiceRef;

	@MarshallField(as="location")
	@Getter @Setter private X47BOrganizationalObjectRef<X47BOrgDivisionServiceLocationOID,X47BOrgDivisionServiceLocationID> _orgDivisionServiceLocationRef;

/////////////////////////////////////////////////////////////////////////////////////////
//  METHODS
/////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * @return the alarm organization id (ie: MYORG)
	 */
	public X47BOrganizationID getOrganizationId() {
		return _orgRef.getId();
	}
	/**
	 * @return the alarm division id (ie: MYORG)
	 */
	public X47BOrgDivisionID getDivisionId() {
		return _orgDivisionRef.getId();
	}
	/**
	 * @return the alarm organization id (ie: MYORG)
	 */
	public X47BOrgDivisionServiceID getServiceId() {
		return _orgDivisionServiceRef.getId();
	}
	/**
	 * @return the alarm source location id (ie: MYORG/MYLOC)
	 */
	public X47BOrgDivisionServiceLocationID getLocationId() {
		return _orgDivisionServiceLocationRef.getId();
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	@Override @SuppressWarnings("unchecked")
	public X47BSummarizedWorkPlace getSummarizedIn(final Language lang) {
		return X47BSummarizedWorkPlace.create()
									 .withOid(_oid)
									 .withId(_id)
									 .named(_nameByLanguage.get(lang));
	}
}
