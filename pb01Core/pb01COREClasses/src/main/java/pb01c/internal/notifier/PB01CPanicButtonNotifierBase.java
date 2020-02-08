package pb01c.internal.notifier;

import java.util.Date;

import org.apache.velocity.app.VelocityEngine;

import lombok.Getter;
import lombok.experimental.Accessors;
import pb01a.model.PB01AAlarmMessage;
import r01f.core.notifier.NotifierBase;
import r01f.core.services.notifier.config.NotifierConfig;
import r01f.util.types.Dates;

/**
 * Base notifier service
 */
@Accessors(prefix="_")
abstract class PB01CPanicButtonNotifierBase<C extends NotifierConfig>
	   extends NotifierBase<C,PB01AAlarmMessage>
    implements PB01CPanicButtonNotifier {
/////////////////////////////////////////////////////////////////////////////////////////
//  FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	@Getter protected final VelocityEngine _velocityEngine;
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTORS
/////////////////////////////////////////////////////////////////////////////////////////
	PB01CPanicButtonNotifierBase(final C config,
								final VelocityEngine velocityEngine) {
		super(config);
		_velocityEngine = velocityEngine;
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  METHODS
/////////////////////////////////////////////////////////////////////////////////////////
	protected static final String DATE_FORMAT = "dd/MM/yyyy hh:mm:ss";

	protected static String _alarmDateFormated() {
		return Dates.format(new Date(),
						    DATE_FORMAT);
	}
}
