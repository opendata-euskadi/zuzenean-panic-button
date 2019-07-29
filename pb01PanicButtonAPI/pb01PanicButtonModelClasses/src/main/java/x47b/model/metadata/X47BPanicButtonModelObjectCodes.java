package x47b.model.metadata;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access=AccessLevel.PRIVATE)
abstract class X47BPanicButtonModelObjectCodes {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTANTS
/////////////////////////////////////////////////////////////////////////////////////////
//	public final static long ORGANIZATION_MODEL_OBJ_TYPE_CODE = X47BModelObjectCodes.MODEL_OBJ_TYPE_BASE_CODE + 10;
//	public final static long LOCATION_MODEL_OBJ_TYPE_CODE = X47BModelObjectCodes.MODEL_OBJ_TYPE_BASE_CODE + 20;
//	public final static long WORKPLACE_MODEL_OBJ_TYPE_CODE = X47BModelObjectCodes.MODEL_OBJ_TYPE_BASE_CODE + 30;
	
	public final static long ALARM_EVENT_MODEL_OBJ_TYPE_CODE = X47BModelObjectCodes.MODEL_OBJ_TYPE_BASE_CODE + 100;
}
