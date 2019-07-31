package x47b.model.oids;

import r01f.guids.OIDs;

class X47BGUIDDispenser {
	/**
	 * Generates a GUID
	 * @return 
	 */
	public static String generateGUID() {
        return OIDs.supplyOid();
	}
}
