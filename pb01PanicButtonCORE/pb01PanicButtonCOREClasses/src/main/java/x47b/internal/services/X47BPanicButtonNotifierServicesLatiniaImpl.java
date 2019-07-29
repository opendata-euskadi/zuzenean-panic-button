package x47b.internal.services;

import java.io.StringWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import lombok.extern.slf4j.Slf4j;
import r01f.internal.R01F;
import r01f.model.latinia.LatiniaRequestMessage;
import r01f.model.latinia.LatiniaResponse;
import r01f.services.latinia.LatiniaService;
import r01f.types.Path;
import r01f.types.contact.Phone;
import r01f.util.types.collections.CollectionUtils;
import x47b.model.X47BAlarmMessage;
import x47b.model.X47BAlarmMessageEntityAbstracts.X47BAlarmMessageAbstractForDivision;
import x47b.model.X47BAlarmMessageEntityAbstracts.X47BAlarmMessageAbstractForLocation;
import x47b.model.X47BAlarmMessageEntityAbstracts.X47BAlarmMessageAbstractForOrganization;
import x47b.model.X47BAlarmMessageEntityAbstracts.X47BAlarmMessageAbstractForService;
import x47b.model.X47BAlarmMessageEntityAbstracts.X47BAlarmMessageAbstractForWorkPlace;

/**
 * Latinia notifier, sends SMS to Latinia services.
 */
@Singleton
@Slf4j
public class X47BPanicButtonNotifierServicesLatiniaImpl
     extends X47BPanicButtonNotifierServicesBase<X47BNotifierConfigForLatinia> {
/////////////////////////////////////////////////////////////////////////////////////////
//  FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Latinia services
	 */
	private final LatiniaService _latiniaService;
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTORS
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public X47BPanicButtonNotifierServicesLatiniaImpl(final X47BNotifierConfigForLatinia config,final LatiniaService latiniaService,
												      final VelocityEngine velocityEngine) {
		super(config,
			  velocityEngine);
		_latiniaService = latiniaService;
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  METHODS
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void sendNotification(final X47BAlarmMessage alarmMessage) {
		boolean enabled = _config.isEnabled();
		if (!enabled) {
			log.warn("Instant message sending is DISABLED!");
			return;
		}
		log.info("==============>SEND ALERT EVENT NOTIFICATION BY (latinia)!!!");
		Collection<Phone> phones = alarmMessage.getPhonesSanitized();
		if (CollectionUtils.hasData(phones)) {
			// We must send "one message per phone" because when sends Latinia's priority messages, works only with single phone per message.
			for(final Phone currPhone:phones) {
				// Transform X47BAlarmMessage to X47BLatiniaRequestMessage
				// BEWARE! 	Do NOT set timestamp because the message MUST immediately delivered,
				//			(timestamp should be used when delivering deferred messages).
				LatiniaRequestMessage latiniaMsg = new LatiniaRequestMessage();
				latiniaMsg.setAcknowledge("S"); // Telephone company must send acknowledge to latinia service,
												// X47B does NOT received this confirmation directly
				latiniaMsg.setMessageContent(_composeIMMessage(_velocityEngine,_config.getAlertMsgTemplatePath(),
															   alarmMessage.getOrganization(),
															   alarmMessage.getDivision(),
															   alarmMessage.getService(),
															   alarmMessage.getLocation(),
															   alarmMessage.getWorkPlace()));

				// One phone
				latiniaMsg.setReceiverNumbers(currPhone.asStringWithoutCountryCode());

				// Send Message to Latinia service
				log.warn("\t--> Sending an instant message to: {}",currPhone.asStringWithoutCountryCode());
				LatiniaResponse response = _latiniaService.sendNotification(latiniaMsg);

				//TODO Write response into database
//					X47BClientAPI api = X47BClientAPIProvider.getDefaultApi();
//					X47BAlarmEvent alarmEvent = api.alarmEventsAPI()
//													.getForCRUD()
//													.load(alarmMessage.getAlarmEventOid());
//					X47BNotifierResponse alarmResponse = alarmEvent.getAlarmNotificationMsg() != null ? alarmEvent.getAlarmNotificationMsg()
//																									  : new X47BNotifierResponse("");
			}
		} else {
			log.warn("\t--> there aren�t phones to send message...");
		}
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	PRIVATE METHODS
/////////////////////////////////////////////////////////////////////////////////////////
	private static String _composeIMMessage(final VelocityEngine velocityEngine,final Path alertMsgTemplatePath,
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
