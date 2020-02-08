package pb01c.rest.resources.delegates;

import pb01a.api.interfaces.PB01ACRUDServicesForOrgDivision;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionOID;
import pb01a.model.org.PB01AOrgDivision;
import pb01c.server.rest.resources.delegates.PB01CRESTCRUDDelegateBaseForOrganizationalEntity;

public class PB01CRESTCRUDDelegateForOrgDivision
	 extends PB01CRESTCRUDDelegateBaseForOrganizationalEntity<PB01AOrgDivisionOID,PB01AOrgDivisionID,PB01AOrgDivision> {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01CRESTCRUDDelegateForOrgDivision(final PB01ACRUDServicesForOrgDivision crudServices) {
		super(PB01AOrgDivision.class,
			  crudServices);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
}
