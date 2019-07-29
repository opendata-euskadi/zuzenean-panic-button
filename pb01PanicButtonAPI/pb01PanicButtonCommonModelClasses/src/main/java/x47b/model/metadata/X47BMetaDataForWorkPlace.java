package x47b.model.metadata;

import r01f.locale.Language;
import r01f.model.metadata.HasMetaDataForHasIDModelObject;
import r01f.model.metadata.annotations.DescInLang;
import r01f.model.metadata.annotations.MetaDataForType;
import x47b.model.oids.X47BOrganizationalIDs.X47BWorkPlaceID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BWorkPlaceOID;
import x47b.model.org.X47BWorkPlace;

/**
 * Describes a {@link X47BWorkPlace}
 */
@MetaDataForType(modelObjTypeCode = X47BModelObjectCodes.WORKPLACE_MODEL_OBJ_TYPE_CODE,
			     description = {
						@DescInLang(language=Language.SPANISH, value="Puesto de trabajo"),
						@DescInLang(language=Language.BASQUE, value="[eu] Puesto de trabajo"),
						@DescInLang(language=Language.ENGLISH, value="Puesto de trabajo")
				 })
public abstract class X47BMetaDataForWorkPlace
	 		  extends X47BMetaDataForModelObjectBase<X47BWorkPlaceOID> 
	 	   implements HasMetaDataForHasIDModelObject<X47BWorkPlaceID>,
	 	   			  X47BHasFieldsMetaDataForHasOrganization, 
	 	   			  X47BHasFieldsMetaDataForHasOrgDivision,
	 	   			  X47BHasFieldsMetaDataForHasOrgDivisionService {
/////////////////////////////////////////////////////////////////////////////////////////
//	  
/////////////////////////////////////////////////////////////////////////////////////////
}
