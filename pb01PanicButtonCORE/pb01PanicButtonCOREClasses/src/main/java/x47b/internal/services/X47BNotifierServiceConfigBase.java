package x47b.internal.services;

import lombok.Getter;
import lombok.experimental.Accessors;
import r01f.config.ContainsConfigData;
import r01f.types.Path;
import r01f.util.types.Strings;
import r01f.xmlproperties.XMLPropertiesForAppComponent;

@Accessors(prefix="_")
abstract class X47BNotifierServiceConfigBase
    implements ContainsConfigData {
/////////////////////////////////////////////////////////////////////////////////////////
//	FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	@Getter protected final String _name;
	@Getter protected final boolean _enabled;
	@Getter protected final Path _alertMsgTemplatePath;
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	protected X47BNotifierServiceConfigBase(final String name,
											final boolean enabled,
											final Path alertMsgTemplatePath) {
		_name = name;
		_enabled = enabled;
		_alertMsgTemplatePath = alertMsgTemplatePath;
	}
	protected X47BNotifierServiceConfigBase(final String name,
											final XMLPropertiesForAppComponent props) {
		_name = name;
		_enabled = props.propertyAt("/notifier/notifiers/" + name + "/@enabled").asBoolean(true);

		String defaultAlertMsgTemplatePath = Strings.customized("x47b/notifier/X47B{}MessageTemplate.vm",
																Strings.capitalizeFirstLetter(name));
		_alertMsgTemplatePath = props.propertyAt("/notifier/notifiers/" + name + "/msgTemplate")
									 .asPath(defaultAlertMsgTemplatePath);
	}
}
