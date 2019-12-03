package x47b.internal.notifier.config;

import lombok.Getter;
import lombok.experimental.Accessors;
import r01f.core.services.notifier.config.NotifierAppDependentConfigBase;
import r01f.core.services.notifier.config.NotifierEnums.NotifierType;
import r01f.types.Path;
import r01f.types.url.Url;
import r01f.util.types.Strings;
import r01f.xmlproperties.XMLPropertiesForAppComponent;
import x47b.common.internal.X47BAppCodes;

/**
 * App dependent config for voice notifier
 */
@Accessors(prefix="_")
public class X47BNotifierConfigForVoice
     extends NotifierAppDependentConfigBase {
/////////////////////////////////////////////////////////////////////////////////////////
//	FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	@Getter private final Path _msgTemplatePath;
	@Getter private final Url _twmlUrl;
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BNotifierConfigForVoice(final Path msgTemplatePath) {
		super(NotifierType.VOICE,
			  X47BAppCodes.CORE_APPCODE);
		_msgTemplatePath = msgTemplatePath;
		_twmlUrl = null;	// TODO review!
	}
	public X47BNotifierConfigForVoice(final XMLPropertiesForAppComponent props) {
		super(NotifierType.VOICE,
			  props.getAppCode());
		 _msgTemplatePath = props.propertyAt(_xPathBase() + "/msgTemplate")
								 .asPath(Strings.customized("{}/notifier/{}{}MessageTemplate.vm",	// ie: x47b/notifier/x47bEMAILMessageTemplate.vm
										  					_appCode,
															_appCode,_type));
		_twmlUrl = props.propertyAt("/notifier/notifiers/" + _type.asStringLowerCase() + "/twmlUrl")
					   .asUrl(Url.from("http://demo.twilio.com/docs/voice.xml"));
	}
	public static final X47BNotifierConfigForVoice createFrom(final XMLPropertiesForAppComponent props) {
		return new X47BNotifierConfigForVoice(props);
	}
}
