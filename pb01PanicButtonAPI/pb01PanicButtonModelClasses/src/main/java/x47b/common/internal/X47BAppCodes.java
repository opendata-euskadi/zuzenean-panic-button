package x47b.common.internal;

import r01f.services.ids.ServiceIDs.ClientApiAppCode;
import r01f.services.ids.ServiceIDs.CoreAppCode;
import r01f.services.ids.ServiceIDs.CoreModule;

public class X47BAppCodes {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTANTS
/////////////////////////////////////////////////////////////////////////////////////////
	public static final String API_APPCODE_STR = "x47b";
	public static final String CORE_APPCODE_STR = "x47b";
	public static final String UI_APPCODE_STR = "x47b";

	public static final String INTERACTIONTYPING_MODULE_STR = "interactiontyping";
	public static final String PANICBUTTON_MODULE_STR = "panicbutton";

	public static final String INTERACTIONTYPING_APP_AND_MOD_STR = "x47b.interactiontyping";
	public static final String PANICBUTTON_APP_AND_MOD_STR = "x47b.panicbutton";



	public static final ClientApiAppCode API_APPCODE = ClientApiAppCode.forId(X47BAppCodes.API_APPCODE_STR);
	public static final CoreAppCode CORE_APPCODE = CoreAppCode.forId(X47BAppCodes.CORE_APPCODE_STR);
	public static final CoreAppCode UI_APPCODE = CoreAppCode.forId(X47BAppCodes.UI_APPCODE_STR);

	public static final CoreModule INTERACTIONTYPING_MOD = CoreModule.forId(INTERACTIONTYPING_MODULE_STR);
	public static final CoreModule PANICBUTTON_MOD = CoreModule.forId(PANICBUTTON_MODULE_STR);
}
