package x47b.client.api.sub;

import java.util.Map;

import javax.inject.Provider;

import lombok.Getter;
import lombok.experimental.Accessors;
import r01f.objectstreamer.Marshaller;
import r01f.securitycontext.SecurityContext;
import r01f.services.client.ClientSubAPIBase;
import r01f.services.interfaces.ServiceInterface;
import x47b.api.interfaces.X47BCRUDServicesForOrgDivisionService;
import x47b.api.interfaces.X47BFindServicesForOrgDivisionService;
import x47b.client.api.sub.delegates.X47BClientAPIDelegateForOrgDivisionServiceCRUDServices;
import x47b.client.api.sub.delegates.X47BClientAPIDelegateForOrgDivisionServiceFindServices;

/**
 * Client implementation of services maintenance.
 */
@Accessors(prefix="_")
public class X47BClientAPIForOrgDivisionServices
     extends ClientSubAPIBase {
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	@Getter private X47BClientAPIDelegateForOrgDivisionServiceCRUDServices _forCRUD;
	@Getter private X47BClientAPIDelegateForOrgDivisionServiceFindServices _forFind;
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@SuppressWarnings("rawtypes")
	public X47BClientAPIForOrgDivisionServices(final Provider<SecurityContext> securityContextProvider,
											   final Marshaller modelObjectsMarshaller,
											   final Map<Class,ServiceInterface> srvcIfaceMappings) {
		super(securityContextProvider,
			  modelObjectsMarshaller,
			  srvcIfaceMappings);	// reference to other client apis

		_forCRUD = new X47BClientAPIDelegateForOrgDivisionServiceCRUDServices(securityContextProvider,
																			  modelObjectsMarshaller,
																			  this.getServiceInterfaceCoreImplOrProxy(X47BCRUDServicesForOrgDivisionService.class));
		_forFind = new X47BClientAPIDelegateForOrgDivisionServiceFindServices(securityContextProvider,
																			  modelObjectsMarshaller,
															  		     	  this.getServiceInterfaceCoreImplOrProxy(X47BFindServicesForOrgDivisionService.class));
	}
}
