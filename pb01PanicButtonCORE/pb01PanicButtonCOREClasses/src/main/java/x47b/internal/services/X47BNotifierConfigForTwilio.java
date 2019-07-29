package x47b.internal.services;

import lombok.Getter;
import lombok.experimental.Accessors;
import r01f.twilio.TwilioConfig;
import r01f.types.Path;
import r01f.types.url.Url;
import r01f.xmlproperties.XMLPropertiesForAppComponent;

@Accessors(prefix="_")
public class X47BNotifierConfigForTwilio
     extends X47BNotifierServiceConfigBase {
/////////////////////////////////////////////////////////////////////////////////////////
//	FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	@Getter private final TwilioConfig _twilioConfig;
	@Getter private final Url _twmlUrl;
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR  
/////////////////////////////////////////////////////////////////////////////////////////
	private X47BNotifierConfigForTwilio(final boolean enabled,
							 			final Path alertMsgTemplatePath,
							 			final TwilioConfig twilioConfig,final Url twnlUrl) {
		super("voice",
			  enabled,
			  alertMsgTemplatePath);
		_twilioConfig = twilioConfig;
		_twmlUrl = twnlUrl;
	}
	private X47BNotifierConfigForTwilio(final XMLPropertiesForAppComponent props) {
		super("voice",
			  props);
		_twilioConfig = TwilioConfig.createFrom(props,
											    "notifier");
		_twmlUrl = props.propertyAt("/notifier/notifiers/voice/twmlUrl")
					   .asUrl(Url.from("http://demo.twilio.com/docs/voice.xml"));
	}
	public static final X47BNotifierConfigForTwilio createFrom(final XMLPropertiesForAppComponent props) {
		return new X47BNotifierConfigForTwilio(props);
	}
}
