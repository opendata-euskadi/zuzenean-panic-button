package pb01a.model.org;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import pb01a.model.metadata.PB01AMetaDataForOrgDivisionService;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionID;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionServiceID;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrganizationID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionServiceOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrganizationOID;
import pb01a.model.org.summaries.PB01ASummarizedOrgDivisionService;
import r01f.aspects.interfaces.dirtytrack.ConvertToDirtyStateTrackable;
import r01f.locale.Language;
import r01f.model.metadata.annotations.ModelObjectData;
import r01f.objectstreamer.annotations.MarshallField;
import r01f.objectstreamer.annotations.MarshallType;
import r01f.validation.ObjectValidationResult;

@ModelObjectData(PB01AMetaDataForOrgDivisionService.class)
@MarshallType(as="orgDivisionService")
@ConvertToDirtyStateTrackable			// changes in state are tracked
@Accessors(prefix="_")
public class PB01AOrgDivisionService
     extends PB01AOrganizationalPersistableObjectBase<PB01AOrgDivisionServiceOID,PB01AOrgDivisionServiceID,
     								   		   		 PB01AOrgDivisionService> {

	private static final long serialVersionUID = -6014693898907709134L;
/////////////////////////////////////////////////////////////////////////////////////////
//  FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	@MarshallField(as="organization")
	@Getter @Setter private PB01AOrgObjectRef<PB01AOrganizationOID,PB01AOrganizationID> _orgRef;

	@MarshallField(as="division")
	@Getter @Setter private PB01AOrgObjectRef<PB01AOrgDivisionOID,PB01AOrgDivisionID> _orgDivisionRef;
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	@Override @SuppressWarnings("unchecked")
	public PB01ASummarizedOrgDivisionService getSummarizedIn(final Language lang) {
		return PB01ASummarizedOrgDivisionService.create()
											   .withOid(_oid)
											   .withId(_id)
											   .named(_nameByLanguage != null ? _nameByLanguage.get(lang) : null)
											   .withPhones(_phones)
											   .withEmails(_emails);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  VALIDATION
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public ObjectValidationResult<PB01AOrgDivisionService> validate() {
		return PB01AOrganizationalObjectsValidators.createOrgDivisionServiceValidator()
													   .validate(this);
	}
}
