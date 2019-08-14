package x47b.internal.services.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import r01f.config.ContainsConfigData;

@Accessors(prefix="_")
@RequiredArgsConstructor
public class X47BNotifiersConfig
  implements ContainsConfigData {
/////////////////////////////////////////////////////////////////////////////////////////
//	FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	@Getter private final X47BNotifierConfigForEMail _forEmail;
	@Getter private final X47BNotifierConfigForSMS _forSMS;
	@Getter private final X47BNotifierConfigForVoice _forVoice;
	@Getter private final X47BNotifierConfigForLog _forLog;
}
