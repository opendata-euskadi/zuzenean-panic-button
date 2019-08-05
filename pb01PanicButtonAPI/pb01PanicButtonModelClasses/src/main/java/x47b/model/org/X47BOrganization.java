package x47b.model.org;

import lombok.experimental.Accessors;
import r01f.aspects.interfaces.dirtytrack.ConvertToDirtyStateTrackable;
import r01f.locale.Language;
import r01f.model.metadata.annotations.ModelObjectData;
import r01f.objectstreamer.annotations.MarshallType;
import x47b.model.metadata.X47BMetaDataForOrganization;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrganizationID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrganizationOID;
import x47b.model.org.summaries.X47BSummarizedOrganization;

@ModelObjectData(X47BMetaDataForOrganization.class)
@MarshallType(as="entity")
@ConvertToDirtyStateTrackable			// changes in state are tracked
@Accessors(prefix="_")
public class X47BOrganization
     extends X47BOrganizationalPersistableObjectBase<X47BOrganizationOID,X47BOrganizationID,
     								   		   		 X47BOrganization> {

	private static final long serialVersionUID = 8349805975439486112L;

/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	@Override @SuppressWarnings("unchecked")
	public X47BSummarizedOrganization getSummarizedIn(final Language lang) {
		return X47BSummarizedOrganization.create()
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
