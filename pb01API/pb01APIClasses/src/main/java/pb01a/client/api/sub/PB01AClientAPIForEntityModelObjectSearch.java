package pb01a.client.api.sub;

import javax.inject.Provider;

import lombok.experimental.Accessors;
import pb01a.api.interfaces.PB01ASearchServicesForOrganizationalEntityObject;
import pb01a.model.search.PB01ASearchFilterForPanicButtonOrganizationalEntity;
import pb01a.model.search.PB01ASearchResultItemForPanicButtonOrganizationalEntity;
import r01f.objectstreamer.Marshaller;
import r01f.securitycontext.SecurityContext;
import r01f.services.client.api.delegates.ClientAPIDelegateForModelObjectSearchServices;

/**
 * Client implementation of search api
 */
@Accessors(prefix="_")
public class PB01AClientAPIForEntityModelObjectSearch
     extends ClientAPIDelegateForModelObjectSearchServices<PB01ASearchFilterForPanicButtonOrganizationalEntity,			
     													   PB01ASearchResultItemForPanicButtonOrganizationalEntity> {		
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01AClientAPIForEntityModelObjectSearch(final Provider<SecurityContext> securityContextProvider,
												   final Marshaller modelObjectsMarshaller,
								  				   final PB01ASearchServicesForOrganizationalEntityObject entitySearchServicesProxy) {
		super(securityContextProvider,
			  modelObjectsMarshaller,
			  entitySearchServicesProxy,	// reference to other client apis
			  PB01ASearchFilterForPanicButtonOrganizationalEntity.class,PB01ASearchResultItemForPanicButtonOrganizationalEntity.class);
	}
}
