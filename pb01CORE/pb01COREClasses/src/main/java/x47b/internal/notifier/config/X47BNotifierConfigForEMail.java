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
 * App dependent config for EMail notifier
 */
@Accessors(prefix="_")
public class X47BNotifierConfigForEMail
     extends NotifierAppDependentConfigBase {
/////////////////////////////////////////////////////////////////////////////////////////
//	FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	@Getter private final Path _msgTemplatePath;
	@Getter private final Path _msgImagePath;
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BNotifierConfigForEMail(final Path msgTemplatePath,
							 	   	   final Path msgImagePath) {
		super(NotifierType.EMAIL,
			  X47BAppCodes.CORE_APPCODE);
		_msgTemplatePath = msgTemplatePath;
		_msgImagePath = msgImagePath;
	}
	public X47BNotifierConfigForEMail(final XMLPropertiesForAppComponent props) {
		super(NotifierType.EMAIL,
			  props.getAppCode());
 	     // template path
		 _msgTemplatePath = props.propertyAt(_xPathBase() + "/msgTemplate")
								 .asPath(Strings.customized("{}/notifier/{}{}MessageTemplate.vm",	// ie: x47b/notifier/x47bEMAILMessageTemplate.vm
										  					_appCode,
															_appCode,_type));
		_msgImagePath = props.propertyAt(_xPathBase() + "/msgHeaderImage")
						 .asPath(Strings.customized("{}/notifier/{}Logo.gif",
								 					props.getAppCode(),props.getAppCode()));
	}
	public static final X47BNotifierConfigForEMail createFrom(final XMLPropertiesForAppComponent props) {
		return new X47BNotifierConfigForEMail(props);
	}
}
