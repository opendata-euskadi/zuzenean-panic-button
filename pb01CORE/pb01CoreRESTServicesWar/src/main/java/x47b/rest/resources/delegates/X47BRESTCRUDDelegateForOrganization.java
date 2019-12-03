package x47b.rest.resources.delegates;

import x47b.api.interfaces.X47BCRUDServicesForOrganization;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrganizationID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrganizationOID;
import x47b.model.org.X47BOrganization;
import x47b.server.rest.resources.delegates.X47BRESTCRUDDelegateBaseForOrganizationalEntity;

public class X47BRESTCRUDDelegateForOrganization
	 extends X47BRESTCRUDDelegateBaseForOrganizationalEntity<X47BOrganizationOID,X47BOrganizationID,X47BOrganization> {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BRESTCRUDDelegateForOrganization(final X47BCRUDServicesForOrganization crudServices) {
		super(X47BOrganization.class,
			  crudServices);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
}
