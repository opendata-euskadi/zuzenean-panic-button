package pb01a.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import pb01a.model.metadata.PB01AMetaDataForAlarmEvent;
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
import r01f.aspects.interfaces.dirtytrack.ConvertToDirtyStateTrackable;
import r01f.model.IndexableModelObject;
import r01f.model.PersistableModelObjectBase;
import r01f.model.metadata.annotations.ModelObjectData;
import r01f.objectstreamer.annotations.MarshallField;
import r01f.objectstreamer.annotations.MarshallType;
import r01f.types.Path;
import r01f.util.types.Paths;
import r01f.validation.ObjectValidationResult;
import r01f.validation.SelfValidates;

@ModelObjectData(PB01AMetaDataForAlarmEvent.class)
@ConvertToDirtyStateTrackable			// changes in state are tracked
@MarshallType(as="alarmEvent")
@Accessors(prefix="_")
public class PB01AAlarmEvent
     extends PersistableModelObjectBase<PB01AAlarmEventOID,PB01AAlarmEvent>
  implements IndexableModelObject,
  			 PB01AModelObject,
  			 SelfValidates<PB01AAlarmEvent> {

	private static final long serialVersionUID = 303784316448260274L;

/////////////////////////////////////////////////////////////////////////////////////////
//	FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	@MarshallField(as="time")
	@Getter @Setter private Date _dateTime;

	@MarshallField(as="organization")
	@Getter @Setter private PB01AOrgObjectRef<PB01AOrganizationOID,PB01AOrganizationID> _organization;

	@MarshallField(as="division")
	@Getter @Setter private PB01AOrgObjectRef<PB01AOrgDivisionOID,PB01AOrgDivisionID> _division;

	@MarshallField(as="service")
	@Getter @Setter private PB01AOrgObjectRef<PB01AOrgDivisionServiceOID,PB01AOrgDivisionServiceID> _service;

	@MarshallField(as="location")
	@Getter @Setter private PB01AOrgObjectRef<PB01AOrgDivisionServiceLocationOID,PB01AOrgDivisionServiceLocationID> _location;

	@MarshallField(as="workPlace")
	@Getter @Setter private PB01AOrgObjectRef<PB01AWorkPlaceOID,PB01AWorkPlaceID> _workPlace;

	@MarshallField(as="notifierResponse")
	@Getter @Setter private PB01ANotifierResponse _alarmNotificationMsg;

/////////////////////////////////////////////////////////////////////////////////////////
//  UTIL METHODS
/////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Returns a path with the event hierarchy
	 * @return
	 */
	public Path getHiearchyPath() {
		return Paths.forPaths()
					.join(_organization.getId(),
						  _division.getId(),
						  _service.getId(),
						  _location.getId(),
						  _workPlace.getId());
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  VALIDATION
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public ObjectValidationResult<PB01AAlarmEvent> validate() {
		return PB01AAlarmEventValidators.createAlarmEventObjectValidator()
									  .validate(this);
	}
}
