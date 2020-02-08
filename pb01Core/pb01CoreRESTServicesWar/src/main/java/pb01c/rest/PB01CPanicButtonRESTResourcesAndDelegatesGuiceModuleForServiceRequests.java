package pb01c.rest;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Singleton;

import lombok.EqualsAndHashCode;
import pb01c.rest.resources.PB01CRESTCRUDResourceForAlarmEvent;
import pb01c.rest.resources.PB01CRESTCRUDResourceForOrgDivision;
import pb01c.rest.resources.PB01CRESTCRUDResourceForOrgDivisionService;
import pb01c.rest.resources.PB01CRESTCRUDResourceForOrgDivisionServiceLocation;
import pb01c.rest.resources.PB01CRESTCRUDResourceForOrganization;
import pb01c.rest.resources.PB01CRESTCRUDResourceForWorkPlace;
import pb01c.rest.resources.PB01CRESTFindResourceForAlarmEvent;
import pb01c.rest.resources.PB01CRESTFindResourceForOrgDivision;
import pb01c.rest.resources.PB01CRESTFindResourceForOrgDivisionService;
import pb01c.rest.resources.PB01CRESTFindResourceForOrgDivisionServiceLocation;
import pb01c.rest.resources.PB01CRESTFindResourceForOrganization;
import pb01c.rest.resources.PB01CRESTFindResourceForWorkPlace;
import pb01c.rest.resources.PB01CRESTResourceForAlarmEventClient;
import pb01c.rest.resources.PB01CRESTSearchResourceForEntityModelObject;

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
public class PB01CPanicButtonRESTResourcesAndDelegatesGuiceModuleForServiceRequests
  implements Module {

	@Override
	public void configure(final Binder binder) {
		binder.bind(PB01CPanicButtonRESTApp.class)
			  .in(Singleton.class);

		// ... REST Resources <- all the logic is splitted in DELEGATES in order to maintain resources code to
		//						 a minimum
		// 	   Must configure at least one JAX-RS resource or the
		// 	   server will fail to start.
		binder.bind(PB01CRESTCRUDResourceForOrganization.class)
			  .in(Singleton.class);
		binder.bind(PB01CRESTCRUDResourceForOrgDivision.class)
		  	  .in(Singleton.class);
		binder.bind(PB01CRESTCRUDResourceForOrgDivisionService.class)
		  	  .in(Singleton.class);
		binder.bind(PB01CRESTCRUDResourceForOrgDivisionServiceLocation.class)
		  	  .in(Singleton.class);
		binder.bind(PB01CRESTCRUDResourceForWorkPlace.class)
			  .in(Singleton.class);

		binder.bind(PB01CRESTFindResourceForOrganization.class)
			  .in(Singleton.class);
		binder.bind(PB01CRESTFindResourceForOrgDivision.class)
		  	  .in(Singleton.class);
		binder.bind(PB01CRESTFindResourceForOrgDivisionService.class)
		  	  .in(Singleton.class);
		binder.bind(PB01CRESTFindResourceForOrgDivisionServiceLocation.class)
		  	  .in(Singleton.class);
		binder.bind(PB01CRESTFindResourceForWorkPlace.class)
			  .in(Singleton.class);

		binder.bind(PB01CRESTSearchResourceForEntityModelObject.class)
			  .in(Singleton.class);

		binder.bind(PB01CRESTCRUDResourceForAlarmEvent.class)
			  .in(Singleton.class);
		binder.bind(PB01CRESTFindResourceForAlarmEvent.class)
			  .in(Singleton.class);
		binder.bind(PB01CRESTResourceForAlarmEventClient.class)
			  .in(Singleton.class);

	}
}
