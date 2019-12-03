package x47b.model.metadata;

import r01f.guids.OID;
import r01f.locale.Language;
import r01f.model.metadata.HasMetaDataForHasFullTextSummaryModelObject;
import r01f.model.metadata.HasMetaDataForHasSummaryModelObject;
import r01f.model.metadata.TypeMetaDataForPersistableModelObjectBase;
import r01f.model.metadata.annotations.DescInLang;
import r01f.model.metadata.annotations.MetaDataForType;
import r01f.types.summary.LangDependentSummary;
import x47b.model.X47BPersistableObject;
import x47b.model.org.X47BOrganization;
import x47b.model.org.X47BWorkPlace;

/**
 * {@link ModelObjectTypeMetaData} for every X47B entity model object
 * <ul>
 * 		<li>the generic super-type: {@link X47BPersistableObject}</li>
 * 		<li>... and it's concrete types:
 * 			<ul>
 * 				<li>{@link X47BOrganization}</li>
 * 				<li>{@link X47BLocation}</li>
 * 				<li>{@link X47BWorkPlace}</li>
 * 			</ul>
 * 		</li>
 * </ul>
 */
@MetaDataForType(modelObjTypeCode = X47BModelObjectCodes.MODEL_OBJ_TYPE_BASE_CODE,
			     description = {
						@DescInLang(language=Language.SPANISH, value="X47B Persistent object"),
						@DescInLang(language=Language.BASQUE, value="[eu] X47B Persistent object"),
						@DescInLang(language=Language.ENGLISH, value="X47B Persistent object")
				 })
public abstract class X47BMetaDataForModelObjectBase<O extends OID>
       		  extends TypeMetaDataForPersistableModelObjectBase<O> 
		   implements HasMetaDataForHasSummaryModelObject<LangDependentSummary>,
   			   		  HasMetaDataForHasFullTextSummaryModelObject<LangDependentSummary> {
/////////////////////////////////////////////////////////////////////////////////////////
//	  
/////////////////////////////////////////////////////////////////////////////////////////
}
