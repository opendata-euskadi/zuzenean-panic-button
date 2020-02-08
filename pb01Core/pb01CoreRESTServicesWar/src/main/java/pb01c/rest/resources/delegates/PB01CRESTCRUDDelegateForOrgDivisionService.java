package pb01c.rest.resources.delegates;

import pb01a.api.interfaces.PB01ACRUDServicesForOrgDivisionService;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionServiceID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionServiceOID;
import pb01a.model.org.PB01AOrgDivisionService;
import pb01c.server.rest.resources.delegates.PB01CRESTCRUDDelegateBaseForOrganizationalEntity;

public class PB01CRESTCRUDDelegateForOrgDivisionService
	 extends PB01CRESTCRUDDelegateBaseForOrganizationalEntity<PB01AOrgDivisionServiceOID,PB01AOrgDivisionServiceID,PB01AOrgDivisionService> {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01CRESTCRUDDelegateForOrgDivisionService(final PB01ACRUDServicesForOrgDivisionService crudServices) {
		super(PB01AOrgDivisionService.class,
			  crudServices);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	EXTENSION METHODS
/////////////////////////////////////////////////////////////////////////////////////////

}
