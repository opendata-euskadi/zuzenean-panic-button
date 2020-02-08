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
 * App dependent config for EMail notifier
 */
@Accessors(prefix="_")
public class PB01CNotifierConfigForEMail
     extends NotifierAppDependentConfigBase {
/////////////////////////////////////////////////////////////////////////////////////////
//	FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	@Getter private final Path _msgTemplatePath;
	@Getter private final Path _msgImagePath;
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01CNotifierConfigForEMail(final Path msgTemplatePath,
							 	   	   final Path msgImagePath) {
		super(NotifierType.EMAIL,
			  P01AAppCodes.CORE_APPCODE);
		_msgTemplatePath = msgTemplatePath;
		_msgImagePath = msgImagePath;
	}
	public PB01CNotifierConfigForEMail(final XMLPropertiesForAppComponent props) {
		super(NotifierType.EMAIL,
			  props.getAppCode());
 	     // template path
		 _msgTemplatePath = props.propertyAt(_xPathBase() + "/msgTemplate")
								 .asPath(Strings.customized("{}/notifier/{}{}MessageTemplate.vm",	// ie: pb01c/notifier/pb01cEMAILMessageTemplate.vm
										  					_appCode,
															_appCode,_type));
		_msgImagePath = props.propertyAt(_xPathBase() + "/msgHeaderImage")
						 .asPath(Strings.customized("{}/notifier/{}Logo.gif",
								 					props.getAppCode(),props.getAppCode()));
	}
	public static final PB01CNotifierConfigForEMail createFrom(final XMLPropertiesForAppComponent props) {
		return new PB01CNotifierConfigForEMail(props);
	}
}
