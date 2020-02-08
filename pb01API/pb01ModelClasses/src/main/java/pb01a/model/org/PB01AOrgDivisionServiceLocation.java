package pb01a.model.org;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import pb01a.model.metadata.PB01AMetaDataForOrgDivisionServiceLocation;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionID;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionServiceID;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionServiceLocationID;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrganizationID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionServiceLocationOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionServiceOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrganizationOID;
import pb01a.model.org.summaries.PB01ASummarizedOrgDivisionServiceLocation;
import r01f.aspects.interfaces.dirtytrack.ConvertToDirtyStateTrackable;
import r01f.locale.Language;
import r01f.model.metadata.annotations.ModelObjectData;
import r01f.objectstreamer.annotations.MarshallField;
import r01f.objectstreamer.annotations.MarshallType;
import r01f.types.contact.ContactInfo;
import r01f.types.geo.GeoPosition;
import r01f.validation.ObjectValidationResult;

@ModelObjectData(PB01AMetaDataForOrgDivisionServiceLocation.class)
@MarshallType(as="orgDivisionServiceLocation")
@ConvertToDirtyStateTrackable			// changes in state are tracked
@Accessors(prefix="_")
public class PB01AOrgDivisionServiceLocation
     extends PB01AOrganizationalPersistableObjectBase<PB01AOrgDivisionServiceLocationOID,PB01AOrgDivisionServiceLocationID,
     								   		   		 PB01AOrgDivisionServiceLocation> {

	private static final long serialVersionUID = 6807946102046157627L;

/////////////////////////////////////////////////////////////////////////////////////////
//  FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	@MarshallField(as="organization")
	@Getter @Setter private PB01AOrgObjectRef<PB01AOrganizationOID,PB01AOrganizationID> _orgRef;

	@MarshallField(as="division")
	@Getter @Setter private PB01AOrgObjectRef<PB01AOrgDivisionOID,PB01AOrgDivisionID> _orgDivisionRef;

	@MarshallField(as="service")
	@Getter @Setter private PB01AOrgObjectRef<PB01AOrgDivisionServiceOID,PB01AOrgDivisionServiceID> _orgDivisionServiceRef;
/////////////////////////////////////////////////////////////////////////////////////////
//  LOCATION
/////////////////////////////////////////////////////////////////////////////////////////
	@MarshallField(as="geoPosition")
	@Getter @Setter private GeoPosition _position;
/////////////////////////////////////////////////////////////////////////////////////////
//  CONTACT
/////////////////////////////////////////////////////////////////////////////////////////
	@MarshallField(as="contactInfo")
	@Getter @Setter private ContactInfo _contactInfo;

/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	@Override @SuppressWarnings("unchecked")
	public PB01ASummarizedOrgDivisionServiceLocation getSummarizedIn(final Language lang) {
		return PB01ASummarizedOrgDivisionServiceLocation.create()
													   .withOid(_oid)
													   .withId(_id)
													   .named(_nameByLanguage.getFor(lang))
													   // geo position
													   .country(_position != null && _position.getCountry() != null ? _position.getCountry().getNameIn(lang) : null)
													   .territory(_position != null && _position.getTerritory() != null ? _position.getTerritory().getNameIn(lang) : null)
													   .state(_position != null && _position.getTerritory() != null ? _position.getTerritory().getNameIn(lang) : null)
													   .municipality(_position != null && _position.getMunicipality() != null ? _position.getMunicipality().getNameIn(lang) : null)
													   .steet(_position != null && _position.getStreet() != null ? _position.getStreet().getNameIn(lang) : null)
													   // contact info
													   .phones(_contactInfo != null ? _contactInfo.getContactPhones() : null)
													   // alarm phones
													   .withPhones(_phones)
													   .withEmails(_emails);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  VALIDATION
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public ObjectValidationResult<PB01AOrgDivisionServiceLocation> validate() {
		return PB01AOrganizationalObjectsValidators.createOrgDivisionServiceLocationValidator()
													   .validate(this);
	}
}
