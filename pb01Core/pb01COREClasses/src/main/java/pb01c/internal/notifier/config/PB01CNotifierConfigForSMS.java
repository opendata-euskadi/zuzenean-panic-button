package pb01c.internal.notifier.config;

import lombok.Getter;
import lombok.experimental.Accessors;
import pb01a.common.internal.P01AAppCodes;
import r01f.core.services.notifier.config.NotifierAppDependentConfigBase;
import r01f.core.services.notifier.config.NotifierEnums.NotifierType;
import r01f.types.Path;
import r01f.util.types.Strings;
import r01f.xmlproperties.XMLPropertiesForAppComponent;

/**
 * App dependent config for SMS notifier
 */
@Accessors(prefix="_")
public class PB01CNotifierConfigForSMS
     extends NotifierAppDependentConfigBase {
/////////////////////////////////////////////////////////////////////////////////////////
//	FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	@Getter private final Path _msgTemplatePath;
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01CNotifierConfigForSMS(final Path msgTemplatePath) {
		super(NotifierType.SMS,
			  P01AAppCodes.CORE_APPCODE);
		_msgTemplatePath = msgTemplatePath;
	}
	public PB01CNotifierConfigForSMS(final XMLPropertiesForAppComponent props) {
		super(NotifierType.SMS,
			  props);
		 _msgTemplatePath = props.propertyAt(_xPathBase() + "/msgTemplate")
								 .asPath(Strings.customized("{}/notifier/{}{}MessageTemplate.vm",	// ie: pb01c/notifier/pb01cEMAILMessageTemplate.vm
										  					_appCode,
															_appCode,_type));
	}
	public static final PB01CNotifierConfigForSMS createFrom(final XMLPropertiesForAppComponent props) {
		return new PB01CNotifierConfigForSMS(props);
	}
}
