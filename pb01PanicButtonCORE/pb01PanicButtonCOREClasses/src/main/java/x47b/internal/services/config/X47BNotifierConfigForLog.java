package x47b.internal.services.config;

import lombok.experimental.Accessors;
import r01f.core.services.notifier.config.NotifierConfigForLogBase;
import r01f.core.services.notifier.config.NotifierEnums.NotifierImpl;
import r01f.xmlproperties.XMLPropertiesForAppComponent;
import x47b.common.internal.X47BAppCodes;

@Accessors(prefix="_")
public class X47BNotifierConfigForLog
     extends NotifierConfigForLogBase {
/////////////////////////////////////////////////////////////////////////////////////////
//	FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	// nothing specific
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	private X47BNotifierConfigForLog(final boolean enabled,
									 final NotifierImpl impl) {
		super(X47BAppCodes.CORE_APPCODE,
			  enabled,
			  impl);
	}
	private X47BNotifierConfigForLog(final XMLPropertiesForAppComponent props) {
		super(props);
	}
	public static final X47BNotifierConfigForLog createFrom(final XMLPropertiesForAppComponent props) {
		return new X47BNotifierConfigForLog(props);
	}
}
