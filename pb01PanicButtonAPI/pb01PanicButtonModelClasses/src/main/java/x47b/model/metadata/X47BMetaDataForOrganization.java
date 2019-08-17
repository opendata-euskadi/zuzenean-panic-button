package x47b.model.metadata;

import r01f.locale.Language;
import r01f.model.metadata.HasMetaDataForHasIDModelObject;
import r01f.model.metadata.annotations.DescInLang;
import r01f.model.metadata.annotations.MetaDataForType;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrganizationID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrganizationOID;
import x47b.model.org.X47BOrganization;

/**
 * Describes a {@link X47BOrganization}
 */
@MetaDataForType(modelObjTypeCode = X47BModelObjectCodes.ORGANIZATION_MODEL_OBJ_TYPE_CODE,
			     description = {
						@DescInLang(language=Language.SPANISH, value="Organización"),
						@DescInLang(language=Language.BASQUE, value="[eu] Organization"),
						@DescInLang(language=Language.ENGLISH, value="Organization")
				 })
public abstract class X47BMetaDataForOrganization
	 		  extends X47BMetaDataForModelObjectBase<X47BOrganizationOID>
	 	   implements HasMetaDataForHasIDModelObject<X47BOrganizationID> {
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
}
