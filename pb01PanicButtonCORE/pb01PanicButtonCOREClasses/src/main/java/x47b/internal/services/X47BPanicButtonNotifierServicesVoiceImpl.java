package x47b.internal.services;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import r01f.core.services.notifier.NotifierServicesForVoicePhoneCall;
import r01f.internal.R01F;
import r01f.patterns.Factory;
import r01f.types.Path;
import r01f.util.types.collections.CollectionUtils;
import x47b.internal.services.config.X47BNotifierConfigForVoice;
import x47b.model.X47BAlarmMessage;
import x47b.model.X47BAlarmMessageEntityAbstracts.X47BAlarmMessageAbstractForDivision;
import x47b.model.X47BAlarmMessageEntityAbstracts.X47BAlarmMessageAbstractForLocation;
import x47b.model.X47BAlarmMessageEntityAbstracts.X47BAlarmMessageAbstractForOrganization;
import x47b.model.X47BAlarmMessageEntityAbstracts.X47BAlarmMessageAbstractForService;
import x47b.model.X47BAlarmMessageEntityAbstracts.X47BAlarmMessageAbstractForWorkPlace;

/**
 * A notifier that makes a voice call and locutates the message
 */
@Slf4j
@Singleton
public class X47BPanicButtonNotifierServicesVoiceImpl
     extends X47BPanicButtonNotifierServicesBase<X47BNotifierConfigForVoice> {
/////////////////////////////////////////////////////////////////////////////////////////
//  FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	@Getter private final NotifierServicesForVoicePhoneCall _voiceNotifier;
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTORS
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public X47BPanicButtonNotifierServicesVoiceImpl(final X47BNotifierConfigForVoice config,final NotifierServicesForVoicePhoneCall voiceNotifier,
													final VelocityEngine velocityEngine) {
		super(config,
			  velocityEngine);
		_voiceNotifier = voiceNotifier;
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  METHODS
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void sendNotification(final X47BAlarmMessage alarmMessage) {
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
											return _composeVoiceMessage(_velocityEngine,_config.getMsgTemplatePath(),
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
