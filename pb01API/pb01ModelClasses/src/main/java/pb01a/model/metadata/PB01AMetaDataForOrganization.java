package pb01a.model.metadata;

import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrganizationID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrganizationOID;
import pb01a.model.org.PB01AOrganization;
import r01f.locale.Language;
import r01f.model.metadata.HasMetaDataForHasIDModelObject;
import r01f.model.metadata.annotations.DescInLang;
import r01f.model.metadata.annotations.MetaDataForType;

/**
 * Describes a {@link PB01AOrganization}
 */
@MetaDataForType(modelObjTypeCode = PB01AModelObjectCodes.ORGANIZATION_MODEL_OBJ_TYPE_CODE,
			     description = {
						@DescInLang(language=Language.SPANISH, value="Organización"),
						@DescInLang(language=Language.BASQUE, value="[eu] Organization"),
						@DescInLang(language=Language.ENGLISH, value="Organization")
				 })
public abstract class PB01AMetaDataForOrganization
	 		  extends PB01AMetaDataForModelObjectBase<PB01AOrganizationOID>
	 	   implements HasMetaDataForHasIDModelObject<PB01AOrganizationID> {
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
}
