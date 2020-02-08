package pb01a.model.metadata;

import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionOID;
import pb01a.model.org.PB01AOrgDivision;
import r01f.locale.Language;
import r01f.model.metadata.HasMetaDataForHasIDModelObject;
import r01f.model.metadata.annotations.DescInLang;
import r01f.model.metadata.annotations.MetaDataForType;

/**
 * Describes a {@link PB01AOrgDivision}
 */
@MetaDataForType(modelObjTypeCode = PB01AModelObjectCodes.ORG_DIVISION_MODEL_OBJ_TYPE_CODE,
			     description = {
						@DescInLang(language=Language.SPANISH, value="División de la organizaci�n"),
						@DescInLang(language=Language.BASQUE, value="[eu] Organization's division"),
						@DescInLang(language=Language.ENGLISH, value="Organization's division")
				 })
public abstract class PB01AMetaDataForOrgDivision
	          extends PB01AMetaDataForModelObjectBase<PB01AOrgDivisionOID>
	 	   implements HasMetaDataForHasIDModelObject<PB01AOrgDivisionID>,
	 	   			  PB01AHasFieldsMetaDataForHasOrganization {
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
}
