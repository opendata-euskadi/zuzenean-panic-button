package x47b.client.api.sub;

import java.util.Map;

import javax.inject.Provider;

import lombok.Getter;
import lombok.experimental.Accessors;
import r01f.objectstreamer.Marshaller;
import r01f.securitycontext.SecurityContext;
import r01f.services.client.ClientSubAPIBase;
import r01f.services.interfaces.ServiceInterface;
import x47b.api.interfaces.X47BCRUDServicesForOrgDivisionServiceLocation;
import x47b.api.interfaces.X47BFindServicesForOrgDivisionServiceLocation;
import x47b.client.api.sub.delegates.X47BClientAPIDelegateForOrgDivisionServiceLocationCRUDServices;
import x47b.client.api.sub.delegates.X47BClientAPIDelegateForOrgDivisionServiceLocationFindServices;

/**
 * Client implementation of services maintenance.
 */
@Accessors(prefix="_")
public class X47BClientAPIForOrgDivisionServiceLocations
     extends ClientSubAPIBase {
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	@Getter private X47BClientAPIDelegateForOrgDivisionServiceLocationCRUDServices _forCRUD;
	@Getter private X47BClientAPIDelegateForOrgDivisionServiceLocationFindServices _forFind;
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@SuppressWarnings("rawtypes")
	public X47BClientAPIForOrgDivisionServiceLocations(final Provider<SecurityContext> securityContextProvider,
													   final Marshaller modelObjectsMarshaller,
													   final Map<Class,ServiceInterface> srvcIfaceMappings) {
		super(securityContextProvider,
			  modelObjectsMarshaller,
			  srvcIfaceMappings);	// reference to other client apis

		_forCRUD = new X47BClientAPIDelegateForOrgDivisionServiceLocationCRUDServices(securityContextProvider,
																					  modelObjectsMarshaller,
													 			 	     			  this.getServiceInterfaceCoreImplOrProxy(X47BCRUDServicesForOrgDivisionServiceLocation.class));
		_forFind = new X47BClientAPIDelegateForOrgDivisionServiceLocationFindServices(securityContextProvider,
																					  modelObjectsMarshaller,
															  		     			  this.getServiceInterfaceCoreImplOrProxy(X47BFindServicesForOrgDivisionServiceLocation.class));
	}
}
