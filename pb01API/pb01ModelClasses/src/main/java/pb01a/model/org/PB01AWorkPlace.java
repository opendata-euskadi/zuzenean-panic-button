package pb01a.model.org;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import pb01a.model.metadata.PB01AMetaDataForWorkPlace;
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
import pb01a.model.org.summaries.PB01ASummarizedWorkPlace;
import r01f.aspects.interfaces.dirtytrack.ConvertToDirtyStateTrackable;
import r01f.locale.Language;
import r01f.model.metadata.annotations.ModelObjectData;
import r01f.objectstreamer.annotations.MarshallField;
import r01f.objectstreamer.annotations.MarshallType;

@ModelObjectData(PB01AMetaDataForWorkPlace.class)
@MarshallType(as="workPlace")
@ConvertToDirtyStateTrackable			// changes in state are tracked
@Accessors(prefix="_")
@NoArgsConstructor
public class PB01AWorkPlace
     extends PB01AOrganizationalPersistableObjectBase<PB01AWorkPlaceOID,PB01AWorkPlaceID,PB01AWorkPlace> {

	private static final long serialVersionUID = -6014693898907709134L;
/////////////////////////////////////////////////////////////////////////////////////////
//  FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	@MarshallField(as="organization")
	@Getter @Setter private PB01AOrgObjectRef<PB01AOrganizationOID,PB01AOrganizationID> _orgRef;

	@MarshallField(as="division")
	@Getter @Setter private PB01AOrgObjectRef<PB01AOrgDivisionOID,PB01AOrgDivisionID> _orgDivisionRef;

	@MarshallField(as="service")
	@Getter @Setter private PB01AOrgObjectRef<PB01AOrgDivisionServiceOID,PB01AOrgDivisionServiceID> _orgDivisionServiceRef;

	@MarshallField(as="location")
	@Getter @Setter private PB01AOrgObjectRef<PB01AOrgDivisionServiceLocationOID,PB01AOrgDivisionServiceLocationID> _orgDivisionServiceLocationRef;

/////////////////////////////////////////////////////////////////////////////////////////
//  METHODS
/////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * @return the alarm organization id (ie: MYORG)
	 */
	public PB01AOrganizationID getOrganizationId() {
		return _orgRef.getId();
	}
	/**
	 * @return the alarm division id (ie: MYORG)
	 */
	public PB01AOrgDivisionID getDivisionId() {
		return _orgDivisionRef.getId();
	}
	/**
	 * @return the alarm organization id (ie: MYORG)
	 */
	public PB01AOrgDivisionServiceID getServiceId() {
		return _orgDivisionServiceRef.getId();
	}
	/**
	 * @return the alarm source location id (ie: MYORG/MYLOC)
	 */
	public PB01AOrgDivisionServiceLocationID getLocationId() {
		return _orgDivisionServiceLocationRef.getId();
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	@Override @SuppressWarnings("unchecked")
	public PB01ASummarizedWorkPlace getSummarizedIn(final Language lang) {
		return PB01ASummarizedWorkPlace.create()
									 .withOid(_oid)
									 .withId(_id)
									 .named(_nameByLanguage.get(lang))
								     .withPhones(_phones)
								     .withEmails(_emails);
	}
}
