package x47b.model.metadata;

import r01f.locale.Language;
import r01f.model.metadata.HasMetaDataForHasIDModelObject;
import r01f.model.metadata.annotations.DescInLang;
import r01f.model.metadata.annotations.MetaDataForType;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionOID;
import x47b.model.org.X47BOrgDivision;

/**
 * Describes a {@link X47BOrgDivision}
 */
@MetaDataForType(modelObjTypeCode = X47BModelObjectCodes.ORG_DIVISION_MODEL_OBJ_TYPE_CODE,
			     description = {
						@DescInLang(language=Language.SPANISH, value="División de la organización"),
						@DescInLang(language=Language.BASQUE, value="[eu] Organization's division"),
						@DescInLang(language=Language.ENGLISH, value="Organization's division")
				 })
public abstract class X47BMetaDataForOrgDivision
	          extends X47BMetaDataForModelObjectBase<X47BOrgDivisionOID> 
	 	   implements HasMetaDataForHasIDModelObject<X47BOrgDivisionID>,
	 	   			  X47BHasFieldsMetaDataForHasOrganization {
/////////////////////////////////////////////////////////////////////////////////////////
//	  
/////////////////////////////////////////////////////////////////////////////////////////
}
