package x47b.internal.notifier.config;

import lombok.Getter;
import lombok.experimental.Accessors;
import r01f.config.ContainsConfigData;
import r01f.core.services.notifier.config.NotifierConfigForVoice;
import r01f.core.services.notifier.config.NotifierEnums.NotifierImpl;
import r01f.types.Path;
import r01f.types.contact.OwnedContactMean;
import r01f.types.contact.Phone;
import r01f.types.url.Url;
import r01f.util.types.Strings;
import r01f.xmlproperties.XMLPropertiesForAppComponent;
import x47b.common.internal.X47BAppCodes;

@Accessors(prefix="_")
public class X47BNotifierConfigForVoice
     extends NotifierConfigForVoice {
/////////////////////////////////////////////////////////////////////////////////////////
//	FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	@Getter private final Path _msgTemplatePath;
	@Getter private final Url _twmlUrl;
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	private X47BNotifierConfigForVoice(final boolean enabled,
									   final NotifierImpl impl,final ContainsConfigData config,
							 	   	   final OwnedContactMean<Phone> from,
							 	   	   final Path msgTemplatePath) {
		super(X47BAppCodes.CORE_APPCODE,
			  enabled,
			  impl,config,
			  from);
		_msgTemplatePath = msgTemplatePath;
		_twmlUrl = null;	// TODO review!
	}
	private X47BNotifierConfigForVoice(final XMLPropertiesForAppComponent props) {
		super(props);
		 _msgTemplatePath = props.propertyAt(_xPathBaseForCommonProperties() + "/msgTemplate")
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
