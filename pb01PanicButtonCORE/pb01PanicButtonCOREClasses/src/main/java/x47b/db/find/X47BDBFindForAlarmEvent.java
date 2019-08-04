package x47b.db.find;

import java.util.Collection;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

import r01f.model.persistence.FindResult;
import r01f.model.persistence.FindResultBuilder;
import r01f.objectstreamer.Marshaller;
import r01f.persistence.db.DBFindForModelObjectBase;
import r01f.persistence.db.config.DBModuleConfig;
import r01f.persistence.db.entities.primarykeys.DBPrimaryKeyForModelObject;
import r01f.securitycontext.SecurityContext;
import r01f.types.Range;
import x47b.api.interfaces.X47BFindServicesForAlarmEvent;
import x47b.db.X47BAlarmEventDBEntityToAndFromModelObject;
import x47b.db.entities.X47BDBEntityForAlarmEvent;
import x47b.model.X47BAlarmEvent;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionID;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionServiceID;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionServiceLocationID;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrganizationID;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgObjectID;
import x47b.model.oids.X47BOrganizationalIDs.X47BWorkPlaceID;
import x47b.model.oids.X47BPanicButtonOIDs.X47BAlarmEventOID;

/**
 * Persistence layer
 */
public class X47BDBFindForAlarmEvent
	 extends DBFindForModelObjectBase<X47BAlarmEventOID,X47BAlarmEvent,
	 								  DBPrimaryKeyForModelObject,X47BDBEntityForAlarmEvent>
  implements X47BFindServicesForAlarmEvent {
/////////////////////////////////////////////////////////////////////////////////////////
//  FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	private final X47BAlarmEventDBEntityToAndFromModelObject _transformer;
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BDBFindForAlarmEvent(final DBModuleConfig dbCfg,
								   final EntityManager entityManager,
								   final Marshaller marshaller) {
		super(X47BAlarmEvent.class,X47BDBEntityForAlarmEvent.class,
			  dbCfg,
			  entityManager,
			  marshaller);
		_transformer = new X47BAlarmEventDBEntityToAndFromModelObject();
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  EXTENSION METHODS
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public FindResult<X47BAlarmEvent> findBySourceId(final SecurityContext securityContext,
											         final X47BOrganizationID id,
											         final Range<Date> dateRange) {
		return _findBySourceId("X47BDBEntitiesForAlarmEventsByOrganizationID",
							   securityContext,
							   id,dateRange);
	}
	@Override
	public FindResult<X47BAlarmEvent> findBySourceId(final SecurityContext securityContext,
											         final X47BOrgDivisionID id,
											         final Range<Date> dateRange) {
		return _findBySourceId("X47BDBEntitiesForAlarmEventsByDivisionID",
							   securityContext,
							   id,dateRange);
	}
	@Override
	public FindResult<X47BAlarmEvent> findBySourceId(final SecurityContext securityContext,
											         final X47BOrgDivisionServiceID id,
											         final Range<Date> dateRange) {
		return _findBySourceId("X47BDBEntitiesForAlarmEventsByServiceID",
							   securityContext,
							   id,dateRange);
	}
	@Override
	public FindResult<X47BAlarmEvent> findBySourceId(final SecurityContext securityContext,
											         final X47BOrgDivisionServiceLocationID id,
											         final Range<Date> dateRange) {
		return _findBySourceId("X47BDBEntitiesForAlarmEventsByLocationID",
							   securityContext,
							   id,dateRange);
	}
	@Override
	public FindResult<X47BAlarmEvent> findBySourceId(final SecurityContext securityContext,
											         final X47BWorkPlaceID id,
											         final Range<Date> dateRange) {
		return _findBySourceId("X47BDBEntitiesForAlarmEventsByWorkPlaceID",
							   securityContext,
							   id,dateRange);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	private FindResult<X47BAlarmEvent> _findBySourceId(final String namedQuery,
													   final SecurityContext securityContext,
											           final X47BOrgObjectID<?> id,
											           final Range<Date> dateRange) {
		// Do the query
		TypedQuery<X47BDBEntityForAlarmEvent> query = this.getEntityManager()
														  .createNamedQuery(namedQuery,X47BDBEntityForAlarmEvent.class)
														  .setParameter("startDate",dateRange.getLowerBound(),TemporalType.TIMESTAMP)
														  .setParameter("endDate",dateRange.getUpperBound(),TemporalType.TIMESTAMP)
														  .setParameter("id",id.asString());
		query.setHint(QueryHints.READ_ONLY,HintValues.TRUE);
		Collection<X47BDBEntityForAlarmEvent> dbEntities = query.getResultList();

		// Return
		return FindResultBuilder.using(securityContext)
								.on(_modelObjectType)
								.foundDBEntities(dbEntities)
								.transformedToModelObjectsUsing(_transformer);
	}
}
