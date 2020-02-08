package pb01a.model.metadata;

import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionServiceID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionServiceOID;
import pb01a.model.org.PB01AOrgDivisionService;
import r01f.locale.Language;
import r01f.model.metadata.HasMetaDataForHasIDModelObject;
import r01f.model.metadata.annotations.DescInLang;
import r01f.model.metadata.annotations.MetaDataForType;

/**
 * Describes a {@link PB01AOrgDivisionService}
 */
@MetaDataForType(modelObjTypeCode = PB01AModelObjectCodes.ORG_DIVISION_SERVICE_MODEL_OBJ_TYPE_CODE,
			     description = {
						@DescInLang(language=Language.SPANISH, value="Servicio de una división de una organización"),
						@DescInLang(language=Language.BASQUE, value="[eu] Organization's division's service"),
						@DescInLang(language=Language.ENGLISH, value="Organization's division's service")
				 })
public abstract class PB01AMetaDataForOrgDivisionService
	 		  extends PB01AMetaDataForModelObjectBase<PB01AOrgDivisionServiceOID>
	 	   implements HasMetaDataForHasIDModelObject<PB01AOrgDivisionServiceID>,
	 	   			  PB01AHasFieldsMetaDataForHasOrganization,
	 	   			  PB01AHasFieldsMetaDataForHasOrgDivision {
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
}
