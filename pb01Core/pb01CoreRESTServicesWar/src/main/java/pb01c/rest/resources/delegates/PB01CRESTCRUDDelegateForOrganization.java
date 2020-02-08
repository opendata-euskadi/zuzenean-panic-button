package pb01c.rest.resources.delegates;

import pb01a.api.interfaces.PB01ACRUDServicesForOrganization;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrganizationID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrganizationOID;
import pb01a.model.org.PB01AOrganization;
import pb01c.server.rest.resources.delegates.PB01CRESTCRUDDelegateBaseForOrganizationalEntity;

public class PB01CRESTCRUDDelegateForOrganization
	 extends PB01CRESTCRUDDelegateBaseForOrganizationalEntity<PB01AOrganizationOID,PB01AOrganizationID,PB01AOrganization> {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01CRESTCRUDDelegateForOrganization(final PB01ACRUDServicesForOrganization crudServices) {
		super(PB01AOrganization.class,
			  crudServices);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
}
