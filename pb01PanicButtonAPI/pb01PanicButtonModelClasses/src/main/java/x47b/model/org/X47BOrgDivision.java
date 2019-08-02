package x47b.model.org;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import r01f.aspects.interfaces.dirtytrack.ConvertToDirtyStateTrackable;
import r01f.locale.Language;
import r01f.model.metadata.annotations.ModelObjectData;
import r01f.objectstreamer.annotations.MarshallField;
import r01f.objectstreamer.annotations.MarshallType;
import r01f.validation.ObjectValidationResult;
import x47b.model.metadata.X47BMetaDataForOrgDivision;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionID;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrganizationID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrganizationOID;
import x47b.model.org.summaries.X47BSummarizedOrgDivision;

@ModelObjectData(X47BMetaDataForOrgDivision.class)
@MarshallType(as="orgDivision")
@ConvertToDirtyStateTrackable			// changes in state are tracked
@Accessors(prefix="_")
public class X47BOrgDivision
     extends X47BOrganizationalPersistableObjectBase<X47BOrgDivisionOID,X47BOrgDivisionID,
     								   		   		 X47BOrgDivision> {

	private static final long serialVersionUID = -6014693898907709134L;
/////////////////////////////////////////////////////////////////////////////////////////
//  FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	@MarshallField(as="organization")
	@Getter @Setter private X47BOrgObjectRef<X47BOrganizationOID,X47BOrganizationID> _orgRef;

/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	@Override @SuppressWarnings("unchecked")
	public X47BSummarizedOrgDivision getSummarizedIn(final Language lang) {
		return X47BSummarizedOrgDivision.create()
									    .withOid(_oid)
									    .withId(_id)
									    .named(_nameByLanguage.get(lang));
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  VALIDATION
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public ObjectValidationResult<X47BOrgDivision> validate() {
		return X47BOrganizationalObjectsValidators.createOrgDivisionValidator()
													   .validate(this);
	}
}
