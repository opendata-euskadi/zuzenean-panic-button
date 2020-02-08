package pb01a.client.api.sub;

import java.util.Map;

import javax.inject.Provider;

import lombok.Getter;
import lombok.experimental.Accessors;
import pb01a.api.interfaces.PB01ACRUDServicesForOrgDivisionService;
import pb01a.api.interfaces.PB01AFindServicesForOrgDivisionService;
import pb01a.client.api.sub.delegates.PB01AClientAPIDelegateForOrgDivisionServiceCRUDServices;
import pb01a.client.api.sub.delegates.PB01AClientAPIDelegateForOrgDivisionServiceFindServices;
import r01f.objectstreamer.Marshaller;
import r01f.securitycontext.SecurityContext;
import r01f.services.client.ClientSubAPIBase;
import r01f.services.interfaces.ServiceInterface;

/**
 * Client implementation of services maintenance.
 */
@Accessors(prefix="_")
public class PB01AClientAPIForOrgDivisionServices
     extends ClientSubAPIBase {
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	@Getter private PB01AClientAPIDelegateForOrgDivisionServiceCRUDServices _forCRUD;
	@Getter private PB01AClientAPIDelegateForOrgDivisionServiceFindServices _forFind;
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@SuppressWarnings("rawtypes")
	public PB01AClientAPIForOrgDivisionServices(final Provider<SecurityContext> securityContextProvider,
											   final Marshaller modelObjectsMarshaller,
											   final Map<Class,ServiceInterface> srvcIfaceMappings) {
		super(securityContextProvider,
			  modelObjectsMarshaller,
			  srvcIfaceMappings);	// reference to other client apis

		_forCRUD = new PB01AClientAPIDelegateForOrgDivisionServiceCRUDServices(securityContextProvider,
																			  modelObjectsMarshaller,
																			  this.getServiceInterfaceCoreImplOrProxy(PB01ACRUDServicesForOrgDivisionService.class));
		_forFind = new PB01AClientAPIDelegateForOrgDivisionServiceFindServices(securityContextProvider,
																			  modelObjectsMarshaller,
															  		     	  this.getServiceInterfaceCoreImplOrProxy(PB01AFindServicesForOrgDivisionService.class));
	}
}
