package x47b.test.entities;

import java.util.Collection;

import org.junit.Assert;

import com.google.common.collect.Lists;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

import r01f.bootstrap.services.ServicesBootstrapUtil;
import r01f.bootstrap.services.config.ServicesBootstrapConfig;
import r01f.bootstrap.services.config.ServicesBootstrapConfigBuilder;
import r01f.bootstrap.services.config.client.ServicesClientBootstrapConfig;
import r01f.bootstrap.services.config.core.ServicesCoreBootstrapConfig;
import r01f.types.TimeLapse;
import r01f.util.types.collections.CollectionUtils;
import r01f.xmlproperties.XMLPropertiesBuilder;
import r01f.xmlproperties.XMLPropertiesForApp;
import x47b.bootstrap.client.panicbutton.X47BPanicButtonClientBootstrapConfigBuilder;
import x47b.bootstrap.core.panicbutton.X47BPanicButtonCOREServicesBootstrapConfigBuilder;
import x47b.client.api.X47BPanicButtonClientAPI;
import x47b.common.internal.X47BAppCodes;
import x47b.model.X47BAlarmEvent;
import x47b.model.oids.X47BOrganizationalIDs.X47BWorkPlaceID;
import x47b.model.oids.X47BPanicButtonOIDs.X47BAlarmEventOID;
import x47b.model.org.X47BOrgDivision;
import x47b.model.org.X47BOrgDivisionService;
import x47b.model.org.X47BOrgDivisionServiceLocation;
import x47b.model.org.X47BOrganization;
import x47b.model.org.X47BWorkPlace;

public class X47BAlarmEventTest {
/////////////////////////////////////////////////////////////////////////////////////////
//  FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	private final X47BPanicButtonClientAPI _api;
	private final X47BOrganization _organization;
	private final X47BOrgDivision _division;
	private final X47BOrgDivisionService _service;
	private final X47BOrgDivisionServiceLocation _location;
	private final X47BWorkPlace _workPlace;

/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BAlarmEventTest(final X47BPanicButtonClientAPI api,
							  final X47BOrganization organization,
							  final X47BOrgDivision division,
							  final X47BOrgDivisionService service,
							  final X47BOrgDivisionServiceLocation location,
							  final X47BWorkPlace workPlace) {
		_api = api;
		_organization = organization;
		_division = division;
		_service = service;
		_location = location;
		_workPlace = workPlace;
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  TEST
/////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Stores the raised alarms
	 */
	private final Collection<X47BAlarmEventOID> _raisedAlarms = Lists.newArrayList();

	public void doRaiseAlarms() {
		// simply create an alarm event for a source workPlace
		X47BAlarmEvent createdAlarmBySourceId = _api.alarmEventsAPI()
														.getForNotify()
															.raiseAlarm(_workPlace.getId());
		Assert.assertNotNull(createdAlarmBySourceId);
		System.out.println("--------->Alarm created for " + _workPlace.getId() + ": " + createdAlarmBySourceId.getOid());
		_checkAlarmRaiseCount(1);	// 1 alarm expected

		_raisedAlarms.add(createdAlarmBySourceId.getOid());



		// Create another alarm for an workPlace host
		X47BAlarmEvent createdAlarmByHost = _api.alarmEventsAPI()
													.getForNotify()
														.raiseAlarm(_workPlace.getOid());
		Assert.assertNotNull(createdAlarmByHost);
		System.out.println("--------->Alarm created for " + _workPlace.getOid() + ": " + createdAlarmBySourceId.getOid());
		_checkAlarmRaiseCount(2);	// 2 alarms expected

		_raisedAlarms.add(createdAlarmByHost.getOid());
	}
	public void doQueryAlarms(final TimeLapse timeLapse) {
		System.out.println("::::>Querying alarm events:");
		Collection<X47BAlarmEvent> alarmEvents = _api.alarmEventsAPI()
														.getForFind()
															.findBySourceId(_workPlace.getId(),
																			timeLapse);
		Assert.assertTrue(CollectionUtils.hasData(alarmEvents));
		for (X47BAlarmEvent event : alarmEvents) {
			System.out.println(">> Event created at " + event.getDateTime() + " at " + event.getHiearchyPath());
		}
	}
	public void doCancelAlarms() {
		// Remove the raised alarms
		if (CollectionUtils.isNullOrEmpty(_raisedAlarms)) return;

		for (X47BAlarmEventOID alarmEventOid : _raisedAlarms) {
			X47BAlarmEvent removedAlarm = _api.alarmEventsAPI()
												.getForNotify()
													.cancelAlarm(alarmEventOid);
			Assert.assertNotNull(removedAlarm);
			System.out.println("--------->Alarm "+ alarmEventOid + " REMOVED!");
		}
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	private void _checkAlarmRaiseCount(final int expectedCount) {
		X47BOrganization org = _api.organizationsAPI()
										.getForCRUD()
											.load(_organization.getOid());
		Assert.assertTrue(expectedCount == org.getAlarmRaiseCount());

		X47BOrgDivisionServiceLocation loc = _api.orgDivisionServiceLocationsAPI()
													.getForCRUD()
														.load(_location.getOid());
		Assert.assertTrue(expectedCount == loc.getAlarmRaiseCount());

		X47BWorkPlace workPlace = _api.workPlacesAPI()
										.getForCRUD()
											.load(_workPlace.getOid());
		Assert.assertTrue(expectedCount == workPlace.getAlarmRaiseCount());

		System.out.println(">>Alarm raise count: ");
		System.out.println("\t>Organization: " + org.getAlarmRaiseCount());
		System.out.println("\t>    Location: " + loc.getAlarmRaiseCount());
		System.out.println("\t>   WorkPlace: " + workPlace.getAlarmRaiseCount());
	}

	public static void main(final String[] args) {
		// [0] - Load properties
		XMLPropertiesForApp xmlProps = XMLPropertiesBuilder.createForApp(X47BAppCodes.CORE_APPCODE)
														   .notUsingCache();
		// [1] - Create the modules bootstrap config
		ServicesClientBootstrapConfig clientBootCfg = X47BPanicButtonClientBootstrapConfigBuilder.buildClientBootstrapConfig();
		ServicesCoreBootstrapConfig coreBootCfg = X47BPanicButtonCOREServicesBootstrapConfigBuilder.buildCoreBootstrapConfig(xmlProps);
		ServicesBootstrapConfig bootstrapCfg = ServicesBootstrapConfigBuilder
														.forClient(clientBootCfg)
													    .ofCoreModules(coreBootCfg)
													    .coreEventsHandledSynchronously()
													    .build();

		// [2]: Create the injector
		Iterable<Module> bootstrapModuleInstances = ServicesBootstrapUtil.getBootstrapGuiceModules(bootstrapCfg)
																		   .withoutCommonBindingModules();
		Injector injector = Guice.createInjector(bootstrapModuleInstances);
		// If stand-alone (no app-server is used), init the JPA service or any service that needs to be started
		// like the search engine index
		// 		If the core is available at client classpath, start it
		// 		This is the case where there's no app-server
		// 		(usually the JPA's ServiceHandler is binded at the Guice module extending DBGuiceModuleBase at core side)
		ServicesBootstrapUtil.startServices(injector);

		// [3] -Get the api
		X47BPanicButtonClientAPI api = injector.getInstance(X47BPanicButtonClientAPI.class);

		X47BWorkPlaceID sourceId = X47BWorkPlaceID.forId("CO02549m");

		System.out.println("--------->Read alarms for id " + sourceId.asString());
		Collection<X47BAlarmEvent>alarms = api.alarmEventsAPI().getForFind().findBySourceId(sourceId,TimeLapse.createFor("60h"));
		System.out.println("Tiene " + alarms.size() + " alarmas..........");
		int i = 0;
		for(X47BAlarmEvent theAlarm:alarms) {
			System.out.println("---------> Alarm(" + (i++) + "):" + theAlarm.getHiearchyPath());
		}

	}
}
