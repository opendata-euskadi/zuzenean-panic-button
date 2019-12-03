package x47b.internal.notifier;

import java.io.StringWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import lombok.extern.slf4j.Slf4j;
import r01f.core.services.notifier.NotifierServiceForSMS;
import r01f.core.services.notifier.config.NotifierConfigForSMS;
import r01f.internal.R01F;
import r01f.patterns.Factory;
import r01f.types.Path;
import r01f.types.contact.Phone;
import r01f.util.types.collections.CollectionUtils;
import x47b.internal.notifier.config.X47BNotifierConfigForSMS;
import x47b.model.X47BAlarmMessage;
import x47b.model.X47BAlarmMessageEntityAbstracts.X47BAlarmMessageAbstractForDivision;
import x47b.model.X47BAlarmMessageEntityAbstracts.X47BAlarmMessageAbstractForLocation;
import x47b.model.X47BAlarmMessageEntityAbstracts.X47BAlarmMessageAbstractForOrganization;
import x47b.model.X47BAlarmMessageEntityAbstracts.X47BAlarmMessageAbstractForService;
import x47b.model.X47BAlarmMessageEntityAbstracts.X47BAlarmMessageAbstractForWorkPlace;

/**
 * SMS notifier
 */
@Singleton
@Slf4j
public class X47BPanicButtonNotifierSMSImpl
     extends X47BPanicButtonNotifierBase<NotifierConfigForSMS> {
/////////////////////////////////////////////////////////////////////////////////////////
//  FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * SMS notifier services
	 */
	private final NotifierServiceForSMS _smsNotifier;
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTORS
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public X47BPanicButtonNotifierSMSImpl(final NotifierConfigForSMS config,final NotifierServiceForSMS smsNotifier,
										  final VelocityEngine velocityEngine) {
		super(config,
			  velocityEngine);
		_smsNotifier = smsNotifier;
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  METHODS
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void sendNotification(final X47BAlarmMessage alarmMessage) {
		if (!this.isEnabledConsidering(_smsNotifier)) {
			log.warn("SMS notifier is DISABLED!");
			return;
		}
		log.warn("[SMS Notifier ({})]================================================",
				 _smsNotifier.getClass().getSimpleName());
		Collection<Phone> phones = alarmMessage.getPhonesSanitized();
		if (CollectionUtils.isNullOrEmpty(phones)) {
			log.warn("... NO phones to notify to");
			return;
		}
		_smsNotifier.notifyAll(_config.getFrom(),phones,
							   new Factory<String>() {
										@Override
										public String create() {
											return _composeIMMessage(_velocityEngine,_config.getAppConfigAs(X47BNotifierConfigForSMS.class)
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
