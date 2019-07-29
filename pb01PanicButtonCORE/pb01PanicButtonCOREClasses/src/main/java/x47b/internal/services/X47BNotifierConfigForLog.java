package x47b.internal.services;

import lombok.experimental.Accessors;
import r01f.types.Path;
import r01f.xmlproperties.XMLPropertiesForAppComponent;

@Accessors(prefix="_")
public class X47BNotifierConfigForLog
     extends X47BNotifierServiceConfigBase {
/////////////////////////////////////////////////////////////////////////////////////////
//	FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	// nothing specific
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR  
/////////////////////////////////////////////////////////////////////////////////////////
	private X47BNotifierConfigForLog(final boolean enabled,
							 	     final Path alertMsgTemplatePath) {
		super("log",
			  enabled,
			  alertMsgTemplatePath);
	}
	private X47BNotifierConfigForLog(final XMLPropertiesForAppComponent props) {
		super("log",
			  props);
	}
	public static final X47BNotifierConfigForLog createFrom(final XMLPropertiesForAppComponent props) {
		return new X47BNotifierConfigForLog(props);
	}
}
