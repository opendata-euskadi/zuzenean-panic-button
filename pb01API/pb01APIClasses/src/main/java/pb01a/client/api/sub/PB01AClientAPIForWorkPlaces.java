package pb01a.client.api.sub;

import java.util.Map;

import javax.inject.Provider;

import lombok.Getter;
import lombok.experimental.Accessors;
import pb01a.api.interfaces.PB01ACRUDServicesForWorkPlace;
import pb01a.api.interfaces.PB01AFindServicesForWorkPlace;
import pb01a.client.api.sub.delegates.PB01AClientAPIDelegateForWorkPlaceCRUDServices;
import pb01a.client.api.sub.delegates.PB01AClientAPIDelegateForWorkPlaceFindServices;
import r01f.objectstreamer.Marshaller;
import r01f.securitycontext.SecurityContext;
import r01f.services.client.ClientSubAPIBase;
import r01f.services.interfaces.ServiceInterface;

/**
 * Client implementation of work place's maintenance.
 */
@Accessors(prefix="_")
public class PB01AClientAPIForWorkPlaces
     extends ClientSubAPIBase {
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	@Getter private PB01AClientAPIDelegateForWorkPlaceCRUDServices _forCRUD;
	@Getter private PB01AClientAPIDelegateForWorkPlaceFindServices _forFind;
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@SuppressWarnings("rawtypes")
	public PB01AClientAPIForWorkPlaces(final Provider<SecurityContext> securityContextProvider,
								 	  final Marshaller modelObjectsMarshaller,
								 	  final Map<Class,ServiceInterface> srvcIfaceMappings) {
		super(securityContextProvider,
			  modelObjectsMarshaller,
			  srvcIfaceMappings);	// reference to other client apis

		_forCRUD = new PB01AClientAPIDelegateForWorkPlaceCRUDServices(securityContextProvider,
																	 modelObjectsMarshaller,
												 			 	     this.getServiceInterfaceCoreImplOrProxy(PB01ACRUDServicesForWorkPlace.class));
		_forFind = new PB01AClientAPIDelegateForWorkPlaceFindServices(securityContextProvider,
																	 modelObjectsMarshaller,
														  		     this.getServiceInterfaceCoreImplOrProxy(PB01AFindServicesForWorkPlace.class));
	}
}
