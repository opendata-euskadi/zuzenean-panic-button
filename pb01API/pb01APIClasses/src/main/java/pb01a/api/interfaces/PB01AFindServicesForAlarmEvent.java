package pb01a.api.interfaces;

import java.util.Date;

import pb01a.model.PB01AAlarmEvent;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionID;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionServiceID;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionServiceLocationID;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrganizationID;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AWorkPlaceID;
import pb01a.model.oids.PB01APanicButtonOIDs.PB01AAlarmEventOID;
import r01f.model.persistence.FindResult;
import r01f.securitycontext.SecurityContext;
import r01f.services.interfaces.ExposedServiceInterface;
import r01f.services.interfaces.FindServicesForModelObject;
import r01f.types.Range;
import r01f.types.contact.EMail;
import r01f.types.contact.Phone;

@ExposedServiceInterface
public interface PB01AFindServicesForAlarmEvent
         extends FindServicesForModelObject<PB01AAlarmEventOID,PB01AAlarmEvent>,
         		 PB01APanicButtonServiceInterface {
	/**
	 * Find all {@link PB01AAlarmEvent} objects by the organization id
	 * @param securityContext
	 * @param id
	 * @param dateRange
	 * @return
	 */
	public FindResult<PB01AAlarmEvent> findBySourceId(final SecurityContext securityContext,
											   		 final PB01AOrganizationID id,
											   		 final Range<Date> dateRange);
	/**
	 * Find all {@link PB01AAlarmEvent} objects by the division id
	 * @param securityContext
	 * @param id
	 * @param dateRange
	 * @return
	 */
	public FindResult<PB01AAlarmEvent> findBySourceId(final SecurityContext securityContext,
											   		 final PB01AOrgDivisionID id,
											   		 final Range<Date> dateRange);
	/**
	 * Find all {@link PB01AAlarmEvent} objects by the service id
	 * @param securityContext
	 * @param id
	 * @param dateRange
	 * @return
	 */
	public FindResult<PB01AAlarmEvent> findBySourceId(final SecurityContext securityContext,
											   		 final PB01AOrgDivisionServiceID id,
											   		 final Range<Date> dateRange);
	/**
	 * Find all {@link PB01AAlarmEvent} objects by the location id
	 * @param securityContext
	 * @param id
	 * @param dateRange
	 * @return
	 */
	public FindResult<PB01AAlarmEvent> findBySourceId(final SecurityContext securityContext,
											   		 final PB01AOrgDivisionServiceLocationID id,
											   		 final Range<Date> dateRange);
	/**
	 * Find all {@link PB01AAlarmEvent} objects by the work place idO
	 * @param securityContext
	 * @param id
	 * @param dateRange
	 * @return
	 */
	public FindResult<PB01AAlarmEvent> findBySourceId(final SecurityContext securityContext,
											   		 final PB01AWorkPlaceID id,
											   		 final Range<Date> dateRange);
	/**
	 * Find all {@link PB01AAlarmEvent} objects by the notified phone
	 * @param securityContext
	 * @param phone
	 * @param dateRange
	 * @return
	 */
	public FindResult<PB01AAlarmEvent> findByNotifiedPhone(final SecurityContext securityContext,
											   		 	  final Phone phone,
											   		 	  final Range<Date> dateRange);
	/**
	 * Find all {@link PB01AAlarmEvent} objects by the notified email
	 * @param securityContext
	 * @param email
	 * @param dateRange
	 * @return
	 */
	public FindResult<PB01AAlarmEvent> findByNotifiedEMail(final SecurityContext securityContext,
											   		 	  final EMail email,
											   		 	  final Range<Date> dateRange);
}