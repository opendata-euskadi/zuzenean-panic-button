package x47b.api.interfaces;

import java.util.Date;

import r01f.model.persistence.FindResult;
import r01f.securitycontext.SecurityContext;
import r01f.services.interfaces.ExposedServiceInterface;
import r01f.services.interfaces.FindServicesForModelObject;
import r01f.types.Range;
import r01f.types.contact.EMail;
import r01f.types.contact.Phone;
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
	 * @param securityContext
	 * @param id
	 * @param dateRange
	 * @return
	 */
	public FindResult<X47BAlarmEvent> findBySourceId(final SecurityContext securityContext,
											   		 final X47BOrganizationID id,
											   		 final Range<Date> dateRange);
	/**
	 * Find all {@link X47BAlarmEvent} objects by the division id
	 * @param securityContext
	 * @param id
	 * @param dateRange
	 * @return
	 */
	public FindResult<X47BAlarmEvent> findBySourceId(final SecurityContext securityContext,
											   		 final X47BOrgDivisionID id,
											   		 final Range<Date> dateRange);
	/**
	 * Find all {@link X47BAlarmEvent} objects by the service id
	 * @param securityContext
	 * @param id
	 * @param dateRange
	 * @return
	 */
	public FindResult<X47BAlarmEvent> findBySourceId(final SecurityContext securityContext,
											   		 final X47BOrgDivisionServiceID id,
											   		 final Range<Date> dateRange);
	/**
	 * Find all {@link X47BAlarmEvent} objects by the location id
	 * @param securityContext
	 * @param id
	 * @param dateRange
	 * @return
	 */
	public FindResult<X47BAlarmEvent> findBySourceId(final SecurityContext securityContext,
											   		 final X47BOrgDivisionServiceLocationID id,
											   		 final Range<Date> dateRange);
	/**
	 * Find all {@link X47BAlarmEvent} objects by the work place idO
	 * @param securityContext
	 * @param id
	 * @param dateRange
	 * @return
	 */
	public FindResult<X47BAlarmEvent> findBySourceId(final SecurityContext securityContext,
											   		 final X47BWorkPlaceID id,
											   		 final Range<Date> dateRange);
	/**
	 * Find all {@link X47BAlarmEvent} objects by the notified phone
	 * @param securityContext
	 * @param phone
	 * @param dateRange
	 * @return
	 */
	public FindResult<X47BAlarmEvent> findByNotifiedPhone(final SecurityContext securityContext,
											   		 	  final Phone phone,
											   		 	  final Range<Date> dateRange);
	/**
	 * Find all {@link X47BAlarmEvent} objects by the notified email
	 * @param securityContext
	 * @param email
	 * @param dateRange
	 * @return
	 */
	public FindResult<X47BAlarmEvent> findByNotifiedEMail(final SecurityContext securityContext,
											   		 	  final EMail email,
											   		 	  final Range<Date> dateRange);
}