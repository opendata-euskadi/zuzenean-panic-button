package pb01a.model;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import r01f.validation.ObjectValidationResult;
import r01f.validation.ObjectValidationResultBuilder;
import r01f.validation.Validates;

@NoArgsConstructor(access=AccessLevel.PRIVATE)
public abstract class PB01AAlarmEventValidators {
/////////////////////////////////////////////////////////////////////////////////////////
//	Alarm Event	
/////////////////////////////////////////////////////////////////////////////////////////
	public static Validates<PB01AAlarmEvent> createAlarmEventObjectValidator() {
		return new Validates<PB01AAlarmEvent>() {
						@Override
						public ObjectValidationResult<PB01AAlarmEvent> validate(final PB01AAlarmEvent obj) {
							// this valid?
							ObjectValidationResult<PB01AAlarmEvent> thisValidResult = null;
							// The alarm MUST have an organization, division, service, location and workPlace
							if (obj.getOrganization() == null || obj.getOrganization().getOid() == null || obj.getOrganization().getId() == null
							 || obj.getDivision() == null || obj.getDivision().getOid() == null || obj.getDivision().getId() == null
							 || obj.getService() == null || obj.getService().getOid() == null || obj.getService().getId() == null
							 || obj.getLocation() == null || obj.getLocation().getOid() == null || obj.getLocation().getId() == null
							 || obj.getWorkPlace() == null || obj.getWorkPlace().getOid() == null || obj.getWorkPlace().getId() == null) {
								thisValidResult = ObjectValidationResultBuilder.on(obj)
																    .isNotValidBecause("The alarm MUST have organization, division, service, location and workPlace references!");
							}
							// combine
							return thisValidResult;
						}
			   };
	}
}
