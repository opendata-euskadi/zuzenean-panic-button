package x47b.services.delegates.persistence;

import java.util.Date;

import javax.persistence.EntityManager;

import com.google.common.eventbus.EventBus;

import lombok.extern.slf4j.Slf4j;
import r01f.bootstrap.services.config.core.ServicesCoreBootstrapConfigWhenBeanExposed;
import r01f.model.persistence.CRUDResult;
import r01f.model.persistence.CRUDResultBuilder;
import r01f.objectstreamer.Marshaller;
import r01f.persistence.db.config.DBModuleConfigBuilder;
import r01f.securitycontext.SecurityContext;
import r01f.services.delegates.persistence.PersistenceServicesForModelObjectDelegateBase;
import x47b.api.interfaces.X47BNotifyServicesForAlarmEvent;
import x47b.db.crud.X47BDBCRUDForAlarmEvent;
import x47b.model.X47BAlarmEvent;
import x47b.model.oids.X47BOrganizationalIDs.X47BWorkPlaceID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BWorkPlaceOID;
import x47b.model.oids.X47BPanicButtonOIDs.X47BAlarmEventOID;
import x47b.model.org.X47BOrgDivision;
import x47b.model.org.X47BOrgDivisionService;
import x47b.model.org.X47BOrgDivisionServiceLocation;
import x47b.model.org.X47BOrganization;
import x47b.model.org.X47BWorkPlace;

@Slf4j
public class X47BNotifyServicesDelegateForAlarmEvent
	 extends PersistenceServicesForModelObjectDelegateBase<X47BAlarmEventOID,X47BAlarmEvent>
  implements X47BNotifyServicesForAlarmEvent {
/////////////////////////////////////////////////////////////////////////////////////////
//  FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	private final X47BCRUDServicesDelegateForOrganization _orgCRUD;
	private final X47BCRUDServicesDelegateForOrgDivision _divisionCRUD;
	private final X47BCRUDServicesDelegateForOrgDivisionService _serviceCRUD;
	private final X47BCRUDServicesDelegateForOrgDivisionServiceLocation _locationCRUD;
	private final X47BCRUDServicesDelegateForWorkPlace _workPlaceCRUD;
	
	private final X47BCRUDServicesDelegateForAlarmEvent _alarmEventCRUD;
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BNotifyServicesDelegateForAlarmEvent(final ServicesCoreBootstrapConfigWhenBeanExposed coreCfg,
												   final EntityManager entityManager,
												   final Marshaller marshaller,
					  			   		   	   	   final EventBus eventBus) {
		super(coreCfg,
			  X47BAlarmEvent.class,
			  new X47BDBCRUDForAlarmEvent(DBModuleConfigBuilder.dbModuleConfigFrom(coreCfg),
					  					  entityManager,
					  					  marshaller),
			  eventBus);
		_orgCRUD = new X47BCRUDServicesDelegateForOrganization(coreCfg,
															   entityManager,
				 											   marshaller,
				 											   eventBus);
		_divisionCRUD = new X47BCRUDServicesDelegateForOrgDivision(coreCfg,
																   entityManager,
			 													   marshaller,
			 													   eventBus);
		_serviceCRUD = new X47BCRUDServicesDelegateForOrgDivisionService(coreCfg,
																		 entityManager,	
				 														 marshaller,
				 														 eventBus);
		_locationCRUD = new X47BCRUDServicesDelegateForOrgDivisionServiceLocation(coreCfg,
																				  entityManager,
				 																  marshaller,
				 																  eventBus);
		_workPlaceCRUD = new X47BCRUDServicesDelegateForWorkPlace(coreCfg,
																  entityManager,
																  marshaller,
																  eventBus);
		_alarmEventCRUD = new X47BCRUDServicesDelegateForAlarmEvent(coreCfg,
																	entityManager,	// reuse this delegate transactional status (if any)
																    marshaller,
																    eventBus);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	METHODS
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public CRUDResult<X47BAlarmEvent> raiseAlarm(final SecurityContext securityContext,
												 final X47BWorkPlaceID workPlaceId) {
		// [0] - Ensure the source id is valid
		if (workPlaceId == null) return CRUDResultBuilder.using(securityContext)
													     .on(_modelObjectType)
													     .notCreated()
																.becauseClientBadRequest("The alarm event source work place id MUST not be null in order to be raised")
																	.build();
		// [2] - Find the work place
		CRUDResult<X47BWorkPlace> workPlaceLoadResult = _workPlaceCRUD.loadById(securityContext,
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
			X47BWorkPlace workPlace = workPlaceLoadResult.getOrThrow();
			return this.raiseAlarm(securityContext,
								   workPlace.getOid());	// simply call the raise event with the work place's oid
		} else {
			// unknown error
			throw workPlaceLoadResult.asCRUDError().getPersistenceException();		// as(CRUDError.class)
		}
	}
	@Override
	public CRUDResult<X47BAlarmEvent> raiseAlarm(final SecurityContext securityContext,
												 final X47BWorkPlaceOID workPlaceOid) {
		log.info("New Alarm for sourceId={}",workPlaceOid);
		// [0] - Ensure the source id is valid
		if (workPlaceOid == null) return CRUDResultBuilder.using(securityContext)
														  .on(_modelObjectType)
														  .notCreated()
																.becauseClientBadRequest("The alarm event source work place oid MUST not be null in order to be raised")
																	.build();		
		// [1] - Load the work place, location, service, division and organization
		// ... workPlace
		CRUDResult<X47BWorkPlace> workPlaceLoadResult = _workPlaceCRUD.load(securityContext,
															       		    workPlaceOid);
		if (workPlaceLoadResult.hasFailed()
		 && workPlaceLoadResult.asCRUDError().wasBecauseClientRequestedEntityWasNOTFound()) {
			return CRUDResultBuilder.using(securityContext)
								  	.on(_modelObjectType)
								  	.notCreated()
									  	.becauseRequiredRelatedEntityWasNOTFound("The alarm event source id={} is NOT a valid one: either the organization, location or work place do NOT exists!!",workPlaceOid)
										.build();
		} 
		X47BWorkPlace workPlace = workPlaceLoadResult.getOrThrow();
		
		// ... location
		CRUDResult<X47BOrgDivisionServiceLocation> locationLoadResult = _locationCRUD.load(securityContext,
																						   workPlace.getOrgDivisionServiceLocationRef().getOid());
		X47BOrgDivisionServiceLocation location = locationLoadResult.getOrThrow();
		
		// ... service
		CRUDResult<X47BOrgDivisionService> serviceLoadResult = _serviceCRUD.load(securityContext,
																				 workPlace.getOrgDivisionServiceRef().getOid());
		X47BOrgDivisionService service = serviceLoadResult.getOrThrow();
		
		// ... division
		CRUDResult<X47BOrgDivision> divisionLoadResult = _divisionCRUD.load(securityContext,
																		    workPlace.getOrgDivisionRef().getOid());
		X47BOrgDivision division = divisionLoadResult.getOrThrow();
		
		// Org
		CRUDResult<X47BOrganization> orgLoadResult = _orgCRUD.load(securityContext,
																   workPlace.getOrgRef().getOid());
		X47BOrganization org = orgLoadResult.getOrThrow();

		
		// [2] - Create a new X47BAlarmEvent entity
		X47BAlarmEvent newAlarmEvent = new X47BAlarmEvent();
		newAlarmEvent.setTimeStamp(new Date());
		newAlarmEvent.setOrganization(workPlace.getOrgRef());
		newAlarmEvent.setDivision(workPlace.getOrgDivisionRef());
		newAlarmEvent.setService(workPlace.getOrgDivisionServiceRef());
		newAlarmEvent.setLocation(workPlace.getOrgDivisionServiceLocationRef());
		newAlarmEvent.setWorkPlace(workPlace.getReference());

		// [3] - Create a new X47BAlarmEvent entity using it's CRUD
		// IMPORTANT!! The entity creation will fire a CRUD event whose handler is in charge
		//	  		   of dealing with the messaging system to send notification
		CRUDResult<X47BAlarmEvent> createResult = _alarmEventCRUD.create(securityContext,
							  											 newAlarmEvent);
		
		// [4] - Increase the alarm count at the org, division, service, location and work place
		// ... org
		org.increaseAlarmRaiseCount();
		CRUDResult<X47BOrganization> orgUpdated = _orgCRUD.update(securityContext,
					   		 							  		  org);
		log.warn("\t-Increased the alarm raise count at the org with id={} (new count={})",org.getId(),orgUpdated.getOrThrow().getAlarmRaiseCount());

		// ... division
		division.increaseAlarmRaiseCount();
		CRUDResult<X47BOrgDivision> divUpdated = _divisionCRUD.update(securityContext,
					   		 							  		 	  division);
		log.warn("\t-Increased the alarm raise count at the division with id={} (new count={})",division.getId(),divUpdated.getOrThrow().getAlarmRaiseCount());
		
		// ... service
		service.increaseAlarmRaiseCount();
		CRUDResult<X47BOrgDivisionService> srvcUpdated = _serviceCRUD.update(securityContext,
					   		 							  		 	 		 service);
		log.warn("\t-Increased the alarm raise count at the service with id={} (new count={})",service.getId(),srvcUpdated.getOrThrow().getAlarmRaiseCount());
		
		// ... location
		location.increaseAlarmRaiseCount();
		CRUDResult<X47BOrgDivisionServiceLocation> locUpdated = _locationCRUD.update(securityContext,
					   										 						 location);
		log.warn("\t-Increased the alarm raise count at the location with id={} (new count={})",location.getId(),locUpdated.getOrThrow().getAlarmRaiseCount());
		
		// ... work place
		workPlace.increaseAlarmRaiseCount();
		CRUDResult<X47BWorkPlace> workPlaceUpdated = _workPlaceCRUD.update(securityContext,
						 									  	           workPlace);
		log.warn("\t-Increased the alarm raise count at the workPlace with id={} (new count={})",workPlace.getId(),workPlaceUpdated.getOrThrow().getAlarmRaiseCount());
		
		
		// [5] - Return the created alarm
		log.warn("Alarm raised for sourceId={}",workPlaceOid);
		return createResult;
	}
	@Override
	public CRUDResult<X47BAlarmEvent> cancelAlarm(final SecurityContext securityContext,
												  final X47BAlarmEventOID oid) {
		// [0] - Ensure the alarm event oid is valid
		if (oid == null) return CRUDResultBuilder.using(securityContext)
												 .on(_modelObjectType)
												 .notDeleted()
													.becauseClientBadRequest("The alarm event oid MUST not be null in order to be cancelled")
														.build();
		// [1] - Remove the event using the CRUD
		CRUDResult<X47BAlarmEvent> deleteResult = _alarmEventCRUD.delete(securityContext,
							  											 oid);
		// [2] - Return the deleted alarm
		log.warn("Alarm with oid={} cancelled",oid);
		return deleteResult;
		
	}
}
