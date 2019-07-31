package x47b.model.metadata;

import r01f.locale.Language;
import r01f.model.metadata.HasMetaDataForHasIDModelObject;
import r01f.model.metadata.annotations.DescInLang;
import r01f.model.metadata.annotations.MetaDataForType;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionServiceID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceOID;
import x47b.model.org.X47BOrgDivisionService;

/**
 * Describes a {@link X47BOrgDivisionService}
 */
@MetaDataForType(modelObjTypeCode = X47BModelObjectCodes.ORG_DIVISION_SERVICE_MODEL_OBJ_TYPE_CODE,
			     description = {
						@DescInLang(language=Language.SPANISH, value="Servicio de una división de una organización"),
						@DescInLang(language=Language.BASQUE, value="[eu] Organization's division's service"),
						@DescInLang(language=Language.ENGLISH, value="Organization's division's service")
				 })
public abstract class X47BMetaDataForOrgDivisionService
	 		  extends X47BMetaDataForModelObjectBase<X47BOrgDivisionServiceOID> 
	 	   implements HasMetaDataForHasIDModelObject<X47BOrgDivisionServiceID>, 
	 	   			  X47BHasFieldsMetaDataForHasOrganization, 
	 	   			  X47BHasFieldsMetaDataForHasOrgDivision {
/////////////////////////////////////////////////////////////////////////////////////////
//	  
/////////////////////////////////////////////////////////////////////////////////////////
}
