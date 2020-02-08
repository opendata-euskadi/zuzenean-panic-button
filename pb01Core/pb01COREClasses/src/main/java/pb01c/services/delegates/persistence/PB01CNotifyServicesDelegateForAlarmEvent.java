package pb01c.services.delegates.persistence;

import java.util.Date;

import javax.persistence.EntityManager;

import com.google.common.eventbus.EventBus;

import lombok.extern.slf4j.Slf4j;
import pb01a.api.interfaces.PB01ANotifyServicesForAlarmEvent;
import pb01a.model.PB01AAlarmEvent;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AWorkPlaceID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AWorkPlaceOID;
import pb01a.model.oids.PB01APanicButtonOIDs.PB01AAlarmEventOID;
import pb01a.model.org.PB01AOrgDivision;
import pb01a.model.org.PB01AOrgDivisionService;
import pb01a.model.org.PB01AOrgDivisionServiceLocation;
import pb01a.model.org.PB01AOrganization;
import pb01a.model.org.PB01AWorkPlace;
import pb01c.db.crud.PB01CDBCRUDForAlarmEvent;
import r01f.bootstrap.services.config.core.ServicesCoreBootstrapConfigWhenBeanExposed;
import r01f.model.persistence.CRUDResult;
import r01f.model.persistence.CRUDResultBuilder;
import r01f.objectstreamer.Marshaller;
import r01f.persistence.db.config.DBModuleConfigBuilder;
import r01f.securitycontext.SecurityContext;
import r01f.services.delegates.persistence.PersistenceServicesForModelObjectDelegateBase;

@Slf4j
public class PB01CNotifyServicesDelegateForAlarmEvent
	 extends PersistenceServicesForModelObjectDelegateBase<PB01AAlarmEventOID,PB01AAlarmEvent>
  implements PB01ANotifyServicesForAlarmEvent {
/////////////////////////////////////////////////////////////////////////////////////////
//  FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	private final PB01CCRUDServicesDelegateForOrganization _orgCRUD;
	private final PB01CCRUDServicesDelegateForOrgDivision _divisionCRUD;
	private final PB01CCRUDServicesDelegateForOrgDivisionService _serviceCRUD;
	private final PB01CCRUDServicesDelegateForOrgDivisionServiceLocation _locationCRUD;
	private final PB01CCRUDServicesDelegateForWorkPlace _workPlaceCRUD;

	private final PB01CCRUDServicesDelegateForAlarmEvent _alarmEventCRUD;
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01CNotifyServicesDelegateForAlarmEvent(final ServicesCoreBootstrapConfigWhenBeanExposed coreCfg,
												   final EntityManager entityManager,
												   final Marshaller marshaller,
					  			   		   	   	   final EventBus eventBus) {
		super(coreCfg,
			  PB01AAlarmEvent.class,
			  new PB01CDBCRUDForAlarmEvent(DBModuleConfigBuilder.dbModuleConfigFrom(coreCfg),
					  					  entityManager,
					  					  marshaller),
			  eventBus);
		_orgCRUD = new PB01CCRUDServicesDelegateForOrganization(coreCfg,
															   entityManager,
				 											   marshaller,
				 											   eventBus);
		_divisionCRUD = new PB01CCRUDServicesDelegateForOrgDivision(coreCfg,
																   entityManager,
			 													   marshaller,
			 													   eventBus);
		_serviceCRUD = new PB01CCRUDServicesDelegateForOrgDivisionService(coreCfg,
																		 entityManager,
				 														 marshaller,
				 														 eventBus);
		_locationCRUD = new PB01CCRUDServicesDelegateForOrgDivisionServiceLocation(coreCfg,
																				  entityManager,
				 																  marshaller,
				 																  eventBus);
		_workPlaceCRUD = new PB01CCRUDServicesDelegateForWorkPlace(coreCfg,
																  entityManager,
																  marshaller,
																  eventBus);
		_alarmEventCRUD = new PB01CCRUDServicesDelegateForAlarmEvent(coreCfg,
																	entityManager,	// reuse this delegate transactional status (if any)
																    marshaller,
																    eventBus);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	METHODS
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public CRUDResult<PB01AAlarmEvent> raiseAlarm(final SecurityContext securityContext,
												 final PB01AWorkPlaceID workPlaceId) {
		// [0] - Ensure the source id is valid
		if (workPlaceId == null) return CRUDResultBuilder.using(securityContext)
													     .on(_modelObjectType)
													     .notCreated()
																.becauseClientBadRequest("The alarm event source work place id MUST not be null in order to be raised")
																	.build();
		// [2] - Find the work place
		CRUDResult<PB01AWorkPlace> workPlaceLoadResult = _workPlaceCRUD.loadById(securityContext,
																	            workPlaceId);
		if (workPlaceLoadResult.hasFailed()
		 && workPlaceLoadResult.asCRUDError().wasBecauseClientRequestedEntityWasNOTFound()) {		// as(CRUDError.class)
			return CRUDResultBuilder.using(securityContext)
									.on(_modelObjectType)
									.notCreated()
										.becauseClientBadRequest("The alarm event source workPlaceId={} is NOT a valid one: it does NOT exists!!",workPlaceId)
											.build();
		} else if (workPlaceLoadResult.hasSucceeded()) {
			// The work place was found by it's machine's label
			PB01AWorkPlace workPlace = workPlaceLoadResult.getOrThrow();
			return this.raiseAlarm(securityContext,
								   workPlace.getOid());	// simply call the raise event with the work place's oid
		} else {
			// unknown error
			throw workPlaceLoadResult.asCRUDError().getPersistenceException();		// as(CRUDError.class)
		}
	}
	@Override
	public CRUDResult<PB01AAlarmEvent> raiseAlarm(final SecurityContext securityContext,
												 final PB01AWorkPlaceOID workPlaceOid) {
		log.info("New Alarm for sourceId={}",workPlaceOid);
		// [0] - Ensure the source id is valid
		if (workPlaceOid == null) return CRUDResultBuilder.using(securityContext)
														  .on(_modelObjectType)
														  .notCreated()
																.becauseClientBadRequest("The alarm event source work place oid MUST not be null in order to be raised")
																	.build();
		// [1] - Load the work place, location, service, division and organization
		// ... workPlace
		CRUDResult<PB01AWorkPlace> workPlaceLoadResult = _workPlaceCRUD.load(securityContext,
															       		    workPlaceOid);
		if (workPlaceLoadResult.hasFailed()
		 && workPlaceLoadResult.asCRUDError().wasBecauseClientRequestedEntityWasNOTFound()) {
			return CRUDResultBuilder.using(securityContext)
								  	.on(_modelObjectType)
								  	.notCreated()
									  	.becauseRequiredRelatedEntityWasNOTFound("The alarm event source id={} is NOT a valid one: either the organization, location or work place do NOT exists!!",workPlaceOid)
										.build();
		}
		PB01AWorkPlace workPlace = workPlaceLoadResult.getOrThrow();

		// ... location
		CRUDResult<PB01AOrgDivisionServiceLocation> locationLoadResult = _locationCRUD.load(securityContext,
																						   workPlace.getOrgDivisionServiceLocationRef().getOid());
		PB01AOrgDivisionServiceLocation location = locationLoadResult.getOrThrow();

		// ... service
		CRUDResult<PB01AOrgDivisionService> serviceLoadResult = _serviceCRUD.load(securityContext,
																				 workPlace.getOrgDivisionServiceRef().getOid());
		PB01AOrgDivisionService service = serviceLoadResult.getOrThrow();

		// ... division
		CRUDResult<PB01AOrgDivision> divisionLoadResult = _divisionCRUD.load(securityContext,
																		    workPlace.getOrgDivisionRef().getOid());
		PB01AOrgDivision division = divisionLoadResult.getOrThrow();

		// Org
		CRUDResult<PB01AOrganization> orgLoadResult = _orgCRUD.load(securityContext,
																   workPlace.getOrgRef().getOid());
		PB01AOrganization org = orgLoadResult.getOrThrow();


		// [2] - Create a new PB01AAlarmEvent entity
		PB01AAlarmEvent newAlarmEvent = new PB01AAlarmEvent();
		newAlarmEvent.setDateTime(new Date());
		newAlarmEvent.setOrganization(workPlace.getOrgRef());
		newAlarmEvent.setDivision(workPlace.getOrgDivisionRef());
		newAlarmEvent.setService(workPlace.getOrgDivisionServiceRef());
		newAlarmEvent.setLocation(workPlace.getOrgDivisionServiceLocationRef());
		newAlarmEvent.setWorkPlace(workPlace.getReference());

		// [3] - Create a new PB01AAlarmEvent entity using it's CRUD
		// IMPORTANT!! The entity creation will fire a CRUD event whose handler is in charge
		//	  		   of dealing with the messaging system to send notification
		CRUDResult<PB01AAlarmEvent> createResult = _alarmEventCRUD.create(securityContext,
							  											 newAlarmEvent);

		// [4] - Increase the alarm count at the org, division, service, location and work place
		// ... org
		org.increaseAlarmRaiseCount();
		CRUDResult<PB01AOrganization> orgUpdated = _orgCRUD.update(securityContext,
					   		 							  		  org);
		log.warn("\t-Increased the alarm raise count at the org with id={} (new count={})",org.getId(),orgUpdated.getOrThrow().getAlarmRaiseCount());

		// ... division
		division.increaseAlarmRaiseCount();
		CRUDResult<PB01AOrgDivision> divUpdated = _divisionCRUD.update(securityContext,
					   		 							  		 	  division);
		log.warn("\t-Increased the alarm raise count at the division with id={} (new count={})",division.getId(),divUpdated.getOrThrow().getAlarmRaiseCount());

		// ... service
		service.increaseAlarmRaiseCount();
		CRUDResult<PB01AOrgDivisionService> srvcUpdated = _serviceCRUD.update(securityContext,
					   		 							  		 	 		 service);
		log.warn("\t-Increased the alarm raise count at the service with id={} (new count={})",service.getId(),srvcUpdated.getOrThrow().getAlarmRaiseCount());

		// ... location
		location.increaseAlarmRaiseCount();
		CRUDResult<PB01AOrgDivisionServiceLocation> locUpdated = _locationCRUD.update(securityContext,
					   										 						 location);
		log.warn("\t-Increased the alarm raise count at the location with id={} (new count={})",location.getId(),locUpdated.getOrThrow().getAlarmRaiseCount());

		// ... work place
		workPlace.increaseAlarmRaiseCount();
		CRUDResult<PB01AWorkPlace> workPlaceUpdated = _workPlaceCRUD.update(securityContext,
						 									  	           workPlace);
		log.warn("\t-Increased the alarm raise count at the workPlace with id={} (new count={})",workPlace.getId(),workPlaceUpdated.getOrThrow().getAlarmRaiseCount());


		// [5] - Return the created alarm
		log.warn("Alarm raised for sourceId={}",workPlaceOid);
		return createResult;
	}
	@Override
	public CRUDResult<PB01AAlarmEvent> cancelAlarm(final SecurityContext securityContext,
												  final PB01AAlarmEventOID oid) {
		// [0] - Ensure the alarm event oid is valid
		if (oid == null) return CRUDResultBuilder.using(securityContext)
												 .on(_modelObjectType)
												 .notDeleted()
													.becauseClientBadRequest("The alarm event oid MUST not be null in order to be cancelled")
														.build();
		// [1] - Remove the event using the CRUD
		CRUDResult<PB01AAlarmEvent> deleteResult = _alarmEventCRUD.delete(securityContext,
							  											 oid);
		// [2] - Return the deleted alarm
		log.warn("Alarm with oid={} cancelled",oid);
		return deleteResult;

	}
}
