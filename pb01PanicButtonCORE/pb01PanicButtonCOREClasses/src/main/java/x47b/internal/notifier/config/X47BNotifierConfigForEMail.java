package x47b.internal.notifier.config;

import lombok.Getter;
import lombok.experimental.Accessors;
import r01f.config.ContainsConfigData;
import r01f.core.services.notifier.config.NotifierConfigForEMail;
import r01f.core.services.notifier.config.NotifierEnums.NotifierImpl;
import r01f.types.Path;
import r01f.types.contact.EMail;
import r01f.util.types.Strings;
import r01f.xmlproperties.XMLPropertiesForAppComponent;
import x47b.common.internal.X47BAppCodes;

@Accessors(prefix="_")
public class X47BNotifierConfigForEMail
     extends NotifierConfigForEMail {
/////////////////////////////////////////////////////////////////////////////////////////
//	FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	@Getter private final Path _msgTemplatePath;
	@Getter private final Path _msgImagePath;
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	private X47BNotifierConfigForEMail(final boolean enabled,
									   final NotifierImpl impl,final ContainsConfigData config,
							 	   	   final EMail fromMail,final String fromName,
							 	   	   final Path msgTemplatePath,
							 	   	   final Path msgImagePath) {
		super(X47BAppCodes.CORE_APPCODE,
			  enabled,
			  impl,config,
			  fromMail,fromName);
		_msgTemplatePath = msgTemplatePath;
		_msgImagePath = msgImagePath;
	}
	private X47BNotifierConfigForEMail(final XMLPropertiesForAppComponent props) {
		super(props);
 	     // template path
		 _msgTemplatePath = props.propertyAt(_xPathBaseForCommonProperties() + "/msgTemplate")
								 .asPath(Strings.customized("{}/notifier/{}{}MessageTemplate.vm",	// ie: x47b/notifier/x47bEMAILMessageTemplate.vm
										  					_appCode,
															_appCode,_type));
		_msgImagePath = props.propertyAt(_xPathBaseForCommonProperties() + "/msgHeaderImage")
						 .asPath(Strings.customized("{}/notifier/{}Logo.gif",
								 					props.getAppCode(),props.getAppCode()));
	}
	public static final X47BNotifierConfigForEMail createFrom(final XMLPropertiesForAppComponent props) {
		return new X47BNotifierConfigForEMail(props);
	}
}
