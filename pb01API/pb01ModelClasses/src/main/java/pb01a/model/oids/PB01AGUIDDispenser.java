package pb01a.model.oids;

import r01f.guids.OIDs;

class PB01AGUIDDispenser {
	/**
	 * Generates a GUID
	 * @return 
	 */
	public static String generateGUID() {
        return OIDs.supplyOid();
	}
}
