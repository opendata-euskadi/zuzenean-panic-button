package pb01a.api.interfaces;

import pb01a.model.search.PB01ASearchFilterForPanicButtonOrganizationalEntity;
import pb01a.model.search.PB01ASearchResultItemForPanicButtonOrganizationalEntity;
import r01f.services.interfaces.ExposedServiceInterface;
import r01f.services.interfaces.SearchServicesForModelObject;

@ExposedServiceInterface
public interface PB01ASearchServicesForOrganizationalEntityObject 
	     extends SearchServicesForModelObject<PB01ASearchFilterForPanicButtonOrganizationalEntity,PB01ASearchResultItemForPanicButtonOrganizationalEntity>,
	     		 PB01APanicButtonServiceInterface {
	// nothing here
}