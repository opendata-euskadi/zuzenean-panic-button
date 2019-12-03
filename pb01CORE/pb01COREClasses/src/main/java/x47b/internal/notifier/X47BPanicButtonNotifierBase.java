package x47b.internal.notifier;

import java.util.Date;

import org.apache.velocity.app.VelocityEngine;

import lombok.Getter;
import lombok.experimental.Accessors;
import r01f.core.notifier.NotifierBase;
import r01f.core.services.notifier.config.NotifierConfig;
import r01f.util.types.Dates;
import x47b.model.X47BAlarmMessage;

/**
 * Base notifier service
 */
@Accessors(prefix="_")
abstract class X47BPanicButtonNotifierBase<C extends NotifierConfig>
	   extends NotifierBase<C,X47BAlarmMessage>
    implements X47BPanicButtonNotifier {
/////////////////////////////////////////////////////////////////////////////////////////
//  FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	@Getter protected final VelocityEngine _velocityEngine;
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTORS
/////////////////////////////////////////////////////////////////////////////////////////
	X47BPanicButtonNotifierBase(final C config,
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
