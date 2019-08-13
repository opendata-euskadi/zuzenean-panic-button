package x47b.internal.services;

import lombok.Getter;
import lombok.experimental.Accessors;
import r01f.mail.config.JavaMailSenderConfig;
import r01f.mail.config.JavaMailSenderConfigBuilder;
import r01f.mail.model.EMailRFC822Address;
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
	@Getter private final EMailRFC822Address _from;
	@Getter private final Path _msgImage;
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	private X47BNotifierConfigForEMail(final boolean enabled,
							 		   final Path alertMsgTemplatePath,
							 		   final JavaMailSenderConfig mailSenderConfig,
							 		   final EMail fromMail,final String fromName,
							 		   final Path msgImage) {
		super("mail",
			  enabled,
			  alertMsgTemplatePath);
		_mailSenderConfig = mailSenderConfig;
		_msgImage = msgImage;
		_from = EMailRFC822Address.of(fromMail,fromName);
	}
	private X47BNotifierConfigForEMail(final XMLPropertiesForAppComponent props) {
		super("mail",
			  props);
		_mailSenderConfig = JavaMailSenderConfigBuilder.createFrom(props,
							 					  				   "notifier");
		EMail fromMail = props.propertyAt("/notifier/notifiers/mail/from/@mail").asEMail("Zuzenean-No-Reply@euskadi.eus");
		String fromName = props.propertyAt("/notifier/notifiers/mail/from").asString("Zuzenean");

		_from = EMailRFC822Address.of(fromMail,fromName);
		_msgImage = props.propertyAt("/notifier/mail/msgHeaderImage").asPath("x47b/notifier/x47bLogo.gif");
	}
	public static final X47BNotifierConfigForEMail createFrom(final XMLPropertiesForAppComponent props) {
		return new X47BNotifierConfigForEMail(props);
	}
}
