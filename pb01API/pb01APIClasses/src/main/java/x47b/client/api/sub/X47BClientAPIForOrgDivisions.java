package x47b.client.api.sub;

import java.util.Map;

import javax.inject.Provider;

import lombok.Getter;
import lombok.experimental.Accessors;
import r01f.objectstreamer.Marshaller;
import r01f.securitycontext.SecurityContext;
import r01f.services.client.ClientSubAPIBase;
import r01f.services.interfaces.ServiceInterface;
import x47b.api.interfaces.X47BCRUDServicesForOrgDivision;
import x47b.api.interfaces.X47BFindServicesForOrgDivision;
import x47b.client.api.sub.delegates.X47BClientAPIDelegateForOrgDivisionCRUDServices;
import x47b.client.api.sub.delegates.X47BClientAPIDelegateForOrgDivisionFindServices;

/**
 * Client implementation of the divisions maintenance.
 */
@Accessors(prefix="_")
public class X47BClientAPIForOrgDivisions
     extends ClientSubAPIBase {
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	@Getter private X47BClientAPIDelegateForOrgDivisionCRUDServices _forCRUD;
	@Getter private X47BClientAPIDelegateForOrgDivisionFindServices _forFind;
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@SuppressWarnings("rawtypes")
	public X47BClientAPIForOrgDivisions(final Provider<SecurityContext> securityContextProvider,
										final Marshaller modelObjectsMarshaller,
										final Map<Class,ServiceInterface> srvcIfaceMappings) {
		super(securityContextProvider,
			  modelObjectsMarshaller,
			  srvcIfaceMappings);	// reference to other client apis

		_forCRUD = new X47BClientAPIDelegateForOrgDivisionCRUDServices(securityContextProvider,
																	   modelObjectsMarshaller,
													 			 	   this.getServiceInterfaceCoreImplOrProxy(X47BCRUDServicesForOrgDivision.class));
		_forFind = new X47BClientAPIDelegateForOrgDivisionFindServices(securityContextProvider,
																	   modelObjectsMarshaller,
															  		   this.getServiceInterfaceCoreImplOrProxy(X47BFindServicesForOrgDivision.class));
	}
}
