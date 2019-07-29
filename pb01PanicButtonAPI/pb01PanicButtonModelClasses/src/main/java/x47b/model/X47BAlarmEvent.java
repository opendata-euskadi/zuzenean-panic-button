package x47b.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
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
import x47b.model.metadata.X47BMetaDataForAlarmEvent;
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

@ModelObjectData(X47BMetaDataForAlarmEvent.class)
@ConvertToDirtyStateTrackable			// changes in state are tracked
@MarshallType(as="alarmEvent")
@Accessors(prefix="_")
public class X47BAlarmEvent
     extends PersistableModelObjectBase<X47BAlarmEventOID,X47BAlarmEvent>
  implements IndexableModelObject,
  			 X47BModelObject,
  			 SelfValidates<X47BAlarmEvent> {

	private static final long serialVersionUID = 303784316448260274L;

/////////////////////////////////////////////////////////////////////////////////////////
//	FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	@MarshallField(as="timeStamp") 
	@Getter @Setter private Date _timeStamp;

	@MarshallField(as="organization")
	@Getter @Setter private X47BOrganizationalModelObjectRef<X47BOrganizationOID,X47BOrganizationID> _organization;

	@MarshallField(as="division")
	@Getter @Setter private X47BOrganizationalModelObjectRef<X47BOrgDivisionOID,X47BOrgDivisionID> _division;
	
	@MarshallField(as="service")
	@Getter @Setter private X47BOrganizationalModelObjectRef<X47BOrgDivisionServiceOID,X47BOrgDivisionServiceID> _service;
	
	@MarshallField(as="location")
	@Getter @Setter private X47BOrganizationalModelObjectRef<X47BOrgDivisionServiceLocationOID,X47BOrgDivisionServiceLocationID> _location;

	@MarshallField(as="workPlace")
	@Getter @Setter private X47BOrganizationalModelObjectRef<X47BWorkPlaceOID,X47BWorkPlaceID> _workPlace;
	
	@MarshallField(as="notifierResponse")
	@Getter @Setter private X47BNotifierResponse _alarmNotificationMsg;

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
	public ObjectValidationResult<X47BAlarmEvent> validate() {
		return X47BAlarmEventValidators.createAlarmEventObjectValidator()
									  .validate(this);
	}
}
