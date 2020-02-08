package pb01a.common.internal;

import r01f.services.ids.ServiceIDs.ClientApiAppCode;
import r01f.services.ids.ServiceIDs.CoreAppCode;
import r01f.services.ids.ServiceIDs.CoreModule;

public class P01AAppCodes {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTANTS
/////////////////////////////////////////////////////////////////////////////////////////
	public static final String API_APPCODE_STR = "pb01a";
	public static final String CORE_APPCODE_STR = "pb01c";
	public static final String UI_APPCODE_STR = "pb01ui";

	public static final String INTERACTIONTYPING_MODULE_STR = "interactiontyping";
	public static final String PANICBUTTON_MODULE_STR = "panicbutton";

	public static final String PANICBUTTON_APP_AND_MOD_STR = "pb01c.panicbutton";



	public static final ClientApiAppCode API_APPCODE = ClientApiAppCode.forId(P01AAppCodes.API_APPCODE_STR);
	public static final CoreAppCode CORE_APPCODE = CoreAppCode.forId(P01AAppCodes.CORE_APPCODE_STR);
	public static final CoreAppCode UI_APPCODE = CoreAppCode.forId(P01AAppCodes.UI_APPCODE_STR);

	public static final CoreModule PANICBUTTON_MOD = CoreModule.forId(PANICBUTTON_MODULE_STR);
}
