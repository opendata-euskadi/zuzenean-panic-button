package x47b.rest.resources.delegates;

import x47b.api.interfaces.X47BCRUDServicesForOrgDivisionServiceLocation;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionServiceLocationID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceLocationOID;
import x47b.model.org.X47BOrgDivisionServiceLocation;
import x47b.server.rest.resources.delegates.X47BRESTCRUDDelegateBaseForOrganizationalEntity;

public class X47BRESTCRUDDelegateForOrgDivisionServiceLocation
	 extends X47BRESTCRUDDelegateBaseForOrganizationalEntity<X47BOrgDivisionServiceLocationOID,X47BOrgDivisionServiceLocationID,X47BOrgDivisionServiceLocation> {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BRESTCRUDDelegateForOrgDivisionServiceLocation(final X47BCRUDServicesForOrgDivisionServiceLocation crudServices) {
		super(X47BOrgDivisionServiceLocation.class,
			  crudServices);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	EXTENSION METHODS
/////////////////////////////////////////////////////////////////////////////////////////

}
