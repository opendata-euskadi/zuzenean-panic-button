package x47b.internal.services;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.apache.velocity.app.VelocityEngine;

import lombok.extern.slf4j.Slf4j;
import x47b.model.X47BAlarmMessage;

/**
 * A notifier that just logs the message
 */
@Singleton
@Slf4j
public class X47BPanicButtonNotifierServicesLoggerImpl
     extends X47BPanicButtonNotifierServicesBase<X47BNotifierConfigForLog> {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTORS
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public X47BPanicButtonNotifierServicesLoggerImpl(final X47BNotifierConfigForLog config,
												     final VelocityEngine velocityEngine) {
		super(config,
			  velocityEngine);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  METHODS
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void sendNotification(final X47BAlarmMessage alarmMessage) {
		boolean enabled = _config.isEnabled();
		if (enabled) {
			log.info("==============>ALARM EVENT: {}",alarmMessage.getAlarmEventOid());
			log.info("\t>Organization: {}",alarmMessage.getOrganization().getEntityId());
			log.info("\t>    Division: {}",alarmMessage.getDivision().getEntityId());
			log.info("\t>     Service: {}",alarmMessage.getService().getEntityId());
			log.info("\t>    Location: {}",alarmMessage.getLocation().getEntityId());
			log.info("\t>  Work Place: {}",alarmMessage.getWorkPlace().getEntityId());
			
			log.info("\t>Phones: {}",alarmMessage.getPhones());
			log.info("\t>EMails: {}",alarmMessage.getMails());
		}
	}
}
