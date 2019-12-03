package x47b.model.org;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import r01f.aspects.interfaces.dirtytrack.ConvertToDirtyStateTrackable;
import r01f.locale.Language;
import r01f.model.metadata.annotations.ModelObjectData;
import r01f.objectstreamer.annotations.MarshallField;
import r01f.objectstreamer.annotations.MarshallType;
import r01f.types.contact.ContactInfo;
import r01f.types.geo.GeoPosition;
import r01f.validation.ObjectValidationResult;
import x47b.model.metadata.X47BMetaDataForOrgDivisionServiceLocation;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionID;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionServiceID;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionServiceLocationID;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrganizationID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceLocationOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrganizationOID;
import x47b.model.org.summaries.X47BSummarizedOrgDivisionServiceLocation;

@ModelObjectData(X47BMetaDataForOrgDivisionServiceLocation.class)
@MarshallType(as="orgDivisionServiceLocation")
@ConvertToDirtyStateTrackable			// changes in state are tracked
@Accessors(prefix="_")
public class X47BOrgDivisionServiceLocation
     extends X47BOrganizationalPersistableObjectBase<X47BOrgDivisionServiceLocationOID,X47BOrgDivisionServiceLocationID,
     								   		   		 X47BOrgDivisionServiceLocation> {

	private static final long serialVersionUID = 6807946102046157627L;

/////////////////////////////////////////////////////////////////////////////////////////
//  FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	@MarshallField(as="organization")
	@Getter @Setter private X47BOrgObjectRef<X47BOrganizationOID,X47BOrganizationID> _orgRef;

	@MarshallField(as="division")
	@Getter @Setter private X47BOrgObjectRef<X47BOrgDivisionOID,X47BOrgDivisionID> _orgDivisionRef;

	@MarshallField(as="service")
	@Getter @Setter private X47BOrgObjectRef<X47BOrgDivisionServiceOID,X47BOrgDivisionServiceID> _orgDivisionServiceRef;
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
	public X47BSummarizedOrgDivisionServiceLocation getSummarizedIn(final Language lang) {
		return X47BSummarizedOrgDivisionServiceLocation.create()
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
	public ObjectValidationResult<X47BOrgDivisionServiceLocation> validate() {
		return X47BOrganizationalObjectsValidators.createOrgDivisionServiceLocationValidator()
													   .validate(this);
	}
}
