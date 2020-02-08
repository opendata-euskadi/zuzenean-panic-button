package pb01t.test.entities;

import java.util.Collection;

import org.junit.Assert;

import com.google.common.collect.Lists;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

import pb01a.bootstrap.client.panicbutton.PB01APanicButtonClientBootstrapConfigBuilder;
import pb01a.client.api.PB01APanicButtonClientAPI;
import pb01a.common.internal.P01AAppCodes;
import pb01a.model.PB01AAlarmEvent;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AWorkPlaceID;
import pb01a.model.oids.PB01APanicButtonOIDs.PB01AAlarmEventOID;
import pb01a.model.org.PB01AOrgDivision;
import pb01a.model.org.PB01AOrgDivisionService;
import pb01a.model.org.PB01AOrgDivisionServiceLocation;
import pb01a.model.org.PB01AOrganization;
import pb01a.model.org.PB01AWorkPlace;
import pb01c.bootstrap.core.panicbutton.PB01CPanicButtonCOREServicesBootstrapConfigBuilder;
import r01f.bootstrap.services.ServicesBootstrapUtil;
import r01f.bootstrap.services.config.ServicesBootstrapConfig;
import r01f.bootstrap.services.config.ServicesBootstrapConfigBuilder;
import r01f.bootstrap.services.config.client.ServicesClientBootstrapConfig;
import r01f.bootstrap.services.config.core.ServicesCoreBootstrapConfig;
import r01f.types.TimeLapse;
import r01f.util.types.collections.CollectionUtils;
import r01f.xmlproperties.XMLPropertiesBuilder;
import r01f.xmlproperties.XMLPropertiesForApp;

public class PB01TAlarmEventTest {
/////////////////////////////////////////////////////////////////////////////////////////
//  FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	private final PB01APanicButtonClientAPI _api;
	private final PB01AOrganization _organization;
	private final PB01AOrgDivision _division;
	private final PB01AOrgDivisionService _service;
	private final PB01AOrgDivisionServiceLocation _location;
	private final PB01AWorkPlace _workPlace;

/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01TAlarmEventTest(final PB01APanicButtonClientAPI api,
							  final PB01AOrganization organization,
							  final PB01AOrgDivision division,
							  final PB01AOrgDivisionService service,
							  final PB01AOrgDivisionServiceLocation location,
							  final PB01AWorkPlace workPlace) {
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
	private final Collection<PB01AAlarmEventOID> _raisedAlarms = Lists.newArrayList();

	public void doRaiseAlarms() {
		// simply create an alarm event for a source workPlace
		PB01AAlarmEvent createdAlarmBySourceId = _api.alarmEventsAPI()
														.getForNotify()
															.raiseAlarm(_workPlace.getId());
		Assert.assertNotNull(createdAlarmBySourceId);
		System.out.println("--------->Alarm created for " + _workPlace.getId() + ": " + createdAlarmBySourceId.getOid());
		_checkAlarmRaiseCount(1);	// 1 alarm expected

		_raisedAlarms.add(createdAlarmBySourceId.getOid());



		// Create another alarm for an workPlace host
		PB01AAlarmEvent createdAlarmByHost = _api.alarmEventsAPI()
													.getForNotify()
														.raiseAlarm(_workPlace.getOid());
		Assert.assertNotNull(createdAlarmByHost);
		System.out.println("--------->Alarm created for " + _workPlace.getOid() + ": " + createdAlarmBySourceId.getOid());
		_checkAlarmRaiseCount(2);	// 2 alarms expected

		_raisedAlarms.add(createdAlarmByHost.getOid());
	}
	public void doQueryAlarms(final TimeLapse timeLapse) {
		System.out.println("::::>Querying alarm events:");
		Collection<PB01AAlarmEvent> alarmEvents = _api.alarmEventsAPI()
														.getForFind()
															.findBySourceId(_workPlace.getId(),
																			timeLapse);
		Assert.assertTrue(CollectionUtils.hasData(alarmEvents));
		for (PB01AAlarmEvent event : alarmEvents) {
			System.out.println(">> Event created at " + event.getDateTime() + " at " + event.getHiearchyPath());
		}
	}
	public void doCancelAlarms() {
		// Remove the raised alarms
		if (CollectionUtils.isNullOrEmpty(_raisedAlarms)) return;

		for (PB01AAlarmEventOID alarmEventOid : _raisedAlarms) {
			PB01AAlarmEvent removedAlarm = _api.alarmEventsAPI()
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
		PB01AOrganization org = _api.organizationsAPI()
										.getForCRUD()
											.load(_organization.getOid());
		Assert.assertTrue(expectedCount == org.getAlarmRaiseCount());

		PB01AOrgDivisionServiceLocation loc = _api.orgDivisionServiceLocationsAPI()
													.getForCRUD()
														.load(_location.getOid());
		Assert.assertTrue(expectedCount == loc.getAlarmRaiseCount());

		PB01AWorkPlace workPlace = _api.workPlacesAPI()
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
		XMLPropertiesForApp xmlProps = XMLPropertiesBuilder.createForApp(P01AAppCodes.CORE_APPCODE)
														   .notUsingCache();
		// [1] - Create the modules bootstrap config
		ServicesClientBootstrapConfig clientBootCfg = PB01APanicButtonClientBootstrapConfigBuilder.buildClientBootstrapConfig();
		ServicesCoreBootstrapConfig coreBootCfg = PB01CPanicButtonCOREServicesBootstrapConfigBuilder.buildCoreBootstrapConfig(xmlProps);
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
		PB01APanicButtonClientAPI api = injector.getInstance(PB01APanicButtonClientAPI.class);

		PB01AWorkPlaceID sourceId = PB01AWorkPlaceID.forId("CO02549m");

		System.out.println("--------->Read alarms for id " + sourceId.asString());
		Collection<PB01AAlarmEvent>alarms = api.alarmEventsAPI().getForFind().findBySourceId(sourceId,TimeLapse.createFor("60h"));
		System.out.println("Tiene " + alarms.size() + " alarmas..........");
		int i = 0;
		for(PB01AAlarmEvent theAlarm:alarms) {
			System.out.println("---------> Alarm(" + (i++) + "):" + theAlarm.getHiearchyPath());
		}

	}
}
