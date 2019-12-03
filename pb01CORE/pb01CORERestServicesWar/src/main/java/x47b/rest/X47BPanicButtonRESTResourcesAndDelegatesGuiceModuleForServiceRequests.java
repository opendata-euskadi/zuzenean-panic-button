package x47b.rest;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Singleton;

import lombok.EqualsAndHashCode;
import x47b.rest.resources.X47BRESTCRUDResourceForAlarmEvent;
import x47b.rest.resources.X47BRESTCRUDResourceForOrgDivision;
import x47b.rest.resources.X47BRESTCRUDResourceForOrgDivisionService;
import x47b.rest.resources.X47BRESTCRUDResourceForOrgDivisionServiceLocation;
import x47b.rest.resources.X47BRESTCRUDResourceForOrganization;
import x47b.rest.resources.X47BRESTCRUDResourceForWorkPlace;
import x47b.rest.resources.X47BRESTFindResourceForAlarmEvent;
import x47b.rest.resources.X47BRESTFindResourceForOrgDivision;
import x47b.rest.resources.X47BRESTFindResourceForOrgDivisionService;
import x47b.rest.resources.X47BRESTFindResourceForOrgDivisionServiceLocation;
import x47b.rest.resources.X47BRESTFindResourceForOrganization;
import x47b.rest.resources.X47BRESTFindResourceForWorkPlace;
import x47b.rest.resources.X47BRESTResourceForAlarmEventClient;
import x47b.rest.resources.X47BRESTSearchResourceForEntityModelObject;

/**
 * This module binds the REST resources and its delegates
 * (delegation is used to split the logic and maintain REST resources code to a minimum)
 * <p>In order to maintain the REST resource code at a minimum, each REST resource ({appCode}REST{service}ResourceFor{entity}) delegates it's implementation 
 * 	  to a REST Delegate (ie {appCode}REST{Service}DelegateFor{entity}) where all the logic resides</p>
 * <p>In order to do it's work, the REST service delegate will need one or more service(s) implementation(s). These service implementation will be injected to
 *    the REST service resource that will hand them to the delegate</p> 
 *    
 * <p>For example, a CRUD service of an entity named "MyEntity" will have a resource named {appCode}RESTCRUDServiceResourceForMyEntity that will delegate to {appCode}RESTCRUDServiceDelegateForMyEntity</p>
 * <p>The CRUD REST resource ({appCode}RESTCRUDServiceResourceForMyEntity) will be injected with the CRUD service implementation ({appCode}CRUDServicesForMyEntity) that will be handed
 *    to the delegate ({appCode}RESTCRUDServiceDelegateForMyEntity)
 */
@EqualsAndHashCode				// This is important for guice modules
public class X47BPanicButtonRESTResourcesAndDelegatesGuiceModuleForServiceRequests
  implements Module {

	@Override
	public void configure(final Binder binder) {
		binder.bind(X47BPanicButtonRESTApp.class)
			  .in(Singleton.class);

		// ... REST Resources <- all the logic is splitted in DELEGATES in order to maintain resources code to
		//						 a minimum
		// 	   Must configure at least one JAX-RS resource or the
		// 	   server will fail to start.
		binder.bind(X47BRESTCRUDResourceForOrganization.class)
			  .in(Singleton.class);
		binder.bind(X47BRESTCRUDResourceForOrgDivision.class)
		  	  .in(Singleton.class);
		binder.bind(X47BRESTCRUDResourceForOrgDivisionService.class)
		  	  .in(Singleton.class);
		binder.bind(X47BRESTCRUDResourceForOrgDivisionServiceLocation.class)
		  	  .in(Singleton.class);
		binder.bind(X47BRESTCRUDResourceForWorkPlace.class)
			  .in(Singleton.class);
		
		binder.bind(X47BRESTFindResourceForOrganization.class)
			  .in(Singleton.class);
		binder.bind(X47BRESTFindResourceForOrgDivision.class)
		  	  .in(Singleton.class);
		binder.bind(X47BRESTFindResourceForOrgDivisionService.class)
		  	  .in(Singleton.class);
		binder.bind(X47BRESTFindResourceForOrgDivisionServiceLocation.class)
		  	  .in(Singleton.class);
		binder.bind(X47BRESTFindResourceForWorkPlace.class)
			  .in(Singleton.class);
		
		binder.bind(X47BRESTSearchResourceForEntityModelObject.class)
			  .in(Singleton.class);
		
		binder.bind(X47BRESTCRUDResourceForAlarmEvent.class)
			  .in(Singleton.class);
		binder.bind(X47BRESTFindResourceForAlarmEvent.class)
			  .in(Singleton.class);
		binder.bind(X47BRESTResourceForAlarmEventClient.class)
			  .in(Singleton.class);
		
	}
}
