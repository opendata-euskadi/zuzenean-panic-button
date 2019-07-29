package x47b.model;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import r01f.validation.ObjectValidationResult;
import r01f.validation.ObjectValidationResultBuilder;
import r01f.validation.Validates;

@NoArgsConstructor(access=AccessLevel.PRIVATE)
public abstract class X47BAlarmEventValidators {
/////////////////////////////////////////////////////////////////////////////////////////
//	Alarm Event	
/////////////////////////////////////////////////////////////////////////////////////////
	public static Validates<X47BAlarmEvent> createAlarmEventObjectValidator() {
		return new Validates<X47BAlarmEvent>() {
						@Override
						public ObjectValidationResult<X47BAlarmEvent> validate(final X47BAlarmEvent obj) {
							// this valid?
							ObjectValidationResult<X47BAlarmEvent> thisValidResult = null;
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
