package x47b.test;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;
import r01f.test.api.TestAPIBase;
import r01f.test.persistence.TestPersistableModelObjectManager;
import r01f.types.TimeLapse;
import x47b.client.api.X47BPanicButtonClientAPI;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceLocationOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrganizationOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BWorkPlaceOID;
import x47b.model.org.X47BOrgDivision;
import x47b.model.org.X47BOrgDivisionService;
import x47b.model.org.X47BOrgDivisionServiceLocation;
import x47b.model.org.X47BOrganization;
import x47b.model.org.X47BWorkPlace;
import x47b.test.entities.X47BAlarmEventTest;
import x47b.test.entities.X47BOrgDivisionTest;
import x47b.test.entities.X47BDivisionServiceTest;
import x47b.test.entities.X47BOrgDivisionServiceLocationTest;
import x47b.test.entities.X47BOrganizationTest;
import x47b.test.entities.X47BWorkPlaceTest;
import x47b.test.entities.mock.X47BMockOrgDivisionFactory;
import x47b.test.entities.mock.X47BMockOrgDivisionServiceFactory;
import x47b.test.entities.mock.X47BMockOrgDivisionServiceLocationFactory;
import x47b.test.entities.mock.X47BMockOrganizationFactory;
import x47b.test.entities.mock.X47BMockWorkPlaceFactory;
import x47b.test.entities.X47BModelObjectSearchTest;


/**
 * JVM arguments:
 * -javaagent:D:/develop/local_libs/aspectj/lib/aspectjweaver.jar -Daj.weaving.verbose=true
 */
@Slf4j
public class X47BPanicButtonAPITest
	 extends X47BPanicButtonAPITestBase {
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////

	@Test @SuppressWarnings("static-method")
	public void testOrganizations() {
		X47BPanicButtonClientAPI api = TestAPIBase.getClientApi(X47BPanicButtonClientAPI.class);
		final X47BOrganizationTest testOrg = new X47BOrganizationTest(api);
		testOrg.doTest();
	}
	@Test @SuppressWarnings("static-method")
	public void testDivisions() {
		X47BPanicButtonClientAPI api = TestAPIBase.getClientApi(X47BPanicButtonClientAPI.class);

		// [0]: create an organization to act as parent of divisions
		// -- organization
		TestPersistableModelObjectManager<X47BOrganizationOID,X47BOrganization> orgFactory = TestPersistableModelObjectManager.create(X47BOrganization.class,
																																	  new X47BMockOrganizationFactory(),
																																	  api.organizationsAPI()
																																	 			.getForCRUD());
		orgFactory.setUpMockObjs(1);
		X47BOrganization divParentOrg = orgFactory.getAnyCreatedMockObj();

		// [1]: testing...
		final X47BOrgDivisionTest testDivision = new X47BOrgDivisionTest(api,
															  		     				   divParentOrg);
		testDivision.doTest();

		// [2]: tear down
		orgFactory.tearDownCreatedMockObjs();
	}
	@Test @SuppressWarnings("static-method")
	public void testServices() {
		X47BPanicButtonClientAPI api = TestAPIBase.getClientApi(X47BPanicButtonClientAPI.class);

		// [0]: create an organization to act as parent of division and a division to act as parent of services
		// -- organization
		TestPersistableModelObjectManager<X47BOrganizationOID,X47BOrganization> orgFactory = TestPersistableModelObjectManager.create(X47BOrganization.class,
																																	  new X47BMockOrganizationFactory(),
																																	  api.organizationsAPI()
																																	 	  		.getForCRUD());
		orgFactory.setUpMockObjs(1);
		X47BOrganization divParentOrg = orgFactory.getAnyCreatedMockObj();
		// -- division
		TestPersistableModelObjectManager<X47BOrgDivisionOID,X47BOrgDivision> divFactory = TestPersistableModelObjectManager.create(X47BOrgDivision.class,
																																    new X47BMockOrgDivisionFactory(divParentOrg),
																																    api.orgDivisionsAPI()
																																	 		.getForCRUD());
		divFactory.setUpMockObjs(1);
		X47BOrgDivision serviceParentDivision = divFactory.getAnyCreatedMockObj();


		// [1]: Testing...
		final X47BDivisionServiceTest testService = new X47BDivisionServiceTest(api,
											  										  					divParentOrg,
											  										  					serviceParentDivision);
		testService.doTest();

		// [2]: Tear down (reverse order)
		divFactory.tearDownCreatedMockObjs();
		orgFactory.tearDownCreatedMockObjs();
	}
	@Test @SuppressWarnings("static-method")
	public void testLocations() {
		X47BPanicButtonClientAPI api = TestAPIBase.getClientApi(X47BPanicButtonClientAPI.class);

		// [0]: create an organization to act as parent of division, a division to act as parent of a service and a service to act as perent of locations
		// -- organization
		TestPersistableModelObjectManager<X47BOrganizationOID,X47BOrganization> orgFactory = TestPersistableModelObjectManager.create(X47BOrganization.class,
																																	  new X47BMockOrganizationFactory(),
																																	  api.organizationsAPI()
																																	 	  		.getForCRUD());
		orgFactory.setUpMockObjs(1);
		X47BOrganization divParentOrg = orgFactory.getAnyCreatedMockObj();
		// -- division
		TestPersistableModelObjectManager<X47BOrgDivisionOID,X47BOrgDivision> divFactory = TestPersistableModelObjectManager.create(X47BOrgDivision.class,
																																    new X47BMockOrgDivisionFactory(divParentOrg),
																																    api.orgDivisionsAPI()
																																	 			.getForCRUD());
		divFactory.setUpMockObjs(1);
		X47BOrgDivision serviceParentDivision = divFactory.getAnyCreatedMockObj();
		// -- service
		TestPersistableModelObjectManager<X47BOrgDivisionServiceOID,X47BOrgDivisionService> serviceFactory = TestPersistableModelObjectManager.create(X47BOrgDivisionService.class,
																																					  new X47BMockOrgDivisionServiceFactory(divParentOrg,serviceParentDivision),
																																				      api.orgDivisionServicesAPI()
																																					 		.getForCRUD());
		serviceFactory.setUpMockObjs(1);
		X47BOrgDivisionService locationParentService = serviceFactory.getAnyCreatedMockObj();

		// [1]: Testing...
		final X47BOrgDivisionServiceLocationTest testLocation = new X47BOrgDivisionServiceLocationTest(api,
																		  										 	     divParentOrg,
																		  										 	     serviceParentDivision,
																		  										 	     locationParentService);
		testLocation.doTest();

		// [2]: Tear down (reverse order)
		serviceFactory.tearDownCreatedMockObjs();
		divFactory.tearDownCreatedMockObjs();
		orgFactory.tearDownCreatedMockObjs();
	}
	@Test @SuppressWarnings("static-method")
	public void testWorkPlaces() {
		X47BPanicButtonClientAPI api = TestAPIBase.getClientApi(X47BPanicButtonClientAPI.class);

		// [0]: create an organization to act as parent of division, a division to act as parent of a service and a service to act as perent of locations
		// -- organization
		TestPersistableModelObjectManager<X47BOrganizationOID,X47BOrganization> orgFactory = TestPersistableModelObjectManager.create(X47BOrganization.class,
																																	  new X47BMockOrganizationFactory(),
																																	  api.organizationsAPI()
																																	 			.getForCRUD());
		orgFactory.setUpMockObjs(1);
		X47BOrganization divParentOrg = orgFactory.getAnyCreatedMockObj();
		// -- division
		TestPersistableModelObjectManager<X47BOrgDivisionOID,X47BOrgDivision> divFactory = TestPersistableModelObjectManager.create(X47BOrgDivision.class,
																																    new X47BMockOrgDivisionFactory(divParentOrg),
																																    api.orgDivisionsAPI()
																																	 			.getForCRUD());
		divFactory.setUpMockObjs(1);
		X47BOrgDivision serviceParentDivision = divFactory.getAnyCreatedMockObj();
		// -- service
		TestPersistableModelObjectManager<X47BOrgDivisionServiceOID,X47BOrgDivisionService> serviceFactory = TestPersistableModelObjectManager.create(X47BOrgDivisionService.class,
																																					  new X47BMockOrgDivisionServiceFactory(divParentOrg,serviceParentDivision),
																																				      api.orgDivisionServicesAPI()
																																					 		 .getForCRUD());
		serviceFactory.setUpMockObjs(1);
		X47BOrgDivisionService locationParentService = serviceFactory.getAnyCreatedMockObj();
		// -- location
		TestPersistableModelObjectManager<X47BOrgDivisionServiceLocationOID,X47BOrgDivisionServiceLocation> locationFactory = TestPersistableModelObjectManager.create(X47BOrgDivisionServiceLocation.class,
																																									   new X47BMockOrgDivisionServiceLocationFactory(divParentOrg,serviceParentDivision,locationParentService),
																																								       api.orgDivisionServiceLocationsAPI()
																																									 			.getForCRUD());
		locationFactory.setUpMockObjs(1);
		X47BOrgDivisionServiceLocation workPlaceParentLocation = locationFactory.getAnyCreatedMockObj();


		// [1]: Testing...
		final X47BWorkPlaceTest testWorkPlace = new X47BWorkPlaceTest(api,
										  										 	    divParentOrg,
										  										 	    serviceParentDivision,
										  										 	    locationParentService,
										  										 	    workPlaceParentLocation);
		testWorkPlace.doTest();

		// [2]: Tear down (reverse order)
		locationFactory.tearDownCreatedMockObjs();
		serviceFactory.tearDownCreatedMockObjs();
		divFactory.tearDownCreatedMockObjs();
		orgFactory.tearDownCreatedMockObjs();
	}
	@Test @SuppressWarnings("static-method")
	public void testAlarmEvents() {
		X47BPanicButtonClientAPI api = TestAPIBase.getClientApi(X47BPanicButtonClientAPI.class);

		// [0]: create an organization to act as parent of division, a division to act as parent of a service and a service to act as perent of locations
		// -- organization
		TestPersistableModelObjectManager<X47BOrganizationOID,X47BOrganization> orgFactory = TestPersistableModelObjectManager.create(X47BOrganization.class,
																																	  new X47BMockOrganizationFactory(),
																																	  api.organizationsAPI()
																																	 			.getForCRUD());
		orgFactory.setUpMockObjs(1);
		X47BOrganization divParentOrg = orgFactory.getAnyCreatedMockObj();
		// -- division
		TestPersistableModelObjectManager<X47BOrgDivisionOID,X47BOrgDivision> divFactory = TestPersistableModelObjectManager.create(X47BOrgDivision.class,
																																    new X47BMockOrgDivisionFactory(divParentOrg),
																																    api.orgDivisionsAPI()
																																	 			.getForCRUD());
		divFactory.setUpMockObjs(1);
		X47BOrgDivision serviceParentDivision = divFactory.getAnyCreatedMockObj();
		// -- service
		TestPersistableModelObjectManager<X47BOrgDivisionServiceOID,X47BOrgDivisionService> serviceFactory = TestPersistableModelObjectManager.create(X47BOrgDivisionService.class,
																																					  new X47BMockOrgDivisionServiceFactory(divParentOrg,serviceParentDivision),
																																				      api.orgDivisionServicesAPI()
																																					 		 	 .getForCRUD());
		serviceFactory.setUpMockObjs(1);
		X47BOrgDivisionService locationParentService = serviceFactory.getAnyCreatedMockObj();
		// -- location
		TestPersistableModelObjectManager<X47BOrgDivisionServiceLocationOID,X47BOrgDivisionServiceLocation> locationFactory = TestPersistableModelObjectManager.create(X47BOrgDivisionServiceLocation.class,
																																									   new X47BMockOrgDivisionServiceLocationFactory(divParentOrg,serviceParentDivision,locationParentService),
																																								       api.orgDivisionServiceLocationsAPI()
																																									 		 	.getForCRUD());
		locationFactory.setUpMockObjs(1);
		X47BOrgDivisionServiceLocation workPlaceParentLocation = locationFactory.getAnyCreatedMockObj();
		// -- workPlace
		TestPersistableModelObjectManager<X47BWorkPlaceOID,X47BWorkPlace> workPlaceFactory = TestPersistableModelObjectManager.create(X47BWorkPlace.class,
																																      new X47BMockWorkPlaceFactory(divParentOrg,serviceParentDivision,locationParentService,workPlaceParentLocation),
																																      api.workPlacesAPI()
																																				.getForCRUD());
		workPlaceFactory.setUpMockObjs(1);
		X47BWorkPlace alarmParentWorkPlace = workPlaceFactory.getAnyCreatedMockObj();

		// [1]: Testing...
		log.warn("===========================================================");
		log.warn("TEST ALARM EVENTS");
		log.warn("===========================================================");
		final X47BAlarmEventTest testAlarmEvent = new X47BAlarmEventTest(api,
																						   divParentOrg,
																						   serviceParentDivision,
																						   locationParentService,
																						   workPlaceParentLocation,
																						   alarmParentWorkPlace);
		testAlarmEvent.doRaiseAlarms();		// raise alarms
		testAlarmEvent.doQueryAlarms(TimeLapse.createFor("10m"));
		testAlarmEvent.doCancelAlarms();

		// [2]: Tear down (reverse order)
		workPlaceFactory.tearDownCreatedMockObjs();
		locationFactory.tearDownCreatedMockObjs();
		serviceFactory.tearDownCreatedMockObjs();
		divFactory.tearDownCreatedMockObjs();
		orgFactory.tearDownCreatedMockObjs();
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	public void _testSearch() {
		X47BPanicButtonClientAPI api = TestAPIBase.getClientApi(X47BPanicButtonClientAPI.class);

		// [0]: create an organization to act as parent of division, a division to act as parent of a service and a service to act as perent of locations
		// -- organization
		TestPersistableModelObjectManager<X47BOrganizationOID,X47BOrganization> orgFactory = TestPersistableModelObjectManager.create(X47BOrganization.class,
																																	  new X47BMockOrganizationFactory(),
																																	  api.organizationsAPI()
																																	 			.getForCRUD());
		orgFactory.setUpMockObjs(1);
		X47BOrganization divParentOrg = orgFactory.getAnyCreatedMockObj();
		// -- division
		TestPersistableModelObjectManager<X47BOrgDivisionOID,X47BOrgDivision> divFactory = TestPersistableModelObjectManager.create(X47BOrgDivision.class,
																																    new X47BMockOrgDivisionFactory(divParentOrg),
																																    api.orgDivisionsAPI()
																																	 		.getForCRUD());
		divFactory.setUpMockObjs(1);
		X47BOrgDivision serviceParentDivision = divFactory.getAnyCreatedMockObj();
		// -- service
		TestPersistableModelObjectManager<X47BOrgDivisionServiceOID,X47BOrgDivisionService> serviceFactory = TestPersistableModelObjectManager.create(X47BOrgDivisionService.class,
																																					  new X47BMockOrgDivisionServiceFactory(divParentOrg,serviceParentDivision),
																																				      api.orgDivisionServicesAPI()
																																				      .getForCRUD());
		serviceFactory.setUpMockObjs(1);
		X47BOrgDivisionService locationParentService = serviceFactory.getAnyCreatedMockObj();
		// -- location
		TestPersistableModelObjectManager<X47BOrgDivisionServiceLocationOID,X47BOrgDivisionServiceLocation> locationFactory = TestPersistableModelObjectManager.create(X47BOrgDivisionServiceLocation.class,
																																									   new X47BMockOrgDivisionServiceLocationFactory(divParentOrg,serviceParentDivision,locationParentService),
																																								       api.orgDivisionServiceLocationsAPI()
																																									 			.getForCRUD());
		serviceFactory.setUpMockObjs(1);
		X47BOrgDivisionServiceLocation workPlaceParentLocation = locationFactory.getAnyCreatedMockObj();
		// -- workPlace
		TestPersistableModelObjectManager<X47BWorkPlaceOID,X47BWorkPlace> workPlaceFactory = TestPersistableModelObjectManager.create(X47BWorkPlace.class,
																																      new X47BMockWorkPlaceFactory(divParentOrg,serviceParentDivision,locationParentService,workPlaceParentLocation),
																																      api.workPlacesAPI()
																																				.getForCRUD());
		workPlaceFactory.setUpMockObjs(1);
		X47BWorkPlace alarmParentWorkPlace = workPlaceFactory.getAnyCreatedMockObj();

		// [1]: Testing
		log.warn("===========================================================");
		log.warn("TEST SEARCH");
		log.warn("===========================================================");
		final X47BModelObjectSearchTest testSearch = new X47BModelObjectSearchTest(api,
																		 divParentOrg,
																	     serviceParentDivision,
																	     locationParentService,
																	     workPlaceParentLocation,
																	     alarmParentWorkPlace);
		testSearch.doTest();

		// [2]: Tear down (reverse order)
		workPlaceFactory.tearDownCreatedMockObjs();
		locationFactory.tearDownCreatedMockObjs();
		serviceFactory.tearDownCreatedMockObjs();
		divFactory.tearDownCreatedMockObjs();
		orgFactory.tearDownCreatedMockObjs();
	}
}
