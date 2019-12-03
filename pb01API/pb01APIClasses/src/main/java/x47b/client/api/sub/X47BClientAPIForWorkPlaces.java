package x47b.client.api.sub;

import java.util.Map;

import javax.inject.Provider;

import lombok.Getter;
import lombok.experimental.Accessors;
import r01f.objectstreamer.Marshaller;
import r01f.securitycontext.SecurityContext;
import r01f.services.client.ClientSubAPIBase;
import r01f.services.interfaces.ServiceInterface;
import x47b.api.interfaces.X47BCRUDServicesForWorkPlace;
import x47b.api.interfaces.X47BFindServicesForWorkPlace;
import x47b.client.api.sub.delegates.X47BClientAPIDelegateForWorkPlaceCRUDServices;
import x47b.client.api.sub.delegates.X47BClientAPIDelegateForWorkPlaceFindServices;

/**
 * Client implementation of work place's maintenance.
 */
@Accessors(prefix="_")
public class X47BClientAPIForWorkPlaces
     extends ClientSubAPIBase {
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	@Getter private X47BClientAPIDelegateForWorkPlaceCRUDServices _forCRUD;
	@Getter private X47BClientAPIDelegateForWorkPlaceFindServices _forFind;
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@SuppressWarnings("rawtypes")
	public X47BClientAPIForWorkPlaces(final Provider<SecurityContext> securityContextProvider,
								 	  final Marshaller modelObjectsMarshaller,
								 	  final Map<Class,ServiceInterface> srvcIfaceMappings) {
		super(securityContextProvider,
			  modelObjectsMarshaller,
			  srvcIfaceMappings);	// reference to other client apis

		_forCRUD = new X47BClientAPIDelegateForWorkPlaceCRUDServices(securityContextProvider,
																	 modelObjectsMarshaller,
												 			 	     this.getServiceInterfaceCoreImplOrProxy(X47BCRUDServicesForWorkPlace.class));
		_forFind = new X47BClientAPIDelegateForWorkPlaceFindServices(securityContextProvider,
																	 modelObjectsMarshaller,
														  		     this.getServiceInterfaceCoreImplOrProxy(X47BFindServicesForWorkPlace.class));
	}
}
