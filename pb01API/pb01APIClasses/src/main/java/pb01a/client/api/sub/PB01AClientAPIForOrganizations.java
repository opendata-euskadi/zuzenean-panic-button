package pb01a.client.api.sub;

import java.util.Map;

import javax.inject.Provider;

import lombok.Getter;
import lombok.experimental.Accessors;
import pb01a.api.interfaces.PB01ACRUDServicesForOrganization;
import pb01a.api.interfaces.PB01AFindServicesForOrganization;
import pb01a.client.api.sub.delegates.PB01AClientAPIDelegateForOrganizationCRUDServices;
import pb01a.client.api.sub.delegates.PB01AClientAPIDelegateForOrganizationFindServices;
import r01f.objectstreamer.Marshaller;
import r01f.securitycontext.SecurityContext;
import r01f.services.client.ClientSubAPIBase;
import r01f.services.interfaces.ServiceInterface;

/**
 * Client implementation of the organizations maintenance
 */
@Accessors(prefix="_")
public class PB01AClientAPIForOrganizations
     extends ClientSubAPIBase {
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	@Getter private PB01AClientAPIDelegateForOrganizationCRUDServices _forCRUD;
	@Getter private PB01AClientAPIDelegateForOrganizationFindServices _forFind;
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@SuppressWarnings("rawtypes")
	public PB01AClientAPIForOrganizations(final Provider<SecurityContext> securityContextProvider,
										 final Marshaller modelObjectsMarshaller,
								  		 Map<Class,ServiceInterface> srvcIfaceMappings) {
		super(securityContextProvider,
			  modelObjectsMarshaller,
			  srvcIfaceMappings);	// reference to other client apis

		_forCRUD = new PB01AClientAPIDelegateForOrganizationCRUDServices(securityContextProvider,
																		modelObjectsMarshaller,
													 			 	    this.getServiceInterfaceCoreImplOrProxy(PB01ACRUDServicesForOrganization.class));
		_forFind = new PB01AClientAPIDelegateForOrganizationFindServices(securityContextProvider,
																		modelObjectsMarshaller,
															  		    this.getServiceInterfaceCoreImplOrProxy(PB01AFindServicesForOrganization.class));
	}
}
