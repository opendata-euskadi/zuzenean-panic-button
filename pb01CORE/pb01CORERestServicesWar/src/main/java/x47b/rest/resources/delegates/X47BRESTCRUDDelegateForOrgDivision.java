package x47b.rest.resources.delegates;

import x47b.api.interfaces.X47BCRUDServicesForOrgDivision;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionOID;
import x47b.model.org.X47BOrgDivision;
import x47b.server.rest.resources.delegates.X47BRESTCRUDDelegateBaseForOrganizationalEntity;

public class X47BRESTCRUDDelegateForOrgDivision
	 extends X47BRESTCRUDDelegateBaseForOrganizationalEntity<X47BOrgDivisionOID,X47BOrgDivisionID,X47BOrgDivision> {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BRESTCRUDDelegateForOrgDivision(final X47BCRUDServicesForOrgDivision crudServices) {
		super(X47BOrgDivision.class,
			  crudServices);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
}
