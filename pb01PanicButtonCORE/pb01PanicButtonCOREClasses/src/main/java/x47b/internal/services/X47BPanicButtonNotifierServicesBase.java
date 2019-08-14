package x47b.internal.services;

import java.util.Date;

import org.apache.velocity.app.VelocityEngine;

import lombok.Getter;
import lombok.experimental.Accessors;
import r01f.core.services.notifier.NotifierService;
import r01f.core.services.notifier.config.NotifierConfig;
import r01f.service.ServiceCanBeDisabled;
import r01f.util.types.Dates;

/**
 * Base notifier service
 */
@Accessors(prefix="_")
abstract class X47BPanicButtonNotifierServicesBase<C extends NotifierConfig>
    implements X47BPanicButtonNotifierServices {
/////////////////////////////////////////////////////////////////////////////////////////
//  FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	@Getter protected final C _config;
	@Getter protected final VelocityEngine _velocityEngine;
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTORS
/////////////////////////////////////////////////////////////////////////////////////////
	X47BPanicButtonNotifierServicesBase(final C config,
									    final VelocityEngine velocityEngine) {
		_config = config;
		_velocityEngine = velocityEngine;
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public boolean isEnabled() {
		return _config != null ? _config.isEnabled()
							   : false;
	}
	public boolean isEnabledConsidering(final NotifierService<?,?,?> notifierService) {
		boolean isEnabled = this.isEnabled();
		if (notifierService instanceof ServiceCanBeDisabled) {
			ServiceCanBeDisabled serviceCanBeDisabled = (ServiceCanBeDisabled)notifierService;
			if (serviceCanBeDisabled.isDisabled()) isEnabled = false;
		}
		return isEnabled;
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
