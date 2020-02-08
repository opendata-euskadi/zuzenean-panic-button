package pb01c.internal.notifier;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import pb01a.model.PB01AAlarmMessage;
import pb01a.model.PB01AAlarmMessageEntityAbstracts.PB01AAlarmMessageAbstractForDivision;
import pb01a.model.PB01AAlarmMessageEntityAbstracts.PB01AAlarmMessageAbstractForLocation;
import pb01a.model.PB01AAlarmMessageEntityAbstracts.PB01AAlarmMessageAbstractForOrganization;
import pb01a.model.PB01AAlarmMessageEntityAbstracts.PB01AAlarmMessageAbstractForService;
import pb01a.model.PB01AAlarmMessageEntityAbstracts.PB01AAlarmMessageAbstractForWorkPlace;
import pb01c.internal.notifier.config.PB01CNotifierConfigForVoice;
import r01f.core.services.notifier.NotifierServiceForVoicePhoneCall;
import r01f.core.services.notifier.config.NotifierConfigForVoice;
import r01f.internal.R01F;
import r01f.patterns.Factory;
import r01f.types.Path;
import r01f.util.types.collections.CollectionUtils;

/**
 * A notifier that makes a voice call and locutates the message
 */
@Slf4j
@Singleton
public class PB01CPanicButtonNotifiereVoiceImpl
     extends PB01CPanicButtonNotifierBase<NotifierConfigForVoice> {
/////////////////////////////////////////////////////////////////////////////////////////
//  FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	@Getter private final NotifierServiceForVoicePhoneCall _voiceNotifier;
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTORS
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public PB01CPanicButtonNotifiereVoiceImpl(final NotifierConfigForVoice config,final NotifierServiceForVoicePhoneCall voiceNotifier,
											 final VelocityEngine velocityEngine) {
		super(config,
			  velocityEngine);
		_voiceNotifier = voiceNotifier;
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  METHODS
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void sendNotification(final PB01AAlarmMessage alarmMessage) {
		if (!this.isEnabledConsidering(_voiceNotifier)) {
			log.warn("Voice notifier is DISABLED!");
			return;
		}
		log.warn("[VoiceNotifier ({})]================================================",
				 _voiceNotifier.getClass().getSimpleName());
		if (CollectionUtils.isNullOrEmpty(alarmMessage.getPhonesSanitized())) {
			log.warn("... NO phones to notify to");
			return;
		}
		_voiceNotifier.notifyAll(_config.getFrom(),alarmMessage.getPhonesSanitized(),
								 new Factory<String>() {
										@Override
										public String create() {
											return _composeVoiceMessage(_velocityEngine,_config.getAppConfigAs(PB01CNotifierConfigForVoice.class)
																							   .getMsgTemplatePath(),
																		alarmMessage.getOrganization(),
																		alarmMessage.getDivision(),
																		alarmMessage.getService(),
																		alarmMessage.getLocation(),
																		alarmMessage.getWorkPlace());
										}
								 });
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	private static String _composeVoiceMessage(final VelocityEngine velocityEngine,final Path alertMsgTemplatePath,
											   final PB01AAlarmMessageAbstractForOrganization org,
											   final PB01AAlarmMessageAbstractForDivision div,
											   final PB01AAlarmMessageAbstractForService srvc,
									 		   final PB01AAlarmMessageAbstractForLocation loc,
									 		   final PB01AAlarmMessageAbstractForWorkPlace workPlace) {
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
