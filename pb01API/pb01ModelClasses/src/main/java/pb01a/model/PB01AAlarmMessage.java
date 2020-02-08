package pb01a.model;

import java.util.Collection;
import java.util.Date;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import pb01a.model.PB01AAlarmMessageEntityAbstracts.PB01AAlarmMessageAbstractForDivision;
import pb01a.model.PB01AAlarmMessageEntityAbstracts.PB01AAlarmMessageAbstractForLocation;
import pb01a.model.PB01AAlarmMessageEntityAbstracts.PB01AAlarmMessageAbstractForOrganization;
import pb01a.model.PB01AAlarmMessageEntityAbstracts.PB01AAlarmMessageAbstractForService;
import pb01a.model.PB01AAlarmMessageEntityAbstracts.PB01AAlarmMessageAbstractForWorkPlace;
import pb01a.model.oids.PB01APanicButtonOIDs.PB01AAlarmEventOID;
import r01f.model.PersistableModelObject;
import r01f.objectstreamer.annotations.MarshallField;
import r01f.objectstreamer.annotations.MarshallField.DateFormat;
import r01f.objectstreamer.annotations.MarshallField.MarshallDateFormat;
import r01f.objectstreamer.annotations.MarshallField.MarshallFieldAsXml;
import r01f.objectstreamer.annotations.MarshallType;
import r01f.types.contact.EMail;
import r01f.types.contact.Phone;
import r01f.util.types.collections.CollectionUtils;

/**
 * Encapsulates all data about a message to be sent
 * This model object is NOT persisted (it's NOT a {@link PersistableModelObject} instance) since it's composed
 * when handling the {@link PB01AAlarmEvent} creation event from other model objects
 */
@MarshallType(as="alarmMessage")
@Accessors(prefix="_")
@Slf4j
public class PB01AAlarmMessage
  implements PB01AModelObject {

	private static final long serialVersionUID = -7549631620318919359L;

/////////////////////////////////////////////////////////////////////////////////////////
//  FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	@MarshallField(as="alarmEventOid",
			   	   whenXml=@MarshallFieldAsXml(attr=true))
	@Getter @Setter private PB01AAlarmEventOID _alarmEventOid;

	@MarshallField(as="dateTime",dateFormat=@MarshallDateFormat(use=DateFormat.ISO8601),
				   whenXml=@MarshallFieldAsXml(attr=true))
	@Getter @Setter private Date _dateTime;

	@MarshallField(as="organization",
			   	   whenXml=@MarshallFieldAsXml(attr=true))
	@Getter @Setter private PB01AAlarmMessageAbstractForOrganization _organization;

	@MarshallField(as="division",
			   	   whenXml=@MarshallFieldAsXml(attr=true))
	@Getter @Setter private PB01AAlarmMessageAbstractForDivision _division;

	@MarshallField(as="service",
			   	   whenXml=@MarshallFieldAsXml(attr=true))
	@Getter @Setter private PB01AAlarmMessageAbstractForService _service;

	@MarshallField(as="location",
			   	   whenXml=@MarshallFieldAsXml(attr=true))
	@Getter @Setter private PB01AAlarmMessageAbstractForLocation _location;

	@MarshallField(as="workPlace",
			   	   whenXml=@MarshallFieldAsXml(attr=true))
	@Getter @Setter private PB01AAlarmMessageAbstractForWorkPlace _workPlace;

	@MarshallField(as="phones",
			   	   whenXml=@MarshallFieldAsXml(collectionElementName="phone"))
	@Getter @Setter private Collection<Phone> _phones;

	@MarshallField(as="mails",
			   	   whenXml=@MarshallFieldAsXml(collectionElementName="mail"))
	@Getter @Setter private Collection<EMail> _mails;
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * @return a {@link Collection} of sanitized phones (removes invalid phones an sets a valid format)
	 */
	public Collection<Phone> getPhonesSanitized() {
		if (CollectionUtils.isNullOrEmpty(_phones)) return null;
		Collection<Phone> outPhones = FluentIterable.from(_phones)
													// Filter NOT valid phones
												    .filter(new Predicate<Phone>() {
																		@Override
																		public boolean apply(final Phone phone) {
																			if (!phone.isValid()) {
																				log.warn("{} phone is NOT valid",phone.asString());
																				return false;
																			}
																			return true;
																		}
													  		})
													// put the phones in an standard format
													.transform(new Function<Phone,Phone>() {
																		@Override
																		public Phone apply(final Phone phone) {
																			return Phone.create(phone.asStringWithoutCountryCode());
																		}
													  		   })
													.toSet();
		return outPhones;
	}

}
