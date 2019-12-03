package x47b.internal.notifier.config;

import lombok.Getter;
import lombok.experimental.Accessors;
import r01f.core.services.notifier.config.NotifierAppDependentConfigBase;
import r01f.core.services.notifier.config.NotifierEnums.NotifierType;
import r01f.types.Path;
import r01f.util.types.Strings;
import r01f.xmlproperties.XMLPropertiesForAppComponent;
import x47b.common.internal.X47BAppCodes;

/**
 * App dependent config for SMS notifier
 */
@Accessors(prefix="_")
public class X47BNotifierConfigForSMS
     extends NotifierAppDependentConfigBase {
/////////////////////////////////////////////////////////////////////////////////////////
//	FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	@Getter private final Path _msgTemplatePath;
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BNotifierConfigForSMS(final Path msgTemplatePath) {
		super(NotifierType.SMS,
			  X47BAppCodes.CORE_APPCODE);
		_msgTemplatePath = msgTemplatePath;
	}
	public X47BNotifierConfigForSMS(final XMLPropertiesForAppComponent props) {
		super(NotifierType.SMS,
			  props);
		 _msgTemplatePath = props.propertyAt(_xPathBase() + "/msgTemplate")
								 .asPath(Strings.customized("{}/notifier/{}{}MessageTemplate.vm",	// ie: x47b/notifier/x47bEMAILMessageTemplate.vm
										  					_appCode,
															_appCode,_type));
	}
	public static final X47BNotifierConfigForSMS createFrom(final XMLPropertiesForAppComponent props) {
		return new X47BNotifierConfigForSMS(props);
	}
}
