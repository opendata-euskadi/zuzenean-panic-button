package x47b.services.delegates.persistence;

import javax.persistence.EntityManager;

import com.google.common.eventbus.EventBus;
import com.google.inject.persist.Transactional;

import r01f.bootstrap.services.config.core.ServicesCoreBootstrapConfigWhenBeanExposed;
import r01f.model.persistence.FindResult;
import r01f.objectstreamer.Marshaller;
import r01f.persistence.db.config.DBModuleConfigBuilder;
import r01f.securitycontext.SecurityContext;
import r01f.services.delegates.persistence.FindServicesForModelObjectDelegateBase;
import r01f.types.TimeLapse;
import x47b.api.interfaces.X47BFindServicesForAlarmEvent;
import x47b.db.find.X47BDBFindForAlarmEvent;
import x47b.model.X47BAlarmEvent;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionID;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionServiceID;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionServiceLocationID;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrganizationID;
import x47b.model.oids.X47BOrganizationalIDs.X47BWorkPlaceID;
import x47b.model.oids.X47BPanicButtonOIDs.X47BAlarmEventOID;

/**
 * Service layer delegated type for CRUD (Create/Read/Update/Delete) operations
 */
public class X47BFindServicesDelegateForAlarmEvent
	 extends FindServicesForModelObjectDelegateBase<X47BAlarmEventOID,X47BAlarmEvent>
  implements X47BFindServicesForAlarmEvent {

/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BFindServicesDelegateForAlarmEvent(final ServicesCoreBootstrapConfigWhenBeanExposed coreCfg,
												 final EntityManager entityManager,
											     final Marshaller marshaller,
				  			   		   	   	     final EventBus eventBus) {
		super(coreCfg,
			  X47BAlarmEvent.class,
			  new X47BDBFindForAlarmEvent(DBModuleConfigBuilder.dbModuleConfigFrom(coreCfg),
					  					  entityManager,
					  					  marshaller),
			  eventBus);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  EXTENSION METHODS
/////////////////////////////////////////////////////////////////////////////////////////
	@Transactional
	@Override
	public FindResult<X47BAlarmEvent> findBySourceId(final SecurityContext securityContext,
													 final X47BOrganizationID id,
													 final TimeLapse timeLapse) {
		return this.getServiceImplAs(X47BFindServicesForAlarmEvent.class)
						.findBySourceId(securityContext,
										id,
										timeLapse);
	}
	@Transactional
	@Override
	public FindResult<X47BAlarmEvent> findBySourceId(final SecurityContext securityContext,
													 final X47BOrgDivisionID id,
													 final TimeLapse timeLapse) {
		return this.getServiceImplAs(X47BFindServicesForAlarmEvent.class)
						.findBySourceId(securityContext,
										id,
										timeLapse);
	}
	@Transactional
	@Override
	public FindResult<X47BAlarmEvent> findBySourceId(final SecurityContext securityContext,
													 final X47BOrgDivisionServiceID id,
													 final TimeLapse timeLapse) {
		return this.getServiceImplAs(X47BFindServicesForAlarmEvent.class)
						.findBySourceId(securityContext,
										id,
										timeLapse);
	}
	@Transactional
	@Override
	public FindResult<X47BAlarmEvent> findBySourceId(final SecurityContext securityContext,
													 final X47BOrgDivisionServiceLocationID id,
													 final TimeLapse timeLapse) {
		return this.getServiceImplAs(X47BFindServicesForAlarmEvent.class)
						.findBySourceId(securityContext,
										id,
										timeLapse);
	}
	@Transactional
	@Override
	public FindResult<X47BAlarmEvent> findBySourceId(final SecurityContext securityContext,
													 final X47BWorkPlaceID id,
													 final TimeLapse timeLapse) {
		return this.getServiceImplAs(X47BFindServicesForAlarmEvent.class)
						.findBySourceId(securityContext,
										id,
										timeLapse);
	}
}
