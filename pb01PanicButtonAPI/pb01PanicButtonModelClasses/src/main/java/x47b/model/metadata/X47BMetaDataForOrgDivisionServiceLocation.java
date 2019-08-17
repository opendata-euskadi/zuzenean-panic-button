package x47b.model.metadata;

import r01f.locale.Language;
import r01f.model.metadata.HasMetaDataForHasIDModelObject;
import r01f.model.metadata.annotations.DescInLang;
import r01f.model.metadata.annotations.MetaDataForType;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionServiceLocationID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceLocationOID;
import x47b.model.org.X47BOrgDivisionServiceLocation;

/**
 * Describes a {@link X47BOrgDivisionServiceLocation}
 */
@MetaDataForType(modelObjTypeCode = X47BModelObjectCodes.ORG_DIVISION_SERVICE_LOCATION_MODEL_OBJ_TYPE_CODE,
			     description = {
						@DescInLang(language=Language.SPANISH, value="Localización de un servicio de una división de una organización"),
						@DescInLang(language=Language.BASQUE, value="[eu] Organization's division's service's location"),
						@DescInLang(language=Language.ENGLISH, value="Organization's division's service's location")
				 })
public abstract class X47BMetaDataForOrgDivisionServiceLocation
	 		  extends X47BMetaDataForModelObjectBase<X47BOrgDivisionServiceLocationOID>
	 	   implements HasMetaDataForHasIDModelObject<X47BOrgDivisionServiceLocationID>,
	 	   			  X47BHasFieldsMetaDataForHasOrganization,
	 	   			  X47BHasFieldsMetaDataForHasOrgDivision {
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
}
