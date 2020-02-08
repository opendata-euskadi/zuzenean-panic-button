package pb01c.internal.notifier.config;

import lombok.Getter;
import lombok.experimental.Accessors;
import pb01a.common.internal.P01AAppCodes;
import r01f.core.services.notifier.config.NotifierAppDependentConfigBase;
import r01f.core.services.notifier.config.NotifierEnums.NotifierType;
import r01f.types.Path;
import r01f.types.url.Url;
import r01f.util.types.Strings;
import r01f.xmlproperties.XMLPropertiesForAppComponent;

/**
 * App dependent config for voice notifier
 */
@Accessors(prefix="_")
public class PB01CNotifierConfigForVoice
     extends NotifierAppDependentConfigBase {
/////////////////////////////////////////////////////////////////////////////////////////
//	FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	@Getter private final Path _msgTemplatePath;
	@Getter private final Url _twmlUrl;
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01CNotifierConfigForVoice(final Path msgTemplatePath) {
		super(NotifierType.VOICE,
			  P01AAppCodes.CORE_APPCODE);
		_msgTemplatePath = msgTemplatePath;
		_twmlUrl = null;	// TODO review!
	}
	public PB01CNotifierConfigForVoice(final XMLPropertiesForAppComponent props) {
		super(NotifierType.VOICE,
			  props.getAppCode());
		 _msgTemplatePath = props.propertyAt(_xPathBase() + "/msgTemplate")
								 .asPath(Strings.customized("{}/notifier/{}{}MessageTemplate.vm",	// ie: pb01c/notifier/pb01cEMAILMessageTemplate.vm
										  					_appCode,
															_appCode,_type));
		_twmlUrl = props.propertyAt("/notifier/notifiers/" + _type.asStringLowerCase() + "/twmlUrl")
					   .asUrl(Url.from("http://demo.twilio.com/docs/voice.xml"));
	}
	public static final PB01CNotifierConfigForVoice createFrom(final XMLPropertiesForAppComponent props) {
		return new PB01CNotifierConfigForVoice(props);
	}
}
