package pb01a.model.metadata;

import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionServiceLocationID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionServiceLocationOID;
import pb01a.model.org.PB01AOrgDivisionServiceLocation;
import r01f.locale.Language;
import r01f.model.metadata.HasMetaDataForHasIDModelObject;
import r01f.model.metadata.annotations.DescInLang;
import r01f.model.metadata.annotations.MetaDataForType;

/**
 * Describes a {@link PB01AOrgDivisionServiceLocation}
 */
@MetaDataForType(modelObjTypeCode = PB01AModelObjectCodes.ORG_DIVISION_SERVICE_LOCATION_MODEL_OBJ_TYPE_CODE,
			     description = {
						@DescInLang(language=Language.SPANISH, value="Localización de un servicio de una división de una organización"),
						@DescInLang(language=Language.BASQUE, value="[eu] Organization's division's service's location"),
						@DescInLang(language=Language.ENGLISH, value="Organization's division's service's location")
				 })
public abstract class PB01AMetaDataForOrgDivisionServiceLocation
	 		  extends PB01AMetaDataForModelObjectBase<PB01AOrgDivisionServiceLocationOID>
	 	   implements HasMetaDataForHasIDModelObject<PB01AOrgDivisionServiceLocationID>,
	 	   			  PB01AHasFieldsMetaDataForHasOrganization,
	 	   			  PB01AHasFieldsMetaDataForHasOrgDivision {
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
}
