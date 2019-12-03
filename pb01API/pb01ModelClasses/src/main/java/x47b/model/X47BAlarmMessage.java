package x47b.model;

import java.util.Collection;
import java.util.Date;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import r01f.model.PersistableModelObject;
import r01f.objectstreamer.annotations.MarshallField;
import r01f.objectstreamer.annotations.MarshallField.DateFormat;
import r01f.objectstreamer.annotations.MarshallField.MarshallDateFormat;
import r01f.objectstreamer.annotations.MarshallField.MarshallFieldAsXml;
import r01f.objectstreamer.annotations.MarshallType;
import r01f.types.contact.EMail;
import r01f.types.contact.Phone;
import r01f.util.types.collections.CollectionUtils;
import x47b.model.X47BAlarmMessageEntityAbstracts.X47BAlarmMessageAbstractForDivision;
import x47b.model.X47BAlarmMessageEntityAbstracts.X47BAlarmMessageAbstractForLocation;
import x47b.model.X47BAlarmMessageEntityAbstracts.X47BAlarmMessageAbstractForOrganization;
import x47b.model.X47BAlarmMessageEntityAbstracts.X47BAlarmMessageAbstractForService;
import x47b.model.X47BAlarmMessageEntityAbstracts.X47BAlarmMessageAbstractForWorkPlace;
import x47b.model.oids.X47BPanicButtonOIDs.X47BAlarmEventOID;

/**
 * Encapsulates all data about a message to be sent
 * This model object is NOT persisted (it's NOT a {@link PersistableModelObject} instance) since it's composed
 * when handling the {@link X47BAlarmEvent} creation event from other model objects
 */
@MarshallType(as="alarmMessage")
@Accessors(prefix="_")
@Slf4j
public class X47BAlarmMessage
  implements X47BModelObject {

	private static final long serialVersionUID = -7549631620318919359L;

/////////////////////////////////////////////////////////////////////////////////////////
//  FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	@MarshallField(as="alarmEventOid",
			   	   whenXml=@MarshallFieldAsXml(attr=true))
	@Getter @Setter private X47BAlarmEventOID _alarmEventOid;

	@MarshallField(as="dateTime",dateFormat=@MarshallDateFormat(use=DateFormat.ISO8601),
				   whenXml=@MarshallFieldAsXml(attr=true))
	@Getter @Setter private Date _dateTime;

	@MarshallField(as="organization",
			   	   whenXml=@MarshallFieldAsXml(attr=true))
	@Getter @Setter private X47BAlarmMessageAbstractForOrganization _organization;

	@MarshallField(as="division",
			   	   whenXml=@MarshallFieldAsXml(attr=true))
	@Getter @Setter private X47BAlarmMessageAbstractForDivision _division;

	@MarshallField(as="service",
			   	   whenXml=@MarshallFieldAsXml(attr=true))
	@Getter @Setter private X47BAlarmMessageAbstractForService _service;

	@MarshallField(as="location",
			   	   whenXml=@MarshallFieldAsXml(attr=true))
	@Getter @Setter private X47BAlarmMessageAbstractForLocation _location;

	@MarshallField(as="workPlace",
			   	   whenXml=@MarshallFieldAsXml(attr=true))
	@Getter @Setter private X47BAlarmMessageAbstractForWorkPlace _workPlace;

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
