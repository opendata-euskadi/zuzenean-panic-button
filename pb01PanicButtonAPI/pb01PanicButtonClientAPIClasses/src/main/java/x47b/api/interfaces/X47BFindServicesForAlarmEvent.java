package x47b.api.interfaces;

import r01f.model.persistence.FindResult;
import r01f.securitycontext.SecurityContext;
import r01f.services.interfaces.ExposedServiceInterface;
import r01f.services.interfaces.FindServicesForModelObject;
import r01f.types.TimeLapse;
import x47b.model.X47BAlarmEvent;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionID;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionServiceID;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionServiceLocationID;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrganizationID;
import x47b.model.oids.X47BOrganizationalIDs.X47BWorkPlaceID;
import x47b.model.oids.X47BPanicButtonOIDs.X47BAlarmEventOID;

@ExposedServiceInterface
public interface X47BFindServicesForAlarmEvent
         extends FindServicesForModelObject<X47BAlarmEventOID,X47BAlarmEvent>,
         		 X47BPanicButtonServiceInterface {
	/**
	 * Find all {@link X47BAlarmEvent} objects by the organization id
	 * @param id
	 * @param timeLapse
	 * @return
	 */
	public FindResult<X47BAlarmEvent> findBySourceId(final SecurityContext securityContext,
											   		 final X47BOrganizationID id,
											   		 final TimeLapse timeLapse);
	/**
	 * Find all {@link X47BAlarmEvent} objects by the division id
	 * @param id
	 * @param timeLapse
	 * @return
	 */
	public FindResult<X47BAlarmEvent> findBySourceId(final SecurityContext securityContext,
											   		 final X47BOrgDivisionID id,
											   		 final TimeLapse timeLapse);
	/**
	 * Find all {@link X47BAlarmEvent} objects by the service id
	 * @param id
	 * @param timeLapse
	 * @return
	 */
	public FindResult<X47BAlarmEvent> findBySourceId(final SecurityContext securityContext,
											   		 final X47BOrgDivisionServiceID id,
											   		 final TimeLapse timeLapse);
	/**
	 * Find all {@link X47BAlarmEvent} objects by the location id
	 * @param id
	 * @param timeLapse
	 * @return
	 */
	public FindResult<X47BAlarmEvent> findBySourceId(final SecurityContext securityContext,
											   		 final X47BOrgDivisionServiceLocationID id,
											   		 final TimeLapse timeLapse);
	/**
	 * Find all {@link X47BAlarmEvent} objects by the work place idO
	 * @param id
	 * @param timeLapse
	 * @return
	 */
	public FindResult<X47BAlarmEvent> findBySourceId(final SecurityContext securityContext,
											   		 final X47BWorkPlaceID id,
											   		 final TimeLapse timeLapse);
}