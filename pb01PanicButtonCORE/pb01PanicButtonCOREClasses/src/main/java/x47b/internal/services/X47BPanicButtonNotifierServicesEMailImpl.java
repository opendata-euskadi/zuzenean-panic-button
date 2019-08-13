package x47b.internal.services;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import r01f.internal.R01F;
import r01f.mail.model.EMailRFC822Address;
import r01f.service.ServiceCanBeDisabled;
import r01f.types.Path;
import r01f.types.contact.EMail;
import r01f.util.types.Strings;
import r01f.util.types.collections.CollectionUtils;
import x47b.model.X47BAlarmMessage;
import x47b.model.X47BAlarmMessageEntityAbstracts.X47BAlarmMessageAbstractForDivision;
import x47b.model.X47BAlarmMessageEntityAbstracts.X47BAlarmMessageAbstractForLocation;
import x47b.model.X47BAlarmMessageEntityAbstracts.X47BAlarmMessageAbstractForOrganization;
import x47b.model.X47BAlarmMessageEntityAbstracts.X47BAlarmMessageAbstractForService;
import x47b.model.X47BAlarmMessageEntityAbstracts.X47BAlarmMessageAbstractForWorkPlace;

/**
 * EMail notifier
 */
@Slf4j
public class X47BPanicButtonNotifierServicesEMailImpl
     extends X47BPanicButtonNotifierServicesBase<X47BNotifierConfigForEMail> {
/////////////////////////////////////////////////////////////////////////////////////////
//  FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	@Getter private final JavaMailSender _mailSender;
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public X47BPanicButtonNotifierServicesEMailImpl(final X47BNotifierConfigForEMail mailSenderConfig,final JavaMailSender mailSender,
												    final VelocityEngine velocityEngine) {
		super(mailSenderConfig,
			  velocityEngine);
		_mailSender = mailSender;
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  METHODS
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void sendNotification(final X47BAlarmMessage alarmMessage) {
		boolean isEnabled = this.isEnabled();
		if (_mailSender instanceof ServiceCanBeDisabled) {
			ServiceCanBeDisabled serviceCanBeDisabled = (ServiceCanBeDisabled)_mailSender;
			if (serviceCanBeDisabled.isDisabled()) isEnabled = false;
		}
		if (isEnabled) {
			log.info("==============>SEND ALERT EVENT NOTIFICATION BY EMAIL");
			if (CollectionUtils.hasData(alarmMessage.getMails())) {
				for (EMail mail : alarmMessage.getMails()) {
					_sendEMailMessage(mail,
									   alarmMessage.getOrganization(),
									   alarmMessage.getDivision(),
									   alarmMessage.getService(),
									   alarmMessage.getLocation(),
									   alarmMessage.getWorkPlace());
				}
			} else {
				log.warn("\t--> there aren�t eMails to send message...");
			}
		} else {
			log.warn("Mail sending is DISABLED");
		}
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Send a mail message when an alarm message is received
	 * @param to
	 * @param org
	 * @param log
	 * @param workPlace
	 */
	private void _sendEMailMessage(final EMail to,
								   final X47BAlarmMessageAbstractForOrganization org,
								   final X47BAlarmMessageAbstractForDivision div,
								   final X47BAlarmMessageAbstractForService srvc,
								   final X47BAlarmMessageAbstractForLocation loc,
								   final X47BAlarmMessageAbstractForWorkPlace workPlace) {
		log.info("\t-->sending email to {} using {}",to,_mailSender.getClass());

		// [1] - Create a MimeMessagePreparator
		final String subject = _composeMailMessageSubject(org,loc,workPlace);
		final String body = _composeMailMessageBody(_velocityEngine,_config.getAlertMsgTemplatePath(),
													org,div,srvc,loc,workPlace);

//		EMailMessage msg = EMailMessageBuilder.from(EMailRFC822Address.of("me@futuretelematics.net"))
//											  .to(EMailRFC822Address.of(to))
//											  .noCC().noBCC()
//											  .withSubject(subject)
//											  .withPlainTextBody(body)
//											  .build();
//		// one way to send
//		try {
//			AWSClientConfig cfg = AWSClientConfigBuilder.region(Region.EU_WEST_1)
//											.using(AWSAccessKey.forId("AKIASOHENLOJWOPU37CL"),
//												   AWSAccessSecret.forId("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"))
//											.charset(Charset.defaultCharset())
//											.build();
//			AWSSESClientConfig sesCfg = new AWSSESClientConfig(cfg);
//			AWSSESClient sesCli = new AWSSESClient(sesCfg);
//
//			SesResponse res = sesCli.sendEMail(msg);
//			log.warn("EMail message sent (id={}",
//					 ((SendEmailResponse)res).messageId());
//		} catch (Throwable th) {
//			th.printStackTrace();
//		}
// 		// Another way
//		try {
//			MimeMessage mimeMsg = EMailMimeMessages.createMimeMessageFor(msg)
//												   .withDefaultCharset()
//												   .usingDefaultSession()
//												   .noAttachments()
//												   .build();
//			_mailSender.send(mimeMsg);
//		} catch (Throwable th) {
//			th.printStackTrace();
//		}

		// the proper way
		MimeMessagePreparator msgPreparator = new MimeMessagePreparator() {
													@Override
										            public void prepare(final MimeMessage mimeMessage) throws Exception {
														_createMimeMessage(mimeMessage,
																		   to,
																		   _config.getFrom(),
																		   subject,
																		   body);
										            }
											  };
		  // [2] - Send the message
        _mailSender.send(msgPreparator);
	}
	private void _createMimeMessage(final MimeMessage mimeMessage,
									final EMail to,
									final EMailRFC822Address from,
									final String subject,
									final String text) throws MessagingException {
	    MimeMessageHelper message = new MimeMessageHelper(mimeMessage,
	    												  true);	// multi-part!!
	    // To & From
	    message.setTo(to.asString());
	    message.setFrom(from.asRFC822Address());

	    // Subject
	    message.setSubject(subject);

	    // Text
	    message.setText(text,
	    				true);	// html message

	    // Header image (ALWAYS AFTER message.setText())
        ClassPathResource res = new ClassPathResource(_config.getAlertMsgTemplatePath()
        													 .asRelativeString());
        message.addInline("logo",res);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  MAIL MESSAGE COMPOSING
/////////////////////////////////////////////////////////////////////////////////////////
	private static String _composeMailMessageSubject(final X47BAlarmMessageAbstractForOrganization org,
											   		 final X47BAlarmMessageAbstractForLocation loc,
											   		 final X47BAlarmMessageAbstractForWorkPlace workPlace) {
		String outSubject = null;
		if (org != null && loc != null && workPlace != null) {
			outSubject = Strings.customized("[X47B][{}]: Alarm at {} > {} > {}",
											_alarmDateFormated(),
											org.getDefaultName(),
											loc.getDefaultName(),
											workPlace.getDefaultName());
		}
		else if (org != null && loc != null) {
			outSubject = Strings.customized("[X47B][{}]: Alarm at {} > {}",
											_alarmDateFormated(),
											org.getDefaultName(),
										  	loc.getDefaultName());
		} else if (org != null) {
			outSubject = Strings.customized("[X47B][{}]: Alarm at {}",
											_alarmDateFormated(),
											org.getDefaultName());
		} else {
			throw new IllegalStateException("The alarm event data is NOT valid!!");
		}
		return outSubject;
	}
	private static String _composeMailMessageBody(final VelocityEngine velocityEngine,final Path alertMsgTemplatePath,
												  final X47BAlarmMessageAbstractForOrganization org,
												  final X47BAlarmMessageAbstractForDivision div,
												  final X47BAlarmMessageAbstractForService srvc,
										   		  final X47BAlarmMessageAbstractForLocation loc,
										   		  final X47BAlarmMessageAbstractForWorkPlace workPlace) {
	    // Text... using velocity
	    Map<String,Object> model = new HashMap<String,Object>();
	    model.put("alarmDate",_alarmDateFormated());
	    if (org != null) {
	    	model.put("orgId",org.getEntityId().asString());
	    	model.put("org",org.getDefaultName());
	    }
	    if (div != null) {
	    	model.put("divId",div.getEntityId().asString());
	    	model.put("div",div.getDefaultName());
	    }
	    if (srvc != null) {
	    	model.put("srvcId",srvc.getEntityId().asString());
	    	model.put("srvc",srvc.getDefaultName());
	    }
	    if (loc != null) {
	    	model.put("locId",loc.getEntityId().asString());
	    	model.put("loc",loc.getDefaultName());
	    }
	    if (workPlace != null) {
	    	model.put("workPlaceId",workPlace.getEntityId().asString());
	    	model.put("workPlace",workPlace.getDefaultName());
	    }
	    VelocityContext context = new VelocityContext(model);
	    StringWriter sw = new StringWriter();
		velocityEngine.mergeTemplate(alertMsgTemplatePath.asRelativeString(),
			  						  R01F.DEFAULT_CHARSET.name(),
			  						  context,
			  						  sw);

		sw.flush();
	    return sw.toString();
	}
}
