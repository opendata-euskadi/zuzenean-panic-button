package x47b.internal.services;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import com.twilio.sdk.TwilioRestException;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import r01f.cloud.twilio.TwilioService;
import r01f.internal.R01F;
import r01f.types.Path;
import r01f.types.contact.Phone;
import r01f.types.url.Url;
import x47b.model.X47BAlarmMessage;
import x47b.model.X47BAlarmMessageEntityAbstracts.X47BAlarmMessageAbstractForDivision;
import x47b.model.X47BAlarmMessageEntityAbstracts.X47BAlarmMessageAbstractForLocation;
import x47b.model.X47BAlarmMessageEntityAbstracts.X47BAlarmMessageAbstractForOrganization;
import x47b.model.X47BAlarmMessageEntityAbstracts.X47BAlarmMessageAbstractForService;
import x47b.model.X47BAlarmMessageEntityAbstracts.X47BAlarmMessageAbstractForWorkPlace;

/**
 * A notifier that just logs the message
 */
@Singleton
@Slf4j
public class X47BPanicButtonNotifierServicesVoiceImpl
     extends X47BPanicButtonNotifierServicesBase<X47BNotifierConfigForTwilio> {
/////////////////////////////////////////////////////////////////////////////////////////
//  FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	@Getter private final TwilioService _twilioService;
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTORS
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public X47BPanicButtonNotifierServicesVoiceImpl(final X47BNotifierConfigForTwilio twilioConfig,final TwilioService twilioService,
													final VelocityEngine velocityEngine) {
		super(twilioConfig,
			  velocityEngine);
		_twilioService = twilioService;
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  METHODS
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void sendNotification(final X47BAlarmMessage alarmMessage) {
		boolean isEnabled = this.isEnabled() & _twilioService.isEnabled();
		if (isEnabled) {
			log.info("==============>SEND ALERT EVENT NOTIFICATION BY VOICE (twilio)");
			if (alarmMessage.getPhonesSanitized() != null) {
				try {
					for (Phone phone : alarmMessage.getPhonesSanitized()) {
						Phone intPhone = Phone.of(phone.asStringEnsuringCountryCode("+34"));	// ensure the phone contains +34
						log.warn("\t--> sending voice message to {}",intPhone);
						Url twmlUrl = Url.from("https://dl.dropboxusercontent.com/u/1264561/testTwilioTWML.xml");
						_twilioService.makeCall(intPhone,
												twmlUrl);
					}
				} catch (TwilioRestException restEx) {
					restEx.printStackTrace(System.out);
				}
			} else {
				log.warn("\t--> there aren�t phones to send message...");
			}
		}
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	private static String _composeVoiceMessage(final VelocityEngine velocityEngine,final Path alertMsgTemplatePath,
											   final X47BAlarmMessageAbstractForOrganization org,
											   final X47BAlarmMessageAbstractForDivision div,
											   final X47BAlarmMessageAbstractForService srvc,
									 		   final X47BAlarmMessageAbstractForLocation loc,
									 		   final X47BAlarmMessageAbstractForWorkPlace workPlace) {
	    // Text... using velocity
	    Map<String,Object> model = new HashMap<String,Object>();
	    model.put("alarmDate",_alarmDateFormated());
	    model.put("org",org.getDefaultName());
	    model.put("div",div.getDefaultName());
	    model.put("srvc",srvc.getDefaultName());
	    model.put("loc",loc.getDefaultName());
	    model.put("workPlace",workPlace.getDefaultName());
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
