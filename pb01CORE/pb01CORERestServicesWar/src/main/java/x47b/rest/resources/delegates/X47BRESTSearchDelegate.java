package x47b.rest.resources.delegates;

import r01f.rest.resources.delegates.RESTSearchDelegateBase;
import x47b.api.interfaces.X47BSearchServicesForOrganizationalEntityObject;
import x47b.model.search.X47BSearchFilterForPanicButtonOrganizationalEntity;
import x47b.model.search.X47BSearchResultItemForPanicButtonOrganizationalEntity;

public class X47BRESTSearchDelegate
	 extends RESTSearchDelegateBase<X47BSearchFilterForPanicButtonOrganizationalEntity,X47BSearchResultItemForPanicButtonOrganizationalEntity> {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BRESTSearchDelegate(final X47BSearchServicesForOrganizationalEntityObject searchServices) {
		super(searchServices);
	}
}
