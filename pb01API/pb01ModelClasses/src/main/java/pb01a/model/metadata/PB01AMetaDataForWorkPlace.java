package pb01a.model.metadata;

import pb01a.model.oids.PB01AOrganizationalIDs.PB01AWorkPlaceID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AWorkPlaceOID;
import pb01a.model.org.PB01AWorkPlace;
import r01f.locale.Language;
import r01f.model.metadata.HasMetaDataForHasIDModelObject;
import r01f.model.metadata.annotations.DescInLang;
import r01f.model.metadata.annotations.MetaDataForType;

/**
 * Describes a {@link PB01AWorkPlace}
 */
@MetaDataForType(modelObjTypeCode = PB01AModelObjectCodes.WORKPLACE_MODEL_OBJ_TYPE_CODE,
			     description = {
						@DescInLang(language=Language.SPANISH, value="Puesto de trabajo"),
						@DescInLang(language=Language.BASQUE, value="[eu] Puesto de trabajo"),
						@DescInLang(language=Language.ENGLISH, value="Puesto de trabajo")
				 })
public abstract class PB01AMetaDataForWorkPlace
	 		  extends PB01AMetaDataForModelObjectBase<PB01AWorkPlaceOID> 
	 	   implements HasMetaDataForHasIDModelObject<PB01AWorkPlaceID>,
	 	   			  PB01AHasFieldsMetaDataForHasOrganization, 
	 	   			  PB01AHasFieldsMetaDataForHasOrgDivision,
	 	   			  PB01AHasFieldsMetaDataForHasOrgDivisionService {
/////////////////////////////////////////////////////////////////////////////////////////
//	  
/////////////////////////////////////////////////////////////////////////////////////////
}
