package pb01c.db;

import pb01a.model.PB01AAlarmEvent;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionID;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionServiceID;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionServiceLocationID;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrganizationID;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AWorkPlaceID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionServiceLocationOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionServiceOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrganizationOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AWorkPlaceOID;
import pb01a.model.oids.PB01APanicButtonOIDs.PB01AAlarmEventOID;
import pb01a.model.org.PB01AOrgObjectRef;
import pb01c.db.entities.PB01CDBEntityForAlarmEvent;
import r01f.persistence.db.TransfersModelObjectStateToDBEntity;
import r01f.persistence.db.TransformsDBEntityIntoModelObject;
import r01f.securitycontext.SecurityContext;

public class PB01CAlarmEventDBEntityToAndFromModelObject
  implements TransfersModelObjectStateToDBEntity<PB01AAlarmEvent,PB01CDBEntityForAlarmEvent>,
  			 TransformsDBEntityIntoModelObject<PB01CDBEntityForAlarmEvent,PB01AAlarmEvent> {
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void setDBEntityFieldsFromModelObject(final SecurityContext securityContext,
												 final PB01AAlarmEvent alarmEvent,final PB01CDBEntityForAlarmEvent dbEntity) {
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
	public PB01AAlarmEvent dbEntityToModelObject(final SecurityContext securityContext,
								   				final PB01CDBEntityForAlarmEvent dbAlarm) {
		PB01AAlarmEvent outAlarm = new PB01AAlarmEvent();

		outAlarm.setOid(PB01AAlarmEventOID.forId(dbAlarm.getOid()));
		outAlarm.setOrganization(new PB01AOrgObjectRef<PB01AOrganizationOID,PB01AOrganizationID>(PB01AOrganizationOID.forId(dbAlarm.getOrganizationOid()),
																											  PB01AOrganizationID.forId(dbAlarm.getOrganizationId())));
		outAlarm.setDivision(new PB01AOrgObjectRef<PB01AOrgDivisionOID,PB01AOrgDivisionID>(PB01AOrgDivisionOID.forId(dbAlarm.getDivisionOid()),
																									    PB01AOrgDivisionID.forId(dbAlarm.getDivisionId())));
		outAlarm.setService(new PB01AOrgObjectRef<PB01AOrgDivisionServiceOID,PB01AOrgDivisionServiceID>(PB01AOrgDivisionServiceOID.forId(dbAlarm.getServiceOid()),
																									    			 PB01AOrgDivisionServiceID.forId(dbAlarm.getServiceId())));
		outAlarm.setLocation(new PB01AOrgObjectRef<PB01AOrgDivisionServiceLocationOID,PB01AOrgDivisionServiceLocationID>(PB01AOrgDivisionServiceLocationOID.forId(dbAlarm.getLocationOid()),
																									    			 				  PB01AOrgDivisionServiceLocationID.forId(dbAlarm.getLocationId())));
		outAlarm.setWorkPlace(new PB01AOrgObjectRef<PB01AWorkPlaceOID,PB01AWorkPlaceID>(PB01AWorkPlaceOID.forId(dbAlarm.getWorkPlaceOid()),
																						 			 PB01AWorkPlaceID.forId(dbAlarm.getWorkPlaceId())));

		outAlarm.setDateTime(dbAlarm.getCreateTimeStamp());

		return outAlarm;
	}
}
