package x47b.internal.notifier.config;

import lombok.Getter;
import lombok.experimental.Accessors;
import r01f.config.ContainsConfigData;
import r01f.core.services.notifier.config.NotifierConfigForSMS;
import r01f.core.services.notifier.config.NotifierEnums.NotifierImpl;
import r01f.guids.CommonOIDs.AppCode;
import r01f.types.Path;
import r01f.types.contact.OwnedContactMean;
import r01f.types.contact.Phone;
import r01f.util.types.Strings;
import r01f.xmlproperties.XMLPropertiesForAppComponent;
import x47b.common.internal.X47BAppCodes;

@Accessors(prefix="_")
public class X47BNotifierConfigForSMS
     extends NotifierConfigForSMS {
/////////////////////////////////////////////////////////////////////////////////////////
//	FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	@Getter private final Path _msgTemplatePath;
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	private X47BNotifierConfigForSMS(final AppCode appCode,
								 	 final boolean enabled,
								 	 final NotifierImpl impl,final ContainsConfigData config,
								 	 final OwnedContactMean<Phone> from,
								 	 final Path msgTemplatePath) {
		super(X47BAppCodes.CORE_APPCODE,
			  enabled,
			  impl,config,
			  from);
		_msgTemplatePath = msgTemplatePath;
	}
	private X47BNotifierConfigForSMS(final XMLPropertiesForAppComponent props) {
		super(props);
		 _msgTemplatePath = props.propertyAt(_xPathBaseForCommonProperties() + "/msgTemplate")
								 .asPath(Strings.customized("{}/notifier/{}{}MessageTemplate.vm",	// ie: x47b/notifier/x47bEMAILMessageTemplate.vm
										  					_appCode,
															_appCode,_type));
	}
	public static final X47BNotifierConfigForSMS createFrom(final XMLPropertiesForAppComponent props) {
		return new X47BNotifierConfigForSMS(props);
	}
}
