package pb01c.rest.resources.delegates;

import pb01a.api.interfaces.PB01ASearchServicesForOrganizationalEntityObject;
import pb01a.model.search.PB01ASearchFilterForPanicButtonOrganizationalEntity;
import pb01a.model.search.PB01ASearchResultItemForPanicButtonOrganizationalEntity;
import r01f.rest.resources.delegates.RESTSearchDelegateBase;

public class PB01CRESTSearchDelegate
	 extends RESTSearchDelegateBase<PB01ASearchFilterForPanicButtonOrganizationalEntity,PB01ASearchResultItemForPanicButtonOrganizationalEntity> {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01CRESTSearchDelegate(final PB01ASearchServicesForOrganizationalEntityObject searchServices) {
		super(searchServices);
	}
}
