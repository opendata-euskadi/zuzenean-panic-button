package x47b.internal.services;

import lombok.Getter;
import lombok.experimental.Accessors;
import r01f.mail.config.JavaMailSenderConfig;
import r01f.mail.config.JavaMailSenderConfigBuilder;
import r01f.types.Path;
import r01f.types.contact.EMail;
import r01f.xmlproperties.XMLPropertiesForAppComponent;

@Accessors(prefix="_")
public class X47BNotifierConfigForEMail
     extends X47BNotifierServiceConfigBase {
/////////////////////////////////////////////////////////////////////////////////////////
//	FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	@Getter private final JavaMailSenderConfig _mailSenderConfig;
	@Getter private final EMail _from;
	@Getter private final Path _msgImage;
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	private X47BNotifierConfigForEMail(final boolean enabled,
							 		   final Path alertMsgTemplatePath,
							 		   final JavaMailSenderConfig mailSenderConfig,
							 		   final EMail from,final Path msgImage) {
		super("mail",
			  enabled,
			  alertMsgTemplatePath);
		_mailSenderConfig = mailSenderConfig;
		_msgImage = msgImage;
		_from = from;
	}
	private X47BNotifierConfigForEMail(final XMLPropertiesForAppComponent props) {
		super("mail",
			  props);
		_mailSenderConfig = JavaMailSenderConfigBuilder.createFrom(props,
							 					  				   "notifier");
		_from = props.propertyAt("/notifier/notifiers/mail/from").asEMail("Zuzenean-No-Reply@euskadi.eus");
		_msgImage = props.propertyAt("/notifier/mail/msgHeaderImage").asPath("x47b/notifier/x47bLogo.gif");
	}
	public static final X47BNotifierConfigForEMail createFrom(final XMLPropertiesForAppComponent props) {
		return new X47BNotifierConfigForEMail(props);
	}
}
