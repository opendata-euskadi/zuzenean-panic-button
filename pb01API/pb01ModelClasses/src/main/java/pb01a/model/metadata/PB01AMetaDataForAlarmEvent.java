package pb01a.model.metadata;

import java.util.Date;

import lombok.Getter;
import pb01a.model.PB01AAlarmEvent;
import pb01a.model.oids.PB01APanicButtonOIDs.PB01AAlarmEventOID;
import r01f.locale.Language;
import r01f.model.metadata.annotations.DescInLang;
import r01f.model.metadata.annotations.MetaDataForField;
import r01f.model.metadata.annotations.MetaDataForType;
import r01f.model.metadata.annotations.Storage;

/**
 * Describes a {@link PB01AAlarmEvent}
 */
@MetaDataForType(modelObjTypeCode = PB01APanicButtonModelObjectCodes.ALARM_EVENT_MODEL_OBJ_TYPE_CODE,
			     description = {
						@DescInLang(language=Language.SPANISH, value="Alarm event"),
						@DescInLang(language=Language.BASQUE, value="[eu] Alarm event"),
						@DescInLang(language=Language.ENGLISH, value="Alarm event")
				 })
public abstract class PB01AMetaDataForAlarmEvent
	 	extends PB01AMetaDataForModelObjectBase<PB01AAlarmEventOID>
	 implements PB01AHasFieldsMetaDataForHasOrganization,
	 			PB01AHasFieldsMetaDataForHasOrgDivision,
	 			PB01AHasFieldsMetaDataForHasOrgDivisionService,
	 			PB01AHasFieldsMetaDataForHasOrgDivisionServiceLocation,
	 			PB01AHasFieldsMetaDataForHasWorkPlace {
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	@MetaDataForField(description = {
							@DescInLang(language=Language.SPANISH, value="Fecha/hora en la que se lanza la alarma"),
							@DescInLang(language=Language.BASQUE, value="[eu] Fecha/hora en la que se lanza la alarma"),
							@DescInLang(language=Language.ENGLISH, value="Date/time when the alarm was raisen")
					  },
					  storage = @Storage(indexed=true,
					  					 stored=true))
	@Getter private Date _timeStamp;

}
