package pb01c.internal.notifier;

import javax.inject.Inject;
import javax.inject.Singleton;

import lombok.extern.slf4j.Slf4j;
import pb01a.model.PB01AAlarmMessage;
import r01f.core.services.notifier.config.NotifierConfigForLog;

/**
 * A notifier that just logs the message
 */
@Singleton
@Slf4j
public class PB01CPanicButtonNotifierLoggerImpl
     extends PB01CPanicButtonNotifierBase<NotifierConfigForLog> {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTORS
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public PB01CPanicButtonNotifierLoggerImpl(final NotifierConfigForLog config) {
		super(config,
			  null);	// no velocity engine needed for logging
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  METHODS
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void sendNotification(final PB01AAlarmMessage alarmMessage) {
		boolean enabled = _config.isEnabled();
		if (!enabled) return;

		log.info("[LogNotifier]: ALARM EVENT: {}",alarmMessage.getAlarmEventOid());
		log.info("\t>Organization: {}",alarmMessage.getOrganization().getEntityId());
		log.info("\t>    Division: {}",alarmMessage.getDivision().getEntityId());
		log.info("\t>     Service: {}",alarmMessage.getService().getEntityId());
		log.info("\t>    Location: {}",alarmMessage.getLocation().getEntityId());
		log.info("\t>  Work Place: {}",alarmMessage.getWorkPlace().getEntityId());

		log.info("\t>Phones: {}",alarmMessage.getPhones());
		log.info("\t>EMails: {}",alarmMessage.getMails());
	}
}
