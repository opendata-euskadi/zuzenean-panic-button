package pb01c.internal.notifier;

import java.io.StringWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.mail.internet.MimeMessage;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;

import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import pb01a.model.PB01AAlarmMessage;
import pb01a.model.PB01AAlarmMessageEntityAbstracts.PB01AAlarmMessageAbstractForDivision;
import pb01a.model.PB01AAlarmMessageEntityAbstracts.PB01AAlarmMessageAbstractForLocation;
import pb01a.model.PB01AAlarmMessageEntityAbstracts.PB01AAlarmMessageAbstractForOrganization;
import pb01a.model.PB01AAlarmMessageEntityAbstracts.PB01AAlarmMessageAbstractForService;
import pb01a.model.PB01AAlarmMessageEntityAbstracts.PB01AAlarmMessageAbstractForWorkPlace;
import pb01c.internal.notifier.config.PB01CNotifierConfigForEMail;
import r01f.core.services.mail.model.EMailRFC822Address;
import r01f.core.services.mail.notifier.JavaMailSenderNotifierService;
import r01f.core.services.notifier.NotifierServiceForEMail;
import r01f.core.services.notifier.config.NotifierConfigForEMail;
import r01f.internal.R01F;
import r01f.patterns.Factory;
import r01f.types.Path;
import r01f.types.contact.EMail;
import r01f.util.types.Strings;
import r01f.util.types.collections.CollectionUtils;

/**
 * EMail notifier
 */
@Slf4j
@Singleton
public class PB01CPanicButtonNotifierEMailImpl
     extends PB01CPanicButtonNotifierBase<NotifierConfigForEMail> {
/////////////////////////////////////////////////////////////////////////////////////////
//  FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	@Getter private final NotifierServiceForEMail _mailNotifier;
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public PB01CPanicButtonNotifierEMailImpl(final NotifierConfigForEMail notifierConfig,final NotifierServiceForEMail notifier,
											final VelocityEngine velocityEngine) {
		super(notifierConfig,
			  velocityEngine);
		_mailNotifier = notifier;
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  METHODS
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void sendNotification(final PB01AAlarmMessage alarmMessage) {
		if (!this.isEnabledConsidering(_mailNotifier)) {
			log.warn("EMail notifier is DISABLED!");
			return;
		}
		log.warn("[EMailNotifier ({})]================================================",
				 _mailNotifier.getClass().getSimpleName());
		if (CollectionUtils.isNullOrEmpty(alarmMessage.getMails())) {
			log.warn("... NO emails to notify to");
			return;
		}
		_sendEMailMessage(FluentIterable.from(alarmMessage.getMails())
										.transform(new Function<EMail,EMailRFC822Address>() {
															@Override
															public EMailRFC822Address apply(final EMail to) {
																return EMailRFC822Address.of(to);
															}
												   })
										.toList(),
						  alarmMessage.getOrganization(),
						  alarmMessage.getDivision(),
						  alarmMessage.getService(),
						  alarmMessage.getLocation(),
						  alarmMessage.getWorkPlace());
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
	private void _sendEMailMessage(final Collection<EMailRFC822Address> to,
								   final PB01AAlarmMessageAbstractForOrganization org,
								   final PB01AAlarmMessageAbstractForDivision div,
								   final PB01AAlarmMessageAbstractForService srvc,
								   final PB01AAlarmMessageAbstractForLocation loc,
								   final PB01AAlarmMessageAbstractForWorkPlace workPlace) {
		log.info("\t-->sending email to {} using {}",
				 to,_mailNotifier.getClass());

		// [1] - Create a MimeMessagePreparator
		final String subject = _composeMailMessageSubject(org,loc,workPlace);
		final String body = _composeMailMessageBody(_velocityEngine,_config.getAppConfigAs(PB01CNotifierConfigForEMail.class)
																		   .getMsgTemplatePath(),
													org,div,srvc,loc,workPlace);

		  // [2] - Send the message
        _mailNotifier.notifyAll(_config.getFrom(),to,
        						// mime message factory
        						new Factory<MimeMessage>() {
										@Override @SneakyThrows
										public MimeMessage create() {
											JavaMailSender mailSender = ((JavaMailSenderNotifierService)_mailNotifier).getSpringJavaMailSender();
											MimeMessage mimeMessage = mailSender.createMimeMessage();
										    MimeMessageHelper msgHelper = new MimeMessageHelper(mimeMessage,
										    												    true);	// multi-part!!
										    // To & From
										    msgHelper.setTo(EMailRFC822Address.multipleAsRFC822Address(to));
										    msgHelper.setFrom(_config.getFrom().asRFC822Address());

										    // Subject & Text
										    msgHelper.setSubject(subject);
										    msgHelper.setText(body,
										    				  true);	// html message
										    // Header image (ALWAYS AFTER message.setText())
									        ClassPathResource res = new ClassPathResource(_config.getAppConfigAs(PB01CNotifierConfigForEMail.class)
									        													 .getMsgImagePath()
									        													 .asRelativeString());
									        msgHelper.addInline("logo",res);
									        return mimeMessage;
										}
								});
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  MAIL MESSAGE COMPOSING
/////////////////////////////////////////////////////////////////////////////////////////
	private static String _composeMailMessageSubject(final PB01AAlarmMessageAbstractForOrganization org,
											   		 final PB01AAlarmMessageAbstractForLocation loc,
											   		 final PB01AAlarmMessageAbstractForWorkPlace workPlace) {
		String outSubject = null;
		if (org != null && loc != null && workPlace != null) {
			outSubject = Strings.customized("[PB01][{}]: Alarm at {} > {} > {}",
											_alarmDateFormated(),
											org.getDefaultName(),
											loc.getDefaultName(),
											workPlace.getDefaultName());
		}
		else if (org != null && loc != null) {
			outSubject = Strings.customized("[PB01][{}]: Alarm at {} > {}",
											_alarmDateFormated(),
											org.getDefaultName(),
										  	loc.getDefaultName());
		} else if (org != null) {
			outSubject = Strings.customized("[PB01][{}]: Alarm at {}",
											_alarmDateFormated(),
											org.getDefaultName());
		} else {
			throw new IllegalStateException("The alarm event data is NOT valid!!");
		}
		return outSubject;
	}
	private static String _composeMailMessageBody(final VelocityEngine velocityEngine,final Path alertMsgTemplatePath,
												  final PB01AAlarmMessageAbstractForOrganization org,
												  final PB01AAlarmMessageAbstractForDivision div,
												  final PB01AAlarmMessageAbstractForService srvc,
										   		  final PB01AAlarmMessageAbstractForLocation loc,
										   		  final PB01AAlarmMessageAbstractForWorkPlace workPlace) {
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
