package x47b.db;

import r01f.persistence.db.TransfersModelObjectStateToDBEntity;
import r01f.persistence.db.TransformsDBEntityIntoModelObject;
import r01f.securitycontext.SecurityContext;
import x47b.db.entities.X47BDBEntityForAlarmEvent;
import x47b.model.X47BAlarmEvent;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionID;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionServiceID;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionServiceLocationID;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrganizationID;
import x47b.model.oids.X47BOrganizationalIDs.X47BWorkPlaceID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceLocationOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrganizationOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BWorkPlaceOID;
import x47b.model.oids.X47BPanicButtonOIDs.X47BAlarmEventOID;
import x47b.model.org.X47BOrganizationalModelObjectRef;

public class X47BAlarmEventDBEntityToAndFromModelObject
  implements TransfersModelObjectStateToDBEntity<X47BAlarmEvent,X47BDBEntityForAlarmEvent>,
  			 TransformsDBEntityIntoModelObject<X47BDBEntityForAlarmEvent,X47BAlarmEvent> {
/////////////////////////////////////////////////////////////////////////////////////////
//  
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void setDBEntityFieldsFromModelObject(final SecurityContext securityContext,
												 final X47BAlarmEvent alarmEvent,final X47BDBEntityForAlarmEvent dbEntity) {
		// oid
		dbEntity.setOid(alarmEvent.getOid().asString());
		
		// org reference
		dbEntity.setOrganizationOid(alarmEvent.getOrganization().getOid().asString());
		dbEntity.setOrganizationId(alarmEvent.getOrganization().getId().asString());
		
		// division reference
		dbEntity.setDivisionOid(alarmEvent.getDivision().getOid().asString());
		dbEntity.setDivisionId(alarmEvent.getDivision().getId().asString());
		
		// service reference
		dbEntity.setServiceOid(alarmEvent.getService().getOid().asString());
		dbEntity.setServiceId(alarmEvent.getService().getId().asString());
		
		// location
		dbEntity.setLocationOid(alarmEvent.getLocation().getOid().asString());
		dbEntity.setLocationId(alarmEvent.getLocation().getId().asString());
		
		dbEntity.setWorkPlaceOid(alarmEvent.getWorkPlace().getOid().asString());
		dbEntity.setWorkPlaceId(alarmEvent.getWorkPlace().getId().asString());
	}
	@Override
	public X47BAlarmEvent dbEntityToModelObject(final SecurityContext securityContext,
								   				final X47BDBEntityForAlarmEvent dbAlarm) {
		X47BAlarmEvent outAlarm = new X47BAlarmEvent();
		
		outAlarm.setOid(X47BAlarmEventOID.forId(dbAlarm.getOid()));
		outAlarm.setOrganization(new X47BOrganizationalModelObjectRef<X47BOrganizationOID,X47BOrganizationID>(X47BOrganizationOID.forId(dbAlarm.getOrganizationOid()),
																											  X47BOrganizationID.forId(dbAlarm.getOrganizationId())));
		outAlarm.setDivision(new X47BOrganizationalModelObjectRef<X47BOrgDivisionOID,X47BOrgDivisionID>(X47BOrgDivisionOID.forId(dbAlarm.getDivisionOid()),
																									    X47BOrgDivisionID.forId(dbAlarm.getDivisionId())));
		outAlarm.setService(new X47BOrganizationalModelObjectRef<X47BOrgDivisionServiceOID,X47BOrgDivisionServiceID>(X47BOrgDivisionServiceOID.forId(dbAlarm.getServiceOid()),
																									    			 X47BOrgDivisionServiceID.forId(dbAlarm.getServiceId())));
		outAlarm.setLocation(new X47BOrganizationalModelObjectRef<X47BOrgDivisionServiceLocationOID,X47BOrgDivisionServiceLocationID>(X47BOrgDivisionServiceLocationOID.forId(dbAlarm.getLocationOid()),
																									    			 				  X47BOrgDivisionServiceLocationID.forId(dbAlarm.getLocationId())));
		outAlarm.setWorkPlace(new X47BOrganizationalModelObjectRef<X47BWorkPlaceOID,X47BWorkPlaceID>(X47BWorkPlaceOID.forId(dbAlarm.getWorkPlaceOid()),
																						 			 X47BWorkPlaceID.forId(dbAlarm.getWorkPlaceId())));
		
		outAlarm.setTimeStamp(dbAlarm.getCreateTimeStamp());
		
		return outAlarm;
	}
}
