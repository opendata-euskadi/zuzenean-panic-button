package pb01c.db.find;

import java.util.Collection;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

import pb01a.api.interfaces.PB01AFindServicesForAlarmEvent;
import pb01a.model.PB01AAlarmEvent;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionID;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionServiceID;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionServiceLocationID;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgObjectID;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrganizationID;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AWorkPlaceID;
import pb01a.model.oids.PB01APanicButtonOIDs.PB01AAlarmEventOID;
import pb01c.db.PB01CAlarmEventDBEntityToAndFromModelObject;
import pb01c.db.entities.PB01CDBEntityForAlarmEvent;
import r01f.model.persistence.FindResult;
import r01f.model.persistence.FindResultBuilder;
import r01f.objectstreamer.Marshaller;
import r01f.persistence.db.DBFindForModelObjectBase;
import r01f.persistence.db.config.DBModuleConfig;
import r01f.persistence.db.entities.primarykeys.DBPrimaryKeyForModelObject;
import r01f.securitycontext.SecurityContext;
import r01f.types.Range;
import r01f.types.contact.EMail;
import r01f.types.contact.Phone;

/**
 * Persistence layer
 */
public class PB01CDBFindForAlarmEvent
	 extends DBFindForModelObjectBase<PB01AAlarmEventOID,PB01AAlarmEvent,
	 								  DBPrimaryKeyForModelObject,PB01CDBEntityForAlarmEvent>
  implements PB01AFindServicesForAlarmEvent {
/////////////////////////////////////////////////////////////////////////////////////////
//  FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	private final PB01CAlarmEventDBEntityToAndFromModelObject _transformer;
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01CDBFindForAlarmEvent(final DBModuleConfig dbCfg,
								   final EntityManager entityManager,
								   final Marshaller marshaller) {
		super(PB01AAlarmEvent.class,PB01CDBEntityForAlarmEvent.class,
			  dbCfg,
			  entityManager,
			  marshaller);
		_transformer = new PB01CAlarmEventDBEntityToAndFromModelObject();
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  EXTENSION METHODS
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public FindResult<PB01AAlarmEvent> findBySourceId(final SecurityContext securityContext,
											         final PB01AOrganizationID id,
											         final Range<Date> dateRange) {
		return _findBySourceId("PB01DBEntitiesForAlarmEventsByOrganizationID",
							   securityContext,
							   id,dateRange);
	}
	@Override
	public FindResult<PB01AAlarmEvent> findBySourceId(final SecurityContext securityContext,
											         final PB01AOrgDivisionID id,
											         final Range<Date> dateRange) {
		return _findBySourceId("PB01DBEntitiesForAlarmEventsByDivisionID",
							   securityContext,
							   id,dateRange);
	}
	@Override
	public FindResult<PB01AAlarmEvent> findBySourceId(final SecurityContext securityContext,
											         final PB01AOrgDivisionServiceID id,
											         final Range<Date> dateRange) {
		return _findBySourceId("PB01DBEntitiesForAlarmEventsByServiceID",
							   securityContext,
							   id,dateRange);
	}
	@Override
	public FindResult<PB01AAlarmEvent> findBySourceId(final SecurityContext securityContext,
											         final PB01AOrgDivisionServiceLocationID id,
											         final Range<Date> dateRange) {
		return _findBySourceId("PB01DBEntitiesForAlarmEventsByLocationID",
							   securityContext,
							   id,dateRange);
	}
	@Override
	public FindResult<PB01AAlarmEvent> findBySourceId(final SecurityContext securityContext,
											         final PB01AWorkPlaceID id,
											         final Range<Date> dateRange) {
		return _findBySourceId("PB01DBEntitiesForAlarmEventsByWorkPlaceID",
							   securityContext,
							   id,dateRange);
	}
	@Override
	public FindResult<PB01AAlarmEvent> findByNotifiedPhone(final SecurityContext securityContext,
											   		 	  final Phone phone,
											   		 	  final Range<Date> dateRange) {
		// there's NO info about the phone stored at PB01CDBEntityForAlarmEvent db entity
		// maybe a column can store all notified phones comma separated...
		// ... is this function really necessary???
		throw new UnsupportedOperationException("Not yet implemented!!");
	}
	@Override
	public FindResult<PB01AAlarmEvent> findByNotifiedEMail(final SecurityContext securityContext,
											   		 	  final EMail email,
											   		 	  final Range<Date> dateRange) {
		// there's NO info about the phone stored at PB01CDBEntityForAlarmEvent db entity
		// maybe a column can store all notified phones comma separated...
		// ... is this function really necessary???
		throw new UnsupportedOperationException("Not yet implemented!!");
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	private FindResult<PB01AAlarmEvent> _findBySourceId(final String namedQuery,
													   final SecurityContext securityContext,
											           final PB01AOrgObjectID<?> id,
											           final Range<Date> dateRange) {
		// Do the query
		TypedQuery<PB01CDBEntityForAlarmEvent> query = this.getEntityManager()
														  .createNamedQuery(namedQuery,PB01CDBEntityForAlarmEvent.class)
														  .setParameter("startDate",dateRange.getLowerBound(),TemporalType.TIMESTAMP)
														  .setParameter("endDate",dateRange.getUpperBound(),TemporalType.TIMESTAMP)
														  .setParameter("id",id.asString());
		query.setHint(QueryHints.READ_ONLY,HintValues.TRUE);
		Collection<PB01CDBEntityForAlarmEvent> dbEntities = query.getResultList();

		// Return
		return FindResultBuilder.using(securityContext)
								.on(_modelObjectType)
								.foundDBEntities(dbEntities)
								.transformedToModelObjectsUsing(_transformer);
	}
}
