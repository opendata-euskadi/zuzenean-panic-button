package x47b.api.interfaces;

import r01f.services.interfaces.ExposedServiceInterface;
import r01f.services.interfaces.SearchServicesForModelObject;
import x47b.model.search.X47BSearchFilterForPanicButtonOrganizationalEntity;
import x47b.model.search.X47BSearchResultItemForPanicButtonOrganizationalEntity;

@ExposedServiceInterface
public interface X47BSearchServicesForOrganizationalEntityObject 
	     extends SearchServicesForModelObject<X47BSearchFilterForPanicButtonOrganizationalEntity,X47BSearchResultItemForPanicButtonOrganizationalEntity>,
	     		 X47BPanicButtonServiceInterface {
	// nothing here
}