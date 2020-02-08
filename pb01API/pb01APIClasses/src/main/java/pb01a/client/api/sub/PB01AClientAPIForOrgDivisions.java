package pb01a.client.api.sub;

import java.util.Map;

import javax.inject.Provider;

import lombok.Getter;
import lombok.experimental.Accessors;
import pb01a.api.interfaces.PB01ACRUDServicesForOrgDivision;
import pb01a.api.interfaces.PB01AFindServicesForOrgDivision;
import pb01a.client.api.sub.delegates.PB01AClientAPIDelegateForOrgDivisionCRUDServices;
import pb01a.client.api.sub.delegates.PB01AClientAPIDelegateForOrgDivisionFindServices;
import r01f.objectstreamer.Marshaller;
import r01f.securitycontext.SecurityContext;
import r01f.services.client.ClientSubAPIBase;
import r01f.services.interfaces.ServiceInterface;

/**
 * Client implementation of the divisions maintenance.
 */
@Accessors(prefix="_")
public class PB01AClientAPIForOrgDivisions
     extends ClientSubAPIBase {
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	@Getter private PB01AClientAPIDelegateForOrgDivisionCRUDServices _forCRUD;
	@Getter private PB01AClientAPIDelegateForOrgDivisionFindServices _forFind;
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@SuppressWarnings("rawtypes")
	public PB01AClientAPIForOrgDivisions(final Provider<SecurityContext> securityContextProvider,
										final Marshaller modelObjectsMarshaller,
										final Map<Class,ServiceInterface> srvcIfaceMappings) {
		super(securityContextProvider,
			  modelObjectsMarshaller,
			  srvcIfaceMappings);	// reference to other client apis

		_forCRUD = new PB01AClientAPIDelegateForOrgDivisionCRUDServices(securityContextProvider,
																	   modelObjectsMarshaller,
													 			 	   this.getServiceInterfaceCoreImplOrProxy(PB01ACRUDServicesForOrgDivision.class));
		_forFind = new PB01AClientAPIDelegateForOrgDivisionFindServices(securityContextProvider,
																	   modelObjectsMarshaller,
															  		   this.getServiceInterfaceCoreImplOrProxy(PB01AFindServicesForOrgDivision.class));
	}
}
