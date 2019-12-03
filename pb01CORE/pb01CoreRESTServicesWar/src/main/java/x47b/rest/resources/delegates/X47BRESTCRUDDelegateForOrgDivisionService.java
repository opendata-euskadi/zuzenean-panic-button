package x47b.rest.resources.delegates;

import x47b.api.interfaces.X47BCRUDServicesForOrgDivisionService;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionServiceID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceOID;
import x47b.model.org.X47BOrgDivisionService;
import x47b.server.rest.resources.delegates.X47BRESTCRUDDelegateBaseForOrganizationalEntity;

public class X47BRESTCRUDDelegateForOrgDivisionService
	 extends X47BRESTCRUDDelegateBaseForOrganizationalEntity<X47BOrgDivisionServiceOID,X47BOrgDivisionServiceID,X47BOrgDivisionService> {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BRESTCRUDDelegateForOrgDivisionService(final X47BCRUDServicesForOrgDivisionService crudServices) {
		super(X47BOrgDivisionService.class,
			  crudServices);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	EXTENSION METHODS
/////////////////////////////////////////////////////////////////////////////////////////

}
