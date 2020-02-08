package pb01c.rest.resources.delegates;

import pb01a.api.interfaces.PB01ACRUDServicesForOrgDivisionServiceLocation;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionServiceLocationID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionServiceLocationOID;
import pb01a.model.org.PB01AOrgDivisionServiceLocation;
import pb01c.server.rest.resources.delegates.PB01CRESTCRUDDelegateBaseForOrganizationalEntity;

public class PB01CRESTCRUDDelegateForOrgDivisionServiceLocation
	 extends PB01CRESTCRUDDelegateBaseForOrganizationalEntity<PB01AOrgDivisionServiceLocationOID,PB01AOrgDivisionServiceLocationID,PB01AOrgDivisionServiceLocation> {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01CRESTCRUDDelegateForOrgDivisionServiceLocation(final PB01ACRUDServicesForOrgDivisionServiceLocation crudServices) {
		super(PB01AOrgDivisionServiceLocation.class,
			  crudServices);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	EXTENSION METHODS
/////////////////////////////////////////////////////////////////////////////////////////

}
