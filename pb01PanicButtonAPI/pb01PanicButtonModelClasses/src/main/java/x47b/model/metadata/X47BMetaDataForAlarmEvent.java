package x47b.model.metadata;

import java.util.Date;

import lombok.Getter;
import r01f.locale.Language;
import r01f.model.metadata.annotations.DescInLang;
import r01f.model.metadata.annotations.MetaDataForField;
import r01f.model.metadata.annotations.MetaDataForType;
import r01f.model.metadata.annotations.Storage;
import x47b.model.X47BAlarmEvent;
import x47b.model.oids.X47BPanicButtonOIDs.X47BAlarmEventOID;

/**
 * Describes a {@link X47BAlarmEvent}
 */
@MetaDataForType(modelObjTypeCode = X47BPanicButtonModelObjectCodes.ALARM_EVENT_MODEL_OBJ_TYPE_CODE,
			     description = {
						@DescInLang(language=Language.SPANISH, value="Alarm event"),
						@DescInLang(language=Language.BASQUE, value="[eu] Alarm event"),
						@DescInLang(language=Language.ENGLISH, value="Alarm event")
				 })
public abstract class X47BMetaDataForAlarmEvent
	 	extends X47BMetaDataForModelObjectBase<X47BAlarmEventOID>
	 implements X47BHasFieldsMetaDataForHasOrganization,
	 			X47BHasFieldsMetaDataForHasOrgDivision,
	 			X47BHasFieldsMetaDataForHasOrgDivisionService,
	 			X47BHasFieldsMetaDataForHasOrgDivisionServiceLocation,
	 			X47BHasFieldsMetaDataForHasWorkPlace {	
/////////////////////////////////////////////////////////////////////////////////////////
// 	
/////////////////////////////////////////////////////////////////////////////////////////
	@MetaDataForField(description = {
							@DescInLang(language=Language.SPANISH, value="Fecha/hora en la que se lanzó la alarma"),
							@DescInLang(language=Language.BASQUE, value="[eu] Fecha/hora en la que se lanzó la alarma"),
							@DescInLang(language=Language.ENGLISH, value="Date/time when the alarm was raisen")
					  },
					  storage = @Storage(indexed=true, 
					  					 stored=true))
	@Getter private Date _timeStamp;

}
