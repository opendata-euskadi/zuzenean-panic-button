package pb01a.client.api.sub;

import java.util.Map;

import javax.inject.Provider;

import lombok.Getter;
import lombok.experimental.Accessors;
import pb01a.api.interfaces.PB01ACRUDServicesForOrgDivisionServiceLocation;
import pb01a.api.interfaces.PB01AFindServicesForOrgDivisionServiceLocation;
import pb01a.client.api.sub.delegates.PB01AClientAPIDelegateForOrgDivisionServiceLocationCRUDServices;
import pb01a.client.api.sub.delegates.PB01AClientAPIDelegateForOrgDivisionServiceLocationFindServices;
import r01f.objectstreamer.Marshaller;
import r01f.securitycontext.SecurityContext;
import r01f.services.client.ClientSubAPIBase;
import r01f.services.interfaces.ServiceInterface;

/**
 * Client implementation of services maintenance.
 */
@Accessors(prefix="_")
public class PB01AClientAPIForOrgDivisionServiceLocations
     extends ClientSubAPIBase {
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	@Getter private PB01AClientAPIDelegateForOrgDivisionServiceLocationCRUDServices _forCRUD;
	@Getter private PB01AClientAPIDelegateForOrgDivisionServiceLocationFindServices _forFind;
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@SuppressWarnings("rawtypes")
	public PB01AClientAPIForOrgDivisionServiceLocations(final Provider<SecurityContext> securityContextProvider,
													   final Marshaller modelObjectsMarshaller,
													   final Map<Class,ServiceInterface> srvcIfaceMappings) {
		super(securityContextProvider,
			  modelObjectsMarshaller,
			  srvcIfaceMappings);	// reference to other client apis

		_forCRUD = new PB01AClientAPIDelegateForOrgDivisionServiceLocationCRUDServices(securityContextProvider,
																					  modelObjectsMarshaller,
													 			 	     			  this.getServiceInterfaceCoreImplOrProxy(PB01ACRUDServicesForOrgDivisionServiceLocation.class));
		_forFind = new PB01AClientAPIDelegateForOrgDivisionServiceLocationFindServices(securityContextProvider,
																					  modelObjectsMarshaller,
															  		     			  this.getServiceInterfaceCoreImplOrProxy(PB01AFindServicesForOrgDivisionServiceLocation.class));
	}
}
