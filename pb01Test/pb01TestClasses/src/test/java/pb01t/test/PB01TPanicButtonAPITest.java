package pb01t.test;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;
import pb01a.client.api.PB01APanicButtonClientAPI;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionServiceLocationOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionServiceOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrganizationOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AWorkPlaceOID;
import pb01a.model.org.PB01AOrgDivision;
import pb01a.model.org.PB01AOrgDivisionService;
import pb01a.model.org.PB01AOrgDivisionServiceLocation;
import pb01a.model.org.PB01AOrganization;
import pb01a.model.org.PB01AWorkPlace;
import pb01t.test.entities.PB01TAlarmEventTest;
import pb01t.test.entities.PB01TDivisionServiceTest;
import pb01t.test.entities.PB01TModelObjectSearchTest;
import pb01t.test.entities.PB01TOrgDivisionServiceLocationTest;
import pb01t.test.entities.PB01TOrgDivisionTest;
import pb01t.test.entities.PB01TOrganizationTest;
import pb01t.test.entities.PB01TWorkPlaceTest;
import pb01t.test.entities.mock.PB01TMockOrgDivisionFactory;
import pb01t.test.entities.mock.PB01TMockOrgDivisionServiceFactory;
import pb01t.test.entities.mock.PB01TMockOrgDivisionServiceLocationFactory;
import pb01t.test.entities.mock.PB01TMockOrganizationFactory;
import pb01t.test.entities.mock.PB01TMockWorkPlaceFactory;
import r01f.test.api.TestAPIBase;
import r01f.test.persistence.TestPersistableModelObjectManager;
import r01f.types.TimeLapse;


/**
 * JVM arguments:
 * -javaagent:D:/develop/local_libs/aspectj/lib/aspectjweaver.jar -Daj.weaving.verbose=true
 */
@Slf4j
public class PB01TPanicButtonAPITest
	 extends PB01TPanicButtonAPITestBase {
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////

	@Test @SuppressWarnings("static-method")
	public void testOrganizations() {
		PB01APanicButtonClientAPI api = TestAPIBase.getClientApi(PB01APanicButtonClientAPI.class);
		final PB01TOrganizationTest testOrg = new PB01TOrganizationTest(api);
		testOrg.doTest();
	}
	@Test @SuppressWarnings("static-method")
	public void testDivisions() {
		PB01APanicButtonClientAPI api = TestAPIBase.getClientApi(PB01APanicButtonClientAPI.class);

		// [0]: create an organization to act as parent of divisions
		// -- organization
		TestPersistableModelObjectManager<PB01AOrganizationOID,PB01AOrganization> orgFactory = TestPersistableModelObjectManager.create(PB01AOrganization.class,
																																	  new PB01TMockOrganizationFactory(),
																																	  api.organizationsAPI()
																																	 			.getForCRUD());
		orgFactory.setUpMockObjs(1);
		PB01AOrganization divParentOrg = orgFactory.getAnyCreatedMockObj();

		// [1]: testing...
		final PB01TOrgDivisionTest testDivision = new PB01TOrgDivisionTest(api,
															  		     				   divParentOrg);
		testDivision.doTest();

		// [2]: tear down
		orgFactory.tearDownCreatedMockObjs();
	}
	@Test @SuppressWarnings("static-method")
	public void testServices() {
		PB01APanicButtonClientAPI api = TestAPIBase.getClientApi(PB01APanicButtonClientAPI.class);

		// [0]: create an organization to act as parent of division and a division to act as parent of services
		// -- organization
		TestPersistableModelObjectManager<PB01AOrganizationOID,PB01AOrganization> orgFactory = TestPersistableModelObjectManager.create(PB01AOrganization.class,
																																	  new PB01TMockOrganizationFactory(),
																																	  api.organizationsAPI()
																																	 	  		.getForCRUD());
		orgFactory.setUpMockObjs(1);
		PB01AOrganization divParentOrg = orgFactory.getAnyCreatedMockObj();
		// -- division
		TestPersistableModelObjectManager<PB01AOrgDivisionOID,PB01AOrgDivision> divFactory = TestPersistableModelObjectManager.create(PB01AOrgDivision.class,
																																    new PB01TMockOrgDivisionFactory(divParentOrg),
																																    api.orgDivisionsAPI()
																																	 		.getForCRUD());
		divFactory.setUpMockObjs(1);
		PB01AOrgDivision serviceParentDivision = divFactory.getAnyCreatedMockObj();


		// [1]: Testing...
		final PB01TDivisionServiceTest testService = new PB01TDivisionServiceTest(api,
											  										  					divParentOrg,
											  										  					serviceParentDivision);
		testService.doTest();

		// [2]: Tear down (reverse order)
		divFactory.tearDownCreatedMockObjs();
		orgFactory.tearDownCreatedMockObjs();
	}
	@Test @SuppressWarnings("static-method")
	public void testLocations() {
		PB01APanicButtonClientAPI api = TestAPIBase.getClientApi(PB01APanicButtonClientAPI.class);

		// [0]: create an organization to act as parent of division, a division to act as parent of a service and a service to act as perent of locations
		// -- organization
		TestPersistableModelObjectManager<PB01AOrganizationOID,PB01AOrganization> orgFactory = TestPersistableModelObjectManager.create(PB01AOrganization.class,
																																	  new PB01TMockOrganizationFactory(),
																																	  api.organizationsAPI()
																																	 	  		.getForCRUD());
		orgFactory.setUpMockObjs(1);
		PB01AOrganization divParentOrg = orgFactory.getAnyCreatedMockObj();
		// -- division
		TestPersistableModelObjectManager<PB01AOrgDivisionOID,PB01AOrgDivision> divFactory = TestPersistableModelObjectManager.create(PB01AOrgDivision.class,
																																    new PB01TMockOrgDivisionFactory(divParentOrg),
																																    api.orgDivisionsAPI()
																																	 			.getForCRUD());
		divFactory.setUpMockObjs(1);
		PB01AOrgDivision serviceParentDivision = divFactory.getAnyCreatedMockObj();
		// -- service
		TestPersistableModelObjectManager<PB01AOrgDivisionServiceOID,PB01AOrgDivisionService> serviceFactory = TestPersistableModelObjectManager.create(PB01AOrgDivisionService.class,
																																					  new PB01TMockOrgDivisionServiceFactory(divParentOrg,serviceParentDivision),
																																				      api.orgDivisionServicesAPI()
																																					 		.getForCRUD());
		serviceFactory.setUpMockObjs(1);
		PB01AOrgDivisionService locationParentService = serviceFactory.getAnyCreatedMockObj();

		// [1]: Testing...
		final PB01TOrgDivisionServiceLocationTest testLocation = new PB01TOrgDivisionServiceLocationTest(api,
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
		PB01APanicButtonClientAPI api = TestAPIBase.getClientApi(PB01APanicButtonClientAPI.class);

		// [0]: create an organization to act as parent of division, a division to act as parent of a service and a service to act as perent of locations
		// -- organization
		TestPersistableModelObjectManager<PB01AOrganizationOID,PB01AOrganization> orgFactory = TestPersistableModelObjectManager.create(PB01AOrganization.class,
																																	  new PB01TMockOrganizationFactory(),
																																	  api.organizationsAPI()
																																	 			.getForCRUD());
		orgFactory.setUpMockObjs(1);
		PB01AOrganization divParentOrg = orgFactory.getAnyCreatedMockObj();
		// -- division
		TestPersistableModelObjectManager<PB01AOrgDivisionOID,PB01AOrgDivision> divFactory = TestPersistableModelObjectManager.create(PB01AOrgDivision.class,
																																    new PB01TMockOrgDivisionFactory(divParentOrg),
																																    api.orgDivisionsAPI()
																																	 			.getForCRUD());
		divFactory.setUpMockObjs(1);
		PB01AOrgDivision serviceParentDivision = divFactory.getAnyCreatedMockObj();
		// -- service
		TestPersistableModelObjectManager<PB01AOrgDivisionServiceOID,PB01AOrgDivisionService> serviceFactory = TestPersistableModelObjectManager.create(PB01AOrgDivisionService.class,
																																					  new PB01TMockOrgDivisionServiceFactory(divParentOrg,serviceParentDivision),
																																				      api.orgDivisionServicesAPI()
																																					 		 .getForCRUD());
		serviceFactory.setUpMockObjs(1);
		PB01AOrgDivisionService locationParentService = serviceFactory.getAnyCreatedMockObj();
		// -- location
		TestPersistableModelObjectManager<PB01AOrgDivisionServiceLocationOID,PB01AOrgDivisionServiceLocation> locationFactory = TestPersistableModelObjectManager.create(PB01AOrgDivisionServiceLocation.class,
																																									   new PB01TMockOrgDivisionServiceLocationFactory(divParentOrg,serviceParentDivision,locationParentService),
																																								       api.orgDivisionServiceLocationsAPI()
																																									 			.getForCRUD());
		locationFactory.setUpMockObjs(1);
		PB01AOrgDivisionServiceLocation workPlaceParentLocation = locationFactory.getAnyCreatedMockObj();


		// [1]: Testing...
		final PB01TWorkPlaceTest testWorkPlace = new PB01TWorkPlaceTest(api,
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
		PB01APanicButtonClientAPI api = TestAPIBase.getClientApi(PB01APanicButtonClientAPI.class);

		// [0]: create an organization to act as parent of division, a division to act as parent of a service and a service to act as perent of locations
		// -- organization
		TestPersistableModelObjectManager<PB01AOrganizationOID,PB01AOrganization> orgFactory = TestPersistableModelObjectManager.create(PB01AOrganization.class,
																																	  new PB01TMockOrganizationFactory(),
																																	  api.organizationsAPI()
																																	 			.getForCRUD());
		orgFactory.setUpMockObjs(1);
		PB01AOrganization divParentOrg = orgFactory.getAnyCreatedMockObj();
		// -- division
		TestPersistableModelObjectManager<PB01AOrgDivisionOID,PB01AOrgDivision> divFactory = TestPersistableModelObjectManager.create(PB01AOrgDivision.class,
																																    new PB01TMockOrgDivisionFactory(divParentOrg),
																																    api.orgDivisionsAPI()
																																	 			.getForCRUD());
		divFactory.setUpMockObjs(1);
		PB01AOrgDivision serviceParentDivision = divFactory.getAnyCreatedMockObj();
		// -- service
		TestPersistableModelObjectManager<PB01AOrgDivisionServiceOID,PB01AOrgDivisionService> serviceFactory = TestPersistableModelObjectManager.create(PB01AOrgDivisionService.class,
																																					  new PB01TMockOrgDivisionServiceFactory(divParentOrg,serviceParentDivision),
																																				      api.orgDivisionServicesAPI()
																																					 		 	 .getForCRUD());
		serviceFactory.setUpMockObjs(1);
		PB01AOrgDivisionService locationParentService = serviceFactory.getAnyCreatedMockObj();
		// -- location
		TestPersistableModelObjectManager<PB01AOrgDivisionServiceLocationOID,PB01AOrgDivisionServiceLocation> locationFactory = TestPersistableModelObjectManager.create(PB01AOrgDivisionServiceLocation.class,
																																									   new PB01TMockOrgDivisionServiceLocationFactory(divParentOrg,serviceParentDivision,locationParentService),
																																								       api.orgDivisionServiceLocationsAPI()
																																									 		 	.getForCRUD());
		locationFactory.setUpMockObjs(1);
		PB01AOrgDivisionServiceLocation workPlaceParentLocation = locationFactory.getAnyCreatedMockObj();
		// -- workPlace
		TestPersistableModelObjectManager<PB01AWorkPlaceOID,PB01AWorkPlace> workPlaceFactory = TestPersistableModelObjectManager.create(PB01AWorkPlace.class,
																																      new PB01TMockWorkPlaceFactory(divParentOrg,serviceParentDivision,locationParentService,workPlaceParentLocation),
																																      api.workPlacesAPI()
																																				.getForCRUD());
		workPlaceFactory.setUpMockObjs(1);
		PB01AWorkPlace alarmParentWorkPlace = workPlaceFactory.getAnyCreatedMockObj();

		// [1]: Testing...
		log.warn("===========================================================");
		log.warn("TEST ALARM EVENTS");
		log.warn("===========================================================");
		final PB01TAlarmEventTest testAlarmEvent = new PB01TAlarmEventTest(api,
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
		PB01APanicButtonClientAPI api = TestAPIBase.getClientApi(PB01APanicButtonClientAPI.class);

		// [0]: create an organization to act as parent of division, a division to act as parent of a service and a service to act as perent of locations
		// -- organization
		TestPersistableModelObjectManager<PB01AOrganizationOID,PB01AOrganization> orgFactory = TestPersistableModelObjectManager.create(PB01AOrganization.class,
																																	  new PB01TMockOrganizationFactory(),
																																	  api.organizationsAPI()
																																	 			.getForCRUD());
		orgFactory.setUpMockObjs(1);
		PB01AOrganization divParentOrg = orgFactory.getAnyCreatedMockObj();
		// -- division
		TestPersistableModelObjectManager<PB01AOrgDivisionOID,PB01AOrgDivision> divFactory = TestPersistableModelObjectManager.create(PB01AOrgDivision.class,
																																    new PB01TMockOrgDivisionFactory(divParentOrg),
																																    api.orgDivisionsAPI()
																																	 		.getForCRUD());
		divFactory.setUpMockObjs(1);
		PB01AOrgDivision serviceParentDivision = divFactory.getAnyCreatedMockObj();
		// -- service
		TestPersistableModelObjectManager<PB01AOrgDivisionServiceOID,PB01AOrgDivisionService> serviceFactory = TestPersistableModelObjectManager.create(PB01AOrgDivisionService.class,
																																					  new PB01TMockOrgDivisionServiceFactory(divParentOrg,serviceParentDivision),
																																				      api.orgDivisionServicesAPI()
																																				      .getForCRUD());
		serviceFactory.setUpMockObjs(1);
		PB01AOrgDivisionService locationParentService = serviceFactory.getAnyCreatedMockObj();
		// -- location
		TestPersistableModelObjectManager<PB01AOrgDivisionServiceLocationOID,PB01AOrgDivisionServiceLocation> locationFactory = TestPersistableModelObjectManager.create(PB01AOrgDivisionServiceLocation.class,
																																									   new PB01TMockOrgDivisionServiceLocationFactory(divParentOrg,serviceParentDivision,locationParentService),
																																								       api.orgDivisionServiceLocationsAPI()
																																									 			.getForCRUD());
		serviceFactory.setUpMockObjs(1);
		PB01AOrgDivisionServiceLocation workPlaceParentLocation = locationFactory.getAnyCreatedMockObj();
		// -- workPlace
		TestPersistableModelObjectManager<PB01AWorkPlaceOID,PB01AWorkPlace> workPlaceFactory = TestPersistableModelObjectManager.create(PB01AWorkPlace.class,
																																      new PB01TMockWorkPlaceFactory(divParentOrg,serviceParentDivision,locationParentService,workPlaceParentLocation),
																																      api.workPlacesAPI()
																																				.getForCRUD());
		workPlaceFactory.setUpMockObjs(1);
		PB01AWorkPlace alarmParentWorkPlace = workPlaceFactory.getAnyCreatedMockObj();

		// [1]: Testing
		log.warn("===========================================================");
		log.warn("TEST SEARCH");
		log.warn("===========================================================");
		final PB01TModelObjectSearchTest testSearch = new PB01TModelObjectSearchTest(api,
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
