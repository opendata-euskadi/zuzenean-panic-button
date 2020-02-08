package pb01a.model.org;

import lombok.experimental.Accessors;
import pb01a.model.metadata.PB01AMetaDataForOrganization;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrganizationID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrganizationOID;
import pb01a.model.org.summaries.PB01ASummarizedOrganization;
import r01f.aspects.interfaces.dirtytrack.ConvertToDirtyStateTrackable;
import r01f.locale.Language;
import r01f.model.metadata.annotations.ModelObjectData;
import r01f.objectstreamer.annotations.MarshallType;

@ModelObjectData(PB01AMetaDataForOrganization.class)
@MarshallType(as="entity")
@ConvertToDirtyStateTrackable			// changes in state are tracked
@Accessors(prefix="_")
public class PB01AOrganization
     extends PB01AOrganizationalPersistableObjectBase<PB01AOrganizationOID,PB01AOrganizationID,
     								   		   		 PB01AOrganization> {

	private static final long serialVersionUID = 8349805975439486112L;

/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	@Override @SuppressWarnings("unchecked")
	public PB01ASummarizedOrganization getSummarizedIn(final Language lang) {
		return PB01ASummarizedOrganization.create()
									     .withOid(_oid)
									     .withId(_id)
									     .named(_nameByLanguage.get(lang))
									     .withPhones(_phones)
									     .withEmails(_emails);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
}
