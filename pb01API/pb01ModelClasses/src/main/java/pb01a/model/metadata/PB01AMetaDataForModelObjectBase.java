package pb01a.model.metadata;

import pb01a.model.PB01APersistableObject;
import pb01a.model.org.PB01AOrgDivisionServiceLocation;
import pb01a.model.org.PB01AOrganization;
import pb01a.model.org.PB01AWorkPlace;
import r01f.guids.OID;
import r01f.locale.Language;
import r01f.model.metadata.HasMetaDataForHasFullTextSummaryModelObject;
import r01f.model.metadata.HasMetaDataForHasSummaryModelObject;
import r01f.model.metadata.TypeMetaDataForPersistableModelObjectBase;
import r01f.model.metadata.annotations.DescInLang;
import r01f.model.metadata.annotations.MetaDataForType;
import r01f.types.summary.LangDependentSummary;

/**
 * {@link ModelObjectTypeMetaData} for every PB01 entity model object
 * <ul>
 * 		<li>the generic super-type: {@link PB01APersistableObject}</li>
 * 		<li>... and it's concrete types:
 * 			<ul>
 * 				<li>{@link PB01AOrganization}</li>
 * 				<li>{@link PB01AOrgDivisionServiceLocation}</li>
 * 				<li>{@link PB01AWorkPlace}</li>
 * 			</ul>
 * 		</li>
 * </ul>
 */
@MetaDataForType(modelObjTypeCode = PB01AModelObjectCodes.MODEL_OBJ_TYPE_BASE_CODE,
			     description = {
						@DescInLang(language=Language.SPANISH, value="PB01 Persistent object"),
						@DescInLang(language=Language.BASQUE, value="[eu] PB01 Persistent object"),
						@DescInLang(language=Language.ENGLISH, value="PB01 Persistent object")
				 })
public abstract class PB01AMetaDataForModelObjectBase<O extends OID>
       		  extends TypeMetaDataForPersistableModelObjectBase<O>
		   implements HasMetaDataForHasSummaryModelObject<LangDependentSummary>,
   			   		  HasMetaDataForHasFullTextSummaryModelObject<LangDependentSummary> {
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
}
