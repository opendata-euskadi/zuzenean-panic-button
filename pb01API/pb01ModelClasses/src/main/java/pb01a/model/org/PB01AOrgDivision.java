package pb01a.model.org;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import pb01a.model.metadata.PB01AMetaDataForOrgDivision;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionID;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrganizationID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrganizationOID;
import pb01a.model.org.summaries.PB01ASummarizedOrgDivision;
import r01f.aspects.interfaces.dirtytrack.ConvertToDirtyStateTrackable;
import r01f.locale.Language;
import r01f.model.metadata.annotations.ModelObjectData;
import r01f.objectstreamer.annotations.MarshallField;
import r01f.objectstreamer.annotations.MarshallType;
import r01f.validation.ObjectValidationResult;

@ModelObjectData(PB01AMetaDataForOrgDivision.class)
@MarshallType(as="orgDivision")
@ConvertToDirtyStateTrackable			// changes in state are tracked
@Accessors(prefix="_")
public class PB01AOrgDivision
     extends PB01AOrganizationalPersistableObjectBase<PB01AOrgDivisionOID,PB01AOrgDivisionID,
     								   		   		 PB01AOrgDivision> {

	private static final long serialVersionUID = -6014693898907709134L;
/////////////////////////////////////////////////////////////////////////////////////////
//  FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	@MarshallField(as="organization")
	@Getter @Setter private PB01AOrgObjectRef<PB01AOrganizationOID,PB01AOrganizationID> _orgRef;

/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	@Override @SuppressWarnings("unchecked")
	public PB01ASummarizedOrgDivision getSummarizedIn(final Language lang) {
		return PB01ASummarizedOrgDivision.create()
									    .withOid(_oid)
									    .withId(_id)
									    .named(_nameByLanguage.get(lang))
									    .withPhones(_phones)
									    .withEmails(_emails);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  VALIDATION
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public ObjectValidationResult<PB01AOrgDivision> validate() {
		return PB01AOrganizationalObjectsValidators.createOrgDivisionValidator()
													   .validate(this);
	}
}
