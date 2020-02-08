package pb01a.model.metadata;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access=AccessLevel.PRIVATE)
abstract class PB01AModelObjectCodes {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTANTS
/////////////////////////////////////////////////////////////////////////////////////////
	public final static long MODEL_OBJ_TYPE_BASE_CODE = 1000;
	
	public final static long ORGANIZATION_MODEL_OBJ_TYPE_CODE = MODEL_OBJ_TYPE_BASE_CODE + 10;
	public final static long ORG_DIVISION_MODEL_OBJ_TYPE_CODE = MODEL_OBJ_TYPE_BASE_CODE + 20;
	public final static long ORG_DIVISION_SERVICE_MODEL_OBJ_TYPE_CODE = MODEL_OBJ_TYPE_BASE_CODE + 30;
	public final static long ORG_DIVISION_SERVICE_LOCATION_MODEL_OBJ_TYPE_CODE = MODEL_OBJ_TYPE_BASE_CODE + 40;
	
	public final static long WORKPLACE_MODEL_OBJ_TYPE_CODE = MODEL_OBJ_TYPE_BASE_CODE + 50;
}
