package x47b.model.org;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import r01f.aspects.interfaces.dirtytrack.ConvertToDirtyStateTrackable;
import r01f.locale.Language;
import r01f.locale.LanguageTexts;
import r01f.model.metadata.annotations.ModelObjectData;
import r01f.objectstreamer.annotations.MarshallField;
import r01f.objectstreamer.annotations.MarshallType;
import r01f.validation.ObjectValidationResult;
import x47b.model.metadata.X47BMetaDataForOrgDivisionService;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionID;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionServiceID;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrganizationID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrganizationOID;
import x47b.model.org.summaries.X47BSummarizedOrgDivisionService;

@ModelObjectData(X47BMetaDataForOrgDivisionService.class)
@MarshallType(as="orgDivisionService")
@ConvertToDirtyStateTrackable			// changes in state are tracked
@Accessors(prefix="_")
public class X47BOrgDivisionService
     extends X47BOrganizationalModelObjectBase<X47BOrgDivisionServiceOID,X47BOrgDivisionServiceID,
     								   		   X47BOrgDivisionService> {

	private static final long serialVersionUID = -6014693898907709134L;
/////////////////////////////////////////////////////////////////////////////////////////
//  FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	@MarshallField(as="organization")
	@Getter @Setter private X47BOrganizationalModelObjectRef<X47BOrganizationOID,X47BOrganizationID> _orgRef;
	
	@MarshallField(as="division")
	@Getter @Setter private X47BOrganizationalModelObjectRef<X47BOrgDivisionOID,X47BOrgDivisionID> _orgDivisionRef;
	
	@MarshallField(as="procedure")
	@Getter @Setter private LanguageTexts _procedure;
/////////////////////////////////////////////////////////////////////////////////////////
//  
/////////////////////////////////////////////////////////////////////////////////////////
	@Override @SuppressWarnings("unchecked")
	public X47BSummarizedOrgDivisionService getSummarizedIn(final Language lang) {
		return X47BSummarizedOrgDivisionService.create()
											   .withOid(_oid)
											   .withId(_id)											   
											   .named(_nameByLanguage != null ? _nameByLanguage.get(lang) : null)
											   .managedProcedure(_procedure != null ? _procedure.get(lang) : null);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  VALIDATION
/////////////////////////////////////////////////////////////////////////////////////////
	@Override 
	public ObjectValidationResult<X47BOrgDivisionService> validate() {
		return X47BOrganizationalModelObjectsValidators.createOrgDivisionServiceValidator()
													   .validate(this);
	}
}
