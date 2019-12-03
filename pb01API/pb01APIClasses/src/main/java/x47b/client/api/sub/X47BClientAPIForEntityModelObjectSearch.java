package x47b.client.api.sub;

import javax.inject.Provider;

import lombok.experimental.Accessors;
import r01f.objectstreamer.Marshaller;
import r01f.securitycontext.SecurityContext;
import r01f.services.client.api.delegates.ClientAPIDelegateForModelObjectSearchServices;
import x47b.api.interfaces.X47BSearchServicesForOrganizationalEntityObject;
import x47b.model.search.X47BSearchFilterForPanicButtonOrganizationalEntity;
import x47b.model.search.X47BSearchResultItemForPanicButtonOrganizationalEntity;

/**
 * Client implementation of search api
 */
@Accessors(prefix="_")
public class X47BClientAPIForEntityModelObjectSearch
     extends ClientAPIDelegateForModelObjectSearchServices<X47BSearchFilterForPanicButtonOrganizationalEntity,			
     													   X47BSearchResultItemForPanicButtonOrganizationalEntity> {		
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BClientAPIForEntityModelObjectSearch(final Provider<SecurityContext> securityContextProvider,
												   final Marshaller modelObjectsMarshaller,
								  				   final X47BSearchServicesForOrganizationalEntityObject entitySearchServicesProxy) {
		super(securityContextProvider,
			  modelObjectsMarshaller,
			  entitySearchServicesProxy,	// reference to other client apis
			  X47BSearchFilterForPanicButtonOrganizationalEntity.class,X47BSearchResultItemForPanicButtonOrganizationalEntity.class);
	}
}
