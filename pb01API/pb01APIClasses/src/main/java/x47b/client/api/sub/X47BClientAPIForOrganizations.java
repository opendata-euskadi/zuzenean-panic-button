package x47b.client.api.sub;

import java.util.Map;

import javax.inject.Provider;

import lombok.Getter;
import lombok.experimental.Accessors;
import r01f.objectstreamer.Marshaller;
import r01f.securitycontext.SecurityContext;
import r01f.services.client.ClientSubAPIBase;
import r01f.services.interfaces.ServiceInterface;
import x47b.api.interfaces.X47BCRUDServicesForOrganization;
import x47b.api.interfaces.X47BFindServicesForOrganization;
import x47b.client.api.sub.delegates.X47BClientAPIDelegateForOrganizationCRUDServices;
import x47b.client.api.sub.delegates.X47BClientAPIDelegateForOrganizationFindServices;

/**
 * Client implementation of the organizations maintenance
 */
@Accessors(prefix="_")
public class X47BClientAPIForOrganizations
     extends ClientSubAPIBase {
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	@Getter private X47BClientAPIDelegateForOrganizationCRUDServices _forCRUD;
	@Getter private X47BClientAPIDelegateForOrganizationFindServices _forFind;
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@SuppressWarnings("rawtypes")
	public X47BClientAPIForOrganizations(final Provider<SecurityContext> securityContextProvider,
										 final Marshaller modelObjectsMarshaller,
								  		 Map<Class,ServiceInterface> srvcIfaceMappings) {
		super(securityContextProvider,
			  modelObjectsMarshaller,
			  srvcIfaceMappings);	// reference to other client apis

		_forCRUD = new X47BClientAPIDelegateForOrganizationCRUDServices(securityContextProvider,
																		modelObjectsMarshaller,
													 			 	    this.getServiceInterfaceCoreImplOrProxy(X47BCRUDServicesForOrganization.class));
		_forFind = new X47BClientAPIDelegateForOrganizationFindServices(securityContextProvider,
																		modelObjectsMarshaller,
															  		    this.getServiceInterfaceCoreImplOrProxy(X47BFindServicesForOrganization.class));
	}
}
